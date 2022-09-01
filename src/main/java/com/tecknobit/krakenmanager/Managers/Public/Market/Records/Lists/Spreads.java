package com.tecknobit.krakenmanager.Managers.Public.Market.Records.Lists;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.Tools.Trading.TradingTools.roundValue;

/**
 * The {@code Spreads} class is useful to format Spreads object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
 * https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads</a>
 **/

public class Spreads extends MarketList {

    /**
     * {@code recentSpreads} is instance that memorizes list of {@link Spread}
     * **/
    private final ArrayList<Spread> recentSpreads;

    /** Constructor to init a {@link Spreads} object
     * @param jsonResponse: base json response
     * @param last: last timestamp value
     * @param symbol: symbol value
     * @param recentSpreads: list of {@link Spread}
     * **/
    public Spreads(JSONObject jsonResponse, long last, String symbol, ArrayList<Spread> recentSpreads) {
        super(jsonResponse, last, symbol);
        this.recentSpreads = recentSpreads;
    }

    /** Constructor to init a {@link Spreads} object
     * @param jsonResponse: base json response
     * @param recentSpreads: list of {@link Spread}
     * **/
    public Spreads(JSONObject jsonResponse, ArrayList<Spread> recentSpreads) {
        super(jsonResponse);
        this.recentSpreads = recentSpreads;
    }

    /** Constructor to init a {@link Spreads} object
     * @param jsonResponse: base json response
     * **/
    public Spreads(JSONObject jsonResponse) {
        super(jsonResponse);
        recentSpreads = new ArrayList<>();
        JSONArray jsonSpreads = result.getJSONArray(symbol);
        for (int j = 0; j < jsonSpreads.length(); j++)
            recentSpreads.add(new Spread(jsonSpreads.getJSONArray(j)));
    }

    public ArrayList<Spread> getRecentSpreads() {
        return recentSpreads;
    }

    /**
     * The {@code Spread} class is useful to format a spread object
     * **/

    public static class Spread {

        /**
         * {@code timestamp} is instance that memorizes timestamp value
         * **/
        private final long timestamp;

        /**
         * {@code doubleOne} is instance that memorizes doubleOne value
         * **/
        private final double doubleOne;

        /**
         * {@code doubleTwo} is instance that memorizes doubleTwo value
         * **/
        private final double doubleTwo;

        /** Constructor to init a {@link Spread} object
         * @param timestamp: timestamp value
         * @param doubleOne: doubleOne value
         * @param doubleTwo: doubleTwo value
         * **/
        public Spread(long timestamp, double doubleOne, double doubleTwo) {
            this.timestamp = timestamp;
            this.doubleOne = doubleOne;
            this.doubleTwo = doubleTwo;
        }

        /** Constructor to init a {@link Spread} object
         * @param jsonSpread: spread data in JSON format
         * **/
        public Spread(JSONArray jsonSpread) {
            if(jsonSpread != null){
                timestamp = jsonSpread.getLong(2);
                doubleOne = jsonSpread.getDouble(0);
                doubleTwo = jsonSpread.getDouble(1);
            }else{
                timestamp = -1;
                doubleOne = -1;
                doubleTwo = -1;
            }
        }

        public long getTimestamp() {
            return timestamp;
        }

        public double getDoubleOne() {
            return doubleOne;
        }

        /**
         * Method to get {@link #doubleOne} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #doubleOne} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getDoubleOne(int decimals) {
            return roundValue(doubleOne, decimals);
        }

        public double getDoubleTwo() {
            return doubleTwo;
        }

        /**
         * Method to get {@link #doubleTwo} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #doubleTwo} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getDoubleTwo(int decimals) {
            return roundValue(doubleTwo, decimals);
        }

        @Override
        public String toString() {
            return "Spread{" +
                    "timestamp=" + timestamp +
                    ", doubleOne=" + doubleOne +
                    ", doubleTwo=" + doubleTwo +
                    '}';
        }

    }

}
