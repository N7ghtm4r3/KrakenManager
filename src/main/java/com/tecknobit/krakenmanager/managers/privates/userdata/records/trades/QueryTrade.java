package com.tecknobit.krakenmanager.managers.privates.userdata.records.trades;

import org.json.JSONObject;

/**
 * The {@code QueryTrade} class is useful to format query trade object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
 * Get Trades Info</a>
 **/
public class QueryTrade extends Trade {

    /**
     * {@code postTradeId} is instance that memorizes post trade identifier
     * **/
    private final String postTradeId;

    /** Constructor to init a {@link QueryTrade} object
     *
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
     *
     * @param jsonResponse: base json response
     **/
    public QueryTrade(JSONObject jsonResponse) {
        super(jsonResponse);
        postTradeId = result.getString("postxid");
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

}
