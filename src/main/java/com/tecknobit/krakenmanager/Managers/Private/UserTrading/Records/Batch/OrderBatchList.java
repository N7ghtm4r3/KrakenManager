package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Batch;

import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order;
import com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderAdded;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order.*;
import static com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders.OrderAdded.*;

/**
 * The {@code OrderBatchList} class is useful to assemble order batch list object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
 *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class OrderBatchList {

    /**
     * {@code orders} is instance that memorizes all orders added
     * **/
    private final JSONArray orders;

    /**
     * {@code pair} is instance that memorizes pair value
     * **/
    private final String pair;

    /**
     * {@code params} is instance that memorizes {@link Params} object to insert details in list
     * **/
    private final Params params;

    /** Constructor to init a {@link OrderBatchList} object
     * @param pair: pair value
     **/
    public OrderBatchList(String pair) {
        orders = new JSONArray();
        params = new Params();
        this.pair = pair;
    }

    /** Method to add a market order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * **/
    public void addMarkeOrder(String type, double volume) {
        addMarkeOrder(type, volume, params);
    }

    /** Method to add a market order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline or validate
     * **/
    public void addMarkeOrder(String type, double volume, Params params) {
        orders.put(createBaseOrder(MARKET_ORDER_TYPE, type, volume, params));
    }

    /** Method to add a limit order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * **/
    public void addLimitOrder(String type, double volume, double price) {
        addLimitOrder(type, volume, price, params);
    }

    /** Method to add a limit order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     * **/
    public void addLimitOrder(String type, double volume, double price, Params params) {
        JSONObject limitOrder = createBaseOrder(LIMIT_ORDER_TYPE, type, volume, params);
        if(price < 0)
            throw new IllegalArgumentException("Price value cannot be smaller than 0");
        else
            limitOrder.put("price", price);
        orders.put(limitOrder);
    }

    /** Method to add a stop-loss order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param trigger: price signal used to trigger
     * **/
    public void addStopLossOrder(String type, double volume, double price, String trigger) {
        addStopLossOrder(type, volume, price, trigger, params);
    }

    /** Method to add a stop-loss order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param trigger: price signal used to trigger
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     * **/
    public void addStopLossOrder(String type, double volume, double price, String trigger, Params params) {
        addLevelOrder(STOP_LOSS_ORDER_TYPE, type, volume, price, trigger, params);
    }

    /** Method to add a stop-loss-limit order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param price2: secondary price value
     * @param trigger: price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * **/
    public void addStopLossLimitOrder(String type, double volume, double price, double price2, String trigger,
                                      String offsetType) {
        addStopLossLimitOrder(type, volume, price, price2, trigger, offsetType, params);
    }

    /** Method to add a stop-loss-limit order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param price2: secondary price value
     * @param trigger: price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     * **/
    public void addStopLossLimitOrder(String type, double volume, double price, double price2, String trigger,
                                      String offsetType, Params params) {
        addLevelLimitOrder(STOP_LOSS_LIMIT_ORDER_TYPE, type, volume, price, price2, trigger, offsetType, params);
    }

    /** Method to add a take-profit order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param trigger: price signal used to trigger
     * **/
    public void addTakeProfitOrder(String type, double volume, double price, String trigger) {
        addTakeProfitOrder(type, volume, price, trigger, params);
    }

    /** Method to add a take-profit order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param trigger: price signal used to trigger
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     * **/
    public void addTakeProfitOrder(String type, double volume, double price, String trigger, Params params) {
        addLevelOrder(TAKE_PROFIT_ORDER_TYPE, type, volume, price, trigger, params);
    }

    /** Method to add a take-profit-limit order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param price2: secondary price value
     * @param trigger: price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * **/
    public void addTakeProfitLimitOrder(String type, double volume, double price, double price2, String trigger,
                                      String offsetType) {
        addTakeProfitLimitOrder(type, volume, price, price2, trigger, offsetType, params);
    }

    /** Method to add a take-profit-limit order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param price2: secondary price value
     * @param trigger: price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     * **/
    public void addTakeProfitLimitOrder(String type, double volume, double price, double price2, String trigger,
                                      String offsetType, Params params) {
        addLevelLimitOrder(TAKE_PROFIT_LIMIT_ORDER_TYPE, type, volume, price, price2, trigger, offsetType, params);
    }

    /** Method to add level order
     * @param orderType: stop-loss or take-profit order type -> constants in {@link Order} class
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param trigger: price signal used to trigger
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     * **/
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

    /** Method to add level limit order
     * @param orderType: stop-loss-limit or take-profit-limit order type -> constants in {@link Order} class
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param price2: secondary price value
     * @param trigger: price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     * **/
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
            if(!offsetType.equals(ADD_OFFSET_AMOUNT) && !offsetType.equals(SUBSTRACS_OFFSET_AMOUNT)
                    && !offsetType.equals(GENERIC_OFFSET_AMOUNT) && !offsetType.equals(RELATIVE_PERCENTAGE_OFFSET_AMOUNT))
                throw new IllegalArgumentException("Offset value must be +,-,# or %");
            else
                levelLimitOrder.put("price2", offsetType + price2);
        }
        orders.put(levelLimitOrder);
    }

    /** Method to create base order details
     * @param orderType: order type -> all constants in {@link Order} class
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     * @return base order details as {@link JSONObject}
     * **/
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

    /** Method to check validity of a trigger
     * @param trigger: price signal used to trigger
     * @return validity of trigger as boolean
     * **/
    private boolean isTriggerValid(String trigger) {
        return trigger.equals(TRIGGER_LAST) || trigger.equals(TRIGGER_INDEX);
    }

    public String getPair() {
        return pair;
    }

    public JSONArray getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "OrderBatchList{" +
                "orders=" + orders +
                ", pair='" + pair + '\'' +
                ", params=" + params +
                '}';
    }

}
