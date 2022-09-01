package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tecknobit.apimanager.Tools.Trading.TradingTools.roundValue;

/**
 * The {@code TradeVolume} class is useful to format trade volume object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
 * https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
 **/

public class TradeVolume extends KrakenManager.KrakenResponse {

    /**
     * {@code currency} is instance that memorizes volume currency
     * **/
    private final String currency;

    /**
     * {@code volume} is instance that memorizes current discount volume
     * **/
    private final double volume;

    /**
     * {@code fees} is instance that memorizes list of fees as {@link TradeFee}
     * **/
    private final ArrayList<TradeFee> fees;

    /**
     * {@code makerFees} is instance that memorizes list of maker fees as {@link TradeFee}
     * **/
    private final ArrayList<TradeFee> makerFees;

    /** Constructor to init a {@link TradeVolume} object
     * @param jsonResponse: base json response
     * @param currency: volume currency
     * @param volume: current discount volume
     * @param fees: list of fees as {@link TradeFee}
     * @param makerFees: list of maker fees as {@link TradeFee}
     **/
    public TradeVolume(JSONObject jsonResponse, String currency, double volume, ArrayList<TradeFee> fees,
                       ArrayList<TradeFee> makerFees) {
        super(jsonResponse);
        this.currency = currency;
        this.volume = volume;
        this.fees = fees;
        this.makerFees = makerFees;
    }

    /** Constructor to init a {@link TradeVolume} object
     * @param currency: volume currency
     * @param volume: current discount volume
     * @param fees: list of fees as {@link TradeFee}
     * @param makerFees: list of maker fees as {@link TradeFee}
     **/
    public TradeVolume(String currency, double volume, double fee, double minFee, double maxFee, double nextFee,
                       double tierVolume, double nextVolume, ArrayList<TradeFee> fees, ArrayList<TradeFee> makerFees) {
        super(null);
        this.currency = currency;
        this.volume = volume;
        this.fees = fees;
        this.makerFees = makerFees;
    }

    /**
     * Constructor to init a {@link TradeVolume} object
     * @param jsonResponse: base json response
     **/
    public TradeVolume(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject trade = getResult();
        JsonHelper tradeHelper = new JsonHelper(trade);
        currency = tradeHelper.getString("currency");
        volume = tradeHelper.getDouble("volume");
        fees = assembleTradeFeesList(trade, "fees");
        makerFees = assembleTradeFeesList(trade, "fees_maker");
    }

    /** Method to assemble a trade fee list
     * @param fees: jsonObject obtained by response request
     * @param key: key of list in JSON
     * @return trade fee list as {@link ArrayList} of {@link TradeFee}
     * **/
    private ArrayList<TradeFee> assembleTradeFeesList(JSONObject fees, String key){
        ArrayList<TradeFee> tradeFees = new ArrayList<>();
        fees = JsonHelper.getJSONObject(fees, key, new JSONObject());
        for (String fee : fees.keySet())
            tradeFees.add(new TradeFee(fees.getJSONObject(fee), fee));
        return tradeFees;
    }

    public String getCurrency() {
        return currency;
    }

    public double getVolume() {
        return volume;
    }

    /**
     * Method to get {@link #volume} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #volume} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getVolume(int decimals) {
        return roundValue(volume, decimals);
    }

    public ArrayList<TradeFee> getFees() {
        return fees;
    }

    public ArrayList<TradeFee> getMakerFees() {
        return makerFees;
    }

    @Override
    public String toString() {
        return "TradeVolume{" +
                "currency='" + currency + '\'' +
                ", volume=" + volume +
                ", fees=" + fees +
                ", makerFees=" + makerFees +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    /**
     * The {@code TradeFee} class is useful to format trade fee object
     * **/

    public static class TradeFee {

        /**
         * {@code feeKeys} is instance that memorizes keys for read trade fee details from JSON
         * **/
        private static final String[] feeKeys = new String[]{"min_fee", "max_fee", "next_fee", "tier_volume", "next_volume"};

        /**
         * {@code pair} is instance that memorizes pair value
         * **/
        private final String pair;

        /**
         * {@code fee} is instance that memorizes current fee (in percent)
         * **/
        private final double fee;

        /**
         * {@code minFee} is instance that memorizes minimum fee for pair (if not fixed fee)
         * **/
        private double minFee;

        /**
         * {@code maxFee} is instance that memorizes maximum fee for pair (if not fixed fee)
         * **/
        private double maxFee;

        /**
         * {@code nextFee} is instance that memorizes next tier's fee for pair (if not fixed fee, null if at lowest fee tier)
         * **/
        private double nextFee;

        /**
         * {@code tierVolume} is instance that memorizes volume level of current tier (if not fixed fee. null if at lowest fee tier)
         * **/
        private double tierVolume;

        /**
         * {@code tierVolume} is instance that memorizes volume level of next tier (if not fixed fee. null if at lowest fee tier)
         * **/
        private double nextVolume;

        /** Constructor to init a {@link TradeFee} object
         * @param pair: pair value
         * @param fee: minimum fee for pair (if not fixed fee)
         * @param minFee: type value
         * @param maxFee: maximum fee for pair (if not fixed fee)
         * @param nextFee: next tier's fee for pair (if not fixed fee, null if at lowest fee tier)
         * @param tierVolume: volume level of current tier (if not fixed fee. null if at lowest fee tier)
         * @param nextVolume: is instance that memorizes volume level of next tier (if not fixed fee. null if at lowest fee tier)
         **/
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
         * @param jsonFee: fee details in JSON format
         * @param pair: pair value
         **/
        public TradeFee(JSONObject jsonFee, String pair){
            this.pair = pair;
            fee = jsonFee.getDouble("fee");
            loadFeesDetails(jsonFee);
        }

        /**
         * Constructor to init a {@link TradeFee} object
         * @param jsonFee: fee details in JSON format
         **/
        private void loadFeesDetails(JSONObject jsonFee){
            if(!jsonFee.has(feeKeys[0]))
                for (int j = 0; j < feeKeys.length; j++)
                    feeKeys[j] = feeKeys[j].replace("_", "");
            minFee = jsonFee.getDouble(feeKeys[0]);
            maxFee = jsonFee.getDouble(feeKeys[1]);
            nextFee = jsonFee.getDouble(feeKeys[2]);
            tierVolume = jsonFee.getDouble(feeKeys[3]);
            nextVolume = jsonFee.getDouble(feeKeys[4]);
        }

        public String getPair() {
            return pair;
        }

        public double getFee() {
            return fee;
        }

        /**
         * Method to get {@link #fee} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #fee} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getFee(int decimals) {
            return roundValue(fee, decimals);
        }

        public double getMinFee() {
            return minFee;
        }

        /**
         * Method to get {@link #minFee} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #minFee} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getMinFee(int decimals) {
            return roundValue(minFee, decimals);
        }

        public double getMaxFee() {
            return maxFee;
        }

        /**
         * Method to get {@link #maxFee} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #maxFee} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getMaxFee(int decimals) {
            return roundValue(maxFee, decimals);
        }

        public double getNextFee() {
            return nextFee;
        }

        /**
         * Method to get {@link #nextFee} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #nextFee} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getNextFee(int decimals) {
            return roundValue(nextFee, decimals);
        }

        public double getTierVolume() {
            return tierVolume;
        }

        /**
         * Method to get {@link #tierVolume} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #tierVolume} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getTierVolume(int decimals) {
            return roundValue(tierVolume, decimals);
        }

        public double getNextVolume() {
            return nextVolume;
        }

        /**
         * Method to get {@link #nextVolume} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #nextVolume} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getNextVolume(int decimals) {
            return roundValue(nextVolume, decimals);
        }

        @Override
        public String toString() {
            return "TradeFee{" +
                    "fee=" + fee +
                    ", minFee=" + minFee +
                    ", maxFee=" + maxFee +
                    ", nextFee=" + nextFee +
                    ", tierVolume=" + tierVolume +
                    ", nextVolume=" + nextVolume +
                    '}';
        }

    }

}
