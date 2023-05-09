package com.tecknobit.krakenmanager.privates.userdata.records;

import com.tecknobit.krakenmanager.KrakenManager;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Ledger} class is useful to format ledger object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
 * Query Ledgers</a>
 **/
public class Ledger extends KrakenManager.KrakenResponse {

    /**
     * {@code time} is instance that memorizes {@code "UNIX"} timestamp of ledger
     * **/
    private final long time;

    /**
     * {@code ledgerId} is instance that memorizes ledger identifier value
     * **/
    private final String ledgerId;

    /**
     * {@code refId} is instance that memorizes reference id value
     * **/
    private final String refId;
    /**
     * {@code type} is instance that memorizes type of ledger entry
     **/
    private final LedgerType type;

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
    public Ledger(String ledgerId, String refId, long time, LedgerType type, String subType, String aClass, String asset,
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
     * {@code subType} is instance that memorizes additional info relating to the ledger entry type, where applicable
     **/
    private final String subType;

    /**
     * {@code aClass} is instance that memorizes asset class value
     **/
    private final String aClass;

    /**
     * {@code asset} is instance that memorizes asset value
     **/
    private final String asset;

    /**
     * {@code amount} is instance that memorizes transaction amount value
     **/
    private final double amount;

    /**
     * {@code fee} is instance that memorizes transaction fee value
     **/
    private final double fee;

    /**
     * {@code balance} is instance that memorizes resulting balance value
     **/
    private final double balance;

    /**
     * Constructor to init a {@link Ledger} object
     *
     * @param jsonResponse: base json response
     **/
    public Ledger(JSONObject jsonResponse) {
        super(jsonResponse);
        ledgerId = result.getString("ledgerId");
        refId = result.getString("refid");
        time = result.getLong("time", 0);
        type = LedgerType.valueOf(result.getString("type", LedgerType.all.name()));
        subType = result.getString("subtype");
        aClass = result.getString("aclass");
        asset = result.getString("asset");
        amount = result.getDouble("amount", 0);
        fee = result.getDouble("fee", 0);
        balance = result.getDouble("balance", 0);
    }

    /**
     * Method to get {@link #type} instance <br>
     * No-any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public LedgerType getType() {
        return type;
    }

    /**
     * Method to get {@link #ledgerId} instance <br>
     * No-any params required
     *
     * @return {@link #ledgerId} instance as {@link String}
     **/
    public String getLedgerId() {
        return ledgerId;
    }

    /**
     * Method to get {@link #refId} instance <br>
     * No-any params required
     *
     * @return {@link #refId} instance as {@link String}
     **/
    public String getRefId() {
        return refId;
    }

    /**
     * Method to get {@link #time} instance <br>
     * No-any params required
     *
     * @return {@link #time} instance as long
     **/
    public long getTime() {
        return time;
    }

    /**
     * {@code "LedgerType"} list of types for ledger
     * **/
    public enum LedgerType {

        /**
         * {@code "all"} ledger type
         **/
        all,

        /**
         * {@code "deposit"} ledger type
         **/
        deposit,

        /**
         * {@code "withdrawal"} ledger type
         **/
        withdrawal,

        /**
         * {@code "trade"} ledger type
         **/
        trade,

        /**
         * {@code "margin"} ledger type
         **/
        margin,

        /**
         * {@code "rollover"} ledger type
         **/
        rollover,

        /**
         * {@code "credit"} ledger type
         **/
        credit,

        /**
         * {@code "transfer"} ledger type
         **/
        transfer,

        /**
         * {@code "settled"} ledger type
         **/
        settled,

        /**
         * {@code "staking"} ledger type
         **/
        staking,

        /**
         * {@code "sale"} ledger type
         **/
        sale,

    }

    /**
     * Method to get {@link #subType} instance <br>
     * No-any params required
     *
     * @return {@link #subType} instance as {@link String}
     **/
    public String getSubType() {
        return subType;
    }

    /**
     * Method to get {@link #aClass} instance <br>
     * No-any params required
     *
     * @return {@link #aClass} instance as {@link String}
     **/
    public String getaClass() {
        return aClass;
    }

    /**
     * Method to get {@link #asset} instance <br>
     * No-any params required
     *
     * @return {@link #asset} instance as {@link String}
     **/
    public String getAsset() {
        return asset;
    }

    /**
     * Method to get {@link #amount} instance <br>
     * No-any params required
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
     * No-any params required
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
     * No-any params required
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

}
