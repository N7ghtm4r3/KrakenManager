package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * The {@code OrderCancelledAfter} class is useful to assemble an order that has been cancelled in {@code after} mode
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter">
 *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class OrderCancelledAfter extends KrakenManager.KrakenResponse {

    /**
     * {@code currentTime} is instance that memorizes current time value
     * **/
    private final long currentTime;

    /**
     * {@code currentTimeDate} is instance that memorizes current time value as {@link Date}
     * **/
    private Date currentTimeDate;

    /**
     * {@code triggerTime} is instance that memorizes trigger time value, so when all orders will be canceled
     * **/
    private final long triggerTime;

    /**
     * {@code triggerTimeDate} is instance that memorizes trigger time value, so when all orders will be canceled as {@link Date}
     * **/
    private Date triggerTimeDate;

    /** Constructor to init a {@link OrderCancelledAfter} object
     * @param jsonResponse: base json response
     * @param currentTime: current time value
     * @param triggerTime: trigger time value, so when all orders will be canceled
     **/
    public OrderCancelledAfter(JSONObject jsonResponse, long currentTime, long triggerTime) {
        super(jsonResponse);
        this.currentTime = currentTime;
        this.triggerTime = triggerTime;
    }

    /** Constructor to init a {@link OrderCancelledAfter} object
     * @param currentTime: current time value
     * @param triggerTime: trigger time value, so when all orders will be canceled
     **/
    public OrderCancelledAfter(long currentTime, long triggerTime) {
        this(null, currentTime, triggerTime);
    }

    /**
     * Constructor to init a {@link OrderCancelledAfter} object
     * @param jsonResponse: base json response
     **/
    public OrderCancelledAfter(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject orderCancelled = getResult();
        currentTime = getDateTimeStamp(orderCancelled, "currentTime");
        triggerTime = getDateTimeStamp(orderCancelled, "triggerTime");
    }

    /** Method to transform string time value in long value
     * @param order: JSON that contains time detail
     * @param key: key of time to transform
     * @return time as long es 169294220821
     * **/
    private long getDateTimeStamp(JSONObject order, String key) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(order.getString(key)).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getTriggerTime() {
        return triggerTime;
    }

    /** Method to transform {@link #currentTime} time value in {@link Date} <br>
     * Any params required
     * @return {@link #currentTime} as {@link Date}
     * **/
    public Date getCurrentTimeDate() {
        if(currentTimeDate == null)
            return currentTimeDate = new Date(currentTime);
        return currentTimeDate;
    }

    /** Method to transform {@link #triggerTime} time value in {@link Date} <br>
     * Any params required
     * @return {@link #triggerTime} as {@link Date}
     * **/
    public Date getTriggerTimeDate() {
        if(triggerTimeDate == null)
            return triggerTimeDate = new Date(triggerTime);
        return triggerTimeDate;
    }

    @Override
    public String toString() {
        return "OrderCancelledAfter{" +
                "currentTime=" + currentTime +
                ", triggerTime=" + triggerTime +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
