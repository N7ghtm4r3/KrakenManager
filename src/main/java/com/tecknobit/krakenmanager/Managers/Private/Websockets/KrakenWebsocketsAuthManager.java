package com.tecknobit.krakenmanager.Managers.Private.Websockets;

import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.Websockets.Records.WebsocketsToken;
import org.json.JSONObject;

import static com.tecknobit.krakenmanager.Constants.EndpointsList.GET_WEBSOCKETS_TOKEN_ENDPOINT;

public class KrakenWebsocketsAuthManager extends KrakenPrivateManager {

    public KrakenWebsocketsAuthManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    public KrakenWebsocketsAuthManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    public KrakenWebsocketsAuthManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    public KrakenWebsocketsAuthManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    public String getWebsocketsToken() throws Exception {
        return sendPostRequest(GET_WEBSOCKETS_TOKEN_ENDPOINT, null);
    }

    public JSONObject getWebsocketsTokenJSON() throws Exception {
        return new JSONObject(getWebsocketsToken());
    }

    public WebsocketsToken getWebsocketsTokenObject() throws Exception {
        return new WebsocketsToken(getWebsocketsTokenJSON());
    }

}
