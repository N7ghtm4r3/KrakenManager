package com.tecknobit.krakenmanager.privates.userdata.records.trades;

import com.tecknobit.krakenmanager.KrakenManager;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Trade} class is useful to format trade object and give base trade instances
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 * <li>
 * <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
 * Get Trade History</a>
 * </li>
 * <li>
 * <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
 *   Get Trades Info</a>
 * </li>
 * </ul>
 **/
public abstract class Trade extends KrakenManager.KrakenResponse {

    /**
     * {@code tradeSymbol} is instance that memorizes trade identifier value
     **/
    protected final String tradeSymbol;

    /**
     * {@code orderTransactionId} is instance that memorizes order transaction identifier value
     **/
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
     **/
    protected final double margin;

    /**
     * {@code misc} is instance that memorizes misc value
     **/
    protected final String misc;

    /**
     * Constructor to init a {@link Trade} object
     *
     * @param tradeSymbol:        trade identifier value
     * @param orderTransactionId: order transaction identifier value
     * @param pair:               pair value
     * @param time:               time value
     * @param type:               type value
     * @param orderType:          order type value
     * @param price:              price value
     * @param cost:               cost value
     * @param fee:                fee value
     * @param vol:                vol value
     * @param margin:             margin value
     * @param misc:               misc value
     **/
    public Trade(String tradeSymbol, String orderTransactionId, String pair, long time, String type, String orderType,
                 double price, double cost, double fee, double vol, double margin, String misc) {
        super(null);
        this.tradeSymbol = tradeSymbol;
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
     * Constructor to init a {@link Trade} object
     *
     * @param jsonResponse: base json response
     **/
    public Trade(JSONObject jsonResponse) {
        super(jsonResponse);
        tradeSymbol = result.getString("tradeSymbol");
        orderTransactionId = result.getString("ordertxid");
        pair = result.getString("pair");
        time = result.getLong("time", 0);
        type = result.getString("type");
        orderType = result.getString("ordertype");
        price = result.getDouble("price", 0);
        cost = result.getDouble("cost", 0);
        fee = result.getDouble("fee", 0);
        vol = result.getDouble("vol", 0);
        margin = result.getDouble("margin", 0);
        misc = result.getString("misc");
    }

    /**
     * Method to get {@link #tradeSymbol} instance <br>
     * No-any params required
     *
     * @return {@link #tradeSymbol} instance as {@link String}
     **/
    public String getTradeSymbol() {
        return tradeSymbol;
    }

    /**
     * Method to get {@link #orderTransactionId} instance <br>
     * No-any params required
     *
     * @return {@link #orderTransactionId} instance as {@link String}
     **/
    public String getOrderTransactionId() {
        return orderTransactionId;
    }

    /**
     * Method to get {@link #pair} instance <br>
     * No-any params required
     *
     * @return {@link #pair} instance as {@link String}
     **/
    public String getPair() {
        return pair;
    }

    /**
     * Method to get {@link #time} instance <br>
     * No-any params required
     *
     * @return {@link #time} instance as long
     **/
    public long getTime() {
        return time;
    }

    /**
     * Method to get {@link #type} instance <br>
     * No-any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public String getType() {
        return type;
    }

    /**
     * Method to get {@link #orderType} instance <br>
     * No-any params required
     *
     * @return {@link #orderType} instance as {@link String}
     **/
    public String getOrderType() {
        return orderType;
    }

    /**
     * Method to get {@link #price} instance <br>
     * No-any params required
     *
     * @return {@link #price} instance as double
     **/
    public double getPrice() {
        return price;
    }

    /**
     * Method to get {@link #price} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #price} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getPrice(int decimals) {
        return roundValue(price, decimals);
    }

    /**
     * Method to get {@link #cost} instance <br>
     * No-any params required
     *
     * @return {@link #cost} instance as double
     **/
    public double getCost() {
        return cost;
    }

    /**
     * Method to get {@link #cost} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #cost} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getCost(int decimals) {
        return roundValue(cost, decimals);
    }

    /**
     * Method to get {@link #fee} instance <br>
     * No-any params required
     *
     * @return {@link #fee} instance as double
     **/
    public double getFee() {
        return fee;
    }

    /**
     * Method to get {@link #fee} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #fee} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getFee(int decimals) {
        return roundValue(fee, decimals);
    }

    /**
     * Method to get {@link #vol} instance <br>
     * No-any params required
     *
     * @return {@link #vol} instance as double
     **/
    public double getVol() {
        return vol;
    }

    /**
     * Method to get {@link #vol} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #vol} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getVol(int decimals) {
        return roundValue(fee, decimals);
    }

    /**
     * Method to get {@link #margin} instance <br>
     * No-any params required
     *
     * @return {@link #margin} instance as double
     **/
    public double getMargin() {
        return margin;
    }

    /**
     * Method to get {@link #margin} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #margin} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getMargin(int decimals) {
        return roundValue(margin, decimals);
    }

    /**
     * Method to get {@link #misc} instance <br>
     * No-any params required
     *
     * @return {@link #misc} instance as {@link String}
     **/
    public String getMisc() {
        return misc;
    }

    /**
     * {@code "TradeType"} list of types for trade
     **/
    public enum TradeType {

        /**
         * {@code "all"} trade type
         **/
        all("all"),

        /**
         * {@code "any_position"} trade type
         **/
        any_position("any position"),

        /**
         * {@code "closed_position"} trade type
         **/
        closed_position("closed position"),

        /**
         * {@code "closing_position"} trade type
         **/
        closing_position("closing position"),

        /**
         * {@code "no_position"} trade type
         **/
        no_position("no position");

        /**
         * {@code "type"} trade type
         **/
        private final String type;

        /**
         * Constructor to init a {@link TradeType}
         *
         * @param type: trade type value
         **/
        TradeType(String type) {
            this.type = type;
        }

        /**
         * Method to get {@link #type} instance <br>
         * No-any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        @Override
        public String toString() {
            return type;
        }

    }

}
