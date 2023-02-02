package com.tecknobit.krakenmanager.managers.privates.userstaking;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.krakenmanager.managers.privates.KrakenPrivateManager;
import com.tecknobit.krakenmanager.managers.privates.userstaking.records.StakeableAsset;
import com.tecknobit.krakenmanager.managers.privates.userstaking.records.StakingTransaction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.apimanager.formatters.ScientificNotationParser.sNotationParse;
import static com.tecknobit.krakenmanager.constants.EndpointsList.*;
import static com.tecknobit.krakenmanager.managers.KrakenManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code KrakenUserStakingManager} class is useful to manage all user staking endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking">
 * User Staking</a>
 **/
public class KrakenUserStakingManager extends KrakenPrivateManager {

    /**
     * Constructor to init a {@link KrakenUserStakingManager}
     *
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout:      custom timeout for request
     * @param apiKey:              api key of Kraken's platform
     * @param apiSign:             api sign of Kraken's platform
     **/
    public KrakenUserStakingManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserStakingManager}
     *
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param apiKey:              api key of Kraken's platform
     * @param apiSign:             api sign of Kraken's platform
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
     * No-any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
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
     *    Stake Asset</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Stake")
    public String stakeAsset(String asset, double amount, String method) throws Exception {
        return stakeAsset(asset, amount, method, LIBRARY_OBJECT);
    }

    /**
     * Request to stake an asset from your spot wallet. This operation requires an API key with Withdraw funds permission
     *
     * @param asset:  asset to stake (asset ID or altname)
     * @param amount: amount of the asset to stake
     * @param method: name of the staking option to use (refer to the Staking Assets endpoint for the correct method names for each asset)
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return stake result as {"format"} defines
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
     * Stake Asset</a>
     * @implSpec in this case {@link ReturnFormat#LIBRARY_OBJECT} will return the {@code "refid"} associated with the
     * stake made
     **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Stake")
    public <T> T stakeAsset(String asset, double amount, String method, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("amount", sNotationParse(8, amount));
        params.addParam("method", method);
        String stakeResponse = sendPostRequest(STAKE_ENDPOINT, params);
        switch (format) {
            case JSON:
                return (T) new JSONObject(stakeResponse);
            case LIBRARY_OBJECT:
                return (T) new JSONObject(stakeResponse).getJSONObject("result").getString("refid");
            default:
                return (T) stakeResponse;
        }
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
     *     Unstake Asset</a>
     * @return unstake result as {@link String}
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Unstake")
    public String unstakeAsset(String asset, double amount) throws Exception {
        return unstakeAsset(asset, amount, LIBRARY_OBJECT);
    }

    /**
     * Request to unstake an asset from your staking wallet. This operation requires an API key with Withdraw funds permission.
     *
     * @param asset:  asset to unstake (asset ID or altname). Must be a valid staking asset (e.g. XBT.M, XTZ.S, ADA.S)
     * @param amount: amount of the asset to unstake
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return unstake result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/unstake">
     * Unstake Asset</a>
     * @implSpec in this case {@link ReturnFormat#LIBRARY_OBJECT} will return the {@code "refid"} associated with the
     * stake made
     **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Unstake")
    public <T> T unstakeAsset(String asset, double amount, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("amount", sNotationParse(8, amount));
        String unstakeResponse = sendPostRequest(UNSTAKE_ENDPOINT, params);
        switch (format) {
            case JSON:
                return (T) new JSONObject(unstakeResponse);
            case LIBRARY_OBJECT:
                return (T) new JSONObject(unstakeResponse).getJSONObject("result").getString("refid");
            default:
                return (T) unstakeResponse;
        }
    }

    /** Request to return the list of assets that the user is able to stake. This operation requires an API key with
     * both Withdraw funds and Query funds permission<br>
     * No-any params required
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
     *     List of Stakeable Assets</a>
     * @return stakeable assets list as {@link ArrayList} of {@link StakeableAsset} custom object
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Staking/Assets")
    public ArrayList<StakeableAsset> getStakeableAssets() throws Exception {
        return getStakeableAssets(LIBRARY_OBJECT);
    }

    /**
     * Request to return the list of assets that the user is able to stake. This operation requires an API key with
     * both Withdraw funds and Query funds permission
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return stakeable assets list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingAssetInfo">
     * List of Stakeable Assets</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Staking/Assets")
    public <T> T getStakeableAssets(ReturnFormat format) throws Exception {
        String assetsResponse = sendPostRequest(ASSETS_STAKEABLE_ENDPOINT, null);
        switch (format) {
            case JSON:
                return (T) new JSONObject(assetsResponse);
            case LIBRARY_OBJECT:
                ArrayList<StakeableAsset> stakeableAssets = new ArrayList<>();
                JSONArray jAssets = new JSONObject(assetsResponse).getJSONArray("result");
                for (int j = 0; j < jAssets.length(); j++)
                    stakeableAssets.add(new StakeableAsset(jAssets.getJSONObject(j)));
                return (T) stakeableAssets;
            default:
                return (T) assetsResponse;
        }
    }

    /** Request to return the list of pending staking transactions. Once resolved, these transactions will appear on the
     * List of Staking Transactions endpoint. <br>
     * No-any params required
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
     *     Get Pending Staking Transactions</a>
     * @return list of pending staking transactions as {@link ArrayList} of {@link StakingTransaction} custom object
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Staking/Pending")
    public ArrayList<StakingTransaction> getPendingStakingTransactions() throws Exception {
        return getPendingStakingTransactions(LIBRARY_OBJECT);
    }

    /**
     * Request to return the list of pending staking transactions. Once resolved, these transactions will appear on the
     * List of Staking Transactions endpoint.
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return list of pending staking transactions as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingPendingDeposits">
     * Get Pending Staking Transactions</a>
     **/
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Staking/Pending")
    public <T> T getPendingStakingTransactions(ReturnFormat format) throws Exception {
        return returnTransactionsList(sendPostRequest(PENDING_STAKING_TRANSACTIONS_ENDPOINT, null), format);
    }

    /** Request to return the list of 1000 recent staking transactions from past 90 days <br>
     * No-any params required
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
     *     List of Staking Transactions</a>
     * @return list staking transactions as {@link ArrayList} of {@link StakingTransaction} custom object
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Staking/Transactions")
    public ArrayList<StakingTransaction> getStakingTransactions() throws Exception {
        return getStakingTransactions(LIBRARY_OBJECT);
    }

    /**
     * Request to return the list of 1000 recent staking transactions from past 90 days
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return list staking transactions as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Staking/operation/getStakingTransactions">
     * List of Staking Transactions</a>
     **/
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Staking/Transactions")
    public <T> T getStakingTransactions(ReturnFormat format) throws Exception {
        return returnTransactionsList(sendPostRequest(STAKING_TRANSACTIONS_ENDPOINT, null), format);
    }

    /**
     * Method to assemble a transactions list
     *
     * @param transactionsListResponse: transactions list to format
     * @param format:                   return type formatter -> {@link ReturnFormat}
     * @return transactions list as {"format"} defines
     **/
    @Returner
    private <T> T returnTransactionsList(String transactionsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(transactionsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<StakingTransaction> transactions = new ArrayList<>();
                JSONArray jTransactions = new JSONObject(transactionsListResponse).getJSONArray("result");
                for (int j = 0; j < jTransactions.length(); j++)
                    transactions.add(new StakingTransaction(jTransactions.getJSONObject(j)));
                return (T) transactions;
            default:
                return (T) transactionsListResponse;
        }
    }

}
