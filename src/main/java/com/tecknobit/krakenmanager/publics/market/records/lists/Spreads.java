package com.tecknobit.krakenmanager.publics.market.records.lists;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Spreads} class is useful to format Spreads object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
 * Get Recent Spreads</a>
 */
public class Spreads extends MarketList {

    /**
     * {@code recentSpreads} is instance that memorizes list of {@link Spread}
     */
    private final ArrayList<Spread> recentSpreads;

    /**
     * Constructor to init a {@link Spreads} object
     *
     * @param last:          last timestamp value
     * @param symbol:        symbol value
     * @param recentSpreads: list of {@link Spread}
     */
    public Spreads(long last, String symbol, ArrayList<Spread> recentSpreads) {
        super(last, symbol);
        this.recentSpreads = recentSpreads;
    }

    /**
     * Constructor to init a {@link Spreads} object
     *
     * @param recentSpreads: list of {@link Spread}
     */
    public Spreads(ArrayList<Spread> recentSpreads) {
        super(null);
        this.recentSpreads = recentSpreads;
    }

    /** Constructor to init a {@link Spreads} object
     * @param jsonResponse: base json response
     */
    public Spreads(JSONObject jsonResponse) {
        super(jsonResponse);
        recentSpreads = new ArrayList<>();
        JSONArray jsonSpreads = result.getJSONArray(symbol, new JSONArray());
        for (int j = 0; j < jsonSpreads.length(); j++)
            recentSpreads.add(new Spread(jsonSpreads.getJSONArray(j)));
    }

    /**
     * Method to get {@link #recentSpreads} instance <br>
     * No-any params required
     *
     * @return {@link #recentSpreads} instance as {@link ArrayList} of {@link Spread}
     */
    public ArrayList<Spread> getRecentSpreads() {
        return recentSpreads;
    }

    /**
     * The {@code Spread} class is useful to format a spread object
     */
    public static class Spread {

        /**
         * {@code timestamp} is instance that memorizes timestamp value
         */
        private final long timestamp;

        /**
         * {@code doubleOne} is instance that memorizes doubleOne value
         */
        private final double doubleOne;

        /**
         * {@code doubleTwo} is instance that memorizes doubleTwo value
         */
        private final double doubleTwo;

        /** Constructor to init a {@link Spread} object
         * @param timestamp: timestamp value
         * @param doubleOne: doubleOne value
         * @param doubleTwo: doubleTwo value
         */
        public Spread(long timestamp, double doubleOne, double doubleTwo) {
            this.timestamp = timestamp;
            this.doubleOne = doubleOne;
            this.doubleTwo = doubleTwo;
        }

        /** Constructor to init a {@link Spread} object
         * @param jsonSpread: spread data in {@code "JSON"} format
         */
        public Spread(JSONArray jsonSpread) {
            if (jsonSpread != null) {
                timestamp = jsonSpread.getLong(2);
                doubleOne = jsonSpread.getDouble(0);
                doubleTwo = jsonSpread.getDouble(1);
            } else {
                timestamp = -1;
                doubleOne = -1;
                doubleTwo = -1;
            }
        }

        /**
         * Method to get {@link #timestamp} instance <br>
         * No-any params required
         *
         * @return {@link #timestamp} instance as long
         */
        public long getTimestamp() {
            return timestamp;
        }

        /**
         * Method to get {@link #doubleOne} instance <br>
         * No-any params required
         *
         * @return {@link #doubleOne} instance as double
         */
        public double getDoubleOne() {
            return doubleOne;
        }

        /**
         * Method to get {@link #doubleOne} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #doubleOne} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getDoubleOne(int decimals) {
            return roundValue(doubleOne, decimals);
        }

        /**
         * Method to get {@link #doubleTwo} instance <br>
         * No-any params required
         *
         * @return {@link #doubleTwo} instance as double
         */
        public double getDoubleTwo() {
            return doubleTwo;
        }

        /**
         * Method to get {@link #doubleTwo} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #doubleTwo} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         */
        public double getDoubleTwo(int decimals) {
            return roundValue(doubleTwo, decimals);
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
