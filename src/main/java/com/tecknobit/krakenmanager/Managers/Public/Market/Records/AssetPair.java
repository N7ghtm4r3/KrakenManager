package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class AssetPair extends KrakenManager.KrakenResponse {

    public static final String INFO = "info";
    public static final String INFO_LEVERAGE = "leverage";
    public static final String INFO_FEES = "fees";
    public static final String INFO_MARGIN = "margin";
    private final String altName;
    private final String wsName;
    private final String aClassBase;
    private final String base;
    private final String aClassQuote;
    private final String quote;
    private final String lot;
    private final int pairDecimals;
    private final int lotDecimals;
    private final int lotMultiplier;
    private final int[] leverageBuy;
    private final int[] leverageSell;
    private final ArrayList<Fee> fees;
    private final ArrayList<Fee> makerFees;
    private final String feeVolumeCurrency;
    private final double marginCall;
    private final double marginStop;
    private final double minOrder;

    public AssetPair(JSONObject jsonResponse, String altName, String wsName, String aClassBase, String base,
                     String aClassQuote, String quote, String lot, int pairDecimals, int lotDecimals, int lotMultiplier,
                     String feeVolumeCurrency, double marginCall, double marginStop, double minOrder, String pairIndex) {
        super(jsonResponse);
        this.altName = altName;
        this.wsName = wsName;
        this.aClassBase = aClassBase;
        this.base = base;
        this.aClassQuote = aClassQuote;
        this.quote = quote;
        this.lot = lot;
        this.pairDecimals = pairDecimals;
        this.lotDecimals = lotDecimals;
        this.lotMultiplier = lotMultiplier;
        JSONObject assetPair = getResult();
        assetPair = assetPair.getJSONObject(pairIndex);
        if(assetPair != null){
            JsonHelper listHelper = new JsonHelper(assetPair);
            leverageBuy = loadLeverageList(listHelper.getJSONArray("leverage_buy"));
            leverageSell = loadLeverageList(listHelper.getJSONArray("leverage_sell"));
            fees = loadFeesList(listHelper.getJSONArray("fees"));
            makerFees = loadFeesList(listHelper.getJSONArray("fees_maker"));
        }else{
            leverageBuy = new int[]{};
            leverageSell = new int[]{};
            fees = new ArrayList<>();
            makerFees = new ArrayList<>();
        }
        this.feeVolumeCurrency = feeVolumeCurrency;
        this.marginCall = marginCall;
        this.marginStop = marginStop;
        this.minOrder = minOrder;
    }

    public AssetPair(String altName, String wsName, String aClassBase, String base, String aClassQuote, String quote,
                     String lot, int pairDecimals, int lotDecimals, int lotMultiplier, int[] leverageBuy, int[] leverageSell,
                     ArrayList<Fee> fees, ArrayList<Fee> makerFees, String feeVolumeCurrency, double marginCall,
                     double marginStop, double minOrder) {
        super(null);
        this.altName = altName;
        this.wsName = wsName;
        this.aClassBase = aClassBase;
        this.base = base;
        this.aClassQuote = aClassQuote;
        this.quote = quote;
        this.lot = lot;
        this.pairDecimals = pairDecimals;
        this.lotDecimals = lotDecimals;
        this.lotMultiplier = lotMultiplier;
        this.leverageBuy = leverageBuy;
        this.leverageSell = leverageSell;
        this.fees = fees;
        this.makerFees = makerFees;
        this.feeVolumeCurrency = feeVolumeCurrency;
        this.marginCall = marginCall;
        this.marginStop = marginStop;
        this.minOrder = minOrder;
    }

    public AssetPair(JSONObject jsonAsset) {
        super(null);
        JsonHelper asset = new JsonHelper(jsonAsset);
        altName = asset.getString("altname");
        wsName = asset.getString("wsname");
        aClassBase = asset.getString("aclass_base");
        base = asset.getString("base");
        aClassQuote = asset.getString("aclass_quote");
        quote = asset.getString("quote");
        lot = asset.getString("lot");
        pairDecimals = asset.getInt("pair_decimals");
        lotDecimals = asset.getInt("lot_decimals");
        lotMultiplier = asset.getInt("lot_multiplier");
        leverageBuy = loadLeverageList(asset.getJSONArray("leverage_buy"));
        leverageSell = loadLeverageList(asset.getJSONArray("leverage_sell"));
        fees = loadFeesList(asset.getJSONArray("fees"));
        makerFees = loadFeesList(asset.getJSONArray("fees_maker"));
        feeVolumeCurrency = asset.getString("fee_volume_currency");
        marginCall = asset.getDouble("margin_call");
        marginStop = asset.getDouble("margin_stop");
        minOrder = asset.getDouble("ordermin");
    }

    private int[] loadLeverageList(JSONArray jsonList){
        if(jsonList != null){
            int elements = jsonList.length();
            final int[] list = new int[elements];
            for (int j = 0; j < elements; j++)
                list[j] = jsonList.getInt(j);
            return list;
        }
        return new int[]{};
    }

    private ArrayList<Fee> loadFeesList(JSONArray jsonList){
        ArrayList<Fee> feesList = new ArrayList<>();
        if(jsonList != null)
            for (int j = 0; j < jsonList.length(); j++)
                feesList.add(new Fee(jsonList.getJSONArray(j)));
        return feesList;
    }

    public String getAltName() {
        return altName;
    }

    public String getWsName() {
        return wsName;
    }

    public String getaClassBase() {
        return aClassBase;
    }

    public String getBase() {
        return base;
    }

    public String getaClassQuote() {
        return aClassQuote;
    }

    public String getQuote() {
        return quote;
    }

    @Deprecated
    public String getLot() {
        return lot;
    }

    public int getPairDecimals() {
        return pairDecimals;
    }

    public int getLotDecimals() {
        return lotDecimals;
    }

    public int getLotMultiplier() {
        return lotMultiplier;
    }

    public int[] getLeverageBuy() {
        return leverageBuy;
    }

    public int[] getLeverageSell() {
        return leverageSell;
    }

    public ArrayList<Fee> getFees() {
        return fees;
    }

    public ArrayList<Fee> getMakerFees() {
        return makerFees;
    }

    public String getFeeVolumeCurrency() {
        return feeVolumeCurrency;
    }

    public double getMarginCall() {
        return marginCall;
    }

    public double getMarginStop() {
        return marginStop;
    }

    public double getMinOrder() {
        return minOrder;
    }

    @Override
    public String toString() {
        return "AssetPair{" +
                "altName='" + altName + '\'' +
                ", wsName='" + wsName + '\'' +
                ", aClassBase='" + aClassBase + '\'' +
                ", base='" + base + '\'' +
                ", aClassQuote='" + aClassQuote + '\'' +
                ", quote='" + quote + '\'' +
                ", lot='" + lot + '\'' +
                ", pairDecimals=" + pairDecimals +
                ", lotDecimals=" + lotDecimals +
                ", lotMultiplier=" + lotMultiplier +
                ", leverageBuy=" + Arrays.toString(leverageBuy) +
                ", leverageSell=" + Arrays.toString(leverageSell) +
                ", fees=" + fees +
                ", makerFees=" + makerFees +
                ", feeVolumeCurrency='" + feeVolumeCurrency + '\'' +
                ", marginCall=" + marginCall +
                ", marginStop=" + marginStop +
                ", minOrder=" + minOrder +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    public static class Fee {

        private final int[] fees;

        public Fee(JSONArray jsonFees) {
            int feesNumber = jsonFees.length();
            fees = new int[feesNumber];
            for (int j = 0; j < feesNumber; j++)
                fees[j] = jsonFees.getInt(j);
        }

        public int[] getFees() {
            return fees;
        }

        @Override
        public String toString() {
            return "Fee{" +
                    "fees=" + Arrays.toString(fees) +
                    '}';
        }

    }

}
