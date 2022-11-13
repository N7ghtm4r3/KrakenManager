package com.tecknobit.krakenmanager.managers.privates.userstaking;

import com.tecknobit.krakenmanager.managers.privates.KrakenPrivateManager;
import com.tecknobit.krakenmanager.managers.privates.userstaking.records.StakeableAsset;
import com.tecknobit.krakenmanager.managers.privates.userstaking.records.StakingTransaction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.ScientificNotationParser.sNotationParse;
import static com.tecknobit.krakenmanager.constants.EndpointsList.*;

/**
 * The {@code KrakenUserStakingManager} class is useful to manage all user staking endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking">
 * https://docs.kraken.com/rest/#tag/User-Staking</a>
 **/
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

    /**
     * Constructor to init a {@link KrakenUserStakingManager}
     *
     * @param requestTimeout: custom timeout for request
     * @param apiKey:         api key of Kraken's platform
     * @param apiSign:        api sign of Kraken's platform
     **/
    public KrakenUserStakingManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserStakingManager}
     *
     * @param apiKey:  api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserStakingManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserStakingManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructur
     * @apiNote this constructor is useful to instantiate a new {@link KrakenPrivateManager}'s manager without re-insert
     * the credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        KrakenPrivateManager firstManager = new KrakenPrivateManager("apiKey", "apiSign");
     *        //You don't need to insert all credentials to make manager work
     *        KrakenPrivateManager secondManager = new KrakenPrivateManager(); //same credentials used
     *     }
     * </pre>
     **/
    public KrakenUserStakingManager() {
        super();
    }

    /**
     * Request to stake an asset from your spot wallet. This operation requires an API key with Withdraw funds permission
     *
     * @param asset:  asset to stake (asset ID or altname)
     * @param amount: amount of the asset to stake
     * @param method: name of the staking option to use (refer to the Staking Assets endpoint for the correct method names for each asset)
     * @return stake result as {@link String}
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/stake">
     * https://docs.kraken.com/rest/#tag/User-Staking/operation/stake</a>
     **/
    public String stakeAsset(String asset, double amount, String method) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("amount", sNotationParse(8, amount));
        params.addParam("method", method);
        return sendPostRequest(STAKE_ENDPOINT, params);
    }

    /**
     * Request to stake an asset from your spot wallet. This operation requires an API key with Withdraw funds permission
     *
     * @param asset:  asset to stake (asset ID or altname)
     * @param amount: amount of the asset to stake
     * @param method: name of the staking option to use (refer to the Staking Assets endpoint for the correct method names for each asset)
     * @return stake result as {@link JSONObject}
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/stake">
     * https://docs.kraken.com/rest/#tag/User-Staking/operation/stake</a>
     **/
    public JSONObject stakeAssetJSON(String asset, double amount, String method) throws Exception {
        return new JSONObject(stakeAsset(asset, amount, method));
    }

    /** Request to stake an asset from your spot wallet. This operation requires an API key with Withdraw funds permission
     * @param asset: asset to stake (asset ID or altname)
     * @param amount: amount of the asset to stake
     * @param method: name of the staking option to use (refer to the Staking Assets endpoint for the correct method names for each asset)
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/stake">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/stake</a>
     * @return stake result identifier as {@link String}
     * **/
    public String stakeAssetAndGetId(String asset, double amount, String method) throws Exception {
        return stakeAssetJSON(asset, amount, method).getJSONObject("result").getString("refid");
    }

    /** Request to unstake an asset from your staking wallet. This operation requires an API key with Withdraw funds permission.
     * @param asset: asset to unstake (asset ID or altname). Must be a valid staking asset (e.g. XBT.M, XTZ.S, ADA.S)
     * @param amount: amount of the asset to unstake
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake">
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
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake</a>
     * @return unstake result as {@link JSONObject}
     * **/
    public JSONObject unstakeAssetJSON(String asset, double amount) throws Exception {
        return new JSONObject(unstakeAsset(asset, amount));
    }

    /** Request to unstake an asset from your staking wallet. This operation requires an API key with Withdraw funds permission.
     * @param asset: asset to unstake (asset ID or altname). Must be a valid staking asset (e.g. XBT.M, XTZ.S, ADA.S)
     * @param amount: amount of the asset to unstake
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake</a>
     * @return unstake result identifier as {@link JSONObject}
     * **/
    public String unstakeAssetAndGetId(String asset, double amount) throws Exception {
        return unstakeAssetJSON(asset, amount).getJSONObject("result").getString("refid");
    }

    /** Request to return the list of assets that the user is able to stake. This operation requires an API key with
     * both Withdraw funds and Query funds permission<br>
     * Any params required
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo</a>
     * @return stakeable assets list as {@link String}
     * **/
    public String getStakeableAssets() throws Exception {
        return sendPostRequest(ASSETS_STAKEABLE_ENDPOINT, null);
    }

    /** Request to return the list of assets that the user is able to stake. This operation requires an API key with
     * both Withdraw funds and Query funds permission<br>
     * Any params required
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo</a>
     * @return stakeable assets list as {@link JSONObject}
     * **/
    public JSONObject getStakeableAssetsJSON() throws Exception {
        return new JSONObject(getStakeableAssets());
    }

    /** Request to return the list of assets that the user is able to stake. This operation requires an API key with
     * both Withdraw funds and Query funds permission<br>
     * Any params required
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo">
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
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits</a>
     * @return list of pending staking transactions as {@link String}
     * **/
    public String getPendingStakingTransactions() throws Exception {
        return sendPostRequest(PENDING_STAKING_TRANSACTIONS_ENDPOINT, null);
    }

    /** Request to return the list of pending staking transactions. Once resolved, these transactions will appear on the
     * List of Staking Transactions endpoint. <br>
     * Any params required
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits</a>
     * @return list of pending staking transactions as {@link JSONObject}
     * **/
    public JSONObject getPendingStakingTransactionsJSON() throws Exception {
        return new JSONObject(getPendingStakingTransactions());
    }

    /** Request to return the list of pending staking transactions. Once resolved, these transactions will appear on the
     * List of Staking Transactions endpoint. <br>
     * Any params required
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits</a>
     * @return list of pending staking transactions as {@link ArrayList} of {@link StakingTransaction} custom object
     * **/
    public ArrayList<StakingTransaction> getPendingStakingTransactionsList() throws Exception {
        return assembleTransactionsList(getPendingStakingTransactionsJSON());
    }

    /** Request to return the list of 1000 recent staking transactions from past 90 days <br>
     * Any params required
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions</a>
     * @return list staking transactions as {@link String}
     * **/
    public String getStakingTransactions() throws Exception {
        return sendPostRequest(STAKING_TRANSACTIONS_ENDPOINT, null);
    }

    /** Request to return the list of 1000 recent staking transactions from past 90 days <br>
     * Any params required
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions</a>
     * @return list staking transactions as {@link JSONObject}
     * **/
    public JSONObject getStakingTransactionsJSON() throws Exception {
        return new JSONObject(getStakingTransactions());
    }

    /** Request to return the list of 1000 recent staking transactions from past 90 days <br>
     * Any params required
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions">
     *     https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions</a>
     * @return list staking transactions as {@link ArrayList} of {@link StakingTransaction} custom object
     * **/
    public ArrayList<StakingTransaction> getStakingTransactionsList() throws Exception {
        return assembleTransactionsList(getStakingTransactionsJSON());
    }

    /** Method to assemble a staking transactions list
     * @param jsonList: obtained by response request
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
