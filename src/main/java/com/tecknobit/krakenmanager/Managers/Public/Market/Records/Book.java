package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code Book} class is useful to format Book data object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
 *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

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
        super(null);
        this.symbol = symbol;
        this.asks = asks;
        this.bids = bids;
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
        for (int j = 0; j < jsonList.length(); j++){
            JSONArray book = jsonList.getJSONArray(j);
            bookElements.add(new BookElement(book.getDouble(0),
                    book.getDouble(1),
                    book.getLong(2)
            ));
        }
        return bookElements;
    }

    public String getSymbol() {
        return symbol;
    }

    public ArrayList<BookElement> getAsks() {
        return asks;
    }

    public ArrayList<BookElement> getBids() {
        return bids;
    }

    @Override
    public String toString() {
        return "Book{" +
                "symbol='" + symbol + '\'' +
                ", asks=" + asks +
                ", bids=" + bids +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    /**
     * The {@code BookElement} class is useful to format a book element object <br>
     * This class represents ask and bid.
     * **/

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

        public double getPrice() {
            return price;
        }

        public double getVolume() {
            return volume;
        }

        public long getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "BookElement{" +
                    "price=" + price +
                    ", volume=" + volume +
                    ", timestamp=" + timestamp +
                    '}';
        }

    }

}
