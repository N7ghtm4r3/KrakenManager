package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades;

import org.json.JSONObject;

import java.util.Arrays;

public class QueryTrade extends Trade {

    private final String postTransactionId;

    public QueryTrade(JSONObject jsonResponse, String transactionId, String orderTransactionId, String pair, long time,
                      String type, String orderType, double price, double cost, double fee, double vol, double margin,
                      String misc, String postTransactionId) {
        super(jsonResponse, transactionId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc);
        this.postTransactionId = postTransactionId;
    }

    public QueryTrade(String transactionId, String orderTransactionId, String pair, long time, String type, String orderType,
                      double price, double cost, double fee, double vol, double margin, String misc, String postTransactionId) {
        super(transactionId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc);
        this.postTransactionId = postTransactionId;
    }

    public QueryTrade(JSONObject jsonResponse, String transactionId) {
        super(jsonResponse, transactionId);
        postTransactionId = jsonResponse.getString("postxid");
    }

    public String getPostTransactionId() {
        return postTransactionId;
    }

    @Override
    public String toString() {
        return "QueryTrade{" +
                "postTransactionId='" + postTransactionId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", orderTransactionId='" + orderTransactionId + '\'' +
                ", pair='" + pair + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", orderType='" + orderType + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", fee=" + fee +
                ", vol=" + vol +
                ", margin=" + margin +
                ", misc='" + misc + '\'' +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
