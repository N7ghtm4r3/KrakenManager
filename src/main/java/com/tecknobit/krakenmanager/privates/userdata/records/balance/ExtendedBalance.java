package com.tecknobit.krakenmanager.privates.userdata.records.balance;

import com.tecknobit.krakenmanager.KrakenManager.KrakenResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code ExtendedBalance} class is useful to format a {@code Kraken}'s extended balance
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getExtendedBalance">
 * Get Extended Balance</a>
 */
public class ExtendedBalance extends KrakenResponse {

    /**
     * {@code balance} total balance amount for an asset
     */
    private final double balance;

    /**
     * {@code credit} total credit amount (only applicable if account has a credit line)
     */
    private final double credit;

    /**
     * {@code creditUsed} used credit amount (only applicable if account has a credit line)
     */
    private final double creditUsed;

    /**
     * {@code holdTrade} total held amount for an asset
     */
    private final double holdTrade;

    /**
     * Constructor to init a {@link ExtendedBalance} object
     *
     * @param balance:    total balance amount for an asset
     * @param credit:     total credit amount (only applicable if account has a credit line)
     * @param creditUsed: used credit amount (only applicable if account has a credit line)
     * @param holdTrade:  total held amount for an asset
     */
    public ExtendedBalance(double balance, double credit, double creditUsed, double holdTrade) {
        super(null);
        this.balance = balance;
        this.credit = credit;
        this.creditUsed = creditUsed;
        this.holdTrade = holdTrade;
    }

    /**
     * Constructor to init a {@link ExtendedBalance} object
     *
     * @param jExtendedBalance: extended balance details as {@link JSONObject}
     */
    public ExtendedBalance(JSONObject jExtendedBalance) {
        super(jExtendedBalance);
        balance = hResponse.getDouble("balance", 0);
        credit = hResponse.getDouble("credit", 0);
        creditUsed = hResponse.getDouble("credit_used", 0);
        holdTrade = hResponse.getDouble("hold_trade", 0);
    }

    /**
     * Method to get {@link #balance} instance <br>
     * No-any params required
     *
     * @return {@link #balance} instance as double
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Method to get {@link #balance} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #balance} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     */
    public double getBalance(int decimals) {
        return roundValue(balance, decimals);
    }

    /**
     * Method to get {@link #credit} instance <br>
     * No-any params required
     *
     * @return {@link #credit} instance as double
     */
    public double getCredit() {
        return credit;
    }

    /**
     * Method to get {@link #credit} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #credit} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     */
    public double getCredit(int decimals) {
        return roundValue(credit, decimals);
    }

    /**
     * Method to get {@link #creditUsed} instance <br>
     * No-any params required
     *
     * @return {@link #creditUsed} instance as double
     */
    public double getCreditUsed() {
        return creditUsed;
    }

    /**
     * Method to get {@link #creditUsed} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #creditUsed} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     */
    public double getCreditUsed(int decimals) {
        return roundValue(creditUsed, decimals);
    }

    /**
     * Method to get {@link #holdTrade} instance <br>
     * No-any params required
     *
     * @return {@link #holdTrade} instance as double
     */
    public double getHoldTrade() {
        return holdTrade;
    }

    /**
     * Method to get {@link #holdTrade} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #holdTrade} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     */
    public double getHoldTrade(int decimals) {
        return roundValue(holdTrade, decimals);
    }

}
