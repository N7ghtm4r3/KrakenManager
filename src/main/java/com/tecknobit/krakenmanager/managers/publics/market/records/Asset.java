package com.tecknobit.krakenmanager.managers.publics.market.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

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

    /**
     * Method to get {@link #aClass} instance <br>
     * Any params required
     *
     * @return {@link #aClass} instance as {@link String}
     **/
    public String getaClass() {
        return aClass;
    }

    /**
     * Method to get {@link #altName} instance <br>
     * Any params required
     *
     * @return {@link #altName} instance as {@link String}
     **/
    public String getAltName() {
        return altName;
    }

    /**
     * Method to get {@link #decimals} instance <br>
     * Any params required
     *
     * @return {@link #decimals} instance as int
     **/
    public int getDecimals() {
        return decimals;
    }

    /**
     * Method to get {@link #displayDecimals} instance <br>
     * Any params required
     *
     * @return {@link #displayDecimals} instance as int
     **/
    public int getDisplayDecimals() {
        return displayDecimals;
    }

    /**
     * Returns a string representation of the object <br>
     * Any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
