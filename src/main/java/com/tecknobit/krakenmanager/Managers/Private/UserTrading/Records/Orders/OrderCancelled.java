package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class OrderCancelled extends KrakenManager.KrakenResponse {

    protected final int count;

    public OrderCancelled(JSONObject jsonResponse, int count) {
        super(jsonResponse);
        this.count = count;
    }

    public OrderCancelled(int count) {
        super(null);
        this.count = count;
    }

    /**
     * Constructor to init a {@link OrderCancelled} object
     * @param jsonResponse : base json response
     **/
    public OrderCancelled(JSONObject jsonResponse) {
        super(jsonResponse);
        count = ((JSONObject) getResult()).getInt("count");
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "OrderCancelled{" +
                "count=" + count +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
