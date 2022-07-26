package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

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

    /** Constructor to init a {@link SystemStatus}
     * @param jsonResponse: base json response
     * @param status: status value
     * @param timestamp: timestamp value
     * **/
    public SystemStatus(JSONObject jsonResponse, String status, String timestamp) {
        super(jsonResponse);
        this.status = status;
        this.timestamp = timestamp;
    }

    /** Constructor to init a {@link SystemStatus}
     * @param status: status value
     * @param timestamp: timestamp value
     * **/
    public SystemStatus(String status, String timestamp) {
        super(null);
        this.status = status;
        this.timestamp = timestamp;
    }

    /** Constructor to init a {@link SystemStatus}
     * @param jsonResponse: base json response
     * **/
    public SystemStatus(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject status = getResult();
        if(status != null){
            this.status = status.getString("status");
            timestamp = status.getString("timestamp");
        }else{
            this.status = "";
            timestamp = "";
        }
    }

    public String getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "SystemStatus{" +
                "status='" + status + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
