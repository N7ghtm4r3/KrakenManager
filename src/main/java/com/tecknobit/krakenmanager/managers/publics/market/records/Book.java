package com.tecknobit.krakenmanager.managers.publics.market.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Book} class is useful to format Book data object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
 * https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook</a>
 **/
public class Book extends KrakenManager.KrakenResponse {

    /**
     * {@code symbol} is instance that memorizes symbol value
     * **/
    private final String symbol;

    /**
     * {@code asks} is instance that memorizes list of {@link BookElement} for asks
     * **/
    private final ArrayList<BookElement> asks;

    /**
     * {@code bids} is instance that memorizes list of {@link BookElement} for bids
     * **/
    private final ArrayList<BookElement> bids;

    /** Constructor to init a {@link Book} object
     * @param jsonResponse: base json response
     * @param symbol: symbol value
     * @param asks: list of {@link BookElement} for asks
     * @param bids: list of {@link BookElement} for bids
     * **/
    public Book(JSONObject jsonResponse, String symbol, ArrayList<BookElement> asks, ArrayList<BookElement> bids) {
        super(jsonResponse);
        this.symbol = symbol;
        this.asks = asks;
        this.bids = bids;
    }

    /** Constructor to init a {@link Book} object
     * @param symbol: symbol value
     * @param asks: list of {@link BookElement} for asks
     * @param bids: list of {@link BookElement} for bids
     * **/
    public Book(String symbol, ArrayList<BookElement> asks, ArrayList<BookElement> bids) {
        this(null, symbol, asks, bids);
    }

    /** Constructor to init a {@link Book} object
     * @param jsonBook: base json response
     * **/
    public Book(JSONObject jsonBook) {
        super(jsonBook);
        JSONObject book = getResult();
        symbol = book.keys().next();
        JSONObject lists = book.getJSONObject(symbol);
        asks = assembleBookElementsList(lists.getJSONArray("asks"));
        bids = assembleBookElementsList(lists.getJSONArray("bids"));
    }

    /** Method to assemble a book element list
     * @param jsonList: jsonObject obtained by response request
     * @return book element list as {@link ArrayList} of {@link BookElement}
     * **/
    private ArrayList<BookElement> assembleBookElementsList(JSONArray jsonList){
        ArrayList<BookElement> bookElements = new ArrayList<>();
        for (int j = 0; j < jsonList.length(); j++) {
            JSONArray book = jsonList.getJSONArray(j);
            bookElements.add(new BookElement(book.getDouble(0),
                    book.getDouble(1),
                    book.getLong(2)
            ));
        }
        return bookElements;
    }

    /**
     * Method to get {@link #symbol} instance <br>
     * Any params required
     *
     * @return {@link #symbol} instance as {@link String}
     **/
    public String getSymbol() {
        return symbol;
    }

    /**
     * Method to get {@link #asks} instance <br>
     * Any params required
     *
     * @return {@link #asks} instance as {@link ArrayList} of {@link BookElement}
     **/
    public ArrayList<BookElement> getAsks() {
        return asks;
    }

    /**
     * Method to get {@link #bids} instance <br>
     * Any params required
     *
     * @return {@link #bids} instance as {@link ArrayList} of {@link BookElement}
     **/
    public ArrayList<BookElement> getBids() {
        return bids;
    }

    /**
     * Returns a string representation of the object <br>
     * Any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    /**
     * The {@code BookElement} class is useful to format a book element object <br>
     * This class represents ask and bid.
     **/
    public static class BookElement {

        /**
         * {@code price} is instance that memorizes price value
         * **/
        private final double price;

        /**
         * {@code volume} is instance that memorizes volume value
         * **/
        private final double volume;

        /**
         * {@code timestamp} is instance that memorizes timestamp value
         * **/
        private final long timestamp;

        /** Constructor to init a {@link BookElement}
         * @param price: price value
         * @param volume: volume value
         * @param timestamp: timestamp value
         * **/
        public BookElement(double price, double volume, long timestamp) {
            this.price = price;
            this.volume = volume;
            this.timestamp = timestamp;
        }

        /**
         * Method to get {@link #price} instance <br>
         * Any params required
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
         * Method to get {@link #volume} instance <br>
         * Any params required
         *
         * @return {@link #volume} instance as double
         **/
        public double getVolume() {
            return volume;
        }

        /**
         * Method to get {@link #volume} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #volume} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getVolume(int decimals) {
            return roundValue(volume, decimals);
        }

        /**
         * Method to get {@link #timestamp} instance <br>
         * Any params required
         *
         * @return {@link #timestamp} instance as long
         **/
        public long getTimestamp() {
            return timestamp;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
