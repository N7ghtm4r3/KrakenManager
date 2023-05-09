package com.tecknobit.krakenmanager.privates.usersubaccounts.records;

import com.tecknobit.krakenmanager.KrakenManager.KrakenResponse;
import org.json.JSONObject;

/**
 * The {@code AccountTransfer} class is useful to format an account transfer result
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Subaccounts/operation/accountTransfer">
 * Account Transfer</a>
 * @see KrakenResponse
 **/
public class AccountTransfer extends KrakenResponse {

    /**
     * {@code transferId} transfer ID
     **/
    private final String transferId;

    /**
     * {@code status} transfer status
     **/
    private final String status;

    /**
     * Constructor to init a {@link AccountTransfer} object
     *
     * @param transferId: transfer ID
     * @param status:     transfer status
     **/
    public AccountTransfer(String transferId, String status) {
        super(null);
        this.transferId = transferId;
        this.status = status;
    }

    /**
     * Constructor to init a {@link AccountTransfer} object
     *
     * @param jAccountTransfer: account transfer details as {@link JSONObject}
     **/
    public AccountTransfer(JSONObject jAccountTransfer) {
        super(jAccountTransfer);
        transferId = hResponse.getString("transfer_id");
        status = hResponse.getString("status");
    }

    /**
     * Method to get {@link #transferId} instance <br>
     * No-any params required
     *
     * @return {@link #transferId} instance as {@link String}
     **/
    public String getTransferId() {
        return transferId;
    }

    /**
     * Method to get {@link #status} instance <br>
     * No-any params required
     *
     * @return {@link #status} instance as {@link String}
     **/
    public String getStatus() {
        return status;
    }

}

