package com.tecknobit.krakenmanager.Managers.Private.UserTrading;

import com.tecknobit.apimanager.Manager.APIRequest.Params;
import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order;
import com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderAdded;
import com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderBatchList;
import org.json.JSONObject;

import static com.tecknobit.krakenmanager.Constants.EndpointsList.ADD_ORDER_BATCH_ENDPOINT;
import static com.tecknobit.krakenmanager.Constants.EndpointsList.ADD_ORDER_ENDPOINT;
import static com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderAdded.*;

public class KrakenUserTradingManager extends KrakenPrivateManager {

    public KrakenUserTradingManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    public KrakenUserTradingManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    public KrakenUserTradingManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    public KrakenUserTradingManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    // TODO: 04/08/2022 ADD TO ALL ORDERS VALIDATE FLAG WHAT IT MEANS IN IMPL NOTE
    public String addMarketOrder(String type, double volume, String pair) throws Exception {
        return addOrder(Order.MARKET_ORDER_TYPE, type, volume, pair, null);
    }

    public JSONObject addMarketOrderJSON(String type, double volume, String pair) throws Exception {
        return new JSONObject(addMarketOrder(type, volume, pair));
    }

    public OrderAdded addMarketOrderObject(String type, double volume, String pair) throws Exception {
        return new OrderAdded(addMarketOrderJSON(type, volume, pair));
    }

    public String addMarketOrder(String type, double volume, String pair, Params params) throws Exception {
        return addOrder(Order.MARKET_ORDER_TYPE, type, volume, pair, params);
    }

    public JSONObject addMarketOrderJSON(String type, double volume, String pair, Params params) throws Exception {
        return new JSONObject(addMarketOrder(type, volume, pair, params));
    }

    public OrderAdded addMarketOrderObject(String type, double volume, String pair, Params params) throws Exception {
        return new OrderAdded(addMarketOrderJSON(type, volume, pair, params));
    }

    public String addLimitOrder(String type, double volume, String pair, double price, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        return addOrder(Order.LIMIT_ORDER_TYPE, type, volume, pair, params);
    }

    public JSONObject addLimitOrderJSON(String type, double volume, String pair, double price, Params params) throws Exception {
        return new JSONObject(addLimitOrder(type, volume, pair, price, params));
    }

    public OrderAdded addLimitOrderObject(String type, double volume, String pair, double price, Params params) throws Exception {
        return new OrderAdded(addLimitOrderJSON(type, volume, pair, price, params));
    }

    public String addLimitOrder(String type, double volume, String pair, double price) throws Exception {
        return addLimitOrder(type, volume, pair, price, null);
    }

    public JSONObject addLimitOrderJSON(String type, double volume, String pair, double price) throws Exception {
        return new JSONObject(addLimitOrder(type, volume, pair, price));
    }

    public OrderAdded addLimitOrderObject(String type, double volume, String pair, double price) throws Exception {
        return new OrderAdded(addLimitOrderJSON(type, volume, pair, price));
    }

    public String addStopLossOrder(String type, double volume, String pair, double price, String trigger,
                                   Params params) throws Exception {
        return addLevelOrder(Order.STOP_LOSS_ORDER_TYPE, type, volume, pair, price, trigger, params);
    }

    public JSONObject addStopLossOrderJSON(String type, double volume, String pair, double price, String trigger,
                                           Params params) throws Exception {
        return new JSONObject(addStopLossOrder(type, volume, pair, price, trigger, params));
    }

    public OrderAdded addStopLossOrderObject(String type, double volume, String pair, double price, String trigger,
                                             Params params) throws Exception {
        return new OrderAdded(addStopLossOrderJSON(type, volume, pair, price, trigger, params));
    }

    public String addStopLossOrder(String type, double volume, String pair, double price, String trigger) throws Exception {
        return addStopLossOrder(type, volume, pair, price, trigger, null);
    }

    public JSONObject addStopLossOrderJSON(String type, double volume, String pair, double price,
                                           String trigger) throws Exception {
        return new JSONObject(addStopLossOrder(type, volume, pair, price, trigger));
    }

    public OrderAdded addStopLossOrderObject(String type, double volume, String pair, double price,
                                             String trigger) throws Exception {
        return new OrderAdded(addStopLossOrderJSON(type, volume, pair, price, trigger));
    }

    public String addStopLossLimitOrder(String type, double volume, String pair, double price, double price2,
                                        String trigger, String offsetType, Params params) throws Exception {
        return addLevelLimitOrder(Order.STOP_LOSS_LIMIT_ORDER_TYPE, type, volume, pair, price, price2, trigger,
                offsetType, params);
    }

    public JSONObject addStopLossLimitOrderJSON(String type, double volume, String pair, double price, double price2,
                                                String trigger, String offsetType, Params params) throws Exception {
        return new JSONObject(addStopLossLimitOrder(type, volume, pair, price, price2, trigger, offsetType, params));
    }

    public OrderAdded addStopLossLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType, Params params) throws Exception {
        return new OrderAdded(addStopLossLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, params));
    }
    
    public String addStopLossLimitOrder(String type, double volume, String pair, double price, double price2,
                                        String trigger, String offsetType) throws Exception {
        return addStopLossLimitOrder(type, volume, pair, price, price2, trigger, offsetType, null);
    }

    public JSONObject addStopLossLimitOrderJSON(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType) throws Exception {
        return new JSONObject(addStopLossLimitOrder(type, volume, pair, price, price2, trigger, offsetType));
    }

    public OrderAdded addStopLossLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType) throws Exception {
        return new OrderAdded(addStopLossLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType));
    }

    public String addTakeProfitOrder(String type, double volume, String pair, double price, String trigger,
                                     Params params) throws Exception {
        return addLevelOrder(Order.TAKE_PROFIT_ORDER_TYPE, type, volume, pair, price, trigger, params);
    }

    public JSONObject addTakeProfitOrderJSON(String type, double volume, String pair, double price, String trigger,
                                             Params params) throws Exception {
        return new JSONObject(addTakeProfitOrder(type, volume, pair, price, trigger, params));
    }

    public OrderAdded addTakeProfitOrderObject(String type, double volume, String pair, double price, String trigger,
                                               Params params) throws Exception {
        return new OrderAdded(addTakeProfitOrderJSON(type, volume, pair, price, trigger, params));
    }

    public String addTakeProfitOrder(String type, double volume, String pair, double price, String trigger) throws Exception {
        return addTakeProfitOrder(type, volume, pair, price, trigger, null);
    }

    public JSONObject addTakeProfitOrderJSON(String type, double volume, String pair, double price,
                                             String trigger) throws Exception {
        return new JSONObject(addTakeProfitOrder(type, volume, pair, price, trigger));
    }

    public OrderAdded addTakeProfitOrderObject(String type, double volume, String pair, double price,
                                               String trigger) throws Exception {
        return new OrderAdded(addTakeProfitOrderJSON(type, volume, pair, price, trigger));
    }

    public String addTakeProfitLimitOrder(String type, double volume, String pair, double price, double price2,
                                          String trigger, String offsetType, Params params) throws Exception {
        return addLevelLimitOrder(Order.TAKE_PROFIT_LIMIT_ORDER_TYPE, type, volume, pair, price, price2, trigger,
                offsetType, params);
    }

    public JSONObject addTakeProfitLimitOrderJSON(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType, Params params) throws Exception {
        return new JSONObject(addTakeProfitLimitOrder(type, volume, pair, price, price2, trigger, offsetType, params));
    }

    public OrderAdded addTakeProfitLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                    String trigger, String offsetType, Params params) throws Exception {
        return new OrderAdded(addTakeProfitLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, params));
    }

    public String addTakeProfitLimitOrder(String type, double volume, String pair, double price, double price2,
                                          String trigger, String offsetType) throws Exception {
        return addTakeProfitLimitOrder(type, volume, pair, price, price2, trigger, offsetType, null);
    }

    public JSONObject addTakeProfitLimitOrderJSON(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType) throws Exception {
        return new JSONObject(addTakeProfitLimitOrder(type, volume, pair, price, price2, trigger, offsetType));
    }

    public OrderAdded addTakeProfitLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                    String trigger, String offsetType) throws Exception {
        return new OrderAdded(addTakeProfitLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType));
    }

    private String addLevelOrder(String orderType, String type, double volume, String pair, double price,
                                 String trigger, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("trigger", trigger);
        return sendPostRequest(orderType, params);
    }

    private String addLevelLimitOrder(String orderType, String type, double volume, String pair, double price, double price2,
                                      String trigger, String offsetType, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("trigger", trigger);
        params.addParam("price2", offsetType + price2);
        return addOrder(orderType, type, volume, pair, params);
    }

    // TODO: 04/08/2022 TEST ALL ORDERS METHOD AND CHECK DESCR RESULT TO FILL CUSTOM OBJECT
    private String addOrder(String orderType, String type, double volume, String pair, Params params) throws Exception {
        addBaseOrderParameters(orderType, type, volume, pair, params);
        return sendPostRequest(ADD_ORDER_ENDPOINT, params);
    }

    public String addOrderBatch(OrderBatchList orderBatchList, Params params) throws Exception {
        if(orderBatchList == null)
            throw new IllegalArgumentException("Order batch list cannot be null");
        if(params == null)
            params = new Params();
        params.addParam("pair", orderBatchList.getPair());
        params.addParam("orders", orderBatchList.getOrders());
        return sendPostRequest(ADD_ORDER_BATCH_ENDPOINT, params);
    }

    public JSONObject addOrderBatchJSON(OrderBatchList orderBatchList, Params params) throws Exception {
        return new JSONObject(addOrderBatch(orderBatchList, params));
    }

        public JSONObject addOrderBatchObject(OrderBatchList orderBatchList, Params params) throws Exception {
        return /*customObject = */ null;
    }

    public String addOrderBatch(OrderBatchList orderBatchList) throws Exception {
        return addOrderBatch(orderBatchList, null);
    }

    public JSONObject addOrderBatchJSON(OrderBatchList orderBatchList) throws Exception {
        return new JSONObject(addOrderBatch(orderBatchList));
    }

    public JSONObject addOrderBatchObject(OrderBatchList orderBatchList) throws Exception {
        return /*customObject = */ null;
    }

}
