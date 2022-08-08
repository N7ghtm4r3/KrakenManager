package com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class DepositMethod extends KrakenManager.KrakenResponse {

    private final String method;
    private final double fee;
    private final String addressSetupFee;
    private final boolean genAddress;

    public DepositMethod(JSONObject jsonResponse, String method, double fee, String addressSetupFee, boolean genAddress) {
        super(jsonResponse);
        this.method = method;
        this.fee = fee;
        this.addressSetupFee = addressSetupFee;
        this.genAddress = genAddress;
    }

    public DepositMethod(String method, double fee, String addressSetupFee, boolean genAddress) {
        super(null);
        this.method = method;
        this.fee = fee;
        this.addressSetupFee = addressSetupFee;
        this.genAddress = genAddress;
    }

    /**
     * Constructor to init a {@link DepositMethod} object
     * @param jsonResponse : base json response
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
