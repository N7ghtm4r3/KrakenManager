package com.tecknobit.krakenmanager.privates.usertrading.records.orders;

import com.tecknobit.krakenmanager.KrakenManager;
import com.tecknobit.krakenmanager.privates.userdata.records.orders.Order;
import com.tecknobit.krakenmanager.privates.userdata.records.orders.Order.OrderType;
import com.tecknobit.krakenmanager.privates.userdata.records.orders.Order.Side;
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
 * Add Order</a>
 **/
public class OrderAdded extends KrakenManager.KrakenResponse {

    /**
     * Method to add base order paramaters
     *
     * @param orderType: order type -> all constants in {@link Order} class
     * @param type:      order direction -> buy or sell
     * @param volume:    order quantity in terms of the base asset
     * @param pair:      pair value
     * @param params:    extra order details
     **/
    public static void addBaseOrderParameters(OrderType orderType, Side type, double volume, String pair, Params params) {
        if (params == null)
            params = new Params();
        params.addParam("ordertype", orderType);
        params.addParam("type", type);
        params.addParam("volume", sNotationParse(8, volume));
        params.addParam("pair", pair);
    }

    /**
     * {@code StpType} list for self trade prevention types
     **/
    public enum StpType {

        /**
         * {@code "cancel_newest"} if self trade is triggered, arriving order will be canceled
         **/
        cancel_newest("cancel-newest"),

        /**
         * {@code "cancel_oldest"} if self trade is triggered, resting order will be canceled
         **/
        cancel_oldest("cancel-oldest"),

        /**
         * {@code "cancel_both"} if self trade is triggered, both arriving and resting orders will be canceled
         **/
        cancel_both("cancel-both");

        /**
         * {@code type} self trade prevention type
         **/
        private final String type;

        /**
         * Constructor to init a {@link StpType} object
         *
         * @param type: self trade prevention type
         **/
        StpType(String type) {
            this.type = type;
        }

        /**
         * Method to get {@link #type} instance <br>
         * No-any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        @Override
        public String toString() {
            return type;
        }

    }

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
     * {@code TimeInForce} list for time in force types
     **/
    public enum TimeInForce {

        /**
         * {@code "GTC"} time in force type
         **/
        GTC,

        /**
         * {@code "IOC"} time in force type
         **/
        IOC,

        /**
         * {@code "GTD"} time in force type
         **/
        GTD

    }

    /**
     * Method to get {@link #txIds} instance <br>
     * No-any params required
     *
     * @return {@link #txIds} instance as {@link String}
     **/
    public ArrayList<String> getTxIds() {
        return txIds;
    }

    /**
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link Order.OrderDescription}
     **/
    public Order.OrderDescription getDescription() {
        return description;
    }

}
