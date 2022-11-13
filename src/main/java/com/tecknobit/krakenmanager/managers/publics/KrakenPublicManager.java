package com.tecknobit.krakenmanager.managers.publics;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.GET_METHOD;
import static com.tecknobit.apimanager.formatters.JsonHelper.getJSONObject;

/**
 * The {@code KrakenPublicManager} class is useful to manage all public KrakenManager's endpoints
 * giving basics methods for others public Kraken's managers and basics endpoints for API requests
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#section/General-Usage">
 * https://docs.kraken.com/rest/#section/General-Usage</a>
 **/
public class KrakenPublicManager extends KrakenManager {

    /** Constructor to init a {@link KrakenPublicManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * **/
    public KrakenPublicManager(String defaultErrorMessage, int requestTimeout) {
        super(defaultErrorMessage, requestTimeout);
    }

    /** Constructor to init a {@link KrakenPublicManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * **/
    public KrakenPublicManager(String defaultErrorMessage) {
        super(defaultErrorMessage);
    }

    /** Constructor to init a {@link KrakenPublicManager}
     * @param requestTimeout: custom timeout for request
     * **/
    public KrakenPublicManager(int requestTimeout) {
        super(requestTimeout);
    }

    /** Constructor to init a {@link KrakenPublicManager} <br>
     * Any params required
     * **/
    public KrakenPublicManager() {
        super();
    }

    /** Method to send a GET request<br>
     * @param endpoint: endpoint of API request
     * @return response as {@link String}
     * **/
    public String sendGetRequest(String endpoint) throws IOException {
        apiRequest.sendAPIRequest(BASE_ENDPOINT + "/public/" + endpoint, GET_METHOD);
        JSONObject response = new JSONObject(apiRequest.getResponse());
        if(getJSONObject(response, "result") == null) {
            errorResponse = response.getJSONArray("error").toString();
            throw new IOException();
        }
        return response.toString();
    }

}
