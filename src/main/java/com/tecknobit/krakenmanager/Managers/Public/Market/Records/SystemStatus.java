package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class SystemStatus extends KrakenManager.KrakenResponse {

    private final String status;
    private final String timestamp;

    public SystemStatus(JSONObject jsonResponse, String status, String timestamp) {
        super(jsonResponse);
        this.status = status;
        this.timestamp = timestamp;
    }

    public SystemStatus(String status, String timestamp) {
        super(null);
        this.status = status;
        this.timestamp = timestamp;
    }

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
