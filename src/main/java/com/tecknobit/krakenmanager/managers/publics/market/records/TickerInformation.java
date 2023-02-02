package com.tecknobit.krakenmanager.managers.publics.market.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;
import static com.tecknobit.krakenmanager.managers.publics.market.records.TickerInformation.MarketAction.assembleMarketAction;
import static com.tecknobit.krakenmanager.managers.publics.market.records.TickerInformation.MarketParam.assembleMarketParam;
import static com.tecknobit.krakenmanager.managers.publics.market.records.TickerInformation.Trade.assembleTrade;

/**
 * The {@code TickerInformation} class is useful to format Ticker information object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation">
 * Get Ticker Information</a>
 **/
public class TickerInformation extends KrakenManager.KrakenResponse {

    /**
     * {@code symbol} is instance that memorizes symbol value
     * **/
    private final String symbol;

    /**
     * {@code ask} is instance that memorizes ask value
     * **/
    private final MarketAction ask;

    /**
     * {@code bid} is instance that memorizes bid value
     * **/
    private final MarketAction bid;

    /**
     * {@code close} is instance that memorizes close value
     * **/
    private final Trade close;

    /**
     * {@code volume} is instance that memorizes volume value
     * **/
    private final MarketParam volume;

    /**
     * {@code volumeWeightedAvgPrice} is instance that memorizes weighted volume average price value
     * **/
    private final MarketParam volumeWeightedAvgPrice;

    /**
     * {@code trades} is instance that memorizes trades value
     * **/
    private final MarketParam trades;

    /**
     * {@code low} is instance that memorizes low value
     * **/
    private final MarketParam low;

    /**
     * {@code high} is instance that memorizes high value
     * **/
    private final MarketParam high;

    /**
     * {@code openPrice} is instance that memorizes open price value
     * **/
    private final double openPrice;

    /** Constructor to init a {@link TickerInformation} object
     *
     * @param symbol: symbol value
     * @param ask: ask value
     * @param bid: bid value
     * @param close: close value
     * @param volume: volume value
     * @param volumeWeightedAvgPrice: weighted volume average price value
     * @param trades: tradesv alue
     * @param low: low value
     * @param high: high value
     * @param openPrice: open price value
     * **/
    public TickerInformation(String symbol, MarketAction ask, MarketAction bid, Trade close,
                             MarketParam volume, MarketParam volumeWeightedAvgPrice, MarketParam trades, MarketParam low,
                             MarketParam high, double openPrice) {
        super(null);
        this.symbol = symbol;
        this.ask = ask;
        this.bid = bid;
        this.close = close;
        this.volume = volume;
        this.volumeWeightedAvgPrice = volumeWeightedAvgPrice;
        this.trades = trades;
        this.low = low;
        this.high = high;
        this.openPrice = openPrice;
    }

    /** Constructor to init a {@link TickerInformation} object
     * @param jsonTicker: base json response
     * **/
    public TickerInformation(JSONObject jsonTicker) {
        super(jsonTicker);
        this.symbol = result.getJSONObjectSource().keys().next();
        JsonHelper hTicker = new JsonHelper(result.getJSONObject(symbol, new JSONObject()));
        ask = assembleMarketAction(hTicker.getJSONArray("a", new JSONArray()));
        bid = assembleMarketAction(hTicker.getJSONArray("b", new JSONArray()));
        close = assembleTrade(hTicker.getJSONArray("c", new JSONArray()));
        volume = assembleMarketParam(hTicker.getJSONArray("v", new JSONArray()));
        volumeWeightedAvgPrice = assembleMarketParam(hTicker.getJSONArray("p", new JSONArray()));
        trades = assembleMarketParam(hTicker.getJSONArray("t", new JSONArray()));
        low = assembleMarketParam(hTicker.getJSONArray("l", new JSONArray()));
        high = assembleMarketParam(hTicker.getJSONArray("h", new JSONArray()));
        openPrice = hTicker.getDouble("o", 0);
    }

    /**
     * Method to get {@link #symbol} instance <br>
     * No-any params required
     *
     * @return {@link #symbol} instance as {@link String}
     **/
    public String getSymbol() {
        return symbol;
    }

    /**
     * Method to get {@link #ask} instance <br>
     * No-any params required
     *
     * @return {@link #ask} instance as {@link MarketAction}
     **/
    public MarketAction getAsk() {
        return ask;
    }

    /**
     * Method to get {@link #bid} instance <br>
     * No-any params required
     *
     * @return {@link #bid} instance as {@link MarketAction}
     **/
    public MarketAction getBid() {
        return bid;
    }

    /**
     * Method to get {@link #close} instance <br>
     * No-any params required
     *
     * @return {@link #close} instance as {@link Trade}
     **/
    public Trade getClose() {
        return close;
    }

    /**
     * Method to get {@link #volume} instance <br>
     * No-any params required
     *
     * @return {@link #volume} instance as {@link MarketParam}
     **/
    public MarketParam getVolume() {
        return volume;
    }

    /**
     * Method to get {@link #volumeWeightedAvgPrice} instance <br>
     * No-any params required
     *
     * @return {@link #volumeWeightedAvgPrice} instance as {@link MarketParam}
     **/
    public MarketParam getVolumeWeightedAvgPrice() {
        return volumeWeightedAvgPrice;
    }

    /**
     * Method to get {@link #trades} instance <br>
     * No-any params required
     *
     * @return {@link #trades} instance as {@link MarketParam}
     **/
    public MarketParam getTrades() {
        return trades;
    }

    /**
     * Method to get {@link #low} instance <br>
     * No-any params required
     *
     * @return {@link #low} instance as {@link MarketParam}
     **/
    public MarketParam getLow() {
        return low;
    }

    /**
     * Method to get {@link #high} instance <br>
     * No-any params required
     *
     * @return {@link #high} instance as {@link MarketParam}
     **/
    public MarketParam getHigh() {
        return high;
    }

    /**
     * Method to get {@link #openPrice} instance <br>
     * No-any params required
     *
     * @return {@link #openPrice} instance as double
     **/
    public double getOpenPrice() {
        return openPrice;
    }

    /**
     * Method to get {@link #openPrice} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #openPrice} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getOpenPrice(int decimals) {
        return roundValue(openPrice, decimals);
    }

    /**
     * The {@code Trade} class is useful to format a trade object
     **/
    public static class Trade {

        /**
         * {@code price} is instance that memorizes price value
         **/
        protected final double price;

        /**
         * {@code lotVolume} is instance that memorizes lot volume value
         **/
        protected final double lotVolume;

        /**
         * Constructor to init a {@link Trade} object
         *
         * @param price:     price value
         * @param lotVolume: lot volume value
         **/
        public Trade(double price, double lotVolume) {
            this.price = price;
            this.lotVolume = lotVolume;
        }

        /**
         * Method to get {@link #price} instance <br>
         * No-any params required
         *
         * @return {@link #price} instance as double
         **/
        public double getPrice() {
            return price;
        }

        /**
         * Method to get {@link #price} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #price} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getPrice(int decimals) {
            return roundValue(price, decimals);
        }

        /**
         * Method to get {@link #lotVolume} instance <br>
         * No-any params required
         *
         * @return {@link #lotVolume} instance as double
         **/
        public double getLotVolume() {
            return lotVolume;
        }

        /**
         * Method to get {@link #lotVolume} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #lotVolume} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getLotVolume(int decimals) {
            return roundValue(lotVolume, decimals);
        }

        /**
         * Method to assemble a trade object
         *
         * @param jsonTrade: obtained by response request
         * @return trade object as {@link Trade}
         **/
        public static Trade assembleTrade(JSONArray jsonTrade) {
            int valuesLength = jsonTrade.length();
            final double[] values = new double[valuesLength];
            for (int j = 0; j < valuesLength; j++)
                values[j] = jsonTrade.getDouble(j);
            return new Trade(values[0], values[1]);
        }

        /**
         * Returns a string representation of the object <br>
         * No-any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

    /**
     * The {@code MarketAction} class is useful to format a market action object <br>
     * This class represents ask and bid.
     * **/
    public static class MarketAction extends Trade {

        /**
         * {@code wholeLotVolume} is instance that memorizes lot whole volume value
         **/
        private final double wholeLotVolume;

        /**
         * Constructor to init a {@link MarketAction} object
         *
         * @param price:          price value
         * @param lotVolume:      lot volume value
         * @param wholeLotVolume: lot whole volume value
         **/
        public MarketAction(double price, double lotVolume, double wholeLotVolume) {
            super(price, lotVolume);
            this.wholeLotVolume = wholeLotVolume;
        }

        /**
         * Method to get {@link #wholeLotVolume} instance <br>
         * No-any params required
         *
         * @return {@link #wholeLotVolume} instance as double
         **/
        public double getWholeLotVolume() {
            return wholeLotVolume;
        }

        /**
         * Method to get {@link #wholeLotVolume} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #wholeLotVolume} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getWholeLotVolume(int decimals) {
            return roundValue(wholeLotVolume, decimals);
        }

        /**
         * Method to assemble a market action object
         *
         * @param jsonMarket: obtained by response request
         * @return market action object as {@link MarketAction}
         **/
        @Returner
        protected static MarketAction assembleMarketAction(JSONArray jsonMarket) {
            int valuesLength = jsonMarket.length();
            final double[] values = new double[valuesLength];
            for (int j = 0; j < valuesLength; j++)
                values[j] = jsonMarket.getDouble(j);
            return new MarketAction(values[0], values[1], values[2]);
        }

        /**
         * Returns a string representation of the object <br>
         * No-any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

    /**
     * The {@code MarketParam} class is useful to format a market param object
     * **/
    public static class MarketParam {

        /**
         * {@code today} is instance that memorizes today value
         **/
        private final double today;

        /**
         * {@code last24Hours} is instance that memorizes last 24 Hours value
         **/
        private final double last24Hours;

        /**
         * Constructor to init a {@link MarketParam} object
         *
         * @param today:       today value
         * @param last24Hours: last 24 Hours value
         **/
        public MarketParam(double today, double last24Hours) {
            this.today = today;
            this.last24Hours = last24Hours;
        }

        /**
         * Method to get {@link #today} instance <br>
         * No-any params required
         *
         * @return {@link #today} instance as double
         **/
        public double getToday() {
            return today;
        }

        /**
         * Method to get {@link #today} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #today} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getToday(int decimals) {
            return roundValue(today, decimals);
        }

        /**
         * Method to get {@link #last24Hours} instance <br>
         * No-any params required
         *
         * @return {@link #last24Hours} instance as double
         **/
        public double getLast24Hours() {
            return last24Hours;
        }

        /**
         * Method to get {@link #last24Hours} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #last24Hours} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getLast24Hours(int decimals) {
            return roundValue(last24Hours, decimals);
        }

        /**
         * Method to assemble a market param object
         *
         * @param jsonParam: obtained by response request
         * @return market param object as {@link MarketParam}
         **/
        @Returner
        protected static MarketParam assembleMarketParam(JSONArray jsonParam) {
            int valuesLength = jsonParam.length();
            final double[] values = new double[valuesLength];
            for (int j = 0; j < valuesLength; j++)
                values[j] = jsonParam.getDouble(j);
            return new MarketParam(values[0], values[1]);
        }

        /**
         * Returns a string representation of the object <br>
         * No-any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
