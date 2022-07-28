package com.tecknobit.krakenmanager.Managers.Private.UserData.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class TradeBalance extends KrakenManager.KrakenResponse {

    // TODO: 28/07/2022 READ DOCU FIRST TO INSERT OPEN POSITIONS IN DOCU STRING

    private double equivalentBalance;
    private double tradeBalance;
    private double marginAmount;
    private double unrealizeIncome;
    private double costBasis;
    private double floatingValuation;
    private double equity;
    private double freeMargin;
    private String marginLevel;

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

    /**
     * Constructor to init a {@link TradeBalance}
     * @param jsonResponse : base json response
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

    public void setEquivalentBalance(double equivalentBalance) {
        if(equivalentBalance < 0)
            throw new IllegalArgumentException("Equivalent balance value cannot be lesser than 0");
        this.equivalentBalance = equivalentBalance;
    }

    public double getTradeBalance() {
        return tradeBalance;
    }

    public void setTradeBalance(double tradeBalance) {
        if(tradeBalance < 0)
            throw new IllegalArgumentException("Trade balance value cannot be lesser than 0");
        this.tradeBalance = tradeBalance;
    }

    public double getMarginAmount() {
        return marginAmount;
    }

    public void setMarginAmount(double marginAmount) {
        if(marginAmount < 0)
            throw new IllegalArgumentException("Margin amount value cannot be lesser than 0");
        this.marginAmount = marginAmount;
    }

    public double getUnrealizeIncome() {
        return unrealizeIncome;
    }

    public void setUnrealizeIncome(double unrealizeIncome) {
        if(unrealizeIncome < 0)
            throw new IllegalArgumentException("Unrealize income value cannot be lesser than 0");
        this.unrealizeIncome = unrealizeIncome;
    }

    public double getCostBasis() {
        return costBasis;
    }

    public void setCostBasis(double costBasis) {
        if(costBasis < 0)
            throw new IllegalArgumentException("Cost basis value cannot be lesser than 0");
        this.costBasis = costBasis;
    }

    public double getFloatingValuation() {
        return floatingValuation;
    }

    public void setFloatingValuation(double floatingValuation) {
        if(floatingValuation < 0)
            throw new IllegalArgumentException("Floating valuation cannot be lesser than 0");
        this.floatingValuation = floatingValuation;
    }

    public double getEquity() {
        return equity;
    }

    public void setEquity(double equity) {
        if(equity < 0)
            throw new IllegalArgumentException("Equity value cannot be lesser than 0");
        this.equity = equity;
    }

    public double getFreeMargin() {
        return freeMargin;
    }

    public void setFreeMargin(double freeMargin) {
        if(freeMargin < 0)
            throw new IllegalArgumentException("Free margin value cannot be lesser than 0");
        this.freeMargin = freeMargin;
    }

    public String getMarginLevel() {
        return marginLevel;
    }

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
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
