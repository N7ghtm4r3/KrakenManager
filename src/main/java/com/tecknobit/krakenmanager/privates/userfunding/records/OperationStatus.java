package com.tecknobit.krakenmanager.privates.userfunding.records;

import com.tecknobit.krakenmanager.KrakenManager;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;
import static java.lang.Double.parseDouble;

/**
 * The {@code OperationStatus} class is useful to format deposit and withdrawal objects
 * @apiNote see the official documentation at:
 * <ul>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
 *           Get Status of Recent Deposits/a>
 *    </li>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
 *           Get Status of Recent Withdrawals</a>
 *    </li>
 * </ul>
 * @author N7ghtm4r3 - Tecknobit
 */
public class OperationStatus extends KrakenManager.KrakenResponse {

    /**
     * {@code INITIAL_STATUS} is constant for initial status
     */
    public static final String INITIAL_STATUS = "Initial";

    /**
     * {@code PENDING_STATUS} is constant for pending status
     */
    public static final String PENDING_STATUS = "Pending";

    /**
     * {@code SETTLED_STATUS} is constant for settled status
     */
    public static final String SETTLED_STATUS = "Settled";

    /**
     * {@code SUCCESS_STATUS} is constant for success status
     */
    public static final String SUCCESS_STATUS = "Success";

    /**
     * {@code FAILURE_STATUS} is constant for failure status
     */
    public static final String FAILURE_STATUS = "Failure";

    /**
     * {@code RETURN_STATUS_PROPERTIES} is constant for return transaction initiated by Kraken, in withdrawals
     * operation it cannot be canceled
     */
    public static final String RETURN_STATUS_PROPERTIES = "return";

    /**
     * {@code ONHOLD_STATUS_PROPERTIES} is constant for operation is on hold pending review
     */
    public static final String ONHOLD_STATUS_PROPERTIES = "onhold";

    /**
     * {@code CANCEL_PENDING_STATUS_PROPERTIES} is constant for cancellation requested
     */
    public static final String CANCEL_PENDING_STATUS_PROPERTIES = "cancel-pending";

    /**
     * {@code CANCELED_STATUS_PROPERTIES} is constant for canceled
     */
    public static final String CANCELED_STATUS_PROPERTIES = "canceled";

    /**
     * {@code CANCEL_DENIED_STATUS_PROPERTIES} is constant for cancelation requested but was denied
     */
    public static final String CANCEL_DENIED_STATUS_PROPERTIES = "cancel-denied";

    /**
     * {@code method} is instance that memorizes name of deposit/withdrawal method
     */
    private final String method;

    /**
     * {@code aClass} is instance that memorizes asset class
     */
    private final String aClass;

    /**
     * {@code asset} is instance that memorizes asset value
     */
    private final String asset;

    /**
     * {@code refId} is instance that memorizes reference identifier value
     */
    private final String refId;

    /**
     * {@code txId} is instance that memorizes method transaction identifier value
     */
    private final String txId;

    /**
     * {@code info} is instance that memorizes method transaction information
     */
    private final String info;

    /**
     * {@code amount} is instance that memorizes amount deposited/withdrawn
     */
    private final double amount;

    /**
     * {@code fee} is instance that memorizes fees paid
     */
    private final double fee;

    /**
     * {@code time} is instance that memorizes unix timestamp when request was made
     */
    private final long time;

    /**
     * {@code status} is instance that memorizes status of operation
     */
    private final String status;

    /**
     * {@code statusProp} is instance that memorizes addition status properties
     */
    private final String statusProp;

    /**
     * {@code originators} is instance that memorizes originators values
     */
    private final ArrayList<String> originators;

    /**
     * Constructor to init a {@link OperationStatus} object
     *
     * @param method       : name of deposit/withdrawal method
     * @param aClass       : asset class
     * @param asset        : asset value
     * @param refId        : reference identifier value
     * @param txId         : method transaction identifier value
     * @param info         : method transaction information
     * @param amount       : amount deposited/withdrawn
     * @param fee          : fees paid
     * @param time         : unix timestamp when request was made
     * @param status       : status of operation
     * @param statusProp   : addition status properties
     * @param originators: originators values
     */
    public OperationStatus(String method, String aClass, String asset, String refId, String txId, String info,
                           double amount, double fee, long time, String status, String statusProp,
                           ArrayList<String> originators) {
        super(null);
        this.method = method;
        this.aClass = aClass;
        this.asset = asset;
        this.refId = refId;
        this.txId = txId;
        this.info = info;
        this.amount = amount;
        this.fee = fee;
        this.time = time;
        this.status = status;
        this.statusProp = statusProp;
        this.originators = originators;
    }

    /**
     * Constructor to init a {@link OperationStatus} object
     * @param jsonResponse: base json response
     */
    public OperationStatus(JSONObject jsonResponse) {
        super(jsonResponse);
        method = result.getString("method");
        aClass = result.getString("aclass");
        asset = result.getString("asset");
        refId = result.getString("refid");
        txId = result.getString("txid");
        info = result.getString("info");
        amount = parseDouble(result.getString("amount"));
        time = result.getLong("time", 0);
        fee = result.getDouble("fee", 0);
        status = result.getString("status");
        statusProp = result.getString("status-prop");
        originators = result.fetchList("originators");
    }

    /**
     * Method to get {@link #method} instance <br>
     * No-any params required
     *
     * @return {@link #method} instance as {@link String}
     */
    public String getMethod() {
        return method;
    }

    /**
     * Method to get {@link #aClass} instance <br>
     * No-any params required
     *
     * @return {@link #aClass} instance as {@link String}
     */
    public String getaClass() {
        return aClass;
    }

    /**
     * Method to get {@link #asset} instance <br>
     * No-any params required
     *
     * @return {@link #asset} instance as {@link String}
     */
    public String getAsset() {
        return asset;
    }

    /**
     * Method to get {@link #refId} instance <br>
     * No-any params required
     *
     * @return {@link #refId} instance as {@link String}
     */
    public String getRefId() {
        return refId;
    }

    /**
     * Method to get {@link #txId} instance <br>
     * No-any params required
     *
     * @return {@link #txId} instance as {@link String}
     */
    public String getTxId() {
        return txId;
    }

    /**
     * Method to get {@link #info} instance <br>
     * No-any params required
     *
     * @return {@link #info} instance as {@link String}
     */
    public String getInfo() {
        return info;
    }

    /**
     * Method to get {@link #amount} instance <br>
     * No-any params required
     *
     * @return {@link #amount} instance as double
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Method to get {@link #amount} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #amount} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     */
    public double getAmount(int decimals) {
        return roundValue(amount, decimals);
    }

    /**
     * Method to get {@link #fee} instance <br>
     * No-any params required
     *
     * @return {@link #fee} instance as double
     */
    public double getFee() {
        return fee;
    }

    /**
     * Method to get {@link #fee} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #fee} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     */
    public double getFee(int decimals) {
        return roundValue(fee, decimals);
    }

    /**
     * Method to get {@link #time} instance <br>
     * No-any params required
     *
     * @return {@link #time} instance as long
     */
    public long getTime() {
        return time;
    }

    /**
     * Method to get {@link #status} instance <br>
     * No-any params required
     *
     * @return {@link #status} instance as {@link String}
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method to get {@link #statusProp} instance <br>
     * No-any params required
     *
     * @return {@link #statusProp} instance as {@link String}
     */
    public String getStatusProp() {
        return statusProp;
    }

    /**
     * Method to get {@link #originators} instance <br>
     * No-any params required
     *
     * @return {@link #originators} instance as {@link ArrayList} of {@link String}
     */
    public ArrayList<String> getOriginators() {
        return originators;
    }

}
