package com.tecknobit.krakenmanager.managers.privates.userfunding.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

import static java.lang.Long.parseLong;

/**
 * The {@code DepositAddress} class is useful to format a deposit address
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses">
 * Get Deposit Addresses</a>
 **/
public class DepositAddress extends KrakenManager.KrakenResponse {

    /**
     * {@code address} is instance that memorizes deposit address
     * **/
    private final String address;

    /**
     * {@code expireTime} is instance that memorizes expiration time in unix timestamp, or 0 if not expiring
     **/
    private final long expireTime;

    /**
     * {@code newAddress} is flag that memorizes whether address has ever been used
     **/
    private final boolean newAddress;

    /**
     * Constructor to init a {@link DepositAddress} object
     *
     * @param address:    deposit address
     * @param expireTime: expiration time in unix timestamp, or 0 if not expiring
     * @param newAddress: whether address has ever been used
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
        address = result.getString("address");
        expireTime = parseLong(result.getString("expiretm", "0"));
        newAddress = result.getBoolean("new");
    }

    /**
     * Method to get {@link #address} instance <br>
     * No-any params required
     *
     * @return {@link #address} instance as {@link String}
     **/
    public String getAddress() {
        return address;
    }

    /**
     * Method to get {@link #expireTime} instance <br>
     * No-any params required
     *
     * @return {@link #expireTime} instance as long
     **/
    public long getExpireTime() {
        return expireTime;
    }

    /**
     * Method to get {@link #newAddress} instance <br>
     * No-any params required
     *
     * @return {@link #newAddress} instance as boolean
     **/
    public boolean getNewAddress() {
        return newAddress;
    }

    /**
     * Returns a string representation of the object <br>
     * No-any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
