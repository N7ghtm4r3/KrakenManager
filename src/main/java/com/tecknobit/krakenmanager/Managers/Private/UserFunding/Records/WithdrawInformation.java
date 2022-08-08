package com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class WithdrawInformation extends KrakenManager.KrakenResponse {

    private final String method;
    private final double limit;
    private final double amount;
    private final double fee;

    public WithdrawInformation(JSONObject jsonResponse, String method, double limit, double amount, double fee) {
        super(jsonResponse);
        this.method = method;
        this.limit = limit;
        this.amount = amount;
        this.fee = fee;
    }

    public WithdrawInformation(String method, double limit, double amount, double fee) {
        super(null);
        this.method = method;
        this.limit = limit;
        this.amount = amount;
        this.fee = fee;
    }

    /**
     * Constructor to init a {@link WithdrawInformation} object
     * @param jsonResponse : base json response
     **/
    public WithdrawInformation(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject withdraw = getResult();
        method = withdraw.getString("method");
        limit = withdraw.getDouble("limit");
        amount = withdraw.getDouble("amount");
        fee = withdraw.getDouble("fee");
    }

    public String getMethod() {
        return method;
    }

    public double getLimit() {
        return limit;
    }

    public double getAmount() {
        return amount;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return "WithdrawInformation{" +
                "method='" + method + '\'' +
                ", limit=" + limit +
                ", amount=" + amount +
                ", fee=" + fee +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
