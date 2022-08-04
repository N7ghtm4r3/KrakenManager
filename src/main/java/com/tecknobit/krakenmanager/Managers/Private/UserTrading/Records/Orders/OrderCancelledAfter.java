package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

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
        currentTime = orderCancelled.getLong("currentTime");
        triggerTime = orderCancelled.getLong("triggerTime");
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getTriggerTime() {
        return triggerTime;
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
