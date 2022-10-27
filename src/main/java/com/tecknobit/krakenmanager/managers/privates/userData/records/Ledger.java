package com.tecknobit.krakenmanager.managers.privates.userData.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Ledger} class is useful to format ledger object
 * @apiNote see official documentation at:
 * <ul>
 * <li>
 * <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
 *      https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers</a>
 * </li>
 * <li>
 * <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
 *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
 *    </li>
 * </ul>
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
    private final String refId;

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
     * @param jsonResponse: base json response
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
    public Ledger(JSONObject jsonResponse, String ledgerId, String refId, long time, String type, String subType,
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
    public Ledger(String ledgerId, String refId, long time, String type, String subType, String aClass, String asset,
                  double amount, double fee, double balance) {
        this(null, ledgerId, refId, time, type, subType, aClass, asset, amount, fee, balance);
    }

    /** Constructor to init a {@link Ledger} object
     * @param jsonResponse: base json response
     * @param ledgerId: ledger identifier value
     **/
    public Ledger(JSONObject jsonResponse, String ledgerId) {
        super(jsonResponse);
        this.ledgerId = ledgerId;
        refId = jsonResponse.getString("refid");
        time = jsonResponse.getLong("time");
        type = jsonResponse.getString("type");
        subType = jsonResponse.getString("subtype");
        aClass = jsonResponse.getString("aclass");
        asset = jsonResponse.getString("asset");
        amount = jsonResponse.getDouble("amount");
        fee = jsonResponse.getDouble("fee");
        balance = jsonResponse.getDouble("balance");
    }

    /**
     * Method to get {@link #ledgerId} instance <br>
     * Any params required
     *
     * @return {@link #ledgerId} instance as {@link String}
     **/
    public String getLedgerId() {
        return ledgerId;
    }

    /**
     * Method to get {@link #refId} instance <br>
     * Any params required
     *
     * @return {@link #refId} instance as {@link String}
     **/
    public String getRefId() {
        return refId;
    }

    /**
     * Method to get {@link #time} instance <br>
     * Any params required
     *
     * @return {@link #time} instance as long
     **/
    public long getTime() {
        return time;
    }

    /**
     * Method to get {@link #type} instance <br>
     * Any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public String getType() {
        return type;
    }

    /**
     * Method to get {@link #subType} instance <br>
     * Any params required
     *
     * @return {@link #subType} instance as {@link String}
     **/
    public String getSubType() {
        return subType;
    }

    /**
     * Method to get {@link #aClass} instance <br>
     * Any params required
     *
     * @return {@link #aClass} instance as {@link String}
     **/
    public String getaClass() {
        return aClass;
    }

    /**
     * Method to get {@link #asset} instance <br>
     * Any params required
     *
     * @return {@link #asset} instance as {@link String}
     **/
    public String getAsset() {
        return asset;
    }

    /**
     * Method to get {@link #amount} instance <br>
     * Any params required
     *
     * @return {@link #amount} instance as double
     **/
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
     * Method to get {@link #balance} instance <br>
     * Any params required
     *
     * @return {@link #balance} instance as double
     **/
    public double getBalance() {
        return balance;
    }

    /**
     * Method to get {@link #balance} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #balance} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getBalance(int decimals) {
        return roundValue(balance, decimals);
    }

    /**
     * Returns a string representation of the object <br>
     * Any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
