package com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

import static java.lang.Double.parseDouble;

/**
 * The {@code OperationStatus} class is useful to format deposit and withdrawal objects
 * @apiNote see official documentation at:
 <ul>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
            https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits</a>
     </li>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
            https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals</a>
     </li>
 </ul>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class OperationStatus extends KrakenManager.KrakenResponse {

    /**
     * {@code INITIAL_STATUS} is constant for initial status
     * **/
    public static final String INITIAL_STATUS = "Initial";

    /**
     * {@code PENDING_STATUS} is constant for pending status
     * **/
    public static final String PENDING_STATUS = "Pending";

    /**
     * {@code SETTLED_STATUS} is constant for settled status
     * **/
    public static final String SETTLED_STATUS = "Settled";

    /**
     * {@code SUCCESS_STATUS} is constant for success status
     * **/
    public static final String SUCCESS_STATUS = "Success";

    /**
     * {@code FAILURE_STATUS} is constant for failure status
     * **/
    public static final String FAILURE_STATUS = "Failure";

    /**
     * {@code RETURN_STATUS_PROPERTIES} is constant for return transaction initiated by Kraken, in withdrawals
     * operation it cannot be canceled
     * **/
    public static final String RETURN_STATUS_PROPERTIES = "return";

    /**
     * {@code ONHOLD_STATUS_PROPERTIES} is constant for operation is on hold pending review
     * **/
    public static final String ONHOLD_STATUS_PROPERTIES = "onhold";

    /**
     * {@code CANCEL_PENGING_STATUS_PROPERTIES} is constant for cancelation requested
     * **/
    public static final String CANCEL_PENGING_STATUS_PROPERTIES = "cancel-pending";

    /**
     * {@code CANCELED_STATUS_PROPERTIES} is constant for canceled
     * **/
    public static final String CANCELED_STATUS_PROPERTIES = "canceled";

    /**
     * {@code CANCEL_DENIED_STATUS_PROPERTIES} is constant for cancelation requested but was denied
     * **/
    public static final String CANCEL_DENIED_STATUS_PROPERTIES = "cancel-denied";

    /**
     * {@code method} is instance that memorizes name of deposit/withdrawal method
     * **/
    private final String method;

    /**
     * {@code aClass} is instance that memorizes asset class
     * **/
    private final String aClass;

    /**
     * {@code asset} is instance that memorizes asset value
     * **/
    private final String asset;

    /**
     * {@code refId} is instance that memorizes reference identifier value
     * **/
    private final String refId;

    /**
     * {@code txId} is instance that memorizes method transaction identifier value
     * **/
    private final String txId;

    /**
     * {@code info} is instance that memorizes method transaction information
     * **/
    private final String info;

    /**
     * {@code amount} is instance that memorizes amount deposited/withdrawn
     * **/
    private final double amount;

    /**
     * {@code fee} is instance that memorizes fees paid
     * **/
    private final double fee;

    /**
     * {@code time} is instance that memorizes unix timestamp when request was made
     * **/
    private final long time;

    /**
     * {@code status} is instance that memorizes status of operation
     * **/
    private final String status;

    /**
     * {@code statusProp} is instance that memorizes addition status properties
     * **/
    private final String statusProp;

    /** Constructor to init a {@link OperationStatus} object
     * @param jsonResponse: base json response
     * @param method: name of deposit/withdrawal method
     * @param aClass: asset class
     * @param asset: asset value
     * @param refId: reference identifier value
     * @param txId: method transaction identifier value
     * @param info: method transaction information
     * @param amount: amount deposited/withdrawn
     * @param fee: fees paid
     * @param time: unix timestamp when request was made
     * @param status: status of operation
     * @param statusProp: addition status properties
     **/
    public OperationStatus(JSONObject jsonResponse, String method, String aClass, String asset, String refId, String txId,
                           String info, double amount, double fee, long time, String status, String statusProp) {
        super(jsonResponse);
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
    }

    /** Constructor to init a {@link OperationStatus} object
     * @param method: name of deposit/withdrawal method
     * @param aClass: asset class
     * @param asset: asset value
     * @param refId: reference identifier value
     * @param txId: method transaction identifier value
     * @param info: method transaction information
     * @param amount: amount deposited/withdrawn
     * @param fee: fees paid
     * @param time: unix timestamp when request was made
     * @param status: status of operation
     * @param statusProp: addition status properties
     **/
    public OperationStatus(String method, String aClass, String asset, String refId, String txId, String info, double amount,
                           double fee, long time, String status, String statusProp) {
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
    }

    /**
     * Constructor to init a {@link OperationStatus} object
     * @param jsonResponse: base json response
     **/
    public OperationStatus(JSONObject jsonResponse) {
        super(jsonResponse);
        JsonHelper jsonHelper = new JsonHelper(jsonResponse);
        method = jsonHelper.getString("method");
        aClass = jsonHelper.getString("aclass");
        asset = jsonHelper.getString("asset");
        refId = jsonHelper.getString("refid");
        txId = jsonHelper.getString("txid");
        info = jsonHelper.getString("info");
        amount = parseDouble(jsonHelper.getString("amount"));
        time = jsonHelper.getLong("time");
        fee = jsonHelper.getDouble("fee", -1);
        status = jsonHelper.getString("status");
        statusProp = jsonHelper.getString("status-prop");
    }

    public String getMethod() {
        return method;
    }

    public String getaClass() {
        return aClass;
    }

    public String getAsset() {
        return asset;
    }

    public String getRefId() {
        return refId;
    }

    public String getTxId() {
        return txId;
    }

    public String getInfo() {
        return info;
    }

    public double getAmount() {
        return amount;
    }

    public long getTime() {
        return time;
    }

    public String getStatusProp() {
        return statusProp;
    }

    @Override
    public String toString() {
        return "OperationStatus{" +
                "method='" + method + '\'' +
                ", aClass='" + aClass + '\'' +
                ", asset='" + asset + '\'' +
                ", refId=" + refId +
                ", txId='" + txId + '\'' +
                ", info='" + info + '\'' +
                ", amount=" + amount +
                ", time=" + time +
                ", statusProp='" + statusProp + '\'' +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
