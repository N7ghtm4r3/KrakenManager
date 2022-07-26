package com.tecknobit.krakenmanager.Managers.Public;

import com.tecknobit.krakenmanager.Managers.KrakenManager;

import java.io.IOException;

import static com.tecknobit.apimanager.Manager.APIRequest.GET_METHOD;

/**
 *  The {@code KrakenPublicManager} class is useful to manage all public KrakenManager's endpoints
 *  giving basics methods for others public Kraken's managers and basics endpoints for API requests
 *  @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#section/General-Usage">
 *      https://docs.kraken.com/rest/#section/General-Usage</a>
 *  @author N7ghtm4r3 - Tecknobit
 * **/

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
        return apiRequest.getResponse();
    }

}
