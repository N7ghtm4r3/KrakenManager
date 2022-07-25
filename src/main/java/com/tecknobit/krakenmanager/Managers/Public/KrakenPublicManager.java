package com.tecknobit.krakenmanager.Managers.Public;

import com.tecknobit.krakenmanager.Managers.KrakenManager;

import java.io.IOException;

import static com.tecknobit.apimanager.Manager.APIRequest.GET_METHOD;

public class KrakenPublicManager extends KrakenManager {

    public KrakenPublicManager(String defaultErrorMessage, int requestTimeout) {
        super(defaultErrorMessage, requestTimeout);
    }

    public KrakenPublicManager(String defaultErrorMessage) {
        super(defaultErrorMessage);
    }

    public KrakenPublicManager(int requestTimeout) {
        super(requestTimeout);
    }

    public KrakenPublicManager() {
        super();
    }

    public String sendGetRequest(String endpoint) throws IOException {
        apiRequest.sendAPIRequest(BASE_ENDPOINT + "/public/" + endpoint, GET_METHOD);
        return apiRequest.getResponse();
    }

}
