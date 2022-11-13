package com.tecknobit.krakenmanager.managers.privates.usertrading;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.krakenmanager.managers.privates.KrakenPrivateManager;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order;
import com.tecknobit.krakenmanager.managers.privates.usertrading.records.batch.OrderBatch;
import com.tecknobit.krakenmanager.managers.privates.usertrading.records.batch.OrderBatchList;
import com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.krakenmanager.constants.EndpointsList.*;
import static com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.OrderAdded.addBaseOrderParameters;
import static com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.OrderEdited.addBaseEditParameters;

/**
 * The {@code KrakenUserTradingManager} class is useful to manage all user trading endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading">
 * https://docs.kraken.com/rest/#tag/User-Trading</a>
 **/
public class KrakenUserTradingManager extends KrakenPrivateManager {

    /** Constructor to init a {@link KrakenUserTradingManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserTradingManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenUserTradingManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserTradingManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserTradingManager}
     *
     * @param requestTimeout: custom timeout for request
     * @param apiKey:         api key of Kraken's platform
     * @param apiSign:        api sign of Kraken's platform
     **/
    public KrakenUserTradingManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserTradingManager}
     *
     * @param apiKey:  api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenUserTradingManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserTradingManager} <br>
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
    public KrakenUserTradingManager() {
        super();
    }

    /**
     * Request to send a market order
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair:   pair value
     * @param params: extra order details
     * @return market order result as {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     * https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     **/
    public String addMarketOrder(String type, double volume, String pair, Params params) throws Exception {
        return addOrder(Order.MARKET_ORDER_TYPE, type, volume, pair, params);
    }

    /**
     * Request to send a market order
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair:   pair value
     * @param params: extra order details
     * @return market order result as {@link JSONObject}
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     * https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     **/
    public JSONObject addMarketOrderJSON(String type, double volume, String pair, Params params) throws Exception {
        return new JSONObject(addMarketOrder(type, volume, pair, params));
    }

    /** Request to send a market order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return market order result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addMarketOrderObject(String type, double volume, String pair, Params params) throws Exception {
        return new OrderAdded(addMarketOrderJSON(type, volume, pair, params));
    }

    /** Request to send a market order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return market order result as {@link String}
     * **/
    public String addMarketOrder(String type, double volume, String pair) throws Exception {
        return addOrder(Order.MARKET_ORDER_TYPE, type, volume, pair, null);
    }

    /** Request to send a market order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return market order result as {@link JSONObject}
     * **/
    public JSONObject addMarketOrderJSON(String type, double volume, String pair) throws Exception {
        return new JSONObject(addMarketOrder(type, volume, pair, null));
    }

    /** Request to send a market order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return market order result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addMarketOrderObject(String type, double volume, String pair) throws Exception {
        return new OrderAdded(addMarketOrderJSON(type, volume, pair, null));
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return limit order result as {@link String}
     * **/
    public String addLimitOrder(String type, double volume, String pair, double price, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        return addOrder(Order.LIMIT_ORDER_TYPE, type, volume, pair, params);
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return limit order result as {@link JSONObject}
     * **/
    public JSONObject addLimitOrderJSON(String type, double volume, String pair, double price, Params params) throws Exception {
        return new JSONObject(addLimitOrder(type, volume, pair, price, params));
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return limit order result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addLimitOrderObject(String type, double volume, String pair, double price, Params params) throws Exception {
        return new OrderAdded(addLimitOrderJSON(type, volume, pair, price, params));
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return limit order result as {@link String}
     * **/
    public String addLimitOrder(String type, double volume, String pair, double price) throws Exception {
        return addLimitOrder(type, volume, pair, price, null);
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return limit order result as {@link JSONObject}
     * **/
    public JSONObject addLimitOrderJSON(String type, double volume, String pair, double price) throws Exception {
        return addLimitOrderJSON(type, volume, pair, price, null);
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return limit order result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addLimitOrderObject(String type, double volume, String pair, double price) throws Exception {
        return new OrderAdded(addLimitOrderJSON(type, volume, pair, price, null));
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return limit stop loss result as {@link String}
     * **/
    public String addStopLossOrder(String type, double volume, String pair, double price, String trigger,
                                   Params params) throws Exception {
        return addLevelOrder(Order.STOP_LOSS_ORDER_TYPE, type, volume, pair, price, trigger, params);
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return limit stop loss result as {@link JSONObject}
     * **/
    public JSONObject addStopLossOrderJSON(String type, double volume, String pair, double price, String trigger,
                                           Params params) throws Exception {
        return new JSONObject(addStopLossOrder(type, volume, pair, price, trigger, params));
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return limit stop loss result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addStopLossOrderObject(String type, double volume, String pair, double price, String trigger,
                                             Params params) throws Exception {
        return new OrderAdded(addStopLossOrderJSON(type, volume, pair, price, trigger, params));
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return limit stop loss result as {@link String}
     * **/
    public String addStopLossOrder(String type, double volume, String pair, double price, String trigger) throws Exception {
        return addStopLossOrder(type, volume, pair, price, trigger, null);
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return limit stop loss result as {@link JSONObject}
     * **/
    public JSONObject addStopLossOrderJSON(String type, double volume, String pair, double price,
                                           String trigger) throws Exception {
        return addStopLossOrderJSON(type, volume, pair, price, trigger, null);
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return limit stop loss result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addStopLossOrderObject(String type, double volume, String pair, double price,
                                             String trigger) throws Exception {
        return new OrderAdded(addStopLossOrderJSON(type, volume, pair, price, trigger, null));
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return stop loss limit result as {@link String}
     * **/
    public String addStopLossLimitOrder(String type, double volume, String pair, double price, double price2,
                                        String trigger, String offsetType, Params params) throws Exception {
        return addLevelLimitOrder(Order.STOP_LOSS_LIMIT_ORDER_TYPE, type, volume, pair, price, price2, trigger,
                offsetType, params);
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return stop loss limit result as {@link JSONObject}
     * **/
    public JSONObject addStopLossLimitOrderJSON(String type, double volume, String pair, double price, double price2,
                                                String trigger, String offsetType, Params params) throws Exception {
        return new JSONObject(addStopLossLimitOrder(type, volume, pair, price, price2, trigger, offsetType, params));
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return stop loss limit result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addStopLossLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType, Params params) throws Exception {
        return new OrderAdded(addStopLossLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, params));
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return stop loss limit result as {@link String}
     * **/
    public String addStopLossLimitOrder(String type, double volume, String pair, double price, double price2,
                                        String trigger, String offsetType) throws Exception {
        return addStopLossLimitOrder(type, volume, pair, price, price2, trigger, offsetType, null);
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return stop loss limit result as {@link JSONObject}
     * **/
    public JSONObject addStopLossLimitOrderJSON(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType) throws Exception {
        return addStopLossLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, null);
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return stop loss limit result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addStopLossLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType) throws Exception {
        return new OrderAdded(addStopLossLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, null));
    }

    /** Request to send a take profit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return take profit order result as {@link String}
     * **/
    public String addTakeProfitOrder(String type, double volume, String pair, double price, String trigger,
                                     Params params) throws Exception {
        return addLevelOrder(Order.TAKE_PROFIT_ORDER_TYPE, type, volume, pair, price, trigger, params);
    }

    /** Request to send a take profit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return take profit order result as {@link JSONObject}
     * **/
    public JSONObject addTakeProfitOrderJSON(String type, double volume, String pair, double price, String trigger,
                                             Params params) throws Exception {
        return new JSONObject(addTakeProfitOrder(type, volume, pair, price, trigger, params));
    }

    /** Request to send a take profit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return take profit order result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addTakeProfitOrderObject(String type, double volume, String pair, double price, String trigger,
                                               Params params) throws Exception {
        return new OrderAdded(addTakeProfitOrderJSON(type, volume, pair, price, trigger, params));
    }

    /** Request to send a take profit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return take profit order result as {@link String}
     * **/
    public String addTakeProfitOrder(String type, double volume, String pair, double price, String trigger) throws Exception {
        return addTakeProfitOrder(type, volume, pair, price, trigger, null);
    }

    /** Request to send a take profit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return take profit order result as {@link JSONObject}
     * **/
    public JSONObject addTakeProfitOrderJSON(String type, double volume, String pair, double price,
                                             String trigger) throws Exception {
        return addTakeProfitOrderJSON(type, volume, pair, price, trigger, null);
    }

    /** Request to send a take profit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return take profit order result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addTakeProfitOrderObject(String type, double volume, String pair, double price,
                                               String trigger) throws Exception {
        return new OrderAdded(addTakeProfitOrderJSON(type, volume, pair, price, trigger, null));
    }

    /** Request to send a take profit limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return take profit limit order result as {@link String}
     * **/
    public String addTakeProfitLimitOrder(String type, double volume, String pair, double price, double price2,
                                          String trigger, String offsetType, Params params) throws Exception {
        return addLevelLimitOrder(Order.TAKE_PROFIT_LIMIT_ORDER_TYPE, type, volume, pair, price, price2, trigger,
                offsetType, params);
    }

    /** Request to send a take profit limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return take profit limit order result as {@link JSONObject}
     * **/
    public JSONObject addTakeProfitLimitOrderJSON(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType, Params params) throws Exception {
        return new JSONObject(addTakeProfitLimitOrder(type, volume, pair, price, price2, trigger, offsetType, params));
    }

    /** Request to send a take profit limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     * @return take profit limit order result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addTakeProfitLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                    String trigger, String offsetType, Params params) throws Exception {
        return new OrderAdded(addTakeProfitLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, params));
    }

    /** Request to send a take profit limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return take profit limit order result as {@link String}
     * **/
    public String addTakeProfitLimitOrder(String type, double volume, String pair, double price, double price2,
                                          String trigger, String offsetType) throws Exception {
        return addTakeProfitLimitOrder(type, volume, pair, price, price2, trigger, offsetType, null);
    }

    /** Request to send a take profit limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return take profit limit order result as {@link JSONObject}
     * **/
    public JSONObject addTakeProfitLimitOrderJSON(String type, double volume, String pair, double price, double price2,
                                                  String trigger, String offsetType) throws Exception {
        return addTakeProfitLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, null);
    }

    /** Request to send a take profit limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
     * @return take profit limit order result as {@link OrderAdded} custom object
     * **/
    public OrderAdded addTakeProfitLimitOrderObject(String type, double volume, String pair, double price, double price2,
                                                    String trigger, String offsetType) throws Exception {
        return new OrderAdded(addTakeProfitLimitOrderJSON(type, volume, pair, price, price2, trigger, offsetType, null));
    }

    /** Method to send level order type
     * @param orderType: stop-loss or take-profit order type -> constants in {@link Order} class
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param params: extra order details
     * @return result of the order as {@link String}
     * **/
    private String addLevelOrder(String orderType, String type, double volume, String pair, double price,
                                 String trigger, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("trigger", trigger);
        return addOrder(orderType, type, volume, pair, params);
    }

    /** Method to send level limit order type
     * @param orderType: stop-loss-limit or take-profit-limit order type -> constants in {@link Order} class
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param price2: secondary price value
     * @param trigger: price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @return result of the order as {@link String}
     * **/
    private String addLevelLimitOrder(String orderType, String type, double volume, String pair, double price, double price2,
                                      String trigger, String offsetType, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("trigger", trigger);
        params.addParam("price2", offsetType + price2);
        return addOrder(orderType, type, volume, pair, params);
    }

    /** Method to send order type
     * @param orderType: stop-loss or take-profit order type -> constants in {@link Order} class
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param params: extra order details
     * @return result of the order as {@link String}
     * **/
    private String addOrder(String orderType, String type, double volume, String pair, Params params) throws Exception {
        addBaseOrderParameters(orderType, type, volume, pair, params);
        return sendPostRequest(ADD_ORDER_ENDPOINT, params);
    }

    /** Request to send a batch order
     * @param orderBatchList: list of orders as {@link OrderBatchList} custom object
     * @param params: order quantity in terms of the base asset
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch</a>
     * @implNote keys for params accepted are: deadline or validate
     * @return batch order result as {@link String}
     * **/
    public String addOrderBatch(OrderBatchList orderBatchList, Params params) throws Exception {
        if(orderBatchList == null)
            throw new IllegalArgumentException("Order batch list cannot be null");
        if(params == null)
            params = new Params();
        params.addParam("pair", orderBatchList.getPair());
        params.addParam("orders", orderBatchList.getOrders());
        return sendPostRequest(ADD_ORDER_BATCH_ENDPOINT, params);
    }

    /** Request to send a batch order
     * @param orderBatchList: list of orders as {@link OrderBatchList} custom object
     * @param params: order quantity in terms of the base asset
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch</a>
     * @implNote keys for params accepted are: deadline or validate
     * @return batch order result as {@link JSONObject}
     * **/
    public JSONObject addOrderBatchJSON(OrderBatchList orderBatchList, Params params) throws Exception {
        return new JSONObject(addOrderBatch(orderBatchList, params));
    }

    /** Request to send a batch order
     * @param orderBatchList: list of orders as {@link OrderBatchList} custom object
     * @param params: order quantity in terms of the base asset
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch</a>
     * @implNote keys for params accepted are: deadline or validate
     * @implSpec return type change by Kraken's response given
     * @return list of {@link OrderBatch} as {@link ArrayList} or {@link OrderAdded} custom object
     * **/
    public <T> T addOrderBatchObject(OrderBatchList orderBatchList, Params params) throws Exception {
        return createOrderBatchObject(addOrderBatchJSON(orderBatchList, params));
    }

    /** Request to send a batch order
     * @param orderBatchList: list of orders as {@link OrderBatchList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch</a>
     * @return batch order result as {@link String}
     * **/
    public String addOrderBatch(OrderBatchList orderBatchList) throws Exception {
        return addOrderBatch(orderBatchList, null);
    }

    /** Request to send a batch order
     * @param orderBatchList: list of orders as {@link OrderBatchList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch</a>
     * @return batch order result as {@link JSONObject}
     * **/
    public JSONObject addOrderBatchJSON(OrderBatchList orderBatchList) throws Exception {
        return addOrderBatchJSON(orderBatchList, null);
    }

    /** Request to send a batch order
     * @param orderBatchList: list of orders as {@link OrderBatchList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch</a>
     * @implNote keys for params accepted are: deadline or validate
     * @return list of {@link OrderBatch} as {@link ArrayList} or {@link OrderAdded} custom object
     * **/
    public <T> T addOrderBatchObject(OrderBatchList orderBatchList) throws Exception {
        return createOrderBatchObject(addOrderBatchJSON(orderBatchList, null));
    }

    /** Method to create result of order batch
     * @param jsonBatch: batch details in {@code "JSON"} format
     * @return list of {@link OrderBatch} as {@link ArrayList} or {@link OrderAdded} custom object
     * **/
    private <T> T createOrderBatchObject(JSONObject jsonBatch) {
        JSONArray orders = JsonHelper.getJSONArray(jsonBatch.getJSONObject("result"), "orders");
        if(orders != null){
            ArrayList<OrderBatch> batchOrders = new ArrayList<>();
            for (int j = 0; j < orders.length(); j++)
                batchOrders.add(new OrderBatch(orders.getJSONObject(j)));
            return (T) batchOrders;
        }
        return (T) new OrderAdded(jsonBatch);
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit market order result as {@link String}
     * **/
    public <T> String editMarketOrder(T orderId, String pair, double volume, Params params) throws Exception {
        return editOrder(orderId, pair, volume, params);
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit market order result as {@link JSONObject}
     * **/
    public <T> JSONObject editMarketOrderJSON(T orderId, String pair, double volume, Params params) throws Exception {
        return new JSONObject(editOrder(orderId, pair, volume, params));
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit market order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editMarketOrderObject(T orderId, String pair, double volume, Params params) throws Exception {
        return new OrderEdited(editMarketOrderJSON(orderId, pair, volume, params));
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit market order result as {@link String}
     * **/
    public <T> String editMarketOrder(T orderId, String pair, double volume) throws Exception {
        return editOrder(orderId, pair, volume, null);
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit market order result as {@link JSONObject}
     * **/
    public <T> JSONObject editMarketOrderJSON(T orderId, String pair, double volume) throws Exception {
        return editMarketOrderJSON(orderId, pair, volume, null);
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit market order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editMarketOrderObject(T orderId, String pair, double volume) throws Exception {
        return new OrderEdited(editMarketOrderJSON(orderId, pair, volume, null));
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit limit order result as {@link String}
     * **/
    public <T> String editLimitOrder(T orderId, String pair, double volume, double price, Params params) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, params);
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit limit order result as {@link JSONObject}
     * **/
    public <T> JSONObject editLimitOrderJSON(T orderId, String pair, double volume, double price,
                                         Params params) throws Exception {
        return new JSONObject(editLimitOrder(orderId, pair, volume, price, params));
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit limit order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editLimitOrderObject(T orderId, String pair, double volume, double price,
                                            Params params) throws Exception {
        return new OrderEdited(editLimitOrderJSON(orderId, pair, volume, price, params));
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit limit order result as {@link String}
     * **/
    public <T> String editLimitOrder(T orderId, String pair, double volume, double price) throws Exception {
        return editLimitOrder(orderId, pair, volume, price, null);
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit limit order result as {@link JSONObject}
     * **/
    public <T> JSONObject editLimitOrderJSON(T orderId, String pair, double volume, double price) throws Exception {
        return editLimitOrderJSON(orderId, pair, volume, price, null);
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit limit order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editLimitOrderObject(T orderId, String pair, double volume, double price) throws Exception {
        return new OrderEdited(editLimitOrderJSON(orderId, pair, volume, price, null));
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss order result as {@link String}
     * **/
    public <T> String editStopLossOrder(T orderId, String pair, double volume, double price, Params params) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, params);
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss order result as {@link JSONObject}
     * **/
    public <T> JSONObject editStopLossOrderJSON(T orderId, String pair, double volume, double price,
                                            Params params) throws Exception {
        return new JSONObject(editStopLossOrder(orderId, pair, volume, price, params));
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editStopLossOrderObject(T orderId, String pair, double volume, double price,
                                               Params params) throws Exception {
        return new OrderEdited(editStopLossOrderJSON(orderId, pair, volume, price, params));
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss order result as {@link String}
     * **/
    public <T> String editStopLossOrder(T orderId, String pair, double volume, double price) throws Exception {
        return editStopLossOrder(orderId, pair, volume, price, null);
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss order result as {@link JSONObject}
     * **/
    public <T> JSONObject editStopLossOrderJSON(T orderId, String pair, double volume, double price) throws Exception {
        return editStopLossOrderJSON(orderId, pair, volume, price, null);
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editStopLossOrderObject(T orderId, String pair, double volume, double price) throws Exception {
        return new OrderEdited(editStopLossOrderJSON(orderId, pair, volume, price, null));
    }

    /** Request to edit a stop loss limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss limit order result as {@link String}
     * **/
    public <T> String editStopLossLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                             String offsetType, Params params) throws Exception {
        return editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, params);
    }

    /** Request to edit a stop loss limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss limit order result as {@link JSONObject}
     * **/
    public <T> JSONObject editStopLossLimitOrderJSON(T orderId, String pair, double volume, double price, double price2,
                                                     String offsetType, Params params) throws Exception {
        return new JSONObject(editStopLossLimitOrder(orderId, pair, volume, price, price2, offsetType, params));
    }

    /** Request to edit a stop loss limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss limit order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editStopLossLimitOrderObject(T orderId, String pair, double volume, double price, double price2,
                                                        String offsetType, Params params) throws Exception {
        return new OrderEdited(editStopLossLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, params));
    }

    /** Request to edit a stop loss limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss limit order result as {@link String}
     * **/
    public <T> String editStopLossLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                             String offsetType) throws Exception {
        return editStopLossLimitOrder(orderId, pair, volume, price, price2, offsetType, null);
    }

    /** Request to edit a stop loss limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss limit order result as {@link JSONObject}
     * **/
    public <T> JSONObject editStopLossLimitOrderJSON(T orderId, String pair, double volume, double price, double price2,
                                                 String offsetType) throws Exception {
        return editStopLossLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, null);
    }

    /** Request to edit a stop loss limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit stop loss limit order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editStopLossLimitOrderObject(T orderId, String pair, double volume, double price, double price2,
                                                    String offsetType) throws Exception {
        return new OrderEdited(editStopLossLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, null));
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit order result as {@link String}
     * **/
    public <T> String editTakeProfitOrder(T orderId, String pair, double volume, double price, Params params) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, params);
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit order result as {@link JSONObject}
     * **/
    public <T> JSONObject editTakeProfitOrderJSON(T orderId, String pair, double volume, double price,
                                              Params params) throws Exception {
        return new JSONObject(editTakeProfitOrder(orderId, pair, volume, price, params));
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editTakeProfitOrderObject(T orderId, String pair, double volume, double price,
                                                 Params params) throws Exception {
        return new OrderEdited(editTakeProfitOrderJSON(orderId, pair, volume, price, params));
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit order result as {@link String}
     * **/
    public <T> String editTakeProfitOrder(T orderId, String pair, double volume, double price) throws Exception {
        return editTakeProfitOrder(orderId, pair, volume, price, null);
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit order result as {@link JSONObject}
     * **/
    public <T> JSONObject editTakeProfitOrderJSON(T orderId, String pair, double volume, double price) throws Exception {
        return editTakeProfitOrderJSON(orderId, pair, volume, price, null);
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editTakeProfitOrderObject(T orderId, String pair, double volume, double price) throws Exception {
        return new OrderEdited(editTakeProfitOrderJSON(orderId, pair, volume, price, null));
    }

    /** Request to edit a take profit limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit limit order result as {@link String}
     * **/
    public <T> String editTakeProfitLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                               String offsetType, Params params) throws Exception {
        return editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, params);
    }

    /** Request to edit a take profit limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit limit order result as {@link JSONObject}
     * **/
    public <T> JSONObject editTakeProfitLimitOrderJSON(T orderId, String pair, double volume, double price, double price2,
                                                       String offsetType, Params params) throws Exception {
        return new JSONObject(editTakeProfitLimitOrder(orderId, pair, volume, price, price2, offsetType, params));
    }

    /** Request to edit a take profit limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @implNote keys for params accepted are: userref,oflags,deadline,oflags,cancel_response or validate
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit limit order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editTakeProfitLimitOrderObject(T orderId, String pair, double volume, double price, double price2,
                                                          String offsetType, Params params) throws Exception {
        return new OrderEdited(editTakeProfitLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, params));
    }

    /** Request to edit a take profit limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit limit order result as {@link String}
     * **/
    public <T> String editTakeProfitLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                               String offsetType) throws Exception {
        return editTakeProfitLimitOrder(orderId, pair, volume, price, price2, offsetType, null);
    }

    /** Request to edit a take profit limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit limit order result as {@link JSONObject}
     * **/
    public <T> JSONObject editTakeProfitLimitOrderJSON(T orderId, String pair, double volume, double price, double price2,
                                                       String offsetType) throws Exception {
        return editTakeProfitLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, null);
    }

    /** Request to edit a take profit limit order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder</a>
     * @return edit take profit limit order result as {@link OrderEdited} custom object
     * **/
    public <T> OrderEdited editTakeProfitLimitOrderObject(T orderId, String pair, double volume, double price, double price2,
                                                          String offsetType) throws Exception {
        return new OrderEdited(editTakeProfitLimitOrderJSON(orderId, pair, volume, price, price2, offsetType, null));
    }

    /** Method to send an edit operation of orders with price
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param params: extra order details
     * @return result of edit order as {@link String}
     * **/
    private <T> String editPriceOrder(T orderId, String pair, double volume, double price, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        return editOrder(orderId, pair, volume, params);
    }

    /** Method to send an edit operation of limit orders with price
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details
     * @return result of edit limit order as {@link String}
     * **/
    private <T> String editPriceLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                           String offsetType, Params params) throws Exception {
        if(params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("price2", offsetType + price2);
        return editOrder(orderId, pair, volume, params);
    }

    /** Method to send an edit operation
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details
     * @return result of edit order as {@link String}
     * **/
    private <T> String editOrder(T orderId, String pair, double volume, Params params) throws Exception {
        addBaseEditParameters(orderId, pair, volume, params);
        return sendPostRequest(EDIT_ORDER_ENDPOINT, params);
    }

    /** Request to cancel an order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder</a>
     * @return result of order cancellation as {@link String}
     * **/
    public <T> String cancelOrder(T orderId) throws Exception {
        String idKey = "txid";
        if(orderId instanceof Number)
            idKey = "userref";
        Params params = new Params();
        params.addParam(idKey, orderId);
        return sendPostRequest(CANCEL_ORDER_ENDPOINT, params);
    }

    /** Request to cancel an order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder</a>
     * @return result of order cancellation as {@link JSONObject}
     * **/
    public <T> JSONObject cancelOrderJSON(T orderId) throws Exception {
        return new JSONObject(cancelOrder(orderId));
    }

    /** Request to cancel an order
     * @param orderId: order identifier can be string for {@code txid} use or long for {@code userref} use
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder</a>
     * @return result of order cancellation as {@link OrderCancelledStatus} custom object
     * **/
    public <T> OrderCancelledStatus cancelOrderObject(T orderId) throws Exception {
        return new OrderCancelledStatus(cancelOrderJSON(orderId));
    }

    /** Request to cancel all orders <br>
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders</a>
     * @return result of all orders cancellation as {@link String}
     * **/
    public String cancelAllOrders() throws Exception {
        return sendPostRequest(CANCEL_ALL_ORDERS_ENDPOINT, null);
    }

    /** Request to cancel all orders <br>
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders</a>
     * @return result of all orders cancellation as {@link JSONObject}
     * **/
    public JSONObject cancelAllOrdersJSON() throws Exception {
        return new JSONObject(cancelAllOrders());
    }

    /** Request to cancel all orders <br>
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders</a>
     * @return result of all orders cancellation as {@link OrderCancelled} custom object
     * **/
    public OrderCancelled cancelAllOrdersObject() throws Exception {
        return new OrderCancelled(cancelAllOrdersJSON());
    }

    /** Request to cancel all orders after a timestamp
     * @param timeout: duration (in seconds) to set/extend the timer by
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter</a>
     * @return result of orders cancellation as {@link String}
     * **/
    public String cancelAllOrdersAfter(int timeout) throws Exception {
        Params params = new Params();
        params.addParam("timeout", timeout);
        return sendPostRequest(CANCEL_ALL_ORDERS_AFTER_ENDPOINT, params);
    }

    /** Request to cancel all orders after a timestamp
     * @param timeout: duration (in seconds) to set/extend the timer by
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter</a>
     * @return result of orders cancellation as {@link JSONObject}
     * **/
    public JSONObject cancelAllOrdersAfterJSON(int timeout) throws Exception {
        return new JSONObject(cancelAllOrdersAfter(timeout));
    }

    /** Request to cancel all orders after a timestamp
     * @param timeout: duration (in seconds) to set/extend the timer by
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter</a>
     * @return result of orders cancellation as {@link OrderCancelledAfter} custom object
     * **/
    public OrderCancelledAfter cancelAllOrdersAfterObject(int timeout) throws Exception {
        return new OrderCancelledAfter(cancelAllOrdersAfterJSON(timeout));
    }

    /** Request to cancel a batch order
     * @param orderBatchIdList: list of orders identifier can be string for {@code txid} use or long for {@code userref} use
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrderBatch">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrderBatch</a>
     * @return result of batch order cancellation as {@link String}
     * **/
    public <T> String cancelOrderBatch(ArrayList<T> orderBatchIdList) throws Exception {
        Params params = new Params();
        params.addParam("txid", orderBatchIdList);
        return sendPostRequest(CANCEL_ORDER_BATCH_ENDPOINT, params);
    }

    /** Request to cancel a batch order
     * @param orderBatchIdList: list of orders identifier can be string for {@code txid} use or long for {@code userref} use
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrderBatch">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrderBatch</a>
     * @return result of batch order cancellation as {@link JSONObject}
     * **/
    public <T> JSONObject cancelOrderBatchJSON(ArrayList<T> orderBatchIdList) throws Exception {
        return new JSONObject(cancelOrderBatch(orderBatchIdList));
    }

    /** Request to cancel a batch order
     * @param orderBatchIdList: list of orders identifier can be string for {@code txid} use or long for {@code userref} use
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrderBatch">
     *     https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrderBatch</a>
     * @return result of batch order cancellation as {@link OrderCancelled} custom object
     * **/
    public <T> OrderCancelled cancelOrderBatchObject(ArrayList<T> orderBatchIdList) throws Exception {
        return new OrderCancelled(cancelOrderBatchJSON(orderBatchIdList));
    }

}
