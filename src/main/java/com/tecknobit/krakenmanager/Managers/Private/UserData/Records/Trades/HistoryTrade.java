package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code HistoryTrade} class is useful to format history trade object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
 *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

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
        super(tradeId, orderTransactionId, pair, time, type, orderType, price, cost, fee, vol, margin, misc);
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
     * @param jsonResponse : base json response
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
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
