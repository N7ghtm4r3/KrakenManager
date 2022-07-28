package com.tecknobit.krakenmanager.Managers.Private.UserData;

import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.AccountBalance;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.TradeBalance;
import org.json.JSONObject;

import static com.tecknobit.krakenmanager.Constants.EndpointsList.GET_ACCOUNT_BALANCE_ENDPOINT;
import static com.tecknobit.krakenmanager.Constants.EndpointsList.GET_TRADE_BALANCE_ENDPOINT;

public class KrakenUserDataManager extends KrakenPrivateManager {

    public KrakenUserDataManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    public KrakenUserDataManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    public KrakenUserDataManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    public KrakenUserDataManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    public String getAccountBalance() throws Exception {
        return sendPostRequest(GET_ACCOUNT_BALANCE_ENDPOINT, null);
    }

    public JSONObject getAccountBalanceJSON() throws Exception {
        return new JSONObject(getAccountBalance());
    }

    public AccountBalance getAccountBalanceObject() throws Exception {
        return new AccountBalance(getAccountBalanceJSON());
    }

    public String getTradeBalance() throws Exception {
        return sendPostRequest(GET_TRADE_BALANCE_ENDPOINT, null);
    }

    public JSONObject getTradeBalanceJSON() throws Exception {
        return new JSONObject(getTradeBalance());
    }

    public TradeBalance getTradeBalanceObject() throws Exception {
        return new TradeBalance(getTradeBalanceJSON());
    }

}
