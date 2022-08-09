package com.tecknobit.krakenmanager.Managers.Private.Websockets.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * The {@code StakeableAsset} class is useful to format a websockets token object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken">
 *     https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class WebsocketsToken extends KrakenManager.KrakenResponse {

    /**
     * {@code token} is instance that memorizes websockets token
     * **/
    private final String token;

    /**
     * {@code expires} is instance that memorizes time (in seconds) after which the token expires
     * **/
    private final int expires;

    /** Constructor to init a {@link WebsocketsToken} object
     * @param token: memorizes websockets token
     * @param expires: time (in seconds) after which the token expires
     **/
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
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
