package com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * The {@code WithdrawInformation} class is useful to format a deposit method
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation">
 *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class WithdrawInformation extends KrakenManager.KrakenResponse {

    /**
     * {@code method} is instance that memorizes name of the withdrawal method that will be used
     * **/
    private final String method;

    /**
     * {@code limit} is instance that memorizes maximum net amount that can be withdrawn right now
     * **/
    private final double limit;

    /**
     * {@code amount} is instance that memorizes net amount that will be sent, after fees
     * **/
    private final double amount;

    /**
     * {@code fee} is instance that memorizes amount of fees that will be paid
     * **/
    private final double fee;

    /** Constructor to init a {@link WithdrawInformation} object
     * @param jsonResponse: base json response
     * @param method: name of the withdrawal method that will be used
     * @param limit: maximum net amount that can be withdrawn right now
     * @param amount: net amount that will be sent, after fees
     * @param fee: amount of fees that will be paid
     **/
    public WithdrawInformation(JSONObject jsonResponse, String method, double limit, double amount, double fee) {
        super(jsonResponse);
        this.method = method;
        this.limit = limit;
        this.amount = amount;
        this.fee = fee;
    }

    /** Constructor to init a {@link WithdrawInformation} object
     * @param method: name of the withdrawal method that will be used
     * @param limit: maximum net amount that can be withdrawn right now
     * @param amount: net amount that will be sent, after fees
     * @param fee: amount of fees that will be paid
     **/
    public WithdrawInformation(String method, double limit, double amount, double fee) {
        super(null);
        this.method = method;
        this.limit = limit;
        this.amount = amount;
        this.fee = fee;
    }

    /**
     * Constructor to init a {@link WithdrawInformation} object
     * @param jsonResponse: base json response
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
