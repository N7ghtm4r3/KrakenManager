package com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders;

import com.tecknobit.apimanager.formatters.TimeFormatter;
import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

import java.util.Date;

/**
 * The {@code OrderCancelledAfter} class is useful to assemble an order that has been cancelled in {@code after} mode
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter">
 * Cancel All Orders After X</a>
 **/
public class OrderCancelledAfter extends KrakenManager.KrakenResponse {

    /**
     * {@code currentTime} is instance that memorizes current time value
     * **/
    private final long currentTime;

    /**
     * {@code triggerTime} is instance that memorizes trigger time value, so when all orders will be canceled
     * **/
    private final long triggerTime;

    /**
     * Constructor to init a {@link OrderCancelledAfter} object
     *
     * @param currentTime: current time value
     * @param triggerTime: trigger time value, so when all orders will be canceled
     **/
    public OrderCancelledAfter(long currentTime, long triggerTime) {
        super(null);
        this.currentTime = currentTime;
        this.triggerTime = triggerTime;
    }

    /**
     * Constructor to init a {@link OrderCancelledAfter} object
     * @param jsonResponse: base json response
     **/
    public OrderCancelledAfter(JSONObject jsonResponse) {
        super(jsonResponse);
        TimeFormatter.changeDefaultPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        currentTime = TimeFormatter.getDateTimestamp(result.getString("currentTime"));
        triggerTime = TimeFormatter.getDateTimestamp(result.getString("triggerTime"));
    }

    /**
     * Method to get {@link #currentTime} instance <br>
     * Any params required
     *
     * @return {@link #currentTime} instance as long
     **/
    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * Method to get {@link #triggerTime} instance <br>
     * Any params required
     *
     * @return {@link #triggerTime} instance as long
     **/
    public long getTriggerTime() {
        return triggerTime;
    }

    /**
     * Method to transform {@link #currentTime} time value in {@link Date} <br>
     * Any params required
     *
     * @return {@link #currentTime} as {@link Date}
     **/
    public Date getCurrentTimeDate() {
        return TimeFormatter.getDate(currentTime);
    }

    /**
     * Method to transform {@link #triggerTime} time value in {@link Date} <br>
     * Any params required
     *
     * @return {@link #triggerTime} as {@link Date}
     **/
    public Date getTriggerTimeDate() {
        return TimeFormatter.getDate(triggerTime);
    }

}
