package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class TradeVolume extends KrakenManager.KrakenResponse {

    private final String currency;
    private final double volume;
    private final ArrayList<TradeFee> fees;
    private final ArrayList<TradeFee> makerFees;

    public TradeVolume(JSONObject jsonResponse, String currency, double volume, double fee, double minFee,
                       double maxFee, double nextFee, double tierVolume, double nextVolume, ArrayList<TradeFee> fees,
                       ArrayList<TradeFee> makerFees) {
        super(jsonResponse);
        this.currency = currency;
        this.volume = volume;
        this.fees = fees;
        this.makerFees = makerFees;
    }

    public TradeVolume(String currency, double volume, double fee, double minFee, double maxFee, double nextFee,
                       double tierVolume, double nextVolume, ArrayList<TradeFee> fees, ArrayList<TradeFee> makerFees) {
        super(null);
        this.currency = currency;
        this.volume = volume;
        this.fees = fees;
        this.makerFees = makerFees;
    }

    /**
     * Constructor to init a {@link TradeVolume}
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
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    public static class TradeFee {

        private static final String[] feeKeys = new String[]{"min_fee", "max_fee", "next_fee", "tier_volume", "next_volume"};
        private final String pair;
        private final double fee;
        private double minFee;
        private double maxFee;
        private double nextFee;
        private double tierVolume;
        private double nextVolume;

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

        public TradeFee(JSONObject jsonFee, String pair){
            this.pair = pair;
            fee = jsonFee.getDouble("fee");
            loadFeesDetails(jsonFee);
        }

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
