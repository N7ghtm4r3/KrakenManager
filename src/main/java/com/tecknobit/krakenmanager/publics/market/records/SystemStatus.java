package com.tecknobit.krakenmanager.publics.market.records;

import com.tecknobit.krakenmanager.KrakenManager;
import org.json.JSONObject;

/**
 * The {@code SystemStatus} class is useful to format SystemStatus data object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
 * Get System Status</a>
 */
public class SystemStatus extends KrakenManager.KrakenResponse {

    /**
     * {@code status} is instance that memorizes status value
     */
    private final String status;

    /**
     * {@code timestamp} is instance that memorizes timestamp value
     */
    private final String timestamp;

    /**
     * Constructor to init a {@link SystemStatus} object
     *
     * @param status:    status value
     * @param timestamp: timestamp value
     */
    public SystemStatus(String status, String timestamp) {
        super(null);
        this.status = status;
        this.timestamp = timestamp;
    }

    /** Constructor to init a {@link SystemStatus} object
     * @param jsonResponse: base json response
     */
    public SystemStatus(JSONObject jsonResponse) {
        super(jsonResponse);
        if (!result.getJSONObjectSource().isEmpty()) {
            this.status = result.getString("status");
            timestamp = result.getString("timestamp");
        } else {
            this.status = "";
            timestamp = "";
        }
    }

    /**
     * Method to get {@link #status} instance <br>
     * No-any params required
     *
     * @return {@link #status} instance as {@link String}
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method to get {@link #timestamp} instance <br>
     * No-any params required
     *
     * @return {@link #timestamp} instance as {@link String}
     */
    public String getTimestamp() {
        return timestamp;
    }

}
