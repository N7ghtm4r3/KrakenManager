package com.tecknobit.krakenmanager.Managers.Public.Market;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.Public.KrakenPublicManager;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.*;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.Lists.OHLCData;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.Lists.Spreads;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.Lists.Trades;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static com.tecknobit.krakenmanager.Constants.EndpointsList.*;

/**
 *  The {@code KrakenMarketManager} class is useful to manage all market endpoints
 *  @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data">
 *      https://docs.kraken.com/rest/#tag/Market-Data</a>
 *  @author N7ghtm4r3 - Tecknobit
 * **/

public class KrakenMarketManager extends KrakenPublicManager {

    /**
     * {@code symbols} is instance that memorizes symbols list set
     * **/
    private Set<String> symbols;

    /**
     * {@code previousLoadSymbols} is instance that memorizes previous timestamp of load symbols list set
     * **/
    private long previousLoadSymbols;

    /** Constructor to init a {@link KrakenMarketManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * **/
    public KrakenMarketManager(String defaultErrorMessage, int requestTimeout) {
        super(defaultErrorMessage, requestTimeout);
    }

    /** Constructor to init a {@link KrakenMarketManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * **/
    public KrakenMarketManager(String defaultErrorMessage) {
        super(defaultErrorMessage);
    }

    /** Constructor to init a {@link KrakenMarketManager}
     * @param requestTimeout: custom timeout for request
     * **/
    public KrakenMarketManager(int requestTimeout) {
        super(requestTimeout);
    }

    /** Constructor to init a {@link KrakenMarketManager} <br>
     * Any params required
     * **/
    public KrakenMarketManager() {
        super();
    }

    /** Request to get server time<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime</a>
     * @return server time as {@link String}
     * **/
    public String getServerTime() throws IOException {
        return sendGetRequest(GET_SERVER_TIME_ENDPOINT);
    }

    /** Request to get server time<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime</a>
     * @return server time as {@link JSONObject}
     * **/
    public JSONObject getServerTimeJSON() throws IOException {
        return new JSONObject(getServerTime());
    }

    /** Request to get server time<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime</a>
     * @return server time as {@link ServerTime} custom object
     * **/
    public ServerTime getServerTimeObject() throws IOException {
        return new ServerTime(getServerTimeJSON());
    }

    /** Custom request to get server time value<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime</a>
     * @return server time value as long
     * **/
    public long getServerTimeValue() throws IOException {
        return getServerTimeJSON().getJSONObject("result").getLong("unixtime");
    }

    /** Request to get system status<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus</a>
     * @return system status as {@link String}
     * **/
    public String getSystemStatus() throws IOException {
        return sendGetRequest(GET_SYSTEM_STATUS_ENDPOINT);
    }

    /** Request to get system status<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus</a>
     * @return system status as {@link JSONObject}
     * **/
    public JSONObject getSystemStatusJSON() throws IOException {
        return new JSONObject(getSystemStatus());
    }

    /** Request to get system status<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus</a>
     * @return system status as {@link SystemStatus} custom object
     * **/
    public SystemStatus getSystemStatusObject() throws IOException {
        return new SystemStatus(getSystemStatusJSON());
    }

    /** Custom request to get system status value<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus</a>
     * @return system status value as {@link String}
     * **/
    public String getSystemStatusValue() throws IOException {
        return getSystemStatusJSON().getJSONObject("result").getString("status");
    }

    /** Request to get assets list<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link String}
     * **/
    public String getAssets() throws IOException {
        return sendGetRequest(GET_ASSETS_ENDPOINT);
    }

    /** Request to get assets list<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link JSONObject}
     * **/
    public JSONObject getAssetsJSON() throws IOException {
        return new JSONObject(getAssets());
    }

    /** Request to get assets list<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * **/
    public ArrayList<Asset> getAssetsList() throws IOException {
        return assembleAssetsList(getAssetsJSON());
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link String}
     * **/
    public String getAssets(String[] assets) throws IOException {
        return sendGetRequest(GET_ASSETS_ENDPOINT + "?asset=" + apiRequest.assembleParamsList(",", assets));
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link JSONObject}
     * **/
    public JSONObject getAssetsJSON(String[] assets) throws IOException {
        return new JSONObject(getAssets(assets));
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * **/
    public ArrayList<Asset> getAssetsList(String[] assets) throws IOException {
        return assembleAssetsList(getAssetsJSON(assets));
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link String}
     * **/
    public String getAssets(ArrayList<String> assets) throws IOException {
        return getAssets(assets.toArray(new String[assets.size()]));
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link JSONObject}
     * **/
    public JSONObject getAssetsJSON(ArrayList<String> assets) throws IOException {
        return getAssetsJSON(assets.toArray(new String[assets.size()]));
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * **/
    public ArrayList<Asset> getAssetsList(ArrayList<String> assets) throws IOException {
        return assembleAssetsList(getAssetsJSON(assets));
    }

    /** Request to get assets list<br>
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link String}
     * **/
    public String getAssets(String aClass) throws IOException {
        return sendGetRequest(GET_ASSETS_ENDPOINT + "?aclass=" + aClass);
    }

    /** Request to get assets list<br>
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link JSONObject}
     * **/
    public JSONObject getAssetsJSON(String aClass) throws IOException {
        return new JSONObject(getAssets(aClass));
    }

    /** Request to get assets list<br>
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * **/
    public ArrayList<Asset> getAssetsList(String aClass) throws IOException {
        return assembleAssetsList(getAssetsJSON(aClass));
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link String} array format
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link String}
     * **/
    public String getAssets(String[] assets, String aClass) throws IOException {
        return sendGetRequest(GET_ASSETS_ENDPOINT + "?asset=" + apiRequest.assembleParamsList(",", assets)
                + "&aclass=" + aClass);
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link String} array format
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link JSONObject}
     * **/
    public JSONObject getAssetsJSON(String[] assets, String aClass) throws IOException {
        return new JSONObject(getAssets(assets, aClass));
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link String} array format
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * **/
    public ArrayList<Asset> getAssetsList(String[] assets, String aClass) throws IOException {
        return assembleAssetsList(getAssetsJSON(assets, aClass));
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link String}
     * **/
    public String getAssets(ArrayList<String> assets, String aClass) throws IOException {
        return getAssets(assets.toArray(new String[assets.size()]), aClass);
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link JSONObject}
     * **/
    public JSONObject getAssetsJSON(ArrayList<String> assets, String aClass) throws IOException {
        return getAssetsJSON(assets.toArray(new String[assets.size()]), aClass);
    }

    /** Request to get assets list<br>
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * **/
    public ArrayList<Asset> getAssetsList(ArrayList<String> assets, String aClass) throws IOException {
        return assembleAssetsList(getAssetsJSON(assets, aClass));
    }

    /** Custom request to get a single asset<br>
     * @param symbol: asset to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return single assets pair as {@link String}
     * **/
    public String getAsset(String symbol) throws IOException {
        return getAssets(new String[]{symbol});
    }

    /** Custom request to get a single asset<br>
     * @param symbol: asset to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return single assets pair as {@link JSONObject}
     * **/
    public JSONObject getAssetJSON(String symbol) throws IOException {
        return getAssetsJSON(new String[]{symbol});
    }

    /** Custom request to get a single asset<br>
     * @param symbol: asset to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return single assets pair as {@link Asset} custom object
     * **/
    public Asset getAssetObject(String symbol) throws IOException {
        return new Asset(getAssetJSON(symbol));
    }

    /** Custom request to get a single asset<br>
     * @param symbol: asset to fetch details es. BTCEUR
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return single assets pair as {@link String}
     * **/
    public String getAsset(String symbol, String aClass) throws IOException {
        return getAssets(new String[]{symbol}, aClass);
    }

    /** Custom request to get a single asset<br>
     * @param symbol: asset to fetch details es. BTCEUR
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return single assets pair as {@link JSONObject}
     * **/
    public JSONObject getAssetJSON(String symbol, String aClass) throws IOException {
        return getAssetsJSON(new String[]{symbol}, aClass);
    }

    /** Custom request to get a single asset<br>
     * @param symbol: asset to fetch details es. BTCEUR
     * @param aClass: class of the assets to fetch
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
     * @return single assets pair as {@link Asset} custom object
     * **/
    public Asset getAssetObject(String symbol, String aClass) throws IOException {
        return new Asset(getAssetJSON(symbol, aClass));
    }

    /** Method to assemble an assets list
     * @param jsonResponse: jsonObject obtained by response request
     * @return assets list as {@link ArrayList} of {@link Asset}
     * **/
    private ArrayList<Asset> assembleAssetsList(JSONObject jsonResponse){
        ArrayList<Asset> assets = new ArrayList<>();
        JSONObject jsonAssets = jsonResponse.getJSONObject("result");
        for (String key : jsonAssets.keySet()){
            JSONObject asset = jsonAssets.getJSONObject(key);
            assets.add(new Asset(jsonResponse,
                    asset.getString("aclass"),
                    asset.getString("altname"),
                    asset.getInt("decimals"),
                    asset.getInt("display_decimals")
            ));
        }
        return assets;
    }

    /** Request to get assets pairs list<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link String}
     * **/
    public String getAssetPairs() throws IOException {
        return sendGetRequest(GET_ASSET_PAIRS_ENDPOINT);
    }

    /** Request to get assets pairs list<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link JSONObject}
     * **/
    public JSONObject getAssetPairsJSON() throws IOException {
        return new JSONObject(getAssetPairs());
    }

    /** Request to get assets pairs list<br>
     *  Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * **/
    public ArrayList<AssetPair> getAssetPairsList() throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON());
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link String}
     * **/
    public String getAssetPairs(String[] pairs) throws IOException {
        return sendGetRequest(GET_ASSET_PAIRS_ENDPOINT + "?pair=" + apiRequest.assembleParamsList(",", pairs));
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link JSONObject}
     * **/
    public JSONObject getAssetPairsJSON(String[] pairs) throws IOException {
        return new JSONObject(getAssetPairs(pairs));
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * **/
    public ArrayList<AssetPair> getAssetPairsList(String[] pairs) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(pairs));
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link String}
     * **/
    public String getAssetPairs(ArrayList<String> pairs) throws IOException {
        return getAssetPairs(pairs.toArray(new String[pairs.size()]));
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link JSONObject}
     * **/
    public JSONObject getAssetPairsJSON(ArrayList<String> pairs) throws IOException {
        return new JSONObject(getAssetPairsJSON(pairs.toArray(new String[pairs.size()])));
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * **/
    public ArrayList<AssetPair> getAssetPairsList(ArrayList<String> pairs) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(pairs));
    }

    /** Request to get assets pairs list<br>
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link String}
     * **/
    public String getAssetPairs(String info) throws IOException {
        return sendGetRequest(GET_ASSET_PAIRS_ENDPOINT + "?info=" + info);
    }

    /** Request to get assets pairs list<br>
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link JSONObject}
     * **/
    public JSONObject getAssetPairsJSON(String info) throws IOException {
        return new JSONObject(getAssetPairs(info));
    }

    /** Request to get assets pairs list<br>
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * **/
    public ArrayList<AssetPair> getAssetPairsList(String info) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(info));
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link String}
     * **/
    public String getAssetPairs(String[] pairs, String info) throws IOException {
        return sendGetRequest(GET_ASSET_PAIRS_ENDPOINT + "?pair=" + apiRequest.assembleParamsList(",", pairs) +
                "&info=" + info);
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link JSONObject}
     * **/
    public JSONObject getAssetPairsJSON(String[] pairs, String info) throws IOException {
        return new JSONObject(getAssetPairs(pairs, info));
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * **/
    public ArrayList<AssetPair> getAssetPairsList(String[] pairs, String info) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(pairs, info));
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link String}
     * **/
    public String getAssetPairs(ArrayList<String> pairs, String info) throws IOException {
        return getAssetPairs(pairs.toArray(new String[pairs.size()]), info);
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link JSONObject}
     * **/
    public JSONObject getAssetPairsJSON(ArrayList<String> pairs, String info) throws IOException {
        return new JSONObject(getAssetPairs(pairs.toArray(new String[pairs.size()]), info));
    }

    /** Request to get assets pairs list<br>
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * **/
    public ArrayList<AssetPair> getAssetPairsList(ArrayList<String> pairs, String info) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(pairs, info));
    }

    /** Custom request to get a single assets pair<br>
     * @param pairs: pairs to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return single assets pair as {@link String}
     * **/
    public String getAssetPair(String pairs) throws IOException {
        return getAssetPairs(new String[]{pairs});
    }

    /** Custom request to get a single assets pair<br>
     * @param pairs: pairs to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return single assets pair as {@link JSONObject}
     * **/
    public JSONObject getAssetPairJSON(String pairs) throws IOException {
        return getAssetPairsJSON(new String[]{pairs});
    }

    /** Custom request to get a single assets pair<br>
     * @param pairs: pairs to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return single assets pair as {@link AssetPair} custom object
     * **/
    public AssetPair getAssetPairObject(String pairs) throws IOException {
        return new AssetPair(getAssetPairsJSON(new String[]{pairs}));
    }

    /** Custom request to get a single assets pair<br>
     * @param pairs: pairs to fetch details es. BTCEUR
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return single assets pair as {@link String}
     * **/
    public String getAssetPair(String pairs, String info) throws IOException {
        return getAssetPairs(new String[]{pairs}, info);
    }

    /** Custom request to get a single assets pair<br>
     * @param pairs: pairs to fetch details es. BTCEUR
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return single assets pair as {@link JSONObject}
     * **/
    public JSONObject getAssetPairJSON(String pairs, String info) throws IOException {
        return getAssetPairsJSON(new String[]{pairs}, info);
    }

    /** Custom request to get a single assets pair<br>
     * @param pairs: pairs to fetch details es. BTCEUR
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs</a>
     * @return single assets pair as {@link AssetPair} custom object
     * **/
    public AssetPair getAssetPairObject(String pairs, String info) throws IOException {
        return new AssetPair(getAssetPairsJSON(new String[]{pairs}, info));
    }

    /** Method to assemble an assets pair list
     * @param jsonResponse: jsonObject obtained by response request
     * @return assets pair list as {@link ArrayList} of {@link AssetPair}
     * **/
    private ArrayList<AssetPair> assembleAssetPairsList(JSONObject jsonResponse){
        ArrayList<AssetPair> assetPairs = new ArrayList<>();
        JSONObject jsonAssets = jsonResponse.getJSONObject("result");
        for (String key : jsonAssets.keySet()){
            JsonHelper assetPair = new JsonHelper(jsonAssets.getJSONObject(key));
            assetPairs.add(new AssetPair(jsonResponse,
                    assetPair.getString("altname"),
                    assetPair.getString("wsname"),
                    assetPair.getString("aclass_base"),
                    assetPair.getString("base"),
                    assetPair.getString("aclass_quote"),
                    assetPair.getString("quote"),
                    assetPair.getInt("pair_decimals"),
                    assetPair.getInt("lot_decimals"),
                    assetPair.getInt("lot_multiplier"),
                    assetPair.getString("fee_volume_currency"),
                    assetPair.getDouble("margin_call"),
                    assetPair.getDouble("margin_stop"),
                    assetPair.getDouble("ordermin"),
                    key
            ));
        }
        return assetPairs;
    }

    /** Request to get ticker information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation</a>
     * @return ticker information as {@link String}
     * **/
    public String getTickerInformation(String pair) throws IOException {
        return sendGetRequest(GET_TICKER_ENDPOINT + "?pair=" + pair);
    }

    /** Request to get ticker information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation</a>
     * @return ticker information as {@link JSONObject}
     * **/
    public JSONObject getTickerInformationJSON(String pair) throws IOException {
        return new JSONObject(getTickerInformation(pair));
    }

    /** Request to get ticker information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation</a>
     * @return ticker information as {@link TickerInformation} custom object
     * **/
    public TickerInformation getTickerInformationObject(String pair) throws IOException {
        return new TickerInformation(getTickerInformationJSON(pair));
    }

    /** Custom request to get all tickers information <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation</a>
     * @return all tickers' information as {@link String}
     * **/
    public String getAllTickers() throws IOException {
        return getAllTickersJSON().toString();
    }

    /** Custom request to get all tickers information <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation</a>
     * @return all tickers' information as {@link JSONArray}
     * **/
    public JSONArray getAllTickersJSON() throws IOException {
        JSONArray tickers = new JSONArray();
        long actualTimestamp = System.currentTimeMillis();
        if(symbols == null || ((actualTimestamp - previousLoadSymbols) > 3600 * 1000)) {
            symbols = getAssetPairsJSON().getJSONObject("result").keySet();
            previousLoadSymbols = actualTimestamp;
        }
        for (String symbol : symbols)
            tickers.put(getTickerInformationJSON(symbol));
        return tickers;
    }

    /** Custom request to get all tickers information <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation</a>
     * @return all tickers' information as {@link ArrayList} of {@link TickerInformation} custom object
     * **/
    public ArrayList<TickerInformation> getAllTickersList() throws IOException {
        ArrayList<TickerInformation> tickers = new ArrayList<>();
        JSONArray jsonTickers = getAllTickersJSON();
        for (int j = 0; j < jsonTickers.length(); j++)
            tickers.add(new TickerInformation(jsonTickers.getJSONObject(j)));
        return tickers;
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link String}
     * **/
    public String getOHLCData(String pair) throws IOException {
        return sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair);
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link JSONObject}
     * **/
    public JSONObject getOHLCDataJSON(String pair) throws IOException {
        return new JSONObject(getOHLCData(pair));
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link OHLCData} custom object
     * **/
    public OHLCData getOHLCDataObject(String pair) throws IOException {
        return new OHLCData(getOHLCDataJSON(pair));
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link String}
     * **/
    public String getOHLCData(String pair, int interval) throws IOException {
        return sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair + "&interval=" + interval);
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link JSONObject}
     * **/
    public JSONObject getOHLCDataJSON(String pair, int interval) throws IOException {
        return new JSONObject(getOHLCData(pair, interval));
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link OHLCData} custom object
     * **/
    public OHLCData getOHLCDataObject(String pair, int interval) throws IOException {
        return new OHLCData(getOHLCDataJSON(pair, interval));
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link String}
     * **/
    public String getOHLCData(String pair, long since) throws IOException {
        return sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair + "&since=" + since);
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link JSONObject}
     * **/
    public JSONObject getOHLCDataJSON(String pair, long since) throws IOException {
        return new JSONObject(getOHLCData(pair, since));
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link OHLCData} custom object
     * **/
    public OHLCData getOHLCDataObject(String pair, long since) throws IOException {
        return new OHLCData(getOHLCDataJSON(pair, since));
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link String}
     * **/
    public String getOHLCData(String pair, int interval, long since) throws IOException {
        return sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair + "&interval=" + interval + "&since=" + since);
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link JSONObject}
     * **/
    public JSONObject getOHLCDataJSON(String pair, int interval, long since) throws IOException {
        return new JSONObject(getOHLCData(pair, interval, since));
    }

    /** Request to get OHLC data information<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     * @return OHLC data information as {@link OHLCData} custom object
     * **/
    public OHLCData getOHLCDataObject(String pair, int interval, long since) throws IOException {
        return new OHLCData(getOHLCDataJSON(pair, interval, since));
    }

    /** Request to get order book details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook</a>
     * @return order book details as {@link String}
     * **/
    public String getOrderBook(String pair) throws IOException {
        return sendGetRequest(GET_ORDER_BOOK_ENDPOINT + "?pair=" + pair);
    }

    /** Request to get order book details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook</a>
     * @return order book details as {@link JSONObject}
     * **/
    public JSONObject getOrderBookJSON(String pair) throws IOException {
        return new JSONObject(getOrderBook(pair));
    }

    /** Request to get order book details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook</a>
     * @return order book details as {@link Book} custom object
     * **/
    public Book getOrderBookObject(String pair) throws IOException {
        return new Book(getOrderBookJSON(pair));
    }

    /** Request to get order book details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param count: maximum number of asks and bids
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook</a>
     * @return order book details as {@link String}
     * **/
    public String getOrderBook(String pair, int count) throws IOException {
        return sendGetRequest(GET_ORDER_BOOK_ENDPOINT + "?pair=" + pair + "&count=" + count);
    }

    /** Request to get order book details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param count: maximum number of asks and bids
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook</a>
     * @return order book details as {@link JSONObject}
     * **/
    public JSONObject getOrderBookJSON(String pair, int count) throws IOException {
        return new JSONObject(getOrderBook(pair, count));
    }

    /** Request to get order book details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param count: maximum number of asks and bids
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook</a>
     * @return order book details as {@link Book} custom object
     * **/
    public Book getOrderBookObject(String pair, int count) throws IOException {
        return new Book(getOrderBookJSON(pair, count));
    }

    /** Request to get recent trades details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades</a>
     * @return recent trades details as {@link String}
     * **/
    public String getRecentTrades(String pair) throws IOException {
        return sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair);
    }

    /** Request to get recent trades details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades</a>
     * @return recent trades details as {@link JSONObject}
     * **/
    public JSONObject getRecentTradesJSON(String pair) throws IOException {
        return new JSONObject(getRecentTrades(pair));
    }

    /** Request to get recent trades details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades</a>
     * @return recent trades details as {@link Trades} custom object
     * **/
    public Trades getRecentTradesObject(String pair) throws IOException {
        return new Trades(getRecentTradesJSON(pair));
    }

    /** Request to get recent trades details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades</a>
     * @return recent trades details as {@link String}
     * **/
    public String getRecentTrades(String pair, long since) throws IOException {
        return sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair + "&since=" + since);
    }

    /** Request to get recent trades details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades</a>
     * @return recent trades details as {@link JSONObject}
     * **/
    public JSONObject getRecentTradesJSON(String pair, long since) throws IOException {
        return new JSONObject(getRecentTrades(pair, since));
    }

    /** Request to get recent trades details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades</a>
     * @return recent trades details as {@link Trades} custom object
     * **/
    public Trades getRecentTradesObject(String pair, long since) throws IOException {
        return new Trades(getRecentTradesJSON(pair, since));
    }

    /** Request to get recent spreads details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads</a>
     * @return recent spreads details as {@link String}
     * **/
    public String getRecentSpreads(String pair) throws IOException {
        return sendGetRequest(GET_RECENT_SPREADS_ENDPOINT + "?pair=" + pair);
    }

    /** Request to get recent spreads details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads</a>
     * @return recent spreads details as {@link JSONObject}
     * **/
    public JSONObject getRecentSpreadsJSON(String pair) throws IOException {
        return new JSONObject(getRecentSpreads(pair));
    }

    /** Request to get recent spreads details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads</a>
     * @return recent spreads details as {@link Spreads} custom object
     * **/
    public Spreads getRecentSpreadsObject(String pair) throws IOException {
        return new Spreads(getRecentSpreadsJSON(pair));
    }

    /** Request to get recent spreads details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads</a>
     * @return recent spreads details as {@link String}
     * **/
    public String getRecentSpreads(String pair, long since) throws IOException {
        return sendGetRequest(GET_RECENT_SPREADS_ENDPOINT + "?pair=" + pair + "&since=" + since);
    }

    /** Request to get recent spreads details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads</a>
     * @return recent spreads details as {@link JSONObject}
     * **/
    public JSONObject getRecentSpreadsJSON(String pair, long since) throws IOException {
        return new JSONObject(getRecentSpreads(pair, since));
    }

    /** Request to get recent spreads details<br>
     * @param pair: pair to fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads</a>
     * @return recent spreads details as {@link Spreads} custom object
     * **/
    public Spreads getRecentSpreadsObject(String pair, long since) throws IOException {
        return new Spreads(getRecentSpreadsJSON(pair, since));
    }

    /** Method to get prevision of a cryptocurrency in base of day's gap inserted
     * @param symbol: symbol to calculate forecast es. BTCEUR
     * @param OHCLInterval: temporal interval of data for the forecast
     * @param intervalDays: days gap for the prevision range
     * @param toleranceValue: tolerance for select similar value compared to lastValue inserted
     * @return prevision value as a double es. 8 or -8
     * @throws IllegalArgumentException if lastValue is negative or intervalDays are less or equal to 0
     * **/
    public double getSymbolForecast(String symbol, int OHCLInterval, int intervalDays, int toleranceValue) throws IOException {
        ArrayList<Double> historicalValues = new ArrayList<>();
        for (OHLCData.TickData tickData : getOHLCDataObject(symbol, OHCLInterval).getTicksData())
            historicalValues.add(tickData.getHigh());
        return tradingTools.computeTPTOPAsset(historicalValues, getTickerInformationObject(symbol).getClose().getPrice(),
                intervalDays, toleranceValue);
    }

    /** Method to get prevision of a cryptocurrency in base of day's gap inserted
     * @param symbol: symbol to calculate forecast es. BTCEUR
     * @param OHCLInterval: temporal interval of data for the forecast
     * @param intervalDays: days gap for the prevision range
     * @param toleranceValue: tolerance for select similar value compared to lastValue inserted
     * @param decimalDigits: number of digits to round final forecast value
     * @return forecast value as a double es. 8 or -8
     * @throws IllegalArgumentException if lastValue is negative or intervalDays are less or equal to 0
     * **/
    public double getSymbolForecast(String symbol, int OHCLInterval, int intervalDays, int toleranceValue,
                                    int decimalDigits) throws IOException {
        return tradingTools.roundValue(getSymbolForecast(symbol, OHCLInterval, intervalDays, toleranceValue), decimalDigits);
    }

}

