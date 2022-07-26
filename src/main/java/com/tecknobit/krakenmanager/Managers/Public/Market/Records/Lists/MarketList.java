package com.tecknobit.krakenmanager.Managers.Public.Market.Records.Lists;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code MarketList} class is useful to format list object
 * @apiNote see official documentation at:
 <ul>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
            https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
     </li>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
            https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades</a>
     </li>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
            https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads</a>
     </li>
 </ul>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class MarketList extends KrakenManager.KrakenResponse {

    /**
     * {@code last} is instance that memorizes last timestamp value
     * **/
    protected long last;

    /**
     * {@code symbol} is instance that memorizes symbol value
     * **/
    protected String symbol;

    /**
     * {@code result} is instance that memorizes result value in JSON format
     * **/
    protected JSONObject result;

    /** Constructor to init a {@link MarketList} object
     * @param jsonResponse: base json response
     * @param last: last timestamp value
     * @param symbol: symbol value
     * **/
    public MarketList(JSONObject jsonResponse, long last, String symbol) {
        super(jsonResponse);
        this.last = last;
        this.symbol = symbol;
    }

    /** Constructor to init a {@link MarketList} object
     * @param jsonResponse: base json response
     * **/
    public MarketList(JSONObject jsonResponse) {
        super(jsonResponse);
        result = getResult();
        ArrayList<String> keys = new ArrayList<>(result.keySet());
        try {
            last = result.getLong(keys.get(0));
            symbol = keys.get(1);
        }catch (JSONException e){
            last = result.getLong(keys.get(1));
            symbol = keys.get(0);
        }
    }

    public long getLast() {
        return last;
    }

    public String getSymbol() {
        return symbol;
    }

}