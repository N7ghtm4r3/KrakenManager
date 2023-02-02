package com.tecknobit.krakenmanager.managers.privates.userdata.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code OpenPosition} class is useful to format an open positions object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
 * Get Open Positions</a>
 **/
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
     * {@code time} is instance that memorizes {@code "UNIX"} timestamp of trade
     **/
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
     * {@code net} is instance that memorizes unrealised {@code "{@code "P&L"}"} of remaining position (if docalcs requested)
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
     * @param positionId: position identifier value
     * @param orderTransactionId: order id responsible for the position
     * @param postStatus: position status
     * @param pair: asset pair value
     * @param time: {@code "UNIX"} timestamp of trade
     * @param type: direction (buy/sell) of position
     * @param orderType: order type used to open position
     * @param cost: opening cost of position (in quote currency)
     * @param fee: opening fee of position (in quote currency)
     * @param vol: position opening size (in base currency)
     * @param volClosed: quantity closed (in base currency)
     * @param margin: initial margin consumed (in quote currency)
     * @param value: current value of remaining position (if docalcs requested)
     * @param net: unrealised {@code "P&L"} of remaining position (if docalcs requested)
     * @param terms: funding cost and term of position
     * @param rolloverTerm: timestamp of next margin rollover fee
     * @param misc: comma delimited list of add'l info
     * @param oFlags: comma delimited list of opening order flags
     **/
    public OpenPosition(String positionId, String orderTransactionId, String postStatus, String pair, long time,
                        String type, String orderType, double cost, double fee, double vol, double volClosed, double margin,
                        double value, double net, String terms, long rolloverTerm, String misc, String oFlags) {
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
     * @param jsonResponse: base json response
     **/
    public OpenPosition(JSONObject jsonResponse) {
        super(jsonResponse);
        positionId = result.getString("positionId");
        orderTransactionId = result.getString("ordertxid");
        postStatus = result.getString("posstatus");
        pair = result.getString("pair");
        time = result.getLong("time", 0);
        type = result.getString("type");
        orderType = result.getString("ordertype");
        cost = result.getDouble("cost", 0);
        fee = result.getDouble("fee", 0);
        vol = result.getDouble("vol", 0);
        volClosed = result.getDouble("vol_closed", 0);
        margin = result.getDouble("margin", 0);
        value = result.getDouble("value", 0);
        net = result.getDouble("net", 0);
        terms = result.getString("terms");
        rolloverTerm = result.getLong("rollovertm", 0);
        misc = result.getString("misc");
        oFlags = result.getString("oflags");
    }

    /**
     * Method to get {@link #positionId} instance <br>
     * No-any params required
     *
     * @return {@link #positionId} instance as {@link String}
     **/
    public String getPositionId() {
        return positionId;
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
        return roundValue(vol, decimals);
    }

    /**
     * Method to get {@link #volClosed} instance <br>
     * No-any params required
     *
     * @return {@link #volClosed} instance as double
     **/
    public double getVolClosed() {
        return volClosed;
    }

    /**
     * Method to get {@link #volClosed} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #volClosed} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getVolClosed(int decimals) {
        return roundValue(volClosed, decimals);
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
     * Method to get {@link #value} instance <br>
     * No-any params required
     *
     * @return {@link #value} instance as double
     **/
    public double getValue() {
        return value;
    }

    /**
     * Method to get {@link #value} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #value} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getValue(int decimals) {
        return roundValue(value, decimals);
    }

    /**
     * Method to get {@link #net} instance <br>
     * No-any params required
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
     * Method to get {@link #terms} instance <br>
     * No-any params required
     *
     * @return {@link #terms} instance as {@link String}
     **/
    public String getTerms() {
        return terms;
    }

    /**
     * Method to get {@link #rolloverTerm} instance <br>
     * No-any params required
     *
     * @return {@link #rolloverTerm} instance as long
     **/
    public long getRolloverTerm() {
        return rolloverTerm;
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
     * Method to get {@link #oFlags} instance <br>
     * No-any params required
     *
     * @return {@link #oFlags} instance as {@link String}
     **/
    public String getoFlags() {
        return oFlags;
    }

    /**
     * Returns a string representation of the object <br>
     * No-any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
