package com.tecknobit.krakenmanager.Managers.Public.Market;

import com.tecknobit.krakenmanager.Managers.Public.KrakenPublicManager;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.Asset;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.ServerTime;
import com.tecknobit.krakenmanager.Managers.Public.Market.Records.SystemStatus;
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
        return sendGetRequest(SERVER_TIME_ENDPOINT);
    }

    public JSONObject getServerTimeJSON() throws IOException {
        return new JSONObject(getServerTime());
    }

    public ServerTime getServerTimeObject() throws IOException {
        return new ServerTime(getServerTimeJSON());
    }

    public String getSystemStatus() throws IOException {
        return sendGetRequest(SYSTEM_STATUS_ENDPOINT);
    }

    public JSONObject getSystemStatusJSON() throws IOException {
        return new JSONObject(getSystemStatus());
    }

    public SystemStatus getSystemStatusObject() throws IOException {
        return new SystemStatus(getSystemStatusJSON());
    }

    public String getAssets() throws IOException {
        return sendGetRequest(ASSETS_ENDPOINT);
    }

    public JSONObject getAssetsJSON() throws IOException {
        return new JSONObject(getAssets());
    }

    public ArrayList<Asset> getAssetsList() throws IOException {
        return assembleAssetsList(getAssetsJSON());
    }

    public String getAssets(String aClass) throws IOException {
        return sendGetRequest(ASSETS_ENDPOINT + "?aclass=" + aClass);
    }

    public JSONObject getAssetsJSON(String aClass) throws IOException {
        return new JSONObject(getAssets(aClass));
    }

    public ArrayList<Asset> getAssetsList(String aClass) throws IOException {
        return assembleAssetsList(getAssetsJSON(aClass));
    }

    public String getAssets(String[] assets) throws IOException {
        return sendGetRequest(ASSETS_ENDPOINT + "?asset=" + assembleParamsList(",", assets));
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

    // TODO: 25/07/2022 IMPORT FROM LIBRARY
    public String assembleParamsList(String separator, String... assets){
        StringBuilder params = new StringBuilder();
        for (String symbol : assets)
            params.append(symbol).append(separator);
        params.replace(params.length() - 1, params.length(),"");
        return params.toString();
    }

}

