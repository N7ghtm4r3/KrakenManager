package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order.OrderDescription;
import org.json.JSONObject;

import java.util.Arrays;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.apimanager.Tools.Formatters.ScientificNotationParser.sNotationParse;

/**
 * The {@code OrderEdited} class is useful to assemble an order that has been edited
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
 *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

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
     * @param jsonResponse: base json response
     * @param status: status value
     * @param txId: new order identifier value
     * @param originalTxId: original order identifier value
     * @param volume: volume value
     * @param price: price value
     * @param price2: secondary price value
     * @param ordersCancelled: orders cancelled value
     * @param orderDescription: order description value
     **/
    public OrderEdited(JSONObject jsonResponse, String status, String txId, String originalTxId, double volume,
                       double price, double price2, int ordersCancelled, OrderDescription orderDescription) {
        super(jsonResponse);
        this.status = status;
        this.txId = txId;
        this.originalTxId = originalTxId;
        this.volume = volume;
        this.price = price;
        this.price2 = price2;
        this.ordersCancelled = ordersCancelled;
        this.orderDescription = orderDescription;
    }

    /** Constructor to init a {@link OrderEdited} object
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
        JsonHelper orderEdited = new JsonHelper((JSONObject) getResult());
        status = orderEdited.getString("status");
        txId = orderEdited.getString("txid");
        originalTxId = orderEdited.getString("originaltxid");
        volume = orderEdited.getDouble("volume");
        price = orderEdited.getDouble("price");
        price2 = orderEdited.getDouble("price2");
        ordersCancelled = orderEdited.getInt("orders_cancelled");
        orderDescription = new OrderDescription(orderEdited.getJSONObject("descr"));
    }

    public String getStatus() {
        return status;
    }

    public String getTxId() {
        return txId;
    }

    public String getOriginalTxId() {
        return originalTxId;
    }

    public double getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public double getPrice2() {
        return price2;
    }

    public int getOrdersCancelled() {
        return ordersCancelled;
    }

    public OrderDescription getOrderDescription() {
        return orderDescription;
    }

    /** Method to add base parameters for edit operation
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: volume value
     * @param params: extra order details
     * @implSpec key for orderId will automatically choose
     * **/
    public static <T> void addBaseEditParameters(T orderId, String pair, double volume, Params params){
        String idKey = "txid";
        if(orderId instanceof Number)
            idKey = "userref";
        if(params == null)
            params = new Params();
        params.addParam(idKey, orderId);
        params.addParam("pair", pair);
        params.addParam("volume", sNotationParse(8, volume));
    }

    @Override
    public String toString() {
        return "OrderEdited{" +
                "status='" + status + '\'' +
                ", txId='" + txId + '\'' +
                ", originalTxId='" + originalTxId + '\'' +
                ", volume=" + volume +
                ", price=" + price +
                ", price2=" + price2 +
                ", ordersCancelled=" + ordersCancelled +
                ", orderDescription=" + orderDescription +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
