package com.tecknobit.krakenmanager.Managers.Public.Market.Records.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class OHLCData extends MarketList {

    public static final int INTERVAL_1m = 1;
    public static final int INTERVAL_5m = 5;
    public static final int INTERVAL_15m = 15;
    public static final int INTERVAL_30m = 30;
    public static final int INTERVAL_60m = 60;
    public static final int INTERVAL_4h = 240;
    public static final int INTERVAL_1d = 1440;
    public static final int INTERVAL_1w = 10080;
    public static final int INTERVAL_1M = 21600;
    private final ArrayList<TickData> ticksData;

    public OHLCData(JSONObject jsonResponse, long last, String symbol, ArrayList<TickData> ticksData) {
        super(jsonResponse, last, symbol);
        this.ticksData = ticksData;
    }

    public OHLCData(long last, String symbol, ArrayList<TickData> ticksData) {
        super(null, last, symbol);
        this.ticksData = ticksData;
    }

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
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    public static class TickData {

        private final long time;
        private final double open;
        private final double high;
        private final double low;
        private final double close;
        private final double vWap;
        private final double volume;
        private final int count;

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
