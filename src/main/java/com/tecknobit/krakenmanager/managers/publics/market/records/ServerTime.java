package com.tecknobit.krakenmanager.managers.publics.market.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

/**
 * The {@code Book} class is useful to format Book data object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
 *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/
public class ServerTime extends KrakenManager.KrakenResponse {

    /**
     * {@code unixTime} is instance that memorizes unix time value
     * **/
    private final long unixTime;

    /**
     * {@code rfc1123} is instance that memorizes rfc1123 value
     * **/
    private final String rfc1123;

    /** Constructor to init a {@link ServerTime} object
     * @param jsonResponse: base json response
     * @param unixTime: unix time value
     * @param rfc1123: rfc1123 value
     * **/
    public ServerTime(JSONObject jsonResponse, long unixTime, String rfc1123) {
        super(jsonResponse);
        this.unixTime = unixTime;
        this.rfc1123 = rfc1123;
    }

    /** Constructor to init a {@link ServerTime} object
     * @param unixTime: unix time value
     * @param rfc1123: rfc1123 value
     * **/
    public ServerTime(long unixTime, String rfc1123) {
        this(null, unixTime, rfc1123);
    }

    /** Constructor to init a {@link ServerTime} object
     * @param jsonResponse: base json response
     * **/
    public ServerTime(JSONObject jsonResponse) {
        super(jsonResponse);
        final JSONObject serverTime = getResult();
        if (serverTime != null) {
            unixTime = serverTime.getLong("unixtime");
            rfc1123 = serverTime.getString("rfc1123");
        } else {
            unixTime = -1;
            rfc1123 = "";
        }
    }

    /**
     * Method to get {@link #unixTime} instance <br>
     * Any params required
     *
     * @return {@link #unixTime} instance as long
     **/
    public long getUnixTime() {
        return unixTime;
    }

    /**
     * Method to get {@link #rfc1123} instance <br>
     * Any params required
     *
     * @return {@link #rfc1123} instance as {@link String}
     **/
    public String getRfc1123() {
        return rfc1123;
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
