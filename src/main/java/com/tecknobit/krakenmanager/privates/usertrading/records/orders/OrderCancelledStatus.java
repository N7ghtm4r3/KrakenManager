package com.tecknobit.krakenmanager.privates.usertrading.records.orders;

import org.json.JSONObject;

/**
 * The {@code OrderCancelledStatus} class is useful to assemble an order that has been cancelled
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder">
 * Cancel Order</a>
 **/
public class OrderCancelledStatus extends OrderCancelled {

    /**
     * {@code pending} is flag that memorizes if order cancelation is pending or not
     * **/
    private final boolean pending;

    /**
     * Constructor to init a {@link OrderCancelledStatus} object
     *
     * @param count:   count of the orders cancelled
     * @param pending: flag that memorizes if order cancelation is pending or not
     **/
    public OrderCancelledStatus(int count, boolean pending) {
        super(count);
        this.pending = pending;
    }

    /**
     * Constructor to init a {@link OrderCancelledStatus} object
     *
     * @param jsonResponse: base json response
     **/
    public OrderCancelledStatus(JSONObject jsonResponse) {
        super(jsonResponse);
        pending = result.getBoolean("pending");
    }

    /**
     * Method to get {@link #pending} instance <br>
     * No-any params required
     *
     * @return {@link #pending} instance as boolean
     **/
    public boolean isPending() {
        return pending;
    }

}
