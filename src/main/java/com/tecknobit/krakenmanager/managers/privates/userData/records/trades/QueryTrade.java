package com.tecknobit.krakenmanager.managers.privates.userData.records.trades;

import org.json.JSONObject;

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

    /** Constructor to init a {@link QueryTrade} object
     * @param jsonResponse: base json response
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

    /** Constructor to init a {@link QueryTrade} object
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
        this(null, tradeId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc,
                postTradeId);
    }

    /**
     * Constructor to init a {@link QueryTrade} object
     *
     * @param jsonResponse: base json response
     * @param tradeId:      trade identifier value
     **/
    public QueryTrade(JSONObject jsonResponse, String tradeId) {
        super(jsonResponse, tradeId);
        postTradeId = jsonResponse.getString("postxid");
    }

    /**
     * Method to get {@link #postTradeId} instance <br>
     * Any params required
     *
     * @return {@link #postTradeId} instance as {@link String}
     **/
    public String getPostTradeId() {
        return postTradeId;
    }

    /**
     * Returns a string representation of the object <br>
     * Any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
