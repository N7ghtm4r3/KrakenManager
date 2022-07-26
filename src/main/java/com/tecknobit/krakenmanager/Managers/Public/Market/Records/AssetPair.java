package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code AssetPair} class is useful to format AssetPair data object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
 *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class AssetPair extends KrakenManager.KrakenResponse {

    /**
     * {@code INFO} is constant for info
     * **/
    public static final String INFO = "info";

    /**
     * {@code INFO_LEVERAGE} is constant for info leverage
     * **/
    public static final String INFO_LEVERAGE = "leverage";

    /**
     * {@code INFO_FEES} is constant for info fees
     * **/
    public static final String INFO_FEES = "fees";

    /**
     * {@code INFO_MARGIN} is constant for info margin
     * **/
    public static final String INFO_MARGIN = "margin";

    /**
     * {@code altName} is instance that memorizes alt name value
     * **/
    private final String altName;

    /**
     * {@code wsName} is instance that memorizes ws name value
     * **/
    private final String wsName;

    /**
     * {@code aClassBase} is instance that memorizes asset class base value
     * **/
    private final String aClassBase;

    /**
     * {@code base} is instance that memorizes base value
     * **/
    private final String base;

    /**
     * {@code aClassQuote} is instance that memorizes asset class quote value
     * **/
    private final String aClassQuote;

    /**
     * {@code quote} is instance that memorizes quote value
     * **/
    private final String quote;

    /**
     * {@code lot} is instance that memorizes lot value
     * **/
    private final String lot;

    /**
     * {@code pairDecimals} is instance that memorizes pair decimals value
     * **/
    private final int pairDecimals;

    /**
     * {@code lotDecimals} is instance that memorizes lot decimals value
     * **/
    private final int lotDecimals;

    /**
     * {@code lotMultiplier} is instance that memorizes lot multiplier value
     * **/
    private final int lotMultiplier;

    /**
     * {@code leverageBuy} is instance that memorizes list of leverage buy
     * **/
    private final int[] leverageBuy;

    /**
     * {@code leverageSell} is instance that memorizes list of leverage sell
     * **/
    private final int[] leverageSell;

    /**
     * {@code fees} is instance that memorizes list of {@link Fee}
     * **/
    private final ArrayList<Fee> fees;

    /**
     * {@code makerFees} is instance that memorizes list of maker {@link Fee}
     * **/
    private final ArrayList<Fee> makerFees;

    /**
     * {@code feeVolumeCurrency} is instance that memorizes fee volume currency value
     * **/
    private final String feeVolumeCurrency;

    /**
     * {@code marginCall} is instance that memorizes margin call value
     * **/
    private final double marginCall;

    /**
     * {@code marginStop} is instance that memorizes margin stop value
     * **/
    private final double marginStop;

    /**
     * {@code minOrder} is instance that memorizes minimum order value
     * **/
    private final double minOrder;

    /** Constructor to init a {@link AssetPair}
     * @param jsonResponse: base json response
     * @param altName: alt name value
     * @param wsName: ws name value
     * @param aClassBase: asset class base value
     * @param base: base value
     * @param aClassQuote: asset class quote value
     * @param quote: quote value
     * @param lot: lot value
     * @param pairDecimals: pair decimals value
     * @param lotDecimals: lot decimals value
     * @param lotMultiplier: lot multiplier value
     * @param feeVolumeCurrency: fee volume currency value
     * @param marginCall: margin call value
     * @param marginStop: margin stop value
     * @param minOrder: minimum order value
     * @param pairIndex: symbol of pairs es. XXBTCZUSD
     * **/
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

    /** Constructor to init a {@link AssetPair}
     * @param altName: alt name value
     * @param wsName: ws name value
     * @param aClassBase: asset class base value
     * @param base: base value
     * @param aClassQuote: asset class quote value
     * @param quote: quote value
     * @param lot: lot value
     * @param pairDecimals: pair decimals value
     * @param lotDecimals: lot decimals value
     * @param lotMultiplier: lot multiplier value
     * @param leverageBuy: list of leverage buy
     * @param leverageSell: list of leverage sell
     * @param fees: list of {@link Fee}
     * @param makerFees: list of maker {@link Fee}
     * @param feeVolumeCurrency: fee volume currency value
     * @param marginCall: margin call value
     * @param marginStop: margin stop value
     * @param minOrder: minimum order value
     * **/
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

    /** Constructor to init a {@link AssetPair}
     * @param jsonAsset: asset pair value in JSON format
     * **/
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

    /** Method to assemble a leverage list
     * @param jsonList: jsonObject obtained by response request
     * @return leverage list as int array
     * **/
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

    /** Method to assemble a fees list
     * @param jsonList: jsonObject obtained by response request
     * @return fees list as {@link ArrayList} of {@link Fee}
     * **/
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

    /**
     * The {@code Fee} class is useful to format a fee object
     * **/

    public static class Fee {

        /**
         * {@code fees} is instance that memorizes list of fees
         * **/
        private final int[] fees;

        /** Constructor to init a {@link Fee}
         * @param jsonFees: fees data in JSON format
         * **/
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
