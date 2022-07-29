package com.tecknobit.krakenmanager.Managers.Private.UserData;

import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Balance.AccountBalance;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Balance.TradeBalance;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.ClosedOrder;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades.HistoryTrade;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades.QueryTrade;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.krakenmanager.Constants.EndpointsList.*;

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

    public String getTradeBalance(String asset) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return sendPostRequest(GET_TRADE_BALANCE_ENDPOINT, params);
    }

    public JSONObject getTradeBalanceJSON(String asset) throws Exception {
        return new JSONObject(getTradeBalance(asset));
    }

    public TradeBalance getTradeBalanceObject(String asset) throws Exception {
        return new TradeBalance(getTradeBalanceJSON(asset));
    }

    public String getOpenOrders(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(GET_OPEN_ORDERS_ENDPOINT, params);
    }

    public JSONObject getOpenOrdersJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getOpenOrders(insertTrades));
    }

    public ArrayList<Order> getOpenOrdersList(boolean insertTrades) throws Exception {
        return assembleOrdersList(getOpenOrdersJSON(insertTrades).getJSONObject("result").getJSONObject("open"));
    }

    public String getOpenOrders(boolean insertTrades, long userRef) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("userref", userRef);
        return sendPostRequest(GET_OPEN_ORDERS_ENDPOINT, params);
    }

    public JSONObject getOpenOrdersJSON(boolean insertTrades, long userRef) throws Exception {
        return new JSONObject(getOpenOrders(insertTrades, userRef));
    }

    public ArrayList<Order> getOpenOrdersList(boolean insertTrades, long userRef) throws Exception {
        return assembleOrdersList(getOpenOrdersJSON(insertTrades, userRef).getJSONObject("result")
                .getJSONObject("open"));
    }

    private ArrayList<Order> assembleOrdersList(JSONObject jsonOrder){
        ArrayList<Order> openOrders = new ArrayList<>();
        for (String order : jsonOrder.keySet())
            openOrders.add(new Order(jsonOrder.getJSONObject(order)));
        return openOrders;
    }

    // TODO: 29/07/2022 INSERT COUNT CORRESPONDING TO LIST SIZE INTO DOCUSTRING
    public String getClosedOrders(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(GET_CLOSED_ORDERS_ENDPOINT, params);
    }

    public JSONObject getClosedOrdersJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getClosedOrders(insertTrades));
    }

    public ArrayList<ClosedOrder> getClosedOrdersList(boolean insertTrades) throws Exception {
        return assembleClosedOrdersList(getClosedOrdersJSON(insertTrades));
    }

    public String getClosedOrders(Params params) throws Exception {
        return sendPostRequest(GET_CLOSED_ORDERS_ENDPOINT, params);
    }

    public JSONObject getClosedOrdersJSON(Params params) throws Exception {
        return new JSONObject(getClosedOrders(params));
    }

    public ArrayList<ClosedOrder> getClosedOrdersList(Params params) throws Exception {
        return assembleClosedOrdersList(getClosedOrdersJSON(params));
    }

    private ArrayList<ClosedOrder> assembleClosedOrdersList(JSONObject jsonOrder){
        ArrayList<ClosedOrder> openOrders = new ArrayList<>();
        jsonOrder = jsonOrder.getJSONObject("result").getJSONObject("closed");
        for (String order : jsonOrder.keySet())
            openOrders.add(new ClosedOrder(jsonOrder.getJSONObject(order)));
        return openOrders;
    }

    public String getQueryOrdersInfo(long txId, boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", txId);
        return sendPostRequest(QUERY_ORDERS_ENDPOINT, params);
    }

    public JSONObject getQueryOrdersInfoJSON(long txId, boolean insertTrades) throws Exception {
        return new JSONObject(getQueryOrdersInfo(txId, insertTrades));
    }

    public ArrayList<Order> getQueryOrdersInfoList(long txId, boolean insertTrades) throws Exception {
        return assembleOrdersList(getQueryOrdersInfoJSON(txId, insertTrades).getJSONObject("result"));
    }

    public String getQueryOrdersInfo(long txId, boolean insertTrades, long userRef) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", txId);
        params.addParam("userref", userRef);
        return sendPostRequest(QUERY_ORDERS_ENDPOINT, params);
    }

    public JSONObject getQueryOrdersInfoJSON(long txId, boolean insertTrades, long userRef) throws Exception {
        return new JSONObject(getQueryOrdersInfo(txId, insertTrades, userRef));
    }

    public ArrayList<Order> getQueryOrdersInfoList(long txId, boolean insertTrades, long userRef) throws Exception {
        return assembleOrdersList(getQueryOrdersInfoJSON(txId, insertTrades, userRef).getJSONObject("result"));
    }

    // TODO: 29/07/2022 INSERT COUNT CORRESPONDING TO LIST SIZE INTO DOCUSTRING
    public String getTradesHistory() throws Exception {
        return sendPostRequest(GET_TRADES_HISTORY_ENDPOINT, null);
    }

    public JSONObject getTradesHistoryJSON() throws Exception {
        return new JSONObject(getTradesHistory());
    }

    public ArrayList<HistoryTrade> getTradesHistoryList() throws Exception {
        return assebleHistoryTradesList(getTradesHistoryJSON());
    }

    public String getTradesHistory(Params params) throws Exception {
        return sendPostRequest(GET_TRADES_HISTORY_ENDPOINT, params);
    }

    public JSONObject getTradesHistoryJSON(Params params) throws Exception {
        return new JSONObject(getTradesHistory(params));
    }

    public ArrayList<HistoryTrade> getTradesHistoryList(Params params) throws Exception {
        return assebleHistoryTradesList(getTradesHistoryJSON(params));
    }

    private ArrayList<HistoryTrade> assebleHistoryTradesList(JSONObject jsonTrades) {
        ArrayList<HistoryTrade> trades = new ArrayList<>();
        jsonTrades = jsonTrades.getJSONObject("result").getJSONObject("trades");
        for (String trade : jsonTrades.keySet())
            trades.add(new HistoryTrade(jsonTrades.getJSONObject(trade), trade));
        return trades;
    }

    public String getQueryTradesInfo(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(QUERY_TRADES_ENDPOINT, params);
    }

    public JSONObject getQueryTradesInfoJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getQueryTradesInfo(insertTrades));
    }

    public ArrayList<QueryTrade> getQueryTradesInfoList(boolean insertTrades) throws Exception {
        return assebleQueryTradesList(getQueryTradesInfoJSON(insertTrades));
    }

    public String getQueryTradesInfo(boolean insertTrades, String txId) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", txId);
        return sendPostRequest(QUERY_TRADES_ENDPOINT, params);
    }

    public JSONObject getQueryTradesInfoJSON(boolean insertTrades, String txId) throws Exception {
        return new JSONObject(getQueryTradesInfo(insertTrades, txId));
    }

    public ArrayList<QueryTrade> getQueryTradesInfoList(boolean insertTrades, String txId) throws Exception {
        return assebleQueryTradesList(getQueryTradesInfoJSON(insertTrades, txId));
    }

    private ArrayList<QueryTrade> assebleQueryTradesList(JSONObject jsonTrades) {
        ArrayList<QueryTrade> trades = new ArrayList<>();
        for (String trade : jsonTrades.keySet())
            trades.add(new QueryTrade(jsonTrades.getJSONObject(trade), trade));
        return trades;
    }

}
