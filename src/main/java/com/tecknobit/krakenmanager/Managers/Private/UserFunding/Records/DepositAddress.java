package com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

import static java.lang.Long.parseLong;

public class DepositAddress extends KrakenManager.KrakenResponse {

    private final String address;
    private final long expireTime;
    private final boolean newAddress;

    public DepositAddress(JSONObject jsonResponse, String address, long expireTime, boolean newAddress) {
        super(jsonResponse);
        this.address = address;
        this.expireTime = expireTime;
        this.newAddress = newAddress;
    }

    public DepositAddress(String address, long expireTime, boolean newAddress) {
        super(null);
        this.address = address;
        this.expireTime = expireTime;
        this.newAddress = newAddress;
    }

    /**
     * Constructor to init a {@link DepositAddress} object
     * @param jsonResponse : base json response
     **/
    public DepositAddress(JSONObject jsonResponse) {
        super(jsonResponse);
        JsonHelper jsonAddress = new JsonHelper(jsonResponse);
        address = jsonAddress.getString("address");
        expireTime = parseLong(jsonAddress.getString("expiretm"));
        newAddress = jsonAddress.getBoolean("new");
    }

    public String getAddress() {
        return address;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public boolean getNewAddress() {
        return newAddress;
    }

    @Override
    public String toString() {
        return "DepositAddress{" +
                "address='" + address + '\'' +
                ", expireTime=" + expireTime +
                ", newAddress=" + newAddress +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
