package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.apimanager.Tools.Formatters.ScientificNotationParser.sNotationParse;

public class OrderAdded extends KrakenManager.KrakenResponse {

    public static final String STP_CANCEL_NEWEST_TYPE = "cancel-newest";
    public static final String STP_CANCEL_OLDEST_TYPE = "cancel-oldest";
    public static final String STP_CANCEL_BOTH_TYPE = "cancel-both";
    public static final String GTC_TIME_IN_FORCE = "GTC";
    public static final String IOC_TIME_IN_FORCE = "IOC";
    public static final String GTD_TIME_IN_FORCE = "GTD";
    public static final String DEFAULT_SCHEDULED_TIME = "0";
    public static final String FROM_NOW_SCHEDULED_TIME = "+<n>";
    public static final String UNIX_TIMESTAMP_SCHEDULED_TIME = "<n>";
    public static final String ADD_OFFSET_AMOUNT = "+";
    public static final String SUBTRATS_OFFSET_AMOUNT = "-";
    public static final String GENERIC_OFFSET_AMOUNT = "#";
    public static final String RELATIVE_PERCENTAGE_OFFSET_AMOUNT = "%";

    private final ArrayList<String> txIds;
    private final Description description;

    public OrderAdded(JSONObject jsonResponse, ArrayList<String> txIds, Description description) {
        super(jsonResponse);
        this.txIds = txIds;
        this.description = description;
    }

    public OrderAdded(ArrayList<String> txIds, Description description) {
        super(null);
        this.txIds = txIds;
        this.description = description;
    }

    /**
     * Constructor to init a {@link OrderAdded} object
     * @param jsonResponse : base json response
     **/
    public OrderAdded(JSONObject jsonResponse) {
        super(jsonResponse);
        JSONObject result = getResult();
        description = new Description(result.getJSONObject("descr"));
        txIds = new ArrayList<>();
        JSONArray jsonTxIds = result.getJSONArray("txid");
        for (int j = 0; j < jsonTxIds.length(); j++)
            txIds.add(jsonTxIds.getString(j));
    }

    public ArrayList<String> getTxIds() {
        return txIds;
    }

    public Description getDescription() {
        return description;
    }

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

    // TODO: 03/08/2022 INSERT CREATE TICKET WITH SCREEN OF RESULT JSON TO CREATE OTHER DESCR INSTANCE

    public static class Description {

        // TODO: 03/08/2022 TMP AS FAR AS DESCR WILL NOT COMPLETE
        private final JsonHelper jsonHelper;
        private final String order;
        private final String close;

        public Description(String order, String close) {
            jsonHelper = null;
            this.order = order;
            this.close = close;
        }

        public Description(JSONObject jsonDescription) {
            jsonHelper = new JsonHelper(jsonDescription);
            order = jsonHelper.getString("order");
            close = jsonHelper.getString("close");
        }

        // TODO: 03/08/2022 INSERT CREATE TICKET WITH SCREEN OF RESULT JSON TO CREATE OTHER DESCR INSTANCE
        //  AND WARN USER ABOUT TMP METHOD AS FAR AS DESCR WILL NOT BE COMPLETED
        public JsonHelper getJsonHelper() {
            return jsonHelper;
        }

        public String getOrder() {
            return order;
        }

        public String getClose() {
            return close;
        }

        @Override
        public String toString() {
            return "Description{" +
                    "order='" + order + '\'' +
                    ", close='" + close + '\'' +
                    '}';
        }

    }

}
