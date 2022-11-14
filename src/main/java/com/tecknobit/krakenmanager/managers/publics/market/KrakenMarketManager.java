package com.tecknobit.krakenmanager.managers.publics.market;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.krakenmanager.managers.publics.KrakenPublicManager;
import com.tecknobit.krakenmanager.managers.publics.market.records.*;
import com.tecknobit.krakenmanager.managers.publics.market.records.lists.OHLCData;
import com.tecknobit.krakenmanager.managers.publics.market.records.lists.Spreads;
import com.tecknobit.krakenmanager.managers.publics.market.records.lists.Trades;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static com.tecknobit.apimanager.trading.TradingTools.computeTPTOPIndex;
import static com.tecknobit.krakenmanager.constants.EndpointsList.*;
import static com.tecknobit.krakenmanager.managers.KrakenManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.krakenmanager.managers.KrakenManager.ReturnFormat.STRING;

/**
 * The {@code KrakenMarketManager} class is useful to manage all market endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data">
 * Market Data</a>
 **/
public class KrakenMarketManager extends KrakenPublicManager {

    /**
     * {@code symbols} is instance that memorizes symbols list set
     * **/
    private Set<String> symbols;

    /** Constructor to init a {@link KrakenMarketManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * **/
    public KrakenMarketManager(String defaultErrorMessage, int requestTimeout) {
        super(defaultErrorMessage, requestTimeout);
    }

    /** Constructor to init a {@link KrakenMarketManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * **/
    public KrakenMarketManager(String defaultErrorMessage) {
        super(defaultErrorMessage);
    }

    /** Constructor to init a {@link KrakenMarketManager}
     * @param requestTimeout: custom timeout for request
     * **/
    public KrakenMarketManager(int requestTimeout) {
        super(requestTimeout);
    }

    /**
     * Constructor to init a {@link KrakenMarketManager} <br>
     * Any params required
     **/
    public KrakenMarketManager() {
        super();
    }

    /**
     * Request to get server time<br>
     * Any params required
     *
     * @return server time as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime">
     * Get Server Time</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Time")
    public ServerTime getServerTime() throws IOException {
        return getServerTime(LIBRARY_OBJECT);
    }

    /**
     * Custom request to get server time value <br>
     * Any params required
     *
     * @return server time value as long
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime">
     * Get Server Time</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Time")
    public long getServerTimeValue() throws IOException {
        return getServerTime().getUnixTime();
    }

    /**
     * Request to get server time
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return server time as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getServerTime">
     * Get Server Time</a>
     **/
    @Returner
    @RequestPath(path = "https://api.kraken.com/0/public/Time")
    public <T> T getServerTime(ReturnFormat format) throws IOException {
        String serverTimeResponse = sendGetRequest(GET_SERVER_TIME_ENDPOINT);
        switch (format) {
            case JSON:
                return (T) new JSONObject(serverTimeResponse);
            case LIBRARY_OBJECT:
                return (T) new ServerTime(new JSONObject(serverTimeResponse));
            default:
                return (T) serverTimeResponse;
        }
    }

    /**
     * Request to get system status<br>
     * Any params required
     *
     * @return system status as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
     * Get System Status</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/SystemStatus")
    public SystemStatus getSystemStatus() throws IOException {
        return getSystemStatus(LIBRARY_OBJECT);
    }

    /**
     * Custom request to get system status value<br>
     * Any params required
     *
     * @return system status value as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
     * Get System Status</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/SystemStatus")
    public String getSystemStatusValue() throws IOException {
        return getSystemStatus().getStatus();
    }

    /**
     * Request to get system status
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return system status as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getSystemStatus">
     * Get System Status</a>
     **/
    @Returner
    @RequestPath(path = "https://api.kraken.com/0/public/SystemStatus")
    public <T> T getSystemStatus(ReturnFormat format) throws IOException {
        String serverTimeResponse = sendGetRequest(GET_SYSTEM_STATUS_ENDPOINT);
        switch (format) {
            case JSON:
                return (T) new JSONObject(serverTimeResponse);
            case LIBRARY_OBJECT:
                return (T) new SystemStatus(new JSONObject(serverTimeResponse));
            default:
                return (T) serverTimeResponse;
        }
    }

    /**
     * Request to get assets list<br>
     * Any params required
     *
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public ArrayList<Asset> getAssetsList() throws IOException {
        return getAssetsList(LIBRARY_OBJECT);
    }

    /**
     * Request to get assets list
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public <T> T getAssetsList(ReturnFormat format) throws IOException {
        return returnAssetsList(GET_ASSETS_ENDPOINT, format);
    }

    /**
     * Request to get assets list<br>
     *
     * @param assets: list of asset to fetch in {@link String} array format
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public ArrayList<Asset> getAssetsList(String[] assets) throws IOException {
        return getAssetsList(assets, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets list<br>
     *
     * @param assets: list of asset to fetch in {@link String} array format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public <T> T getAssetsList(String[] assets, ReturnFormat format) throws IOException {
        return returnAssetsList(GET_ASSETS_ENDPOINT + "?asset=" + apiRequest.assembleParamsList(",",
                assets), format);
    }

    /**
     * Request to get assets list<br>
     *
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public ArrayList<Asset> getAssetsList(ArrayList<String> assets) throws IOException {
        return getAssetsList(assets, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets list<br>
     *
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public <T> T getAssetsList(ArrayList<String> assets, ReturnFormat format) throws IOException {
        return returnAssetsList(GET_ASSETS_ENDPOINT + "?asset=" + apiRequest.assembleParamsList(",",
                assets), format);
    }

    /**
     * Request to get assets list<br>
     *
     * @param aClass: class of the assets to fetch
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public ArrayList<Asset> getAssetsList(String aClass) throws IOException {
        return getAssetsList(aClass, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets list<br>
     *
     * @param aClass: class of the assets to fetch
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public <T> T getAssetsList(String aClass, ReturnFormat format) throws IOException {
        return returnAssetsList(GET_ASSETS_ENDPOINT + "?aclass=" + aClass, format);
    }

    /**
     * Request to get assets list<br>
     *
     * @param assets: list of asset to fetch in {@link String} array format
     * @param aClass: class of the assets to fetch
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public ArrayList<Asset> getAssetsList(String[] assets, String aClass) throws IOException {
        return getAssetsList(assets, aClass, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets list<br>
     *
     * @param assets: list of asset to fetch in {@link String} array format
     * @param aClass: class of the assets to fetch
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public <T> T getAssetsList(String[] assets, String aClass, ReturnFormat format) throws IOException {
        return returnAssetsList(GET_ASSETS_ENDPOINT + "?asset=" + apiRequest.assembleParamsList(",",
                assets) + "&aclass=" + aClass, format);
    }

    /**
     * Request to get assets list<br>
     *
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @param aClass: class of the assets to fetch
     * @return assets list as {@link ArrayList} of {@link Asset} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public ArrayList<Asset> getAssetsList(ArrayList<String> assets, String aClass) throws IOException {
        return getAssetsList(assets, aClass, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets list<br>
     *
     * @param assets: list of asset to fetch in {@link ArrayList} of {@link String} format
     * @param aClass: class of the assets to fetch
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getAssetInfo">
     * Get Asset Info</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Assets")
    public <T> T getAssetsList(ArrayList<String> assets, String aClass, ReturnFormat format) throws IOException {
        return returnAssetsList(GET_ASSETS_ENDPOINT + "?asset=" + apiRequest.assembleParamsList(",",
                assets) + "&aclass=" + aClass, format);
    }

    /**
     * Method to assemble an assets list
     *
     * @param endpoint: endpoint to do the request
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return assets list as {"format"} defines
     **/
    @Returner
    private <T> T returnAssetsList(String endpoint, ReturnFormat format) throws IOException {
        String assetsListResponse = sendGetRequest(endpoint);
        switch (format) {
            case JSON:
                return (T) new JSONObject(assetsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<Asset> assets = new ArrayList<>();
                JSONObject jAssets = new JSONObject(assetsListResponse).getJSONObject("result");
                for (String asset : jAssets.keySet())
                    assets.add(new Asset(jAssets.getJSONObject(asset)));
                return (T) assets;
            default:
                return (T) assetsListResponse;
        }
    }

    /**
     * Custom request to get a single asset<br>
     *
     * @param symbol: asset to fetch details es. BTCEUR
     * @return single asset as {@link Asset} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    @WrappedRequest
    public Asset getSingleAsset(String symbol) throws IOException {
        return getSingleAsset(symbol, LIBRARY_OBJECT);
    }

    /**
     * Custom request to get a single asset<br>
     *
     * @param symbol: asset to fetch details es. BTCEUR
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return asset as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    @WrappedRequest
    public <T> T getSingleAsset(String symbol, ReturnFormat format) throws IOException {
        return returnAsset(getAssetsList(new String[]{symbol}, STRING), format);
    }

    /**
     * Custom request to get a single asset<br>
     *
     * @param symbol: asset to fetch details es. BTCEUR
     * @param aClass: class of the assets to fetch
     * @return single asset as {@link Asset} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    @WrappedRequest
    public Asset getSingleAsset(String symbol, String aClass) throws IOException {
        return getSingleAsset(symbol, aClass, LIBRARY_OBJECT);
    }

    /**
     * Custom request to get a single asset<br>
     *
     * @param symbol: asset to fetch details es. BTCEUR
     * @param aClass: class of the assets to fetch
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return asset as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    @WrappedRequest
    public <T> T getSingleAsset(String symbol, String aClass, ReturnFormat format) throws IOException {
        return returnAsset(getAssetsList(new String[]{symbol}, aClass, STRING), format);
    }

    /**
     * Method to assemble an asset object
     *
     * @param assetResponse: asset to format
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return asset as {"format"} defines
     **/
    @Returner
    private <T> T returnAsset(String assetResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(assetResponse);
            case LIBRARY_OBJECT:
                return (T) new Asset(new JSONObject(assetResponse));
            default:
                return (T) assetResponse;
        }
    }

    /**
     * Request to get assets pairs list<br>
     * Any params required
     *
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public ArrayList<AssetPair> getAssetPairsList() throws IOException {
        return getAssetPairsList(LIBRARY_OBJECT);
    }

    /**
     * Request to get assets pairs list
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets pairs list as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public <T> T getAssetPairsList(ReturnFormat format) throws IOException {
        return returnAssetPairsList(GET_ASSET_PAIRS_ENDPOINT, format);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public ArrayList<AssetPair> getAssetPairsList(String[] pairs) throws IOException {
        return getAssetPairsList(pairs, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param pairs:  pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets pairs list as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public <T> T getAssetPairsList(String[] pairs, ReturnFormat format) throws IOException {
        return returnAssetPairsList(GET_ASSET_PAIRS_ENDPOINT + "?pair=" + apiRequest.assembleParamsList(",",
                pairs), format);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public ArrayList<AssetPair> getAssetPairsList(ArrayList<String> pairs) throws IOException {
        return getAssetPairsList(pairs, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param pairs:  pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets pairs list as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public <T> T getAssetPairsList(ArrayList<String> pairs, ReturnFormat format) throws IOException {
        return returnAssetPairsList(GET_ASSET_PAIRS_ENDPOINT + "?pair=" + apiRequest.assembleParamsList(",",
                pairs), format);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public ArrayList<AssetPair> getAssetPairsList(String info) throws IOException {
        return getAssetPairsList(info, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param info:   detail to fetch (info, leverage, fees or margin)
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets pairs list as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public <T> T getAssetPairsList(String info, ReturnFormat format) throws IOException {
        return returnAssetPairsList(GET_ASSET_PAIRS_ENDPOINT + "?info=" + info, format);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @param info:  detail to fetch (info, leverage, fees or margin)
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public ArrayList<AssetPair> getAssetPairsList(String[] pairs, String info) throws IOException {
        return getAssetPairsList(pairs, info, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param pairs:  pairs to fetch details es. BTCEUR or ETHEUR in {@link String} array format
     * @param info:   detail to fetch (info, leverage, fees or margin)
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets pairs list as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public <T> T getAssetPairsList(String[] pairs, String info, ReturnFormat format) throws IOException {
        return returnAssetPairsList(GET_ASSET_PAIRS_ENDPOINT + "?pair=" + apiRequest.assembleParamsList(",",
                pairs) + "&info=" + info, format);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param pairs: pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @param info:  detail to fetch (info, leverage, fees or margin)
     * @return assets pairs list as {@link ArrayList} of {@link AssetPair} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public ArrayList<AssetPair> getAssetPairsList(ArrayList<String> pairs, String info) throws IOException {
        return getAssetPairsList(pairs, info, LIBRARY_OBJECT);
    }

    /**
     * Request to get assets pairs list<br>
     *
     * @param pairs:  pairs to fetch details es. BTCEUR or ETHEUR in {@link ArrayList} of {@link String} format
     * @param info:   detail to fetch (info, leverage, fees or margin)
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assets pairs list as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTradableAssetPairs">
     * Get Tradable Asset Pairs</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/AssetPairs")
    public <T> T getAssetPairsList(ArrayList<String> pairs, String info, ReturnFormat format) throws IOException {
        return returnAssetPairsList(GET_ASSET_PAIRS_ENDPOINT + "?pair=" + apiRequest.assembleParamsList(",",
                pairs) + "&info=" + info, format);
    }

    /**
     * Method to assemble an assets pairs list
     *
     * @param endpoint: endpoint to do the request
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return assets pairs list as {"format"} defines
     **/
    @Returner
    private <T> T returnAssetPairsList(String endpoint, ReturnFormat format) throws IOException {
        String assetsPairsListResponse = sendGetRequest(endpoint);
        switch (format) {
            case JSON:
                return (T) new JSONObject(assetsPairsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<AssetPair> assetsPairs = new ArrayList<>();
                JSONObject jAssetsPairs = new JSONObject(assetsPairsListResponse).getJSONObject("result");
                for (String assetPairs : jAssetsPairs.keySet())
                    assetsPairs.add(new AssetPair(jAssetsPairs.getJSONObject(assetPairs)));
                return (T) assetsPairs;
            default:
                return (T) assetsPairsListResponse;
        }
    }

    /**
     * Custom request to get a single assets pair<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return single assets pair as {@link AssetPair} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    @WrappedRequest
    public AssetPair getAssetPair(String pair) throws IOException {
        return getAssetPair(pair, LIBRARY_OBJECT);
    }

    /**
     * Custom request to get a single assets pair<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return asset pair as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    @WrappedRequest
    public <T> T getAssetPair(String pair, ReturnFormat format) throws IOException {
        return returnAssetPair(getAssetPairsList(new String[]{pair}, STRING), format);
    }

    /**
     * Custom request to get a single assets pair<br>
     *
     * @param pair: pairs to fetch details es. BTCEUR
     * @param info: detail to fetch (info, leverage, fees or margin)
     * @return single assets pair as {@link AssetPair} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    @WrappedRequest
    public AssetPair getAssetPair(String pair, String info) throws IOException {
        return getAssetPair(pair, info, LIBRARY_OBJECT);
    }

    /**
     * Custom request to get a single assets pair<br>
     *
     * @param pair:   pairs to fetch details es. BTCEUR
     * @param info:   detail to fetch (info, leverage, fees or margin)
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return asset pair as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    @WrappedRequest
    public <T> T getAssetPair(String pair, String info, ReturnFormat format) throws IOException {
        return returnAssetPair(getAssetPairsList(new String[]{pair}, info, STRING), format);
    }

    /**
     * Method to assemble an asset pair object
     *
     * @param assetPairResponse: asset pair to format
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return asset pair as {"format"} defines
     **/
    @Returner
    private <T> T returnAssetPair(String assetPairResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(assetPairResponse);
            case LIBRARY_OBJECT:
                return (T) new AssetPair(new JSONObject(assetPairResponse));
            default:
                return (T) assetPairResponse;
        }
    }

    /**
     * Request to get ticker information<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return ticker information as {@link TickerInformation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation">
     * Get Ticker Information</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Ticker?pair={pair}")
    public TickerInformation getTickerInformation(String pair) throws IOException {
        return getTickerInformation(pair, LIBRARY_OBJECT);
    }

    /**
     * Request to get ticker information<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return ticker information as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getTickerInformation">
     * Get Ticker Information</a>
     **/
    @Returner
    @RequestPath(path = "https://api.kraken.com/0/public/Ticker?pair={pair}")
    public <T> T getTickerInformation(String pair, ReturnFormat format) throws IOException {
        String tickerResponse = sendGetRequest(GET_TICKER_ENDPOINT + "?pair=" + pair);
        switch (format) {
            case JSON:
                return (T) new JSONObject(tickerResponse);
            case LIBRARY_OBJECT:
                return (T) new TickerInformation(new JSONObject(tickerResponse));
            default:
                return (T) tickerResponse;
        }
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return OHLC data information as {@link OHLCData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}")
    public OHLCData getOHLCDataObject(AssetPair pair) throws IOException {
        return getOHLCData(pair, LIBRARY_OBJECT);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return OHLC data information as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}")
    public <T> T getOHLCData(AssetPair pair, ReturnFormat format) throws IOException {
        return returnOHLCData(sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair.getAltName()), format);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return OHLC data information as {@link OHLCData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}")
    public OHLCData getOHLCDataObject(String pair) throws IOException {
        return getOHLCData(pair, LIBRARY_OBJECT);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return OHLC data information as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}")
    public <T> T getOHLCData(String pair, ReturnFormat format) throws IOException {
        return returnOHLCData(sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair), format);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:     pair from fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @return OHLC data information as {@link OHLCData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&interval={interval}")
    public OHLCData getOHLCData(AssetPair pair, int interval) throws IOException {
        return getOHLCData(pair, interval, LIBRARY_OBJECT);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:     pair from fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return OHLC data information as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&interval={interval}")
    public OHLCData getOHLCData(AssetPair pair, int interval, ReturnFormat format) throws IOException {
        return returnOHLCData(sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair.getAltName() + "&interval=" +
                interval), format);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:     pair from fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @return OHLC data information as {@link OHLCData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&interval={interval}")
    public OHLCData getOHLCData(String pair, int interval) throws IOException {
        return getOHLCData(pair, interval, LIBRARY_OBJECT);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:     pair from fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return OHLC data information as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&interval={interval}")
    public OHLCData getOHLCData(String pair, int interval, ReturnFormat format) throws IOException {
        return returnOHLCData(sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair + "&interval=" + interval),
                format);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @return OHLC data information as {@link OHLCData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&since={since}")
    public OHLCData getOHLCData(AssetPair pair, long since) throws IOException {
        return getOHLCData(pair, since, LIBRARY_OBJECT);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param since:  since timestamp from fetch data
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return OHLC data information as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&since={since}")
    public <T> T getOHLCData(AssetPair pair, long since, ReturnFormat format) throws IOException {
        return returnOHLCData(sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair.getAltName() + "&since=" + since),
                format);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @return OHLC data information as {@link OHLCData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&since={since}")
    public OHLCData getOHLCData(String pair, long since) throws IOException {
        return getOHLCData(pair, since, LIBRARY_OBJECT);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param since:  since timestamp from fetch data
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return OHLC data information as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&since={since}")
    public <T> T getOHLCData(String pair, long since, ReturnFormat format) throws IOException {
        return returnOHLCData(sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair + "&since=" + since), format);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:     pair from fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @param since:    since timestamp from fetch data
     * @return OHLC data information as {@link OHLCData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&interval={interval}&since={since}")
    public OHLCData getOHLCData(AssetPair pair, int interval, long since) throws IOException {
        return getOHLCData(pair, interval, since, LIBRARY_OBJECT);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:     pair from fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @param since:    since timestamp from fetch data
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return OHLC data as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&interval={interval}&since={since}")
    public <T> T getOHLCData(AssetPair pair, int interval, long since, ReturnFormat format) throws IOException {
        return returnOHLCData(sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair.getAltName() + "&interval=" +
                interval + "&since=" + since), format);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:     pair from fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @param since:    since timestamp from fetch data
     * @return OHLC data information as {@link OHLCData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&interval={interval}&since={since}")
    public OHLCData getOHLCData(String pair, int interval, long since) throws IOException {
        return getOHLCData(pair, interval, since, LIBRARY_OBJECT);
    }

    /**
     * Request to get OHLC data information<br>
     *
     * @param pair:     pair from fetch details es. BTCEUR
     * @param interval: time frame interval in minutes
     * @param since:    since timestamp from fetch data
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return OHLC data as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOHLCData">
     * Get OHLC Data</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/OHLC?pair={pair}&interval={interval}&since={since}")
    public <T> T getOHLCData(String pair, int interval, long since, ReturnFormat format) throws IOException {
        return returnOHLCData(sendGetRequest(GET_OHLC_ENDPOINT + "?pair=" + pair + "&interval=" + interval +
                "&since=" + since), format);
    }

    /**
     * Method to assemble an OHLC data object
     *
     * @param OHLCDataResponse: OHLC data to format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return OHLC data as {"format"} defines
     **/
    @Returner
    private <T> T returnOHLCData(String OHLCDataResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(OHLCDataResponse);
            case LIBRARY_OBJECT:
                return (T) new OHLCData(new JSONObject(OHLCDataResponse));
            default:
                return (T) OHLCDataResponse;
        }
    }

    /**
     * Request to get order book details<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return order book details as {@link Book} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     * Get Order Book</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Depth?pair={pair}")
    public Book getOrderBook(AssetPair pair) throws IOException {
        return getOrderBook(pair, LIBRARY_OBJECT);
    }

    /**
     * Request to get order book details<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return book as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     * Get Order Book</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Depth?pair={pair}")
    public <T> T getOrderBook(AssetPair pair, ReturnFormat format) throws IOException {
        return returnBook(sendGetRequest(GET_ORDER_BOOK_ENDPOINT + "?pair=" + pair.getAltName()), format);
    }

    /**
     * Request to get order book details<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return order book details as {@link Book} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     * Get Order Book</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Depth?pair={pair}")
    public Book getOrderBook(String pair) throws IOException {
        return getOrderBook(pair, LIBRARY_OBJECT);
    }

    /**
     * Request to get order book details<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return book as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     * Get Order Book</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Depth?pair={pair}")
    public <T> T getOrderBook(String pair, ReturnFormat format) throws IOException {
        return returnBook(sendGetRequest(GET_ORDER_BOOK_ENDPOINT + "?pair=" + pair), format);
    }

    /**
     * Request to get order book details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param count: maximum number of asks and bids
     * @return order book details as {@link Book} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     * Get Order Book</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Depth?pair={pair}&count={count}")
    public Book getOrderBook(AssetPair pair, int count) throws IOException {
        return getOrderBook(pair, count, LIBRARY_OBJECT);
    }

    /**
     * Request to get order book details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param count: maximum number of asks and bids
     * @return book as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     * Get Order Book</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Depth?pair={pair}&count={count}")
    public <T> T getOrderBook(AssetPair pair, int count, ReturnFormat format) throws IOException {
        return returnBook(sendGetRequest(GET_ORDER_BOOK_ENDPOINT + "?pair=" + pair.getAltName() + "&count=" +
                count), format);
    }

    /**
     * Request to get order book details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param count: maximum number of asks and bids
     * @return order book details as {@link Book} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     * Get Order Book</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Depth?pair={pair}&count={count}")
    public Book getOrderBook(String pair, int count) throws IOException {
        return getOrderBook(pair, count, LIBRARY_OBJECT);
    }

    /**
     * Request to get order book details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param count: maximum number of asks and bids
     * @return book as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getOrderBook">
     * Get Order Book</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Depth?pair={pair}&count={count}")
    public <T> T getOrderBook(String pair, int count, ReturnFormat format) throws IOException {
        return returnBook(sendGetRequest(GET_ORDER_BOOK_ENDPOINT + "?pair=" + pair + "&count=" + count), format);
    }

    /**
     * Method to assemble a book object
     *
     * @param bookResponse: book to format
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return book as {"format"} defines
     **/
    @Returner
    private <T> T returnBook(String bookResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(bookResponse);
            case LIBRARY_OBJECT:
                return (T) new Book(new JSONObject(bookResponse));
            default:
                return (T) bookResponse;
        }
    }

    /**
     * Request to get recent trades details<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return recent trades details as {@link Trades} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     * Get Recent Trades</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Trades?pair={pair}")
    public Trades getRecentTrades(AssetPair pair) throws IOException {
        return getRecentTrades(pair, LIBRARY_OBJECT);
    }

    /**
     * Request to get recent trades details<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return trades as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     * Get Recent Trades</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Trades?pair={pair}")
    public <T> T getRecentTrades(AssetPair pair, ReturnFormat format) throws IOException {
        return returnRecentTrades(sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair.getAltName()),
                format);
    }

    /**
     * Request to get recent trades details<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return recent trades details as {@link Trades} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     * Get Recent Trades</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Trades?pair={pair}")
    public Trades getRecentTrades(String pair) throws IOException {
        return getRecentTrades(pair, LIBRARY_OBJECT);
    }

    /**
     * Request to get recent trades details<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return trades as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     * Get Recent Trades</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Trades?pair={pair}")
    public <T> T getRecentTrades(String pair, ReturnFormat format) throws IOException {
        return returnRecentTrades(sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair), format);
    }

    /**
     * Request to get recent trades details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @return recent trades details as {@link Trades} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     * Get Recent Trades</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Trades?pair={pair}&since={since}")
    public Trades getRecentTrades(AssetPair pair, long since) throws IOException {
        return getRecentTrades(pair, since, LIBRARY_OBJECT);
    }

    /**
     * Request to get recent trades details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @return trades as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     * Get Recent Trades</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Trades?pair={pair}&since={since}")
    public <T> T getRecentTrades(AssetPair pair, long since, ReturnFormat format) throws IOException {
        return returnRecentTrades(sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair.getAltName() +
                "&since=" + since), format);
    }

    /**
     * Request to get recent trades details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @return recent trades details as {@link Trades} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     * Get Recent Trades</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Trades?pair={pair}&since={since}")
    public Trades getRecentTrades(String pair, long since) throws IOException {
        return getRecentTrades(pair, since, LIBRARY_OBJECT);
    }

    /**
     * Request to get recent trades details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @return trades as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentTrades">
     * Get Recent Trades</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Trades?pair={pair}&since={since}")
    public <T> T getRecentTrades(String pair, long since, ReturnFormat format) throws IOException {
        return returnRecentTrades(sendGetRequest(GET_RECENT_TRADES_ENDPOINT + "?pair=" + pair + "&since=" +
                since), format);
    }

    /**
     * Method to assemble a trades object
     *
     * @param recentTradesResponse: trades to format
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return trades as {"format"} defines
     **/
    @Returner
    private <T> T returnRecentTrades(String recentTradesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(recentTradesResponse);
            case LIBRARY_OBJECT:
                return (T) new Trades(new JSONObject(recentTradesResponse));
            default:
                return (T) recentTradesResponse;
        }
    }

    /**
     * Request to get recent spreads details<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return recent spreads details as {@link Spreads} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     * Get Recent Spreads</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Spread?pair={pair}")
    public Spreads getRecentSpreadsObject(AssetPair pair) throws IOException {
        return getRecentSpreadsObject(pair, LIBRARY_OBJECT);
    }

    /**
     * Request to get recent spreads details<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return spreads as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     * Get Recent Spreads</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Spread?pair={pair}")
    public <T> T getRecentSpreadsObject(AssetPair pair, ReturnFormat format) throws IOException {
        return returnRecentSpreads(sendGetRequest(GET_RECENT_SPREADS_ENDPOINT + "?pair=" + pair.getAltName()),
                format);
    }

    /**
     * Request to get recent spreads details<br>
     *
     * @param pair: pair from fetch details es. BTCEUR
     * @return recent spreads details as {@link Spreads} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     * Get Recent Spreads</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Spread?pair={pair}")
    public Spreads getRecentSpreadsObject(String pair) throws IOException {
        return getRecentSpreadsObject(pair, LIBRARY_OBJECT);
    }

    /**
     * Request to get recent spreads details<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return spreads as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     * Get Recent Spreads</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Spread?pair={pair}")
    public <T> T getRecentSpreadsObject(String pair, ReturnFormat format) throws IOException {
        return returnRecentSpreads(sendGetRequest(GET_RECENT_SPREADS_ENDPOINT + "?pair=" + pair), format);
    }

    /**
     * Request to get recent spreads details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @return recent spreads details as {@link Spreads} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     * Get Recent Spreads</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Spread?pair={pair}&since={since}")
    public Spreads getRecentSpreadsObject(AssetPair pair, long since) throws IOException {
        return getRecentSpreadsObject(pair, since, LIBRARY_OBJECT);
    }

    /**
     * Request to get recent spreads details<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param since:  since timestamp from fetch data
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return spreads as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     * Get Recent Spreads</a>
     **/
    @WrappedRequest
    @RequestPath(path = "https://api.kraken.com/0/public/Spread?pair={pair}&since={since}")
    public <T> T getRecentSpreadsObject(AssetPair pair, long since, ReturnFormat format) throws IOException {
        return returnRecentSpreads(sendGetRequest(GET_RECENT_SPREADS_ENDPOINT + "?pair=" + pair.getAltName()
                + "&since=" + since), format);
    }

    /**
     * Request to get recent spreads details<br>
     *
     * @param pair:  pair from fetch details es. BTCEUR
     * @param since: since timestamp from fetch data
     * @return recent spreads details as {@link Spreads} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     * Get Recent Spreads</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Spread?pair={pair}&since={since}")
    public Spreads getRecentSpreadsObject(String pair, long since) throws IOException {
        return getRecentSpreadsObject(pair, since, LIBRARY_OBJECT);
    }

    /**
     * Request to get recent spreads details<br>
     *
     * @param pair:   pair from fetch details es. BTCEUR
     * @param since:  since timestamp from fetch data
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return spreads as {"format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Market-Data/operation/getRecentSpreads">
     * Get Recent Spreads</a>
     **/
    @RequestPath(path = "https://api.kraken.com/0/public/Spread?pair={pair}&since={since}")
    public <T> T getRecentSpreadsObject(String pair, long since, ReturnFormat format) throws IOException {
        return returnRecentSpreads(sendGetRequest(GET_RECENT_SPREADS_ENDPOINT + "?pair=" + pair + "&since="
                + since), format);
    }

    /**
     * Method to assemble a spreads object
     *
     * @param recentSpreadsResponse: spreads to format
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return spreads as {"format"} defines
     **/
    @Returner
    private <T> T returnRecentSpreads(String recentSpreadsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(recentSpreadsResponse);
            case LIBRARY_OBJECT:
                return (T) new Spreads(new JSONObject(recentSpreadsResponse));
            default:
                return (T) recentSpreadsResponse;
        }
    }

    /** Method to get prevision of a cryptocurrency in base of day's gap inserted
     * @param symbol: symbol to calculate forecast es. BTCEUR
     * @param OHCLInterval: temporal interval of data for the forecast
     * @param intervalDays: days gap for the prevision range
     * @param toleranceValue: tolerance for select similar value compared to lastValue inserted
     * @return prevision value as a double es. 8 or -8
     * @throws IllegalArgumentException if lastValue is negative or intervalDays are less or equal to 0
     * **/
    public double getSymbolForecast(String symbol, int OHCLInterval, int intervalDays, int toleranceValue) throws IOException {
        ArrayList<Double> historicalValues = new ArrayList<>();
        for (OHLCData.TickData tickData : getOHLCData(symbol, OHCLInterval).getTicksData())
            historicalValues.add(tickData.getHigh());
        return computeTPTOPIndex(historicalValues, getTickerInformation(symbol).getClose().getPrice(), intervalDays,
                toleranceValue);
    }

    /** Method to get prevision of a cryptocurrency in base of day's gap inserted
     * @param symbol: symbol to calculate forecast es. BTCEUR
     * @param OHCLInterval: temporal interval of data for the forecast
     * @param intervalDays: days gap for the prevision range
     * @param toleranceValue: tolerance for select similar value compared to lastValue inserted
     * @param decimalDigits: number of digits to round final forecast value
     * @return forecast value as a double es. 8 or -8
     * @throws IllegalArgumentException if lastValue is negative or intervalDays are less or equal to 0
     * **/
    public double getSymbolForecast(String symbol, int OHCLInterval, int intervalDays, int toleranceValue,
                                    int decimalDigits) throws IOException {
        return roundValue(getSymbolForecast(symbol, OHCLInterval, intervalDays, toleranceValue), decimalDigits);
    }

}

