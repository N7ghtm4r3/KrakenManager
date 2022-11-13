package com.tecknobit.krakenmanager.managers.privates.usertrading.records.orders;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import com.tecknobit.krakenmanager.managers.privates.userdata.records.orders.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.Params;
import static com.tecknobit.apimanager.formatters.ScientificNotationParser.sNotationParse;

/**
 * The {@code OrderAdded} class is useful to assemble order added object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
 * https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
 **/
public class OrderAdded extends KrakenManager.KrakenResponse {

    /**
     * {@code STP_CANCEL_NEWEST_TYPE} is constant for cancel-newest self trade prevention type
     * **/
    public static final String STP_CANCEL_NEWEST_TYPE = "cancel-newest";

    /**
     * {@code STP_CANCEL_OLDEST_TYPE} is constant for cancel-oldest self trade prevention type
     * **/
    public static final String STP_CANCEL_OLDEST_TYPE = "cancel-oldest";

    /**
     * {@code STP_CANCEL_BOTH_TYPE} is constant for cancel-both self trade prevention type
     * **/
    public static final String STP_CANCEL_BOTH_TYPE = "cancel-both";

    /**
     * {@code GTC_TIME_IN_FORCE} is constant for GTC time in force type
     * **/
    public static final String GTC_TIME_IN_FORCE = "GTC";

    /**
     * {@code IOC_TIME_IN_FORCE} is constant for IOC time in force type
     * **/
    public static final String IOC_TIME_IN_FORCE = "IOC";

    /**
     * {@code GTD_TIME_IN_FORCE} is constant for GTD time in force type
     * **/
    public static final String GTD_TIME_IN_FORCE = "GTD";

    /**
     * {@code DEFAULT_SCHEDULED_TIME} is constant for default scheduled time type
     * **/
    public static final String DEFAULT_SCHEDULED_TIME = "0";

    /**
     * {@code FROM_NOW_SCHEDULED_TIME} is constant for from now scheduled time type
     * **/
    public static final String FROM_NOW_SCHEDULED_TIME = "+<n>";

    /**
     * {@code UNIX_TIMESTAMP_SCHEDULED_TIME} is constant for unix timestamp scheduled time type
     * **/
    public static final String UNIX_TIMESTAMP_SCHEDULED_TIME = "<n>";

    /**
     * {@code ADD_OFFSET_AMOUNT} is constant for add offset amount
     * **/
    public static final String ADD_OFFSET_AMOUNT = "+";

    /**
     * {@code SUBSTRACS_OFFSET_AMOUNT} is constant for substract offset amount
     * **/
    public static final String SUBSTRACS_OFFSET_AMOUNT = "-";

    /**
     * {@code GENERIC_OFFSET_AMOUNT} is constant for generic offset amount
     * **/
    public static final String GENERIC_OFFSET_AMOUNT = "#";

    /**
     * {@code RELATIVE_PERCENTAGE_OFFSET_AMOUNT} is constant for relative percentage offset amount
     * **/
    public static final String RELATIVE_PERCENTAGE_OFFSET_AMOUNT = "%";

    /**
     * {@code txIds} is instance that memorizes list of tx ids
     **/
    private final ArrayList<String> txIds;

    /**
     * {@code description} is instance that memorizes description of added order
     **/
    private final Order.OrderDescription description;

    /**
     * Constructor to init a {@link OrderAdded} object
     *
     * @param txIds:       list of tx ids
     * @param description: description of added order
     **/
    public OrderAdded(ArrayList<String> txIds, Order.OrderDescription description) {
        super(null);
        this.txIds = txIds;
        this.description = description;
    }

    /**
     * Constructor to init a {@link OrderAdded} object
     * @param jsonResponse: base json response
     **/
    public OrderAdded(JSONObject jsonResponse) {
        super(jsonResponse);
        description = new Order.OrderDescription(result.getJSONObject("descr", new JSONObject()));
        txIds = new ArrayList<>();
        JSONArray jsonTxIds = result.getJSONArray("txid", new JSONArray());
        for (int j = 0; j < jsonTxIds.length(); j++)
            txIds.add(jsonTxIds.getString(j));
    }

    /**
     * Method to add base order paramaters
     *
     * @param orderType: order type -> all constants in {@link Order} class
     * @param type:      order direction -> buy or sell
     * @param volume:    order quantity in terms of the base asset
     * @param pair:      pair value
     * @param params:    extra order details
     **/
    public static void addBaseOrderParameters(String orderType, String type, double volume, String pair, Params params) {
        if (params == null)
            params = new Params();
        params.addParam("ordertype", orderType);
        params.addParam("type", type);
        params.addParam("volume", sNotationParse(8, volume));
        params.addParam("pair", pair);
    }

    /**
     * Method to get {@link #txIds} instance <br>
     * Any params required
     *
     * @return {@link #txIds} instance as {@link String}
     **/
    public ArrayList<String> getTxIds() {
        return txIds;
    }

    /**
     * Method to get {@link #description} instance <br>
     * Any params required
     *
     * @return {@link #description} instance as {@link Order.OrderDescription}
     **/
    public Order.OrderDescription getDescription() {
        return description;
    }

}
