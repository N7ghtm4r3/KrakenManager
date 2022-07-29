package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Order extends KrakenManager.KrakenResponse {

    public static final String BUY_TYPE = "buy";
    public static final String SELL_TYPE = "sell";
    public static final String MARKET_ORDER_TYPE = "market";
    public static final String LIMIT_ORDER_TYPE = "limit";
    public static final String STOP_LOSS_ORDER_TYPE = "stop-loss";
    public static final String TAKE_PROFIT_ORDER_TYPE = "take-profit";
    public static final String STOP_LOSS_LIMIT_ORDER_TYPE = "stop-loss-limit";
    public static final String TAKE_PROFIT_LIMIT_ORDER_TYPE = "take-profit-limit";
    public static final String SETTLE_POSITION_ORDER_TYPE = "settle-position";
    public static final String PENDING_STATUS = "pending";
    public static final String OPEN_STATUS = "open";
    public static final String CLOSES_STATUS = "closed";
    public static final String CANCELED_STATUS = "canceled";
    public static final String EXPIRED_STATUS = "expired";
    public static final String TRIGGER_LAST = "last";
    public static final String TRIGGER_INDEX = "index";
    public static final String MISC_STOPPED = "stopped";
    public static final String MISC_TOUCHED = "touched";
    public static final String MISC_LIQUIDATED = "liquidated";
    public static final String MISC_PARTIAL = "partial";
    public static final String OFLAG_POST = "post";
    public static final String OFLAG_FCIB = "fcib";
    public static final String OFLAG_FCIQ = "fciq";
    public static final String OFLAG_NOMPP = "nompp";
    public static final String OFLAG_VIQC = "viqc";

    protected final long refId;
    protected final long userRef;
    protected final String status;
    protected final long openTime;
    protected final long startTime;
    protected final long expireTime;
    protected final OrderDescription orderDescription;
    protected final double volume;
    protected final double executedVolume;
    protected final double cost;
    private final double fee;
    protected final double price;
    protected final double stopPrice;
    protected final double limitPrice;
    protected final String trigger;
    protected final String misc;
    protected final String oFlags;
    protected final ArrayList<Long> trades;

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

    /**
     * Constructor to init a {@link Order}
     * @param jsonResponse : base json response
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

    public double getExecutedVolume() {
        return executedVolume;
    }

    public double getCost() {
        return cost;
    }

    public double getFee() {
        return fee;
    }

    public double getStopPrice() {
        return stopPrice;
    }

    public double getLimitPrice() {
        return limitPrice;
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
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    public static class OrderDescription {

        private final String pair;
        private final String type;
        private final String orderType;
        private final double price;
        private final double secondPrice;
        private final String leverage;
        private final String order;
        private final String close;

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

        public OrderDescription(JSONObject jsonOrder){
            pair = jsonOrder.getString("pair");
            type = jsonOrder.getString("type");
            orderType = jsonOrder.getString("ordertype");
            price = jsonOrder.getDouble("price");
            secondPrice = jsonOrder.getDouble("price2");
            leverage = jsonOrder.getString("leverage");
            order = jsonOrder.getString("order");
            close = jsonOrder.getString("close");
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

        public double getSecondPrice() {
            return secondPrice;
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
