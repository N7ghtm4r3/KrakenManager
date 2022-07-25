package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class Asset extends KrakenManager.KrakenResponse {

    public static final String CURRENCY_ASSET_CLASS = "currency";

    private final String aClass;
    private final String altName;
    private final int decimals;
    private final int displayDecimals;

    public Asset(JSONObject jsonResponse, String aClass, String altName, int decimals, int displayDecimals) {
        super(jsonResponse);
        this.aClass = aClass;
        this.altName = altName;
        this.decimals = decimals;
        this.displayDecimals = displayDecimals;
    }

    public Asset(String aClass, String altName, int decimals, int displayDecimals) {
        super(null);
        this.aClass = aClass;
        this.altName = altName;
        this.decimals = decimals;
        this.displayDecimals = displayDecimals;
    }

    public Asset(JSONObject asset) {
        super(null);
        aClass = asset.getString("aclass");
        altName = asset.getString("altname");
        decimals = asset.getInt("decimals");
        displayDecimals = asset.getInt("display_decimals");
    }

    public String getaClass() {
        return aClass;
    }

    public String getAltName() {
        return altName;
    }

    public int getDecimals() {
        return decimals;
    }

    public int getDisplayDecimals() {
        return displayDecimals;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "aClass='" + aClass + '\'' +
                ", altName='" + altName + '\'' +
                ", decimals=" + decimals +
                ", displayDecimals=" + displayDecimals +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
