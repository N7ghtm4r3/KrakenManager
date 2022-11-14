package com.tecknobit.krakenmanager.managers.privates.userdata.records.orders;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Order} class is useful to format order object
 * @apiNote see the official documentation at:
 * <ul>
 *   <li>
 *       <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
 *           Get Open Orders</a>
 *   </li>
 *   <li>
 *        <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
 *           Get Closed Orders</a>
 *    </li>
 * </ul>
 * @author N7ghtm4r3 - Tecknobit
 * **/
public class Order extends KrakenManager.KrakenResponse {

    /**
     * {@code status} is instance that memorizes status value
     **/
    protected final OrderStatus status;

    /** Constructor to init a {@link Order} object
     * @param refId: referral order transaction id
     * @param userRef: user reference id
     * @param status: status value
     * @param openTime: open time value
     * @param startTime: start time value
     * @param expireTime: expire time value
     * @param orderDescription: order description value
     * @param volume: volume value
     * @param executedVolume: executed volume value
     * @param cost: cost value
     * @param fee: fee value
     * @param price: price value
     * @param stopPrice: stop price value
     * @param limitPrice: limit price value
     * @param trigger: trigger value
     * @param misc: misc value
     * @param oFlags: order flags value
     * @param trades: list of trades id
     **/
    public Order(long refId, long userRef, OrderStatus status, long openTime, long startTime, long expireTime,
                 OrderDescription orderDescription, double volume, double executedVolume, double cost, double fee,
                 double price, double stopPrice, double limitPrice, String trigger, String misc, String oFlags,
                 ArrayList<Long> trades) {
        super(null);
        this.refId = refId;
        this.userRef = userRef;
        this.status = status;
        this.openTime = openTime;
        this.startTime = startTime;
        this.expireTime = expireTime;
        this.orderDescription = orderDescription;
        this.volume = volume;
        this.executedVolume = executedVolume;
        this.cost = cost;
        this.fee = fee;
        this.price = price;
        this.stopPrice = stopPrice;
        this.limitPrice = limitPrice;
        this.trigger = trigger;
        this.misc = misc;
        this.oFlags = oFlags;
        this.trades = trades;
    }

    /** Constructor to init a {@link Order}
     * @param jsonResponse: base json response
     **/
    public Order(JSONObject jsonResponse) {
        super(jsonResponse);
        refId = result.getLong("refid", 0);
        userRef = result.getLong("userref", 0);
        status = OrderStatus.valueOf(result.getString("status", OrderStatus.open.toString()));
        openTime = result.getLong("opentm", 0);
        startTime = result.getLong("starttm", 0);
        expireTime = result.getLong("expiretm", 0);
        orderDescription = new OrderDescription(result.getJSONObject("descr"));
        volume = result.getDouble("vol", 0);
        executedVolume = result.getDouble("vol_exec", 0);
        cost = result.getDouble("cost", 0);
        fee = result.getDouble("fee", 0);
        price = result.getDouble("price", 0);
        stopPrice = result.getDouble("stopprice", 0);
        limitPrice = result.getDouble("limitprice", 0);
        trigger = result.getString("trigger");
        misc = result.getString("misc");
        oFlags = result.getString("oflags");
        JSONArray jsonTrades = JsonHelper.getJSONArray(result.getJSONObjectSource(), "trades", new JSONArray());
        trades = new ArrayList<>();
        for (int j = 0; j < jsonTrades.length(); j++)
            trades.add(jsonTrades.getLong(j));
    }

    /**
     * Method to get {@link #status} instance <br>
     * Any params required
     *
     * @return {@link #status} instance as {@link String}
     **/
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * {@code Side} sides for an order
     **/
    public enum Side {

        /**
         * {@code "buy"} side
         **/
        buy,

        /**
         * {@code "sell"} side
         **/
        sell

    }

    /**
     * {@code OrderType} list of order types
     **/
    public enum OrderType {

        /**
         * {@code "market"} order type
         **/
        market("market"),

        /**
         * {@code "limit"} order type
         **/
        limit("limit"),

        /**
         * {@code "stop_loss"} order type
         **/
        stop_loss("stop-loss"),

        /**
         * {@code "take_profit"} order type
         **/
        take_profit("take-profit"),

        /**
         * {@code "stop_loss_limit"} order type
         **/
        stop_loss_limit("stop-loss-limit"),

        /**
         * {@code "take_profit_limit"} order type
         **/
        take_profit_limit("take-profit-limit"),

        /**
         * {@code "settle_position"} order type
         **/
        settle_position("settle-position");

        /**
         * {@code type} type of the order
         **/
        private final String type;

        /**
         * Constructor to init a {@link OrderType} object
         *
         * @param type: type of the order
         **/
        OrderType(String type) {
            this.type = type;
        }

        /**
         * Method to get {@link #type} instance <br>
         * Any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        @Override
        public String toString() {
            return type;
        }

    }

    /**
     * {@code refId} is instance that memorizes referral order transaction id
     **/
    protected final long refId;

    /**
     * {@code userRef} is instance that memorizes user reference id
     **/
    protected final long userRef;

    /**
     * {@code OrderStatus} statuses list for an order
     **/
    public enum OrderStatus {

        /**
         * {@code "pending"} status
         **/
        pending,

        /**
         * {@code "open"} status
         **/
        open,

        /**
         * {@code "closed"} status
         **/
        closed,

        /**
         * {@code "canceled"} status
         **/
        canceled,

        /**
         * {@code "expired"} status
         **/
        expired,

    }

    /**
     * {@code openTime} is instance that memorizes open time value
     **/
    protected final long openTime;

    /**
     * {@code startTime} is instance that memorizes start time value
     **/
    protected final long startTime;

    /**
     * {@code expireTime} is instance that memorizes expire time value
     **/
    protected final long expireTime;

    /**
     * {@code orderDescription} is instance that memorizes order description value
     **/
    protected final OrderDescription orderDescription;

    /**
     * {@code volume} is instance that memorizes volume value
     **/
    protected final double volume;

    /**
     * {@code executedVolume} is instance that memorizes executed volume value
     **/
    protected final double executedVolume;

    /**
     * {@code cost} is instance that memorizes cost value
     **/
    protected final double cost;

    /**
     * {@code fee} is instance that memorizes fee value
     **/
    private final double fee;

    /**
     * {@code price} is instance that memorizes price value
     **/
    protected final double price;

    /**
     * {@code stopPrice} is instance that memorizes stop price value
     **/
    protected final double stopPrice;

    /**
     * {@code limitPrice} is instance that memorizes limit price value
     **/
    protected final double limitPrice;

    /**
     * {@code trigger} is instance that memorizes trigger value
     **/
    protected final String trigger;

    /**
     * {@code misc} is instance that memorizes misc value
     **/
    protected final String misc;

    /**
     * {@code oFlags} is instance that memorizes order flags value
     **/
    protected final String oFlags;

    /**
     * {@code trades} is instance that memorizes list of trades id
     **/
    protected final ArrayList<Long> trades;

    /**
     * {@code Trigger} triggers list for an order
     **/
    public enum Trigger {

        /**
         * {@code "last"} trigger
         **/
        last,

        /**
         * {@code "index"} trigger
         **/
        index

    }

    /**
     * {@code Misc} misc list for an order
     **/
    public enum Misc {

        /**
         * {@code "stopped"} misc
         **/
        stopped,

        /**
         * {@code "touched"} trigger
         **/
        touched,

        /**
         * {@code "liquidated"} misc
         **/
        liquidated,

        /**
         * {@code "partial"} misc
         **/
        partial

    }

    /**
     * Method to get {@link #refId} instance <br>
     * Any params required
     *
     * @return {@link #refId} instance as long
     **/
    public long getRefId() {
        return refId;
    }

    /**
     * Method to get {@link #userRef} instance <br>
     * Any params required
     *
     * @return {@link #userRef} instance as long
     **/
    public long getUserRef() {
        return userRef;
    }

    /**
     * {@code OFlag} oflags list for an order
     * **/
    public enum OFlag {

        /**
         * {@code "post"} post-only order (available when ordertype = limit)
         **/
        post,

        /**
         * {@code "fcib"} prefer fee in base currency (default if selling)
         **/
        fcib,

        /**
         * {@code "fciq"} prefer fee in quote currency (default if buying, mutually exclusive with {@code "fcib"})
         **/
        fciq,

        /**
         * {@code "nompp"} disable market price protection for market orders
         **/
        nompp,

        /**
         * {@code "viqc"} order volume expressed in quote currency. This is supported only for market orders
         **/
        viqc

    }

    /**
     * Method to get {@link #openTime} instance <br>
     * Any params required
     *
     * @return {@link #openTime} instance as long
     **/
    public long getOpenTime() {
        return openTime;
    }

    /**
     * Method to get {@link #startTime} instance <br>
     * Any params required
     *
     * @return {@link #startTime} instance as long
     **/
    public long getStartTime() {
        return startTime;
    }

    /**
     * Method to get {@link #expireTime} instance <br>
     * Any params required
     *
     * @return {@link #expireTime} instance as long
     **/
    public long getExpireTime() {
        return expireTime;
    }

    /**
     * Method to get {@link #orderDescription} instance <br>
     * Any params required
     *
     * @return {@link #orderDescription} instance as {@link OrderDescription}
     **/
    public OrderDescription getOrderDescription() {
        return orderDescription;
    }

    /**
     * Method to get {@link #volume} instance <br>
     * Any params required
     *
     * @return {@link #volume} instance as double
     **/
    public double getVolume() {
        return volume;
    }

    /**
     * Method to get {@link #volume} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #volume} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getVolume(int decimals) {
        return roundValue(volume, decimals);
    }

    /**
     * Method to get {@link #executedVolume} instance <br>
     * Any params required
     *
     * @return {@link #executedVolume} instance as double
     **/
    public double getExecutedVolume() {
        return executedVolume;
    }

    /**
     * Method to get {@link #executedVolume} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #executedVolume} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getExecutedVolume(int decimals) {
        return roundValue(executedVolume, decimals);
    }

    /**
     * Method to get {@link #cost} instance <br>
     * Any params required
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
     * Any params required
     *
     * @return {@link #fee} instance as double
     **/
    public double getFee() {
        return fee;
    }

    /**
     * Method to get {@link #fee} instance <br>
     * Any params required
     *
     * @return {@link #fee} instance as double
     **/
    public double getFee(int decimals) {
        return roundValue(fee, decimals);
    }

    /**
     * Method to get {@link #stopPrice} instance <br>
     * Any params required
     *
     * @return {@link #stopPrice} instance as double
     **/
    public double getStopPrice() {
        return stopPrice;
    }

    /**
     * Method to get {@link #stopPrice} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #stopPrice} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getStopPrice(int decimals) {
        return roundValue(stopPrice, decimals);
    }

    /**
     * Method to get {@link #limitPrice} instance <br>
     * Any params required
     *
     * @return {@link #limitPrice} instance as double
     **/
    public double getLimitPrice() {
        return limitPrice;
    }

    /**
     * Method to get {@link #limitPrice} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #limitPrice} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getLimitPrice(int decimals) {
        return roundValue(limitPrice, decimals);
    }

    /**
     * Method to get {@link #trigger} instance <br>
     * Any params required
     *
     * @return {@link #trigger} instance as {@link String}
     **/
    public String getTrigger() {
        return trigger;
    }

    /**
     * Method to get {@link #misc} instance <br>
     * Any params required
     *
     * @return {@link #misc} instance as {@link String}
     **/
    public String getMisc() {
        return misc;
    }

    /**
     * Method to get {@link #oFlags} instance <br>
     * Any params required
     *
     * @return {@link #oFlags} instance as {@link String}
     **/
    public String getoFlags() {
        return oFlags;
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
     * The {@code OrderDescription} class is useful to give description of an {@link Order}
     * with a custom object
     **/
    public static class OrderDescription {

        /**
         * {@code pair} is instance that memorizes pair value
         * **/
        private final String pair;

        /**
         * {@code type} is instance that memorizes type value
         * **/
        private final String type;

        /**
         * {@code orderType} is instance that memorizes order type value
         * **/
        private final OrderType orderType;

        /**
         * {@code price} is instance that memorizes price value
         * **/
        private final double price;

        /**
         * {@code secondPrice} is instance that memorizes second price value
         * **/
        private final double secondPrice;

        /**
         * {@code leverage} is instance that memorizes leverage value
         * **/
        private final String leverage;

        /**
         * {@code order} is instance that memorizes order value
         * **/
        private final String order;

        /**
         * {@code order} is instance that memorizes close value
         * **/
        private final String close;

        /**
         * Constructor to init a {@link OrderDescription} object
         * @param pair: pair value
         * @param type: type value
         * @param orderType: order type value
         * @param price: price value
         * @param secondPrice: second price value
         * @param leverage: leverage value
         * @param order: order value
         * @param close: close value
         **/
        public OrderDescription(String pair, String type, OrderType orderType, double price, double secondPrice,
                                String leverage, String order, String close) {
            this.pair = pair;
            this.type = type;
            this.orderType = orderType;
            this.price = price;
            this.secondPrice = secondPrice;
            this.leverage = leverage;
            this.order = order;
            this.close = close;
        }

        /**
         * Constructor to init a {@link OrderDescription} object
         *
         * @param jsonOrder: order description details in {@code "JSON"} format
         **/
        public OrderDescription(JSONObject jsonOrder){
            JsonHelper orderDescription = new JsonHelper(jsonOrder);
            pair = orderDescription.getString("pair");
            type = orderDescription.getString("type");
            orderType = OrderType.valueOf(orderDescription.getString("ordertype", OrderType.market.toString()));
            price = orderDescription.getDouble("price");
            secondPrice = orderDescription.getDouble("price2");
            leverage = orderDescription.getString("leverage");
            order = orderDescription.getString("order");
            close = orderDescription.getString("close");
        }

        /**
         * Method to get {@link #pair} instance <br>
         * Any params required
         *
         * @return {@link #pair} instance as {@link String}
         **/
        public String getPair() {
            return pair;
        }

        /**
         * Method to get {@link #type} instance <br>
         * Any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        public String getType() {
            return type;
        }

        /**
         * Method to get {@link #orderType} instance <br>
         * Any params required
         *
         * @return {@link #orderType} instance as {@link OrderType}
         **/
        public OrderType getOrderType() {
            return orderType;
        }

        /**
         * Method to get {@link #price} instance <br>
         * Any params required
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
         * Method to get {@link #secondPrice} instance <br>
         * Any params required
         *
         * @return {@link #secondPrice} instance as double
         **/
        public double getSecondPrice() {
            return secondPrice;
        }

        /**
         * Method to get {@link #secondPrice} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #secondPrice} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getSecondPrice(int decimals) {
            return roundValue(secondPrice, decimals);
        }

        /**
         * Method to get {@link #leverage} instance <br>
         * Any params required
         *
         * @return {@link #leverage} instance as {@link String}
         **/
        public String getLeverage() {
            return leverage;
        }

        /**
         * Method to get {@link #order} instance <br>
         * Any params required
         *
         * @return {@link #order} instance as {@link String}
         **/
        public String getOrder() {
            return order;
        }

        /**
         * Method to get {@link #close} instance <br>
         * Any params required
         *
         * @return {@link #close} instance as {@link String}
         **/
        public String getClose() {
            return close;
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

}
