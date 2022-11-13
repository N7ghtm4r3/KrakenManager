package com.tecknobit.krakenmanager.managers.privates.websockets.records;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

/**
 * The {@code StakeableAsset} class is useful to format a websockets token object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken">
 * https://docs.kraken.com/rest/#tag/Websockets-Authentication/operation/getWebsocketsToken</a>
 **/
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
        token = result.getString("token");
        expires = result.getInt("expires", 0);
    }

    /**
     * Method to get {@link #token} instance <br>
     * Any params required
     *
     * @return {@link #token} instance as {@link String}
     **/
    public String getToken() {
        return token;
    }

    /**
     * Method to get {@link #expires} instance <br>
     * Any params required
     *
     * @return {@link #expires} instance as int
     **/
    public int getExpires() {
        return expires;
    }

}
