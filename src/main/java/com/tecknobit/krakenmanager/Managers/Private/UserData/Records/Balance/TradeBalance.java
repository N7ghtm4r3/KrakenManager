package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Balance;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

import static com.tecknobit.apimanager.Tools.Trading.TradingTools.roundValue;

/**
 * The {@code TradeBalance} class is useful to format trade balance data object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
 * https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance</a>
 **/

public class TradeBalance extends KrakenManager.KrakenResponse {

    /**
     * {@code equivalentBalance} is instance that memorizes combined balance of all currencies
     * **/
    private double equivalentBalance;

    /**
     * {@code tradeBalance} is instance that memorizes combined balance of all equity currencies
     * **/
    private double tradeBalance;

    /**
     * {@code marginAmount} is instance that memorizes margin amount of open positions
     * **/
    private double marginAmount;

    /**
     * {@code unrealizeIncome} is instance that memorizes unrealized net profit/loss of open positions
     * **/
    private double unrealizeIncome;

    /**
     * {@code costBasis} is instance that memorizes cost basis of open positions
     * **/
    private double costBasis;

    /**
     * {@code floatingValuation} is instance that memorizes current floating valuation of open positions
     * **/
    private double floatingValuation;

    /**
     * {@code equity} is instance that memorizes equity value
     * **/
    private double equity;

    /**
     * {@code freeMargin} is instance that memorizes free margin value
     * **/
    private double freeMargin;

    /**
     * {@code marginLevel} is instance that memorizes margin level value
     * **/
    private String marginLevel;

    /** Constructor to init a {@link TradeBalance} object
     * @param jsonResponse: base json response
     * @param equivalentBalance: combined balance of all currencies
     * @param tradeBalance: combined balance of all equity currencies
     * @param marginAmount: margin amount of open positions
     * @param unrealizeIncome: unrealized net profit/loss of open positions
     * @param costBasis: cost basis of open positions
     * @param floatingValuation: current floating valuation of open positions
     * @param equity: equity value
     * @param freeMargin: free margin value
     * @param marginLevel: margin level value
     * @throws IllegalArgumentException when parameters inserted do not respect correct range
     **/
    public TradeBalance(JSONObject jsonResponse, double equivalentBalance, double tradeBalance, double marginAmount,
                        double unrealizeIncome, double costBasis, double floatingValuation, double equity, double freeMargin,
                        String marginLevel) {
        super(jsonResponse);
        if(equivalentBalance < 0)
            throw new IllegalArgumentException("Equivalent balance value cannot be lesser than 0");
        else
            this.equivalentBalance = equivalentBalance;
        if(tradeBalance < 0)
            throw new IllegalArgumentException("Trade balance value cannot be lesser than 0");
        else
            this.tradeBalance = tradeBalance;
        if(marginAmount < 0)
            throw new IllegalArgumentException("Margin amount value cannot be lesser than 0");
        else
            this.marginAmount = marginAmount;
        if(unrealizeIncome < 0)
            throw new IllegalArgumentException("Unrealize income value cannot be lesser than 0");
        else
            this.unrealizeIncome = unrealizeIncome;
        if(costBasis < 0)
            throw new IllegalArgumentException("Cost basis value cannot be lesser than 0");
        else
            this.costBasis = costBasis;
        if(floatingValuation < 0)
            throw new IllegalArgumentException("Floating valuation cannot be lesser than 0");
        else
            this.floatingValuation = floatingValuation;
        if(equity < 0)
            throw new IllegalArgumentException("Equity value cannot be lesser than 0");
        else
            this.equity = equity;
        if(freeMargin < 0)
            throw new IllegalArgumentException("Free margin value cannot be lesser than 0");
        else
            this.freeMargin = freeMargin;
        if(marginLevel == null || marginLevel.isEmpty())
            this.marginLevel = "No margin level found";
        else
            this.marginLevel = marginLevel;
    }

    /** Constructor to init a {@link TradeBalance} objectù
     * @param equivalentBalance: combined balance of all currencies
     * @param tradeBalance: combined balance of all equity currencies
     * @param marginAmount: margin amount of open positions
     * @param unrealizeIncome: unrealized net profit/loss of open positions
     * @param costBasis: cost basis of open positions
     * @param floatingValuation: current floating valuation of open positions
     * @param equity: equity value
     * @param freeMargin: free margin value
     * @param marginLevel: margin level value
     * @throws IllegalArgumentException when parameters inserted do not respect correct range
     **/
    public TradeBalance(double equivalentBalance, double tradeBalance, double marginAmount, double unrealizeIncome,
                        double costBasis, double floatingValuation, double equity, double freeMargin, String marginLevel) {
        super(null);
        if(equivalentBalance < 0)
            throw new IllegalArgumentException("Equivalent balance value cannot be lesser than 0");
        else
            this.equivalentBalance = equivalentBalance;
        if(tradeBalance < 0)
            throw new IllegalArgumentException("Trade balance value cannot be lesser than 0");
        else
            this.tradeBalance = tradeBalance;
        if(marginAmount < 0)
            throw new IllegalArgumentException("Margin amount value cannot be lesser than 0");
        else
            this.marginAmount = marginAmount;
        if(unrealizeIncome < 0)
            throw new IllegalArgumentException("Unrealize income value cannot be lesser than 0");
        else
            this.unrealizeIncome = unrealizeIncome;
        if(costBasis < 0)
            throw new IllegalArgumentException("Cost basis value cannot be lesser than 0");
        else
            this.costBasis = costBasis;
        if(floatingValuation < 0)
            throw new IllegalArgumentException("Floating valuation cannot be lesser than 0");
        else
            this.floatingValuation = floatingValuation;
        if(equity < 0)
            throw new IllegalArgumentException("Equity value cannot be lesser than 0");
        else
            this.equity = equity;
        if(freeMargin < 0)
            throw new IllegalArgumentException("Free margin value cannot be lesser than 0");
        else
            this.freeMargin = freeMargin;
        if(marginLevel == null || marginLevel.isEmpty())
            this.marginLevel = "No margin level found";
        else
            this.marginLevel = marginLevel;
    }

    /** Constructor to init a {@link TradeBalance} object
     * @param jsonResponse: base json response
     **/
    public TradeBalance(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject tradeBalance = getResult();
        equivalentBalance = tradeBalance.getDouble("eb");
        this.tradeBalance = tradeBalance.getDouble("tb");
        marginAmount = tradeBalance.getDouble("m");
        unrealizeIncome = tradeBalance.getDouble("n");
        costBasis = tradeBalance.getDouble("c");
        floatingValuation = tradeBalance.getDouble("v");
        equity = tradeBalance.getDouble("e");
        freeMargin = tradeBalance.getDouble("mf");
        marginLevel = JsonHelper.getString(tradeBalance, "ml", "No margin level found");
    }

    public double getEquivalentBalance() {
        return equivalentBalance;
    }

    /**
     * Method to get {@link #equivalentBalance} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #equivalentBalance} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getEquivalentBalance(int decimals) {
        return roundValue(equivalentBalance, decimals);
    }

    /**
     * Method to set {@link #equivalentBalance}
     *
     * @param equivalentBalance: combined balance of all currencies
     * @throws IllegalArgumentException when equivalent balance value is smaller than 0
     **/
    public void setEquivalentBalance(double equivalentBalance) {
        if (equivalentBalance < 0)
            throw new IllegalArgumentException("Equivalent balance value cannot be lesser than 0");
        this.equivalentBalance = equivalentBalance;
    }

    public double getTradeBalance() {
        return tradeBalance;
    }

    /**
     * Method to get {@link #tradeBalance} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #tradeBalance} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getTradeBalance(int decimals) {
        return roundValue(tradeBalance, decimals);
    }

    /**
     * Method to set {@link #tradeBalance}
     *
     * @param tradeBalance: combined balance of all equity currencies
     * @throws IllegalArgumentException when trade balance value is smaller than 0
     **/
    public void setTradeBalance(double tradeBalance) {
        if (tradeBalance < 0)
            throw new IllegalArgumentException("Trade balance value cannot be lesser than 0");
        this.tradeBalance = tradeBalance;
    }

    public double getMarginAmount() {
        return marginAmount;
    }

    /**
     * Method to get {@link #marginAmount} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #marginAmount} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getMarginAmount(int decimals) {
        return roundValue(marginAmount, decimals);
    }

    /**
     * Method to set {@link #marginAmount}
     *
     * @param marginAmount: margin amount of open positions
     * @throws IllegalArgumentException when margin amount value is smaller than 0
     **/
    public void setMarginAmount(double marginAmount) {
        if (marginAmount < 0)
            throw new IllegalArgumentException("Margin amount value cannot be lesser than 0");
        this.marginAmount = marginAmount;
    }

    public double getUnrealizeIncome() {
        return unrealizeIncome;
    }

    /**
     * Method to get {@link #unrealizeIncome} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #unrealizeIncome} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getUnrealizeIncome(int decimals) {
        return roundValue(unrealizeIncome, decimals);
    }

    /**
     * Method to set {@link #unrealizeIncome}
     *
     * @param unrealizeIncome: unrealized net profit/loss of open positions
     * @throws IllegalArgumentException when unrealize income value is smaller than 0
     **/
    public void setUnrealizeIncome(double unrealizeIncome) {
        if (unrealizeIncome < 0)
            throw new IllegalArgumentException("Unrealize income value cannot be lesser than 0");
        this.unrealizeIncome = unrealizeIncome;
    }

    public double getCostBasis() {
        return costBasis;
    }

    /**
     * Method to get {@link #costBasis} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #costBasis} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getCostBasis(int decimals) {
        return roundValue(costBasis, decimals);
    }

    /**
     * Method to set {@link #costBasis}
     *
     * @param costBasis: cost basis of open positions
     * @throws IllegalArgumentException when cost basis value is smaller than 0
     **/
    public void setCostBasis(double costBasis) {
        if (costBasis < 0)
            throw new IllegalArgumentException("Cost basis value cannot be lesser than 0");
        this.costBasis = costBasis;
    }

    public double getFloatingValuation() {
        return floatingValuation;
    }

    /**
     * Method to get {@link #floatingValuation} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #floatingValuation} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getFloatingValuation(int decimals) {
        return roundValue(floatingValuation, decimals);
    }

    /**
     * Method to set {@link #floatingValuation}
     *
     * @param floatingValuation: current floating valuation of open positions
     * @throws IllegalArgumentException when floating valuation value is smaller than 0
     **/
    public void setFloatingValuation(double floatingValuation) {
        if (floatingValuation < 0)
            throw new IllegalArgumentException("Floating valuation cannot be lesser than 0");
        this.floatingValuation = floatingValuation;
    }

    public double getEquity() {
        return equity;
    }

    /**
     * Method to get {@link #equity} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #equity} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getEquity(int decimals) {
        return roundValue(equity, decimals);
    }

    /**
     * Method to set {@link #equity}
     *
     * @param equity: equity value
     * @throws IllegalArgumentException when equity value is smaller than 0
     **/
    public void setEquity(double equity) {
        if (equity < 0)
            throw new IllegalArgumentException("Equity value cannot be lesser than 0");
        this.equity = equity;
    }

    public double getFreeMargin() {
        return freeMargin;
    }

    /**
     * Method to get {@link #freeMargin} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #freeMargin} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getFreeMargin(int decimals) {
        return roundValue(freeMargin, decimals);
    }

    /**
     * Method to set {@link #freeMargin}
     *
     * @param freeMargin: free margin value
     * @throws IllegalArgumentException when free margin value is smaller than 0
     **/
    public void setFreeMargin(double freeMargin) {
        if (freeMargin < 0)
            throw new IllegalArgumentException("Free margin value cannot be lesser than 0");
        this.freeMargin = freeMargin;
    }

    public String getMarginLevel() {
        return marginLevel;
    }

    /** Method to set {@link #marginLevel}
     * @param marginLevel: margin level value
     * @throws IllegalArgumentException when margin level value is null or is blank
     * **/
    public void setMarginLevel(String marginLevel) {
        if(marginLevel == null || marginLevel.isEmpty())
            this.marginLevel = "No margin level found";
        else
            this.marginLevel = marginLevel;
    }

    @Override
    public String toString() {
        return "TradeBalance{" +
                "equivalentBalance=" + equivalentBalance +
                ", tradeBalance=" + tradeBalance +
                ", marginAmount=" + marginAmount +
                ", unrealizeIncome=" + unrealizeIncome +
                ", costBasis=" + costBasis +
                ", floatingValuation=" + floatingValuation +
                ", equity=" + equity +
                ", freeMargin=" + freeMargin +
                ", marginLevel='" + marginLevel + '\'' +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
