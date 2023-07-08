package com.tecknobit.krakenmanager.publics.market.records.lists;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Trades} class is useful to format Trades object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
 * Get Recent Trades</a>
 */
public class Trades extends MarketList {

    /**
     * {@code recentTrades} is instance that memorizes list of {@link RecentTrade}
     */
    private final ArrayList<RecentTrade> recentTrades;

    /**
     * Constructor to init a {@link Trades} object
     *
     * @param last:         last timestamp value
     * @param symbol:       symbol value
     * @param recentTrades: list of {@link RecentTrade}
     */
    public Trades(long last, String symbol, ArrayList<RecentTrade> recentTrades) {
        super(last, symbol);
        this.recentTrades = recentTrades;
    }

    /**
     * Constructor to init a {@link Trades} object
     *
     * @param recentTrades: list of {@link RecentTrade}
     */
    public Trades(ArrayList<RecentTrade> recentTrades) {
        super(null);
        this.recentTrades = recentTrades;
    }

    /**
     * Constructor to init a {@link Trades} object
     *
     * @param jsonResponse: base json response
     */
    public Trades(JSONObject jsonResponse) {
        super(jsonResponse);
        recentTrades = new ArrayList<>();
        JSONArray jsonTrades = result.getJSONArray(symbol, new JSONArray());
        for (int j = 0; j < jsonTrades.length(); j++)
            recentTrades.add(new RecentTrade(jsonTrades.getJSONArray(0)));
    }

    /**
     * Method to get {@link #recentTrades} instance <br>
     * No-any params required
     *
     * @return {@link #recentTrades} instance as {@link ArrayList} of {@link RecentTrade}
     */
    public ArrayList<RecentTrade> getRecentTrades() {
        return recentTrades;
    }

    /**
     * The {@code RecentTrade} class is useful to format a recent trade object
     */
    public static class RecentTrade {

        /**
         * {@code price} is instance that memorizes price value
         */
        private final double price;

        /**
         * {@code volume} is instance that memorizes volume value
         */
        private final double volume;

        /**
         * {@code time} is instance that memorizes time value
         */
        private final long time;

        /**
         * {@code side} is instance that memorizes side value (b/l -> buy or sell)
         */
        private final String side;

        /**
         * {@code type} is instance that memorizes type value
         */
        private final String type;

        /**
         * {@code miscellaneous} is instance that memorizes miscellaneous value
         */
        private final String miscellaneous;

        /** Constructor to init a {@link RecentTrade} object
         * @param price: price value
         * @param volume: volume value
         * @param time: time value
         * @param side: side value (b/l -> buy or sell)
         * @param type: type value
         * @param miscellaneous: miscellaneous value
         */
        public RecentTrade(double price, double volume, long time, String side, String type, String miscellaneous) {
            this.price = price;
            this.volume = volume;
            this.time = time;
            this.side = side;
            this.type = type;
            this.miscellaneous = miscellaneous;
        }

        /** Constructor to init a {@link RecentTrade} object
         * @param jsonTrade: recent trade data in {@code "JSON"} format
         */
        public RecentTrade(JSONArray jsonTrade) {
            if (jsonTrade != null) {
                price = jsonTrade.getDouble(0);
                volume = jsonTrade.getDouble(1);
                time = jsonTrade.getLong(2);
                side = jsonTrade.getString(3);
                type = jsonTrade.getString(4);
                miscellaneous = jsonTrade.getString(5);
            } else {
                price = -1;
                volume = -1;
                time = -1;
                side = null;
                type = null;
                miscellaneous = null;
            }
        }

        /**
         * Method to get {@link #price} instance <br>
         * No-any params required
         *
         * @return {@link #price} instance as double
         */
        public double getPrice() {
            return price;
        }

        /**
         * Method to get {@link #price} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #price} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getPrice(int decimals) {
            return roundValue(price, decimals);
        }

        /**
         * Method to get {@link #volume} instance <br>
         * No-any params required
         *
         * @return {@link #volume} instance as double
         */
        public double getVolume() {
            return volume;
        }

        /**
         * Method to get {@link #volume} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #volume} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getVolume(int decimals) {
            return roundValue(volume, decimals);
        }

        /**
         * Method to get {@link #time} instance <br>
         * No-any params required
         *
         * @return {@link #time} instance as long
         */
        public long getTime() {
            return time;
        }

        /**
         * Method to get {@link #side} instance <br>
         * No-any params required
         *
         * @return {@link #side} instance as {@link String}
         */
        public String getSide() {
            return side;
        }

        /**
         * Method to get {@link #type} instance <br>
         * No-any params required
         *
         * @return {@link #type} instance as {@link String}
         */
        public String getType() {
            return type;
        }

        /**
         * Method to get {@link #miscellaneous} instance <br>
         * No-any params required
         *
         * @return {@link #miscellaneous} instance as {@link String}
         */
        public String getMiscellaneous() {
            return miscellaneous;
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
