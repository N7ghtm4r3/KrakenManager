package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ClosedOrder extends Order {

    public static final String CLOSE_TIME_BOTH = "both";
    public static final String CLOSE_TIME_OPEN = "open";
    public static final String CLOSE_TIME_CLOSE = "close";

    private final long closeTime;
    private final String reason;

    public ClosedOrder(JSONObject jsonResponse, long refId, long userRef, String status, long openTime, long startTime,
                       long expireTime, OrderDescription orderDescription, double volume, double executedVolume, double cost,
                       double fee, double price, double stopPrice, double limitPrice, String trigger, String misc,
                       String oFlags, ArrayList<Long> trades, long closeTime, String reason) {
        super(jsonResponse, refId, userRef, status, openTime, startTime, expireTime, orderDescription, volume, executedVolume,
                cost, fee, price, stopPrice, limitPrice, trigger, misc, oFlags, trades);
        this.closeTime = closeTime;
        this.reason = reason;
    }

    public ClosedOrder(long refId, long userRef, String status, long openTime, long startTime, long expireTime,
                       OrderDescription orderDescription, double volume, double executedVolume, double cost, double fee,
                       double price, double stopPrice, double limitPrice, String trigger, String misc, String oFlags,
                       ArrayList<Long> trades, long closeTime, String reason) {
        super(refId, userRef, status, openTime, startTime, expireTime, orderDescription, volume, executedVolume, cost,
                fee, price, stopPrice, limitPrice, trigger, misc, oFlags, trades);
        this.closeTime = closeTime;
        this.reason = reason;
    }

    public ClosedOrder(JSONObject jsonResponse) {
        super(jsonResponse);
        closeTime = jsonResponse.getLong("closetm");
        reason = jsonResponse.getString("reason");
    }

    public long getCloseTime() {
        return closeTime;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "ClosedOrder{" +
                "closeTime=" + closeTime +
                ", reason='" + reason + '\'' +
                ", refId=" + refId +
                ", userRef=" + userRef +
                ", status='" + status + '\'' +
                ", openTime=" + openTime +
                ", startTime=" + startTime +
                ", expireTime=" + expireTime +
                ", orderDescription=" + orderDescription +
                ", volume=" + volume +
                ", executedVolume=" + executedVolume +
                ", cost=" + cost +
                ", price=" + price +
                ", stopPrice=" + stopPrice +
                ", limitPrice=" + limitPrice +
                ", trigger='" + trigger + '\'' +
                ", misc='" + misc + '\'' +
                ", oFlags='" + oFlags + '\'' +
                ", trades=" + trades +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
