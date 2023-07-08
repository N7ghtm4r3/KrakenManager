package com.tecknobit.krakenmanager.privates.userdata.records.trades;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.krakenmanager.KrakenManager;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code TradeVolume} class is useful to format trade volume object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
 * Get Trade Volume</a>
 */
public class TradeVolume extends KrakenManager.KrakenResponse {

    /**
     * {@code currency} is instance that memorizes volume currency
     */
    private final String currency;

    /**
     * {@code volume} is instance that memorizes current discount volume
     */
    private final double volume;

    /**
     * {@code fees} is instance that memorizes list of fees as {@link TradeFee}
     */
    private final ArrayList<TradeFee> fees;

    /**
     * {@code makerFees} is instance that memorizes list of maker fees as {@link TradeFee}
     */
    private final ArrayList<TradeFee> makerFees;

    /**
     * Constructor to init a {@link TradeVolume} object
     *
     * @param currency:  volume currency
     * @param volume:    current discount volume
     * @param fees:      list of fees as {@link TradeFee}
     * @param makerFees: list of maker fees as {@link TradeFee}
     */
    public TradeVolume(String currency, double volume, ArrayList<TradeFee> fees, ArrayList<TradeFee> makerFees) {
        super(null);
        this.currency = currency;
        this.volume = volume;
        this.fees = fees;
        this.makerFees = makerFees;
    }

    /**
     * Constructor to init a {@link TradeVolume} object
     * @param jsonResponse: base json response
     */
    public TradeVolume(JSONObject jsonResponse) {
        super(jsonResponse);
        currency = result.getString("currency");
        volume = result.getDouble("volume", 0);
        fees = assembleTradeFeesList(result.getJSONObjectSource(), "fees");
        makerFees = assembleTradeFeesList(result.getJSONObjectSource(), "fees_maker");
    }

    /**
     * Method to assemble a trade fee list
     *
     * @param fees: obtained by response request
     * @param key:  key of list in JSON
     * @return trade fee list as {@link ArrayList} of {@link TradeFee}
     */
    private ArrayList<TradeFee> assembleTradeFeesList(JSONObject fees, String key) {
        ArrayList<TradeFee> tradeFees = new ArrayList<>();
        fees = JsonHelper.getJSONObject(fees, key, new JSONObject());
        for (String fee : fees.keySet())
            tradeFees.add(new TradeFee(fees.getJSONObject(fee), fee));
        return tradeFees;
    }

    /**
     * Method to get {@link #currency} instance <br>
     * No-any params required
     *
     * @return {@link #currency} instance as {@link String}
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Method to get {@link #volume} instance <br>
     * No-any params required
     *
     * @return {@link #volume} instance as double
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Method to get {@link #volume} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #volume} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     */
    public double getVolume(int decimals) {
        return roundValue(volume, decimals);
    }

    /**
     * Method to get {@link #fees} instance <br>
     * No-any params required
     *
     * @return {@link #fees} instance as {@link ArrayList} of {@link TradeFee}
     */
    public ArrayList<TradeFee> getFees() {
        return fees;
    }

    /**
     * Method to get {@link #makerFees} instance <br>
     * No-any params required
     *
     * @return {@link #makerFees} instance as {@link ArrayList} of {@link TradeFee}
     */
    public ArrayList<TradeFee> getMakerFees() {
        return makerFees;
    }

    /**
     * The {@code TradeFee} class is useful to format trade fee object
     */
    public static class TradeFee {

        /**
         * {@code feeKeys} is instance that memorizes keys for read trade fee details from JSON
         */
        private static final String[] feeKeys = new String[]{"min_fee", "max_fee", "next_fee", "tier_volume", "next_volume"};

        /**
         * {@code pair} is instance that memorizes pair value
         */
        private final String pair;

        /**
         * {@code fee} is instance that memorizes current fee (in percent)
         */
        private final double fee;

        /**
         * {@code minFee} is instance that memorizes minimum fee for pair (if not fixed fee)
         */
        private double minFee;

        /**
         * {@code maxFee} is instance that memorizes maximum fee for pair (if not fixed fee)
         */
        private double maxFee;

        /**
         * {@code nextFee} is instance that memorizes next tier's fee for pair (if not fixed fee, null if at lowest fee tier)
         */
        private double nextFee;

        /**
         * {@code tierVolume} is instance that memorizes volume level of current tier (if not fixed fee. null if at lowest fee tier)
         */
        private double tierVolume;

        /**
         * {@code tierVolume} is instance that memorizes volume level of next tier (if not fixed fee. null if at lowest fee tier)
         */
        private double nextVolume;

        /** Constructor to init a {@link TradeFee} object
         * @param pair: pair value
         * @param fee: minimum fee for pair (if not fixed fee)
         * @param minFee: type value
         * @param maxFee: maximum fee for pair (if not fixed fee)
         * @param nextFee: next tier's fee for pair (if not fixed fee, null if at lowest fee tier)
         * @param tierVolume: volume level of current tier (if not fixed fee. null if at lowest fee tier)
         * @param nextVolume: is instance that memorizes volume level of next tier (if not fixed fee. null if at lowest fee tier)
         */
        public TradeFee(String pair, double fee, double minFee, double maxFee, double nextFee, double tierVolume,
                        double nextVolume) {
            this.pair = pair;
            this.fee = fee;
            this.minFee = minFee;
            this.maxFee = maxFee;
            this.nextFee = nextFee;
            this.tierVolume = tierVolume;
            this.nextVolume = nextVolume;
        }

        /** Constructor to init a {@link TradeFee} object
         * @param jsonFee: fee details in {@code "JSON"} format
         * @param pair: pair value
         */
        public TradeFee(JSONObject jsonFee, String pair){
            this.pair = pair;
            fee = jsonFee.getDouble("fee");
            loadFeesDetails(jsonFee);
        }

        /**
         * Constructor to init a {@link TradeFee} object
         * @param jsonFee: fee details in {@code "JSON"} format
         */
        private void loadFeesDetails(JSONObject jsonFee) {
            if (!jsonFee.has(feeKeys[0]))
                for (int j = 0; j < feeKeys.length; j++)
                    feeKeys[j] = feeKeys[j].replace("_", "");
            minFee = jsonFee.getDouble(feeKeys[0]);
            maxFee = jsonFee.getDouble(feeKeys[1]);
            nextFee = jsonFee.getDouble(feeKeys[2]);
            tierVolume = jsonFee.getDouble(feeKeys[3]);
            nextVolume = jsonFee.getDouble(feeKeys[4]);
        }

        /**
         * Method to get {@link #pair} instance <br>
         * No-any params required
         *
         * @return {@link #pair} instance as {@link String}
         */
        public String getPair() {
            return pair;
        }

        /**
         * Method to get {@link #fee} instance <br>
         * No-any params required
         *
         * @return {@link #fee} instance as double
         */
        public double getFee() {
            return fee;
        }

        /**
         * Method to get {@link #fee} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #fee} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getFee(int decimals) {
            return roundValue(fee, decimals);
        }

        /**
         * Method to get {@link #minFee} instance <br>
         * No-any params required
         *
         * @return {@link #minFee} instance as double
         */
        public double getMinFee() {
            return minFee;
        }

        /**
         * Method to get {@link #minFee} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #minFee} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getMinFee(int decimals) {
            return roundValue(minFee, decimals);
        }

        /**
         * Method to get {@link #maxFee} instance <br>
         * No-any params required
         *
         * @return {@link #maxFee} instance as double
         */
        public double getMaxFee() {
            return maxFee;
        }

        /**
         * Method to get {@link #maxFee} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #maxFee} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getMaxFee(int decimals) {
            return roundValue(maxFee, decimals);
        }

        /**
         * Method to get {@link #nextFee} instance <br>
         * No-any params required
         *
         * @return {@link #nextFee} instance as double
         */
        public double getNextFee() {
            return nextFee;
        }

        /**
         * Method to get {@link #nextFee} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #nextFee} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getNextFee(int decimals) {
            return roundValue(nextFee, decimals);
        }

        /**
         * Method to get {@link #tierVolume} instance <br>
         * No-any params required
         *
         * @return {@link #tierVolume} instance as double
         */
        public double getTierVolume() {
            return tierVolume;
        }

        /**
         * Method to get {@link #tierVolume} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #tierVolume} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getTierVolume(int decimals) {
            return roundValue(tierVolume, decimals);
        }

        /**
         * Method to get {@link #nextVolume} instance <br>
         * No-any params required
         *
         * @return {@link #nextVolume} instance as double
         */
        public double getNextVolume() {
            return nextVolume;
        }

        /**
         * Method to get {@link #nextVolume} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #nextVolume} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getNextVolume(int decimals) {
            return roundValue(nextVolume, decimals);
        }

        /**
         * Returns a string representation of the object <br>
         * No-any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
