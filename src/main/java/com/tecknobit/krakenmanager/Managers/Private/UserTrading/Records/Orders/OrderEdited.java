package com.tecknobit.krakenmanager.Managers.Private.UserTrading.Records.Orders;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order.OrderDescription;
import org.json.JSONObject;

import java.util.Arrays;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.apimanager.Tools.Formatters.ScientificNotationParser.sNotationParse;

public class OrderEdited extends KrakenManager.KrakenResponse{

    private final String status;
    private final String txId;
    private final String originalTxId;
    private final double volume;
    private final double price;
    private final double price2;
    private final int ordersCancelled;
    private final OrderDescription orderDescription;

    public OrderEdited(JSONObject jsonResponse, String status, String txId, String originalTxId, double volume,
                       double price, double price2, int ordersCancelled, OrderDescription orderDescription) {
        super(jsonResponse);
        this.status = status;
        this.txId = txId;
        this.originalTxId = originalTxId;
        this.volume = volume;
        this.price = price;
        this.price2 = price2;
        this.ordersCancelled = ordersCancelled;
        this.orderDescription = orderDescription;
    }

    public OrderEdited(String status, String txId, String originalTxId, double volume, double price, double price2,
                       int ordersCancelled, OrderDescription orderDescription) {
        super(null);
        this.status = status;
        this.txId = txId;
        this.originalTxId = originalTxId;
        this.volume = volume;
        this.price = price;
        this.price2 = price2;
        this.ordersCancelled = ordersCancelled;
        this.orderDescription = orderDescription;
    }

    /**
     * Constructor to init a {@link OrderEdited} object
     * @param jsonResponse : base json response
     **/
    public OrderEdited(JSONObject jsonResponse) {
        super(jsonResponse);
        JsonHelper orderEdited = new JsonHelper((JSONObject) getResult());
        status = orderEdited.getString("status");
        txId = orderEdited.getString("txid");
        originalTxId = orderEdited.getString("originaltxid");
        volume = orderEdited.getDouble("volume");
        price = orderEdited.getDouble("price");
        price2 = orderEdited.getDouble("price2");
        ordersCancelled = orderEdited.getInt("orders_cancelled");
        orderDescription = new OrderDescription(orderEdited.getJSONObject("descr"));
    }

    public String getStatus() {
        return status;
    }

    public String getTxId() {
        return txId;
    }

    public String getOriginalTxId() {
        return originalTxId;
    }

    public double getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public double getPrice2() {
        return price2;
    }

    public int getOrdersCancelled() {
        return ordersCancelled;
    }

    public OrderDescription getOrderDescription() {
        return orderDescription;
    }

    public static <T> void addBaseEditParameters(T orderId, String pair, double volume, Params params){
        String idKey = "txid";
        if(orderId instanceof Number)
            idKey = "userref";
        if(params == null)
            params = new Params();
        params.addParam(idKey, orderId);
        params.addParam("pair", pair);
        params.addParam("volume", sNotationParse(8, volume));
    }

    @Override
    public String toString() {
        return "OrderEdited{" +
                "status='" + status + '\'' +
                ", txId='" + txId + '\'' +
                ", originalTxId='" + originalTxId + '\'' +
                ", volume=" + volume +
                ", price=" + price +
                ", price2=" + price2 +
                ", ordersCancelled=" + ordersCancelled +
                ", orderDescription=" + orderDescription +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
