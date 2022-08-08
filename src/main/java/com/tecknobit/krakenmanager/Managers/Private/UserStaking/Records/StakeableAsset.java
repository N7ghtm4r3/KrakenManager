package com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class StakeableAsset extends KrakenManager.KrakenResponse {

    private final String method;
    private final String asset;
    private final String stakingAsset;
    private final Rewards rewards;
    private final boolean onChain;
    private final boolean canStake;
    private final boolean canUnstake;
    private final MinimumAmount minimumAmount;

    public StakeableAsset(JSONObject jsonResponse, String method, String asset, String stakingAsset, Rewards rewards,
                          boolean onChain, boolean canStake, boolean canUnstake, MinimumAmount minimumAmount) {
        super(jsonResponse);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
    }

    public StakeableAsset(String method, String asset, String stakingAsset, Rewards rewards, boolean onChain,
                          boolean canStake, boolean canUnstake, MinimumAmount minimumAmount) {
        super(null);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
    }

    /**
     * Constructor to init a {@link StakeableAsset} object
     * @param jsonResponse : base json response
     **/
    public StakeableAsset(JSONObject jsonResponse) {
        super(jsonResponse);
        method = jsonResponse.getString("method");
        asset = jsonResponse.getString("asset");
        stakingAsset = jsonResponse.getString("staking_asset");
        rewards = new Rewards(jsonResponse.getJSONObject("rewards"));
        onChain = jsonResponse.getBoolean("on_chain");
        canStake = jsonResponse.getBoolean("can_stake");
        canUnstake = jsonResponse.getBoolean("can_unstake");
        minimumAmount = new MinimumAmount(jsonResponse.getJSONObject("minimum_amount"));
    }

    public String getMethod() {
        return method;
    }

    public String getAsset() {
        return asset;
    }

    public String getStakingAsset() {
        return stakingAsset;
    }

    public Rewards getRewards() {
        return rewards;
    }

    public boolean isOnChain() {
        return onChain;
    }

    public boolean canStake() {
        return canStake;
    }

    public boolean canUnstake() {
        return canUnstake;
    }

    public MinimumAmount getMinimumAmount() {
        return minimumAmount;
    }

    @Override
    public String toString() {
        return "StakeableAsset{" +
                "method='" + method + '\'' +
                ", asset='" + asset + '\'' +
                ", stakingAsset='" + stakingAsset + '\'' +
                ", rewards=" + rewards +
                ", onChain=" + onChain +
                ", canStake=" + canStake +
                ", canUnstake=" + canUnstake +
                ", minimumAmount=" + minimumAmount +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    public static class Rewards {

        private final double reward;
        private final String type;

        public Rewards(double reward, String type) {
            this.reward = reward;
            this.type = type;
        }

        public Rewards(JSONObject jsonReward) {
            reward = jsonReward.getDouble("reward");
            type = jsonReward.getString("type");
        }

        public double getReward() {
            return reward;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return "Rewards{" +
                    "reward=" + reward +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    public static class MinimumAmount {

        private final double staking;
        private final double unstaking;

        public MinimumAmount(double staking, double unstaking) {
            this.staking = staking;
            this.unstaking = unstaking;
        }

        public MinimumAmount(JSONObject jsonAmount) {
            staking = jsonAmount.getDouble("staking");
            unstaking = jsonAmount.getDouble("unstaking");
        }


        public double getStaking() {
            return staking;
        }

        public double getUnstaking() {
            return unstaking;
        }

        @Override
        public String toString() {
            return "MinimumAmount{" +
                    "staking=" + staking +
                    ", unstaking=" + unstaking +
                    '}';
        }

    }

}
