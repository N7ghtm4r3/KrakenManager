package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code TradeVolume} class is useful to format trade volume object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
 *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

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
     * @param jsonResponse : base json response
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
     * @param jsonResponse : base json response
     **/
    public TradeVolume(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject trade = getResult();
        this.currency = trade.getString("currency");
        this.volume = trade.getDouble("volume");
        this.fees = assembleTradeFeesList(trade, "fees");
        this.makerFees = assembleTradeFeesList(trade, "fees_maker");
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

        public double getMinFee() {
            return minFee;
        }

        public double getMaxFee() {
            return maxFee;
        }

        public double getNextFee() {
            return nextFee;
        }

        public double getTierVolume() {
            return tierVolume;
        }

        public double getNextVolume() {
            return nextVolume;
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
