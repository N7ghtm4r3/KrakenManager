package com.tecknobit.krakenmanager.managers.privates.userfunding.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

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
     **/
    private final boolean genAddress;

    /**
     * Constructor to init a {@link DepositMethod} object
     *
     * @param method:          name of deposit method
     * @param fee:             amount of fees that will be paid
     * @param addressSetupFee: whether method has an address setup fee
     * @param genAddress:      whether new addresses can be generated for this method
     **/
    public DepositMethod(String method, double fee, String addressSetupFee, boolean genAddress) {
        super(null);
        this.method = method;
        this.fee = fee;
        this.addressSetupFee = addressSetupFee;
        this.genAddress = genAddress;
    }

    /**
     * Constructor to init a {@link DepositMethod} object
     * @param jsonResponse: base json response
     **/
    public DepositMethod(JSONObject jsonResponse) {
        super(jsonResponse);
        method = result.getString("method");
        fee = result.getDouble("fee", 0);
        addressSetupFee = result.getString("address-setup-fee");
        genAddress = result.getBoolean("gen-address");
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
     * Method to get {@link #addressSetupFee} instance <br>
     * Any params required
     *
     * @return {@link #addressSetupFee} instance as {@link String}
     **/
    public String getAddressSetupFee() {
        return addressSetupFee;
    }

    /**
     * Method to get {@link #genAddress} instance <br>
     * Any params required
     *
     * @return {@link #genAddress} instance as boolean
     **/
    public boolean getGenAddress() {
        return genAddress;
    }

}
