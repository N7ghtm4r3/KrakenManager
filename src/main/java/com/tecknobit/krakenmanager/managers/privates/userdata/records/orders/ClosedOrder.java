package com.tecknobit.krakenmanager.managers.privates.userdata.records.orders;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code Order} class is useful to format order object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
 *     https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/
public class ClosedOrder extends Order {

    /**
     * {@code CLOSE_TIME_BOTH} is constant for both close time type
     * **/
    public static final String CLOSE_TIME_BOTH = "both";

    /**
     * {@code CLOSE_TIME_OPEN} is constant for open close time type
     * **/
    public static final String CLOSE_TIME_OPEN = "open";

    /**
     * {@code CLOSE_TIME_CLOSE} is constant for close time type
     * **/
    public static final String CLOSE_TIME_CLOSE = "close";

    /**
     * {@code closeTime} is instance that memorizes close time value
     * **/
    private final long closeTime;

    /**
     * {@code closeTime} is instance that memorizes reason value
     * **/
    private final String reason;

    /** Constructor to init a {@link ClosedOrder} object
     *
     * @param refId: referral order transaction id
     * @param userRef: user reference id
     * @param status: status value
     * @param openTime: open time value
     * @param startTime: start time value
     * @param expireTime: expire time value
     * @param orderDescription: order description value
     * @param volume: volume value
     * @param executedVolume: executed volume value
     * @param cost: cost value
     * @param fee: fee value
     * @param price: price value
     * @param stopPrice: stop price value
     * @param limitPrice: limit price value
     * @param trigger: trigger value
     * @param misc: misc value
     * @param oFlags: order flags value
     * @param trades: list of trades id
     * @param closeTime: close time value
     * @param reason: reason value
     **/
    public ClosedOrder(long refId, long userRef, String status, long openTime, long startTime, long expireTime,
                       OrderDescription orderDescription, double volume, double executedVolume, double cost, double fee,
                       double price, double stopPrice, double limitPrice, String trigger, String misc, String oFlags,
                       ArrayList<Long> trades, long closeTime, String reason) {
        super(refId, userRef, status, openTime, startTime, expireTime, orderDescription, volume, executedVolume, cost,
                fee, price, stopPrice, limitPrice, trigger, misc, oFlags, trades);
        this.closeTime = closeTime;
        this.reason = reason;
    }

    /**
     * Constructor to init a {@link ClosedOrder}
     *
     * @param jsonResponse: base json response
     **/
    public ClosedOrder(JSONObject jsonResponse) {
        super(jsonResponse);
        closeTime = result.getLong("closetm", 0);
        reason = result.getString("reason");
    }

    /**
     * Method to get {@link #closeTime} instance <br>
     * Any params required
     *
     * @return {@link #closeTime} instance as long
     **/
    public long getCloseTime() {
        return closeTime;
    }

    /**
     * Method to get {@link #reason} instance <br>
     * Any params required
     *
     * @return {@link #reason} instance as {@link String}
     **/
    public String getReason() {
        return reason;
    }

}
