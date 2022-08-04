package com.tecknobit.krakenmanager.Managers.Private.UserTrading;

import com.tecknobit.apimanager.Manager.APIRequest.Params;
import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order;
import com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Batch.OrderBatch;
import com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Batch.OrderBatchList;
import com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderAdded;
import com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderCancelledStatus;
import com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderEdited;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.krakenmanager.Constants.EndpointsList.*;
import static com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderAdded.addBaseOrderParameters;
import static com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderEdited.addBaseEditParameters;

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

    public String addMarketOrder(String type, double volume, String pair, Params params) throws Exception {
        return addOrder(Order.MARKET_ORDER_TYPE, type, volume, pair, params);
    }

    public JSONObject addMarketOrderJSON(String type, double volume, String pair, Params params) throws Exception {
        return new JSONObject(addMarketOrder(type, volume, pair, params));
    }

    public OrderAdded addMarketOrderObject(String type, double volume, String pair, Params params) throws Exception {
        return new OrderAdded(addMarketOrderJSON(type, volume, pair, params));
    }

    // TODO: 04/08/2022 ADD TO ALL ORDERS VALIDATE FLAG WHAT IT MEANS IN IMPL NOTE
    public String addMarketOrder(String type, double volume, String pair) throws Exception {
        return addOrder(Order.MARKET_ORDER_TYPE, type, volume, pair, null);
    }

    public JSONObject addMarketOrderJSON(String type, double volume, String pair) throws Exception {
        return new JSONObject(addMarketOrder(type, volume, pair, null));
    }

    public OrderAdded addMarketOrderObject(String type, double volume, String pair) throws Exception {
        return new OrderAdded(addMarketOrderJSON(type, volume, pair, null));
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
        return addLimitOrderJSON(type, volume, pair, price, null);
    }

    public OrderAdded addLimitOrderObject(String type, double volume, String pair, double price) throws Exception {
        return new OrderAdded(addLimitOrderJSON(type, volume, pair, price, null));
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
        return addStopLossOrderJSON(type, volume, pair, price, trigger, null);
    }

    public OrderAdded addStopLossOrderObject(String type, double volume, String pair, double price,
                                             String trigger) throws Exception {
        return new OrderAdded(addStopLossOrderJSON(type, volume, pair, price, trigger, null));
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
        return addStopLossLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, null);
    }

    public OrderAdded addStopLossLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType) throws Exception {
        return new OrderAdded(addStopLossLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, null));
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
        return addTakeProfitOrderJSON(type, volume, pair, price, trigger, null);
    }

    public OrderAdded addTakeProfitOrderObject(String type, double volume, String pair, double price,
                                               String trigger) throws Exception {
        return new OrderAdded(addTakeProfitOrderJSON(type, volume, pair, price, trigger, null));
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
        return addTakeProfitLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, null);
    }

    public OrderAdded addTakeProfitLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                    String trigger, String offsetType) throws Exception {
        return new OrderAdded(addTakeProfitLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, null));
    }

    private String addLevelOrder(String orderType, String type, double volume, String pair, double price,
                                 String trigger, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("trigger", trigger);
        return addOrder(orderType, type, volume, pair, params);
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

    public <T> T addOrderBatchObject(OrderBatchList orderBatchList, Params params) throws Exception {
        return createOrderBatchObject(addOrderBatchJSON(orderBatchList, params));
    }

    public String addOrderBatch(OrderBatchList orderBatchList) throws Exception {
        return addOrderBatch(orderBatchList, null);
    }

    public JSONObject addOrderBatchJSON(OrderBatchList orderBatchList) throws Exception {
        return addOrderBatchJSON(orderBatchList, null);
    }

    public <T> T addOrderBatchObject(OrderBatchList orderBatchList) throws Exception {
        return createOrderBatchObject(addOrderBatchJSON(orderBatchList, null));
    }

    private <T> T createOrderBatchObject(JSONObject jsonBatch) {
        JSONArray orders = JsonHelper.getJSONArray(jsonBatch.getJSONObject("result"), "orders");
        if(orders != null){
            ArrayList<OrderBatch> batchOrders = new ArrayList<>();
            for (int j = 0; j < orders.length(); j++)
                batchOrders.add(new OrderBatch(orders.getJSONObject(j)));
            return (T) batchOrders;
        }
        return (T) new OrderAdded(jsonBatch);
    }

    public <T> String editMarketOrder(T orderId, String pair, double volume, Params params) throws Exception {
        return editOrder(orderId, pair, volume, params);
    }

    public <T> JSONObject editMarketOrderJSON(T orderId, String pair, double volume, Params params) throws Exception {
        return new JSONObject(editOrder(orderId, pair, volume, params));
    }

    public <T> OrderEdited editMarketOrderObject(T orderId, String pair, double volume, Params params) throws Exception {
        return new OrderEdited(editMarketOrderJSON(orderId, pair, volume, params));
    }

    public <T> String editMarketOrder(T orderId, String pair, double volume) throws Exception {
        return editOrder(orderId, pair, volume, null);
    }

    public <T> JSONObject editMarketOrderJSON(T orderId, String pair, double volume) throws Exception {
        return editMarketOrderJSON(orderId, pair, volume, null);
    }

    public <T> OrderEdited editMarketOrderObject(T orderId, String pair, double volume) throws Exception {
        return new OrderEdited(editMarketOrderJSON(orderId, pair, volume, null));
    }

    public <T> String editLimitOrder(T orderId, String pair, double volume, double price, Params params) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, params);
    }

    public <T> JSONObject editLimitOrderJSON(T orderId, String pair, double volume, double price,
                                         Params params) throws Exception {
        return new JSONObject(editLimitOrder(orderId, pair, volume, price, params));
    }

    public <T> OrderEdited editLimitOrderObject(T orderId, String pair, double volume, double price,
                                            Params params) throws Exception {
        return new OrderEdited(editLimitOrderJSON(orderId, pair, volume, price, params));
    }

    public <T> String editLimitOrder(T orderId, String pair, double volume, double price) throws Exception {
        return editLimitOrder(orderId, pair, volume, price, null);
    }

    public <T> JSONObject editLimitOrderJSON(T orderId, String pair, double volume, double price) throws Exception {
        return editLimitOrderJSON(orderId, pair, volume, price, null);
    }

    public <T> OrderEdited editLimitOrderObject(T orderId, String pair, double volume, double price) throws Exception {
        return new OrderEdited(editLimitOrderJSON(orderId, pair, volume, price, null));
    }

    public <T> String editStopLossOrder(T orderId, String pair, double volume, double price, Params params) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, params);
    }

    public <T> JSONObject editStopLossOrderJSON(T orderId, String pair, double volume, double price,
                                            Params params) throws Exception {
        return new JSONObject(editStopLossOrder(orderId, pair, volume, price, params));
    }

    public <T> OrderEdited editStopLossOrderObject(T orderId, String pair, double volume, double price,
                                               Params params) throws Exception {
        return new OrderEdited(editStopLossOrderJSON(orderId, pair, volume, price, params));
    }

    public <T> String editStopLossOrder(T orderId, String pair, double volume, double price) throws Exception {
        return editStopLossOrder(orderId, pair, volume, price, null);
    }

    public <T> JSONObject editStopLossOrderJSON(T orderId, String pair, double volume, double price) throws Exception {
        return editStopLossOrderJSON(orderId, pair, volume, price, null);
    }

    public <T> OrderEdited editStopLossOrderObject(T orderId, String pair, double volume, double price) throws Exception {
        return new OrderEdited(editStopLossOrderJSON(orderId, pair, volume, price, null));
    }

    public <T> String editStopLossLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                         String offsetType, Params params) throws Exception {
        return editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, params);
    }

    public <T> JSONObject editStopLossLimitOrderJSON(T orderId, String pair, double volume, double price, double price2,
                                                 String offsetType, Params params) throws Exception {
        return new JSONObject(editStopLossLimitOrder(orderId, pair, volume, price, price2, offsetType, params));
    }

    public <T> OrderEdited editStopLossLimitOrderObject(T orderId, String pair, double volume, double price, double price2,
                                                    String offsetType, Params params) throws Exception {
        return new OrderEdited(editStopLossLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, params));
    }

    public <T> String editStopLossLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                         String offsetType) throws Exception {
        return editStopLossLimitOrder(orderId, pair, volume, price, price2, offsetType, null);
    }

    public <T> JSONObject editStopLossLimitOrderJSON(T orderId, String pair, double volume, double price, double price2,
                                                 String offsetType) throws Exception {
        return editStopLossLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, null);
    }

    public <T> OrderEdited editStopLossLimitOrderObject(T orderId, String pair, double volume, double price, double price2,
                                                    String offsetType) throws Exception {
        return new OrderEdited(editStopLossLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, null));
    }

    public <T> String editTakeProfitOrder(T orderId, String pair, double volume, double price, Params params) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, params);
    }

    public <T> JSONObject editTakeProfitOrderJSON(T orderId, String pair, double volume, double price,
                                              Params params) throws Exception {
        return new JSONObject(editTakeProfitOrder(orderId, pair, volume, price, params));
    }

    public <T> OrderEdited editTakeProfitOrderObject(T orderId, String pair, double volume, double price,
                                                 Params params) throws Exception {
        return new OrderEdited(editTakeProfitOrderJSON(orderId, pair, volume, price, params));
    }

    public <T> String editTakeProfitOrder(T orderId, String pair, double volume, double price) throws Exception {
        return editTakeProfitOrder(orderId, pair, volume, price, null);
    }

    public <T> JSONObject editTakeProfitOrderJSON(T orderId, String pair, double volume, double price) throws Exception {
        return editTakeProfitOrderJSON(orderId, pair, volume, price, null);
    }

    public <T> OrderEdited editTakeProfitOrderObject(T orderId, String pair, double volume, double price) throws Exception {
        return new OrderEdited(editTakeProfitOrderJSON(orderId, pair, volume, price, null));
    }

    public <T> String editTakeProfitLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                           String offsetType, Params params) throws Exception {
        return editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, params);
    }

    public <T> JSONObject editTakeProfitLimitOrderJSON(T orderId, String pair, double volume, double price, double price2,
                                                   String offsetType, Params params) throws Exception {
        return new JSONObject(editTakeProfitLimitOrder(orderId, pair, volume, price, price2, offsetType, params));
    }

    public <T> OrderEdited editTakeProfitLimitOrderObject(T orderId, String pair, double volume, double price, double price2,
                                                      String offsetType, Params params) throws Exception {
        return new OrderEdited(editTakeProfitLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, params));
    }

    public <T> String editTakeProfitLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                           String offsetType) throws Exception {
        return editTakeProfitLimitOrder(orderId, pair, volume, price, price2, offsetType, null);
    }

    public <T> JSONObject editTakeProfitLimitOrderJSON(T orderId, String pair, double volume, double price, double price2,
                                                   String offsetType) throws Exception {
        return editTakeProfitLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, null);
    }

    public <T> OrderEdited editTakeProfitLimitOrderObject(T orderId, String pair, double volume, double price, double price2,
                                                      String offsetType) throws Exception {
        return new OrderEdited(editTakeProfitLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, null));
    }

    private <T> String editPriceOrder(T orderId, String pair, double volume, double price, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        return editOrder(orderId, pair, volume, params);
    }

    private <T> String editPriceLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                       String offsetType, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("price2", offsetType + price2);
        return editOrder(orderId, pair, volume, params);
    }

    private <T> String editOrder(T orderId, String pair, double volume, Params params) throws Exception {
        addBaseEditParameters(orderId, pair, volume, params);
        return sendPostRequest(EDIT_ORDER_ENDPOINT, params);
    }

    public <T> String cancelOrder(T orderId) throws Exception {
        String idKey = "txid";
        if(orderId instanceof Number)
            idKey = "userref";
        Params params = new Params();
        params.addParam(idKey, orderId);
        return sendPostRequest(CANCEL_ORDER_ENDPOINT, params);
    }

    public <T> JSONObject cancelOrderJSON(T orderId) throws Exception {
        return new JSONObject(cancelOrder(orderId));
    }

    public <T> OrderCancelledStatus cancelOrderObject(T orderId) throws Exception {
        return new OrderCancelledStatus(cancelOrderJSON(orderId));
    }

}
