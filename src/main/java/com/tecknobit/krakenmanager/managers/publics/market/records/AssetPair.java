package com.tecknobit.krakenmanager.managers.publics.market.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code AssetPair} class is useful to format AssetPair data object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
 * https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
 **/
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
     **/
    private final double marginStop;

    /**
     * {@code minOrder} is instance that memorizes minimum order value
     **/
    private final double minOrder;

    /**
     * {@code minCost} minimum order cost (in terms of quote currency)
     **/
    private final double minCost;

    /**
     * Constructor to init an {@link AssetPair} object
     *
     * @param altName           : alt name value
     * @param wsName            : ws name value
     * @param aClassBase        : asset class base value
     * @param base              : base value
     * @param aClassQuote       : asset class quote value
     * @param quote             : quote value
     * @param pairDecimals      : pair decimals value
     * @param lotDecimals       : lot decimals value
     * @param lotMultiplier     : lot multiplier value
     * @param feeVolumeCurrency : fee volume currency value
     * @param marginCall        : margin call value
     * @param marginStop        : margin stop value
     * @param minOrder          : minimum order value
     * @param pairIndex         : symbol of pairs es. BTCEUR
     * @param minCost:          minimum order cost (in terms of quote currency)
     **/
    public AssetPair(String altName, String wsName, String aClassBase, String base, String aClassQuote, String quote,
                     int pairDecimals, int lotDecimals, int lotMultiplier, String feeVolumeCurrency, double marginCall,
                     double marginStop, double minOrder, String pairIndex, double minCost) {
        super(null);
        this.altName = altName;
        this.wsName = wsName;
        this.aClassBase = aClassBase;
        this.base = base;
        this.aClassQuote = aClassQuote;
        this.quote = quote;
        this.pairDecimals = pairDecimals;
        this.lotDecimals = lotDecimals;
        this.lotMultiplier = lotMultiplier;
        this.minCost = minCost;
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

    /**
     * Constructor to init an {@link AssetPair} object
     *
     * @param altName           : alt name value
     * @param wsName            : ws name value
     * @param aClassBase        : asset class base value
     * @param base              : base value
     * @param aClassQuote       : asset class quote value
     * @param quote             : quote value
     * @param pairDecimals      : pair decimals value
     * @param lotDecimals       : lot decimals value
     * @param lotMultiplier     : lot multiplier value
     * @param leverageBuy       : list of leverage buy
     * @param leverageSell      : list of leverage sell
     * @param fees              : list of {@link Fee}
     * @param makerFees         : list of maker {@link Fee}
     * @param feeVolumeCurrency : fee volume currency value
     * @param marginCall        : margin call value
     * @param marginStop        : margin stop value
     * @param minOrder          : minimum order value
     * @param minCost:          minimum order cost (in terms of quote currency)
     **/
    public AssetPair(String altName, String wsName, String aClassBase, String base, String aClassQuote, String quote,
                     int pairDecimals, int lotDecimals, int lotMultiplier, int[] leverageBuy, int[] leverageSell,
                     ArrayList<Fee> fees, ArrayList<Fee> makerFees, String feeVolumeCurrency, double marginCall,
                     double marginStop, double minOrder, double minCost) {
        super(null);
        this.altName = altName;
        this.wsName = wsName;
        this.aClassBase = aClassBase;
        this.base = base;
        this.aClassQuote = aClassQuote;
        this.quote = quote;
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
        this.minCost = minCost;
    }

    /**
     * Constructor to init an {@link AssetPair} object
     *
     * @param jsonAsset: asset pair value in {@code "JSON"} format
     **/
    public AssetPair(JSONObject jsonAsset) {
        super(jsonAsset);
        altName = result.getString("altname");
        wsName = result.getString("wsname");
        aClassBase = result.getString("aclass_base");
        base = result.getString("base");
        aClassQuote = result.getString("aclass_quote");
        quote = result.getString("quote");
        pairDecimals = result.getInt("pair_decimals", 0);
        lotDecimals = result.getInt("lot_decimals", 0);
        lotMultiplier = result.getInt("lot_multiplier", 0);
        leverageBuy = loadLeverageList(result.getJSONArray("leverage_buy", new JSONArray()));
        leverageSell = loadLeverageList(result.getJSONArray("leverage_sell", new JSONArray()));
        fees = loadFeesList(result.getJSONArray("fees", new JSONArray()));
        makerFees = loadFeesList(result.getJSONArray("fees_maker", new JSONArray()));
        feeVolumeCurrency = result.getString("fee_volume_currency");
        marginCall = result.getDouble("margin_call", 0);
        marginStop = result.getDouble("margin_stop", 0);
        minOrder = result.getDouble("ordermin", 0);
        minCost = result.getDouble("costmin", 0);
    }

    /**
     * Method to assemble a leverage list
     *
     * @param jsonList: obtained by response request
     * @return leverage list as int array
     **/
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

    /**
     * Method to assemble a fees list
     *
     * @param jsonList: obtained by response request
     * @return fees list as {@link ArrayList} of {@link Fee}
     **/
    @Returner
    private ArrayList<Fee> loadFeesList(JSONArray jsonList) {
        ArrayList<Fee> feesList = new ArrayList<>();
        if (jsonList != null)
            for (int j = 0; j < jsonList.length(); j++)
                feesList.add(new Fee(jsonList.getJSONArray(j)));
        return feesList;
    }

    /**
     * Method to get {@link #altName} instance <br>
     * Any params required
     *
     * @return {@link #altName} instance as {@link String}
     **/
    public String getAltName() {
        return altName;
    }

    /**
     * Method to get {@link #wsName} instance <br>
     * Any params required
     *
     * @return {@link #wsName} instance as {@link String}
     **/
    public String getWsName() {
        return wsName;
    }

    /**
     * Method to get {@link #aClassBase} instance <br>
     * Any params required
     *
     * @return {@link #aClassBase} instance as {@link String}
     **/
    public String getaClassBase() {
        return aClassBase;
    }

    /**
     * Method to get {@link #base} instance <br>
     * Any params required
     *
     * @return {@link #base} instance as {@link String}
     **/
    public String getBase() {
        return base;
    }

    /**
     * Method to get {@link #aClassQuote} instance <br>
     * Any params required
     *
     * @return {@link #aClassQuote} instance as {@link String}
     **/
    public String getaClassQuote() {
        return aClassQuote;
    }

    /**
     * Method to get {@link #quote} instance <br>
     * Any params required
     *
     * @return {@link #quote} instance as {@link String}
     **/
    public String getQuote() {
        return quote;
    }

    /**
     * Method to get {@link #pairDecimals} instance <br>
     * Any params required
     *
     * @return {@link #pairDecimals} instance as int
     **/
    public int getPairDecimals() {
        return pairDecimals;
    }

    /**
     * Method to get {@link #lotDecimals} instance <br>
     * Any params required
     *
     * @return {@link #lotDecimals} instance as int
     **/
    public int getLotDecimals() {
        return lotDecimals;
    }

    /**
     * Method to get {@link #lotMultiplier} instance <br>
     * Any params required
     *
     * @return {@link #lotMultiplier} instance as int
     **/
    public int getLotMultiplier() {
        return lotMultiplier;
    }

    /**
     * Method to get {@link #leverageBuy} instance <br>
     * Any params required
     *
     * @return {@link #leverageBuy} instance as array of int
     **/
    public int[] getLeverageBuy() {
        return leverageBuy;
    }

    /**
     * Method to get {@link #leverageSell} instance <br>
     * Any params required
     *
     * @return {@link #leverageSell} instance as array of int
     **/
    public int[] getLeverageSell() {
        return leverageSell;
    }

    /**
     * Method to get {@link #fees} instance <br>
     * Any params required
     *
     * @return {@link #fees} instance as {@link ArrayList} of {@link Fee}
     **/
    public ArrayList<Fee> getFees() {
        return fees;
    }

    /**
     * Method to get {@link #makerFees} instance <br>
     * Any params required
     *
     * @return {@link #makerFees} instance as {@link ArrayList} of {@link Fee}
     **/
    public ArrayList<Fee> getMakerFees() {
        return makerFees;
    }

    /**
     * Method to get {@link #feeVolumeCurrency} instance <br>
     * Any params required
     *
     * @return {@link #feeVolumeCurrency} instance as {@link String}
     **/
    public String getFeeVolumeCurrency() {
        return feeVolumeCurrency;
    }

    /**
     * Method to get {@link #marginCall} instance <br>
     * Any params required
     *
     * @return {@link #marginCall} instance as double
     **/
    public double getMarginCall() {
        return marginCall;
    }

    /**
     * Method to get {@link #marginCall} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #marginCall} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getMarginCall(int decimals) {
        return roundValue(marginCall, decimals);
    }

    /**
     * Method to get {@link #marginStop} instance <br>
     * Any params required
     *
     * @return {@link #marginStop} instance as double
     **/
    public double getMarginStop() {
        return marginStop;
    }

    /**
     * Method to get {@link #marginStop} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #marginStop} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getMarginStop(int decimals) {
        return roundValue(marginStop, decimals);
    }

    /**
     * Method to get {@link #minOrder} instance <br>
     * Any params required
     *
     * @return {@link #minOrder} instance as double
     **/
    public double getMinOrder() {
        return minOrder;
    }

    /**
     * Method to get {@link #minOrder} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #minOrder} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getMinOrder(int decimals) {
        return roundValue(minOrder, decimals);
    }

    /**
     * Method to get {@link #minCost} instance <br>
     * Any params required
     *
     * @return {@link #minCost} instance as double
     **/
    public double getMinCost() {
        return minCost;
    }

    /**
     * Method to get {@link #minCost} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #minCost} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getMinCost(int decimals) {
        return roundValue(minCost, decimals);
    }

    /**
     * The {@code Fee} class is useful to format a fee object
     **/
    public static class Fee {

        /**
         * {@code fees} is instance that memorizes list of fees
         **/
        private final int[] fees;

        /**
         * Constructor to init a {@link Fee}
         *
         * @param jsonFees: fees data in {@code "JSON"} format
         **/
        public Fee(JSONArray jsonFees) {
            int feesNumber = jsonFees.length();
            fees = new int[feesNumber];
            for (int j = 0; j < feesNumber; j++)
                fees[j] = jsonFees.getInt(j);
        }

        /**
         * Method to get {@link #fees} instance <br>
         * Any params required
         *
         * @return {@link #fees} instance as array of int
         **/
        public int[] getFees() {
            return fees;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
