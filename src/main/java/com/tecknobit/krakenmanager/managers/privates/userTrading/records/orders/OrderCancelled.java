package com.tecknobit.krakenmanager.managers.privates.userTrading.records.orders;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

/**
 * The {@code OrderCancelled} class is useful to format an order that has been cancelled
 * @apiNote see official documentation at:
 * <ul>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders">
 *           https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders</a>
 *    </li>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrderBatch">
 *           https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrderBatch</a>
 *    </li>
 * </ul>
 * @author N7ghtm4r3 - Tecknobit
 * **/
public class OrderCancelled extends KrakenManager.KrakenResponse {

    /**
     * {@code count} is instance that memorizes count of the orders cancelled
     * **/
    protected final int count;

    /** Constructor to init a {@link OrderCancelled} object
     * @param jsonResponse: base json response
     * @param count: count of the orders cancelled
     **/
    public OrderCancelled(JSONObject jsonResponse, int count) {
        super(jsonResponse);
        this.count = count;
    }

    /** Constructor to init a {@link OrderCancelled} object
     * @param count: count of the orders cancelled
     **/
    public OrderCancelled(int count) {
        this(null, count);
    }

    /**
     * Constructor to init a {@link OrderCancelled} object
     *
     * @param jsonResponse: base json response
     **/
    public OrderCancelled(JSONObject jsonResponse) {
        super(jsonResponse);
        count = ((JSONObject) getResult()).getInt("count");
    }

    /**
     * Method to get {@link #count} instance <br>
     * Any params required
     *
     * @return {@link #count} instance as int
     **/
    public int getCount() {
        return count;
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
