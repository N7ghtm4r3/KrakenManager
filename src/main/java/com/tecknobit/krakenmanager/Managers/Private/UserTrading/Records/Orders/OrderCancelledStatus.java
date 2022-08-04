package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import org.json.JSONObject;

import java.util.Arrays;

public class OrderCancelledStatus extends OrderCancelled {

    private final boolean pending;

    public OrderCancelledStatus(JSONObject jsonResponse, int count, boolean pending) {
        super(jsonResponse, count);
        this.pending = pending;
    }

    public OrderCancelledStatus(int count, boolean pending) {
        super(count);
        this.pending = pending;
    }

    public OrderCancelledStatus(JSONObject jsonResponse) {
        super(jsonResponse);
        pending = ((JSONObject) getResult()).getBoolean("pending");
    }

    public boolean isPending() {
        return pending;
    }

    @Override
    public String toString() {
        return "OrderCancelledStatus{" +
                "pending=" + pending +
                ", count=" + count +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
