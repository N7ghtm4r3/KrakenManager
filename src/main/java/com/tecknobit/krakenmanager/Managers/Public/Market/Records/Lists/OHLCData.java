package com.tecknobit.krakenmanager.Managers.Public.Market.Records.Lists;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code OHLCData} class is useful to format OHLC data object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
 *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class OHLCData extends MarketList {

    /**
     * {@code INTERVAL_1m} is constant for one minute interval
     * **/
    public static final int INTERVAL_1m = 1;

    /**
     * {@code INTERVAL_5m} is constant for five minutes interval
     * **/
    public static final int INTERVAL_5m = 5;

    /**
     * {@code INTERVAL_15m} is constant for fifteen minutes interval
     * **/
    public static final int INTERVAL_15m = 15;

    /**
     * {@code INTERVAL_30m} is constant for thirty minutes interval
     * **/
    public static final int INTERVAL_30m = 30;

    /**
     * {@code INTERVAL_1h} is constant for one hour interval
     * **/
    public static final int INTERVAL_1h = 60;

    /**
     * {@code INTERVAL_4h} is constant for four hours interval
     * **/
    public static final int INTERVAL_4h = 240;

    /**
     * {@code INTERVAL_1d} is constant for one day interval
     * **/
    public static final int INTERVAL_1d = 1440;

    /**
     * {@code INTERVAL_1w} is constant for one-week interval
     * **/
    public static final int INTERVAL_1w = 10080;

    /**
     * {@code INTERVAL_1M} is constant for one-month interval
     * **/
    public static final int INTERVAL_1M = 21600;

    /**
     * {@code ticksData} is instance that memorizes list of {@link TickData}
     * **/
    private final ArrayList<TickData> ticksData;

    /** Constructor to init a {@link OHLCData} object
     * @param jsonResponse: base json response
     * @param last: last timestamp value
     * @param symbol: symbol value
     * @param ticksData: list of {@link TickData}
     * **/
    public OHLCData(JSONObject jsonResponse, long last, String symbol, ArrayList<TickData> ticksData) {
        super(jsonResponse, last, symbol);
        this.ticksData = ticksData;
    }

    /** Constructor to init a {@link OHLCData} object
     * @param last: last timestamp value
     * @param symbol: symbol value
     * @param ticksData: list of {@link TickData}
     * **/
    public OHLCData(long last, String symbol, ArrayList<TickData> ticksData) {
        super(null, last, symbol);
        this.ticksData = ticksData;
    }

    /** Constructor to init a {@link OHLCData} object
     * @param jsonData: OHLC data in JSON format
     * **/
    public OHLCData(JSONObject jsonData) {
        super(jsonData);
        ticksData = new ArrayList<>();
        JSONArray dataList = result.getJSONArray(symbol);
        for (int j = 0; j < dataList.length(); j++)
            ticksData.add(new TickData(dataList.getJSONArray(j)));
    }

    public long getLast() {
        return last;
    }

    public String getSymbol() {
        return symbol;
    }

    public ArrayList<TickData> getTicksData() {
        return ticksData;
    }

    @Override
    public String toString() {
        return "OHLCData{" +
                "last=" + last +
                ", symbol='" + symbol + '\'' +
                ", ticksData=" + ticksData +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    /**
     * The {@code TickData} class is useful to format a tick data object
     * **/

    public static class TickData {

        /**
         * {@code time} is instance that memorizes time value
         * **/
        private final long time;

        /**
         * {@code open} is instance that memorizes open value
         * **/
        private final double open;

        /**
         * {@code high} is instance that memorizes high value
         * **/
        private final double high;

        /**
         * {@code low} is instance that memorizes low value
         * **/
        private final double low;

        /**
         * {@code close} is instance that memorizes close value
         * **/
        private final double close;

        /**
         * {@code vWap} is instance that memorizes vWap value
         * **/
        private final double vWap;

        /**
         * {@code volume} is instance that memorizes volume value
         * **/
        private final double volume;

        /**
         * {@code count} is instance that memorizes count value
         * **/
        private final int count;

        /** Constructor to init a {@link TickData} object
         * @param time: time value
         * @param open: open value
         * @param high: high value
         * @param low: low value
         * @param close: close value
         * @param vWap: vWap value
         * @param volume: volume value
         * @param count: count value
         * **/
        public TickData(long time, double open, double high, double low, double close, double vWap, double volume, int count) {
            this.time = time;
            this.open = open;
            this.high = high;
            this.low = low;
            this.close = close;
            this.vWap = vWap;
            this.volume = volume;
            this.count = count;
        }

        /** Constructor to init a {@link TickData} object
         * @param jsonTick: TickData in JSON format
         * **/
        public TickData(JSONArray jsonTick){
            if(jsonTick != null){
                time = jsonTick.getLong(0);
                open = jsonTick.getDouble(1);
                high = jsonTick.getDouble(2);
                low = jsonTick.getDouble(3);
                close = jsonTick.getDouble(4);
                vWap = jsonTick.getDouble(5);
                volume = jsonTick.getDouble(6);
                count = jsonTick.getInt(7);
            }else{
                time = -1;
                open = -1;
                high = -1;
                low = -1;
                close = -1;
                vWap = -1;
                volume = -1;
                count = -1;
            }
        }

        public long getTime() {
            return time;
        }

        public double getOpen() {
            return open;
        }

        public double getHigh() {
            return high;
        }

        public double getLow() {
            return low;
        }

        public double getClose() {
            return close;
        }

        public double getvWap() {
            return vWap;
        }

        public double getVolume() {
            return volume;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "TickData{" +
                    "time=" + time +
                    ", open=" + open +
                    ", high=" + high +
                    ", low=" + low +
                    ", close=" + close +
                    ", vWap=" + vWap +
                    ", volume=" + volume +
                    ", count=" + count +
                    '}';
        }

    }

}
