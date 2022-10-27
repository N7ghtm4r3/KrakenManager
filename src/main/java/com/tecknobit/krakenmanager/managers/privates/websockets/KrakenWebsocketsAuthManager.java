package com.tecknobit.krakenmanager.managers.privates.websockets;

import com.tecknobit.krakenmanager.managers.privates.KrakenPrivateManager;
import com.tecknobit.krakenmanager.managers.privates.websockets.records.WebsocketsToken;
import org.json.JSONObject;

import static com.tecknobit.krakenmanager.constants.EndpointsList.GET_WEBSOCKETS_TOKEN_ENDPOINT;

/**
 *  The {@code KrakenWebsocketsAuthManager} class is useful to manage all websockets endpoints
 *  @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Websockets-Authentication">
 *      https://docs.kraken.com/rest/#tag/Websockets-Authentication</a>
 *  @author N7ghtm4r3 - Tecknobit
 * **/
public class KrakenWebsocketsAuthManager extends KrakenPrivateManager {

    /** Constructor to init a {@link KrakenWebsocketsAuthManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenWebsocketsAuthManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenWebsocketsAuthManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenWebsocketsAuthManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenWebsocketsAuthManager}
     *
     * @param requestTimeout: custom timeout for request
     * @param apiKey:         api key of Kraken's platform
     * @param apiSign:        api sign of Kraken's platform
     **/
    public KrakenWebsocketsAuthManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenWebsocketsAuthManager}
     *
     * @param apiKey:  api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenWebsocketsAuthManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenWebsocketsAuthManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructur
     * @apiNote this constructor is useful to instantiate a new {@link KrakenPrivateManager}'s manager without re-insert
     * the credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        KrakenPrivateManager firstManager = new KrakenPrivateManager("apiKey", "apiSign");
     *        //You don't need to insert all credentials to make manager work
     *        KrakenPrivateManager secondManager = new KrakenPrivateManager(); //same credentials used
     *     }
     * </pre>
     **/
    public KrakenWebsocketsAuthManager() {
        super();
    }

    /** Request to get websockets auth token <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken">
     *     https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken</a>
     * @implNote an authentication token must be requested via this REST API endpoint in order to connect to and authenticate
     * with Kraken's Websockets API. The token should be used within 15 minutes of creation, but it does not expire once a successful
     * Websockets connection and private subscription has been made and is maintained
     * @implSpec the 'Access WebSockets API' permission must be enabled for the API key in order to generate the authentication token.
     * @return websockets auth token as {@link String}
     * **/
    public String getWebsocketsToken() throws Exception {
        return sendPostRequest(GET_WEBSOCKETS_TOKEN_ENDPOINT, null);
    }

    /** Request to get websockets auth token <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken">
     *     https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken</a>
     * @implNote an authentication token must be requested via this REST API endpoint in order to connect to and authenticate
     * with Kraken's Websockets API. The token should be used within 15 minutes of creation, but it does not expire once a successful
     * Websockets connection and private subscription has been made and is maintained
     * @implSpec the 'Access WebSockets API' permission must be enabled for the API key in order to generate the authentication token.
     * @return websockets auth token as {@link JSONObject}
     * **/
    public JSONObject getWebsocketsTokenJSON() throws Exception {
        return new JSONObject(getWebsocketsToken());
    }

    /** Request to get websockets auth token <br>
     * Any params required
     * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken">
     *     https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken</a>
     * @implNote an authentication token must be requested via this REST API endpoint in order to connect to and authenticate
     * with Kraken's Websockets API. The token should be used within 15 minutes of creation, but it does not expire once a successful
     * Websockets connection and private subscription has been made and is maintained
     * @implSpec the 'Access WebSockets API' permission must be enabled for the API key in order to generate the authentication token.
     * @return websockets auth token as {@link WebsocketsToken} custom object
     * **/
    public WebsocketsToken getWebsocketsTokenObject() throws Exception {
        return new WebsocketsToken(getWebsocketsTokenJSON());
    }

}
