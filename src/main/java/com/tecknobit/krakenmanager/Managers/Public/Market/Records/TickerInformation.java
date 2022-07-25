package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.tecknobit.krakenmanager.Managers.Public.Market.Records.TickerInformation.MarketAction.assembleMarketAction;
import static com.tecknobit.krakenmanager.Managers.Public.Market.Records.TickerInformation.MarketParam.assembleMarketParam;
import static com.tecknobit.krakenmanager.Managers.Public.Market.Records.TickerInformation.Trade.assembleTrade;

public class TickerInformation extends KrakenManager.KrakenResponse {

    private final String symbol;
    private final MarketAction ask;
    private final MarketAction bid;
    private final Trade close;
    private final MarketParam volume;
    private final MarketParam volumeWeightedAvgPrice;
    private final MarketParam trades;
    private final MarketParam low;
    private final MarketParam high;
    private final double openPrice;

    public TickerInformation(JSONObject jsonResponse, String symbol, MarketAction ask, MarketAction bid, Trade close,
                             MarketParam volume, MarketParam volumeWeightedAvgPrice, MarketParam trades, MarketParam low,
                             MarketParam high, double openPrice) {
        super(jsonResponse);
        this.symbol = symbol;
        this.ask = ask;
        this.bid = bid;
        this.close = close;
        this.volume = volume;
        this.volumeWeightedAvgPrice = volumeWeightedAvgPrice;
        this.trades = trades;
        this.low = low;
        this.high = high;
        this.openPrice = openPrice;
    }

    public TickerInformation(String symbol, MarketAction ask, MarketAction bid, Trade close, MarketParam volume,
                             MarketParam volumeWeightedAvgPrice, MarketParam trades, MarketParam low, MarketParam high,
                             double openPrice) {
        super(null);
        this.symbol = symbol;
        this.ask = ask;
        this.bid = bid;
        this.close = close;
        this.volume = volume;
        this.volumeWeightedAvgPrice = volumeWeightedAvgPrice;
        this.trades = trades;
        this.low = low;
        this.high = high;
        this.openPrice = openPrice;
    }

    public TickerInformation(JSONObject jsonTicker) {
        super(jsonTicker);
        JSONObject ticker = getResult();
        this.symbol = ticker.keys().next();
        ticker = ticker.getJSONObject(symbol);
        if(ticker != null){
            ask = assembleMarketAction(ticker.getJSONArray("a"));
            bid = assembleMarketAction(ticker.getJSONArray("b"));
            close = assembleTrade(ticker.getJSONArray("c"));
            volume = assembleMarketParam(ticker.getJSONArray("v"));
            volumeWeightedAvgPrice = assembleMarketParam(ticker.getJSONArray("p"));
            trades = assembleMarketParam(ticker.getJSONArray("t"));
            low = assembleMarketParam(ticker.getJSONArray("l"));
            high = assembleMarketParam(ticker.getJSONArray("h"));
            openPrice = ticker.getDouble("o");
        }else{
            ask = null;
            bid = null;
            close = null;
            volume = null;
            volumeWeightedAvgPrice = null;
            trades = null;
            low = null;
            high = null;
            openPrice = -1;
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public MarketAction getAsk() {
        return ask;
    }

    public MarketAction getBid() {
        return bid;
    }

    public Trade getClose() {
        return close;
    }

    public MarketParam getVolume() {
        return volume;
    }

    public MarketParam getVolumeWeightedAvgPrice() {
        return volumeWeightedAvgPrice;
    }

    public MarketParam getTrades() {
        return trades;
    }

    public MarketParam getLow() {
        return low;
    }

    public MarketParam getHigh() {
        return high;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    @Override
    public String toString() {
        return "TickerInformation{" +
                "symbol='" + symbol + '\'' +
                ", ask=" + ask +
                ", bid=" + bid +
                ", close=" + close +
                ", volume=" + volume +
                ", volumeWeightedAvgPrice=" + volumeWeightedAvgPrice +
                ", trades=" + trades +
                ", low=" + low +
                ", high=" + high +
                ", openPrice=" + openPrice +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    public static class Trade {

        protected final double price;
        protected final double lotVolume;

        public Trade(double price, double lotVolume) {
            this.price = price;
            this.lotVolume = lotVolume;
        }

        public double getPrice() {
            return price;
        }

        public double getLotVolume() {
            return lotVolume;
        }

        public static Trade assembleTrade(JSONArray jsonTrade){
            int valuesLength = jsonTrade.length();
            final double[] values = new double[valuesLength];
            for (int j = 0; j < valuesLength; j++)
                values[j] = jsonTrade.getDouble(j);
            return new Trade(values[0], values[1]);
        }

        @Override
        public String toString() {
            return "Trade{" +
                    "price=" + price +
                    ", lotVolume=" + lotVolume +
                    '}';
        }

    }

    public static class MarketAction extends Trade {

        private final double wholeLotVolume;

        public MarketAction(double price, double lotVolume, double wholeLotVolume) {
            super(price, lotVolume);
            this.wholeLotVolume = wholeLotVolume;
        }

        public double getWholeLotVolume() {
            return wholeLotVolume;
        }

        public static MarketAction assembleMarketAction(JSONArray jsonMarket){
            int valuesLength = jsonMarket.length();
            final double[] values = new double[valuesLength];
            for (int j = 0; j < valuesLength; j++)
                values[j] = jsonMarket.getDouble(j);
            return new MarketAction(values[0], values[1], values[2]);
        }

        @Override
        public String toString() {
            return "MarketAction{" +
                    "wholeLotVolume=" + wholeLotVolume +
                    ", price=" + price +
                    ", lotVolume=" + lotVolume +
                    '}';
        }

    }

    public static class MarketParam {

        private final double today;
        private final double last24Hours;

        public MarketParam(double today, double last24Hours) {
            this.today = today;
            this.last24Hours = last24Hours;
        }

        public double getToday() {
            return today;
        }

        public double getLast24Hours() {
            return last24Hours;
        }

        public static MarketParam assembleMarketParam(JSONArray jsonParam){
            int valuesLength = jsonParam.length();
            final double[] values = new double[valuesLength];
            for (int j = 0; j < valuesLength; j++)
                values[j] = jsonParam.getDouble(j);
            return new MarketParam(values[0], values[1]);
        }

        @Override
        public String toString() {
            return "MarketParam{" +
                    "today=" + today +
                    ", last24Hours=" + last24Hours +
                    '}';
        }

    }

}
