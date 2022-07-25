package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Book extends KrakenManager.KrakenResponse {

    private final String symbol;
    private final ArrayList<BookElement> asks;
    private final ArrayList<BookElement> bids;

    public Book(JSONObject jsonResponse, String symbol, ArrayList<BookElement> asks, ArrayList<BookElement> bids) {
        super(jsonResponse);
        this.symbol = symbol;
        this.asks = asks;
        this.bids = bids;
    }

    public Book(String symbol, ArrayList<BookElement> asks, ArrayList<BookElement> bids) {
        super(null);
        this.symbol = symbol;
        this.asks = asks;
        this.bids = bids;
    }

    public Book(JSONObject jsonBook) {
        super(jsonBook);
        JSONObject book = getResult();
        symbol = book.keys().next();
        JSONObject lists = book.getJSONObject(symbol);
        asks = assembleBookElementsList(lists.getJSONArray("asks"));
        bids = assembleBookElementsList(lists.getJSONArray("bids"));
    }

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
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    public static class BookElement {

        private final double price;
        private final double volume;
        private final long timestamp;

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
