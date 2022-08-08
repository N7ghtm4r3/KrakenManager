package com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import java.util.Arrays;

public class StakingTransaction extends PendingStakingTransaction {

    public static final String BONDING_TYPE = "bonding";
    public static final String REWARD_TYPE = "reward";
    public static final String UNBONDING_TYPE = "unbonding";

    private final long bondStart;
    private final long bondEnd;

    public StakingTransaction(JSONObject jsonResponse, String method, String aClass, String asset, String refId,
                              double amount, double fee, long time, String status, String type, long bondStart, long bondEnd) {
        super(jsonResponse, method, aClass, asset, refId, amount, fee, time, status, type);
        this.bondStart = bondStart;
        this.bondEnd = bondEnd;
    }

    public StakingTransaction(String method, String aClass, String asset, String refId, double amount, double fee,
                              long time, String status, String type, long bondStart, long bondEnd) {
        super(method, aClass, asset, refId, amount, fee, time, status, type);
        this.bondStart = bondStart;
        this.bondEnd = bondEnd;
    }

    public StakingTransaction(JSONObject jsonResponse) {
        super(jsonResponse);
        JsonHelper jsonHelper = new JsonHelper(jsonResponse);
        bondStart = jsonHelper.getLong("bond_start");
        bondEnd = jsonHelper.getLong("bond_end");
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
                "bondStart=" + bondStart +
                ", bondEnd=" + bondEnd +
                ", method='" + method + '\'' +
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
