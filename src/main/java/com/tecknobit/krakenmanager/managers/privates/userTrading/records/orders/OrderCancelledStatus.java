package com.tecknobit.krakenmanager.managers.privates.userTrading.records.orders;

import org.json.JSONObject;

/**
 * The {@code OrderCancelledStatus} class is useful to assemble an order that has been cancelled
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder">
 *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/
public class OrderCancelledStatus extends OrderCancelled {

    /**
     * {@code pending} is flag that memorizes if order cancelation is pending or not
     * **/
    private final boolean pending;

    /** Constructor to init a {@link OrderCancelledStatus} object
     * @param jsonResponse: base json response
     * @param count: count of the orders cancelled
     * @param pending: flag that memorizes if order cancelation is pending or not
     **/
    public OrderCancelledStatus(JSONObject jsonResponse, int count, boolean pending) {
        super(jsonResponse, count);
        this.pending = pending;
    }

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
        pending = ((JSONObject) getResult()).getBoolean("pending");
    }

    /**
     * Method to get {@link #pending} instance <br>
     * Any params required
     *
     * @return {@link #pending} instance as boolean
     **/
    public boolean isPending() {
        return pending;
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
