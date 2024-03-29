package com.tecknobit.krakenmanager.publics.market.records.lists;

import com.tecknobit.krakenmanager.KrakenManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code MarketList} class is useful to format list object
 * @apiNote see the official documentation at:
 * <ul>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
 *           Get OHLC Data</a>
 *    </li>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
 *           Get Recent Trades</a>
 *    </li>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
 *           Get Recent Spreads</a>
 *    </li>
 * </ul>
 * @author N7ghtm4r3 - Tecknobit
 */
public class MarketList extends KrakenManager.KrakenResponse {

    /**
     * {@code last} is instance that memorizes last timestamp value
     */
    protected long last;

    /**
     * {@code symbol} is instance that memorizes symbol value
     */
    protected String symbol;

    /**
     * Constructor to init a {@link MarketList} object
     *
     * @param last:   last timestamp value
     * @param symbol: symbol value
     */
    public MarketList(long last, String symbol) {
        super(null);
        this.last = last;
        this.symbol = symbol;
    }

    /** Constructor to init a {@link MarketList} object
     * @param jsonResponse: base json response
     */
    public MarketList(JSONObject jsonResponse) {
        super(jsonResponse);
        ArrayList<String> keys = new ArrayList<>(result.getJSONObjectSource().keySet());
        try {
            last = result.getLong(keys.get(0));
            symbol = keys.get(1);
        } catch (JSONException e) {
            last = result.getLong(keys.get(1));
            symbol = keys.get(0);
        }
    }

    /**
     * Method to get {@link #last} instance <br>
     * No-any params required
     *
     * @return {@link #last} instance as long
     */
    public long getLast() {
        return last;
    }

    /**
     * Method to get {@link #symbol} instance <br>
     * No-any params required
     *
     * @return {@link #symbol} instance as {@link String}
     */
    public String getSymbol() {
        return symbol;
    }

}