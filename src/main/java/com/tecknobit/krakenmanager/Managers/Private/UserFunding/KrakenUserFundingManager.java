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

public class KrakenUserFundingManager extends KrakenPrivateManager {

    public KrakenUserFundingManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    public KrakenUserFundingManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    public KrakenUserFundingManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    public KrakenUserFundingManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    public String getDepositMethods(String asset) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return sendPostRequest(DEPOSIT_METHODS_ENDPOINT, params);
    }

    public JSONObject getDepositMethodsJSON(String asset) throws Exception {
        return new JSONObject(getDepositMethods(asset));
    }

    public ArrayList<DepositMethod> getDepositMethodsList(String asset) throws Exception {
        ArrayList<DepositMethod> depositMethods = new ArrayList<>();
        JSONArray jsonDeposit = getDepositMethodsJSON(asset).getJSONArray("result");
        for (int j = 0; j < jsonDeposit.length(); j++)
            depositMethods.add(new DepositMethod(jsonDeposit.getJSONObject(j)));
        return depositMethods;
    }

    public String getDepositAddresses(String asset, String method, boolean newAddress) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("method", method);
        if(newAddress)
            params.addParam("new", true);
        return sendPostRequest(DEPOSIT_ADRESSES_ENDPOINT, params);
    }

    public JSONObject getDepositAddressesJSON(String asset, String method, boolean newAddress) throws Exception {
        return new JSONObject(getDepositAddresses(asset, method, newAddress));
    }

    public ArrayList<DepositAddress> getDepositAddressesList(String asset, String method, boolean newAddress) throws Exception {
        ArrayList<DepositAddress> depositAddresses = new ArrayList<>();
        JSONArray jsonDeposit = getDepositAddressesJSON(asset, method, newAddress).getJSONArray("result");
        for (int j = 0; j < jsonDeposit.length(); j++)
            depositAddresses.add(new DepositAddress(jsonDeposit.getJSONObject(j)));
        return depositAddresses;
    }

    public String getRecentDepositsStatus(String asset) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return sendPostRequest(DEPOSIT_STATUS_ENDPOINT, params);
    }

    public JSONObject getRecentDepositsStatusJSON(String asset) throws Exception {
        return new JSONObject(getRecentDepositsStatus(asset));
    }

    public ArrayList<OperationStatus> getRecentDepositsStatusList(String asset) throws Exception {
        return assembleStatusList(getRecentDepositsStatusJSON(asset));
    }

    public String getRecentDepositsStatus(String asset, String method) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("method", method);
        return sendPostRequest(DEPOSIT_STATUS_ENDPOINT, params);
    }

    public JSONObject getRecentDepositsStatusJSON(String asset, String method) throws Exception {
        return new JSONObject(getRecentDepositsStatus(asset, method));
    }

    public ArrayList<OperationStatus> getRecentDepositsStatusList(String asset, String method) throws Exception {
        return assembleStatusList(getRecentDepositsStatusJSON(asset, method));
    }

    private ArrayList<OperationStatus> assembleStatusList(JSONObject jsonDeposits) {
        ArrayList<OperationStatus> statusList = new ArrayList<>();
        JSONArray status = jsonDeposits.getJSONArray("result");
        for (int j = 0; j < status.length(); j++)
            statusList.add(new OperationStatus(status.getJSONObject(j)));
        return statusList;
    }

    public String getWithdrawalInformation(String asset, String key, double amount) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("key", key);
        params.addParam("amount", amount);
        return sendPostRequest(GET_WITHDRAWAL_INFORMATION_ENDPOINT, params);
    }

    public JSONObject getWithdrawalInformationJSON(String asset, String key, double amount) throws Exception {
        return new JSONObject(getWithdrawalInformation(asset, key, amount));
    }

    public WithdrawInformation getWithdrawalInformationObject(String asset, String key, double amount) throws Exception {
        return new WithdrawInformation(getWithdrawalInformationJSON(asset, key, amount));
    }

    public String sendWithdraw(String asset, String key, double amount) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("key", key);
        params.addParam("amount", amount);
        return sendPostRequest(MAKE_WITHDRAW_ENDPOINT, params);
    }

    public JSONObject sendWithdrawJSON(String asset, String key, double amount) throws Exception {
        return new JSONObject(sendWithdraw(asset, key, amount));
    }

    public String sendWithdrawAndGetId(String asset, String key, double amount) throws Exception {
        return sendWithdrawJSON(asset, key, amount).getJSONObject("result").getString("refid");
    }

    public String getRecentWithdrawalsStatus(String asset) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return sendPostRequest(WITHDRAW_STATUS_ENDPOINT, params);
    }

    public JSONObject getRecentWithdrawalsStatusJSON(String asset) throws Exception {
        return new JSONObject(getRecentWithdrawalsStatus(asset));
    }

    public ArrayList<OperationStatus> getRecentWithdrawalsStatusList(String asset) throws Exception {
        return assembleStatusList(getRecentWithdrawalsStatusJSON(asset));
    }

    public String getRecentWithdrawalsStatus(String asset, String method) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("method", method);
        return sendPostRequest(WITHDRAW_STATUS_ENDPOINT, params);
    }

    public JSONObject getRecentWithdrawalsStatusJSON(String asset, String method) throws Exception {
        return new JSONObject(getRecentWithdrawalsStatus(asset, method));
    }

    public ArrayList<OperationStatus> getRecentWithdrawalsStatusList(String asset, String method) throws Exception {
        return assembleStatusList(getRecentWithdrawalsStatusJSON(asset, method));
    }

    public String cancelWithdrawal(String asset, String refId) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("refid", refId);
        return sendPostRequest(CANCEL_WITHDRAW_ENDPOINT, params);
    }

    public JSONObject cancelWithdrawalJSON(String asset, String refId) throws Exception {
        return new JSONObject(cancelWithdrawal(asset, refId));
    }

    public boolean cancelWithdrawalConfirm(String asset, String refId) throws Exception {
        return cancelWithdrawalJSON(asset, refId).getBoolean("result");
    }

    public String requestWalletTransfer(String asset, String from, String to, double amount) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        params.addParam("from", from);
        params.addParam("to", to);
        params.addParam("amount", amount);
        return sendPostRequest(WALLET_TRANSFER_ENDPOINT, params);
    }

    public JSONObject requestWalletTransferJSON(String asset, String from, String to, double amount) throws Exception {
        return new JSONObject(requestWalletTransfer(asset, from, to, amount));
    }

    public String requestWalletTransferAndGetId(String asset, String from, String to, double amount) throws Exception {
        return requestWalletTransferJSON(asset, from, to, amount).getJSONObject("result").getString("refid");
    }

}
