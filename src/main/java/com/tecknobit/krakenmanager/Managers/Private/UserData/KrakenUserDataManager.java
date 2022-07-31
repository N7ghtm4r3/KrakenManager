package com.tecknobit.krakenmanager.Managers.Private.UserData;

import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Balance.AccountBalance;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Balance.TradeBalance;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Ledger;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.OpenPosition;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.ClosedOrder;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.ReportStatus;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades.HistoryTrade;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades.QueryTrade;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades.TradeVolume;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.krakenmanager.Constants.EndpointsList.*;

/**
 *  The {@code KrakenUserDataManager} class is useful to manage all user data endpoints
 *  @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data">
 *      https://docs.kraken.com/rest/#tag/User-Data</a>
 *  @author N7ghtm4r3 - Tecknobit
 * **/

public class KrakenUserDataManager extends KrakenPrivateManager {

    /** Constructor to init a {@link KrakenUserDataManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserDataManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenUserDataManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserDataManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenUserDataManager}
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserDataManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenUserDataManager}
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserDataManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    /** Request to get account balance<br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance</a>
     * @return account balance as {@link String}
     * **/
    public String getAccountBalance() throws Exception {
        return sendPostRequest(GET_ACCOUNT_BALANCE_ENDPOINT, null);
    }

    /** Request to get account balance<br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance</a>
     * @return account balance as {@link JSONObject}
     * **/
    public JSONObject getAccountBalanceJSON() throws Exception {
        return new JSONObject(getAccountBalance());
    }

    /** Request to get account balance<br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance</a>
     * @return account balance as {@link AccountBalance} custom object
     * **/
    public AccountBalance getAccountBalanceObject() throws Exception {
        return new AccountBalance(getAccountBalanceJSON());
    }

    /** Request to get trade balance<br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance</a>
     * @return trade balance as {@link String}
     * **/
    public String getTradeBalance() throws Exception {
        return sendPostRequest(GET_TRADE_BALANCE_ENDPOINT, null);
    }

    /** Request to get trade balance<br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance</a>
     * @return trade balance as {@link JSONObject}
     * **/
    public JSONObject getTradeBalanceJSON() throws Exception {
        return new JSONObject(getTradeBalance());
    }

    /** Request to get trade balance<br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance</a>
     * @return trade balance as {@link TradeBalance} custom object
     * **/
    public TradeBalance getTradeBalanceObject() throws Exception {
        return new TradeBalance(getTradeBalanceJSON());
    }

    /** Request to get trade balance
     * @param asset: asset to fetch trade balance details
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance</a>
     * @return trade balance as {@link String}
     * **/
    public String getTradeBalance(String asset) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return sendPostRequest(GET_TRADE_BALANCE_ENDPOINT, params);
    }

    /** Request to get trade balance
     * @param asset: asset to fetch trade balance details
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance</a>
     * @return trade balance as {@link JSONObject}
     * **/
    public JSONObject getTradeBalanceJSON(String asset) throws Exception {
        return new JSONObject(getTradeBalance(asset));
    }

    /** Request to get trade balance
     * @param asset: asset to fetch trade balance details
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance</a>
     * @return trade balance as {@link TradeBalance} custom object
     * **/
    public TradeBalance getTradeBalanceObject(String asset) throws Exception {
        return new TradeBalance(getTradeBalanceJSON(asset));
    }

    /** Request to get open orders
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return open orders as {@link String}
     * **/
    public String getOpenOrders(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(GET_OPEN_ORDERS_ENDPOINT, params);
    }

    /** Request to get open orders
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return open orders as {@link JSONObject}
     * **/
    public JSONObject getOpenOrdersJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getOpenOrders(insertTrades));
    }

    /** Request to get open orders list
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return open orders as {@link ArrayList} of {@link Order} custom object
     * **/
    public ArrayList<Order> getOpenOrdersList(boolean insertTrades) throws Exception {
        return assembleOrdersList(getOpenOrdersJSON(insertTrades).getJSONObject("result").getJSONObject("open"));
    }

    /** Request to get open orders
     * @param insertTrades: wheter to include trades related to position in output
     * @param userRef: user reference id
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return open orders as {@link String}
     * **/
    public String getOpenOrders(boolean insertTrades, long userRef) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("userref", userRef);
        return sendPostRequest(GET_OPEN_ORDERS_ENDPOINT, params);
    }

    /** Request to get open orders
     * @param insertTrades: wheter to include trades related to position in output
     * @param userRef: user reference id
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return open orders as {@link JSONObject}
     * **/
    public JSONObject getOpenOrdersJSON(boolean insertTrades, long userRef) throws Exception {
        return new JSONObject(getOpenOrders(insertTrades, userRef));
    }

    /** Request to get open orders list
     * @param insertTrades: wheter to include trades related to position in output
     * @param userRef: user reference id
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return open orders as {@link ArrayList} of {@link Order} custom object
     * **/
    public ArrayList<Order> getOpenOrdersList(boolean insertTrades, long userRef) throws Exception {
        return assembleOrdersList(getOpenOrdersJSON(insertTrades, userRef).getJSONObject("result")
                .getJSONObject("open"));
    }

    /** Method to assemble an order list
     * @param jsonOrder: jsonObject obtained by response request
     * @return order list as {@link ArrayList} of {@link Order}
     * **/
    private ArrayList<Order> assembleOrdersList(JSONObject jsonOrder){
        ArrayList<Order> openOrders = new ArrayList<>();
        for (String order : jsonOrder.keySet())
            openOrders.add(new Order(jsonOrder.getJSONObject(order)));
        return openOrders;
    }

    /** Request to get closed orders
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders</a>
     * @implSpec to get also count value use {@link #getClosedOrdersJSON(boolean)} or {@link #getClosedOrdersList(boolean)}
     * methods instead
     * @return closed orders as {@link String}
     * **/
    public String getClosedOrders(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(GET_CLOSED_ORDERS_ENDPOINT, params);
    }

    /** Request to get closed orders
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders</a>
     * @implSpec count value is retriavable with length() method of {@link JSONObject} value returned
     * @return closed orders as {@link JSONObject}
     * **/
    public JSONObject getClosedOrdersJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getClosedOrders(insertTrades));
    }

    /** Request to get closed orders list
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders</a>
     * @implSpec count value is retriavable with size() method of {@link ArrayList} value returned
     * @return closed orders list as {@link ArrayList} of {@link ClosedOrder} custom methods
     * **/
    public ArrayList<ClosedOrder> getClosedOrdersList(boolean insertTrades) throws Exception {
        return assembleClosedOrdersList(getClosedOrdersJSON(insertTrades));
    }

    /** Request to get closed orders
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders</a>
     * @implSpec to get also count value use {@link #getClosedOrdersJSON(Params)} or {@link #getClosedOrdersList(Params)}
     * methods instead
     * @implNote keys for params accepted are: trades,userref,start,end,ofs and closetime
     * @return closed orders as {@link String}
     * **/
    public String getClosedOrders(Params params) throws Exception {
        return sendPostRequest(GET_CLOSED_ORDERS_ENDPOINT, params);
    }

    /** Request to get closed orders
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders</a>
     * @implSpec count value is retriavable with length() method of {@link JSONObject} value returned
     * @implNote keys for params accepted are: trades,userref,start,end,ofs and closetime
     * @return closed orders as {@link JSONObject}
     * **/
    public JSONObject getClosedOrdersJSON(Params params) throws Exception {
        return new JSONObject(getClosedOrders(params));
    }

    /** Request to get closed orders list
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders</a>
     * @implSpec count value is retriavable with size() method of {@link ArrayList} value returned
     * @implNote keys for params accepted are: trades,userref,start,end,ofs and closetime
     * @return closed orders list as {@link ArrayList} of {@link ClosedOrder} custom methods
     * **/
    public ArrayList<ClosedOrder> getClosedOrdersList(Params params) throws Exception {
        return assembleClosedOrdersList(getClosedOrdersJSON(params));
    }

    /** Method to assemble a closed order list
     * @param jsonOrder: jsonObject obtained by response request
     * @return closed order list as {@link ArrayList} of {@link ClosedOrder}
     * **/
    private ArrayList<ClosedOrder> assembleClosedOrdersList(JSONObject jsonOrder){
        ArrayList<ClosedOrder> openOrders = new ArrayList<>();
        jsonOrder = jsonOrder.getJSONObject("result").getJSONObject("closed");
        for (String order : jsonOrder.keySet())
            openOrders.add(new ClosedOrder(jsonOrder.getJSONObject(order)));
        return openOrders;
    }

    /**
     * Request to get query orders info
     * @param insertTrades : wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @return query orders info as {@link String}
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     **/
    public String getQueryOrdersInfo(boolean insertTrades, Long[] txId) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", apiRequest.assembleParamsList(",", txId));
        return sendPostRequest(QUERY_ORDERS_ENDPOINT, params);
    }

    /** Request to get query orders info
     * @param insertTrades : wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return query orders info as {@link JSONObject}
     * **/
    public JSONObject getQueryOrdersInfoJSON(boolean insertTrades, Long[] txId) throws Exception {
        return new JSONObject(getQueryOrdersInfo(insertTrades, txId));
    }

    /** Request to get query orders info list
     * @param insertTrades : wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return query orders info list as {@link ArrayList} of {@link Order} custom object
     * **/
    public ArrayList<Order> getQueryOrdersInfoList(boolean insertTrades, Long[] txId) throws Exception {
        return assembleOrdersList(getQueryOrdersInfoJSON(insertTrades, txId).getJSONObject("result"));
    }

    /**
     * Request to get query orders info
     * @param insertTrades : wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     * of {@link Long} format
     * @return query orders info as {@link String}
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     **/
    public String getQueryOrdersInfo(boolean insertTrades, ArrayList<Long> txId) throws Exception {
        return getQueryOrdersInfo(insertTrades, txId.toArray(new Long[txId.size()]));
    }

    /** Request to get query orders info
     * @param insertTrades : wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     * of {@link Long} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return query orders info as {@link JSONObject}
     * **/
    public JSONObject getQueryOrdersInfoJSON(boolean insertTrades, ArrayList<Long> txId) throws Exception {
        return new JSONObject(getQueryOrdersInfo(insertTrades, txId));
    }

    /** Request to get query orders info list
     * @param insertTrades : wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     * of {@link Long} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return query orders info list as {@link ArrayList} of {@link Order} custom object
     * **/
    public ArrayList<Order> getQueryOrdersInfoList(boolean insertTrades, ArrayList<Long> txId) throws Exception {
        return assembleOrdersList(getQueryOrdersInfoJSON(insertTrades, txId).getJSONObject("result"));
    }

    /**
     * Request to get query orders info
     * @param insertTrades: wheter to include trades related to position in output
     * @param userRef: restrict results to given user reference id
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @return query orders info as {@link String}
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     **/
    public String getQueryOrdersInfo(boolean insertTrades, long userRef, Long[] txId) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", apiRequest.assembleParamsList(",", txId));
        params.addParam("userref", userRef);
        return sendPostRequest(QUERY_ORDERS_ENDPOINT, params);
    }

    /** Request to get query orders info
     * @param insertTrades: wheter to include trades related to position in output
     * @param userRef: restrict results to given user reference id
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return query orders info as {@link JSONObject}
     * **/
    public JSONObject getQueryOrdersInfoJSON(boolean insertTrades, long userRef, Long[] txId) throws Exception {
        return new JSONObject(getQueryOrdersInfo(insertTrades, userRef, txId));
    }

    /** Request to get query orders info
     * @param insertTrades: wheter to include trades related to position in output
     * @param userRef: restrict results to given user reference id
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return query orders info list as {@link ArrayList} of {@link Order} custom object
     * **/
    public ArrayList<Order> getQueryOrdersInfoList(boolean insertTrades, long userRef, Long[] txId) throws Exception {
        return assembleOrdersList(getQueryOrdersInfoJSON(insertTrades, userRef, txId).getJSONObject("result"));
    }

    /**
     * Request to get query orders info
     * @param insertTrades: wheter to include trades related to position in output
     * @param userRef: restrict results to given user reference id
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     *            of {@link Long} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return query orders info as {@link String}
     **/
    public String getQueryOrdersInfo(boolean insertTrades, long userRef, ArrayList<Long> txId) throws Exception {
        return getQueryOrdersInfo(insertTrades, userRef, txId.toArray(new Long[txId.size()]));
    }

    /** Request to get query orders info
     * @param insertTrades: wheter to include trades related to position in output
     * @param userRef: restrict results to given user reference id
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     *            of {@link Long} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return query orders info as {@link JSONObject}
     * **/
    public JSONObject getQueryOrdersInfoJSON(boolean insertTrades, long userRef, ArrayList<Long> txId) throws Exception {
        return new JSONObject(getQueryOrdersInfo(insertTrades, userRef, txId));
    }

    /** Request to get query orders info
     * @param insertTrades: wheter to include trades related to position in output
     * @param userRef: restrict results to given user reference id
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     *            of {@link Long} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders</a>
     * @return query orders info list as {@link ArrayList} of {@link Order} custom object
     * **/
    public ArrayList<Order> getQueryOrdersInfoList(boolean insertTrades, long userRef, ArrayList<Long> txId) throws Exception {
        return assembleOrdersList(getQueryOrdersInfoJSON(insertTrades, userRef, txId).getJSONObject("result"));
    }

    /** Request to get trades history
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory</a>
     * @implSpec to get also count value use {@link #getTradesHistoryJSON(boolean)} or {@link #getTradesHistoryList(boolean)}
     * methods instead
     * @return trades history as {@link String}
     * **/
    public String getTradesHistory(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(GET_TRADES_HISTORY_ENDPOINT, params);
    }

    /** Request to get trades history
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory</a>
     * @implSpec count value is retriavable with length() method of {@link JSONObject} value returned
     * @return trades history as {@link JSONObject}
     * **/
    public JSONObject getTradesHistoryJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getTradesHistory(insertTrades));
    }

    /** Request to get trades history list
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory</a>
     * @implSpec count value is retriavable with size() method of {@link ArrayList} value returned
     * @return trades history list as {@link ArrayList} of {@link Order} custom object
     * **/
    public ArrayList<HistoryTrade> getTradesHistoryList(boolean insertTrades) throws Exception {
        return assebleHistoryTradesList(getTradesHistoryJSON(insertTrades));
    }

    /** Request to get trades history
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory</a>
     * @implSpec to get also count value use {@link #getTradesHistoryJSON(boolean)} or {@link #getTradesHistoryList(boolean)}
     * methods instead
     * @implNote keys for params accepted are: type,trades,start,start,end and ofs
     * @return trades history as {@link String}
     * **/
    public String getTradesHistory(Params params) throws Exception {
        return sendPostRequest(GET_TRADES_HISTORY_ENDPOINT, params);
    }

    /** Request to get trades history
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory</a>
     * @implSpec count value is retriavable with length() method of {@link JSONObject} value returned
     * @implNote keys for params accepted are: type,trades,start,start,end and ofs
     * @return trades history as {@link JSONObject}
     * **/
    public JSONObject getTradesHistoryJSON(Params params) throws Exception {
        return new JSONObject(getTradesHistory(params));
    }

    /** Request to get trades history list
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory</a>
     * @implSpec count value is retriavable with size() method of {@link ArrayList} value returned
     * @implNote keys for params accepted are: type,trades,start,start,end and ofs
     * @return trades history list as {@link ArrayList} of {@link Order} custom object
     * **/
    public ArrayList<HistoryTrade> getTradesHistoryList(Params params) throws Exception {
        return assebleHistoryTradesList(getTradesHistoryJSON(params));
    }

    /** Method to assemble a history trades list
     * @param jsonTrades: jsonObject obtained by response request
     * @return history trades list as {@link ArrayList} of {@link HistoryTrade}
     * **/
    private ArrayList<HistoryTrade> assebleHistoryTradesList(JSONObject jsonTrades) {
        ArrayList<HistoryTrade> trades = new ArrayList<>();
        jsonTrades = jsonTrades.getJSONObject("result").getJSONObject("trades");
        for (String trade : jsonTrades.keySet())
            trades.add(new HistoryTrade(jsonTrades.getJSONObject(trade), trade));
        return trades;
    }

    /** Request to get query trades info
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     * @return query trades info as {@link String}
     * **/
    public String getQueryTradesInfo(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(QUERY_TRADES_ENDPOINT, params);
    }

    /** Request to get query trades info
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     * @return query trades info as {@link JSONObject}
     * **/
    public JSONObject getQueryTradesInfoJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getQueryTradesInfo(insertTrades));
    }

    /** Request to get query trades info list
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     * @return query trades info list as {@link ArrayList} of {@link QueryTrade} custom object
     * **/
    public ArrayList<QueryTrade> getQueryTradesInfoList(boolean insertTrades) throws Exception {
        return assebleQueryTradesList(getQueryTradesInfoJSON(insertTrades));
    }

    /** Request to get query trades info
     * @param insertTrades: wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) is {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     * @return query trades info as {@link String}
     * **/
    public String getQueryTradesInfo(boolean insertTrades, String[] txId) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", txId);
        return sendPostRequest(QUERY_TRADES_ENDPOINT, params);
    }

    /** Request to get query trades info
     * @param insertTrades: wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) is {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     * @return query trades info as {@link JSONObject}
     * **/
    public JSONObject getQueryTradesInfoJSON(boolean insertTrades, String[] txId) throws Exception {
        return new JSONObject(getQueryTradesInfo(insertTrades, txId));
    }

    /** Request to get query trades info
     * @param insertTrades: wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) is {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     * @return query trades info list as {@link ArrayList} of {@link QueryTrade} custom object
     * **/
    public ArrayList<QueryTrade> getQueryTradesInfoList(boolean insertTrades, String[] txId) throws Exception {
        return assebleQueryTradesList(getQueryTradesInfoJSON(insertTrades, txId));
    }

    /** Request to get query trades info
     * @param insertTrades: wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) in {@link ArrayList} of
     *            {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     * @return query trades info as {@link String}
     * **/
    public String getQueryTradesInfo(boolean insertTrades, ArrayList<String> txId) throws Exception {
        return getQueryTradesInfo(insertTrades, txId.toArray(new String[txId.size()]));
    }

    /** Request to get query trades info
     * @param insertTrades: wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) in {@link ArrayList} of
     *            {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     * @return query trades info as {@link JSONObject}
     * **/
    public JSONObject getQueryTradesInfoJSON(boolean insertTrades, ArrayList<String> txId) throws Exception {
        return new JSONObject(getQueryTradesInfo(insertTrades, txId));
    }

    /** Request to get query trades info
     * @param insertTrades: wheter to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) in {@link ArrayList} of
     *            {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo</a>
     * @return query trades info list as {@link ArrayList} of {@link QueryTrade} custom object
     * **/
    public ArrayList<QueryTrade> getQueryTradesInfoList(boolean insertTrades, ArrayList<String> txId) throws Exception {
        return assebleQueryTradesList(getQueryTradesInfoJSON(insertTrades, txId));
    }

    /** Method to assemble a query trades list
     * @param jsonTrades: jsonObject obtained by response request
     * @return query trades list as {@link ArrayList} of {@link QueryTrade}
     * **/
    private ArrayList<QueryTrade> assebleQueryTradesList(JSONObject jsonTrades) {
        ArrayList<QueryTrade> trades = new ArrayList<>();
        for (String trade : jsonTrades.keySet())
            trades.add(new QueryTrade(jsonTrades.getJSONObject(trade), trade));
        return trades;
    }

    /** Request to get open positions
     * @param doCalcs: whether to include P&L calculations
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
     * @return open positions as {@link String}
     * **/
    public String getOpenPositions(boolean doCalcs) throws Exception {
        Params params = new Params();
        if(doCalcs)
            params.addParam("docalcs", true);
        return sendPostRequest(GET_OPEN_POSITIONS_ENDPOINT, params);
    }

    /** Request to get open positions
     * @param doCalcs: whether to include P&L calculations
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
     * @return open positions as {@link JSONObject}
     * **/
    public JSONObject getOpenPositionsJSON(boolean doCalcs) throws Exception {
        return new JSONObject(getOpenPositions(doCalcs));
    }

    /** Request to get open positions list
     * @param doCalcs: whether to include P&L calculations
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
     * @return open positions list as {@link ArrayList} of {@link OpenPosition} custom object
     * **/
    public ArrayList<OpenPosition> getOpenPositionsList(boolean doCalcs) throws Exception {
        return assembleOpenPositionsList(getOpenPositionsJSON(doCalcs));
    }

    /** Request to get open positions
     * @param doCalcs: whether to include P&L calculations
     * @param txId: comma delimited list of txids to limit output in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
     * @return open positions as {@link String}
     * **/
    public String getOpenPositions(boolean doCalcs, String[] txId) throws Exception {
        Params params = new Params();
        if(doCalcs)
            params.addParam("docalcs", true);
        params.addParam("txid", txId);
        return sendPostRequest(GET_OPEN_POSITIONS_ENDPOINT, params);
    }

    /** Request to get open positions
     * @param doCalcs: whether to include P&L calculations
     * @param txId: comma delimited list of txids to limit output in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
     * @return open positions as {@link JSONObject}
     * **/
    public JSONObject getOpenPositionsJSON(boolean doCalcs, String[] txId) throws Exception {
        return new JSONObject(getOpenPositions(doCalcs, txId));
    }

    /** Request to get open positions
     * @param doCalcs: whether to include P&L calculations
     * @param txId: comma delimited list of txids to limit output in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
     * @return open positions list as {@link ArrayList} of {@link OpenPosition} custom object
     * **/
    public ArrayList<OpenPosition> getOpenPositionsList(boolean doCalcs, String[] txId) throws Exception {
        return assembleOpenPositionsList(getOpenPositionsJSON(doCalcs, txId));
    }

    /** Request to get open positions
     * @param doCalcs: whether to include P&L calculations
     * @param txId: comma delimited list of txids to limit output in {@link ArrayList} of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
     * @return open positions as {@link String}
     * **/
    public String getOpenPositions(boolean doCalcs, ArrayList<String> txId) throws Exception {
        return getOpenPositions(doCalcs, txId.toArray(new String[txId.size()]));
    }

    /** Request to get open positions
     * @param doCalcs: whether to include P&L calculations
     * @param txId: comma delimited list of txids to limit output in {@link ArrayList} of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
     * @return open positions as {@link JSONObject}
     * **/
    public JSONObject getOpenPositionsJSON(boolean doCalcs, ArrayList<String> txId) throws Exception {
        return new JSONObject(getOpenPositions(doCalcs, txId));
    }

    /** Request to get open positions
     * @param doCalcs: whether to include P&L calculations
     * @param txId: comma delimited list of txids to limit output in {@link ArrayList} of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions</a>
     * @return open positions list as {@link ArrayList} of {@link OpenPosition} custom object
     * **/
    public ArrayList<OpenPosition> getOpenPositionsList(boolean doCalcs, ArrayList<String> txId) throws Exception {
        return assembleOpenPositionsList(getOpenPositionsJSON(doCalcs, txId));
    }

    /** Method to assemble an open positions list
     * @param jsonPositions: jsonObject obtained by response request
     * @return open positions list as {@link ArrayList} of {@link OpenPosition}
     * **/
    private ArrayList<OpenPosition> assembleOpenPositionsList(JSONObject jsonPositions) {
        ArrayList<OpenPosition> openPositions = new ArrayList<>();
        jsonPositions = jsonPositions.getJSONObject("result");
        for (String positionId : jsonPositions.keySet())
            openPositions.add(new OpenPosition(jsonPositions.getJSONObject(positionId), positionId));
        return openPositions;
    }

    /** Request to get ledgers info
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers</a>
     * @implSpec to get also count value use {@link #getLedgersInfoJSON(boolean)} or {@link #getLedgersInfoList(boolean)}
     * methods instead
     * @return ledgers info as {@link String}
     * **/
    public String getLedgersInfo(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(GET_LEDGERS_ENDPOINT, params);
    }

    /** Request to get ledgers info
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers</a>
     * @implSpec count value is retriavable with length() method of {@link JSONObject} value returned
     * @return ledgers info as {@link JSONObject}
     * **/
    public JSONObject getLedgersInfoJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getLedgersInfo(insertTrades));
    }

    /** Request to get ledgers info list
     * @param insertTrades: wheter to include trades related to position in output
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers</a>
     * @implSpec count value is retriavable with size() method of {@link ArrayList} value returned
     * @return ledgers info list as {@link ArrayList} of {@link Ledger} custom object
     * **/
    public ArrayList<Ledger> getLedgersInfoList(boolean insertTrades) throws Exception {
        return assembleLedgersList(getLedgersInfoJSON(insertTrades).getJSONObject("result")
                .getJSONObject("ledger"));
    }

    /** Request to get ledgers info
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers</a>
     * @implSpec to get also count value use {@link #getLedgersInfoJSON(Params)} or {@link #getLedgersInfoList(Params)}
     * methods instead
     * @implNote keys for params accepted are: asset,aclass,type,start,end and ofs
     * @return ledgers info as {@link String}
     * **/
    public String getLedgersInfo(Params params) throws Exception {
        return sendPostRequest(GET_LEDGERS_ENDPOINT, params);
    }

    /** Request to get ledgers info
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers</a>
     * @implSpec count value is retriavable with length() method of {@link JSONObject} value returned
     * @implNote keys for params accepted are: asset,aclass,type,start,end and ofs
     * @return ledgers info as {@link String}
     * **/
    public JSONObject getLedgersInfoJSON(Params params) throws Exception {
        return new JSONObject(getLedgersInfo(params));
    }

    /** Request to get ledgers info list
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers</a>
     * @implSpec count value is retriavable with size() method of {@link ArrayList} value returned
     * @implNote keys for params accepted are: asset,aclass,type,start,end and ofs
     * @return ledgers info list as {@link ArrayList} of {@link Ledger} custom object
     * **/
    public ArrayList<Ledger> getLedgersInfoList(Params params) throws Exception {
        return assembleLedgersList(getLedgersInfoJSON(params).getJSONObject("result").getJSONObject("ledger"));
    }

    /** Request to get query ledgers
     * @param insertTrades: wheter to include trades related to position in output
     * @param ids: comma delimited list of ledger ids to query info about (20 maximum) in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     * @return query ledgers as {@link String}
     * **/
    public String getQueryLedgers(boolean insertTrades, String... ids) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("id", apiRequest.assembleParamsList(",", ids));
        return sendPostRequest(QUERY_LEDGERS_ENDPOINT, params);
    }

    /** Request to get query ledgers
     * @param insertTrades: wheter to include trades related to position in output
     * @param ids: comma delimited list of ledger ids to query info about (20 maximum) in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     * @return query ledgers as {@link JSONObject}
     * **/
    public JSONObject getQueryLedgersJSON(boolean insertTrades, String... ids) throws Exception {
        return new JSONObject(getQueryLedgers(insertTrades, ids));
    }

    /** Request to get query ledgers list
     * @param insertTrades: wheter to include trades related to position in output
     * @param ids: comma delimited list of ledger ids to query info about (20 maximum) in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     * @return query ledgers list as {@link ArrayList} of {@link Ledger} custom object
     * **/
    public ArrayList<Ledger> getQueryLedgersList(boolean insertTrades, String... ids) throws Exception {
        return assembleLedgersList(getQueryLedgersJSON(insertTrades, ids).getJSONObject("result"));
    }

    /** Request to get query ledgers
     * @param insertTrades: wheter to include trades related to position in output
     * @param ids: comma delimited list of ledger ids to query info about (20 maximum) in {@link ArrayList}
     *           of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     * @return query ledgers as {@link String}
     * **/
    public String getQueryLedgers(boolean insertTrades, ArrayList<String> ids) throws Exception {
       return getQueryLedgers(insertTrades, ids.toArray(new String[ids.size()]));
    }

    /** Request to get query ledgers
     * @param insertTrades: wheter to include trades related to position in output
     * @param ids: comma delimited list of ledger ids to query info about (20 maximum) in {@link ArrayList}
     *          of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     * @return query ledgers as {@link JSONObject}
     * **/
    public JSONObject getQueryLedgersJSON(boolean insertTrades, ArrayList<String> ids) throws Exception {
        return new JSONObject(getQueryLedgers(insertTrades, ids));
    }

    /** Request to get query ledgers
     * @param insertTrades: wheter to include trades related to position in output
     * @param ids: comma delimited list of ledger ids to query info about (20 maximum) in {@link ArrayList}
     *          of {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     * @return query ledgers list as {@link ArrayList} of {@link Ledger} custom object
     * **/
    public ArrayList<Ledger> getQueryLedgersList(boolean insertTrades, ArrayList<String> ids) throws Exception {
        return assembleLedgersList(getQueryLedgersJSON(insertTrades, ids).getJSONObject("result"));
    }

    /** Method to assemble a ledgers list
     * @param jsonLedgers: jsonObject obtained by response request
     * @return ledgers list as {@link ArrayList} of {@link Ledger}
     * **/
    private ArrayList<Ledger> assembleLedgersList(JSONObject jsonLedgers) {
        ArrayList<Ledger> ledgers = new ArrayList<>();
        for (String ledgerId : jsonLedgers.keySet())
            ledgers.add(new Ledger(jsonLedgers.getJSONObject(ledgerId), ledgerId));
        return ledgers;
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
     * @return trade volume as {@link String}
     * **/
    public String getTradeVolume(String pair, boolean insertFeeInfo) throws Exception {
        Params params = new Params();
        if(insertFeeInfo)
            params.addParam("fee-info", true);
        return sendPostRequest(GET_TRADE_VOLUME_ENDPOINT + "?pair=" + pair, params);
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
     * @return trade volume as {@link JSONObject}
     * **/
    public JSONObject getTradeVolumeJSON(String pair, boolean insertFeeInfo) throws Exception {
        return new JSONObject(getTradeVolume(pair, insertFeeInfo));
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
     * @return trade volume as {@link TradeVolume} custom object
     * **/
    public TradeVolume getTradeVolumeObject(String pair, boolean insertFeeInfo) throws Exception {
        return new TradeVolume(getTradeVolumeJSON(pair, insertFeeInfo));
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs: comma delimited list of asset pairs to get fee info on (optional) in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
     * @return trade volume as {@link String}
     * **/
    public String getTradeVolume(String pair, boolean insertFeeInfo, String... pairs) throws Exception {
        Params params = new Params();
        if(insertFeeInfo)
            params.addParam("fee-info", true);
        params.addParam("pair", apiRequest.assembleParamsList(",", pairs));
        return sendPostRequest(GET_TRADE_VOLUME_ENDPOINT + "?pair=" + pair, params);
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs: comma delimited list of asset pairs to get fee info on (optional) in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
     * @return trade volume as {@link JSONObject}
     * **/
    public JSONObject getTradeVolumeJSON(String pair, boolean insertFeeInfo, String... pairs) throws Exception {
        return new JSONObject(getTradeVolume(pair, insertFeeInfo, pairs));
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs: comma delimited list of asset pairs to get fee info on (optional) in {@link String} array format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
     * @return trade volume as {@link TradeVolume} custom object
     * **/
    public TradeVolume getTradeVolumeObject(String pair, boolean insertFeeInfo, String... pairs) throws Exception {
        return new TradeVolume(getTradeVolumeJSON(pair, insertFeeInfo, pairs));
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs: comma delimited list of asset pairs to get fee info on (optional) in {@link ArrayList} of
     *             {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
     * @return trade volume as {@link String}
     * **/
    public String getTradeVolume(String pair, boolean insertFeeInfo, ArrayList<String> pairs) throws Exception {
        return getTradeVolume(pair, insertFeeInfo, pairs.toArray(new String[pairs.size()]));
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs: comma delimited list of asset pairs to get fee info on (optional) in {@link ArrayList} of
     *             {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
     * @return trade volume as {@link JSONObject}
     * **/
    public JSONObject getTradeVolumeJSON(String pair, boolean insertFeeInfo, ArrayList<String> pairs) throws Exception {
        return new JSONObject(getTradeVolume(pair, insertFeeInfo, pairs));
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs: comma delimited list of asset pairs to get fee info on (optional) in {@link ArrayList} of
     *             {@link String} format
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume</a>
     * @return trade volume as {@link TradeVolume} custom object
     * **/
    public TradeVolume getTradeVolumeObject(String pair, boolean insertFeeInfo, ArrayList<String> pairs) throws Exception {
        return new TradeVolume(getTradeVolumeJSON(pair, insertFeeInfo, pairs));
    }

    /** Request to add an export
     * @param report: type of data to export -> trades or ledgers
     * @param description: description for the export
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/addExport</a>
     * @return export as {@link String}
     * **/
    public String addExport(String report, String description) throws Exception {
        Params params = new Params();
        params.addParam("report", report);
        params.addParam("description", description);
        return sendPostRequest(ADD_EXPORT_ENDPOINT, params);
    }

    /** Request to add an export
     * @param report: type of data to export -> trades or ledgers
     * @param description: description for the export
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/addExport</a>
     * @return export as {@link JSONObject}
     * **/
    public JSONObject addExportJSON(String report, String description) throws Exception {
        return new JSONObject(addExport(report, description));
    }

    /** Request to add an export
     * @param report: type of data to export -> trades or ledgers
     * @param description: description for the export
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/addExport</a>
     * @return id of export report added as {@link String}
     * **/
    public String getExportIdAdded(String report, String description) throws Exception {
        return addExportJSON(report, description).getJSONObject("result").getString("id");
    }

    /** Request to add an export
     * @param report: type of data to export -> trades or ledgers
     * @param description: description for the export
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/addExport</a>
     * @implNote keys for params accepted are: format,fields,starttm end endtm
     * @return export as {@link String}
     * **/
    public String addExport(String report, String description, Params params) throws Exception {
        params.addParam("report", report);
        params.addParam("description", description);
        return sendPostRequest(ADD_EXPORT_ENDPOINT, params);
    }

    /** Request to add an export
     * @param report: type of data to export -> trades or ledgers
     * @param description: description for the export
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/addExport</a>
     * @implNote keys for params accepted are: format,fields,starttm end endtm
     * @return export as {@link JSONObject}
     * **/
    public JSONObject addExportJSON(String report, String description, Params params) throws Exception {
        return new JSONObject(addExport(report, description, params));
    }

    /** Request to add an export
     * @param report: type of data to export -> trades or ledgers
     * @param description: description for the export
     * @param params: custom params for the request
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/addExport</a>
     * @implNote keys for params accepted are: format,fields,starttm end endtm
     * @return id of export report added as {@link String}
     * **/
    public String getExportIdAdded(String report, String description, Params params) throws Exception {
        return addExportJSON(report, description, params).getJSONObject("result").getString("id");
    }

    /** Request to get export status
     * @param report: type of data to export -> trades or ledgers
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus</a>
     * @return export status as {@link String}
     * **/
    public String getExportStatus(String report) throws Exception {
        Params params = new Params();
        params.addParam("report", report);
        return sendPostRequest(GET_EXPORT_STATUS_ENDPOINT, params);
    }

    /** Request to get export status
     * @param report: type of data to export -> trades or ledgers
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus</a>
     * @return export status as {@link JSONObject}
     * **/
    public JSONObject getExportStatusJSON(String report) throws Exception {
        return new JSONObject(getExportStatus(report));
    }

    /** Request to get export status list
     * @param report: type of data to export -> trades or ledgers
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus</a>
     * @return export status list as {@link ArrayList} of {@link ReportStatus} custom object
     * **/
    public ArrayList<ReportStatus> getExportStatusList(String report) throws Exception {
        ArrayList<ReportStatus> reports = new ArrayList<>();
        JSONArray jsonReports = getExportStatusJSON(report).getJSONArray("result");
        for (int j = 0; j < jsonReports.length(); j++)
            reports.add(new ReportStatus(jsonReports.getJSONObject(j)));
        return reports;
    }

    /** Request to retrieve an export report
     * @param id: id of report to retrieve
     * @param reportName: name for report file zipped
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/retrieveExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/retrieveExport</a>
     * @implNote this method could not work properly because need different scenarios attemps to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/KrakenManager/issues/new">here</a>
     * with Kraken's API response es:
     * <pre><code>
     * {
     *   "error": [ ],
     *   "result": {
     *       "data": "data_example"
     *    }
     * }
     * </pre></code> hide personal data, and write about error that has been throwed. Thank you for help!
     * @return an export report as zipped {@link File}
     * **/
    public File retrieveDataExport(String id, String reportName) throws Exception {

        Params params = new Params();
        params.addParam("id", id);
        JSONArray chunks = new JSONArray(sendPostRequest(RETRIEVE_EXPORT_ENDPOINT, params));
        StringBuilder chunksBuilder = new StringBuilder();
        for (int j = 0; j < chunks.length(); j++)
            chunksBuilder.append(chunks.getString(j));
        if(!reportName.contains(".zip"))
            reportName += ".zip";
        File fileToZip = new File(reportName);
        FileWriter fileWriter = new FileWriter(fileToZip.getName());
        fileWriter.write(chunksBuilder.toString());
        fileWriter.flush();
        fileWriter.close();
        ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(reportName));
        outputStream.putNextEntry(new ZipEntry(reportName));
        FileInputStream inputStream = new FileInputStream(fileToZip);
        int binary;
        while ((binary = inputStream.read()) != -1)
            outputStream.write(binary);
        inputStream.close();
        outputStream.close();
        return fileToZip;
    }

    /** Request to remove an export report
     * @param id: id of report to delete or cancel
     * @param type: type report export -> cancel or delete
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport</a>
     * @return result of the operation as {@link String}
     * **/
    public String deleteExportReport(String id, String type) throws Exception {
        Params params = new Params();
        params.addParam("id", id);
        params.addParam("type", type);
        return sendPostRequest(DELETE_EXPORT_ENDPOINT, params);
    }

    /** Request to remove an export report
     * @param id: id of report to delete or cancel
     * @param type: type report export -> cancel or delete
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport</a>
     * @return result of the operation as {@link JSONObject}
     * **/
    public JSONObject deleteExportReportJSON(String id, String type) throws Exception {
        return new JSONObject(deleteExportReport(id, type));
    }

    /** Request to remove an export report
     * @param id: id of report to delete or cancel
     * @param type: type report export -> cancel or delete
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport</a>
     * @return result of the operation as boolean, when there is a {@link JSONException} will return false
     * **/
    public boolean getDeleteExportReportConfirm(String id, String type) throws Exception {
        try {
            return deleteExportReportJSON(id, type).getJSONObject("result").getBoolean(type);
        }catch (JSONException e){
            return false;
        }
    }

}
