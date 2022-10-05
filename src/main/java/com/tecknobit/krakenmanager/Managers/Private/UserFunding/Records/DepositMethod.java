package com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

import static com.tecknobit.apimanager.Tools.Trading.TradingTools.roundValue;

/**
 * The {@code DepositMethod} class is useful to format a deposit method
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods">
 * https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods</a>
 **/

public class DepositMethod extends KrakenManager.KrakenResponse {

    /**
     * {@code method} is instance that memorizes name of deposit method
     * **/
    private final String method;

    /**
     * {@code fee} is instance that memorizes amount of fees that will be paid
     * **/
    private final double fee;

    /**
     * {@code addressSetupFee} is instance that memorizes whether method has an address setup fee
     **/
    private final String addressSetupFee;

    /**
     * {@code genAddress} is flag that memorizes whether new addresses can be generated for this method
     * **/
    private final boolean genAddress;

    /** Constructor to init a {@link DepositMethod} object
     * @param jsonResponse: base json response
     * @param method: name of deposit method
     * @param fee: amount of fees that will be paid
     * @param addressSetupFee: whether method has an address setup fee
     * @param genAddress: whether new addresses can be generated for this method
     **/
    public DepositMethod(JSONObject jsonResponse, String method, double fee, String addressSetupFee, boolean genAddress) {
        super(jsonResponse);
        this.method = method;
        this.fee = fee;
        this.addressSetupFee = addressSetupFee;
        this.genAddress = genAddress;
    }

    /** Constructor to init a {@link DepositMethod} object
     * @param method: name of deposit method
     * @param fee: amount of fees that will be paid
     * @param addressSetupFee: whether method has an address setup fee
     * @param genAddress: whether new addresses can be generated for this method
     **/
    public DepositMethod(String method, double fee, String addressSetupFee, boolean genAddress) {
        this(null, method, fee, addressSetupFee, genAddress);
    }

    /**
     * Constructor to init a {@link DepositMethod} object
     * @param jsonResponse: base json response
     **/
    public DepositMethod(JSONObject jsonResponse) {
        super(jsonResponse);
        method = jsonResponse.getString("method");
        fee = jsonResponse.getDouble("fee");
        addressSetupFee = jsonResponse.getString("address-setup-fee");
        genAddress = jsonResponse.getBoolean("gen-address");
    }

    public String getMethod() {
        return method;
    }

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

    public String getAddressSetupFee() {
        return addressSetupFee;
    }

    public boolean getGenAddress() {
        return genAddress;
    }

    @Override
    public String toString() {
        return "DepositMethod{" +
                "method='" + method + '\'' +
                ", fee=" + fee +
                ", addressSetupFee='" + addressSetupFee + '\'' +
                ", genAddress=" + genAddress +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
