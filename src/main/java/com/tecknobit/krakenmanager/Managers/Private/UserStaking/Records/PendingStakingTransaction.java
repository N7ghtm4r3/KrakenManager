package com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class PendingStakingTransaction extends KrakenManager.KrakenResponse {

    protected final String method;
    protected final String aClass;
    protected final String asset;
    protected final String refId;
    protected final double amount;
    protected final double fee;
    protected final long time;
    protected final String status;
    protected final String type;

    public PendingStakingTransaction(JSONObject jsonResponse, String method, String aClass, String asset, String refId,
                                     double amount, double fee, long time, String status, String type) {
        super(jsonResponse);
        this.method = method;
        this.aClass = aClass;
        this.asset = asset;
        this.refId = refId;
        this.amount = amount;
        this.fee = fee;
        this.time = time;
        this.status = status;
        this.type = type;
    }

    public PendingStakingTransaction(String method, String aClass, String asset, String refId, double amount, double fee,
                                     long time, String status, String type) {
        super(null);
        this.method = method;
        this.aClass = aClass;
        this.asset = asset;
        this.refId = refId;
        this.amount = amount;
        this.fee = fee;
        this.time = time;
        this.status = status;
        this.type = type;
    }

    /**
     * Constructor to init a {@link PendingStakingTransaction} object
     * @param jsonResponse : base json response
     **/
    public PendingStakingTransaction(JSONObject jsonResponse) {
        super(jsonResponse);
        method = jsonResponse.getString("method");
        aClass = jsonResponse.getString("aclass");
        asset = jsonResponse.getString("asset");
        refId = jsonResponse.getString("refid");
        amount = jsonResponse.getDouble("amount");
        fee = jsonResponse.getDouble("fee");
        time = jsonResponse.getLong("time");
        status = jsonResponse.getString("status");
        type = jsonResponse.getString("type");
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

    public double getAmount() {
        return amount;
    }

    public double getFee() {
        return fee;
    }

    public long getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "PendingStakingTransaction{" +
                "method='" + method + '\'' +
                ", aClass='" + aClass + '\'' +
                ", asset='" + asset + '\'' +
                ", refId='" + refId + '\'' +
                ", amount=" + amount +
                ", fee=" + fee +
                ", time=" + time +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
