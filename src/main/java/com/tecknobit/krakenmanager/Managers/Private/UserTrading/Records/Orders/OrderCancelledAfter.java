package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class OrderCancelledAfter extends KrakenManager.KrakenResponse {

    private final long currentTime;
    private final long triggerTime;

    public OrderCancelledAfter(JSONObject jsonResponse, long currentTime, long triggerTime) {
        super(jsonResponse);
        this.currentTime = currentTime;
        this.triggerTime = triggerTime;
    }

    public OrderCancelledAfter(long currentTime, long triggerTime) {
        super(null);
        this.currentTime = currentTime;
        this.triggerTime = triggerTime;
    }

    /**
     * Constructor to init a {@link OrderCancelledAfter} object
     * @param jsonResponse : base json response
     **/
    public OrderCancelledAfter(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject orderCancelled = getResult();
        currentTime = getDateTimeStamp(orderCancelled, "currentTime");
        triggerTime = getDateTimeStamp(orderCancelled, "triggerTime");
    }

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

    public Date getCurrentTimeDate() {
        return new Date(currentTime);
    }

    public Date getTriggerTimeDate() {
        return new Date(triggerTime);
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
