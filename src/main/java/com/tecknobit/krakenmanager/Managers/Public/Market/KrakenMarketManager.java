package com.tecknobit.krakenmanager.Managers.Public.Market;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.Public.KrakenPublicManager;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.*;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.List.OHLCData;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.List.Spreads;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.List.Trades;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.krakenmanager.Constants.EndpointsList.*;

public class KrakenMarketManager extends KrakenPublicManager {

    public KrakenMarketManager(String defaultErrorMessage, int requestTimeout) {
        super(defaultErrorMessage, requestTimeout);
    }

    public KrakenMarketManager(String defaultErrorMessage) {
        super(defaultErrorMessage);
    }

    public KrakenMarketManager(int requestTimeout) {
        super(requestTimeout);
    }

    public KrakenMarketManager() {
        super();
    }

    public String getServerTime() throws IOException {
        return sendGetRequest(GET_SERVER_TIME_ENDPOINT);
    }

    public JSONObject getServerTimeJSON() throws IOException {
        return new JSONObject(getServerTime());
    }

    public ServerTime getServerTimeObject() throws IOException {
        return new ServerTime(getServerTimeJSON());
    }

    public String getSystemStatus() throws IOException {
        return sendGetRequest(GET_SYSTEM_STATUS_ENDPOINT);
    }

    public JSONObject getSystemStatusJSON() throws IOException {
        return new JSONObject(getSystemStatus());
    }

    public SystemStatus getSystemStatusObject() throws IOException {
        return new SystemStatus(getSystemStatusJSON());
    }

    public String getAssets() throws IOException {
        return sendGetRequest(GET_ASSETS_ENDPOINT);
    }

    public JSONObject getAssetsJSON() throws IOException {
        return new JSONObject(getAssets());
    }

    public ArrayList<Asset> getAssetsList() throws IOException {
        return assembleAssetsList(getAssetsJSON());
    }

    public String getAssets(String aClass) throws IOException {
        return sendGetRequest(GET_ASSETS_ENDPOINT + "?aclass=" + aClass);
    }

    public JSONObject getAssetsJSON(String aClass) throws IOException {
        return new JSONObject(getAssets(aClass));
    }

    public ArrayList<Asset> getAssetsList(String aClass) throws IOException {
        return assembleAssetsList(getAssetsJSON(aClass));
    }

    public String getAssets(String[] assets) throws IOException {
        return sendGetRequest(GET_ASSETS_ENDPOINT + "?asset=" + assembleParamsList(",", assets));
    }

    public JSONObject getAssetsJSON(String[] assets) throws IOException {
        return new JSONObject(getAssets(assets));
    }

    public ArrayList<Asset> getAssetsList(String[] assets) throws IOException {
        return assembleAssetsList(getAssetsJSON(assets));
    }

    public String getAssets(ArrayList<String> assets) throws IOException {
        return getAssets(assets.toArray(new String[assets.size()]));
    }

    public JSONObject getAssetsJSON(ArrayList<String> assets) throws IOException {
        return getAssetsJSON(assets.toArray(new String[assets.size()]));
    }

    public ArrayList<Asset> getAssetsList(ArrayList<String> assets) throws IOException {
        return assembleAssetsList(getAssetsJSON(assets));
    }

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

    public String getAssetPairs() throws IOException {
        return sendGetRequest(GET_ASSET_PAIRS_ENDPOINT);
    }

    public JSONObject getAssetPairsJSON() throws IOException {
        return new JSONObject(getAssetPairs());
    }

    public ArrayList<AssetPair> getAssetPairsList() throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON());
    }

    public String getAssetPairs(String[] pairs) throws IOException {
        return sendGetRequest(GET_ASSET_PAIRS_ENDPOINT + "?pair=" + assembleParamsList(",", pairs));
    }

    public JSONObject getAssetPairsJSON(String[] pairs) throws IOException {
        return new JSONObject(getAssetPairs(pairs));
    }

    public ArrayList<AssetPair> getAssetPairsList(String[] pairs) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(pairs));
    }

    public String getAssetPairs(ArrayList<String> pairs) throws IOException {
        return getAssetPairs(pairs.toArray(new String[pairs.size()]));
    }

    public JSONObject getAssetPairsJSON(ArrayList<String> pairs) throws IOException {
        return new JSONObject(getAssetPairsJSON(pairs.toArray(new String[pairs.size()])));
    }

    public ArrayList<AssetPair> getAssetPairsList(ArrayList<String> pairs) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(pairs));
    }

    public String getAssetPairs(String info) throws IOException {
        return sendGetRequest(GET_ASSET_PAIRS_ENDPOINT + "?info=" + info);
    }

    public JSONObject getAssetPairsJSON(String info) throws IOException {
        return new JSONObject(getAssetPairs(info));
    }

    public ArrayList<AssetPair> getAssetPairsList(String info) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(info));
    }

    public String getAssetPairs(String info, String[] pairs) throws IOException {
        return sendGetRequest(GET_ASSET_PAIRS_ENDPOINT + "?info=" + info + "&pair=" + assembleParamsList(",",
                pairs));
    }

    public JSONObject getAssetPairsJSON(String info, String[] pairs) throws IOException {
        return new JSONObject(getAssetPairs(info, pairs));
    }

    public ArrayList<AssetPair> getAssetPairsList(String info, String[] pairs) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(info, pairs));
    }

    public String getAssetPairs(String info, ArrayList<String> pairs) throws IOException {
        return getAssetPairs(info, pairs.toArray(new String[pairs.size()]));
    }

    public JSONObject getAssetPairsJSON(String info, ArrayList<String> pairs) throws IOException {
        return new JSONObject(getAssetPairs(info, pairs.toArray(new String[pairs.size()])));
    }

    public ArrayList<AssetPair> getAssetPairsList(String info, ArrayList<String> pairs) throws IOException {
        return assembleAssetPairsList(getAssetPairsJSON(info, pairs));
    }

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
                    assetPair.getString("lot"),
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

    public String getTickerInformation(String pair) throws IOException {
        return sendGetRequest(GET_TICKER_ENDPOINT + "?pair=" + pair);
    }

    public JSONObject getTickerInformationJSON(String pair) throws IOException {
        return new JSONObject(getTickerInformation(pair));
    }

    public TickerInformation getTickerInformationObject(String pair) throws IOException {
        return new TickerInformation(getTickerInformationJSON(pair));
    }

    public String getOHLCData(String pair) throws IOException {
        return sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair);
    }

    public JSONObject getOHLCDataJSON(String pair) throws IOException {
        return new JSONObject(getOHLCData(pair));
    }

    public OHLCData getOHLCDataObject(String pair) throws IOException {
        return new OHLCData(getOHLCDataJSON(pair));
    }

    public String getOHLCData(String pair, int interval) throws IOException {
        return sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair + "&interval=" + interval);
    }

    public JSONObject getOHLCDataJSON(String pair, int interval) throws IOException {
        return new JSONObject(getOHLCData(pair, interval));
    }

    public OHLCData getOHLCDataObject(String pair, int interval) throws IOException {
        return new OHLCData(getOHLCDataJSON(pair, interval));
    }

    public String getOHLCData(String pair, long since) throws IOException {
        return sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair + "&since=" + since);
    }

    public JSONObject getOHLCDataJSON(String pair, long since) throws IOException {
        return new JSONObject(getOHLCData(pair, since));
    }

    public OHLCData getOHLCDataObject(String pair, long since) throws IOException {
        return new OHLCData(getOHLCDataJSON(pair, since));
    }

    public String getOHLCData(String pair, int interval, long since) throws IOException {
        return sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair + "&interval=" + interval + "&since=" + since);
    }

    public JSONObject getOHLCDataJSON(String pair, int interval, long since) throws IOException {
        return new JSONObject(getOHLCData(pair, interval, since));
    }

    public OHLCData getOHLCDataObject(String pair, int interval, long since) throws IOException {
        return new OHLCData(getOHLCDataJSON(pair, interval, since));
    }

    public String getOrderBook(String pair) throws IOException {
        return sendGetRequest(GET_ORDER_BOOK_ENDPOINT + "?pair=" + pair);
    }

    public JSONObject getOrderBookJSON(String pair) throws IOException {
        return new JSONObject(getOrderBook(pair));
    }

    public Book getOrderBookObject(String pair) throws IOException {
        return new Book(getOrderBookJSON(pair));
    }

    public String getOrderBook(String pair, int count) throws IOException {
        return sendGetRequest(GET_ORDER_BOOK_ENDPOINT + "?pair=" + pair + "&count=" + count);
    }

    public JSONObject getOrderBookJSON(String pair, int count) throws IOException {
        return new JSONObject(getOrderBook(pair, count));
    }

    public Book getOrderBookObject(String pair, int count) throws IOException {
        return new Book(getOrderBookJSON(pair, count));
    }

    public String getRecentTrades(String pair) throws IOException {
        return sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair);
    }

    public JSONObject getRecentTradesJSON(String pair) throws IOException {
        return new JSONObject(getRecentTrades(pair));
    }

    public Trades getRecentTradesObject(String pair) throws IOException {
        return new Trades(getRecentTradesJSON(pair));
    }

    public String getRecentTrades(String pair, long since) throws IOException {
        return sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair + "&since=" + since);
    }

    public JSONObject getRecentTradesJSON(String pair, long since) throws IOException {
        return new JSONObject(getRecentTrades(pair, since));
    }

    public Trades getRecentTradesObject(String pair, long since) throws IOException {
        return new Trades(getRecentTradesJSON(pair, since));
    }

    public String getRecentSpreads(String pair) throws IOException {
        return sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair);
    }

    public JSONObject getRecentSpreadsJSON(String pair) throws IOException {
        return new JSONObject(getRecentSpreads(pair));
    }

    public Spreads getRecentSpreadsObject(String pair) throws IOException {
        return new Spreads(getRecentSpreadsJSON(pair));
    }

    public String getRecentSpreads(String pair, long since) throws IOException {
        return sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair + "&since=" + since);
    }

    public JSONObject getRecentSpreadsJSON(String pair, long since) throws IOException {
        return new JSONObject(getRecentSpreads(pair, since));
    }

    public Spreads getRecentSpreadsObject(String pair, long since) throws IOException {
        return new Spreads(getRecentSpreadsJSON(pair, since));
    }

    // TODO: 25/07/2022 IMPORT FROM LIBRARY
    public String assembleParamsList(String separator, String... assets){
        StringBuilder params = new StringBuilder();
        for (String symbol : assets)
            params.append(symbol).append(separator);
        params.replace(params.length() - 1, params.length(),"");
        return params.toString();
    }

}

