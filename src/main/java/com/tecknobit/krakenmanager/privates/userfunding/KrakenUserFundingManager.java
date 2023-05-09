package com.tecknobit.krakenmanager.privates.userfunding;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.krakenmanager.privates.KrakenPrivateManager;
import com.tecknobit.krakenmanager.privates.userfunding.records.DepositAddress;
import com.tecknobit.krakenmanager.privates.userfunding.records.DepositMethod;
import com.tecknobit.krakenmanager.privates.userfunding.records.OperationStatus;
import com.tecknobit.krakenmanager.privates.userfunding.records.WithdrawInformation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.apimanager.formatters.ScientificNotationParser.sNotationParse;
import static com.tecknobit.krakenmanager.KrakenManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code KrakenUserFundingManager} class is useful to manage all user funding endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding">
 * User Funding</a>
 **/
public class KrakenUserFundingManager extends KrakenPrivateManager {

    /**
     * {@code DEPOSIT_METHODS_ENDPOINT} is constant for DEPOSIT_METHODS_ENDPOINT's endpoint
     **/
    public static final String DEPOSIT_METHODS_ENDPOINT = "DepositMethods";

    /**
     * {@code DEPOSIT_ADDRESSES_ENDPOINT} is constant for DEPOSIT_ADDRESSES_ENDPOINT's endpoint
     **/
    public static final String DEPOSIT_ADDRESSES_ENDPOINT = "DepositAddresses";

    /**
     * {@code DEPOSIT_STATUS_ENDPOINT} is constant for DEPOSIT_STATUS_ENDPOINT's endpoint
     **/
    public static final String DEPOSIT_STATUS_ENDPOINT = "DepositStatus";

    /**
     * {@code GET_WITHDRAWAL_INFORMATION_ENDPOINT} is constant for GET_WITHDRAWAL_INFORMATION_ENDPOINT's endpoint
     **/
    public static final String GET_WITHDRAWAL_INFORMATION_ENDPOINT = "WithdrawInfo";

    /**
     * {@code MAKE_WITHDRAW_ENDPOINT} is constant for MAKE_WITHDRAW_ENDPOINT's endpoint
     **/
    public static final String MAKE_WITHDRAW_ENDPOINT = "Withdraw";

    /**
     * {@code WITHDRAW_STATUS_ENDPOINT} is constant for WITHDRAW_STATUS_ENDPOINT's endpoint
     **/
    public static final String WITHDRAW_STATUS_ENDPOINT = "WithdrawStatus";

    /**
     * {@code CANCEL_WITHDRAW_ENDPOINT} is constant for CANCEL_WITHDRAW_ENDPOINT's endpoint
     **/
    public static final String CANCEL_WITHDRAW_ENDPOINT = "WithdrawCancel";

    /**
     * {@code WALLET_TRANSFER_ENDPOINT} is constant for WALLET_TRANSFER_ENDPOINT's endpoint
     **/
    public static final String WALLET_TRANSFER_ENDPOINT = "WalletTransfer";

    /**
     * {@code SPOT_WALLET_SOURCE} is constant for spot wallet source
     **/
    public static final String SPOT_WALLET_SOURCE = "Spot Wallet";

    /**
     * {@code FUTURES_WALLET_SOURCE} is constant for futures wallet source
     **/
    public static final String FUTURES_WALLET_SOURCE = "Futures Wallet";

    /** Constructor to init a {@link KrakenUserFundingManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserFundingManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenUserFundingManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserFundingManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserFundingManager}
     *
     * @param requestTimeout: custom timeout for request
     * @param apiKey:         api key of Kraken's platform
     * @param apiSign:        api sign of Kraken's platform
     **/
    public KrakenUserFundingManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserFundingManager}
     *
     * @param apiKey:  api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserFundingManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserFundingManager} <br>
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
    public KrakenUserFundingManager() {
        super();
    }

    /** Request to retrieve methods available for depositing a particular asset
     * @param asset: asset being deposited
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods">
     *     Get Deposit Methods</a>
     * @return methods available as {@link ArrayList} of {@link DepositMethod} custom object
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/DepositMethods")
    public ArrayList<DepositMethod> getDepositMethods(String asset) throws Exception {
        return getDepositMethods(asset, LIBRARY_OBJECT);
    }

    /**
     * Request to retrieve methods available for depositing a particular asset
     *
     * @param asset:  asset being deposited
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return methods available as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods">
     * Get Deposit Methods</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/DepositMethods")
    public <T> T getDepositMethods(String asset, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        JSONObject jDeposit = new JSONObject(sendPostRequest(DEPOSIT_METHODS_ENDPOINT, params));
        switch (format) {
            case JSON:
                return (T) jDeposit;
            case LIBRARY_OBJECT:
                ArrayList<DepositMethod> depositMethods = new ArrayList<>();
                JSONArray jDeposits = jDeposit.getJSONArray("result");
                for (int j = 0; j < jDeposits.length(); j++)
                    depositMethods.add(new DepositMethod(jDeposits.getJSONObject(j)));
                return (T) depositMethods;
            default:
                return (T) jDeposit.toString();
        }
    }

    /** Request to retrieve (or generate a new) deposit addresses for a particular asset and method
     * @param asset: asset being deposited
     * @param method: name of the deposit method
     * @param newAddress: whether to generate a new address
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses">
     *     Get Deposit Addresses</a>
     * @return deposit addresses as {@link ArrayList} of {@link DepositAddress} custom object
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/DepositAddresses")
    public ArrayList<DepositAddress> getDepositAddresses(String asset, String method, boolean newAddress) throws Exception {
        return getDepositAddresses(asset, method, newAddress, LIBRARY_OBJECT);
    }

    /** Request to retrieve (or generate a new) deposit addresses for a particular asset and method
     * @param asset: asset being deposited
     * @param method: name of the deposit method
     * @param newAddress: whether to generate a new address
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses">
     *     Get Deposit Addresses</a>
     * @return deposit addresses as {@code "format"} defines
     * **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/DepositAddresses")
    public <T> T getDepositAddresses(String asset, String method, boolean newAddress, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("method", method);
        if (newAddress)
            params.addParam("new", true);
        JSONObject jDepositAddresses = new JSONObject(sendPostRequest(DEPOSIT_ADDRESSES_ENDPOINT, params));
        switch (format) {
            case JSON:
                return (T) jDepositAddresses;
            case LIBRARY_OBJECT:
                ArrayList<DepositAddress> depositAddresses = new ArrayList<>();
                JSONArray jsonDeposit = jDepositAddresses.getJSONArray("result");
                for (int j = 0; j < jsonDeposit.length(); j++)
                    depositAddresses.add(new DepositAddress(jsonDeposit.getJSONObject(j)));
                return (T) depositAddresses;
            default:
                return (T) jDepositAddresses.toString();
        }
    }

    /**
     * Request to retrieve information about recent deposits made <br>
     * No-any params required
     *
     * @return recent deposits as {@link ArrayList} of {@link OperationStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     * Get Status of Recent Deposits</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/DepositStatus")
    public ArrayList<OperationStatus> getRecentDepositsStatus() throws Exception {
        return getRecentDepositsStatus(LIBRARY_OBJECT);
    }

    /** Request to retrieve information about recent deposits made
     *
     * @param format:              return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     *    Get Status of Recent Deposits</a>
     * @return recent deposits as {"format"} defines
     * **/
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/DepositStatus")
    public <T> T getRecentDepositsStatus(ReturnFormat format) throws Exception {
        return returnOperationsList(sendPostRequest(DEPOSIT_STATUS_ENDPOINT, null), format);
    }

    /**
     * Request to retrieve information about recent deposits made
     *
     * @param params: extra order details, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "asset"} -> filter for specific asset being deposited - [string]
     *                    </li>
     *                    <li>
     *                        {@code "method"} -> filter for specific name of deposit method - [string]
     *                    </li>
     *                </ul>
     * @return recent deposits as {@link ArrayList} of {@link OperationStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     * Get Status of Recent Deposits</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/DepositStatus")
    public ArrayList<OperationStatus> getRecentDepositsStatus(Params params) throws Exception {
        return getRecentDepositsStatus(params, LIBRARY_OBJECT);
    }

    /**
     * Request to retrieve information about recent deposits made
     *
     * @param params: extra order details, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "asset"} -> filter for specific asset being deposited - [string]
     *                    </li>
     *                    <li>
     *                        {@code "method"} -> filter for specific name of deposit method - [string]
     *                    </li>
     *                </ul>
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return recent deposits as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     * Get Status of Recent Deposits</a>
     **/
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/DepositStatus")
    public <T> T getRecentDepositsStatus(Params params, ReturnFormat format) throws Exception {
        return returnOperationsList(sendPostRequest(DEPOSIT_STATUS_ENDPOINT, params), format);
    }

    /** Request to retrieve fee information about potential withdrawals for a particular asset, key and amount
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation">
     *     Get Withdrawal Information</a>
     * @return potential withdrawals for a particular asset, key and amount as {@link WithdrawInformation} custom object
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WithdrawInfo")
    public WithdrawInformation getWithdrawalInformation(String asset, String key, double amount) throws Exception {
        return getWithdrawalInformation(asset, key, amount, LIBRARY_OBJECT);
    }

    /** Request to retrieve fee information about potential withdrawals for a particular asset, key and amount
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
     * @param format:              return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation">
     *     Get Withdrawal Information</a>
     * @return potential withdrawals for a particular asset, key and amount as {"format"} defines
     * **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WithdrawInfo")
    public <T> T getWithdrawalInformation(String asset, String key, double amount, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("key", key);
        params.addParam("amount", sNotationParse(8, amount));
        String withdrawalResponse = sendPostRequest(GET_WITHDRAWAL_INFORMATION_ENDPOINT, params);
        switch (format) {
            case JSON:
                return (T) new JSONObject(withdrawalResponse);
            case LIBRARY_OBJECT:
                return (T) new WithdrawInformation(new JSONObject(withdrawalResponse));
            default:
                return (T) withdrawalResponse;
        }
    }

    /** Request to make a withdrawal request
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/withdrawFunds">
     *    Withdraw Funds</a>
     * @return withdrawal request result as {@link String}
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Withdraw")
    public String sendWithdraw(String asset, String key, double amount) throws Exception {
        return sendWithdraw(asset, key, amount, LIBRARY_OBJECT);
    }

    /** Request to make a withdrawal request
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
     * @param format:              return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/withdrawFunds">
     *    Withdraw Funds</a>
     * @return withdrawal request result as {"format"} defines
     * @implSpec in this case {@link ReturnFormat#LIBRARY_OBJECT} will return the {@code "refid"} associated with the
     * withdrawal made
     * **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/Withdraw")
    public <T> T sendWithdraw(String asset, String key, double amount, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("key", key);
        params.addParam("amount", sNotationParse(8, amount));
        String withdrawResponse = sendPostRequest(MAKE_WITHDRAW_ENDPOINT, params);
        switch (format) {
            case JSON:
                return (T) new JSONObject(withdrawResponse);
            case LIBRARY_OBJECT:
                return (T) new JSONObject(withdrawResponse).getJSONObject("result").getString("refid");
            default:
                return (T) withdrawResponse;
        }
    }

    /**
     * Request to retrieve information about recently requests withdrawals <br>
     * No-any params required
     *
     * @return information about recently requests withdrawals as {@link ArrayList} of {@link OperationStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     * Get Status of Recent Withdrawals</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WithdrawStatus")
    public ArrayList<OperationStatus> getRecentWithdrawalsStatus() throws Exception {
        return getRecentWithdrawalsStatus(LIBRARY_OBJECT);
    }

    /** Request to retrieve information about recently requests withdrawals
     *
     * @param format:              return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     *     Get Status of Recent Withdrawals</a>
     * @return information about recently requests withdrawals as {"format"} defines
     * **/
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WithdrawStatus")
    public <T> T getRecentWithdrawalsStatus(ReturnFormat format) throws Exception {
        return returnOperationsList(sendPostRequest(WITHDRAW_STATUS_ENDPOINT, null), format);
    }

    /** Request to retrieve information about recently requests withdrawals
     *
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "asset"} -> filter for specific asset being withdrawn - [string]
     *                          </li>
     *                          <li>
     *                              {@code "method"} -> filter for specific name of withdrawal  method - [string]
     *                          </li>
     *                      </ul>
     * @param method: name of the withdrawal method
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     *     Get Status of Recent Withdrawals</a>
     * @return information about recently requests withdrawals as {@link ArrayList} of {@link OperationStatus} custom object
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WithdrawStatus")
    public ArrayList<OperationStatus> getRecentWithdrawalsStatus(Params params, String method) throws Exception {
        return getRecentWithdrawalsStatus(params, LIBRARY_OBJECT);
    }

    /** Request to retrieve information about recently requests withdrawals
     *
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "asset"} -> filter for specific asset being withdrawn - [string]
     *                          </li>
     *                          <li>
     *                              {@code "method"} -> filter for specific name of withdrawal  method - [string]
     *                          </li>
     *                      </ul>
     * @param format:              return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     *     Get Status of Recent Withdrawals</a>
     * @return information about recently requests withdrawals as {"format"} defines
     * **/
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WithdrawStatus")
    public <T> T getRecentWithdrawalsStatus(Params params, ReturnFormat format) throws Exception {
        return returnOperationsList(sendPostRequest(WITHDRAW_STATUS_ENDPOINT, params), format);
    }

    /**
     * Method to assemble an operations status list
     *
     * @param statusListResponse: operations status list to format
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return operations status list as {"format"} defines
     **/
    @Returner
    private <T> T returnOperationsList(String statusListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(statusListResponse);
            case LIBRARY_OBJECT:
                ArrayList<OperationStatus> statusList = new ArrayList<>();
                JSONArray status = new JSONObject(statusListResponse).getJSONArray("result");
                for (int j = 0; j < status.length(); j++)
                    statusList.add(new OperationStatus(status.getJSONObject(j)));
                return (T) statusList;
            default:
                return (T) statusListResponse;
        }
    }

    /**
     * Request to cancel a recently requested withdrawal, if it has not already been successfully processed
     *
     * @param asset: asset being withdrawn
     * @param refId: withdrawal reference identifier
     * @return withdrawal cancellation result as boolean
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal">
     * Request Withdrawal Cancellation</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WithdrawCancel")
    public boolean cancelWithdrawal(String asset, String refId) throws Exception {
        return Boolean.parseBoolean(cancelWithdrawal(asset, refId, LIBRARY_OBJECT));
    }

    /** Request to cancel a recently requested withdrawal, if it has not already been successfully processed
     * @param asset: asset being withdrawn
     * @param refId: withdrawal reference identifier
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return withdrawal cancellation result as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal">
     *    Request Withdrawal Cancellation</a>
     * @implSpec the {@link ReturnFormat#LIBRARY_OBJECT} format type in this case will return whether cancellation has
     * been successful as boolean
     * **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WithdrawCancel")
    public <T> T cancelWithdrawal(String asset, String refId, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("refid", refId);
        String cancelResponse = sendPostRequest(CANCEL_WITHDRAW_ENDPOINT, params);
        switch (format) {
            case JSON:
                return (T) new JSONObject(cancelResponse);
            case LIBRARY_OBJECT:
                return (T) String.valueOf(new JSONObject(cancelResponse).getBoolean("result"));
            default:
                return (T) cancelResponse;
        }
    }

    /** Request to transfer from Kraken spot wallet to Kraken Futures holding wallet. Note that a transfer in the other
     * direction must be requested via the Kraken Futures
     * @param asset: asset to transfer (asset ID or altname)
     * @param from: source wallet
     * @param to: destination wallet
     * @param amount: amount to transfer
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/walletTransfer">
     *    Request Wallet Transfer</a>
     * @return transfer result as {@link String}
     * **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WalletTransfer")
    public String requestWalletTransfer(String asset, String from, String to, double amount) throws Exception {
        return requestWalletTransfer(asset, from, to, amount, LIBRARY_OBJECT);
    }

    /** Request to transfer from Kraken spot wallet to Kraken Futures holding wallet. Note that a transfer in the other
     * direction must be requested via the Kraken Futures
     * @param asset: asset to transfer (asset ID or altname)
     * @param from: source wallet
     * @param to: destination wallet
     * @param amount: amount to transfer
     * @param format:              return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/walletTransfer">
     *    Request Wallet Transfer</a>
     * @implSpec in this case {@link ReturnFormat#LIBRARY_OBJECT} will return the {@code "refid"} associated with the
     * transfer made
     * @return transfer result as {"format"} defines
     * **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/WalletTransfer")
    public <T> T requestWalletTransfer(String asset, String from, String to, double amount,
                                       ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("from", from);
        params.addParam("to", to);
        params.addParam("amount", sNotationParse(8, amount));
        String walletTransferResponse = sendPostRequest(WALLET_TRANSFER_ENDPOINT, params);
        switch (format) {
            case JSON:
                return (T) new JSONObject(walletTransferResponse);
            case LIBRARY_OBJECT:
                return (T) new JSONObject(walletTransferResponse).getJSONObject("result").getString("refid");
            default:
                return (T) walletTransferResponse;
        }
    }

}
