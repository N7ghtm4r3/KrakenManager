package com.tecknobit.krakenmanager.Managers.Private.UserData.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * The {@code Order} class is useful to format ledger object
 * @apiNote see official documentation at:
 <ul>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
            https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers</a>
     </li>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
            https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers</a>
     </li>
 </ul>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class Ledger extends KrakenManager.KrakenResponse {

    /**
     * {@code ALL_TYPE} is constant for all ledger type
     * **/
    public static final String ALL_TYPE = "all";

    /**
     * {@code DEPOSIT_TYPE} is constant for deposit ledger type
     * **/
    public static final String DEPOSIT_TYPE = "deposit";

    /**
     * {@code WITHDRAWAL_TYPE} is constant for withdrawal ledger type
     * **/
    public static final String WITHDRAWAL_TYPE = "withdrawal";

    /**
     * {@code TRADE_TYPE} is constant for trade ledger type
     * **/
    public static final String TRADE_TYPE = "trade";

    /**
     * {@code MARGIN_TYPE} is constant for all margin type
     * **/
    public static final String MARGIN_TYPE = "margin";

    /**
     * {@code ROLLOVER_TYPE} is constant for rollover ledger type
     * **/
    public static final String ROLLOVER_TYPE = "rollover";

    /**
     * {@code CREDIT_TYPE} is constant for credit ledger type
     * **/
    public static final String CREDIT_TYPE = "credit";

    /**
     * {@code TRANSFER_TYPE} is constant for transfer ledger type
     * **/
    public static final String TRANSFER_TYPE = "transfer";

    /**
     * {@code SETTLED_TYPE} is constant for settled ledger type
     * **/
    public static final String SETTLED_TYPE = "settled";

    /**
     * {@code STAKING_TYPE} is constant for staking ledger type
     * **/
    public static final String STAKING_TYPE = "staking";

    /**
     * {@code SALE_TYPE} is constant for sale ledger type
     * **/
    public static final String SALE_TYPE = "sale";

    /**
     * {@code ledgerId} is instance that memorizes ledger identifier value
     * **/
    private final String ledgerId;

    /**
     * {@code refId} is instance that memorizes reference id value
     * **/
    private final long refId;

    /**
     * {@code time} is instance that memorizes UNIX timestamp of ledger
     * **/
    private final long time;

    /**
     * {@code type} is instance that memorizes type of ledger entry
     * **/
    private final String type;

    /**
     * {@code subType} is instance that memorizes additional info relating to the ledger entry type, where applicable
     * **/
    private final String subType;

    /**
     * {@code aClass} is instance that memorizes asset class value
     * **/
    private final String aClass;

    /**
     * {@code asset} is instance that memorizes asset value
     * **/
    private final String asset;

    /**
     * {@code amount} is instance that memorizes transaction amount value
     * **/
    private final double amount;

    /**
     * {@code fee} is instance that memorizes transaction fee value
     * **/
    private final double fee;

    /**
     * {@code balance} is instance that memorizes resulting balance value
     * **/
    private final double balance;

    /** Constructor to init a {@link Ledger} object
     * @param jsonResponse : base json response
     * @param ledgerId: ledger identifier value
     * @param refId: reference id value
     * @param time: unix timestamp of ledger
     * @param type: type of ledger entry
     * @param subType: additional info relating to the ledger entry type, where applicable
     * @param aClass: asset class value
     * @param asset: asset value
     * @param amount: transaction amount value
     * @param fee: transaction fee value
     * @param balance: resulting balance value
     **/
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

    /** Constructor to init a {@link Ledger} object
     * @param ledgerId: ledger identifier value
     * @param refId: reference id value
     * @param time: unix timestamp of ledger
     * @param type: type of ledger entry
     * @param subType: additional info relating to the ledger entry type, where applicable
     * @param aClass: asset class value
     * @param asset: asset value
     * @param amount: transaction amount value
     * @param fee: transaction fee value
     * @param balance: resulting balance value
     **/
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

    /** Constructor to init a {@link Ledger} object
     * @param jsonResponse : base json response
     * @param ledgerId: ledger identifier value
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
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
