package com.tecknobit.krakenmanager.managers.privates.userFunding.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

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
     * {@code newAddress} is flag that memorizes whether address has ever been used
     **/
    private final boolean newAddress;

    /** Constructor to init a {@link DepositAddress} object
     * @param jsonResponse: base json response
     * @param address: deposit address
     * @param expireTime: expiration time in unix timestamp, or 0 if not expiring
     * @param newAddress: whether address has ever been used
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
     * @param newAddress: whether address has ever been used
     **/
    public DepositAddress(String address, long expireTime, boolean newAddress) {
        this(null, address, expireTime, newAddress);
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

    /**
     * Method to get {@link #address} instance <br>
     * Any params required
     *
     * @return {@link #address} instance as {@link String}
     **/
    public String getAddress() {
        return address;
    }

    /**
     * Method to get {@link #expireTime} instance <br>
     * Any params required
     *
     * @return {@link #expireTime} instance as long
     **/
    public long getExpireTime() {
        return expireTime;
    }

    /**
     * Method to get {@link #newAddress} instance <br>
     * Any params required
     *
     * @return {@link #newAddress} instance as boolean
     **/
    public boolean getNewAddress() {
        return newAddress;
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
