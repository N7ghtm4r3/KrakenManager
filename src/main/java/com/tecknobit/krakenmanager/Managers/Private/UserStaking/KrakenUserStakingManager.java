package com.tecknobit.krakenmanager.Managers.Private.UserStaking;

import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records.StakeableAsset;
import com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records.StakingTransaction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.Tools.Formatters.ScientificNotationParser.sNotationParse;
import static com.tecknobit.krakenmanager.Constants.EndpointsList.*;

/**
 *  The {@code KrakenUserStakingManager} class is useful to manage all user staking endpoints
 *  @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking">
 *      https://docs.kraken.com/rest/#tag/User-Staking</a>
 *  @author N7ghtm4r3 - Tecknobit
 * **/

public class KrakenUserStakingManager extends KrakenPrivateManager {

    /** Constructor to init a {@link KrakenUserStakingManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserStakingManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenUserStakingManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserStakingManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenUserStakingManager}
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserStakingManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenUserStakingManager}
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserStakingManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    /** Request to stake an asset from your spot wallet. This operation requires an API key with Withdraw funds permission
     * @param asset: asset to stake (asset ID or altname)
     * @param amount: amount of the asset to stake
     * @param method: name of the staking option to use (refer to the Staking Assets endpoint for the correct method names for each asset)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/stake">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/stake</a>
     * @return stake result as {@link String}
     * **/
    public String stakeAsset(String asset, double amount, String method) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("amount", sNotationParse(8, amount));
        params.addParam("method", method);
        return sendPostRequest(STAKE_ENDPOINT, params);
    }

    /** Request to stake an asset from your spot wallet. This operation requires an API key with Withdraw funds permission
     * @param asset: asset to stake (asset ID or altname)
     * @param amount: amount of the asset to stake
     * @param method: name of the staking option to use (refer to the Staking Assets endpoint for the correct method names for each asset)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/stake">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/stake</a>
     * @return stake result as {@link JSONObject}
     * **/
    public JSONObject stakeAssetJSON(String asset, double amount, String method) throws Exception {
        return new JSONObject(stakeAsset(asset, amount, method));
    }

    /** Request to stake an asset from your spot wallet. This operation requires an API key with Withdraw funds permission
     * @param asset: asset to stake (asset ID or altname)
     * @param amount: amount of the asset to stake
     * @param method: name of the staking option to use (refer to the Staking Assets endpoint for the correct method names for each asset)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/stake">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/stake</a>
     * @return stake result identifier as {@link String}
     * **/
    public String stakeAssetAndGetId(String asset, double amount, String method) throws Exception {
        return stakeAssetJSON(asset, amount, method).getJSONObject("result").getString("refid");
    }

    /** Request to unstake an asset from your staking wallet. This operation requires an API key with Withdraw funds permission.
     * @param asset: asset to unstake (asset ID or altname). Must be a valid staking asset (e.g. XBT.M, XTZ.S, ADA.S)
     * @param amount: amount of the asset to unstake
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake</a>
     * @return unstake result as {@link String}
     * **/
    public String unstakeAsset(String asset, double amount) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("amount", sNotationParse(8, amount));
        return sendPostRequest(UNSTAKE_ENDPOINT, params);
    }

    /** Request to unstake an asset from your staking wallet. This operation requires an API key with Withdraw funds permission.
     * @param asset: asset to unstake (asset ID or altname). Must be a valid staking asset (e.g. XBT.M, XTZ.S, ADA.S)
     * @param amount: amount of the asset to unstake
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake</a>
     * @return unstake result as {@link JSONObject}
     * **/
    public JSONObject unstakeAssetJSON(String asset, double amount) throws Exception {
        return new JSONObject(unstakeAsset(asset, amount));
    }

    /** Request to unstake an asset from your staking wallet. This operation requires an API key with Withdraw funds permission.
     * @param asset: asset to unstake (asset ID or altname). Must be a valid staking asset (e.g. XBT.M, XTZ.S, ADA.S)
     * @param amount: amount of the asset to unstake
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake</a>
     * @return unstake result identifier as {@link JSONObject}
     * **/
    public String unstakeAssetAndGetId(String asset, double amount) throws Exception {
        return unstakeAssetJSON(asset, amount).getJSONObject("result").getString("refid");
    }

    /** Request to return the list of assets that the user is able to stake. This operation requires an API key with
     * both Withdraw funds and Query funds permission<br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo</a>
     * @return stakeable assets list as {@link String}
     * **/
    public String getStakeableAssets() throws Exception {
        return sendPostRequest(ASSETS_STAKEABLE_ENDPOINT, null);
    }

    /** Request to return the list of assets that the user is able to stake. This operation requires an API key with
     * both Withdraw funds and Query funds permission<br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo</a>
     * @return stakeable assets list as {@link JSONObject}
     * **/
    public JSONObject getStakeableAssetsJSON() throws Exception {
        return new JSONObject(getStakeableAssets());
    }

    /** Request to return the list of assets that the user is able to stake. This operation requires an API key with
     * both Withdraw funds and Query funds permission<br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo</a>
     * @return stakeable assets list as {@link ArrayList} of {@link StakeableAsset} custom object
     * **/
    public ArrayList<StakeableAsset> getStakeableAssetsList() throws Exception {
        ArrayList<StakeableAsset> stakeableAssets = new ArrayList<>();
        JSONArray assets = getStakeableAssetsJSON().getJSONArray("result");
        for (int j = 0; j < assets.length(); j++)
            stakeableAssets.add(new StakeableAsset(assets.getJSONObject(j)));
        return stakeableAssets;
    }

    /** Request to return the list of pending staking transactions. Once resolved, these transactions will appear on the
     * List of Staking Transactions endpoint. <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits</a>
     * @return list of pending staking transactions as {@link String}
     * **/
    public String getPendingStakingTransactions() throws Exception {
        return sendPostRequest(PENDING_STAKING_TRANSACTIONS_ENDPOINT, null);
    }

    /** Request to return the list of pending staking transactions. Once resolved, these transactions will appear on the
     * List of Staking Transactions endpoint. <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits</a>
     * @return list of pending staking transactions as {@link JSONObject}
     * **/
    public JSONObject getPendingStakingTransactionsJSON() throws Exception {
        return new JSONObject(getPendingStakingTransactions());
    }

    /** Request to return the list of pending staking transactions. Once resolved, these transactions will appear on the
     * List of Staking Transactions endpoint. <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits</a>
     * @return list of pending staking transactions as {@link ArrayList} of {@link StakingTransaction} custom object
     * **/
    public ArrayList<StakingTransaction> getPendingStakingTransactionsList() throws Exception {
        return assembleTransactionsList(getPendingStakingTransactionsJSON());
    }

    /** Request to return the list of 1000 recent staking transactions from past 90 days <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions</a>
     * @return list staking transactions as {@link String}
     * **/
    public String getStakingTransactions() throws Exception {
        return sendPostRequest(STAKING_TRANSACTIONS_ENDPOINT, null);
    }

    /** Request to return the list of 1000 recent staking transactions from past 90 days <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions</a>
     * @return list staking transactions as {@link JSONObject}
     * **/
    public JSONObject getStakingTransactionsJSON() throws Exception {
        return new JSONObject(getStakingTransactions());
    }

    /** Request to return the list of 1000 recent staking transactions from past 90 days <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions</a>
     * @return list staking transactions as {@link ArrayList} of {@link StakingTransaction} custom object
     * **/
    public ArrayList<StakingTransaction> getStakingTransactionsList() throws Exception {
        return assembleTransactionsList(getStakingTransactionsJSON());
    }

    /** Method to assemble a staking transactions list
     * @param jsonList: jsonObject obtained by response request
     * @return transactions list as {@link ArrayList} of {@link StakingTransaction}
     * **/
    private ArrayList<StakingTransaction> assembleTransactionsList(JSONObject jsonList){
        ArrayList<StakingTransaction> transactions = new ArrayList<>();
        JSONArray jsonTransactions = jsonList.getJSONArray("result");
        for (int j = 0; j < jsonTransactions.length(); j++)
            transactions.add(new StakingTransaction(jsonTransactions.getJSONObject(j)));
        return transactions;
    }

}
