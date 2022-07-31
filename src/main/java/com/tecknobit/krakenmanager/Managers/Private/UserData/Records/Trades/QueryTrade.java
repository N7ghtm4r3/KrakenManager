package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * The {@code QueryTrade} class is useful to format query trade object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
 *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class QueryTrade extends Trade {

    /**
     * {@code postTradeId} is instance that memorizes post trade identifier
     * **/
    private final String postTradeId;

    /**
     * Constructor to init a {@link QueryTrade} object
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
     * @param postTradeId: post trade identifier
     **/
    public QueryTrade(JSONObject jsonResponse, String tradeId, String orderTransactionId, String pair, long time,
                      String type, String orderType, double price, double cost, double fee, double vol, double margin,
                      String misc, String postTradeId) {
        super(jsonResponse, tradeId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc);
        this.postTradeId = postTradeId;
    }

    /**
     * Constructor to init a {@link QueryTrade} object
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
     * @param postTradeId: post trade identifier
     **/
    public QueryTrade(String tradeId, String orderTransactionId, String pair, long time, String type, String orderType,
                      double price, double cost, double fee, double vol, double margin, String misc, String postTradeId) {
        super(tradeId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc);
        this.postTradeId = postTradeId;
    }

    /**
     * Constructor to init a {@link QueryTrade} object
     * @param jsonResponse : base json response
     * @param tradeId: trade identifier value
     **/
    public QueryTrade(JSONObject jsonResponse, String tradeId) {
        super(jsonResponse, tradeId);
        postTradeId = jsonResponse.getString("postxid");
    }

    public String getPostTradeId() {
        return postTradeId;
    }

    @Override
    public String toString() {
        return "QueryTrade{" +
                "postTradeId='" + postTradeId + '\'' +
                ", tradeId='" + tradeId + '\'' +
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
