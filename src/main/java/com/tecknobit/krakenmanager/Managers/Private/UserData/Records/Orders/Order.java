package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tecknobit.apimanager.Tools.Trading.TradingTools.roundValue;

/**
 * The {@code Order} class is useful to format order object
 * @apiNote see official documentation at:
<ul>
<li>
<a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
</li>
<li>
         <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
            https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo</a>
     </li>
 </ul>
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
     * @param jsonResponse: base json response
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
    public Order(JSONObject jsonResponse, long refId, long userRef, String status, long openTime, long startTime,
                 long expireTime, OrderDescription orderDescription, double volume, double executedVolume, double cost,
                 double fee, double price, double stopPrice, double limitPrice, String trigger, String misc, String oFlags,
                 ArrayList<Long> trades) {
        super(jsonResponse);
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
        this(null, refId, userRef, status, openTime, startTime, expireTime, orderDescription, volume, executedVolume,
                cost, fee, price, stopPrice, limitPrice, trigger, misc, oFlags, trades);
    }

    /** Constructor to init a {@link Order}
     * @param jsonResponse: base json response
     **/
    public Order(JSONObject jsonResponse) {
        super(jsonResponse);
        refId = jsonResponse.getLong("refid");
        userRef = jsonResponse.getLong("userref");
        status = jsonResponse.getString("status");
        openTime = jsonResponse.getLong("opentm");
        startTime = jsonResponse.getLong("starttm");
        expireTime = jsonResponse.getLong("expiretm");
        orderDescription = new OrderDescription(jsonResponse.getJSONObject("descr"));
        volume = jsonResponse.getDouble("vol");
        executedVolume = jsonResponse.getDouble("vol_exec");
        cost = jsonResponse.getDouble("cost");
        fee = jsonResponse.getDouble("fee");
        price = jsonResponse.getDouble("price");
        stopPrice = jsonResponse.getDouble("stopprice");
        limitPrice = jsonResponse.getDouble("limitprice");
        trigger = jsonResponse.getString("trigger");
        misc = jsonResponse.getString("misc");
        oFlags = jsonResponse.getString("oflags");
        JSONArray jsonTrades = JsonHelper.getJSONArray(jsonResponse, "trades", new JSONArray());
        trades = new ArrayList<>();
        for (int j = 0; j < jsonTrades.length(); j++)
            trades.add(jsonTrades.getLong(j));
    }

    public long getRefId() {
        return refId;
    }

    public long getUserRef() {
        return userRef;
    }

    public String getStatus() {
        return status;
    }

    public long getOpenTime() {
        return openTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public OrderDescription getOrderDescription() {
        return orderDescription;
    }

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

    public String getTrigger() {
        return trigger;
    }

    public String getMisc() {
        return misc;
    }

    public String getoFlags() {
        return oFlags;
    }

    public ArrayList<Long> getTrades() {
        return trades;
    }

    @Override
    public String toString() {
        return "Order{" +
                "refId='" + refId + '\'' +
                ", userRef='" + userRef + '\'' +
                ", status='" + status + '\'' +
                ", openTime=" + openTime +
                ", startTime=" + startTime +
                ", expireTime=" + expireTime +
                ", orderDescription=" + orderDescription +
                ", volume=" + volume +
                ", executedVolume=" + executedVolume +
                ", cost=" + cost +
                ", fee=" + fee +
                ", stopPrice=" + stopPrice +
                ", limitPrice=" + limitPrice +
                ", trigger='" + trigger + '\'' +
                ", misc='" + misc + '\'' +
                ", oFlags='" + oFlags + '\'' +
                ", trades=" + trades +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    /**
     * The {@code OrderDescription} class is useful to give description of an {@link Order}
     * with a custom object
     * **/

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
         * @param jsonOrder: order description details in JSON format
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

        public String getPair() {
            return pair;
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

        public String getLeverage() {
            return leverage;
        }

        public String getOrder() {
            return order;
        }

        public String getClose() {
            return close;
        }

        @Override
        public String toString() {
            return "OrderDescription{" +
                    "pair='" + pair + '\'' +
                    ", type='" + type + '\'' +
                    ", orderType='" + orderType + '\'' +
                    ", price=" + price +
                    ", secondPrice=" + secondPrice +
                    ", leverage='" + leverage + '\'' +
                    ", order='" + order + '\'' +
                    ", close='" + close + '\'' +
                    '}';
        }

    }

}
