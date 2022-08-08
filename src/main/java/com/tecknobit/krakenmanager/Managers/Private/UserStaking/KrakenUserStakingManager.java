package com.tecknobit.krakenmanager.Managers.Private.UserStaking;

import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records.StakeableAsset;
import com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records.PendingStakingTransaction;
import com.tecknobit.krakenmanager.Managers.Private.UserStaking.Records.StakingTransaction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.Tools.Formatters.ScientificNotationParser.sNotationParse;
import static com.tecknobit.krakenmanager.Constants.EndpointsList.*;

public class KrakenUserStakingManager extends KrakenPrivateManager {

    public KrakenUserStakingManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    public KrakenUserStakingManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    public KrakenUserStakingManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    public KrakenUserStakingManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    public String stakeAsset(String asset, double amount, String method) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("amount", sNotationParse(8, amount));
        params.addParam("method", method);
        return sendPostRequest(STAKE_ENDPOINT, params);
    }

    public JSONObject stakeAssetJSON(String asset, double amount, String method) throws Exception {
        return new JSONObject(stakeAsset(asset, amount, method));
    }

    public String stakeAssetAndGetId(String asset, double amount, String method) throws Exception {
        return stakeAssetJSON(asset, amount, method).getJSONObject("result").getString("refid");
    }

    public String unstakeAsset(String asset, double amount) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("amount", sNotationParse(8, amount));
        return sendPostRequest(UNSTAKE_ENDPOINT, params);
    }

    public JSONObject unstakeAssetJSON(String asset, double amount) throws Exception {
        return new JSONObject(unstakeAsset(asset, amount));
    }

    public String unstakeAssetAndGetId(String asset, double amount) throws Exception {
        return unstakeAssetJSON(asset, amount).getJSONObject("result").getString("refid");
    }

    public String getStakeableAssets() throws Exception {
        return sendPostRequest(ASSETS_STAKEABLE_ENDPOINT, null);
    }

    public JSONObject getStakeableAssetsJSON() throws Exception {
        return new JSONObject(getStakeableAssets());
    }

    public ArrayList<StakeableAsset> getStakeableAssetsList() throws Exception {
        ArrayList<StakeableAsset> stakeableAssets = new ArrayList<>();
        JSONArray assets = getStakeableAssetsJSON().getJSONArray("result");
        for (int j = 0; j < assets.length(); j++)
            stakeableAssets.add(new StakeableAsset(assets.getJSONObject(j)));
        return stakeableAssets;
    }

    public String getPendingStakingTransactions() throws Exception {
        return sendPostRequest(PENDING_STAKING_TRANSACTIONS_ENDPOINT, null);
    }

    public JSONObject getPendingStakingTransactionsJSON() throws Exception {
        return new JSONObject(getPendingStakingTransactions());
    }

    public ArrayList<PendingStakingTransaction> getPendingStakingTransactionsList() throws Exception {
        ArrayList<PendingStakingTransaction> pendingTransactions = new ArrayList<>();
        JSONArray transactions = getPendingStakingTransactionsJSON().getJSONArray("result");
        for (int j = 0; j < transactions.length(); j++)
            pendingTransactions.add(new PendingStakingTransaction(transactions.getJSONObject(j)));
        return pendingTransactions;
    }

    public String getStakingTransactions() throws Exception {
        return sendPostRequest(STAKING_TRANSACTIONS_ENDPOINT, null);
    }

    public JSONObject getStakingTransactionsJSON() throws Exception {
        return new JSONObject(getStakingTransactions());
    }

    public ArrayList<StakingTransaction> getStakingTransactionsList() throws Exception {
        ArrayList<StakingTransaction> pendingTransactions = new ArrayList<>();
        JSONArray transactions = getStakingTransactionsJSON().getJSONArray("result");
        for (int j = 0; j < transactions.length(); j++)
            pendingTransactions.add(new StakingTransaction(transactions.getJSONObject(j)));
        return pendingTransactions;
    }

}
