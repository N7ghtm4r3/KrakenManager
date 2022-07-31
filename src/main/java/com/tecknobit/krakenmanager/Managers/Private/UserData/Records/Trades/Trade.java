package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * The {@code Trade} class is useful to format trade object and give base trade instances
 * @apiNote see official documentation at:
 <ul>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
            https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     </li>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
            https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory</a>
     </li>
 </ul>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class Trade extends KrakenManager.KrakenResponse {

    /**
     * {@code tradeId} is instance that memorizes trade identifier value
     * **/
    protected final String tradeId;

    /**
     * {@code orderTransactionId} is instance that memorizes order transaction identifier value
     * **/
    protected final String orderTransactionId;

    /**
     * {@code pair} is instance that memorizes pair value
     * **/
    protected final String pair;

    /**
     * {@code time} is instance that memorizes time value
     * **/
    protected final long time;

    /**
     * {@code type} is instance that memorizes type of order value -> (buy or sell)
     * **/
    protected final String type;

    /**
     * {@code orderType} is instance that memorizes order type value
     * **/
    protected final String orderType;

    /**
     * {@code price} is instance that memorizes price value
     * **/
    protected final double price;

    /**
     * {@code cost} is instance that memorizes cost value
     * **/
    protected final double cost;

    /**
     * {@code fee} is instance that memorizes fee value
     * **/
    protected final double fee;

    /**
     * {@code vol} is instance that memorizes vol value
     * **/
    protected final double vol;

    /**
     * {@code margin} is instance that memorizes margin value
     * **/
    protected final double margin;

    /**
     * {@code misc} is instance that memorizes misc value
     * **/
    protected final String misc;

    /** Constructor to init a {@link Trade} object
     * @param jsonResponse : base json response
     * @param tradeId: trade identifier value
     * @param orderTransactionId: order transaction identifier value
     * @param pair: pair value
     * @param time: time value
     * @param type: type value
     * @param orderType: order type value
     * @param price: price value
     * @param cost: cost value
     * @param fee: fee value
     * @param vol: vol value
     * @param margin: margin value
     * @param misc: misc value
     **/
    public Trade(JSONObject jsonResponse, String tradeId, String orderTransactionId, String pair, long time,
                 String type, String orderType, double price, double cost, double fee, double vol, double margin,
                 String misc) {
        super(jsonResponse);
        this.tradeId = tradeId;
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

    /** Constructor to init a {@link Trade} object
     * @param tradeId: trade identifier value
     * @param orderTransactionId: order transaction identifier value
     * @param pair: pair value
     * @param time: time value
     * @param type: type value
     * @param orderType: order type value
     * @param price: price value
     * @param cost: cost value
     * @param fee: fee value
     * @param vol: vol value
     * @param margin: margin value
     * @param misc: misc value
     **/
    public Trade(String tradeId, String orderTransactionId, String pair, long time, String type, String orderType,
                 double price, double cost, double fee, double vol, double margin, String misc) {
        super(null);
        this.tradeId = tradeId;
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

    /** Constructor to init a {@link Trade} object
     * @param jsonResponse : base json response
     * @param tradeId: trade identifier value
     **/
    public Trade(JSONObject jsonResponse, String tradeId) {
        super(jsonResponse);
        this.tradeId = tradeId;
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

    public String getTradeId() {
        return tradeId;
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
                "tradeId='" + tradeId + '\'' +
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
