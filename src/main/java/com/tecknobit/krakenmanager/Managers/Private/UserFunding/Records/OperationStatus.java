package com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

import static java.lang.Double.parseDouble;

public class OperationStatus extends KrakenManager.KrakenResponse {

    private final String method;
    private final String aClass;
    private final String asset;
    private final String refId;
    private final String txId;
    private final String info;
    private final double amount;
    private final double fee;
    private final long time;
    private final String status;
    private final String statusProp;

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
     * @param jsonResponse : base json response
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
