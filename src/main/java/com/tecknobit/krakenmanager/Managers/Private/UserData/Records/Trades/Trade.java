package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class Trade extends KrakenManager.KrakenResponse {

    protected final String transactionId;
    protected final String orderTransactionId;
    protected final String pair;
    protected final long time;
    protected final String type;
    protected final String orderType;
    protected final double price;
    protected final double cost;
    protected final double fee;
    protected final double vol;
    protected final double margin;
    protected final String misc;

    public Trade(JSONObject jsonResponse, String transactionId, String orderTransactionId, String pair, long time,
                 String type, String orderType, double price, double cost, double fee, double vol, double margin, String misc) {
        super(jsonResponse);
        this.transactionId = transactionId;
        this.orderTransactionId = orderTransactionId;
        this.pair = pair;
        this.time = time;
        this.type = type;
        this.orderType = orderType;
        this.price = price;
        this.cost = cost;
        this.fee = fee;
        this.vol = vol;
        this.margin = margin;
        this.misc = misc;
    }

    public Trade(String transactionId, String orderTransactionId, String pair, long time, String type, String orderType,
                 double price, double cost, double fee, double vol, double margin, String misc) {
        super(null);
        this.transactionId = transactionId;
        this.orderTransactionId = orderTransactionId;
        this.pair = pair;
        this.time = time;
        this.type = type;
        this.orderType = orderType;
        this.price = price;
        this.cost = cost;
        this.fee = fee;
        this.vol = vol;
        this.margin = margin;
        this.misc = misc;
    }

    /**
     * Constructor to init a {@link Trade}
     * @param jsonResponse : base json response
     **/
    public Trade(JSONObject jsonResponse, String transactionId) {
        super(jsonResponse);
        this.transactionId = transactionId;
        orderTransactionId = jsonResponse.getString("ordertxid");
        pair = jsonResponse.getString("pair");
        time = jsonResponse.getLong("time");
        type = jsonResponse.getString("type");
        orderType = jsonResponse.getString("ordertype");
        price = jsonResponse.getDouble("price");
        cost = jsonResponse.getDouble("cost");
        fee = jsonResponse.getDouble("fee");
        vol = jsonResponse.getDouble("vol");
        margin = jsonResponse.getDouble("margin");
        misc = jsonResponse.getString("misc");
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getOrderTransactionId() {
        return orderTransactionId;
    }

    public String getPair() {
        return pair;
    }

    public long getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getOrderType() {
        return orderType;
    }

    public double getPrice() {
        return price;
    }

    public double getCost() {
        return cost;
    }

    public double getFee() {
        return fee;
    }

    public double getVol() {
        return vol;
    }

    public double getMargin() {
        return margin;
    }

    public String getMisc() {
        return misc;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "transactionId='" + transactionId + '\'' +
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
