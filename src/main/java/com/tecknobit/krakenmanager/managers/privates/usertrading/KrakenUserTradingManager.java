package com.tecknobit.krakenmanager.managers.privates.usertrading;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Returner.ReturnFormat;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.krakenmanager.managers.privates.KrakenPrivateManager;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order;
import com.tecknobit.krakenmanager.managers.privates.usertrading.records.batch.OrderBatch;
import com.tecknobit.krakenmanager.managers.privates.usertrading.records.batch.OrderBatchList;
import com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.*;
import com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.OrderAdded.StpType;
import com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.OrderAdded.TimeInForce;
import com.tecknobit.krakenmanager.managers.publics.market.records.AssetPair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tecknobit.apimanager.annotations.Returner.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.krakenmanager.constants.EndpointsList.*;
import static com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order.*;
import static com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order.OrderType.*;
import static com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.OrderAdded.addBaseOrderParameters;
import static com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders.OrderEdited.addBaseEditParameters;

/**
 * The {@code KrakenUserTradingManager} class is useful to manage all user trading endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading">
 * User Trading</a>
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
     * @return market order result as {@link OrderAdded} custom object
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addMarketOrder(Side type, double volume, AssetPair pair) throws Exception {
        return addOrder(market, type, volume, pair.getAltName(), null, LIBRARY_OBJECT);
    }

    /**
     * Request to send a market order
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair:   pair value
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return market order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addMarketOrder(Side type, double volume, AssetPair pair, ReturnFormat format) throws Exception {
        return addOrder(market, type, volume, pair.getAltName(), null, format);
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
     *    Add Order</a>
     * @return market order result as {@link OrderAdded} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addMarketOrder(Side type, double volume, String pair) throws Exception {
        return addOrder(market, type, volume, pair, null, LIBRARY_OBJECT);
    }

    /** Request to send a market order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return market order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addMarketOrder(Side type, double volume, String pair, ReturnFormat format) throws Exception {
        return addOrder(market, type, volume, pair, null, format);
    }

    /** Request to send a market order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *    Add Order</a>
     * @return market order result as {@link OrderAdded} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addMarketOrder(Side type, double volume, AssetPair pair, Params params) throws Exception {
        return addOrder(market, type, volume, pair.getAltName(), params, LIBRARY_OBJECT);
    }

    /**
     * Request to send a market order
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair:   pair value
     * @param params: extra order details, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                        with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                        id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                        uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                        strategy, etc. This allows clients to more readily cancel or query information about
     *                        orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                        where supported - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                    </li>
     *                    <li>
     *                        {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                        {@link StpType} - [string, default cancel-newest]
     *                    </li>
     *                    <li>
     *                        {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                    </li>
     *                    <li>
     *                        {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                        in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                        is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                        cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                        if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                        {@link TimeInForce} - [string, default GTC]
     *                    </li>
     *                    <li>
     *                        {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                        timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                        after which the matching engine should reject the new order request, in presence of
     *                        latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                    </li>
     *                    <li>
     *                        {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                    </li>
     *                </ul>
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return market order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addMarketOrder(Side type, double volume, AssetPair pair, Params params,
                                ReturnFormat format) throws Exception {
        return addOrder(market, type, volume, pair.getAltName(), params, format);
    }

    /**
     * Request to send a market order
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair:   pair value
     * @param params: extra order details, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                        with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                        id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                        uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                        strategy, etc. This allows clients to more readily cancel or query information about
     *                        orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                        where supported - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                    </li>
     *                    <li>
     *                        {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                        {@link StpType} - [string, default cancel-newest]
     *                    </li>
     *                    <li>
     *                        {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                    </li>
     *                    <li>
     *                        {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                        in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                        is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                        cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                        if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                        {@link TimeInForce} - [string, default GTC]
     *                    </li>
     *                    <li>
     *                        {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                        timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                        after which the matching engine should reject the new order request, in presence of
     *                        latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                    </li>
     *                    <li>
     *                        {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                    </li>
     *                </ul>
     * @return market order result as {@link OrderAdded} custom object
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
     * Add Order</a>
     * @implNote keys for params accepted are: userref,leverage,stp_type,oflags,timeinforce,starttm,expiretm,close[ordertype],
     * close[price],close[price2],deadline or validate
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addMarketOrder(Side type, double volume, String pair, Params params) throws Exception {
        return addOrder(market, type, volume, pair, params, LIBRARY_OBJECT);
    }

    /**
     * Request to send a market order
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair:   pair value
     * @param params: extra order details, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                        with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                        id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                        uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                        strategy, etc. This allows clients to more readily cancel or query information about
     *                        orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                        where supported - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                    </li>
     *                    <li>
     *                        {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                        {@link StpType} - [string, default cancel-newest]
     *                    </li>
     *                    <li>
     *                        {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                    </li>
     *                    <li>
     *                        {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                        in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                        is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                        cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                        if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                        {@link TimeInForce} - [string, default GTC]
     *                    </li>
     *                    <li>
     *                        {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                        timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                        after which the matching engine should reject the new order request, in presence of
     *                        latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                    </li>
     *                    <li>
     *                        {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                    </li>
     *                </ul>
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return market order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addMarketOrder(Side type, double volume, String pair, Params params,
                                ReturnFormat format) throws Exception {
        return addOrder(market, type, volume, pair, params, format);
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
     *    Add Order</a>
     * @return limit order result as {@link OrderAdded} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addLimitOrder(Side type, double volume, AssetPair pair, double price) throws Exception {
        return addLimitOrder(type, volume, pair.getAltName(), price, LIBRARY_OBJECT);
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return limit order result as {@code "format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addLimitOrder(Side type, double volume, AssetPair pair, double price, ReturnFormat format) throws Exception {
        return addLimitOrder(type, volume, pair.getAltName(), price, format);
    }

    /**
     * Request to send a limit order
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair:   pair value
     * @param price:  limit price for the order
     * @return limit order result as {@link OrderAdded} custom object
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
     * Add Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addLimitOrder(Side type, double volume, String pair, double price) throws Exception {
        return addLimitOrder(type, volume, pair, price, LIBRARY_OBJECT);
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return limit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addLimitOrder(Side type, double volume, String pair, double price, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("price", price);
        return addOrder(limit, type, volume, pair, params, format);
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *    Add Order</a>
     * @return limit order result as {@link OrderAdded} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addLimitOrder(Side type, double volume, AssetPair pair, double price, Params params) throws Exception {
        return addLimitOrder(type, volume, pair.getAltName(), price, params, LIBRARY_OBJECT);
    }

    /** Request to send a limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return limit order result as {@code "format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addLimitOrder(Side type, double volume, AssetPair pair, double price, Params params,
                               ReturnFormat format) throws Exception {
        return addLimitOrder(type, volume, pair.getAltName(), price, params, format);
    }

    /**
     * Request to send a limit order
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair:   pair value
     * @param price:  limit price for the order
     * @param params: extra order details, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                        with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                        id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                        uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                        strategy, etc. This allows clients to more readily cancel or query information about
     *                        orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                        where supported - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                    </li>
     *                    <li>
     *                        {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                        {@link StpType} - [string, default cancel-newest]
     *                    </li>
     *                    <li>
     *                        {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                    </li>
     *                    <li>
     *                        {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                        in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                        is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                        cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                        if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                        {@link TimeInForce} - [string, default GTC]
     *                    </li>
     *                    <li>
     *                        {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                        timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                        after which the matching engine should reject the new order request, in presence of
     *                        latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                    </li>
     *                    <li>
     *                        {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                    </li>
     *                </ul>
     * @return limit order result as {@link OrderAdded} custom object
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
     * Add Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addLimitOrder(Side type, double volume, String pair, double price, Params params) throws Exception {
        return addLimitOrder(type, volume, pair, price, params, LIBRARY_OBJECT);
    }

    /**
     * Request to send a limit order
     *
     * @param type:   order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair:   pair value
     * @param price:  limit price for the order
     * @param params: extra order details, keys accepted are:
     *                <ul>
     *                    <li>
     *                        {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                        with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                        id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                        uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                        strategy, etc. This allows clients to more readily cancel or query information about
     *                        orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                        where supported - [integer]
     *                    </li>
     *                    <li>
     *                        {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                    </li>
     *                    <li>
     *                        {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                        {@link StpType} - [string, default cancel-newest]
     *                    </li>
     *                    <li>
     *                        {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                    </li>
     *                    <li>
     *                        {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                        in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                        is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                        cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                        if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                        {@link TimeInForce} - [string, default GTC]
     *                    </li>
     *                    <li>
     *                        {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                        timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                        <ul>
     *                                            <li>
     *                                                {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                            </li>
     *                                            <li>
     *                                                {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                            </li>
     *                                        </ul>
     *                    </li>
     *                    <li>
     *                        {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                        after which the matching engine should reject the new order request, in presence of
     *                        latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                    </li>
     *                    <li>
     *                        {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                    </li>
     *                </ul>
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return limit order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addLimitOrder(Side type, double volume, String pair, double price, Params params,
                               ReturnFormat format) throws Exception {
        if (params == null)
            params = new Params();
        params.addParam("price", price);
        return addOrder(limit, type, volume, pair, params, format);
    }

    /**
     * Request to send a stop loss order
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param pair:    pair value
     * @param price:   trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @return limit stop loss result as {@link OrderAdded} custom object
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addStopLossOrder(Side type, double volume, AssetPair pair, double price,
                                       Trigger trigger) throws Exception {
        return addLevelOrder(stop_loss, type, volume, pair.getAltName(), price, trigger, null, LIBRARY_OBJECT);
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return limit stop loss result as {@code "format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addStopLossOrder(Side type, double volume, AssetPair pair, double price, Trigger trigger,
                                  ReturnFormat format) throws Exception {
        return addLevelOrder(stop_loss, type, volume, pair.getAltName(), price, trigger, null, format);
    }

    /**
     * Request to send a stop loss order
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param pair:    pair value
     * @param price:   trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @return limit stop loss result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addStopLossOrder(Side type, double volume, String pair, double price, Trigger trigger) throws Exception {
        return addLevelOrder(stop_loss, type, volume, pair, price, trigger, null, LIBRARY_OBJECT);
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return limit stop loss result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addStopLossOrder(Side type, double volume, String pair, double price, Trigger trigger,
                                  ReturnFormat format) throws Exception {
        return addLevelOrder(stop_loss, type, volume, pair, price, trigger, null, format);
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *    Add Order</a>
     * @return limit stop loss result as {@link OrderAdded} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addStopLossOrder(Side type, double volume, AssetPair pair, double price, Trigger trigger,
                                       Params params) throws Exception {
        return addLevelOrder(stop_loss, type, volume, pair.getAltName(), price, trigger, params, LIBRARY_OBJECT);
    }

    /**
     * Request to send a stop loss order
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param pair:    pair value
     * @param price:   trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params:  extra order details, keys accepted are:
     *                 <ul>
     *                     <li>
     *                         {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                         with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                         id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                         uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                         strategy, etc. This allows clients to more readily cancel or query information about
     *                         orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                         where supported - [integer]
     *                     </li>
     *                     <li>
     *                         {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                     </li>
     *                     <li>
     *                         {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                         {@link StpType} - [string, default cancel-newest]
     *                     </li>
     *                     <li>
     *                         {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                     </li>
     *                     <li>
     *                         {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                         in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                         is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                         cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                         if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                         {@link TimeInForce} - [string, default GTC]
     *                     </li>
     *                     <li>
     *                         {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                         timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                         after which the matching engine should reject the new order request, in presence of
     *                         latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                     </li>
     *                     <li>
     *                         {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                     </li>
     *                 </ul>
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return limit stop loss result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addStopLossOrder(Side type, double volume, AssetPair pair, double price, Trigger trigger,
                                  Params params, ReturnFormat format) throws Exception {
        return addLevelOrder(stop_loss, type, volume, pair.getAltName(), price, trigger, params, format);
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *    Add Order</a>
     * @return limit stop loss result as {@link OrderAdded} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addStopLossOrder(Side type, double volume, String pair, double price, Trigger trigger,
                                       Params params) throws Exception {
        return addLevelOrder(stop_loss, type, volume, pair, price, trigger, params, LIBRARY_OBJECT);
    }

    /** Request to send a stop loss order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return limit stop loss result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addStopLossOrder(Side type, double volume, String pair, double price, Trigger trigger,
                                  Params params, ReturnFormat format) throws Exception {
        return addLevelOrder(stop_loss, type, volume, pair, price, trigger, params, format);
    }

    /**
     * Request to send a stop loss limit order
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param pair:       pair value
     * @param price:      signal price for the order
     * @param price2:     secondary price for the order
     * @param trigger:    trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @return stop loss limit result as {@link OrderAdded} custom object
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addStopLossLimitOrder(Side type, double volume, AssetPair pair, double price, double price2,
                                            Trigger trigger, String offsetType) throws Exception {
        return addLevelLimitOrder(stop_loss_limit, type, volume, pair.getAltName(), price, price2, trigger,
                offsetType, null, LIBRARY_OBJECT);
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return stop loss limit result as {@code "format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addStopLossLimitOrder(Side type, double volume, AssetPair pair, double price, double price2,
                                       Trigger trigger, String offsetType, ReturnFormat format) throws Exception {
        return addLevelLimitOrder(stop_loss_limit, type, volume, pair.getAltName(), price, price2, trigger,
                offsetType, null, format);
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
     *    Add Order</a>
     * @return stop loss limit result as {@link OrderAdded} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addStopLossLimitOrder(Side type, double volume, String pair, double price, double price2,
                                            Trigger trigger, String offsetType) throws Exception {
        return addLevelLimitOrder(stop_loss_limit, type, volume, pair, price, price2, trigger,
                offsetType, null, LIBRARY_OBJECT);
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return stop loss limit result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addStopLossLimitOrder(Side type, double volume, String pair, double price, double price2,
                                       Trigger trigger, String offsetType, ReturnFormat format) throws Exception {
        return addLevelLimitOrder(stop_loss_limit, type, volume, pair, price, price2, trigger,
                offsetType, null, format);
    }

    /**
     * Request to send a stop loss limit order
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param pair:       pair value
     * @param price:      signal price for the order
     * @param price2:     secondary price for the order
     * @param trigger:    trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details, keys accepted are:
     *                    <ul>
     *                        <li>
     *                            {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                            with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                            id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                            uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                            strategy, etc. This allows clients to more readily cancel or query information about
     *                            orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                            where supported - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                            {@link StpType} - [string, default cancel-newest]
     *                        </li>
     *                        <li>
     *                            {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                            in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                            is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                            cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                            if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                            {@link TimeInForce} - [string, default GTC]
     *                        </li>
     *                        <li>
     *                            {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                            timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                            after which the matching engine should reject the new order request, in presence of
     *                            latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                        </li>
     *                        <li>
     *                            {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                        </li>
     *                    </ul>
     * @return stop loss limit result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addStopLossLimitOrder(Side type, double volume, AssetPair pair, double price, double price2,
                                            Trigger trigger, String offsetType, Params params) throws Exception {
        return addLevelLimitOrder(stop_loss_limit, type, volume, pair.getAltName(), price, price2, trigger, offsetType,
                params, LIBRARY_OBJECT);
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return stop loss limit result as {@code "format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addStopLossLimitOrder(Side type, double volume, AssetPair pair, double price, double price2,
                                       Trigger trigger, String offsetType, Params params,
                                       ReturnFormat format) throws Exception {
        return addLevelLimitOrder(stop_loss_limit, type, volume, pair.getAltName(), price, price2, trigger, offsetType,
                params, format);
    }

    /** Request to send a stop loss limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *    Add Order</a>
     * @return stop loss limit result as {@link OrderAdded} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addStopLossLimitOrder(Side type, double volume, String pair, double price, double price2,
                                            Trigger trigger, String offsetType, Params params) throws Exception {
        return addLevelLimitOrder(stop_loss_limit, type, volume, pair, price, price2, trigger, offsetType, params,
                LIBRARY_OBJECT);
    }

    /**
     * Request to send a stop loss limit order
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param pair:       pair value
     * @param price:      signal price for the order
     * @param price2:     secondary price for the order
     * @param trigger:    trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details, keys accepted are:
     *                    <ul>
     *                        <li>
     *                            {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                            with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                            id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                            uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                            strategy, etc. This allows clients to more readily cancel or query information about
     *                            orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                            where supported - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                            {@link StpType} - [string, default cancel-newest]
     *                        </li>
     *                        <li>
     *                            {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                            in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                            is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                            cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                            if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                            {@link TimeInForce} - [string, default GTC]
     *                        </li>
     *                        <li>
     *                            {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                            timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                            after which the matching engine should reject the new order request, in presence of
     *                            latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                        </li>
     *                        <li>
     *                            {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                        </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return stop loss limit result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addStopLossLimitOrder(Side type, double volume, String pair, double price, double price2,
                                       Trigger trigger, String offsetType, Params params,
                                       ReturnFormat format) throws Exception {
        return addLevelLimitOrder(stop_loss_limit, type, volume, pair, price, price2, trigger, offsetType, params,
                format);
    }

    /**
     * Request to send a take profit order
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param pair:    pair value
     * @param price:   trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @return take profit order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addTakeProfitOrder(Side type, double volume, AssetPair pair, double price,
                                         Trigger trigger) throws Exception {
        return addLevelOrder(take_profit, type, volume, pair.getAltName(), price, trigger, null, LIBRARY_OBJECT);
    }

    /**
     * Request to send a take profit order
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param pair:    pair value
     * @param price:   trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return take profit order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addTakeProfitOrder(Side type, double volume, AssetPair pair, double price, Trigger trigger,
                                    ReturnFormat format) throws Exception {
        return addLevelOrder(take_profit, type, volume, pair.getAltName(), price, trigger, null, format);
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
     *    Add Order</a>
     * @return take profit order result as {@link OrderAdded} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addTakeProfitOrder(Side type, double volume, String pair, double price,
                                         Trigger trigger) throws Exception {
        return addLevelOrder(take_profit, type, volume, pair, price, trigger, null, LIBRARY_OBJECT);
    }

    /** Request to send a take profit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return take profit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addTakeProfitOrder(Side type, double volume, String pair, double price, Trigger trigger,
                                    ReturnFormat format) throws Exception {
        return addLevelOrder(take_profit, type, volume, pair, price, trigger, null, format);
    }

    /**
     * Request to send a take profit order
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param pair:    pair value
     * @param price:   trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params:  extra order details, keys accepted are:
     *                 <ul>
     *                     <li>
     *                         {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                         with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                         id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                         uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                         strategy, etc. This allows clients to more readily cancel or query information about
     *                         orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                         where supported - [integer]
     *                     </li>
     *                     <li>
     *                         {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                     </li>
     *                     <li>
     *                         {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                         {@link StpType} - [string, default cancel-newest]
     *                     </li>
     *                     <li>
     *                         {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                     </li>
     *                     <li>
     *                         {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                         in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                         is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                         cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                         if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                         {@link TimeInForce} - [string, default GTC]
     *                     </li>
     *                     <li>
     *                         {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                         timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                         after which the matching engine should reject the new order request, in presence of
     *                         latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                     </li>
     *                     <li>
     *                         {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                     </li>
     *                 </ul>
     * @return take profit order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addTakeProfitOrder(Side type, double volume, AssetPair pair, double price, Trigger trigger,
                                         Params params) throws Exception {
        return addLevelOrder(take_profit, type, volume, pair.getAltName(), price, trigger, params, LIBRARY_OBJECT);
    }

    /**
     * Request to send a take profit order
     *
     * @param type:    order direction -> buy or sell
     * @param volume:  order quantity in terms of the base asset
     * @param pair:    pair value
     * @param price:   trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params:  extra order details, keys accepted are:
     *                 <ul>
     *                     <li>
     *                         {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                         with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                         id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                         uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                         strategy, etc. This allows clients to more readily cancel or query information about
     *                         orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                         where supported - [integer]
     *                     </li>
     *                     <li>
     *                         {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                     </li>
     *                     <li>
     *                         {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                         {@link StpType} - [string, default cancel-newest]
     *                     </li>
     *                     <li>
     *                         {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                     </li>
     *                     <li>
     *                         {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                         in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                         is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                         cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                         if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                         {@link TimeInForce} - [string, default GTC]
     *                     </li>
     *                     <li>
     *                         {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                         timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                         after which the matching engine should reject the new order request, in presence of
     *                         latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                     </li>
     *                     <li>
     *                         {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                     </li>
     *                 </ul>
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return take profit order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addTakeProfitOrder(Side type, double volume, AssetPair pair, double price, Trigger trigger,
                                    Params params, ReturnFormat format) throws Exception {
        return addLevelOrder(take_profit, type, volume, pair.getAltName(), price, trigger, params, format);
    }

    /** Request to send a take profit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *    Add Order</a>
     * @return take profit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addTakeProfitOrder(Side type, double volume, String pair, double price, Trigger trigger,
                                         Params params) throws Exception {
        return addLevelOrder(take_profit, type, volume, pair, price, trigger, params, LIBRARY_OBJECT);
    }

    /** Request to send a take profit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: trigger price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return take profit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addTakeProfitOrder(Side type, double volume, String pair, double price, Trigger trigger,
                                    Params params, ReturnFormat format) throws Exception {
        return addLevelOrder(take_profit, type, volume, pair, price, trigger, params, format);
    }

    /**
     * Request to send a take profit limit order
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param pair:       pair value
     * @param price:      signal price for the order
     * @param price2:     secondary price for the order
     * @param trigger:    trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @return take profit limit order result as {@link OrderAdded} custom object
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addTakeProfitLimitOrder(Side type, double volume, AssetPair pair, double price, double price2,
                                              Trigger trigger, String offsetType) throws Exception {
        return addLevelLimitOrder(take_profit_limit, type, volume, pair.getAltName(), price, price2, trigger, offsetType,
                null, LIBRARY_OBJECT);
    }

    /**
     * Request to send a take profit limit order
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param pair:       pair value
     * @param price:      signal price for the order
     * @param price2:     secondary price for the order
     * @param trigger:    trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return take profit limit order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addTakeProfitLimitOrder(Side type, double volume, AssetPair pair, double price, double price2,
                                         Trigger trigger, String offsetType, ReturnFormat format) throws Exception {
        return addLevelLimitOrder(take_profit_limit, type, volume, pair.getAltName(), price, price2, trigger, offsetType,
                null, format);
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
     *    Add Order</a>
     * @return take profit limit order result as {@link OrderAdded} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addTakeProfitLimitOrder(Side type, double volume, String pair, double price, double price2,
                                              Trigger trigger, String offsetType) throws Exception {
        return addLevelLimitOrder(take_profit_limit, type, volume, pair, price, price2, trigger, offsetType, null,
                LIBRARY_OBJECT);
    }

    /**
     * Request to send a take profit limit order
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param pair:       pair value
     * @param price:      signal price for the order
     * @param price2:     secondary price for the order
     * @param trigger:    trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return take profit limit order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addTakeProfitLimitOrder(Side type, double volume, String pair, double price, double price2,
                                         Trigger trigger, String offsetType, ReturnFormat format) throws Exception {
        return addLevelLimitOrder(take_profit_limit, type, volume, pair, price, price2, trigger, offsetType, null,
                format);
    }

    /** Request to send a take profit limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *    Add Order</a>
     * @return take profit limit order result as {@link OrderAdded} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addTakeProfitLimitOrder(Side type, double volume, AssetPair pair, double price, double price2,
                                              Trigger trigger, String offsetType, Params params) throws Exception {
        return addLevelLimitOrder(take_profit_limit, type, volume, pair.getAltName(), price, price2, trigger, offsetType,
                params, LIBRARY_OBJECT);
    }

    /** Request to send a take profit limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
     *    Add Order</a>
     * @return take profit limit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addTakeProfitLimitOrder(Side type, double volume, AssetPair pair, double price, double price2,
                                         Trigger trigger, String offsetType, Params params,
                                         ReturnFormat format) throws Exception {
        return addLevelLimitOrder(take_profit_limit, type, volume, pair.getAltName(), price, price2, trigger, offsetType,
                params, format);
    }

    /** Request to send a take profit limit order
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param price: signal price for the order
     * @param price2: secondary price for the order
     * @param trigger: trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *    Add Order</a>
     * @return take profit limit order result as {@link OrderAdded} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public OrderAdded addTakeProfitLimitOrder(Side type, double volume, String pair, double price, double price2,
                                              Trigger trigger, String offsetType, Params params) throws Exception {
        return addLevelLimitOrder(take_profit_limit, type, volume, pair, price, price2, trigger, offsetType, params,
                LIBRARY_OBJECT);
    }

    /**
     * Request to send a take profit limit order
     *
     * @param type:       order direction -> buy or sell
     * @param volume:     order quantity in terms of the base asset
     * @param pair:       pair value
     * @param price:      signal price for the order
     * @param price2:     secondary price for the order
     * @param trigger:    trigger type for the order -> last or index
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details, keys accepted are:
     *                    <ul>
     *                        <li>
     *                            {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                            with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                            id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                            uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                            strategy, etc. This allows clients to more readily cancel or query information about
     *                            orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                            where supported - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                            {@link StpType} - [string, default cancel-newest]
     *                        </li>
     *                        <li>
     *                            {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                            in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                            is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                            cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                            if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                            {@link TimeInForce} - [string, default GTC]
     *                        </li>
     *                        <li>
     *                            {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                            timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                            after which the matching engine should reject the new order request, in presence of
     *                            latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                        </li>
     *                        <li>
     *                            {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                        </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return take profit limit order result as {@code "format"} defines
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
     * Add Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrder")
    public <T> T addTakeProfitLimitOrder(Side type, double volume, String pair, double price, double price2,
                                         Trigger trigger, String offsetType, Params params,
                                         ReturnFormat format) throws Exception {
        return addLevelLimitOrder(take_profit_limit, type, volume, pair, price, price2, trigger, offsetType, params,
                format);
    }

    /**
     * Method to send level order type
     *
     * @param orderType: stop-loss or take-profit order type -> constants in {@link Order} class
     * @param type:      order direction -> buy or sell
     * @param volume:    order quantity in terms of the base asset
     * @param pair:      pair value
     * @param params:    extra order details, keys accepted are:
     *                   <ul>
     *                       <li>
     *                           {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                           with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                           id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                           uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                           strategy, etc. This allows clients to more readily cancel or query information about
     *                           orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                           where supported - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                       </li>
     *                       <li>
     *                           {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                           {@link StpType} - [string, default cancel-newest]
     *                       </li>
     *                       <li>
     *                           {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                           in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                           is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                           cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                           if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                           {@link TimeInForce} - [string, default GTC]
     *                       </li>
     *                       <li>
     *                           {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                           timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                           <ul>
     *                                               <li>
     *                                                   {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                               </li>
     *                                               <li>
     *                                                   {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                               </li>
     *                                               <li>
     *                                                   {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                               </li>
     *                                           </ul>
     *                       </li>
     *                       <li>
     *                           {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                           <ul>
     *                                               <li>
     *                                                   {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                               </li>
     *                                               <li>
     *                                                   {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                               </li>
     *                                               <li>
     *                                                   {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                               </li>
     *                                           </ul>
     *                       </li>
     *                       <li>
     *                           {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                           after which the matching engine should reject the new order request, in presence of
     *                           latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                       </li>
     *                       <li>
     *                           {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                       </li>
     *                   </ul>
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return result of the order as {@code "format"} defines
     **/
    @Returner
    private <T> T addLevelOrder(OrderType orderType, Side type, double volume, String pair, double price,
                                Trigger trigger, Params params, ReturnFormat format) throws Exception {
        if (params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("trigger", trigger);
        return addOrder(orderType, type, volume, pair, params, format);
    }

    /** Method to send level limit order type
     * @param orderType: stop-loss-limit or take-profit-limit order type -> constants in {@link Order} class
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param price: price value
     * @param price2: secondary price value
     * @param trigger: price signal used to trigger
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return result of the order as {@code "format"} defines
     * **/
    @Returner
    private <T> T addLevelLimitOrder(OrderType orderType, Side type, double volume, String pair, double price,
                                     double price2, Trigger trigger, String offsetType, Params params,
                                     ReturnFormat format) throws Exception {
        if (params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("trigger", trigger);
        params.addParam("price2", offsetType + price2);
        return addOrder(orderType, type, volume, pair, params, format);
    }

    /** Method to send order type
     * @param orderType: stop-loss or take-profit order type -> constants in {@link Order} class
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return result of the order as {@code "format"} defines
     * **/
    @Returner
    private <T> T addOrder(OrderType orderType, Side type, double volume, String pair, Params params,
                           ReturnFormat format) throws Exception {
        addBaseOrderParameters(orderType, type, volume, pair, params);
        String addOrderResponse = sendPostRequest(ADD_ORDER_ENDPOINT, params);
        switch (format) {
            case JSON:
                return (T) new JSONObject(addOrderResponse);
            case LIBRARY_OBJECT:
                return (T) new OrderAdded(new JSONObject(addOrderResponse));
            default:
                return (T) addOrderResponse;
        }
    }

    /**
     * Request to send a batch order
     *
     * @param orderBatchList: list of orders as {@link OrderBatchList} custom object
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return list of {@link OrderBatch} as {@link ArrayList} or {@link OrderAdded} as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
     * Add Order Batch</a>
     * @implNote keys for params accepted are: deadline or validate
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrderBatch")
    public <T> T addOrderBatch(OrderBatchList orderBatchList, ReturnFormat format) throws Exception {
        return addOrderBatch(orderBatchList, null, format);
    }

    /**
     * Request to send a batch order
     *
     * @param orderBatchList: list of orders as {@link OrderBatchList} custom object
     * @param params:         extra order details, keys accepted are:
     *                        <ul>
     *                            <li>
     *                                {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                                after which the matching engine should reject the new order request, in presence of
     *                                latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                            </li>
     *                            <li>
     *                                {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                            </li>
     *                        </ul>
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return list of {@link OrderBatch} as {@link ArrayList} or {@link OrderAdded} as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrderBatch">
     * Add Order Batch</a>
     * @implNote keys for params accepted are: deadline or validate
     * @implSpec return type change by Kraken's response given
     **/
    @Returner
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/AddOrderBatch")
    public <T> T addOrderBatch(OrderBatchList orderBatchList, Params params, ReturnFormat format) throws Exception {
        if (orderBatchList == null)
            throw new IllegalArgumentException("Order batch list cannot be null");
        if (params == null)
            params = new Params();
        params.addParam("pair", orderBatchList.getPair());
        params.addParam("orders", orderBatchList.getOrders());
        JSONObject jBathc = new JSONObject(sendPostRequest(ADD_ORDER_BATCH_ENDPOINT, params));
        switch (format) {
            case JSON:
                return (T) jBathc;
            case LIBRARY_OBJECT:
                JSONArray orders = JsonHelper.getJSONArray(jBathc.getJSONObject("result"), "orders");
                if (orders != null) {
                    ArrayList<OrderBatch> batchOrders = new ArrayList<>();
                    for (int j = 0; j < orders.length(); j++)
                        batchOrders.add(new OrderBatch(orders.getJSONObject(j)));
                    return (T) batchOrders;
                }
                return (T) new OrderAdded(jBathc);
            default:
                return (T) jBathc.toString();
        }
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
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
     *     Edit Order</a>
     * @return edit market order result as {@link OrderEdited} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editMarketOrder(T orderId, AssetPair pair, double volume) throws Exception {
        return (OrderEdited) editOrder(orderId, pair.getAltName(), volume, null, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a market order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return edit market order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editMarketOrder(T orderId, AssetPair pair, double volume, ReturnFormat format) throws Exception {
        return editOrder(orderId, pair.getAltName(), volume, null, format);
    }

    /**
     * Request to edit a market order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @return edit market order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editMarketOrder(T orderId, String pair, double volume) throws Exception {
        return (OrderEdited) editOrder(orderId, pair, volume, null, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a market order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return edit market order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editMarketOrder(T orderId, String pair, double volume, ReturnFormat format) throws Exception {
        return editOrder(orderId, pair, volume, null, format);
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit market order result as {@link OrderEdited} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editMarketOrder(T orderId, AssetPair pair, double volume, Params params) throws Exception {
        return (OrderEdited) editOrder(orderId, pair.getAltName(), volume, params, LIBRARY_OBJECT);
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit market order result as {@code "format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editMarketOrder(T orderId, AssetPair pair, double volume, Params params,
                                 ReturnFormat format) throws Exception {
        return editOrder(orderId, pair.getAltName(), volume, params, format);
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit market order result as {@link OrderEdited} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editMarketOrder(T orderId, String pair, double volume, Params params) throws Exception {
        return (OrderEdited) editOrder(orderId, pair, volume, params, LIBRARY_OBJECT);
    }

    /** Request to edit a market order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit market order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editMarketOrder(T orderId, String pair, double volume, Params params, ReturnFormat format) throws Exception {
        return editOrder(orderId, pair, volume, params, format);
    }

    /**
     * Request to edit a limit order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @return edit limit order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editLimitOrder(T orderId, AssetPair pair, double volume, double price) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair.getAltName(), volume, price, null, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a limit order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return edit limit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editLimitOrder(T orderId, AssetPair pair, double volume, double price,
                                ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair.getAltName(), volume, price, null, format);
    }

    /**
     * Request to edit a limit order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @return edit limit order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editLimitOrder(T orderId, String pair, double volume, double price) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair, volume, price, null, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a limit order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return edit limit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editLimitOrder(T orderId, String pair, double volume, double price, ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, null, format);
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit limit order result as {@link OrderEdited} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editLimitOrder(T orderId, AssetPair pair, double volume, double price,
                                          Params params) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair.getAltName(), volume, price, params, LIBRARY_OBJECT);
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit limit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editLimitOrder(T orderId, AssetPair pair, double volume, double price, Params params,
                                ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair.getAltName(), volume, price, params, format);
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit limit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editLimitOrder(T orderId, String pair, double volume, double price,
                                          Params params) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair, volume, price, params, LIBRARY_OBJECT);
    }

    /** Request to edit a limit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit limit order result as {@link OrderEdited} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editLimitOrder(T orderId, String pair, double volume, double price, Params params,
                                ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, params, format);
    }

    /**
     * Request to edit a stop loss order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @return edit stop loss order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editStopLossOrder(T orderId, AssetPair pair, double volume, double price) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair.getAltName(), volume, price, null, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a stop loss order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return edit stop loss order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editStopLossOrder(T orderId, AssetPair pair, double volume, double price,
                                   ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair.getAltName(), volume, price, null, format);
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
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
     *     Edit Order</a>
     * @return edit stop loss order result as {@link OrderEdited} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editStopLossOrderObject(T orderId, String pair, double volume, double price) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair, volume, price, null, LIBRARY_OBJECT);
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit stop loss order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editStopLossOrderObject(T orderId, String pair, double volume, double price,
                                         ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, null, format);
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit stop loss order result as {@link OrderEdited} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editStopLossOrder(T orderId, AssetPair pair, double volume, double price,
                                             Params params) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair.getAltName(), volume, price, params, LIBRARY_OBJECT);
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit stop loss order result as {@code "format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editStopLossOrder(T orderId, AssetPair pair, double volume, double price, Params params,
                                   ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair.getAltName(), volume, price, params, format);
    }

    /** Request to edit a stop loss order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit stop loss order result as {@link OrderEdited} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editStopLossOrder(T orderId, String pair, double volume, double price,
                                             Params params) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair, volume, price, params, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a stop loss order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @param params:  extra order details, keys accepted are:
     *                 <ul>
     *                     <li>
     *                         {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                         with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                         id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                         uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                         strategy, etc. This allows clients to more readily cancel or query information about
     *                         orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                         where supported - [integer]
     *                     </li>
     *                     <li>
     *                         {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                     </li>
     *                     <li>
     *                         {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                         {@link StpType} - [string, default cancel-newest]
     *                     </li>
     *                     <li>
     *                         {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                     </li>
     *                     <li>
     *                         {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                         in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                         is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                         cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                         if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                         {@link TimeInForce} - [string, default GTC]
     *                     </li>
     *                     <li>
     *                         {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                         timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                         after which the matching engine should reject the new order request, in presence of
     *                         latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                     </li>
     *                     <li>
     *                         {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                     </li>
     *                 </ul>
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return edit stop loss order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editStopLossOrder(T orderId, String pair, double volume, double price, Params params,
                                   ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, params, format);
    }

    /**
     * Request to edit a stop loss limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @return edit stop loss limit order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editStopLossLimitOrder(T orderId, AssetPair pair, double volume, double price, double price2,
                                                  String offsetType) throws Exception {
        return (OrderEdited) editPriceLimitOrder(orderId, pair.getAltName(), volume, price, price2, offsetType,
                null, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a stop loss limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return edit stop loss limit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editStopLossLimitOrder(T orderId, AssetPair pair, double volume, double price, double price2,
                                        String offsetType, ReturnFormat format) throws Exception {
        return editPriceLimitOrder(orderId, pair.getAltName(), volume, price, price2, offsetType, null, format);
    }

    /**
     * Request to edit a stop loss limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @return edit stop loss limit order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editStopLossLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                                  String offsetType) throws Exception {
        return (OrderEdited) editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, null,
                LIBRARY_OBJECT);
    }

    /** Request to edit a stop loss limit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit stop loss limit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editStopLossLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                        String offsetType, ReturnFormat format) throws Exception {
        return editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, null, format);
    }

    /** Request to edit a stop loss limit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit stop loss limit order result as {@link OrderEdited} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editStopLossLimitOrder(T orderId, AssetPair pair, double volume, double price, double price2,
                                                  String offsetType, Params params) throws Exception {
        return (OrderEdited) editPriceLimitOrder(orderId, pair.getAltName(), volume, price, price2, offsetType, params,
                LIBRARY_OBJECT);
    }

    /**
     * Request to edit a stop loss limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details, keys accepted are:
     *                    <ul>
     *                        <li>
     *                            {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                            with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                            id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                            uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                            strategy, etc. This allows clients to more readily cancel or query information about
     *                            orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                            where supported - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                            {@link StpType} - [string, default cancel-newest]
     *                        </li>
     *                        <li>
     *                            {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                            in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                            is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                            cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                            if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                            {@link TimeInForce} - [string, default GTC]
     *                        </li>
     *                        <li>
     *                            {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                            timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                            after which the matching engine should reject the new order request, in presence of
     *                            latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                        </li>
     *                        <li>
     *                            {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                        </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return edit stop loss limit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editStopLossLimitOrder(T orderId, AssetPair pair, double volume, double price, double price2,
                                        String offsetType, Params params, ReturnFormat format) throws Exception {
        return editPriceLimitOrder(orderId, pair.getAltName(), volume, price, price2, offsetType, params, format);
    }

    /**
     * Request to edit a stop loss limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details, keys accepted are:
     *                    <ul>
     *                        <li>
     *                            {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                            with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                            id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                            uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                            strategy, etc. This allows clients to more readily cancel or query information about
     *                            orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                            where supported - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                            {@link StpType} - [string, default cancel-newest]
     *                        </li>
     *                        <li>
     *                            {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                            in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                            is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                            cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                            if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                            {@link TimeInForce} - [string, default GTC]
     *                        </li>
     *                        <li>
     *                            {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                            timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                            after which the matching engine should reject the new order request, in presence of
     *                            latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                        </li>
     *                        <li>
     *                            {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                        </li>
     *                    </ul>
     * @return edit stop loss limit order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editStopLossLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                                  String offsetType, Params params) throws Exception {
        return (OrderEdited) editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, params, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a stop loss limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details, keys accepted are:
     *                    <ul>
     *                        <li>
     *                            {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                            with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                            id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                            uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                            strategy, etc. This allows clients to more readily cancel or query information about
     *                            orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                            where supported - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                            {@link StpType} - [string, default cancel-newest]
     *                        </li>
     *                        <li>
     *                            {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                            in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                            is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                            cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                            if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                            {@link TimeInForce} - [string, default GTC]
     *                        </li>
     *                        <li>
     *                            {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                            timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                            after which the matching engine should reject the new order request, in presence of
     *                            latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                        </li>
     *                        <li>
     *                            {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                        </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return edit stop loss limit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editStopLossLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                        String offsetType, Params params, ReturnFormat format) throws Exception {
        return editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, params, format);
    }

    /**
     * Request to edit a take profit order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @return edit take profit order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editTakeProfitOrder(T orderId, AssetPair pair, double volume, double price) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair.getAltName(), volume, price, null, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a take profit order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return edit take profit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editTakeProfitOrder(T orderId, AssetPair pair, double volume, double price,
                                     ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair.getAltName(), volume, price, null, format);
    }

    /**
     * Request to edit a take profit order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @return edit take profit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editTakeProfitOrder(T orderId, String pair, double volume, double price) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair, volume, price, null, LIBRARY_OBJECT);
    }

    /**
     * Request to edit a take profit order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   limit price for the order
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return edit take profit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editTakeProfitOrder(T orderId, String pair, double volume, double price,
                                     ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, null, format);
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit take profit order result as {@link OrderEdited} custom object
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editTakeProfitOrder(T orderId, AssetPair pair, double volume, double price,
                                               Params params) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair.getAltName(), volume, price, params, LIBRARY_OBJECT);
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit take profit order result as {@code "format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editTakeProfitOrder(T orderId, AssetPair pair, double volume, double price, Params params,
                                     ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair.getAltName(), volume, price, params, format);
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit take profit order result as {@link OrderEdited} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editTakeProfitOrder(T orderId, String pair, double volume, double price,
                                               Params params) throws Exception {
        return (OrderEdited) editPriceOrder(orderId, pair, volume, price, params, LIBRARY_OBJECT);
    }

    /** Request to edit a take profit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit take profit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editTakeProfitOrder(T orderId, String pair, double volume, double price, Params params,
                                     ReturnFormat format) throws Exception {
        return editPriceOrder(orderId, pair, volume, price, params, format);
    }

    /**
     * Request to edit a take profit limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @return edit take profit limit order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editTakeProfitLimitOrder(T orderId, AssetPair pair, double volume, double price,
                                                    double price2, String offsetType) throws Exception {
        return (OrderEdited) editPriceLimitOrder(orderId, pair.getAltName(), volume, price, price2, offsetType, null,
                LIBRARY_OBJECT);
    }

    /**
     * Request to edit a take profit limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return edit take profit limit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editTakeProfitLimitOrder(T orderId, AssetPair pair, double volume, double price, double price2,
                                          String offsetType, ReturnFormat format) throws Exception {
        return editPriceLimitOrder(orderId, pair.getAltName(), volume, price, price2, offsetType, null, format);
    }

    /**
     * Request to edit a take profit limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @return edit take profit limit order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editTakeProfitLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                                    String offsetType) throws Exception {
        return (OrderEdited) editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, null,
                LIBRARY_OBJECT);
    }

    /**
     * Request to edit a take profit limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return edit take profit limit order result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editTakeProfitLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                          String offsetType, ReturnFormat format) throws Exception {
        return editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, null, format);
    }

    /**
     * Request to edit a take profit limit order
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      limit price for the order
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params:     extra order details, keys accepted are:
     *                    <ul>
     *                        <li>
     *                            {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                            with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                            id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                            uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                            strategy, etc. This allows clients to more readily cancel or query information about
     *                            orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                            where supported - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                            {@link StpType} - [string, default cancel-newest]
     *                        </li>
     *                        <li>
     *                            {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                            in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                            is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                            cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                            if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                            {@link TimeInForce} - [string, default GTC]
     *                        </li>
     *                        <li>
     *                            {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                            timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                            <ul>
     *                                                <li>
     *                                                    {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                </li>
     *                                                <li>
     *                                                    {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                </li>
     *                                            </ul>
     *                        </li>
     *                        <li>
     *                            {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                            after which the matching engine should reject the new order request, in presence of
     *                            latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                        </li>
     *                        <li>
     *                            {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                        </li>
     *                    </ul>
     * @return edit take profit limit order result as {@link OrderEdited} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     * Edit Order</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editTakeProfitLimitOrder(T orderId, AssetPair pair, double volume, double price, double price2,
                                                    String offsetType, Params params) throws Exception {
        return (OrderEdited) editPriceLimitOrder(orderId, pair.getAltName(), volume, price, price2, offsetType, params,
                LIBRARY_OBJECT);
    }

    /** Request to edit a take profit limit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit take profit limit order result as {@code "format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editTakeProfitLimitOrder(T orderId, AssetPair pair, double volume, double price, double price2,
                                          String offsetType, Params params, ReturnFormat format) throws Exception {
        return editPriceLimitOrder(orderId, pair.getAltName(), volume, price, price2, offsetType, params, format);
    }

    /** Request to edit a take profit limit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
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
     *     Edit Order</a>
     * @return edit take profit limit order result as {@link OrderEdited} custom object
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> OrderEdited editTakeProfitLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                                    String offsetType, Params params) throws Exception {
        return (OrderEdited) editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, params, LIBRARY_OBJECT);
    }

    /** Request to edit a take profit limit order
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param price: limit price for the order
     * @param price2: secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/editOrder">
     *     Edit Order</a>
     * @return edit take profit limit order result as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/EditOrder")
    public <T> T editTakeProfitLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                          String offsetType, Params params, ReturnFormat format) throws Exception {
        return editPriceLimitOrder(orderId, pair, volume, price, price2, offsetType, params, format);
    }

    /**
     * Method to send an edit operation of orders with price
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:    pair value
     * @param volume:  order quantity in terms of the base asset
     * @param price:   price value
     * @param params:  extra order details, keys accepted are:
     *                 <ul>
     *                     <li>
     *                         {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                         with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                         id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                         uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                         strategy, etc. This allows clients to more readily cancel or query information about
     *                         orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                         where supported - [integer]
     *                     </li>
     *                     <li>
     *                         {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                     </li>
     *                     <li>
     *                         {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                         {@link StpType} - [string, default cancel-newest]
     *                     </li>
     *                     <li>
     *                         {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                     </li>
     *                     <li>
     *                         {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                         in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                         is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                         cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                         if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                         {@link TimeInForce} - [string, default GTC]
     *                     </li>
     *                     <li>
     *                         {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                         timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                         <ul>
     *                                             <li>
     *                                                 {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                             </li>
     *                                             <li>
     *                                                 {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                             </li>
     *                                         </ul>
     *                     </li>
     *                     <li>
     *                         {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                         after which the matching engine should reject the new order request, in presence of
     *                         latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                     </li>
     *                     <li>
     *                         {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                     </li>
     *                 </ul>
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return result of edit order as {@code "format"} defines
     **/
    @Returner
    private <T> T editPriceOrder(T orderId, String pair, double volume, double price, Params params,
                                 ReturnFormat format) throws Exception {
        if (params == null)
            params = new Params();
        params.addParam("price", price);
        return editOrder(orderId, pair, volume, params, format);
    }

    /**
     * Method to send an edit operation of limit orders with price
     *
     * @param orderId:    order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair:       pair value
     * @param volume:     order quantity in terms of the base asset
     * @param price:      price value
     * @param price2:     secondary price for the order
     * @param offsetType: +, - , # or % -> constants in {@link OrderAdded} class
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return result of edit order as {@code "format"} defines
     **/
    @Returner
    private <T> T editPriceLimitOrder(T orderId, String pair, double volume, double price, double price2,
                                      String offsetType, Params params, ReturnFormat format) throws Exception {
        if (params == null)
            params = new Params();
        params.addParam("price", price);
        params.addParam("price2", offsetType + price2);
        return editOrder(orderId, pair, volume, params, format);
    }

    /** Method to send an edit operation
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param pair: pair value
     * @param volume: order quantity in terms of the base asset
     * @param params: extra order details, keys accepted are:
     *                      <ul>
     *                          <li>
     *                              {@code "userref"} -> {@code "userref"} is an optional user-specified integer id that can be associated
     *                              with any number of orders. Many clients choose a {@code "userref"} corresponding to a unique integer
     *                              id generated by their systems (e.g. a timestamp). However, because we don't enforce
     *                              uniqueness on our side, it can also be used to easily group orders by pair, side,
     *                              strategy, etc. This allows clients to more readily cancel or query information about
     *                              orders in a particular group, with fewer API calls by using {@code "userref"} instead of our txid,
     *                              where supported - [integer]
     *                          </li>
     *                          <li>
     *                              {@code "leverage"} -> amount of leverage desired (default: none) - [string, default none]
     *                          </li>
     *                          <li>
     *                              {@code "stptype"} -> self trade prevention behavior definition, constants available at
     *                              {@link StpType} - [string, default cancel-newest]
     *                          </li>
     *                          <li>
     *                              {@code "oflags"} -> comma delimited list of order flags, constants available at {@link OFlag} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "timeinforce"} -> time-in-force of the order to specify how long it should remain
     *                              in the order book before being cancelled. GTC (Good-'til-cancelled) is default if the parameter
     *                              is omitted. IOC (immediate-or-cancel) will immediately execute the amount possible and
     *                              cancel any remaining balance rather than resting in the book. GTD (good-'til-date),
     *                              if specified, must coincide with a desired {@code "expiretm"}, constants available at
     *                              {@link TimeInForce} - [string, default GTC]
     *                          </li>
     *                          <li>
     *                              {@code "starttm"} - [string] -> scheduled start time, can be specified as an absolute
     *                              timestamp or as a number of seconds in the future, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "expiretm"} - [string] -> - expiration time, constants available at {@link OrderAdded}:
     *                                              <ul>
     *                                                  <li>
     *                                                      {@link OrderAdded#DEFAULT_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#FROM_NOW_SCHEDULED_TIME}
     *                                                  </li>
     *                                                  <li>
     *                                                      {@link OrderAdded#UNIX_TIMESTAMP_SCHEDULED_TIME}
     *                                                  </li>
     *                                              </ul>
     *                          </li>
     *                          <li>
     *                              {@code "deadline"} -> {@code "RFC3339"} timestamp (e.g. {@code "2021-04-01T00:18:45Z"})
     *                              after which the matching engine should reject the new order request, in presence of
     *                              latency or order queueing: min now() + 2 seconds, max now() + 60 seconds - [string]
     *                          </li>
     *                          <li>
     *                              {@code "validate"} -> validate inputs only. Do not submit order - [boolean, default false]
     *                          </li>
     *                      </ul> 
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return result of edit order as {@code "format"} defines
     * **/
    @Returner
    private <T> T editOrder(T orderId, String pair, double volume, Params params, ReturnFormat format) throws Exception {
        addBaseEditParameters(orderId, pair, volume, params);
        String editOrderResponse = sendPostRequest(EDIT_ORDER_ENDPOINT, params);
        switch (format) {
            case JSON:
                return (T) new JSONObject(editOrderResponse);
            case LIBRARY_OBJECT:
                return (T) new OrderEdited(new JSONObject(editOrderResponse));
            default:
                return (T) editOrderResponse;
        }
    }

    /**
     * Request to cancel an order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @return result of order cancellation as {@link OrderCancelledStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder">
     * Cancel Order</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/CancelOrder")
    public <T> OrderCancelledStatus cancelOrder(T orderId) throws Exception {
        return (OrderCancelledStatus) cancelOrder(orderId, LIBRARY_OBJECT);
    }

    /**
     * Request to cancel an order
     *
     * @param orderId: order identifier can be string for {@code "txid"} use or long for {@code "userref"} use
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return result of order cancellation as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrder">
     * Cancel Order</a>
     **/
    @Returner
    @RequestPath(path = "https://api.kraken.com/0/private/CancelOrder")
    public <T> T cancelOrder(T orderId, ReturnFormat format) throws Exception {
        String idKey = "txid";
        if (orderId instanceof Number)
            idKey = "userref";
        Params params = new Params();
        params.addParam(idKey, orderId);
        String cancelResponse = sendPostRequest(CANCEL_ORDER_ENDPOINT, params);
        switch (format) {
            case JSON:
                return (T) new JSONObject(cancelResponse);
            case LIBRARY_OBJECT:
                return (T) new OrderCancelledStatus(new JSONObject(cancelResponse));
            default:
                return (T) cancelResponse;
        }
    }

    /**
     * Request to cancel all orders <br>
     * Any params required
     *
     * @return result of all orders cancellation as {@link OrderCancelled} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders">
     * Cancel All Orders</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/CancelAll")
    public OrderCancelled cancelAllOrders() throws Exception {
        return returnOrderCancelled(sendPostRequest(CANCEL_ALL_ORDERS_ENDPOINT, null), LIBRARY_OBJECT);
    }

    /**
     * Request to cancel all orders
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return result of all orders cancellation as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrders">
     * Cancel All Orders</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/private/CancelAll")
    public <T> T cancelAllOrders(ReturnFormat format) throws Exception {
        return returnOrderCancelled(sendPostRequest(CANCEL_ALL_ORDERS_ENDPOINT, null), format);
    }

    /**
     * Request to cancel all orders after a timestamp
     *
     * @param timeout: duration (in seconds) to set/extend the timer by
     * @return result of orders cancellation as {@link OrderCancelledAfter} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter">
     * Cancel All Orders After X</a>
     **/
    public OrderCancelledAfter cancelAllOrdersAfter(int timeout) throws Exception {
        return cancelAllOrdersAfter(timeout, LIBRARY_OBJECT);
    }

    /** Request to cancel all orders after a timestamp
     * @param timeout: duration (in seconds) to set/extend the timer by
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelAllOrdersAfter">
     *     Cancel All Orders After X</a>
     * @return result of orders cancellation as {@code "format"} defines
     * **/
    @RequestPath(path = "https://api.kraken.com/0/private/CancelAllOrdersAfter")
    public <T> T cancelAllOrdersAfter(int timeout, ReturnFormat format) throws Exception {
        Params params = new Params();
        params.addParam("timeout", timeout);
        return returnOrderCancelled(sendPostRequest(CANCEL_ALL_ORDERS_AFTER_ENDPOINT, params), format);
    }

    /**
     * Request to cancel a batch order
     *
     * @param orderBatchIds: list of orders identifier can be string for {@code "txid"} use, long for {@code "userref"} use
     *                       or a list of {@link ArrayList} of {@link OrderBatch} to cancel
     * @return result of batch order cancellation as {"format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/cancelOrderBatch">
     * Cancel Order Batch</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/CancelOrderBatch")
    public <T> OrderCancelled cancelOrderBatch(ArrayList<T> orderBatchIds) throws Exception {
        return (OrderCancelled) cancelOrderBatch(orderBatchIds, LIBRARY_OBJECT);
    }

    /** Request to cancel a batch order
     * @param orderBatchIds: list of orders identifier can be string for {@code "txid"} use, long for {@code "userref"} use
     *                          or a list of {@link ArrayList} of {@link OrderBatch} to cancel
     * @param format:             return type formatter -> {@link ReturnFormat}
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
     *     Cancel Order Batch</a>
     * @return result of batch order cancellation as {"format"} defines
     * **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/private/CancelOrderBatch")
    public <T> T cancelOrderBatch(ArrayList<T> orderBatchIds, ReturnFormat format) throws Exception {
        String key = "txid";
        Params params = new Params();
        T orderBatch = orderBatchIds.get(0);
        if (orderBatch instanceof String)
            key = "userref";
        else if (orderBatch instanceof OrderBatch) {
            ArrayList<T> ids = new ArrayList<>();
            for (T order : orderBatchIds)
                ids.add((T) ((OrderBatch) order).getTxId());
            orderBatchIds = ids;
        }
        params.addParam(key, Arrays.stream(orderBatchIds.toArray(new Object[0])).toList());
        return returnOrderCancelled(sendPostRequest(CANCEL_ORDER_BATCH_ENDPOINT, params), format);
    }

    /**
     * Method to assemble an order cancelled response
     *
     * @param orderCancelledResponse: cancelled response to format
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return order cancelled response as {"format"} defines
     **/
    @Returner
    private <T> T returnOrderCancelled(String orderCancelledResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(orderCancelledResponse);
            case LIBRARY_OBJECT:
                return (T) new OrderCancelled(new JSONObject(orderCancelledResponse));
            default:
                return (T) orderCancelledResponse;
        }
    }

}
