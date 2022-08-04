package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import com.tecknobit.krakenmanager.Managers.Private.UserTrading.KrakenUserTradingManager;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.apimanager.Tools.Formatters.JsonHelper.getJSONObject;

public class OrderBatchList {

    private final KrakenUserTradingManager krakenUserTradingManager;
    private final JSONArray orders;
    private final String pair;
    private Params params;

    public OrderBatchList(String apiKey, String apiSign, String pair) {
        krakenUserTradingManager = new KrakenUserTradingManager(apiKey, apiSign);
        orders = new JSONArray();
        params = new Params();
        params.addParam("validate", true);
        this.pair = pair;
    }

    public void addMarkeOrder(String type, double volume) throws Exception {
        JSONObject response = krakenUserTradingManager.addMarketOrderJSON(type, volume, pair, params);
        if(succesfulResponse(response))
            orders.put(response);
        else
            throw new IllegalStateException("Error during market order adding");
    }

    public void addMarkeOrder(String type, double volume, Params params) throws Exception {
        // TODO: 04/08/2022 PUT ALL METHOD TMP WORKFLOW NOW
        params.addParam("validate", true);
        JSONObject response = krakenUserTradingManager.addMarketOrderJSON(type, volume, pair, params);
        if(succesfulResponse(response))
            orders.put(response);
        else
            throw new IllegalStateException("Error during market order adding");
    }

    private boolean succesfulResponse(JSONObject response){
        return getJSONObject(response, "result") != null;
    }

    public JSONArray getOrders() {
        return orders;
    }

}
