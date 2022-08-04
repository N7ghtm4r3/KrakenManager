package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Batch;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class OrderBatch extends KrakenManager.KrakenResponse {

    private final String order;
    private final String txId;
    private final String close;

    public OrderBatch(JSONObject jsonResponse, String order, String txId, String close) {
        super(jsonResponse);
        this.order = order;
        this.txId = txId;
        this.close = close;
    }

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
