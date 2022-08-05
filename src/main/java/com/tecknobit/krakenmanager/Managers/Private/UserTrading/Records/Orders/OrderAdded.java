package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.apimanager.Tools.Formatters.ScientificNotationParser.sNotationParse;

/**
 * The {@code OrderAdded} class is useful to assemble order added object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder">
 *     https://docs.kraken.com/rest/#tag/User-Trading/operation/addOrder</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

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
     * **/
    private final ArrayList<String> txIds;

    /**
     * {@code description} is instance that memorizes description of added order
     * **/
    private final Order.OrderDescription description;

    /** Constructor to init a {@link OrderAdded} object
     * @param jsonResponse: base json response
     * @param txIds: list of tx ids
     * @param description: description of added order
     **/
    public OrderAdded(JSONObject jsonResponse, ArrayList<String> txIds, Order.OrderDescription description) {
        super(jsonResponse);
        this.txIds = txIds;
        this.description = description;
    }

    /** Constructor to init a {@link OrderAdded} object
     * @param txIds: list of tx ids
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
        JSONObject result = getResult();
        description = new Order.OrderDescription(result.getJSONObject("descr"));
        txIds = new ArrayList<>();
        JSONArray jsonTxIds = result.getJSONArray("txid");
        for (int j = 0; j < jsonTxIds.length(); j++)
            txIds.add(jsonTxIds.getString(j));
    }

    public ArrayList<String> getTxIds() {
        return txIds;
    }

    public Order.OrderDescription getDescription() {
        return description;
    }

    /** Method to add base order paramaters
     * @param orderType: order type -> all constants in {@link Order} class
     * @param type: order direction -> buy or sell
     * @param volume: order quantity in terms of the base asset
     * @param pair: pair value
     * @param params: extra order details
     * **/
    public static void addBaseOrderParameters(String orderType, String type, double volume, String pair, Params params){
        if(params == null)
            params = new Params();
        params.addParam("ordertype", orderType);
        params.addParam("type", type);
        params.addParam("volume", sNotationParse(8, volume));
        params.addParam("pair", pair);
    }

    @Override
    public String toString() {
        return "OrderAdded{" +
                "txIds=" + txIds +
                ", description=" + description +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
