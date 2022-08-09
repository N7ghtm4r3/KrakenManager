package com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code StakeableAsset} class is useful to format a stakeable asset
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo">
 *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class StakeableAsset extends KrakenManager.KrakenResponse {

    /**
     * {@code method} is instance that memorizes unique ID of the staking option (used in Stake/Unstake operations)
     * **/
    private final String method;

    /**
     * {@code asset} is instance that memorizes asset code/name
     * **/
    private final String asset;

    /**
     * {@code asset} is instance that memorizes staking asset code/name
     * **/
    private final String stakingAsset;

    /**
     * {@code rewards} is instance that memorizes describes the rewards earned while staking
     * **/
    private final Rewards rewards;

    /**
     * {@code onChain} is flag that memorizes whether the staking operation is on-chain or not
     * **/
    private final boolean onChain;

    /**
     * {@code canStake} is flag that memorizes whether the user will be able to stake this asset
     * **/
    private final boolean canStake;

    /**
     * {@code canStake} is flag that memorizes whether the user will be able to unstake this asset
     * **/
    private final boolean canUnstake;

    /**
     * {@code minimumAmount} is instance that memorizes minimium amounts for staking/unstaking
     * **/
    private final MinimumAmount minimumAmount;

    /**
     * {@code enabledForUser} is flag that memorizes if staking is enabled for user
     * **/
    private final boolean enabledForUser;

    /**
     * {@code disabled} is flag that memorizes if staking is disabled
     * **/
    private final boolean disabled;

    /**
     * {@code unstaking} is flag that memorizes list of {@link Lock} as unstaking list
     * **/
    private final ArrayList<Lock> unstaking;

    /**
     * {@code staking} is flag that memorizes list of {@link Lock} as staking list
     * **/
    private final ArrayList<Lock> staking;

    /**
     * {@code lockup} is flag that memorizes list of {@link Lock} as lockup list
     * **/
    private final ArrayList<Lock> lockup;

    /** Constructor to init a {@link StakeableAsset} object
     * @param jsonResponse: base json response
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param enabledForUser: flag that memorizes if staking is enabled for user
     * @param disabled: flag that memorizes if staking is disabled
     * @param unstaking: list of {@link Lock} as unstaking list
     * @param staking: list of {@link Lock} as staking list
     * @param lockup: list of {@link Lock} as lockup list
     **/
    public StakeableAsset(JSONObject jsonResponse, String method, String asset, String stakingAsset, Rewards rewards,
                          boolean onChain, boolean canStake, boolean canUnstake, MinimumAmount minimumAmount,
                          boolean enabledForUser, boolean disabled, ArrayList<Lock> unstaking, ArrayList<Lock> staking,
                          ArrayList<Lock> lockup) {
        super(jsonResponse);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.enabledForUser = enabledForUser;
        this.disabled = disabled;
        this.unstaking = unstaking;
        this.staking = staking;
        this.lockup = lockup;
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param enabledForUser: flag that memorizes if staking is enabled for user
     * @param disabled: flag that memorizes if staking is disabled
     * @param unstaking: list of {@link Lock} as unstaking list
     * @param staking: list of {@link Lock} as staking list
     * @param lockup: list of {@link Lock} as lockup list
     **/
    public StakeableAsset(String method, String asset, String stakingAsset, Rewards rewards, boolean onChain,
                          boolean canStake, boolean canUnstake, MinimumAmount minimumAmount, boolean enabledForUser,
                          boolean disabled, ArrayList<Lock> unstaking, ArrayList<Lock> staking, ArrayList<Lock> lockup) {
        super(null);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.enabledForUser = enabledForUser;
        this.disabled = disabled;
        this.unstaking = unstaking;
        this.staking = staking;
        this.lockup = lockup;
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param jsonResponse: base json response
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param enabledForUser: flag that memorizes if staking is enabled for user
     * @param disabled: flag that memorizes if staking is disabled
     **/
    public StakeableAsset(JSONObject jsonResponse, String method, String asset, String stakingAsset, Rewards rewards,
                          boolean onChain, boolean canStake, boolean canUnstake, MinimumAmount minimumAmount,
                          boolean enabledForUser, boolean disabled) {
        super(jsonResponse);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.enabledForUser = enabledForUser;
        this.disabled = disabled;
        unstaking = new ArrayList<>();
        staking = new ArrayList<>();
        lockup = new ArrayList<>();
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param enabledForUser: flag that memorizes if staking is enabled for user
     * @param disabled: flag that memorizes if staking is disabled
     **/
    public StakeableAsset(String method, String asset, String stakingAsset, Rewards rewards,
                          boolean onChain, boolean canStake, boolean canUnstake, MinimumAmount minimumAmount,
                          boolean enabledForUser, boolean disabled) {
        super(null);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.enabledForUser = enabledForUser;
        this.disabled = disabled;
        unstaking = new ArrayList<>();
        staking = new ArrayList<>();
        lockup = new ArrayList<>();
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param jsonResponse: base json response
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param enabledForUser: flag that memorizes if staking is enabled for user
     **/
    public StakeableAsset(JSONObject jsonResponse, String method, String asset, String stakingAsset, Rewards rewards,
                          boolean onChain, boolean canStake, boolean canUnstake, MinimumAmount minimumAmount,
                          boolean enabledForUser) {
        super(jsonResponse);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.enabledForUser = enabledForUser;
        disabled = false;
        unstaking = new ArrayList<>();
        staking = new ArrayList<>();
        lockup = new ArrayList<>();
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param enabledForUser: flag that memorizes if staking is enabled for user
     **/
    public StakeableAsset(String method, String asset, String stakingAsset, Rewards rewards, boolean onChain,
                          boolean canStake, boolean canUnstake, MinimumAmount minimumAmount, boolean enabledForUser) {
        super(null);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.enabledForUser = enabledForUser;
        disabled = false;
        unstaking = new ArrayList<>();
        staking = new ArrayList<>();
        lockup = new ArrayList<>();
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param jsonResponse: base json response
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param enabledForUser: flag that memorizes if staking is enabled for user
     * @param unstaking: list of {@link Lock} as unstaking list
     * @param staking: list of {@link Lock} as staking list
     * @param lockup: list of {@link Lock} as lockup list
     **/
    public StakeableAsset(JSONObject jsonResponse, String method, String asset, String stakingAsset, Rewards rewards,
                          boolean onChain, boolean canStake, boolean canUnstake, MinimumAmount minimumAmount,
                          boolean enabledForUser, ArrayList<Lock> unstaking, ArrayList<Lock> staking, ArrayList<Lock> lockup) {
        super(jsonResponse);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.enabledForUser = enabledForUser;
        this.unstaking = unstaking;
        this.staking = staking;
        this.lockup = lockup;
        disabled = false;
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param enabledForUser: flag that memorizes if staking is enabled for user
     * @param unstaking: list of {@link Lock} as unstaking list
     * @param staking: list of {@link Lock} as staking list
     * @param lockup: list of {@link Lock} as lockup list
     **/
    public StakeableAsset(String method, String asset, String stakingAsset, Rewards rewards, boolean onChain,
                          boolean canStake, boolean canUnstake, MinimumAmount minimumAmount, boolean enabledForUser,
                          ArrayList<Lock> unstaking, ArrayList<Lock> staking, ArrayList<Lock> lockup) {
        super(null);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.enabledForUser = enabledForUser;
        this.unstaking = unstaking;
        this.staking = staking;
        this.lockup = lockup;
        disabled = false;
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param jsonResponse: base json response
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param disabled: flag that memorizes if staking is disabled
     **/
    public StakeableAsset(JSONObject jsonResponse, String method, String asset, String stakingAsset, Rewards rewards,
                          boolean onChain, boolean canStake, boolean canUnstake, MinimumAmount minimumAmount,
                          Boolean disabled) {
        super(jsonResponse);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.disabled = disabled;
        enabledForUser = true;
        unstaking = new ArrayList<>();
        staking = new ArrayList<>();
        lockup = new ArrayList<>();
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param disabled: flag that memorizes if staking is disabled
     * @param unstaking: list of {@link Lock} as unstaking list
     * @param staking: list of {@link Lock} as staking list
     * @param lockup: list of {@link Lock} as lockup list
     **/
    public StakeableAsset(String method, String asset, String stakingAsset, Rewards rewards, boolean onChain,
                          boolean canStake, boolean canUnstake, MinimumAmount minimumAmount, Boolean disabled,
                          ArrayList<Lock> unstaking, ArrayList<Lock> staking, ArrayList<Lock> lockup) {
        super(null);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.disabled = disabled;
        this.unstaking = unstaking;
        this.staking = staking;
        this.lockup = lockup;
        enabledForUser = true;
    }

    /** Constructor to init a {@link StakeableAsset} object
     * @param jsonResponse: base json response
     * @param method: unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset: asset code/name
     * @param stakingAsset: staking asset code/name
     * @param rewards: describes the rewards earned while staking
     * @param onChain: flag that memorizes whether the staking operation is on-chain or not
     * @param canStake: flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake: flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param disabled: flag that memorizes if staking is disabled
     * @param unstaking: list of {@link Lock} as unstaking list
     * @param staking: list of {@link Lock} as staking list
     * @param lockup: list of {@link Lock} as lockup list
     **/
    public StakeableAsset(JSONObject jsonResponse, String method, String asset, String stakingAsset, Rewards rewards,
                          boolean onChain, boolean canStake, boolean canUnstake, MinimumAmount minimumAmount, Boolean disabled,
                          ArrayList<Lock> unstaking, ArrayList<Lock> staking, ArrayList<Lock> lockup) {
        super(jsonResponse);
        this.method = method;
        this.asset = asset;
        this.stakingAsset = stakingAsset;
        this.rewards = rewards;
        this.onChain = onChain;
        this.canStake = canStake;
        this.canUnstake = canUnstake;
        this.minimumAmount = minimumAmount;
        this.disabled = disabled;
        this.unstaking = unstaking;
        this.staking = staking;
        this.lockup = lockup;
        enabledForUser = true;
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
        JsonHelper jsonHelper = new JsonHelper(jsonResponse);
        enabledForUser = jsonHelper.getBoolean("enabled_for_user", true);
        disabled = jsonHelper.getBoolean("disabled", false);
        if(jsonResponse.has("lock")){
            jsonResponse = jsonResponse.getJSONObject("lock");
            unstaking = assembleLocksList(jsonResponse, "unstaking");
            staking = assembleLocksList(jsonResponse, "staking");
            lockup = assembleLocksList(jsonResponse, "lockup");
        }else{
            unstaking = new ArrayList<>();
            staking = new ArrayList<>();
            lockup = new ArrayList<>();
        }
    }

    /** Method to assemble a locks list
     * @param jsonLocks: json list obtained by response request
     * @return locks list as {@link ArrayList} of {@link Lock}
     * **/
    private ArrayList<Lock> assembleLocksList(JSONObject jsonLocks, String key){
        ArrayList<Lock> locksList = new ArrayList<>();
        JSONArray jsonList = jsonLocks.getJSONArray(key);
        for (int j = 0; j < jsonList.length(); j++)
            locksList.add(new Lock(jsonList.getJSONObject(j)));
        return locksList;
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

    public boolean isEnabledForUser() {
        return enabledForUser;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public ArrayList<Lock> getUnstaking() {
        return unstaking;
    }

    public ArrayList<Lock> getStaking() {
        return staking;
    }

    public ArrayList<Lock> getLockup() {
        return lockup;
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
                ", enabledForUser=" + enabledForUser +
                ", disabled=" + disabled +
                ", unstaking=" + unstaking +
                ", staking=" + staking +
                ", lockup=" + lockup +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    /**
     * The {@code Rewards} class is useful to describe the rewards earned while staking.
     * **/

    public static class Rewards {

        /**
         * {@code reward} is instance that memorizes reward earned while staking
         * **/
        private final double reward;

        /**
         * {@code type} is instance that memorizes reward type
         * **/
        private final String type;

        /** Constructor to init a {@link Rewards} object
         * @param reward: reward earned while staking
         * @param type: memorizes reward type
         **/
        public Rewards(double reward, String type) {
            this.reward = reward;
            this.type = type;
        }

        /** Constructor to init a {@link Rewards} object
         * @param jsonReward: rewards details in JSON format
         **/
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

    /**
     * The {@code MinimumAmount} class is useful to minimium amounts for staking/unstaking
     * **/

    public static class MinimumAmount {

        /**
         * {@code staking} is instance that memorizes staking value
         * **/
        private final double staking;

        /**
         * {@code unstaking} is instance that memorizes unstaking value
         * **/
        private final double unstaking;

        /** Constructor to init a {@link MinimumAmount} object
         * @param staking: staking value
         * @param unstaking: unstaking value
         **/
        public MinimumAmount(double staking, double unstaking) {
            this.staking = staking;
            this.unstaking = unstaking;
        }

        /** Constructor to init a {@link MinimumAmount} object
         * @param jsonAmount: minimum amount details in JSON format
         **/
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

    /**
     * The {@code Lock} class is useful to describe the locking periods and percentages for staking/unstaking operations
     * **/

    public static class Lock {

        /**
         * {@code days} is instance that memorizes days the funds are locked
         * **/
        private final int days;

        /**
         * {@code percentage} is instance that memorizes percentage of the funds that are locked (0 - 100)
         * **/
        private final double percentage;

        /** Constructor to init a {@link Lock} object
         * @param days: days the funds are locked
         * @param percentage: percentage of the funds that are locked (0 - 100)
         **/
        public Lock(int days, double percentage) {
            this.days = days;
            this.percentage = percentage;
        }

        /** Constructor to init a {@link Lock} object
         * @param jsonLock: lock details in JSON format
         **/
        public Lock(JSONObject jsonLock) {
            days = jsonLock.getInt("days");
            percentage = jsonLock.getDouble("percentage");
        }

        public int getDays() {
            return days;
        }

        public double getPercentage() {
            return percentage;
        }

        @Override
        public String toString() {
            return "Lock{" +
                    "days=" + days +
                    ", percentage=" + percentage +
                    '}';
        }

    }

}
