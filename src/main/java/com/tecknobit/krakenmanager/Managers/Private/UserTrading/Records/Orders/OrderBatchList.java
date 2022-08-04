package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order.*;
import static com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderAdded.*;

public class OrderBatchList {

    private final JSONArray orders;
    private final String pair;
    private final Params params;

    public OrderBatchList(String pair) {
        orders = new JSONArray();
        params = new Params();
        this.pair = pair;
    }

    public void addMarkeOrder(String type, double volume) {
        addMarkeOrder(type, volume, params);
    }

    public void addMarkeOrder(String type, double volume, Params params) {
        orders.put(createBaseOrder(MARKET_ORDER_TYPE, type, volume, params));
    }

    public void addLimitOrder(String type, double volume, double price) {
        addLimitOrder(type, volume, price, params);
    }

    public void addLimitOrder(String type, double volume, double price, Params params) {
        JSONObject limitOrder = createBaseOrder(LIMIT_ORDER_TYPE, type, volume, params);
        if(price < 0)
            throw new IllegalArgumentException("Price value cannot be smaller than 0");
        else
            limitOrder.put("price", price);
        orders.put(limitOrder);
    }

    public void addStopLossOrder(String type, double volume, double price, String trigger) {
        addStopLossOrder(type, volume, price, trigger, params);
    }

    public void addStopLossOrder(String type, double volume, double price, String trigger, Params params) {
        addLevelOrder(STOP_LOSS_ORDER_TYPE, type, volume, price, trigger, params);
    }

    public void addStopLossLimitOrder(String type, double volume, double price, double price2, String trigger,
                                      String offsetType) {
        addStopLossLimitOrder(type, volume, price, price2, trigger, offsetType, params);
    }

    public void addStopLossLimitOrder(String type, double volume, double price, double price2, String trigger,
                                      String offsetType, Params params) {
        addLevelLimitOrder(STOP_LOSS_LIMIT_ORDER_TYPE, type, volume, price, price2, trigger, offsetType, params);
    }

    public void addTakeProfitOrder(String type, double volume, double price, String trigger) {
        addTakeProfitOrder(type, volume, price, trigger, params);
    }

    public void addTakeProfitOrder(String type, double volume, double price, String trigger, Params params) {
        addLevelOrder(TAKE_PROFIT_ORDER_TYPE, type, volume, price, trigger, params);
    }

    public void addTakeProfitLimitOrder(String type, double volume, double price, double price2, String trigger,
                                      String offsetType) {
        addTakeProfitLimitOrder(type, volume, price, price2, trigger, offsetType, params);
    }

    public void addTakeProfitLimitOrder(String type, double volume, double price, double price2, String trigger,
                                      String offsetType, Params params) {
        addLevelLimitOrder(TAKE_PROFIT_LIMIT_ORDER_TYPE, type, volume, price, price2, trigger, offsetType, params);
    }

    private void addLevelOrder(String orderType, String type, double volume, double price, String trigger, Params params) {
        JSONObject levelOrder = createBaseOrder(orderType, type, volume, params);
        if(price < 0)
            throw new IllegalArgumentException("Price value cannot be smaller than 0");
        else
            levelOrder.put("price", price);
        if(!isTriggerValid(trigger))
            throw new IllegalArgumentException("Trigger value must be last or index");
        else
            levelOrder.put("trigger", trigger);
        orders.put(levelOrder);
    }

    private void addLevelLimitOrder(String orderType, String type, double volume, double price, double price2,
                                    String trigger, String offsetType, Params params) {
        JSONObject levelLimitOrder = createBaseOrder(orderType, type, volume, params);
        if(price < 0)
            throw new IllegalArgumentException("Price value cannot be smaller than 0");
        else
            levelLimitOrder.put("price", price);
        if(!isTriggerValid(trigger))
            throw new IllegalArgumentException("Trigger value must be last or index");
        else
            levelLimitOrder.put("trigger", trigger);
        if(price2 < 0)
            throw new IllegalArgumentException("Price 2 value cannot be smaller than 0");
        else{
            if(!isOffsetValid(offsetType))
                throw new IllegalArgumentException("Offset value must be +,-,# or %");
            else
                levelLimitOrder.put("price2", offsetType + price2);
        }
        orders.put(levelLimitOrder);
    }

    private JSONObject createBaseOrder(String orderType, String type, double volume, Params params) {
        if(!type.equals(BUY_TYPE) && !type.equals(SELL_TYPE))
            throw new IllegalArgumentException("Order type must be buy or sell");
        if(volume < 0)
            throw new IllegalArgumentException("Volume value cannot be smaller than 0");
        addBaseOrderParameters(orderType, type, volume, pair, params);
        JSONObject baseOrder = new JSONObject();
        for (String key : params.getParamsKeys())
            baseOrder.put(key, (Object) params.getParam(key));
        return baseOrder;
    }

    private boolean isTriggerValid(String trigger) {
        return trigger.equals(TRIGGER_LAST) || trigger.equals(TRIGGER_INDEX);
    }

    private boolean isOffsetValid(String offset) {
        return offset.equals(ADD_OFFSET_AMOUNT) || offset.equals(SUBTRATS_OFFSET_AMOUNT)
                || offset.equals(GENERIC_OFFSET_AMOUNT) || offset.equals(RELATIVE_PERCENTAGE_OFFSET_AMOUNT);
    }

    public String getPair() {
        return pair;
    }

    public JSONArray getOrders() {
        return orders;
    }

}
