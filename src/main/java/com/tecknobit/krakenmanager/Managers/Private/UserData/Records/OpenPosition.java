package com.tecknobit.krakenmanager.Managers.Private.UserData.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class OpenPosition extends KrakenManager.KrakenResponse {

    private final String positionId;
    private final String orderTransactionId;
    private final String pair;
    private final long time;
    private final String type;
    private final String orderType;
    private final double cost;
    private final double fee;
    private final double vol;
    private final double volClosed;
    private final double margin;
    private final double value;
    private final double net;
    private final String terms;
    private final long rolloverTerm;
    private final String misc;
    private final String oFlags;

    public OpenPosition(JSONObject jsonResponse, String positionId, String orderTransactionId, String pair, long time,
                        String type, String orderType, double cost, double fee, double vol, double volClosed, double margin,
                        double value, double net, String terms, long rolloverTerm, String misc, String oFlags) {
        super(jsonResponse);
        this.positionId = positionId;
        this.orderTransactionId = orderTransactionId;
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

    public OpenPosition(String positionId, String orderTransactionId, String pair, long time, String type, String orderType,
                        double cost, double fee, double vol, double volClosed, double margin, double value, double net,
                        String terms, long rolloverTerm, String misc, String oFlags) {
        super(null);
        this.positionId = positionId;
        this.orderTransactionId = orderTransactionId;
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

    /**
     * Constructor to init a {@link OpenPosition}
     * @param jsonResponse : base json response
     **/
    public OpenPosition(JSONObject jsonResponse, String positionId) {
        super(jsonResponse);
        this.positionId = positionId;
        orderTransactionId = jsonResponse.getString("ordertxid");
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
