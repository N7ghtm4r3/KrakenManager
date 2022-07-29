package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class HistoryTrade extends Trade {

    private final String postStatus;
    private final double cPrice;
    private final double cCost;
    private final double cFee;
    private final double cVol;
    private final double cMargin;
    private final double net;
    private final ArrayList<Long> trades;

    public HistoryTrade(JSONObject jsonResponse, String transactionId, String orderTransactionId, String pair, long time, String type,
                        String orderType, double price, double cost, double fee, double vol, double margin, String misc,
                        String postStatus, double cPrice, double cCost, double cFee, double cVol, double cMargin, double net,
                        ArrayList<Long> trades) {
        super(jsonResponse, transactionId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc);
        this.postStatus = postStatus;
        this.cPrice = cPrice;
        this.cCost = cCost;
        this.cFee = cFee;
        this.cVol = cVol;
        this.cMargin = cMargin;
        this.net = net;
        this.trades = trades;
    }

    public HistoryTrade(String transactionId, String orderTransactionId, String pair, long time, String type,
                        String orderType, double price, double cost, double fee, double vol, double margin, String misc,
                        String postStatus, double cPrice, double cCost, double cFee, double cVol, double cMargin,
                        double net, ArrayList<Long> trades) {
        super(transactionId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc);
        this.postStatus = postStatus;
        this.cPrice = cPrice;
        this.cCost = cCost;
        this.cFee = cFee;
        this.cVol = cVol;
        this.cMargin = cMargin;
        this.net = net;
        this.trades = trades;
    }

    public HistoryTrade(JSONObject jsonResponse, String transactionId) {
        super(jsonResponse, transactionId);
        postStatus = jsonResponse.getString("posstatus");
        cPrice = jsonResponse.getDouble("cprice");
        cCost = jsonResponse.getDouble("ccost");
        cFee = jsonResponse.getDouble("cfee");
        cVol = jsonResponse.getDouble("cvol");
        cMargin = jsonResponse.getDouble("cmargin");
        net = jsonResponse.getDouble("net");
        JSONArray jsonTrades = JsonHelper.getJSONArray(jsonResponse, "trades", new JSONArray());
        trades = new ArrayList<>();
        for (int j = 0; j < jsonTrades.length(); j++)
            trades.add(jsonTrades.getLong(j));
    }

    public String getPostStatus() {
        return postStatus;
    }

    public double getcPrice() {
        return cPrice;
    }

    public double getcCost() {
        return cCost;
    }

    public double getcFee() {
        return cFee;
    }

    public double getcVol() {
        return cVol;
    }

    public double getcMargin() {
        return cMargin;
    }

    public double getNet() {
        return net;
    }

    public ArrayList<Long> getTrades() {
        return trades;
    }

    @Override
    public String toString() {
        return "HistoryTrade{" +
                "postStatus='" + postStatus + '\'' +
                ", cPrice=" + cPrice +
                ", cCost=" + cCost +
                ", cFee=" + cFee +
                ", cVol=" + cVol +
                ", cMargin=" + cMargin +
                ", net=" + net +
                ", trades=" + trades +
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
