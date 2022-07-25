package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class ServerTime extends KrakenManager.KrakenResponse {

    private final long unixTime;
    private final String rfc1123;

    public ServerTime(JSONObject jsonResponse, long unixTime, String rfc1123) {
        super(jsonResponse);
        this.unixTime = unixTime;
        this.rfc1123 = rfc1123;
    }

    public ServerTime(long unixTime, String rfc1123) {
        super(null);
        this.unixTime = unixTime;
        this.rfc1123 = rfc1123;
    }

    public ServerTime(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject serverTime = getResult();
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
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
