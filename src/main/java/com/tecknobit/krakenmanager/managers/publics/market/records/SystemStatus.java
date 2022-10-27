package com.tecknobit.krakenmanager.managers.publics.market.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

/**
 * The {@code SystemStatus} class is useful to format SystemStatus data object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
 *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/
public class SystemStatus extends KrakenManager.KrakenResponse {

    /**
     * {@code status} is instance that memorizes status value
     * **/
    private final String status;

    /**
     * {@code timestamp} is instance that memorizes timestamp value
     * **/
    private final String timestamp;

    /** Constructor to init a {@link SystemStatus} object
     * @param jsonResponse: base json response
     * @param status: status value
     * @param timestamp: timestamp value
     * **/
    public SystemStatus(JSONObject jsonResponse, String status, String timestamp) {
        super(jsonResponse);
        this.status = status;
        this.timestamp = timestamp;
    }

    /** Constructor to init a {@link SystemStatus} object
     * @param status: status value
     * @param timestamp: timestamp value
     * **/
    public SystemStatus(String status, String timestamp) {
        this(null, status, timestamp);
    }

    /** Constructor to init a {@link SystemStatus} object
     * @param jsonResponse: base json response
     * **/
    public SystemStatus(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject status = getResult();
        if (status != null) {
            this.status = status.getString("status");
            timestamp = status.getString("timestamp");
        } else {
            this.status = "";
            timestamp = "";
        }
    }

    /**
     * Method to get {@link #status} instance <br>
     * Any params required
     *
     * @return {@link #status} instance as {@link String}
     **/
    public String getStatus() {
        return status;
    }

    /**
     * Method to get {@link #timestamp} instance <br>
     * Any params required
     *
     * @return {@link #timestamp} instance as {@link String}
     **/
    public String getTimestamp() {
        return timestamp;
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
