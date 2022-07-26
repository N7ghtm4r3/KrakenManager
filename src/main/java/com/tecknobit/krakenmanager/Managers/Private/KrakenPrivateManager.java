package com.tecknobit.krakenmanager.Managers.Private;

import com.tecknobit.krakenmanager.Managers.KrakenManager;

import java.io.IOException;
import java.util.HashMap;

/**
 *  The {@code KrakenPrivateManager} class is useful to manage all private KrakenManager's endpoints
 *  giving basics methods for others public Kraken's managers and basics endpoints for API requests
 *  @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#section/General-Usage">
 *      https://docs.kraken.com/rest/#section/General-Usage</a>
 *  @author N7ghtm4r3 - Tecknobit
 * **/

public class KrakenPrivateManager extends KrakenManager {

    /**
     * {@code apiKey} is the instance that contains api key of Kraken's platform
     * **/
    protected final String apiKey;

    /**
     * {@code apiSign} is the instance that contains api sign of Kraken's platform
     * **/
    protected final String apiSign;

    /**
     * Constructor to init a {@link KrakenPrivateManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenPrivateManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout);
        this.apiKey = apiKey;
        this.apiSign = apiSign;
    }

    /**
     * Constructor to init a {@link KrakenPrivateManager}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenPrivateManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage);
        this.apiKey = apiKey;
        this.apiSign = apiSign;
    }

    /**
     * Constructor to init a {@link KrakenPrivateManager}
     * @param requestTimeout : custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenPrivateManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout);
        this.apiKey = apiKey;
        this.apiSign = apiSign;
    }

    /** Constructor to init a {@link KrakenPrivateManager} <br>
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     * **/
    public KrakenPrivateManager(String apiKey, String apiSign) {
        super();
        this.apiKey = apiKey;
        this.apiSign = apiSign;
    }

    /** Method to send a POST request<br>
     * @param endpoint: endpoint of API request
     * @return response as {@link String}
     * **/
    public String sendPostRequest(String endpoint, HashMap<String, Object> bodyParams) throws IOException {
        apiRequest.sendPostAPIRequest(BASE_ENDPOINT + "/private/" + endpoint, bodyParams);
        return apiRequest.getResponse();
    }

    /** Method to get Kraken api key <br>
     * Any params required
     * @return api key as {@link String}
     * **/
    public String getApiKey() {
        return apiKey;
    }

    /** Method to get Kraken api sign <br>
     * Any params required
     * @return api sign as {@link String}
     * **/
    public String getApiSign() {
        return apiSign;
    }

}
