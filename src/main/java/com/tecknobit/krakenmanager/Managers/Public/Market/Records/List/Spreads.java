package com.tecknobit.krakenmanager.Managers.Public.Market.Records.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Spreads extends MarketList{

    private final ArrayList<Spread> recentSpreads;

    public Spreads(JSONObject jsonResponse, long last, String symbol, ArrayList<Spread> recentSpreads) {
        super(jsonResponse, last, symbol);
        this.recentSpreads = recentSpreads;
    }

    public Spreads(JSONObject jsonResponse, ArrayList<Spread> recentSpreads) {
        super(jsonResponse);
        this.recentSpreads = recentSpreads;
    }

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

    public static class Spread {

        private final long timestamp;
        private final double doubleOne;
        private final double doubleTwo;

        public Spread(long timestamp, double doubleOne, double doubleTwo) {
            this.timestamp = timestamp;
            this.doubleOne = doubleOne;
            this.doubleTwo = doubleTwo;
        }

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

        public double getDoubleTwo() {
            return doubleTwo;
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
