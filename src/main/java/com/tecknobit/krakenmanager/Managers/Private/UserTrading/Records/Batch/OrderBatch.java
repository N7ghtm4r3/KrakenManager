package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Batch;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * The {@code OrderBatch} class is useful to format order batch object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
 *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class OrderBatch extends KrakenManager.KrakenResponse {

    /**
     * {@code order} is instance that memorizes order value
     * **/
    private final String order;

    /**
     * {@code txId} is instance that memorizes transaction identifier for order
     * **/
    private final String txId;

    /**
     * {@code close} is instance that memorizes close value
     * **/
    private final String close;

    /** Constructor to init a {@link OrderBatch} object
     * @param jsonResponse: base json response
     * @param order: order value
     * @param txId: transaction identifier for order
     * @param close: close value
     **/
    public OrderBatch(JSONObject jsonResponse, String order, String txId, String close) {
        super(jsonResponse);
        this.order = order;
        this.txId = txId;
        this.close = close;
    }

    /** Constructor to init a {@link OrderBatch} object
     * @param order: order value
     * @param txId: transaction identifier for order
     * @param close: close value
     **/
    public OrderBatch(String order, String txId, String close) {
        super(null);
        this.order = order;
        this.txId = txId;
        this.close = close;
    }

    /**
     * Constructor to init a {@link OrderBatch} object
     * @param jsonResponse : base json response
     **/
    public OrderBatch(JSONObject jsonResponse) {
        super(jsonResponse);
        JsonHelper batch = new JsonHelper(jsonResponse);
        order = batch.getString("order");
        txId = batch.getString("txid");
        close = batch.getString("close");
    }

    public String getOrder() {
        return order;
    }

    public String getTxId() {
        return txId;
    }

    public String getClose() {
        return close;
    }

    @Override
    public String toString() {
        return "OrderBatch{" +
                "order='" + order + '\'' +
                ", txId='" + txId + '\'' +
                ", close='" + close + '\'' +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
