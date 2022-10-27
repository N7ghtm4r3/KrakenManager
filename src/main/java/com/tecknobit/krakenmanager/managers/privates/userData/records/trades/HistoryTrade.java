package com.tecknobit.krakenmanager.managers.privates.userData.records.trades;

import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code HistoryTrade} class is useful to format history trade object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
 * https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory</a>
 **/
public class HistoryTrade extends Trade {

    /**
     * {@code postStatus} is instance that memorizes post status value
     * **/
    private final String postStatus;

    /**
     * {@code cPrice} is instance that memorizes average price of closed portion of position (quote currency)
     * **/
    private final double cPrice;

    /**
     * {@code cCost} is instance that memorizes total cost of closed portion of position (quote currency)
     * **/
    private final double cCost;

    /**
     * {@code cFee} is instance that memorizes total fee of closed portion of position (quote currency)
     * **/
    private final double cFee;

    /**
     * {@code cVol} is instance that memorizes total fee of closed portion of position (quote currency)
     * **/
    private final double cVol;

    /**
     * {@code cMargin} is instance that memorizes total margin freed in closed portion of position (quote currency)
     * **/
    private final double cMargin;

    /**
     * {@code net} is instance that memorizes net profit/loss of closed portion of position (quote currency, quote currency scale)
     * **/
    private final double net;

    /**
     * {@code trades} is instance that memorizes list of closing trades for position (if available)
     * **/
    private final ArrayList<Long> trades;

    /** Constructor to init a {@link HistoryTrade} object
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
     * @param postStatus: post status value
     * @param cPrice: average price of closed portion of position (quote currency)
     * @param cCost: total cost of closed portion of position (quote currency)
     * @param cFee: total fee of closed portion of position (quote currency)
     * @param cVol: total fee of closed portion of position (quote currency)
     * @param cMargin: total margin freed in closed portion of position (quote currency)
     * @param net: net profit/loss of closed portion of position (quote currency, quote currency scale)
     * @param trades: list of closing trades for position (if available)
     **/
    public HistoryTrade(JSONObject jsonResponse, String tradeId, String orderTransactionId, String pair, long time, String type,
                        String orderType, double price, double cost, double fee, double vol, double margin, String misc,
                        String postStatus, double cPrice, double cCost, double cFee, double cVol, double cMargin, double net,
                        ArrayList<Long> trades) {
        super(jsonResponse, tradeId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc);
        this.postStatus = postStatus;
        this.cPrice = cPrice;
        this.cCost = cCost;
        this.cFee = cFee;
        this.cVol = cVol;
        this.cMargin = cMargin;
        this.net = net;
        this.trades = trades;
    }

    /** Constructor to init a {@link HistoryTrade} object
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
     * @param postStatus: post status value
     * @param cPrice: average price of closed portion of position (quote currency)
     * @param cCost: total cost of closed portion of position (quote currency)
     * @param cFee: total fee of closed portion of position (quote currency)
     * @param cVol: total fee of closed portion of position (quote currency)
     * @param cMargin: total margin freed in closed portion of position (quote currency)
     * @param net: net profit/loss of closed portion of position (quote currency, quote currency scale)
     * @param trades: list of closing trades for position (if available)
     **/
    public HistoryTrade(String tradeId, String orderTransactionId, String pair, long time, String type,
                        String orderType, double price, double cost, double fee, double vol, double margin, String misc,
                        String postStatus, double cPrice, double cCost, double cFee, double cVol, double cMargin,
                        double net, ArrayList<Long> trades) {
        this(null, tradeId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc,
                postStatus, cPrice, cCost, cFee, cVol, cMargin, net, trades);
    }

    /** Constructor to init a {@link HistoryTrade} object
     * @param jsonResponse: base json response
     * @param tradeId: trade identifier value
     **/
    public HistoryTrade(JSONObject jsonResponse, String tradeId) {
        super(jsonResponse, tradeId);
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

    /**
     * Method to get {@link #postStatus} instance <br>
     * Any params required
     *
     * @return {@link #postStatus} instance as {@link String}
     **/
    public String getPostStatus() {
        return postStatus;
    }

    /**
     * Method to get {@link #cPrice} instance <br>
     * Any params required
     *
     * @return {@link #cPrice} instance as double
     **/
    public double getcPrice() {
        return cPrice;
    }

    /**
     * Method to get {@link #cPrice} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #cPrice} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getcPrice(int decimals) {
        return roundValue(cPrice, decimals);
    }

    /**
     * Method to get {@link #cCost} instance <br>
     * Any params required
     *
     * @return {@link #cCost} instance as double
     **/
    public double getcCost() {
        return cCost;
    }

    /**
     * Method to get {@link #cCost} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #cCost} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getcCost(int decimals) {
        return roundValue(cCost, decimals);
    }

    /**
     * Method to get {@link #cFee} instance <br>
     * Any params required
     *
     * @return {@link #cFee} instance as double
     **/
    public double getcFee() {
        return cFee;
    }

    /**
     * Method to get {@link #cFee} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #cFee} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getcFee(int decimals) {
        return roundValue(cCost, decimals);
    }

    /**
     * Method to get {@link #cVol} instance <br>
     * Any params required
     *
     * @return {@link #cVol} instance as double
     **/
    public double getcVol() {
        return cVol;
    }

    /**
     * Method to get {@link #cVol} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #cVol} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getcVol(int decimals) {
        return roundValue(cVol, decimals);
    }

    /**
     * Method to get {@link #cMargin} instance <br>
     * Any params required
     *
     * @return {@link #cMargin} instance as double
     **/
    public double getcMargin() {
        return cMargin;
    }

    /**
     * Method to get {@link #cMargin} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #cMargin} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getcMargin(int decimals) {
        return roundValue(cMargin, decimals);
    }

    /**
     * Method to get {@link #net} instance <br>
     * Any params required
     *
     * @return {@link #net} instance as double
     **/
    public double getNet() {
        return net;
    }

    /**
     * Method to get {@link #net} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #net} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getNet(int decimals) {
        return roundValue(net, decimals);
    }

    /**
     * Method to get {@link #trades} instance <br>
     * Any params required
     *
     * @return {@link #trades} instance as {@link ArrayList} of {@link Long}
     **/
    public ArrayList<Long> getTrades() {
        return trades;
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
