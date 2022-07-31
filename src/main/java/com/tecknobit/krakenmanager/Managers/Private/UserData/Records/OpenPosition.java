package com.tecknobit.krakenmanager.Managers.Private.UserData.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * The {@code OpenPosition} class is useful to format an open positions object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
 *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class OpenPosition extends KrakenManager.KrakenResponse {

    /**
     * {@code positionId} is instance that memorizes position identifier value
     * **/
    private final String positionId;

    /**
     * {@code orderTransactionId} is instance that memorizes order id responsible for the position
     * **/
    private final String orderTransactionId;

    /**
     * {@code postStatus} is instance that memorizes position status
     * **/
    private final String postStatus;

    /**
     * {@code pair} is instance that memorizes asset pair value
     * **/
    private final String pair;

    /**
     * {@code time} is instance that memorizes UNIX timestamp of trade
     * **/
    private final long time;

    /**
     * {@code type} is instance that memorizes direction (buy/sell) of position
     * **/
    private final String type;

    /**
     * {@code orderType} is instance that memorizes order type used to open position
     * **/
    private final String orderType;

    /**
     * {@code cost} is instance that memorizes opening cost of position (in quote currency)
     * **/
    private final double cost;

    /**
     * {@code fee} is instance that memorizes opening fee of position (in quote currency)
     * **/
    private final double fee;

    /**
     * {@code vol} is instance that memorizes position opening size (in base currency)
     * **/
    private final double vol;

    /**
     * {@code volClosed} is instance that memorizes quantity closed (in base currency)
     * **/
    private final double volClosed;

    /**
     * {@code margin} is instance that memorizes initial margin consumed (in quote currency)
     * **/
    private final double margin;

    /**
     * {@code value} is instance that memorizes current value of remaining position (if docalcs requested)
     * **/
    private final double value;

    /**
     * {@code net} is instance that memorizes unrealised P&L of remaining position (if docalcs requested)
     * **/
    private final double net;

    /**
     * {@code terms} is instance that memorizes funding cost and term of position
     * **/
    private final String terms;

    /**
     * {@code rolloverTerm} is instance that memorizes timestamp of next margin rollover fee
     * **/
    private final long rolloverTerm;

    /**
     * {@code misc} is instance that memorizes comma delimited list of add'l info
     * **/
    private final String misc;

    /**
     * {@code oFlags} is instance that memorizes comma delimited list of opening order flags
     * **/
    private final String oFlags;

    /** Constructor to init a {@link OpenPosition} object
     * @param jsonResponse : base json response
     * @param positionId: position identifier value
     * @param orderTransactionId: order id responsible for the position
     * @param postStatus: position status
     * @param pair: asset pair value
     * @param time: UNIX timestamp of trade
     * @param type: direction (buy/sell) of position
     * @param orderType: order type used to open position
     * @param cost: opening cost of position (in quote currency)
     * @param fee: opening fee of position (in quote currency)
     * @param vol: position opening size (in base currency)
     * @param volClosed: quantity closed (in base currency)
     * @param margin: initial margin consumed (in quote currency)
     * @param value: current value of remaining position (if docalcs requested)
     * @param net: unrealised P&L of remaining position (if docalcs requested)
     * @param terms: funding cost and term of position
     * @param rolloverTerm: timestamp of next margin rollover fee
     * @param misc: comma delimited list of add'l info
     * @param oFlags: comma delimited list of opening order flags
     **/
    public OpenPosition(JSONObject jsonResponse, String positionId, String orderTransactionId, String postStatus, String pair,
                        long time, String type, String orderType, double cost, double fee, double vol, double volClosed,
                        double margin, double value, double net, String terms, long rolloverTerm, String misc, String oFlags) {
        super(jsonResponse);
        this.positionId = positionId;
        this.orderTransactionId = orderTransactionId;
        this.postStatus = postStatus;
        this.pair = pair;
        this.time = time;
        this.type = type;
        this.orderType = orderType;
        this.cost = cost;
        this.fee = fee;
        this.vol = vol;
        this.volClosed = volClosed;
        this.margin = margin;
        this.value = value;
        this.net = net;
        this.terms = terms;
        this.rolloverTerm = rolloverTerm;
        this.misc = misc;
        this.oFlags = oFlags;
    }

    /** Constructor to init a {@link OpenPosition} object
     * @param positionId: position identifier value
     * @param orderTransactionId: order id responsible for the position
     * @param postStatus: position status
     * @param pair: asset pair value
     * @param time: UNIX timestamp of trade
     * @param type: direction (buy/sell) of position
     * @param orderType: order type used to open position
     * @param cost: opening cost of position (in quote currency)
     * @param fee: opening fee of position (in quote currency)
     * @param vol: position opening size (in base currency)
     * @param volClosed: quantity closed (in base currency)
     * @param margin: initial margin consumed (in quote currency)
     * @param value: current value of remaining position (if docalcs requested)
     * @param net: unrealised P&L of remaining position (if docalcs requested)
     * @param terms: funding cost and term of position
     * @param rolloverTerm: timestamp of next margin rollover fee
     * @param misc: comma delimited list of add'l info
     * @param oFlags: comma delimited list of opening order flags
     **/
    public OpenPosition(String positionId, String orderTransactionId, String postStatus, String pair, long time, String type,
                        String orderType, double cost, double fee, double vol, double volClosed, double margin, double value,
                        double net, String terms, long rolloverTerm, String misc, String oFlags) {
        super(null);
        this.positionId = positionId;
        this.orderTransactionId = orderTransactionId;
        this.postStatus = postStatus;
        this.pair = pair;
        this.time = time;
        this.type = type;
        this.orderType = orderType;
        this.cost = cost;
        this.fee = fee;
        this.vol = vol;
        this.volClosed = volClosed;
        this.margin = margin;
        this.value = value;
        this.net = net;
        this.terms = terms;
        this.rolloverTerm = rolloverTerm;
        this.misc = misc;
        this.oFlags = oFlags;
    }

    /** Constructor to init a {@link OpenPosition} object
     * @param jsonResponse : base json response
     * @param positionId: position identifier value
     **/
    public OpenPosition(JSONObject jsonResponse, String positionId) {
        super(jsonResponse);
        this.positionId = positionId;
        orderTransactionId = jsonResponse.getString("ordertxid");
        postStatus = jsonResponse.getString("posstatus");
        pair = jsonResponse.getString("pair");
        time = jsonResponse.getLong("time");
        type = jsonResponse.getString("type");
        orderType = jsonResponse.getString("ordertype");
        cost = jsonResponse.getDouble("cost");
        fee = jsonResponse.getDouble("fee");
        vol = jsonResponse.getDouble("vol");
        volClosed = jsonResponse.getDouble("vol_closed");
        margin = jsonResponse.getDouble("margin");
        value = jsonResponse.getDouble("value");
        net = jsonResponse.getDouble("net");
        terms = jsonResponse.getString("terms");
        rolloverTerm = jsonResponse.getLong("rollovertm");
        misc = jsonResponse.getString("misc");
        oFlags = jsonResponse.getString("oflags");
    }

    public String getPositionId() {
        return positionId;
    }

    public String getOrderTransactionId() {
        return orderTransactionId;
    }

    public String getPair() {
        return pair;
    }

    public long getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getOrderType() {
        return orderType;
    }

    public double getCost() {
        return cost;
    }

    public double getFee() {
        return fee;
    }

    public double getVol() {
        return vol;
    }

    public double getVolClosed() {
        return volClosed;
    }

    public double getMargin() {
        return margin;
    }

    public double getValue() {
        return value;
    }

    public double getNet() {
        return net;
    }

    public String getTerms() {
        return terms;
    }

    public long getRolloverTerm() {
        return rolloverTerm;
    }

    public String getMisc() {
        return misc;
    }

    public String getoFlags() {
        return oFlags;
    }

    @Override
    public String toString() {
        return "OpenPosition{" +
                "positionId='" + positionId + '\'' +
                ", orderTransactionId='" + orderTransactionId + '\'' +
                ", pair='" + pair + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", orderType='" + orderType + '\'' +
                ", cost=" + cost +
                ", fee=" + fee +
                ", vol=" + vol +
                ", volClosed=" + volClosed +
                ", margin=" + margin +
                ", value=" + value +
                ", net=" + net +
                ", terms='" + terms + '\'' +
                ", rolloverTerm=" + rolloverTerm +
                ", misc='" + misc + '\'' +
                ", oFlags='" + oFlags + '\'' +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
