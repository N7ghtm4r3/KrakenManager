package com.tecknobit.krakenmanager.Managers.Private.Websockets.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class WebsocketsToken extends KrakenManager.KrakenResponse {

    private final String token;
    private final int expires;

    public WebsocketsToken(JSONObject jsonResponse, String token, int expires) {
        super(jsonResponse);
        this.token = token;
        this.expires = expires;
    }

    public WebsocketsToken(String token, int expires) {
        super(null);
        this.token = token;
        this.expires = expires;
    }

    /**
     * Constructor to init a {@link WebsocketsToken} object
     * @param jsonResponse : base json response
     **/
    public WebsocketsToken(JSONObject jsonResponse) {
        super(jsonResponse);
        jsonResponse = getResult();
        token = jsonResponse.getString("token");
        expires = jsonResponse.getInt("expires");
    }

    public String getToken() {
        return token;
    }

    public int getExpires() {
        return expires;
    }

    @Override
    public String toString() {
        return "WebsocketsToken{" +
                "token='" + token + '\'' +
                ", expires=" + expires +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
