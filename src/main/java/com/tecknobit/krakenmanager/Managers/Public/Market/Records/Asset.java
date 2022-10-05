package com.tecknobit.krakenmanager.Managers.Public.Market.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * The {@code Asset} class is useful to format Asset data object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
 *     https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class Asset extends KrakenManager.KrakenResponse {

    /**
     * {@code CURRENCY_ASSET_CLASS} is constant for currency asset class type
     * **/
    public static final String CURRENCY_ASSET_CLASS = "currency";

    /**
     * {@code FOREX_ASSET_CLASS} is constant for forex asset class type
     * **/
    public static final String FOREX_ASSET_CLASS = "forex";

    /**
     * {@code aClass} is instance that memorizes asset class
     * **/
    private final String aClass;

    /**
     * {@code altName} is instance that memorizes alt name of the asset
     * **/
    private final String altName;

    /**
     * {@code decimals} is instance that memorizes number of decimals digits
     * **/
    private final int decimals;

    /**
     * {@code displayDecimals} is instance that memorizes number of decimals digits displayed
     * **/
    private final int displayDecimals;

    /** Constructor to init an {@link Asset} object
     * @param jsonResponse: base json response
     * @param aClass: asset class
     * @param altName: alt name of the asset
     * @param decimals: number of decimals digits
     * @param displayDecimals: number of decimals digits displayed
     * **/
    public Asset(JSONObject jsonResponse, String aClass, String altName, int decimals, int displayDecimals) {
        super(jsonResponse);
        this.aClass = aClass;
        this.altName = altName;
        this.decimals = decimals;
        this.displayDecimals = displayDecimals;
    }

    /** Constructor to init an {@link Asset} object
     * @param aClass: asset class
     * @param altName: alt name of the asset
     * @param decimals: number of decimals digits
     * @param displayDecimals: number of decimals digits displayed
     * **/
    public Asset(String aClass, String altName, int decimals, int displayDecimals) {
        this(null, aClass, altName, decimals, displayDecimals);
    }

    /** Constructor to init an {@link Asset} object
     * @param asset: asset data in JSON format
     * **/
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
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
