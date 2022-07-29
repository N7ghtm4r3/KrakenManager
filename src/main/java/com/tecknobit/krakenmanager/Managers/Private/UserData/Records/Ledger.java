package com.tecknobit.krakenmanager.Managers.Private.UserData.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class Ledger extends KrakenManager.KrakenResponse {

    public static final String ALL_TYPE = "all";
    public static final String DEPOSIT_TYPE = "deposit";
    public static final String WITHDRAWAL_TYPE = "withdrawal";
    public static final String TRADE_TYPE = "trade";
    public static final String MARGIN_TYPE = "margin";
    public static final String ROLLOVER_TYPE = "rollover";
    public static final String CREDIT_TYPE = "credit";
    public static final String TRANSFER_TYPE = "transfer";
    public static final String SETTLED_TYPE = "settled";
    public static final String STAKING_TYPE = "staking";
    public static final String SALE_TYPE = "sale";

    private final String ledgerId;
    private final long refId;
    private final long time;
    private final String type;
    private final String subType;
    private final String aClass;
    private final String asset;
    private final double amount;
    private final double fee;
    private final double balance;

    public Ledger(JSONObject jsonResponse, String ledgerId, long refId, long time, String type, String subType,
                  String aClass, String asset, double amount, double fee, double balance) {
        super(jsonResponse);
        this.ledgerId = ledgerId;
        this.refId = refId;
        this.time = time;
        this.type = type;
        this.subType = subType;
        this.aClass = aClass;
        this.asset = asset;
        this.amount = amount;
        this.fee = fee;
        this.balance = balance;
    }

    public Ledger(String ledgerId, long refId, long time, String type, String subType, String aClass, String asset,
                  double amount, double fee, double balance) {
        super(null);
        this.ledgerId = ledgerId;
        this.refId = refId;
        this.time = time;
        this.type = type;
        this.subType = subType;
        this.aClass = aClass;
        this.asset = asset;
        this.amount = amount;
        this.fee = fee;
        this.balance = balance;
    }

    /**
     * Constructor to init a {@link Ledger}
     * @param jsonResponse : base json response
     **/
    public Ledger(JSONObject jsonResponse, String ledgerId) {
        super(jsonResponse);
        this.ledgerId = ledgerId;
        refId = jsonResponse.getLong("refid");
        time = jsonResponse.getLong("time");
        type = jsonResponse.getString("type");
        subType = jsonResponse.getString("subtype");
        aClass = jsonResponse.getString("aclass");
        asset = jsonResponse.getString("asset");
        amount = jsonResponse.getDouble("amount");
        fee = jsonResponse.getDouble("fee");
        balance = jsonResponse.getDouble("balance");
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public long getRefId() {
        return refId;
    }

    public long getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getaClass() {
        return aClass;
    }

    public String getAsset() {
        return asset;
    }

    public double getAmount() {
        return amount;
    }

    public double getFee() {
        return fee;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Ledger{" +
                "ledgerId='" + ledgerId + '\'' +
                ", refId=" + refId +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                ", aClass='" + aClass + '\'' +
                ", asset='" + asset + '\'' +
                ", amount=" + amount +
                ", fee=" + fee +
                ", balance=" + balance +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
