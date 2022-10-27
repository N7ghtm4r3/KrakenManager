package com.tecknobit.krakenmanager.managers.privates.userFunding.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code WithdrawInformation} class is useful to format a deposit method
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation">
 * https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation</a>
 **/
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
        this(null, method, limit, amount, fee);
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

    /**
     * Method to get {@link #method} instance <br>
     * Any params required
     *
     * @return {@link #method} instance as {@link String}
     **/
    public String getMethod() {
        return method;
    }

    /**
     * Method to get {@link #limit} instance <br>
     * Any params required
     *
     * @return {@link #limit} instance as double
     **/
    public double getLimit() {
        return limit;
    }

    /**
     * Method to get {@link #limit} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #limit} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getLimit(int decimals) {
        return roundValue(limit, decimals);
    }

    /**
     * Method to get {@link #amount} instance <br>
     * Any params required
     *
     * @return {@link #amount} instance as double
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * Method to get {@link #amount} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #amount} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getAmount(int decimals) {
        return roundValue(amount, decimals);
    }

    /**
     * Method to get {@link #fee} instance <br>
     * Any params required
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
     * Returns a string representation of the object <br>
     * Any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
