package com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

import static java.lang.Long.parseLong;

/**
 * The {@code DepositAddress} class is useful to format a deposit address
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses">
 *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class DepositAddress extends KrakenManager.KrakenResponse {

    /**
     * {@code address} is instance that memorizes deposit address
     * **/
    private final String address;

    /**
     * {@code expireTime} is instance that memorizes expiration time in unix timestamp, or 0 if not expiring
     * **/
    private final long expireTime;

    /**
     * {@code newAddress} is flag that memorizes whether or not address has ever been used
     * **/
    private final boolean newAddress;

    /** Constructor to init a {@link DepositAddress} object
     * @param jsonResponse: base json response
     * @param address: deposit address
     * @param expireTime: expiration time in unix timestamp, or 0 if not expiring
     * @param newAddress: whether or not address has ever been used
     **/
    public DepositAddress(JSONObject jsonResponse, String address, long expireTime, boolean newAddress) {
        super(jsonResponse);
        this.address = address;
        this.expireTime = expireTime;
        this.newAddress = newAddress;
    }

    /** Constructor to init a {@link DepositAddress} object
     * @param address: deposit address
     * @param expireTime: expiration time in unix timestamp, or 0 if not expiring
     * @param newAddress: whether or not address has ever been used
     **/
    public DepositAddress(String address, long expireTime, boolean newAddress) {
        super(null);
        this.address = address;
        this.expireTime = expireTime;
        this.newAddress = newAddress;
    }

    /**
     * Constructor to init a {@link DepositAddress} object
     * @param jsonResponse: base json response
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
