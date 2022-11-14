package com.tecknobit.krakenmanager.managers.privates.usertrading.records.batch;

import com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order;
import com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.OrderAdded;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.tecknobit.apimanager.apis.APIRequest.Params;
import static com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order.*;
import static com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order.OrderType.*;
import static com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.OrderAdded.*;

/**
 * The {@code OrderBatchList} class is useful to assemble an orders list for a batch order
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
 * Add Order Batch</a>
 **/
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

    /**
     * Method to add a market order type
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     **/
    public void addMarketOrder(Side type, double volume) {
        addMarketOrder(type, volume, params);
    }

    /**
     * Method to add a market order type
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline or validate
     **/
    public void addMarketOrder(Side type, double volume, Params params) {
        orders.put(createBaseOrder(market, type, volume, params));
    }

    /**
     * Method to add a limit order type
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price:  price value
     **/
    public void addLimitOrder(Side type, double volume, double price) {
        addLimitOrder(type, volume, price, params);
    }

    /**
     * Method to add a limit order type
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price:  price value
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     **/
    public void addLimitOrder(Side type, double volume, double price, Params params) {
        JSONObject limitOrder = createBaseOrder(limit, type, volume, params);
        if (price < 0)
            throw new IllegalArgumentException("Price value cannot be smaller than 0");
        else
            limitOrder.put("price", price);
        orders.put(limitOrder);
    }

    /**
     * Method to add a stop-loss order type
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param price:   price value
     * @param trigger: price signal used to trigger
     **/
    public void addStopLossOrder(Side type, double volume, double price, Trigger trigger) {
        addStopLossOrder(type, volume, price, trigger, params);
    }

    /**
     * Method to add a stop-loss order type
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param price:   price value
     * @param trigger: price signal used to trigger
     * @param params:  extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     **/
    public void addStopLossOrder(Side type, double volume, double price, Trigger trigger, Params params) {
        addLevelOrder(stop_loss, type, volume, price, trigger, params);
    }

    /**
     * Method to add a stop-loss-limit order type
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param price:      price value
     * @param price2:     secondary price value
     * @param trigger:    price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     **/
    public void addStopLossLimitOrder(Side type, double volume, double price, double price2, Trigger trigger,
                                      String offsetType) {
        addStopLossLimitOrder(type, volume, price, price2, trigger, offsetType, params);
    }

    /**
     * Method to add a stop-loss-limit order type
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param price:      price value
     * @param price2:     secondary price value
     * @param trigger:    price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     **/
    public void addStopLossLimitOrder(Side type, double volume, double price, double price2, Trigger trigger,
                                      String offsetType, Params params) {
        addLevelLimitOrder(stop_loss_limit, type, volume, price, price2, trigger, offsetType, params);
    }

    /** Method to add a take-profit order type
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param trigger: price signal used to trigger
     * **/
    public void addTakeProfitOrder(Side type, double volume, double price, Trigger trigger) {
        addTakeProfitOrder(type, volume, price, trigger, params);
    }

    /**
     * Method to add a take-profit order type
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param price:   price value
     * @param trigger: price signal used to trigger
     * @param params:  extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     **/
    public void addTakeProfitOrder(Side type, double volume, double price, Trigger trigger, Params params) {
        addLevelOrder(take_profit, type, volume, price, trigger, params);
    }

    /**
     * Method to add a take-profit-limit order type
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param price:      price value
     * @param price2:     secondary price value
     * @param trigger:    price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     **/
    public void addTakeProfitLimitOrder(Side type, double volume, double price, double price2, Trigger trigger,
                                        String offsetType) {
        addTakeProfitLimitOrder(type, volume, price, price2, trigger, offsetType, params);
    }

    /**
     * Method to add a take-profit-limit order type
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param price:      price value
     * @param price2:     secondary price value
     * @param trigger:    price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     **/
    public void addTakeProfitLimitOrder(Side type, double volume, double price, double price2, Trigger trigger,
                                        String offsetType, Params params) {
        addLevelLimitOrder(take_profit_limit, type, volume, price, price2, trigger, offsetType, params);
    }

    /**
     * Method to add level order
     *
     * @param orderType: stop-loss or take-profit order type -> constants in {@link Order} class
     * @param type:      order direction -> buy or sell
     * @param volume:    order quantity in terms of the base asset
     * @param price:     price value
     * @param trigger:   price signal used to trigger
     * @param params:    extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     **/
    private void addLevelOrder(OrderType orderType, Side type, double volume, double price, Trigger trigger,
                               Params params) {
        JSONObject levelOrder = createBaseOrder(orderType, type, volume, params);
        if(price < 0)
            throw new IllegalArgumentException("Price value cannot be smaller than 0");
        else
            levelOrder.put("price", price);
        levelOrder.put("trigger", trigger);
        orders.put(levelOrder);
    }

    /**
     * Method to add level limit order
     *
     * @param orderType:  stop-loss-limit or take-profit-limit order type -> constants in {@link Order} class
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param price:      price value
     * @param price2:     secondary price value
     * @param trigger:    price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     **/
    private void addLevelLimitOrder(OrderType orderType, Side type, double volume, double price, double price2,
                                    Trigger trigger, String offsetType, Params params) {
        JSONObject levelLimitOrder = createBaseOrder(orderType, type, volume, params);
        if (price < 0)
            throw new IllegalArgumentException("Price value cannot be smaller than 0");
        else
            levelLimitOrder.put("price", price);
        if (price2 < 0)
            throw new IllegalArgumentException("Price 2 value cannot be smaller than 0");
        else{
            if(!offsetType.equals(ADD_OFFSET_AMOUNT) && !offsetType.equals(SUBSTRACS_OFFSET_AMOUNT)
                    && !offsetType.equals(GENERIC_OFFSET_AMOUNT) && !offsetType.equals(RELATIVE_PERCENTAGE_OFFSET_AMOUNT))
                throw new IllegalArgumentException("Offset value must be +,-,# or %");
            else
                levelLimitOrder.put("price2", offsetType + price2);
        }
        levelLimitOrder.put("trigger", trigger);
        orders.put(levelLimitOrder);
    }

    /**
     * Method to create base order details
     *
     * @param orderType: order type -> all constants in {@link Order} class
     * @param type:      order direction -> buy or sell
     * @param volume:    order quantity in terms of the base asset
     * @param params:    extra order details
     * @return base order details as {@link JSONObject}
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,deadline,validate
     * and close
     **/
    private JSONObject createBaseOrder(OrderType orderType, Side type, double volume, Params params) {
        if (volume < 0)
            throw new IllegalArgumentException("Volume value cannot be smaller than 0");
        addBaseOrderParameters(orderType, type, volume, pair, params);
        JSONObject baseOrder = new JSONObject();
        for (String key : params.getParamsKeys())
            baseOrder.put(key, (Object) params.getParam(key));
        return baseOrder;
    }

    /**
     * Method to get {@link #pair} instance <br>
     * Any params required
     *
     * @return {@link #pair} instance as {@link String}
     **/
    public String getPair() {
        return pair;
    }

    /**
     * Method to get {@link #orders} instance <br>
     * Any params required
     *
     * @return {@link #orders} instance as {@link JSONArray}
     **/
    public JSONArray getOrders() {
        return orders;
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
