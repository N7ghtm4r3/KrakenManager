package com.tecknobit.krakenmanager.managers.publics.market.records.lists;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code OHLCData} class is useful to format OHLC data object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
 * Get OHLC Data</a>
 **/
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
     *
     * @param last: last timestamp value
     * @param symbol: symbol value
     * @param ticksData: list of {@link TickData}
     * **/
    public OHLCData(long last, String symbol, ArrayList<TickData> ticksData) {
        super(last, symbol);
        this.ticksData = ticksData;
    }

    /**
     * Constructor to init a {@link OHLCData} object
     *
     * @param jsonData: OHLC data in {@code "JSON"} format
     **/
    public OHLCData(JSONObject jsonData) {
        super(jsonData);
        ticksData = new ArrayList<>();
        JSONArray dataList = result.getJSONArray(symbol, new JSONArray());
        for (int j = 0; j < dataList.length(); j++)
            ticksData.add(new TickData(dataList.getJSONArray(j)));
    }

    /**
     * Method to get {@link #ticksData} instance <br>
     * No-any params required
     *
     * @return {@link #ticksData} instance as {@link ArrayList} of {@link TickData}
     **/
    public ArrayList<TickData> getTicksData() {
        return ticksData;
    }

    /**
     * The {@code TickData} class is useful to format a tick data object
     **/
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

        /**
         * Constructor to init a {@link TickData} object
         *
         * @param jsonTick: TickData in {@code "JSON"} format
         **/
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

        /**
         * Method to get {@link #time} instance <br>
         * No-any params required
         *
         * @return {@link #time} instance as long
         **/
        public long getTime() {
            return time;
        }

        /**
         * Method to get {@link #open} instance <br>
         * No-any params required
         *
         * @return {@link #open} instance as double
         **/
        public double getOpen() {
            return open;
        }

        /**
         * Method to get {@link #open} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #open} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getOpen(int decimals) {
            return roundValue(open, decimals);
        }

        /**
         * Method to get {@link #high} instance <br>
         * No-any params required
         *
         * @return {@link #high} instance as double
         **/
        public double getHigh() {
            return high;
        }

        /**
         * Method to get {@link #high} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #high} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getHigh(int decimals) {
            return roundValue(high, decimals);
        }

        /**
         * Method to get {@link #low} instance <br>
         * No-any params required
         *
         * @return {@link #low} instance as double
         **/
        public double getLow() {
            return low;
        }

        /**
         * Method to get {@link #low} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #low} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getLow(int decimals) {
            return roundValue(low, decimals);
        }

        /**
         * Method to get {@link #close} instance <br>
         * No-any params required
         *
         * @return {@link #close} instance as double
         **/
        public double getClose() {
            return close;
        }

        /**
         * Method to get {@link #close} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #close} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getClose(int decimals) {
            return roundValue(close, decimals);
        }

        /**
         * Method to get {@link #vWap} instance <br>
         * No-any params required
         *
         * @return {@link #vWap} instance as double
         **/
        public double getvWap() {
            return vWap;
        }

        /**
         * Method to get {@link #vWap} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #vWap} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getvWap(int decimals) {
            return roundValue(vWap, decimals);
        }

        /**
         * Method to get {@link #volume} instance <br>
         * No-any params required
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
         * Method to get {@link #count} instance <br>
         * No-any params required
         *
         * @return {@link #count} instance as int
         **/
        public int getCount() {
            return count;
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
