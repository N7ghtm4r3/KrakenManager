package com.tecknobit.krakenmanager.Managers.Private.UserFunding;

import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records.DepositAddress;
import com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records.DepositMethod;
import com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records.OperationStatus;
import com.tecknobit.krakenmanager.Managers.Private.UserFunding.Records.WithdrawInformation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.krakenmanager.Constants.EndpointsList.*;

/**
 *  The {@code KrakenUserFundingManager} class is useful to manage all user funding endpoints
 *  @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding">
 *      https://docs.kraken.com/rest/#tag/User-Funding</a>
 *  @author N7ghtm4r3 - Tecknobit
 * **/

public class KrakenUserFundingManager extends KrakenPrivateManager {

    /**
     * {@code SPOT_WALLET_SOURCE} is constant for spot wallet source
     * **/
    public static final String SPOT_WALLET_SOURCE = "Spot Wallet";

    /**
     * {@code FUTURES_WALLET_SOURCE} is constant for Futures wallet source
     * **/
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

    /** Constructor to init a {@link KrakenUserFundingManager}
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserFundingManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenUserFundingManager}
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserFundingManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    /** Request to retrieve methods available for depositing a particular asset
     * @param asset: asset being deposited
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods</a>
     * @return methods available as {@link String}
     * **/
    public String getDepositMethods(String asset) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return sendPostRequest(DEPOSIT_METHODS_ENDPOINT, params);
    }

    /** Request to retrieve methods available for depositing a particular asset
     * @param asset: asset being deposited
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods</a>
     * @return methods available as {@link JSONObject}
     * **/
    public JSONObject getDepositMethodsJSON(String asset) throws Exception {
        return new JSONObject(getDepositMethods(asset));
    }

    /** Request to retrieve methods available for depositing a particular asset
     * @param asset: asset being deposited
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositMethods</a>
     * @return methods available as {@link ArrayList} of {@link DepositMethod} custom object
     * **/
    public ArrayList<DepositMethod> getDepositMethodsList(String asset) throws Exception {
        ArrayList<DepositMethod> depositMethods = new ArrayList<>();
        JSONArray jsonDeposit = getDepositMethodsJSON(asset).getJSONArray("result");
        for (int j = 0; j < jsonDeposit.length(); j++)
            depositMethods.add(new DepositMethod(jsonDeposit.getJSONObject(j)));
        return depositMethods;
    }

    /** Request to retrieve (or generate a new) deposit addresses for a particular asset and method
     * @param asset: asset being deposited
     * @param method: name of the deposit method
     * @param newAddress: whether or not to generate a new address
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses</a>
     * @return deposit addresses as {@link String}
     * **/
    public String getDepositAddresses(String asset, String method, boolean newAddress) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("method", method);
        if(newAddress)
            params.addParam("new", true);
        return sendPostRequest(DEPOSIT_ADRESSES_ENDPOINT, params);
    }

    /** Request to retrieve (or generate a new) deposit addresses for a particular asset and method
     * @param asset: asset being deposited
     * @param method: name of the deposit method
     * @param newAddress: whether or not to generate a new address
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses</a>
     * @return deposit addresses as {@link JSONObject}
     * **/
    public JSONObject getDepositAddressesJSON(String asset, String method, boolean newAddress) throws Exception {
        return new JSONObject(getDepositAddresses(asset, method, newAddress));
    }

    /** Request to retrieve (or generate a new) deposit addresses for a particular asset and method
     * @param asset: asset being deposited
     * @param method: name of the deposit method
     * @param newAddress: whether or not to generate a new address
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getDepositAddresses</a>
     * @return deposit addresses as {@link ArrayList} of {@link DepositAddress} custom object
     * **/
    public ArrayList<DepositAddress> getDepositAddressesList(String asset, String method, boolean newAddress) throws Exception {
        ArrayList<DepositAddress> depositAddresses = new ArrayList<>();
        JSONArray jsonDeposit = getDepositAddressesJSON(asset, method, newAddress).getJSONArray("result");
        for (int j = 0; j < jsonDeposit.length(); j++)
            depositAddresses.add(new DepositAddress(jsonDeposit.getJSONObject(j)));
        return depositAddresses;
    }

    /** Request to retrieve information about recent deposits made
     * @param asset: asset being deposited
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits</a>
     * @return recent deposits made as {@link String}
     * **/
    public String getRecentDepositsStatus(String asset) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return sendPostRequest(DEPOSIT_STATUS_ENDPOINT, params);
    }

    /** Request to retrieve information about recent deposits made
     * @param asset: asset being deposited
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits</a>
     * @return recent deposits made as {@link JSONObject}
     * **/
    public JSONObject getRecentDepositsStatusJSON(String asset) throws Exception {
        return new JSONObject(getRecentDepositsStatus(asset));
    }

    /** Request to retrieve information about recent deposits made
     * @param asset: asset being deposited
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits</a>
     * @return recent deposits made as {@link ArrayList} of {@link OperationStatus} custom object
     * **/
    public ArrayList<OperationStatus> getRecentDepositsStatusList(String asset) throws Exception {
        return assembleStatusList(getRecentDepositsStatusJSON(asset));
    }

    /** Request to retrieve information about recent deposits made
     * @param asset: asset being deposited
     * @param method: name of the deposit method
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits</a>
     * @return recent deposits made as {@link String}
     * **/
    public String getRecentDepositsStatus(String asset, String method) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("method", method);
        return sendPostRequest(DEPOSIT_STATUS_ENDPOINT, params);
    }

    /** Request to retrieve information about recent deposits made
     * @param asset: asset being deposited
     * @param method: name of the deposit method
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits</a>
     * @return recent deposits made as {@link JSONObject}
     * **/
    public JSONObject getRecentDepositsStatusJSON(String asset, String method) throws Exception {
        return new JSONObject(getRecentDepositsStatus(asset, method));
    }

    /** Request to retrieve information about recent deposits made
     * @param asset: asset being deposited
     * @param method: name of the deposit method
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentDeposits</a>
     * @return recent deposits made as {@link ArrayList} of {@link OperationStatus} custom object
     * **/
    public ArrayList<OperationStatus> getRecentDepositsStatusList(String asset, String method) throws Exception {
        return assembleStatusList(getRecentDepositsStatusJSON(asset, method));
    }

    /** Method to assemble an operations status list
     * @param jsonDeposits: jsonObject obtained by response request
     * @return operations status list as {@link ArrayList} of {@link OperationStatus}
     * **/
    private ArrayList<OperationStatus> assembleStatusList(JSONObject jsonDeposits) {
        ArrayList<OperationStatus> statusList = new ArrayList<>();
        JSONArray status = jsonDeposits.getJSONArray("result");
        for (int j = 0; j < status.length(); j++)
            statusList.add(new OperationStatus(status.getJSONObject(j)));
        return statusList;
    }

    /** Request to retrieve fee information about potential withdrawals for a particular asset, key and amount
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation</a>
     * @return potential withdrawals for a particular asset, key and amount as {@link String}
     * **/
    public String getWithdrawalInformation(String asset, String key, double amount) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("key", key);
        params.addParam("amount", amount);
        return sendPostRequest(GET_WITHDRAWAL_INFORMATION_ENDPOINT, params);
    }

    /** Request to retrieve fee information about potential withdrawals for a particular asset, key and amount
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation</a>
     * @return potential withdrawals for a particular asset, key and amount as {@link JSONObject}
     * **/
    public JSONObject getWithdrawalInformationJSON(String asset, String key, double amount) throws Exception {
        return new JSONObject(getWithdrawalInformation(asset, key, amount));
    }

    /** Request to retrieve fee information about potential withdrawals for a particular asset, key and amount
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getWithdrawalInformation</a>
     * @return potential withdrawals for a particular asset, key and amount as {@link WithdrawInformation} custom object
     * **/
    public WithdrawInformation getWithdrawalInformationObject(String asset, String key, double amount) throws Exception {
        return new WithdrawInformation(getWithdrawalInformationJSON(asset, key, amount));
    }

    /** Request to make a withdrawal request
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/withdrawFunds">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/withdrawFunds</a>
     * @return withdrawal request result as {@link String}
     * **/
    public String sendWithdraw(String asset, String key, double amount) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("key", key);
        params.addParam("amount", amount);
        return sendPostRequest(MAKE_WITHDRAW_ENDPOINT, params);
    }

    /** Request to make a withdrawal request
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/withdrawFunds">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/withdrawFunds</a>
     * @return withdrawal request result as {@link JSONObject}
     * **/
    public JSONObject sendWithdrawJSON(String asset, String key, double amount) throws Exception {
        return new JSONObject(sendWithdraw(asset, key, amount));
    }

    /** Request to make a withdrawal request
     * @param asset: asset being withdrawn
     * @param key: withdrawal key name, as set up on your account
     * @param amount: amount to be withdrawn
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/withdrawFunds">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/withdrawFunds</a>
     * @return withdrawal request identifier as {@link JSONObject}
     * **/
    public String sendWithdrawAndGetId(String asset, String key, double amount) throws Exception {
        return sendWithdrawJSON(asset, key, amount).getJSONObject("result").getString("refid");
    }

    /** Request to retrieve information about recently requests withdrawals
     * @param asset: asset being withdrawn
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals</a>
     * @return information about recently requests withdrawals as {@link String}
     * **/
    public String getRecentWithdrawalsStatus(String asset) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return sendPostRequest(WITHDRAW_STATUS_ENDPOINT, params);
    }

    /** Request to retrieve information about recently requests withdrawals
     * @param asset: asset being withdrawn
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals</a>
     * @return information about recently requests withdrawals as {@link JSONObject}
     * **/
    public JSONObject getRecentWithdrawalsStatusJSON(String asset) throws Exception {
        return new JSONObject(getRecentWithdrawalsStatus(asset));
    }

    /** Request to retrieve information about recently requests withdrawals
     * @param asset: asset being withdrawn
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals</a>
     * @return information about recently requests withdrawals as {@link ArrayList} of {@link OperationStatus} custom object
     * **/
    public ArrayList<OperationStatus> getRecentWithdrawalsStatusList(String asset) throws Exception {
        return assembleStatusList(getRecentWithdrawalsStatusJSON(asset));
    }

    /** Request to retrieve information about recently requests withdrawals
     * @param asset: asset being withdrawn
     * @param method: name of the withdrawal method
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals</a>
     * @return information about recently requests withdrawals as {@link String}
     * **/
    public String getRecentWithdrawalsStatus(String asset, String method) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("method", method);
        return sendPostRequest(WITHDRAW_STATUS_ENDPOINT, params);
    }

    /** Request to retrieve information about recently requests withdrawals
     * @param asset: asset being withdrawn
     * @param method: name of the withdrawal method
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals</a>
     * @return information about recently requests withdrawals as {@link JSONObject}
     * **/
    public JSONObject getRecentWithdrawalsStatusJSON(String asset, String method) throws Exception {
        return new JSONObject(getRecentWithdrawalsStatus(asset, method));
    }

    /** Request to retrieve information about recently requests withdrawals
     * @param asset: asset being withdrawn
     * @param method: name of the withdrawal method
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/getStatusRecentWithdrawals</a>
     * @return information about recently requests withdrawals as {@link ArrayList} of {@link OperationStatus} custom object
     * **/
    public ArrayList<OperationStatus> getRecentWithdrawalsStatusList(String asset, String method) throws Exception {
        return assembleStatusList(getRecentWithdrawalsStatusJSON(asset, method));
    }

    /** Request to cancel a recently requested withdrawal, if it has not already been successfully processed
     * @param asset: asset being withdrawn
     * @param refId: withdrawal reference identifier
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal</a>
     * @return withdrawal cancellation result as {@link String}
     * **/
    public String cancelWithdrawal(String asset, String refId) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("refid", refId);
        return sendPostRequest(CANCEL_WITHDRAW_ENDPOINT, params);
    }

    /** Request to cancel a recently requested withdrawal, if it has not already been successfully processed
     * @param asset: asset being withdrawn
     * @param refId: withdrawal reference identifier
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal</a>
     * @return withdrawal cancellation result as {@link JSONObject}
     * **/
    public JSONObject cancelWithdrawalJSON(String asset, String refId) throws Exception {
        return new JSONObject(cancelWithdrawal(asset, refId));
    }

    /** Request to cancel a recently requested withdrawal, if it has not already been successfully processed
     * @param asset: asset being withdrawn
     * @param refId: withdrawal reference identifier
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal</a>
     * @return withdrawal cancellation result as boolean
     * **/
    public boolean cancelWithdrawalConfirm(String asset, String refId) throws Exception {
        return cancelWithdrawalJSON(asset, refId).getBoolean("result");
    }

    /** Request to transfer from Kraken spot wallet to Kraken Futures holding wallet. Note that a transfer in the other
     * direction must be requested via the Kraken Futures
     * @param asset: asset to transfer (asset ID or altname)
     * @param from: source wallet
     * @param to: destination wallet
     * @param amount: amount to transfer
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal</a>
     * @return transfer result as {@link String}
     * **/
    public String requestWalletTransfer(String asset, String from, String to, double amount) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("from", from);
        params.addParam("to", to);
        params.addParam("amount", amount);
        return sendPostRequest(WALLET_TRANSFER_ENDPOINT, params);
    }

    /** Request to transfer from Kraken spot wallet to Kraken Futures holding wallet. Note that a transfer in the other
     * direction must be requested via the Kraken Futures
     * @param asset: asset to transfer (asset ID or altname)
     * @param from: source wallet
     * @param to: destination wallet
     * @param amount: amount to transfer
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal</a>
     * @return transfer result as {@link JSONObject}
     * **/
    public JSONObject requestWalletTransferJSON(String asset, String from, String to, double amount) throws Exception {
        return new JSONObject(requestWalletTransfer(asset, from, to, amount));
    }

    /** Request to transfer from Kraken spot wallet to Kraken Futures holding wallet. Note that a transfer in the other
     * direction must be requested via the Kraken Futures
     * @param asset: asset to transfer (asset ID or altname)
     * @param from: source wallet
     * @param to: destination wallet
     * @param amount: amount to transfer
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal">
     *     https://docs.kraken.com/rest/#tag/User-Funding/operation/cancelWithdrawal</a>
     * @return transfer identifier as {@link JSONObject}
     * **/
    public String requestWalletTransferAndGetId(String asset, String from, String to, double amount) throws Exception {
        return requestWalletTransferJSON(asset, from, to, amount).getJSONObject("result").getString("refid");
    }

}
