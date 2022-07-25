package com.tecknobit.krakenmanager.Managers.Public.Market.Records.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Trades extends MarketList {

    private final ArrayList<RecentTrade> recentTrades;

    public Trades(JSONObject jsonResponse, long last, String symbol, ArrayList<RecentTrade> recentTrades) {
        super(jsonResponse, last, symbol);
        this.recentTrades = recentTrades;
    }

    public Trades(JSONObject jsonResponse, ArrayList<RecentTrade> recentTrades) {
        super(jsonResponse);
        this.recentTrades = recentTrades;
    }

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
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    public static class RecentTrade {

        private final double price;
        private final double volume;
        private final long time;
        private final String side;
        private final String type;
        private final String miscellaneous;

        public RecentTrade(double price, double volume, long time, String side, String type, String miscellaneous) {
            this.price = price;
            this.volume = volume;
            this.time = time;
            this.side = side;
            this.type = type;
            this.miscellaneous = miscellaneous;
        }

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
