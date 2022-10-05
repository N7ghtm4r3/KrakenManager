package com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

import static com.tecknobit.apimanager.Tools.Trading.TradingTools.roundValue;

/**
 * The {@code StakingTransaction} class is useful to format a staking transaction
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits">
 * https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits</a>
 **/

public class StakingTransaction extends KrakenManager.KrakenResponse {

    /**
     * {@code BONDING_TYPE} is constant for bonding type transactions
     * **/
    public static final String BONDING_TYPE = "bonding";

    /**
     * {@code REWARD_TYPE} is constant for reward type transactions
     * **/
    public static final String REWARD_TYPE = "reward";

    /**
     * {@code UNBONDING_TYPE} is constant for unbonding type transactions
     * **/
    public static final String UNBONDING_TYPE = "unbonding";
    
    /**
     * {@code method} is instance that memorizes method value
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
     * {@code refId} is instance that memorizes the reference identifier of the transaction
     * **/
    private final String refId;

    /**
     * {@code amount} is instance that memorizes transaction amount
     * **/
    private final double amount;

    /**
     * {@code fee} is instance that memorizes fees paid
     * **/
    private final double fee;

    /**
     * {@code time} is instance that memorizes unix timestamp when the transaction was initiated
     * **/
    private final long time;

    /**
     * {@code status} is instance that memorizes transaction status
     * **/
    private final String status;

    /**
     * {@code type} is instance that memorizes type of transaction
     * **/
    private final String type;

    /**
     * {@code bondStart} is instance that memorizes unix timestamp from the start of bond period (applicable only to bonding transactions)
     * **/
    private final long bondStart;

    /**
     * {@code bondEnd} is instance that memorizes unix timestamp of the end of bond period (applicable only to bonding transactions)
     * **/
    private final long bondEnd;

    /** Constructor to init a {@link StakingTransaction} object
     * @param jsonResponse: base json response
     * @param method: method value
     * @param aClass: asset class
     * @param asset: asset value
     * @param refId: reference identifier of the transaction
     * @param amount: transaction amount
     * @param fee: fees paid
     * @param time: unix timestamp when the transaction was initiated
     * @param status: transaction status
     * @param type: type of transaction
     * @param bondStart: unix timestamp from the start of bond period (applicable only to bonding transactions)
     * @param bondEnd: unix timestamp of the end of bond period (applicable only to bonding transactions)
     * @implSpec this constructor is useful only if transaction type is bonding
     **/
    public StakingTransaction(JSONObject jsonResponse, String method, String aClass, String asset, String refId,
                              double amount, double fee, long time, String status, String type, long bondStart,
                              long bondEnd) {
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
        this.bondStart = bondStart;
        this.bondEnd = bondEnd;
    }

    /** Constructor to init a {@link StakingTransaction} object
     * @param method: method value
     * @param aClass: asset class
     * @param asset: asset value
     * @param refId: reference identifier of the transaction
     * @param amount: transaction amount
     * @param fee: fees paid
     * @param time: unix timestamp when the transaction was initiated
     * @param status: transaction status
     * @param type: type of transaction
     * @param bondStart: unix timestamp from the start of bond period (applicable only to bonding transactions)
     * @param bondEnd: unix timestamp of the end of bond period (applicable only to bonding transactions)
     * @implSpec this constructor is useful only if transaction type is bonding
     **/
    public StakingTransaction(String method, String aClass, String asset, String refId, double amount, double fee,
                              long time, String status, String type, long bondStart, long bondEnd) {
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
        this.bondStart = bondStart;
        this.bondEnd = bondEnd;
    }

    /** Constructor to init a {@link StakingTransaction} object
     * @param jsonResponse: base json response
     * @param method: method value
     * @param aClass: asset class
     * @param asset: asset value
     * @param refId: reference identifier of the transaction
     * @param amount: transaction amount
     * @param fee: fees paid
     * @param time: unix timestamp when the transaction was initiated
     * @param status: transaction status
     * @param type: type of transaction
     **/
    public StakingTransaction(JSONObject jsonResponse, String method, String aClass, String asset, String refId,
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
        bondStart = -1;
        bondEnd = -1;
    }

    /** Constructor to init a {@link StakingTransaction} object
     * @param method: method value
     * @param aClass: asset class
     * @param asset: asset value
     * @param refId: reference identifier of the transaction
     * @param amount: transaction amount
     * @param fee: fees paid
     * @param time: unix timestamp when the transaction was initiated
     * @param status: transaction status
     * @param type: type of transaction
     **/
    public StakingTransaction(String method, String aClass, String asset, String refId, double amount, double fee,
                              long time, String status, String type) {
        this(null, method, aClass, asset, refId, amount, fee, time, status, type);
    }

    /**
     * Constructor to init a {@link StakingTransaction} object
     * @param jsonResponse : base json response
     **/
    public StakingTransaction(JSONObject jsonResponse) {
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
        JsonHelper jsonHelper = new JsonHelper(jsonResponse);
        bondStart = jsonHelper.getLong("bond_start", -1);
        bondEnd = jsonHelper.getLong("bond_end", -1);
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

    /**
     * Method to get {@link #amount} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #amount} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getAmount(int decimals) {
        return roundValue(amount, decimals);
    }

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

    public long getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public long getBondStart() {
        return bondStart;
    }

    public long getBondEnd() {
        return bondEnd;
    }

    @Override
    public String toString() {
        return "StakingTransaction{" +
                "method='" + method + '\'' +
                ", aClass='" + aClass + '\'' +
                ", asset='" + asset + '\'' +
                ", refId='" + refId + '\'' +
                ", amount=" + amount +
                ", fee=" + fee +
                ", time=" + time +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", bondStart=" + bondStart +
                ", bondEnd=" + bondEnd +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
