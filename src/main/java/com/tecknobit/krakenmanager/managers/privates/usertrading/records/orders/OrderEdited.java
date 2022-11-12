package com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order.OrderDescription;
import org.json.JSONObject;

import static com.tecknobit.apimanager.apis.APIRequest.Params;
import static com.tecknobit.apimanager.formatters.ScientificNotationParser.sNotationParse;
import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code OrderEdited} class is useful to assemble an order that has been edited
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
 * https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
 **/
public class OrderEdited extends KrakenManager.KrakenResponse{

    /**
     * {@code status} is instance that memorizes status value
     * **/
    private final String status;

    /**
     * {@code status} is instance that memorizes new order identifier value
     * **/
    private final String txId;

    /**
     * {@code status} is instance that memorizes original order identifier value
     * **/
    private final String originalTxId;

    /**
     * {@code volume} is instance that memorizes volume value
     * **/
    private final double volume;

    /**
     * {@code price} is instance that memorizes price value
     * **/
    private final double price;

    /**
     * {@code price2} is instance that memorizes secondary price value
     * **/
    private final double price2;

    /**
     * {@code ordersCancelled} is instance that memorizes orders cancelled value
     * **/
    private final int ordersCancelled;

    /**
     * {@code orderDescription} is instance that memorizes order description value
     * **/
    private final OrderDescription orderDescription;

    /** Constructor to init a {@link OrderEdited} object
     *
     * @param status: status value
     * @param txId: new order identifier value
     * @param originalTxId: original order identifier value
     * @param volume: volume value
     * @param price: price value
     * @param price2: secondary price value
     * @param ordersCancelled: orders cancelled value
     * @param orderDescription: order description value
     **/
    public OrderEdited(String status, String txId, String originalTxId, double volume, double price, double price2,
                       int ordersCancelled, OrderDescription orderDescription) {
        super(null);
        this.status = status;
        this.txId = txId;
        this.originalTxId = originalTxId;
        this.volume = volume;
        this.price = price;
        this.price2 = price2;
        this.ordersCancelled = ordersCancelled;
        this.orderDescription = orderDescription;
    }

    /**
     * Constructor to init a {@link OrderEdited} object
     * @param jsonResponse: base json response
     **/
    public OrderEdited(JSONObject jsonResponse) {
        super(jsonResponse);
        status = result.getString("status");
        txId = result.getString("txid");
        originalTxId = result.getString("originaltxid");
        volume = result.getDouble("volume", 0);
        price = result.getDouble("price", 0);
        price2 = result.getDouble("price2", 0);
        ordersCancelled = result.getInt("orders_cancelled", 0);
        orderDescription = new OrderDescription(result.getJSONObject("descr", new JSONObject()));
    }

    /**
     * Method to add base parameters for edit operation
     *
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair:    pair value
     * @param volume:  volume value
     * @param params:  extra order details
     * @implSpec key for orderId will automatically choose
     **/
    public static <T> void addBaseEditParameters(T orderId, String pair, double volume, Params params) {
        String idKey = "txid";
        if (orderId instanceof Number)
            idKey = "userref";
        if (params == null)
            params = new Params();
        params.addParam(idKey, orderId);
        params.addParam("pair", pair);
        params.addParam("volume", sNotationParse(8, volume));
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
     * Method to get {@link #txId} instance <br>
     * Any params required
     *
     * @return {@link #txId} instance as {@link String}
     **/
    public String getTxId() {
        return txId;
    }

    /**
     * Method to get {@link #originalTxId} instance <br>
     * Any params required
     *
     * @return {@link #originalTxId} instance as {@link String}
     **/
    public String getOriginalTxId() {
        return originalTxId;
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
     * Method to get {@link #volume} instance <br>
     * Any params required
     *
     * @return {@link #volume} instance as double
     **/
    public double getVolume() {
        return volume;
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
     * Method to get {@link #price} instance <br>
     * Any params required
     *
     * @return {@link #price} instance as double
     **/
    public double getPrice() {
        return price;
    }

    /**
     * Method to get {@link #price2} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #price2} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getPrice2(int decimals) {
        return roundValue(price2, decimals);
    }

    /**
     * Method to get {@link #price2} instance <br>
     * Any params required
     *
     * @return {@link #price2} instance as double
     **/
    public double getPrice2() {
        return price2;
    }

    /**
     * Method to get {@link #ordersCancelled} instance <br>
     * Any params required
     *
     * @return {@link #ordersCancelled} instance as int
     **/
    public int getOrdersCancelled() {
        return ordersCancelled;
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

}
