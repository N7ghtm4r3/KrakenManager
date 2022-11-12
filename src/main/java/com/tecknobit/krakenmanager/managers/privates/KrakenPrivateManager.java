package com.tecknobit.krakenmanager.managers.privates;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;

import static com.tecknobit.apimanager.apis.APIRequest.*;
import static com.tecknobit.apimanager.formatters.JsonHelper.getJSONObject;

/**
 *  The {@code KrakenPrivateManager} class is useful to manage all private KrakenManager's endpoints
 *  giving basics methods for others public Kraken's managers and basics endpoints for API requests
 *  @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#section/General-Usage">
 *      https://docs.kraken.com/rest/#section/General-Usage</a>
 *  @author N7ghtm4r3 - Tecknobit
 * **/
public class KrakenPrivateManager extends KrakenManager {

    /**
     * {@code API_KEY_HEADER} is constant for API-Key header
     * **/
    public static final String API_KEY_HEADER = "API-Key";

    /**
     * {@code API_SIGN_HEADER} is constant for API-Sign header
     * **/
    public static final String API_SIGN_HEADER = "API-Sign";

    /**
     * {@code CONTENT_TYPE_HEADER} is constant for Content-Type header
     * **/
    public static final String CONTENT_TYPE_HEADER = "Content-Type";

    /**
     * {@code USER_AGENT_HEADER} is constant for User-Agent header
     * **/
    public static final String USER_AGENT_HEADER = "User-Agent";

    /**
     * {@code apiKey} is the instance that contains api key of Kraken's platform
     * **/
    protected final String apiKey;

    /**
     * {@code apiSign} is the instance that contains api sign of Kraken's platform
     * **/
    protected final String apiSign;

    /**
     * {@code headers} is the instance that contains headers for private api requests
     * **/
    private final Headers headers;

    /** Constructor to init a {@link KrakenPrivateManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenPrivateManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout);
        this.apiKey = apiKey;
        this.apiSign = apiSign;
        headers = new Headers();
        storeProperties(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenPrivateManager}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenPrivateManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage);
        this.apiKey = apiKey;
        this.apiSign = apiSign;
        headers = new Headers();
        storeProperties(defaultErrorMessage, -1, apiKey, apiSign);
    }

    /** Constructor to init a {@link KrakenPrivateManager}
     * @param requestTimeout : custom timeout for request
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenPrivateManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout);
        this.apiKey = apiKey;
        this.apiSign = apiSign;
        headers = new Headers();
        storeProperties(null, requestTimeout, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenPrivateManager} <br>
     *
     * @param apiKey:  api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     **/
    public KrakenPrivateManager(String apiKey, String apiSign) {
        super();
        this.apiKey = apiKey;
        this.apiSign = apiSign;
        headers = new Headers();
        storeProperties(null, -1, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenPrivateManager} <br>
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
    public KrakenPrivateManager() {
        super();
        apiKey = properties.getProperty("apiKey");
        apiSign = properties.getProperty("apiSign");
        if (apiKey == null || apiSign == null)
            throw new IllegalArgumentException("You need to call a parameterized constructor first");
        headers = new Headers();
        setHeaders();
    }

    /**
     * Method to store some properties
     *
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout:      custom timeout for request
     * @param apiKey:              api key of Kraken's platform
     * @param apiSign:             api sign of Kraken's platform
     **/
    protected void storeProperties(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        storeProperties(defaultErrorMessage, requestTimeout);
        properties.setProperty("apiKey", apiKey);
        properties.setProperty("apiSign", apiSign);
        setHeaders();
    }

    /**
     * Method to set base headers for request<br>
     * Any params required
     **/
    private void setHeaders() {
        headers.addHeader(API_KEY_HEADER, apiKey);
        headers.addHeader(CONTENT_TYPE_HEADER, "application/x-www-form-urlencoded; charset=utf-8'");
        headers.addHeader(USER_AGENT_HEADER, "Mozilla/5.0 Firefox/26.0");
    }

    /** Method to send a POST request<br>
     * @param endpoint: endpoint of API request
     * @param bodyParams: body params of the HTTP api request
     * @return response as {@link String}
     * **/
    public String sendPostRequest(String endpoint, Params bodyParams) throws Exception {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("nonce", System.currentTimeMillis());
        headers.addHeader(API_SIGN_HEADER, getSignature(endpoint, bodyParams));
        apiRequest.sendPayloadedAPIRequest(BASE_ENDPOINT + "/private/" + endpoint, POST_METHOD, headers, bodyParams);
        JSONObject response = new JSONObject(apiRequest.getResponse());
        if (getJSONObject(response, "result") == null) {
            errorResponse = response.getJSONArray("error").toString();
            throw new IOException();
        }
        return response.toString();
    }

    /** Method to get signature for request<br>
     * @param path: endpoint of the request es. Balance
     * @param data: payload of the request
     * @return signature value as {@link String} es. 4/dpxb3iT4tp/ZCVEwSnEsLxx0bqyhLpdfOpc6fn7OR8+UClSV5n9E6aSS8MPtnRfp32bAb0nmbRn6H8ndwLUQ==
     * **/
    private String getSignature(String path, Params data) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA512_ALGORITHM);
            mac.init(new SecretKeySpec(Base64.getDecoder().decode(apiSign.getBytes()), HMAC_SHA512_ALGORITHM));
            mac.update(("/0/private/" + path).getBytes());
            return new String(Base64.getEncoder().encode(mac.doFinal(digest(data.getParam("nonce")
                            + apiRequest.encodeBodyParams(data), SHA256_ALGORITHM))));
        } catch (Exception e) {
            return null;
        }
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
