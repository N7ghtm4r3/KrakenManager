package com.tecknobit.krakenmanager.managers.privates.userdata;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.krakenmanager.managers.privates.KrakenPrivateManager;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.Ledger;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.Ledger.LedgerType;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.OpenPosition;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.ReportStatus;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.ReportStatus.DeletionType;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.ReportStatus.ReportFormat;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.ReportStatus.ReportType;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.balance.AccountBalance;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.balance.TradeBalance;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.ClosedOrder;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.ClosedOrder.CloseTime;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.trades.HistoryTrade;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.trades.QueryTrade;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.trades.Trade.TradeType;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.trades.TradeVolume;
import com.tecknobit.krakenmanager.managers.publics.market.records.AssetPair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.tecknobit.krakenmanager.constants.EndpointsList.*;
import static com.tecknobit.krakenmanager.managers.KrakenManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code KrakenUserDataManager} class is useful to manage all user data endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data">
 * User Data</a>
 **/
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

    /**
     * Constructor to init a {@link KrakenUserDataManager}
     *
     * @param requestTimeout: custom timeout for request
     * @param apiKey:         api key of Kraken's platform
     * @param apiSign:        api sign of Kraken's platform
     **/
    public KrakenUserDataManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserDataManager}
     *
     * @param apiKey:  api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserDataManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserDataManager} <br>
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
    public KrakenUserDataManager() {
        super();
    }

    /** Request to get account balance<br>
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance">
     *     Get Account Balance</a>
     * @return account balance as {@link AccountBalance} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/Balance")
    public AccountBalance getAccountBalance() throws Exception {
        return getAccountBalance(LIBRARY_OBJECT);
    }

    /**
     * Request to get account balance
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return account balance as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance">
     * Get Account Balance</a>
     **/
    @Returner
    @RequestPath(path = "https://api.kraken.com/0/private/Balance")
    public <T> T getAccountBalance(ReturnFormat format) throws Exception {
        String accountBalanceResponse = sendPostRequest(GET_ACCOUNT_BALANCE_ENDPOINT, null);
        switch (format) {
            case JSON:
                return (T) new JSONObject(accountBalanceResponse);
            case LIBRARY_OBJECT:
                return (T) new AccountBalance(new JSONObject(accountBalanceResponse));
            default:
                return (T) accountBalanceResponse;
        }
    }

    /** Request to get trade balance<br>
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     Get Trade Balance</a>
     * @return trade balance as {@link TradeBalance} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeBalance")
    public TradeBalance getTradeBalance() throws Exception {
        return getTradeBalance(LIBRARY_OBJECT);
    }

    /** Request to get trade balance
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     Get Trade Balance</a>
     * @return trade balance as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeBalance")
    public <T> T getTradeBalance(ReturnFormat format) throws Exception {
        return returnTradeBalance(sendPostRequest(GET_TRADE_BALANCE_ENDPOINT, null), format);
    }

    /** Request to get trade balance
     * @param asset: asset to fetch trade balance details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     Get Trade Balance</a>
     * @return trade balance as {@link TradeBalance} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeBalance")
    public TradeBalance getTradeBalance(String asset) throws Exception {
        return getTradeBalance(asset, LIBRARY_OBJECT);
    }

    /** Request to get trade balance
     * @param asset: asset to fetch trade balance details
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeBalance">
     *     Get Trade Balance</a>
     * @return trade balance as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeBalance")
    public <T> T getTradeBalance(String asset, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return returnTradeBalance(sendPostRequest(GET_TRADE_BALANCE_ENDPOINT, params), format);
    }

    /**
     * Method to assemble a trade balance object
     *
     * @param tradeBalanceResponse: trade balance to format
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return trade balance as {"format"} defines
     **/
    @Returner
    private <T> T returnTradeBalance(String tradeBalanceResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(tradeBalanceResponse);
            case LIBRARY_OBJECT:
                return (T) new TradeBalance(new JSONObject(tradeBalanceResponse));
            default:
                return (T) tradeBalanceResponse;
        }
    }

    /**
     * Request to get open orders list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @return open orders as {@link ArrayList} of {@link Order} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     * Get Open Orders</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenOrders")
    public ArrayList<Order> getOpenOrdersList(boolean insertTrades) throws Exception {
        return getOpenOrdersList(insertTrades, LIBRARY_OBJECT);
    }

    /**
     * Request to get open orders list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return open orders list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     * Get Open Orders</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenOrders")
    public <T> T getOpenOrdersList(boolean insertTrades, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        return returnOrders(sendPostRequest(GET_OPEN_ORDERS_ENDPOINT, params), format);
    }

    /** Request to get open orders list
     * @param insertTrades: whether to include trades related to position in output
     * @param userRef: user reference id
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     *     Get Open Orders</a>
     * @return open orders as {@link ArrayList} of {@link Order} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenOrders")
    public ArrayList<Order> getOpenOrdersList(boolean insertTrades, long userRef) throws Exception {
        return getOpenOrdersList(insertTrades, userRef, LIBRARY_OBJECT);
    }

    /**
     * Request to get open orders list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return open orders list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenOrders">
     * Get Open Orders</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenOrders")
    public <T> T getOpenOrdersList(boolean insertTrades, long userRef, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        params.addParam("userref", userRef);
        return returnOrders(sendPostRequest(GET_OPEN_ORDERS_ENDPOINT, params), format);
    }

    /**
     * Request to get closed orders list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @return closed orders list as {@link ArrayList} of {@link ClosedOrder} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     * Get Closed Orders</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/ClosedOrders")
    public ArrayList<ClosedOrder> getClosedOrdersList(boolean insertTrades) throws Exception {
        return getClosedOrdersList(insertTrades, LIBRARY_OBJECT);
    }

    /** Request to get closed orders list
     * @param insertTrades: whether to include trades related to position in output
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     *     Get Closed Orders</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     * @return closed orders list as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/ClosedOrders")
    public <T> T getClosedOrdersList(boolean insertTrades, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        return returnClosedOrders(sendPostRequest(GET_CLOSED_ORDERS_ENDPOINT, params), format);
    }

    /**
     * Request to get closed orders list
     *
     * @param params: custom params for the request, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "trades"} -> whether or not to include trades related to position in output - [boolean, default false]
     *                    </li>
     *                    <li>
     *                        {@code "userref"} -> restrict results to given user reference id - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "ofs"} -> result offset for pagination - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "closetime"} -> which time to use to search, constants available at {@link CloseTime}
     *                        - [string, default both]
     *                    </li>
     *                </ul>
     * @return closed orders list as {@link ArrayList} of {@link ClosedOrder} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     * Get Closed Orders</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/ClosedOrders")
    public ArrayList<ClosedOrder> getClosedOrdersList(Params params) throws Exception {
        return getClosedOrdersList(params, LIBRARY_OBJECT);
    }

    /**
     * Request to get closed orders list
     *
     * @param params: custom params for the request, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "trades"} -> whether or not to include trades related to position in output - [boolean, default false]
     *                    </li>
     *                    <li>
     *                        {@code "userref"} -> restrict results to given user reference id - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "ofs"} -> result offset for pagination - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "closetime"} -> which time to use to search, constants available at {@link CloseTime}
     *                        - [string, default both]
     *                    </li>
     *                </ul>
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return closed orders list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getClosedOrders">
     * Get Closed Orders</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/ClosedOrders")
    public <T> T getClosedOrdersList(Params params, ReturnFormat format) throws Exception {
        return returnClosedOrders(sendPostRequest(GET_CLOSED_ORDERS_ENDPOINT, params), format);
    }

    /**
     * Method to assemble a closed orders list
     *
     * @param closedOrdersResponse: closed orders list to format
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return closed orders list as {"format"} defines
     **/
    @Returner
    private <T> T returnClosedOrders(String closedOrdersResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(closedOrdersResponse);
            case LIBRARY_OBJECT:
                ArrayList<ClosedOrder> closedOrders = new ArrayList<>();
                JSONObject jOrders = new JSONObject(closedOrdersResponse).getJSONObject("result")
                        .getJSONObject("closed");
                for (String closedOrder : jOrders.keySet())
                    closedOrders.add(new ClosedOrder(jOrders.getJSONObject(closedOrder)));
                return (T) closedOrders;
            default:
                return (T) closedOrdersResponse;
        }
    }

    /**
     * Request to get query orders info list
     *
     * @param insertTrades : whether to include trades related to position in output
     * @param txId:        comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     *                     of {@link Long} format
     * @return query orders info list as {@link ArrayList} of {@link Order} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
     * Query Orders Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryOrders")
    public ArrayList<Order> getQueryOrdersInfoList(boolean insertTrades, ArrayList<Long> txId) throws Exception {
        return getQueryOrdersInfoList(insertTrades, txId.toArray(new Long[0]), LIBRARY_OBJECT);
    }

    /**
     * Request to get query orders info list
     *
     * @param insertTrades : whether to include trades related to position in output
     * @param txId:        comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     *                     of {@link Long} format
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return orders list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
     * Query Orders Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryOrders")
    public <T> T getQueryOrdersInfoList(boolean insertTrades, ArrayList<Long> txId, ReturnFormat format) throws Exception {
        return getQueryOrdersInfoList(insertTrades, txId.toArray(txId.toArray(new Long[0])), format);
    }

    /**
     * Request to get query orders info list
     *
     * @param insertTrades : whether to include trades related to position in output
     * @param txId:        comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @return query orders info list as {@link ArrayList} of {@link Order} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
     * Query Orders Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryOrders")
    public ArrayList<Order> getQueryOrdersInfoList(boolean insertTrades, Long[] txId) throws Exception {
        return getQueryOrdersInfoList(insertTrades, txId, LIBRARY_OBJECT);
    }

    /**
     * Request to get query orders info list
     *
     * @param insertTrades : whether to include trades related to position in output
     * @param txId:        comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return orders list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
     * Query Orders Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryOrders")
    public <T> T getQueryOrdersInfoList(boolean insertTrades, Long[] txId, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", apiRequest.assembleParamsList(",", txId));
        return returnOrders(sendPostRequest(QUERY_ORDERS_ENDPOINT, params), format);
    }

    /** Request to get query orders info
     * @param insertTrades: whether to include trades related to position in output
     * @param userRef: restrict results to given user reference id
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     *            of {@link Long} format
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
     * Query Orders Info</a>
     * @return query orders info list as {@link ArrayList} of {@link Order} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryOrders")
    public ArrayList<Order> getQueryOrdersInfoList(boolean insertTrades, long userRef, ArrayList<Long> txId) throws Exception {
        return getQueryOrdersInfoList(insertTrades, userRef, txId.toArray(new Long[0]), LIBRARY_OBJECT);
    }

    /** Request to get query orders info
     * @param insertTrades: whether to include trades related to position in output
     * @param userRef: restrict results to given user reference id
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link ArrayList}
     *            of {@link Long} format
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
     * Query Orders Info</a>
     * @return orders list as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryOrders")
    public <T> T getQueryOrdersInfoList(boolean insertTrades, long userRef, ArrayList<Long> txId,
                                        ReturnFormat format) throws Exception {
        return getQueryOrdersInfoList(insertTrades, userRef, txId.toArray(new Long[0]), format);
    }

    /**
     * Request to get query orders info
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param userRef:      restrict results to given user reference id
     * @param txId:         comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @return query orders info list as {@link ArrayList} of {@link Order} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
     * Query Orders Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryOrders")
    public ArrayList<Order> getQueryOrdersInfoList(boolean insertTrades, long userRef, Long[] txId) throws Exception {
        return getQueryOrdersInfoList(insertTrades, userRef, txId, LIBRARY_OBJECT);
    }

    /** Request to get query orders info
     * @param insertTrades: whether to include trades related to position in output
     * @param userRef: restrict results to given user reference id
     * @param txId: comma delimited list of transaction ids to query info about (50 maximum) in {@link Long} array format
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOrdersInfo">
     * Query Orders Info</a>
     * @return orders list as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryOrders")
    public <T> T getQueryOrdersInfoList(boolean insertTrades, long userRef, Long[] txId, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", apiRequest.assembleParamsList(",", txId));
        params.addParam("userref", userRef);
        return returnOrders(sendPostRequest(QUERY_ORDERS_ENDPOINT, params), format);
    }

    /**
     * Method to assemble an orders list
     *
     * @param ordersResponse: orders list to format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return orders list as {"format"} defines
     **/
    @Returner
    private <T> T returnOrders(String ordersResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(ordersResponse);
            case LIBRARY_OBJECT:
                ArrayList<Order> openOrders = new ArrayList<>();
                JSONObject jOrders = new JSONObject(ordersResponse).getJSONObject("result");
                if (jOrders.has("open"))
                    jOrders = jOrders.getJSONObject("open");
                for (String order : jOrders.keySet())
                    openOrders.add(new Order(jOrders.getJSONObject(order)));
                return (T) openOrders;
            default:
                return (T) ordersResponse;
        }
    }

    /**
     * Request to get trades history list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @return trades history list as {@link ArrayList} of {@link HistoryTrade} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     * Get Trades History</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradesHistory")
    public ArrayList<HistoryTrade> getTradesHistory(boolean insertTrades) throws Exception {
        return getTradesHistory(insertTrades, LIBRARY_OBJECT);
    }

    /**
     * Request to get trades history list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return history trades list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     * Get Trades History</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradesHistory")
    public <T> T getTradesHistory(boolean insertTrades, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        return returnHistoryTrades(sendPostRequest(GET_TRADES_HISTORY_ENDPOINT, params), format);
    }

    /**
     * Request to get trades history list
     *
     * @param params: custom params for the request, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "type"} -> type of trade, constants available at {@link TradeType} - [string, default all]
     *                    </li>
     *                    <li>
     *                        {@code "trades"} -> whether or not to include trades related to position in output - [boolean, default false]
     *                    </li>
     *                    <li>
     *                        {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "ofs"} -> result offset for pagination - [integer]
     *                    </li>
     *                </ul>
     * @return trades history list as {@link ArrayList} of {@link HistoryTrade} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     * Get Trades History</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradesHistory")
    public ArrayList<HistoryTrade> getTradesHistory(Params params) throws Exception {
        return getTradesHistory(params, LIBRARY_OBJECT);
    }

    /**
     * Request to get trades history list
     *
     * @param params: custom params for the request, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "type"} -> type of trade, constants available at {@link TradeType} - [string, default all]
     *                    </li>
     *                    <li>
     *                        {@code "trades"} -> whether or not to include trades related to position in output - [boolean, default false]
     *                    </li>
     *                    <li>
     *                        {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "ofs"} -> result offset for pagination - [integer]
     *                    </li>
     *                </ul>
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return history trades list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeHistory">
     * Get Trades History</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradesHistory")
    public <T> T getTradesHistory(Params params, ReturnFormat format) throws Exception {
        return returnHistoryTrades(sendPostRequest(GET_TRADES_HISTORY_ENDPOINT, params), format);
    }

    /**
     * Method to assemble a history trades list
     *
     * @param tradesListResponse: history trades list to format
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return history trades list as {"format"} defines
     **/
    @Returner
    private <T> T returnHistoryTrades(String tradesListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(tradesListResponse);
            case LIBRARY_OBJECT:
                ArrayList<HistoryTrade> trades = new ArrayList<>();
                JSONObject jTrades = new JSONObject(tradesListResponse).getJSONObject("result")
                        .getJSONObject("trades");
                for (String trade : jTrades.keySet())
                    trades.add(new HistoryTrade(jTrades.getJSONObject(trade).put("tradeId", trade)));
                return (T) trades;
            default:
                return (T) tradesListResponse;
        }
    }

    /**
     * Request to get query trades info list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @return query trades info list as {@link ArrayList} of {@link QueryTrade} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     * Query Trades Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryTrades")
    public ArrayList<QueryTrade> getQueryTradesInfo(boolean insertTrades) throws Exception {
        return getQueryTradesInfo(insertTrades, LIBRARY_OBJECT);
    }

    /** Request to get query trades info list
     * @param insertTrades: whether to include trades related to position in output
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     Query Trades Info</a>
     * @return query trades list as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryTrades")
    public <T> T getQueryTradesInfo(boolean insertTrades, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        return returnQueryTrades(sendPostRequest(QUERY_TRADES_ENDPOINT, null), format);
    }

    /** Request to get query trades info
     * @param insertTrades: whether to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) in {@link ArrayList} of
     *            {@link String} format
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     Query Trades Info</a>
     * @return query trades info list as {@link ArrayList} of {@link QueryTrade} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryTrades")
    public ArrayList<QueryTrade> getQueryTradesInfo(boolean insertTrades, ArrayList<String> txId) throws Exception {
        return getQueryTradesInfo(insertTrades, txId.toArray(new String[0]), LIBRARY_OBJECT);
    }

    /** Request to get query trades info
     * @param insertTrades: whether to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) in {@link ArrayList} of
     *            {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     Query Trades Info</a>
     * @return query trades list as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryTrades")
    public <T> T getQueryTradesInfo(boolean insertTrades, ArrayList<String> txId, ReturnFormat format) throws Exception {
        return getQueryTradesInfo(insertTrades, txId.toArray(new String[0]), format);
    }

    /** Request to get query trades info
     * @param insertTrades: whether to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) is {@link String} array format
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     Query Trades Info</a>
     * @return query trades info list as {@link ArrayList} of {@link QueryTrade} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryTrades")
    public ArrayList<QueryTrade> getQueryTradesInfo(boolean insertTrades, String[] txId) throws Exception {
        return getQueryTradesInfo(insertTrades, txId, LIBRARY_OBJECT);
    }

    /** Request to get query trades info
     * @param insertTrades: whether to include trades related to position in output
     * @param txId: comma delimited list of transaction IDs to query info about (20 maximum) is {@link String} array format
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradesInfo">
     *     Query Trades Info</a>
     * @return query trades list as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryTrades")
    public <T> T getQueryTradesInfo(boolean insertTrades, String[] txId, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", Arrays.stream(txId).toList());
        return returnQueryTrades(sendPostRequest(QUERY_TRADES_ENDPOINT, params), format);
    }

    /**
     * Method to assemble a query trades list
     *
     * @param tradesListResponse: query trades list to format
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return query trades list as {"format"} defines
     **/
    @Returner
    private <T> T returnQueryTrades(String tradesListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(tradesListResponse);
            case LIBRARY_OBJECT:
                ArrayList<QueryTrade> trades = new ArrayList<>();
                JSONObject jTrades = new JSONObject(tradesListResponse).getJSONObject("result");
                for (String trade : jTrades.keySet())
                    trades.add(new QueryTrade(jTrades.getJSONObject(trade).put("tradeId", trade)));
                return (T) trades;
            default:
                return (T) tradesListResponse;
        }
    }

    /**
     * Request to get open positions list
     *
     * @param doCalcs: whether to include {@code "P&L"} calculations
     * @return open positions list as {@link ArrayList} of {@link OpenPosition} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     * Get Open Positions</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenPositions")
    public ArrayList<OpenPosition> getOpenPositions(boolean doCalcs) throws Exception {
        return getOpenPositions(doCalcs, LIBRARY_OBJECT);
    }

    /**
     * Request to get open positions list
     *
     * @param doCalcs: whether to include {@code "P&L"} calculations
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return open positions list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     * Get Open Positions</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenPositions")
    public <T> T getOpenPositions(boolean doCalcs, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (doCalcs)
            params.addParam("docalcs", true);
        return returnOpenPositions(sendPostRequest(GET_OPEN_POSITIONS_ENDPOINT, params), format);
    }

    /** Request to get open positions
     * @param doCalcs: whether to include {@code "P&L"} calculations
     * @param txId: comma delimited list of txids to limit output in {@link ArrayList} of {@link String} format
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     Get Open Positions</a>
     * @return open positions list as {@link ArrayList} of {@link OpenPosition} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenPositions")
    public ArrayList<OpenPosition> getOpenPositions(boolean doCalcs, ArrayList<String> txId) throws Exception {
        return getOpenPositions(doCalcs, txId.toArray(new String[0]), LIBRARY_OBJECT);
    }

    /** Request to get open positions
     * @param doCalcs: whether to include {@code "P&L"} calculations
     * @param txId: comma delimited list of txids to limit output in {@link ArrayList} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     Get Open Positions</a>
     * @return open positions list as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenPositions")
    public <T> T getOpenPositions(boolean doCalcs, ArrayList<String> txId, ReturnFormat format) throws Exception {
        return getOpenPositions(doCalcs, txId.toArray(new String[0]), format);
    }

    /**
     * Request to get open positions
     *
     * @param doCalcs: whether to include {@code "P&L"} calculations
     * @param txId:    comma delimited list of txids to limit output in {@link String} array format
     * @return open positions list as {@link ArrayList} of {@link OpenPosition} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     * Get Open Positions</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenPositions")
    public ArrayList<OpenPosition> getOpenPositions(boolean doCalcs, String[] txId) throws Exception {
        return getOpenPositions(doCalcs, txId, LIBRARY_OBJECT);
    }

    /** Request to get open positions
     * @param doCalcs: whether to include {@code "P&L"} calculations
     * @param txId: comma delimited list of txids to limit output in {@link String} array format
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getOpenPositions">
     *     Get Open Positions</a>
     * @return open positions list as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/OpenPositions")
    public <T> T getOpenPositions(boolean doCalcs, String[] txId, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (doCalcs)
            params.addParam("docalcs", true);
        params.addParam("txid", Arrays.stream(txId).toList());
        return returnOpenPositions(sendPostRequest(GET_OPEN_POSITIONS_ENDPOINT, params), format);
    }

    /**
     * Method to assemble an open positions list
     *
     * @param openPositionsResponse: open positions list to format
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return open positions list as {"format"} defines
     **/
    @Returner
    private <T> T returnOpenPositions(String openPositionsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(openPositionsResponse);
            case LIBRARY_OBJECT:
                ArrayList<OpenPosition> openPositions = new ArrayList<>();
                JSONObject jPositions = new JSONObject(openPositionsResponse).getJSONObject("result");
                for (String positionId : jPositions.keySet())
                    openPositions.add(new OpenPosition(jPositions.getJSONObject(positionId).put("positionId", positionId)));
                return (T) openPositions;
            default:
                return (T) openPositionsResponse;
        }
    }

    /**
     * Request to get ledgers info list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @return ledgers info list as {@link ArrayList} of {@link Ledger} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     * Query Ledgers</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public ArrayList<Ledger> getLedgersInfo(boolean insertTrades) throws Exception {
        return getLedgersInfo(insertTrades, LIBRARY_OBJECT);
    }

    /**
     * Request to get ledgers info list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return ledgers list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     * Query Ledgers</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public <T> T getLedgersInfo(boolean insertTrades, ReturnFormat format) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        return returnLedgers(sendPostRequest(GET_LEDGERS_ENDPOINT, params), format);
    }

    /**
     * Request to get ledgers info list
     *
     * @param params: custom params for the request, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "asset"} -> comma delimited list of assets to restrict output to - [string, default all]
     *                    </li>
     *                    <li>
     *                        {@code "aclass"} -> asset class - [string, default currency]
     *                    </li>
     *                    <li>
     *                        {@code "type"} -> type of ledger, constants available at {@link LedgerType} - [string, default all]
     *                    </li>
     *                    <li>
     *                        {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "ofs"} -> result offset for pagination - [integer]
     *                    </li>
     *                </ul>
     * @return ledgers info list as {@link ArrayList} of {@link Ledger} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     * Query Ledgers</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     * @implNote keys for params accepted are: asset,aclass,type,start,end and ofs
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public ArrayList<Ledger> getLedgersInfo(Params params) throws Exception {
        return returnLedgers(sendPostRequest(GET_LEDGERS_ENDPOINT, params), LIBRARY_OBJECT);
    }

    /**
     * Request to get ledgers info list
     *
     * @param params: custom params for the request, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "asset"} -> comma delimited list of assets to restrict output to - [string, default all]
     *                    </li>
     *                    <li>
     *                        {@code "aclass"} -> asset class - [string, default currency]
     *                    </li>
     *                    <li>
     *                        {@code "type"} -> type of ledger, constants available at {@link LedgerType} - [string, default all]
     *                    </li>
     *                    <li>
     *                        {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "ofs"} -> result offset for pagination - [integer]
     *                    </li>
     *                </ul>
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return ledgers list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgers">
     * Query Ledgers</a>
     * @implSpec count value is retrievable with size() method of {@link ArrayList} value returned
     * @implNote keys for params accepted are: asset,aclass,type,start,end and ofs
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public <T> T getLedgersInfo(Params params, ReturnFormat format) throws Exception {
        return returnLedgers(sendPostRequest(GET_LEDGERS_ENDPOINT, params), format);
    }

    /**
     * Request to get query ledgers
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param ids:          comma delimited list of ledger ids to query info about (20 maximum) in {@link ArrayList}
     *                      of {@link String} format
     * @return query ledgers list as {@link ArrayList} of {@link Ledger} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public ArrayList<Ledger> getLedgersInfo(boolean insertTrades, ArrayList<String> ids) throws Exception {
        return getLedgersInfo(insertTrades, LIBRARY_OBJECT, ids.toArray(new String[0]));
    }

    /**
     * Request to get query ledgers
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param ids:          comma delimited list of ledger ids to query info about (20 maximum) in {@link ArrayList}
     *                      of {@link String} format
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return ledgers list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public <T> T getLedgersInfo(boolean insertTrades, ArrayList<String> ids, ReturnFormat format) throws Exception {
        return getLedgersInfo(insertTrades, format, ids.toArray(new String[0]));
    }

    /**
     * Request to get query ledgers list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param ids:          comma delimited list of ledger ids to query info about (20 maximum) in {@link String} array format
     * @return query ledgers list as {@link ArrayList} of {@link Ledger} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public ArrayList<Ledger> getLedgersInfo(boolean insertTrades, String... ids) throws Exception {
        return getLedgersInfo(insertTrades, LIBRARY_OBJECT, ids);
    }

    /**
     * Request to get query ledgers list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param ids:          comma delimited list of ledger ids to query info about (20 maximum) in {@link String} array format
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return ledgers list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public <T> T getLedgersInfo(boolean insertTrades, ReturnFormat format, String... ids) throws Exception {
        Params params = new Params();
        if (insertTrades)
            params.addParam("trades", true);
        params.addParam("id", apiRequest.assembleParamsList(",", ids));
        return returnLedgers(sendPostRequest(QUERY_LEDGERS_ENDPOINT, params), format);
    }

    /** Request to get query ledgers
     * @param insertTrades: whether to include trades related to position in output
     * @param ids: comma delimited list of ledger ids to query info about (20 maximum) in {@link ArrayList}
     *          of {@link String} format
     * @param params: custom params for the request, keys accepted are:
     *              <ul>
     *                  <li>
     *                      {@code "asset"} -> comma delimited list of assets to restrict output to - [string, default all]
     *                  </li>
     *                  <li>
     *                      {@code "aclass"} -> asset class - [string, default currency]
     *                  </li>
     *                  <li>
     *                      {@code "type"} -> type of ledger, constants available at {@link LedgerType} - [string, default all]
     *                  </li>
     *                  <li>
     *                      {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                  </li>
     *                  <li>
     *                      {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                  </li>
     *                  <li>
     *                      {@code "ofs"} -> result offset for pagination - [integer]
     *                  </li>
     *              </ul>
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     * @return query ledgers list as {@link ArrayList} of {@link Ledger} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public ArrayList<Ledger> getLedgersInfo(boolean insertTrades, ArrayList<String> ids, Params params) throws Exception {
        return getLedgersInfo(insertTrades, params, LIBRARY_OBJECT, ids.toArray(new String[0]));
    }

    /** Request to get query ledgers
     * @param insertTrades: whether to include trades related to position in output
     * @param ids: comma delimited list of ledger ids to query info about (20 maximum) in {@link ArrayList}
     *          of {@link String} format
     * @param params: custom params for the request, keys accepted are:
     *              <ul>
     *                  <li>
     *                      {@code "asset"} -> comma delimited list of assets to restrict output to - [string, default all]
     *                  </li>
     *                  <li>
     *                      {@code "aclass"} -> asset class - [string, default currency]
     *                  </li>
     *                  <li>
     *                      {@code "type"} -> type of ledger, constants available at {@link LedgerType} - [string, default all]
     *                  </li>
     *                  <li>
     *                      {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                  </li>
     *                  <li>
     *                      {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                  </li>
     *                  <li>
     *                      {@code "ofs"} -> result offset for pagination - [integer]
     *                  </li>
     *              </ul>
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     *     https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     * @return ledgers list as {"format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public <T> T getLedgersInfo(boolean insertTrades, ArrayList<String> ids, Params params,
                                ReturnFormat format) throws Exception {
        return getLedgersInfo(insertTrades, params, format, ids.toArray(new String[0]));
    }

    /**
     * Request to get query ledgers list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param ids:          comma delimited list of ledger ids to query info about (20 maximum) in {@link String} array format
     * @param params:       custom params for the request, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "asset"} -> comma delimited list of assets to restrict output to - [string, default all]
     *                          </li>
     *                          <li>
     *                              {@code "aclass"} -> asset class - [string, default currency]
     *                          </li>
     *                          <li>
     *                              {@code "type"} -> type of ledger, constants available at {@link LedgerType} - [string, default all]
     *                          </li>
     *                          <li>
     *                              {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "ofs"} -> result offset for pagination - [integer]
     *                          </li>
     *                      </ul>
     * @return query ledgers list as {@link ArrayList} of {@link Ledger} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public ArrayList<Ledger> getLedgersInfo(boolean insertTrades, Params params, String... ids) throws Exception {
        return getLedgersInfo(insertTrades, params, LIBRARY_OBJECT, ids);
    }

    /**
     * Request to get query ledgers list
     *
     * @param insertTrades: whether to include trades related to position in output
     * @param ids:          comma delimited list of ledger ids to query info about (20 maximum) in {@link String} array format
     * @param params:       custom params for the request, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "asset"} -> comma delimited list of assets to restrict output to - [string, default all]
     *                          </li>
     *                          <li>
     *                              {@code "aclass"} -> asset class - [string, default currency]
     *                          </li>
     *                          <li>
     *                              {@code "type"} -> type of ledger, constants available at {@link LedgerType} - [string, default all]
     *                          </li>
     *                          <li>
     *                              {@code "start"} -> starting unix timestamp or order tx ID of results (exclusive) - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "end"} -> ending unix timestamp or order tx ID of results (inclusive) - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "ofs"} -> result offset for pagination - [integer]
     *                          </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return ledgers list as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo">
     * https://docs.kraken.com/rest/#tag/User-Data/operation/getLedgersInfo</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/QueryLedgers")
    public <T> T getLedgersInfo(boolean insertTrades, Params params, ReturnFormat format, String... ids) throws Exception {
        if (insertTrades)
            params.addParam("trades", true);
        params.addParam("id", apiRequest.assembleParamsList(",", ids));
        return returnLedgers(sendPostRequest(QUERY_LEDGERS_ENDPOINT, params), format);
    }

    /**
     * Method to assemble a ledgers list
     *
     * @param openPositionsResponse: ledgers list to format
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return ledgers list as {"format"} defines
     **/
    @Returner
    private <T> T returnLedgers(String openPositionsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(openPositionsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Ledger> ledgers = new ArrayList<>();
                JSONObject jLedgers = new JSONObject(openPositionsResponse).getJSONObject("result");
                for (String ledgerId : jLedgers.keySet())
                    ledgers.add(new Ledger(jLedgers.getJSONObject(ledgerId).put("ledgerId", ledgerId)));
                return (T) ledgers;
            default:
                return (T) openPositionsResponse;
        }
    }

    /**
     * Request to get trade volume
     *
     * @param pair:          pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @return trade volume as {@link TradeVolume} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     * Get Trade Volume</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public TradeVolume getTradeVolume(AssetPair pair, boolean insertFeeInfo) throws Exception {
        return getTradeVolume(pair.getAltName(), insertFeeInfo, LIBRARY_OBJECT);
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param format:           return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     Get Trade Volume</a>
     * @return trade volume as {"format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public <T> T getTradeVolume(AssetPair pair, boolean insertFeeInfo, ReturnFormat format) throws Exception {
        return getTradeVolume(pair.getAltName(), insertFeeInfo, format);
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     Get Trade Volume</a>
     * @return trade volume as {@link TradeVolume} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public TradeVolume getTradeVolume(String pair, boolean insertFeeInfo) throws Exception {
        return getTradeVolume(pair, insertFeeInfo, LIBRARY_OBJECT);
    }

    /**
     * Request to get trade volume
     *
     * @param pair:          pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return trade volume as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     * Get Trade Volume</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public <T> T getTradeVolume(String pair, boolean insertFeeInfo, ReturnFormat format) throws Exception {
        Params params = new Params();
        if(insertFeeInfo)
            params.addParam("fee-info", true);
        return returnTradeVolume(sendPostRequest(GET_TRADE_VOLUME_ENDPOINT + "?pair=" + pair, params), format);
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs: comma delimited list of asset pairs to get fee info on (optional) in {@link String} array format
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     Get Trade Volume</a>
     * @return trade volume as {@link TradeVolume} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public TradeVolume getTradeVolume(AssetPair pair, boolean insertFeeInfo, String... pairs) throws Exception {
        return getTradeVolume(pair.getAltName(), insertFeeInfo, LIBRARY_OBJECT, pairs);
    }

    /**
     * Request to get trade volume
     *
     * @param pair:          pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs:         comma delimited list of asset pairs to get fee info on (optional) in {@link String} array format
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return trade volume as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     * Get Trade Volume</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public <T> T getTradeVolume(AssetPair pair, boolean insertFeeInfo, ReturnFormat format,
                                String... pairs) throws Exception {
        return getTradeVolume(pair.getAltName(), insertFeeInfo, format, pairs);
    }

    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs: comma delimited list of asset pairs to get fee info on (optional) in {@link ArrayList} of
     *             {@link String} format
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     Get Trade Volume</a>
     * @return trade volume as {@link TradeVolume} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public TradeVolume getTradeVolume(AssetPair pair, boolean insertFeeInfo, ArrayList<String> pairs) throws Exception {
        return getTradeVolume(pair.getAltName(), insertFeeInfo, LIBRARY_OBJECT, pairs.toArray(new String[0]));
    }

    /**
     * Request to get trade volume
     *
     * @param pair:          pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs:         comma delimited list of asset pairs to get fee info on (optional) in {@link ArrayList} of
     *                       {@link String} format
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return trade volume as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     * Get Trade Volume</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public <T> T getTradeVolume(AssetPair pair, boolean insertFeeInfo, ArrayList<String> pairs,
                                ReturnFormat format) throws Exception {
        return getTradeVolume(pair.getAltName(), insertFeeInfo, LIBRARY_OBJECT, pairs.toArray(new String[0]));
    }
    
    /** Request to get trade volume
     * @param pair: pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs: comma delimited list of asset pairs to get fee info on (optional) in {@link ArrayList} of
     *             {@link String} format
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     *     Get Trade Volume</a>
     * @return trade volume as {@link TradeVolume} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public TradeVolume getTradeVolume(String pair, boolean insertFeeInfo, ArrayList<String> pairs) throws Exception {
        return getTradeVolume(pair, insertFeeInfo, LIBRARY_OBJECT, pairs.toArray(new String[0]));
    }

    /**
     * Request to get trade volume
     *
     * @param pair:          pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs:         comma delimited list of asset pairs to get fee info on (optional) in {@link ArrayList} of
     *                       {@link String} format
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return trade volume as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     * Get Trade Volume</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public <T> T getTradeVolume(String pair, boolean insertFeeInfo, ArrayList<String> pairs,
                                ReturnFormat format) throws Exception {
        return getTradeVolume(pair, insertFeeInfo, LIBRARY_OBJECT, pairs.toArray(new String[0]));
    }

    /**
     * Request to get trade volume
     *
     * @param pair:          pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs:         comma delimited list of asset pairs to get fee info on (optional) in {@link String} array format
     * @return trade volume as {@link TradeVolume} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     * Get Trade Volume</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public TradeVolume getTradeVolume(String pair, boolean insertFeeInfo, String... pairs) throws Exception {
        return getTradeVolume(pair, insertFeeInfo, LIBRARY_OBJECT, pairs);
    }

    /**
     * Request to get trade volume
     *
     * @param pair:          pair to get fee info
     * @param insertFeeInfo: whether to include fee info in results (optional)
     * @param pairs:         comma delimited list of asset pairs to get fee info on (optional) in {@link String} array format
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return trade volume as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getTradeVolume">
     * Get Trade Volume</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/TradeVolume?pair={pair}")
    public <T> T getTradeVolume(String pair, boolean insertFeeInfo, ReturnFormat format, String... pairs) throws Exception {
        Params params = new Params();
        if (insertFeeInfo)
            params.addParam("fee-info", true);
        params.addParam("pair", apiRequest.assembleParamsList(",", pairs));
        return returnTradeVolume(sendPostRequest(GET_TRADE_VOLUME_ENDPOINT + "?pair=" + pair, params), format);
    }

    /**
     * Method to assemble a volume balance object
     *
     * @param tradeVolumeResponse: trade volume to format
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return trade volume as {"format"} defines
     **/
    @Returner
    private <T> T returnTradeVolume(String tradeVolumeResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(tradeVolumeResponse);
            case LIBRARY_OBJECT:
                return (T) new TradeVolume(new JSONObject(tradeVolumeResponse));
            default:
                return (T) tradeVolumeResponse;
        }
    }

    /** Request to add an export
     * @param report: type of data to export -> trades or ledgers
     * @param description: description for the export
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     *     Request Export Report</a>
     * @return export as {@link String}
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddExport")
    public String addExport(ReportType report, String description) throws Exception {
        return addExport(report, description, LIBRARY_OBJECT);
    }

    /**
     * Request to add an export
     *
     * @param report:      type of data to export -> trades or ledgers
     * @param description: description for the export
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return export added as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     * Request Export Report</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddExport")
    public <T> T addExport(ReportType report, String description, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("report", report);
        params.addParam("description", description);
        return returnExportAdded(sendPostRequest(ADD_EXPORT_ENDPOINT, params), format);
    }

    /** Request to add an export
     * @param report: type of data to export -> trades or ledgers
     * @param description: description for the export
     * @param params: custom params for the request, keys accepted are:
     *              <ul>
     *                  <li>
     *                      {@code "format"} -> file format to export, constants available at {@link ReportFormat} - [string, default CSV]
     *                  </li>
     *                  <li>
     *                      {@code "fields"} -> comma-delimited list of fields to include - [string, default all]
     *                      <ul>
     *                          <li>
     *                              {@code "trades"}: ordertxid, time, ordertype, price, cost, fee, vol, margin, misc, ledgers
     *                          </li>
     *                          <li>
     *                              {@code "ledgers"}: refid, time, type, aclass, asset, amount, fee, balance
     *                          </li>
     *                      </ul>
     *                  </li>
     *                  <li>
     *                      {@code "starttm"} -> {@code "UNIX"} timestamp for report start time (default 1st of the current month) - [integer]
     *                  </li>
     *                  <li>
     *                      {@code "endtm"} -> {@code "UNIX"} timestamp for report end time (default now) - [integer]
     *                  </li>
     *              </ul>
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     *     Request Export Report</a>
     * @return export as {@link String}
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddExport")
    public String addExport(ReportType report, String description, Params params) throws Exception {
        return addExport(report, description, params, LIBRARY_OBJECT);
    }

    /**
     * Request to add an export
     *
     * @param report:      type of data to export -> trades or ledgers
     * @param description: description for the export
     * @param params:      custom params for the request, keys accepted are:
     *                     <ul>
     *                         <li>
     *                             {@code "format"} -> file format to export, constants available at {@link ReportFormat} - [string, default CSV]
     *                         </li>
     *                         <li>
     *                             {@code "fields"} -> comma-delimited list of fields to include - [string, default all]
     *                             <ul>
     *                                 <li>
     *                                     {@code "trades"}: ordertxid, time, ordertype, price, cost, fee, vol, margin, misc, ledgers
     *                                 </li>
     *                                 <li>
     *                                     {@code "ledgers"}: refid, time, type, aclass, asset, amount, fee, balance
     *                                 </li>
     *                             </ul>
     *                         </li>
     *                         <li>
     *                             {@code "starttm"} -> {@code "UNIX"} timestamp for report start time (default 1st of the current month) - [integer]
     *                         </li>
     *                         <li>
     *                             {@code "endtm"} -> {@code "UNIX"} timestamp for report end time (default now) - [integer]
     *                         </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return export added as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
     * Request Export Report</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddExport")
    public <T> T addExport(ReportType report, String description, Params params, ReturnFormat format) throws Exception {
        params.addParam("report", report);
        params.addParam("description", description);
        return returnExportAdded(sendPostRequest(ADD_EXPORT_ENDPOINT, params), format);
    }

    /**
     * Method to assemble an export added
     *
     * @param exportAddedResponse: export added to format
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return export added as {"format"} defines
     * @apiNote the {@link ReturnFormat#LIBRARY_OBJECT} format type in this case will return the {@code "id"} value
     * as {@link String}
     **/
    @Returner
    private <T> T returnExportAdded(String exportAddedResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(exportAddedResponse);
            case LIBRARY_OBJECT:
                return (T) new JSONObject(exportAddedResponse).getJSONObject("result").getString("id");
            default:
                return (T) exportAddedResponse;
        }
    }

    /** Request to get export status
     * @param report: type of data to export -> trades or ledgers
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus">
     *     Get Export Report Status</a>
     * @return export status as {@link String}
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/ExportStatus")
    public ArrayList<ReportStatus> getExportStatus(ReportType report) throws Exception {
        return getExportStatus(report, LIBRARY_OBJECT);
    }

    /**
     * Request to get export status list
     *
     * @param report: type of data to export -> trades or ledgers
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return export status as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus">
     * Get Export Report Status</a>
     **/
    @Returner
    @RequestPath(path = "https://api.kraken.com/0/private/ExportStatus")
    public <T> T getExportStatus(ReportType report, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("report", report);
        JSONObject reportResponse = new JSONObject(sendPostRequest(GET_EXPORT_STATUS_ENDPOINT, params));
        switch (format) {
            case JSON:
                return (T) reportResponse;
            case LIBRARY_OBJECT:
                ArrayList<ReportStatus> reports = new ArrayList<>();
                JSONArray jReports = reportResponse.getJSONArray("result");
                for (int j = 0; j < jReports.length(); j++)
                    reports.add(new ReportStatus(jReports.getJSONObject(j)));
                return (T) reports;
            default:
                return (T) reportResponse.toString();
        }
    }

    /** Request to retrieve an export report
     * @param id: id of report to retrieve
     * @param reportName: name for report file zipped
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/retrieveExport">
     *     Retrieve Data Export</a>
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
     * </pre></code> hide personal data, and write about error that has been thrown. Thank you for help!
     * @return an export report as zipped {@link File}
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/RetrieveExport")
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
     * @param type: type report export
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport">
     *     Delete Export Report</a>
     * @return result of the operation as {@link String}
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/RemoveExport")
    public boolean deleteExportReport(String id, DeletionType type) throws Exception {
        return Boolean.parseBoolean(deleteExportReport(id, type, LIBRARY_OBJECT));
    }

    /**
     * Request to remove an export report
     *
     * @param id:   id of report to delete or cancel
     * @param type: type report export
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport">
     * Delete Export Report</a>
     * @implSpec the {@link ReturnFormat#LIBRARY_OBJECT} format type in this case will return whether deletion has been successful
     * as boolean
     **/
    @Returner
    @RequestPath(path = "https://api.kraken.com/0/private/RemoveExport")
    public <T> T deleteExportReport(String id, DeletionType type, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("id", id);
        params.addParam("type", type);
        JSONObject deletion = new JSONObject(sendPostRequest(DELETE_EXPORT_ENDPOINT, params));
        switch (format) {
            case JSON:
                return (T) deletion;
            case LIBRARY_OBJECT:
                return (T) String.valueOf(deletion.getJSONObject("result").getBoolean("delete"));
            default:
                return (T) deletion.toString();
        }
    }

}
