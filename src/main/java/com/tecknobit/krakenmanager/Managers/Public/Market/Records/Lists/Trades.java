package com.tecknobit.krakenmanager.Managers.Public.Market.Records.Lists;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code Trades} class is useful to format Trades object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
 *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class Trades extends MarketList {

    /**
     * {@code recentTrades} is instance that memorizes list of {@link RecentTrade}
     * **/
    private final ArrayList<RecentTrade> recentTrades;

    /** Constructor to init a {@link Trades} object
     * @param jsonResponse: base json response
     * @param last: last timestamp value
     * @param symbol: symbol value
     * @param recentTrades: list of {@link RecentTrade}
     * **/
    public Trades(JSONObject jsonResponse, long last, String symbol, ArrayList<RecentTrade> recentTrades) {
        super(jsonResponse, last, symbol);
        this.recentTrades = recentTrades;
    }

    /** Constructor to init a {@link Trades} object
     * @param jsonResponse: base json response
     * @param recentTrades: list of {@link RecentTrade}
     * **/
    public Trades(JSONObject jsonResponse, ArrayList<RecentTrade> recentTrades) {
        super(jsonResponse);
        this.recentTrades = recentTrades;
    }

    /** Constructor to init a {@link Trades} object
     * @param jsonResponse: base json response
     * **/
    public Trades(JSONObject jsonResponse) {
        super(jsonResponse);
        recentTrades = new ArrayList<>();
        JSONArray jsonTrades = result.getJSONArray(symbol);;
        for (int j = 0; j < jsonTrades.length(); j++)
            recentTrades.add(new RecentTrade(jsonTrades.getJSONArray(0)));
    }

    public ArrayList<RecentTrade> getRecentTrades() {
        return recentTrades;
    }

    @Override
    public String toString() {
        return "Trades{" +
                "recentTrades=" + recentTrades +
                ", last=" + last +
                ", symbol='" + symbol + '\'' +
                ", result=" + result +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    /**
     * The {@code RecentTrade} class is useful to format a recent trade object
     * **/

    public static class RecentTrade {

        /**
         * {@code price} is instance that memorizes price value
         * **/
        private final double price;

        /**
         * {@code volume} is instance that memorizes volume value
         * **/
        private final double volume;

        /**
         * {@code time} is instance that memorizes time value
         * **/
        private final long time;

        /**
         * {@code side} is instance that memorizes side value (b/l -> buy or sell)
         * **/
        private final String side;

        /**
         * {@code type} is instance that memorizes type value
         * **/
        private final String type;

        /**
         * {@code miscellaneous} is instance that memorizes miscellaneous value
         * **/
        private final String miscellaneous;

        /** Constructor to init a {@link RecentTrade} object
         * @param price: price value
         * @param volume: volume value
         * @param time: time value
         * @param side: side value (b/l -> buy or sell)
         * @param type: type value
         * @param miscellaneous: miscellaneous value
         * **/
        public RecentTrade(double price, double volume, long time, String side, String type, String miscellaneous) {
            this.price = price;
            this.volume = volume;
            this.time = time;
            this.side = side;
            this.type = type;
            this.miscellaneous = miscellaneous;
        }

        /** Constructor to init a {@link RecentTrade} object
         * @param jsonTrade: recent trade data in JSON format
         * **/
        public RecentTrade(JSONArray jsonTrade) {
            if(jsonTrade != null){
                price = jsonTrade.getDouble(0);
                volume = jsonTrade.getDouble(1);
                time = jsonTrade.getLong(2);
                side = jsonTrade.getString(3);
                type = jsonTrade.getString(4);
                miscellaneous = jsonTrade.getString(5);
            }else{
                price = -1;
                volume = -1;
                time = -1;
                side = null;
                type = null;
                miscellaneous = null;
            }
        }

        public double getPrice() {
            return price;
        }

        public double getVolume() {
            return volume;
        }

        public long getTime() {
            return time;
        }

        public String getSide() {
            return side;
        }

        public String getType() {
            return type;
        }

        public String getMiscellaneous() {
            return miscellaneous;
        }

        @Override
        public String toString() {
            return "RecentTrade{" +
                    "price=" + price +
                    ", volume=" + volume +
                    ", time=" + time +
                    ", side='" + side + '\'' +
                    ", type='" + type + '\'' +
                    ", miscellaneous='" + miscellaneous + '\'' +
                    '}';
        }

    }

}
