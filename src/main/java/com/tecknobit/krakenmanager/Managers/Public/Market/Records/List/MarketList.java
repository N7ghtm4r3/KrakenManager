package com.tecknobit.krakenmanager.Managers.Public.Market.Records.List;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.ArrayList;

public class MarketList extends KrakenManager.KrakenResponse {

    protected final long last;
    protected final String symbol;
    protected JSONObject result;

    public MarketList(JSONObject jsonResponse, long last, String symbol) {
        super(jsonResponse);
        this.last = last;
        this.symbol = symbol;
    }

    public MarketList(JSONObject jsonResponse) {
        super(jsonResponse);
        result = getResult();
        ArrayList<String> keys = new ArrayList<>(result.keySet());
        last = result.getLong(keys.get(0));
        symbol = keys.get(1);
    }

    public long getLast() {
        return last;
    }

    public String getSymbol() {
        return symbol;
    }

}
