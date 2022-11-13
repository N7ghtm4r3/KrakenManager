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
     * {@code BUY_TYPE} is constant for buy side
     * **/
    public static final String BUY_TYPE = "buy";

    /**
     * {@code SELL_TYPE} is constant for sell side
     * **/
    public static final String SELL_TYPE = "sell";

    /**
     * {@code MARKET_ORDER_TYPE} is constant for market order type
     * **/
    public static final String MARKET_ORDER_TYPE = "market";

    /**
     * {@code LIMIT_ORDER_TYPE} is constant for limit order type
     * **/
    public static final String LIMIT_ORDER_TYPE = "limit";

    /**
     * {@code STOP_LOSS_ORDER_TYPE} is constant for stop loss order type
     * **/
    public static final String STOP_LOSS_ORDER_TYPE = "stop-loss";

    /**
     * {@code TAKE_PROFIT_ORDER_TYPE} is constant for take profit order type
     * **/
    public static final String TAKE_PROFIT_ORDER_TYPE = "take-profit";

    /**
     * {@code STOP_LOSS_LIMIT_ORDER_TYPE} is constant for stop loss limit order type
     * **/
    public static final String STOP_LOSS_LIMIT_ORDER_TYPE = "stop-loss-limit";

    /**
     * {@code TAKE_PROFIT_LIMIT_ORDER_TYPE} is constant for take profit limit order type
     * **/
    public static final String TAKE_PROFIT_LIMIT_ORDER_TYPE = "take-profit-limit";

    /**
     * {@code SETTLE_POSITION_ORDER_TYPE} is constant for settle position order type
     * **/
    public static final String SETTLE_POSITION_ORDER_TYPE = "settle-position";

    /**
     * {@code PENDING_STATUS} is constant for pending status
     * **/
    public static final String PENDING_STATUS = "pending";

    /**
     * {@code OPEN_STATUS} is constant for open status
     * **/
    public static final String OPEN_STATUS = "open";

    /**
     * {@code CLOSES_STATUS} is constant for closed status
     * **/
    public static final String CLOSES_STATUS = "closed";

    /**
     * {@code CANCELED_STATUS} is constant for canceled status
     * **/
    public static final String CANCELED_STATUS = "canceled";

    /**
     * {@code EXPIRED_STATUS} is constant for expired status
     * **/
    public static final String EXPIRED_STATUS = "expired";

    /**
     * {@code TRIGGER_LAST} is constant for last trigger type
     * **/
    public static final String TRIGGER_LAST = "last";

    /**
     * {@code TRIGGER_INDEX} is constant for index trigger type
     * **/
    public static final String TRIGGER_INDEX = "index";

    /**
     * {@code MISC_STOPPED} is constant for stopped misc type
     * **/
    public static final String MISC_STOPPED = "stopped";

    /**
     * {@code MISC_TOUCHED} is constant for touched misc type
     * **/
    public static final String MISC_TOUCHED = "touched";

    /**
     * {@code MISC_LIQUIDATED} is constant for liquidated misc type
     * **/
    public static final String MISC_LIQUIDATED = "liquidated";

    /**
     * {@code MISC_PARTIAL} is constant for partial misc type
     * **/
    public static final String MISC_PARTIAL = "partial";

    /**
     * {@code OFLAG_POST} is constant for post oflag type
     * **/
    public static final String OFLAG_POST = "post";

    /**
     * {@code OFLAG_FCIB} is constant for fcib oflag type
     * **/
    public static final String OFLAG_FCIB = "fcib";

    /**
     * {@code OFLAG_FCIQ} is constant for fciq oflag type
     * **/
    public static final String OFLAG_FCIQ = "fciq";

    /**
     * {@code OFLAG_NOMPP} is constant for nompp oflag type
     * **/
    public static final String OFLAG_NOMPP = "nompp";

    /**
     * {@code OFLAG_VIQC} is constant for nompp viqc type
     * **/
    public static final String OFLAG_VIQC = "viqc";

    /**
     * {@code refId} is instance that memorizes referral order transaction id
     * **/
    protected final long refId;

    /**
     * {@code userRef} is instance that memorizes user reference id
     * **/
    protected final long userRef;

    /**
     * {@code status} is instance that memorizes status value
     * **/
    protected final String status;

    /**
     * {@code openTime} is instance that memorizes open time value
     * **/
    protected final long openTime;

    /**
     * {@code startTime} is instance that memorizes start time value
     * **/
    protected final long startTime;

    /**
     * {@code expireTime} is instance that memorizes expire time value
     * **/
    protected final long expireTime;

    /**
     * {@code orderDescription} is instance that memorizes order description value
     * **/
    protected final OrderDescription orderDescription;

    /**
     * {@code volume} is instance that memorizes volume value
     * **/
    protected final double volume;

    /**
     * {@code executedVolume} is instance that memorizes executed volume value
     * **/
    protected final double executedVolume;

    /**
     * {@code cost} is instance that memorizes cost value
     * **/
    protected final double cost;

    /**
     * {@code fee} is instance that memorizes fee value
     * **/
    private final double fee;

    /**
     * {@code price} is instance that memorizes price value
     * **/
    protected final double price;

    /**
     * {@code stopPrice} is instance that memorizes stop price value
     * **/
    protected final double stopPrice;

    /**
     * {@code limitPrice} is instance that memorizes limit price value
     * **/
    protected final double limitPrice;

    /**
     * {@code trigger} is instance that memorizes trigger value
     * **/
    protected final String trigger;

    /**
     * {@code misc} is instance that memorizes misc value
     * **/
    protected final String misc;

    /**
     * {@code oFlags} is instance that memorizes order flags value
     * **/
    protected final String oFlags;

    /**
     * {@code trades} is instance that memorizes list of trades id
     * **/
    protected final ArrayList<Long> trades;

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
    public Order(long refId, long userRef, String status, long openTime, long startTime, long expireTime,
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
        status = result.getString("status");
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
     * Method to get {@link #status} instance <br>
     * Any params required
     *
     * @return {@link #status} instance as {@link String}
     **/
    public String getStatus() {
        return status;
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
        private final String orderType;

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
        public OrderDescription(String pair, String type, String orderType, double price, double secondPrice,
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
            orderType = orderDescription.getString("ordertype");
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
         * @return {@link #orderType} instance as {@link String}
         **/
        public String getOrderType() {
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
