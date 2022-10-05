package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

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
        if(serverTime != null){
            unixTime = serverTime.getLong("unixtime");
            rfc1123 = serverTime.getString("rfc1123");
        }else{
            unixTime = -1;
            rfc1123 = "";
        }
    }

    public long getUnixTime() {
        return unixTime;
    }

    public String getRfc1123() {
        return rfc1123;
    }

    @Override
    public String toString() {
        return "ServerTime{" +
                "unixTime=" + unixTime +
                ", rfc1123='" + rfc1123 + '\'' +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
