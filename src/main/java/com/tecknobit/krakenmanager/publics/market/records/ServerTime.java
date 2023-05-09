package com.tecknobit.krakenmanager.publics.market.records;

import com.tecknobit.krakenmanager.KrakenManager;
import org.json.JSONObject;

/**
 * The {@code Book} class is useful to format Book data object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime">
 * Get Server Time</a>
 **/
public class ServerTime extends KrakenManager.KrakenResponse {

    /**
     * {@code unixTime} is instance that memorizes unix time value
     * **/
    private final long unixTime;

    /**
     * {@code rfc1123} is instance that memorizes rfc1123 value
     * **/
    private final String rfc1123;

    /**
     * Constructor to init a {@link ServerTime} object
     *
     * @param unixTime: unix time value
     * @param rfc1123:  rfc1123 value
     **/
    public ServerTime(long unixTime, String rfc1123) {
        super(null);
        this.unixTime = unixTime;
        this.rfc1123 = rfc1123;
    }

    /** Constructor to init a {@link ServerTime} object
     * @param jsonResponse: base json response
     * **/
    public ServerTime(JSONObject jsonResponse) {
        super(jsonResponse);
        if (!result.getJSONObjectSource().isEmpty()) {
            unixTime = result.getLong("unixtime", 0);
            rfc1123 = result.getString("rfc1123");
        } else {
            unixTime = -1;
            rfc1123 = "";
        }
    }

    /**
     * Method to get {@link #unixTime} instance <br>
     * No-any params required
     *
     * @return {@link #unixTime} instance as long
     **/
    public long getUnixTime() {
        return unixTime;
    }

    /**
     * Method to get {@link #rfc1123} instance <br>
     * No-any params required
     *
     * @return {@link #rfc1123} instance as {@link String}
     **/
    public String getRfc1123() {
        return rfc1123;
    }

}
