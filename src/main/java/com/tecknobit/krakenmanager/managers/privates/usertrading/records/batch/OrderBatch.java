package com.tecknobit.krakenmanager.managers.privates.usertrading.records.batch;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

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
     **/
    private final String txId;

    /**
     * {@code close} is instance that memorizes close value
     **/
    private final String close;

    /**
     * Constructor to init a {@link OrderBatch} object
     *
     * @param order: order value
     * @param txId:  transaction identifier for order
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
     * @param jsonResponse: base json response
     **/
    public OrderBatch(JSONObject jsonResponse) {
        super(jsonResponse);
        order = result.getString("order");
        txId = result.getString("txid");
        close = result.getString("close");
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
     * Method to get {@link #txId} instance <br>
     * Any params required
     *
     * @return {@link #txId} instance as {@link String}
     **/
    public String getTxId() {
        return txId;
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

}
