package com.tecknobit.krakenmanager.managers.privates.userstaking.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code StakeableAsset} class is useful to format a stakeable asset
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo">
 * https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo</a>
 **/
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
        this(null, method, asset, stakingAsset, rewards, onChain, canStake, canUnstake, minimumAmount, enabledForUser,
                disabled);
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
        this(null, method, asset, stakingAsset, rewards, onChain, canStake, canUnstake, minimumAmount, enabledForUser);
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

    /**
     * Constructor to init a {@link StakeableAsset} object
     *
     * @param method:        unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset:         asset code/name
     * @param stakingAsset:  staking asset code/name
     * @param rewards:       describes the rewards earned while staking
     * @param onChain:       flag that memorizes whether the staking operation is on-chain or not
     * @param canStake:      flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake:    flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param disabled:      flag that memorizes if staking is disabled
     **/
    public StakeableAsset(String method, String asset, String stakingAsset, Rewards rewards, boolean onChain,
                          boolean canStake, boolean canUnstake, boolean disabled, MinimumAmount minimumAmount) {
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
        enabledForUser = true;
        unstaking = new ArrayList<>();
        staking = new ArrayList<>();
        lockup = new ArrayList<>();
    }

    /**
     * Constructor to init a {@link StakeableAsset} object
     * @param method:        unique ID of the staking option (used in Stake/Unstake operations)
     * @param asset:         asset code/name
     * @param stakingAsset:  staking asset code/name
     * @param rewards:       describes the rewards earned while staking
     * @param onChain:       flag that memorizes whether the staking operation is on-chain or not
     * @param canStake:      flag that memorizes whether the user will be able to stake this asset
     * @param canUnstake:    flag that memorizes whether the user will be able to unstake this asset
     * @param minimumAmount: minimium amounts for staking/unstaking
     * @param disabled:      flag that memorizes if staking is disabled
     * @param unstaking:     list of {@link Lock} as unstaking list
     * @param staking:       list of {@link Lock} as staking list
     * @param lockup:        list of {@link Lock} as lockup list
     **/
    public StakeableAsset(String method, String asset, String stakingAsset, Rewards rewards, boolean onChain,
                          boolean canStake, boolean canUnstake, boolean disabled, MinimumAmount minimumAmount,
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

    /**
     * Constructor to init a {@link StakeableAsset} object
     * @param jsonResponse : base json response
     **/
    public StakeableAsset(JSONObject jsonResponse) {
        super(jsonResponse);
        method = result.getString("method");
        asset = result.getString("asset");
        stakingAsset = result.getString("staking_asset");
        rewards = new Rewards(result.getJSONObject("rewards", new JSONObject()));
        onChain = result.getBoolean("on_chain");
        canStake = result.getBoolean("can_stake");
        canUnstake = result.getBoolean("can_unstake");
        minimumAmount = new MinimumAmount(result.getJSONObject("minimum_amount", new JSONObject()));
        enabledForUser = result.getBoolean("enabled_for_user", true);
        disabled = result.getBoolean("disabled", false);
        unstaking = returnLocksList(result.getJSONObject("lock", new JSONObject()), "unstaking");
        staking = returnLocksList(result.getJSONObject("lock", new JSONObject()), "staking");
        lockup = returnLocksList(result.getJSONObject("lock", new JSONObject()), "lockup");
    }

    /**
     * Method to assemble a locks list
     *
     * @param jsonLocks: json list obtained by response request
     * @return locks list as {@link ArrayList} of {@link Lock}
     **/
    @Returner
    private ArrayList<Lock> returnLocksList(JSONObject jsonLocks, String key) {
        ArrayList<Lock> locksList = new ArrayList<>();
        JSONArray jsonList = jsonLocks.getJSONArray(key);
        for (int j = 0; j < jsonList.length(); j++)
            locksList.add(new Lock(jsonList.getJSONObject(j)));
        return locksList;
    }

    /**
     * Method to get {@link #method} instance <br>
     * Any params required
     *
     * @return {@link #method} instance as {@link String}
     **/
    public String getMethod() {
        return method;
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
     * Method to get {@link #stakingAsset} instance <br>
     * Any params required
     *
     * @return {@link #stakingAsset} instance as {@link String}
     **/
    public String getStakingAsset() {
        return stakingAsset;
    }

    /**
     * Method to get {@link #rewards} instance <br>
     * Any params required
     *
     * @return {@link #rewards} instance as {@link Rewards}
     **/
    public Rewards getRewards() {
        return rewards;
    }

    /**
     * Method to get {@link #onChain} instance <br>
     * Any params required
     *
     * @return {@link #onChain} instance as boolean
     **/
    public boolean isOnChain() {
        return onChain;
    }

    /**
     * Method to get {@link #canStake} instance <br>
     * Any params required
     *
     * @return {@link #canStake} instance as boolean
     **/
    public boolean canStake() {
        return canStake;
    }

    /**
     * Method to get {@link #canUnstake} instance <br>
     * Any params required
     *
     * @return {@link #canUnstake} instance as boolean
     **/
    public boolean canUnstake() {
        return canUnstake;
    }

    /**
     * Method to get {@link #minimumAmount} instance <br>
     * Any params required
     *
     * @return {@link #minimumAmount} instance as {@link MinimumAmount}
     **/
    public MinimumAmount getMinimumAmount() {
        return minimumAmount;
    }

    /**
     * Method to get {@link #enabledForUser} instance <br>
     * Any params required
     *
     * @return {@link #enabledForUser} instance as boolean
     **/
    public boolean isEnabledForUser() {
        return enabledForUser;
    }

    /**
     * Method to get {@link #disabled} instance <br>
     * Any params required
     *
     * @return {@link #disabled} instance as boolean
     **/
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Method to get {@link #unstaking} instance <br>
     * Any params required
     *
     * @return {@link #unstaking} instance as {@link ArrayList} of {@link Lock}
     **/
    public ArrayList<Lock> getUnstaking() {
        return unstaking;
    }

    /**
     * Method to get {@link #staking} instance <br>
     * Any params required
     *
     * @return {@link #staking} instance as {@link ArrayList} of {@link Lock}
     **/
    public ArrayList<Lock> getStaking() {
        return staking;
    }

    /**
     * Method to get {@link #lockup} instance <br>
     * Any params required
     *
     * @return {@link #lockup} instance as {@link ArrayList} of {@link Lock}
     **/
    public ArrayList<Lock> getLockup() {
        return lockup;
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

    /**
     * The {@code Rewards} class is useful to describe the rewards earned while staking.
     **/
    public static class Rewards {

        /**
         * {@code reward} is instance that memorizes reward earned while staking
         * **/
        private final double reward;

        /**
         * {@code type} is instance that memorizes reward type
         * **/
        private final String type;

        /**
         * Constructor to init a {@link Rewards} object
         *
         * @param reward: reward earned while staking
         * @param type:   memorizes reward type
         **/
        public Rewards(double reward, String type) {
            this.reward = reward;
            this.type = type;
        }

        /**
         * Constructor to init a {@link Rewards} object
         *
         * @param jReward: rewards details in {@code "JSON"} format
         **/
        public Rewards(JSONObject jReward) {
            JsonHelper hReward = new JsonHelper(jReward);
            reward = hReward.getDouble("reward", 0);
            type = hReward.getString("type");
        }

        /**
         * Method to get {@link #reward} instance <br>
         * Any params required
         *
         * @return {@link #reward} instance as double
         **/
        public double getReward() {
            return reward;
        }

        /**
         * Method to get {@link #reward} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #reward} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getReward(int decimals) {
            return roundValue(reward, decimals);
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

        /**
         * Constructor to init a {@link MinimumAmount} object
         *
         * @param staking:   staking value
         * @param unstaking: unstaking value
         **/
        public MinimumAmount(double staking, double unstaking) {
            this.staking = staking;
            this.unstaking = unstaking;
        }

        /**
         * Constructor to init a {@link MinimumAmount} object
         *
         * @param jAmount: minimum amount details in {@code "JSON"} format
         **/
        public MinimumAmount(JSONObject jAmount) {
            JsonHelper hAmount = new JsonHelper(jAmount);
            staking = hAmount.getDouble("staking", 0);
            unstaking = hAmount.getDouble("unstaking", 0);
        }

        /**
         * Method to get {@link #staking} instance <br>
         * Any params required
         *
         * @return {@link #staking} instance as double
         **/
        public double getStaking() {
            return staking;
        }

        /**
         * Method to get {@link #staking} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #staking} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getStaking(int decimals) {
            return roundValue(staking, decimals);
        }

        /**
         * Method to get {@link #unstaking} instance <br>
         * Any params required
         *
         * @return {@link #unstaking} instance as double
         **/
        public double getUnstaking() {
            return unstaking;
        }

        /**
         * Method to get {@link #unstaking} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #unstaking} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getUnstaking(int decimals) {
            return roundValue(unstaking, decimals);
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

        /**
         * Constructor to init a {@link Lock} object
         *
         * @param days:       days the funds are locked
         * @param percentage: percentage of the funds that are locked (0 - 100)
         **/
        public Lock(int days, double percentage) {
            this.days = days;
            this.percentage = percentage;
        }

        /**
         * Constructor to init a {@link Lock} object
         *
         * @param jLock: lock details in {@code "JSON"} format
         **/
        public Lock(JSONObject jLock) {
            JsonHelper hLock = new JsonHelper(jLock);
            days = hLock.getInt("days", 0);
            percentage = hLock.getDouble("percentage", 0);
        }

        /**
         * Method to get {@link #days} instance <br>
         * Any params required
         *
         * @return {@link #days} instance as int
         **/
        public int getDays() {
            return days;
        }

        /**
         * Method to get {@link #percentage} instance <br>
         * Any params required
         *
         * @return {@link #percentage} instance as double
         **/
        public double getPercentage() {
            return percentage;
        }

        /**
         * Method to get {@link #percentage} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #percentage} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getPercentage(int decimals) {
            return roundValue(percentage, decimals);
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

}
