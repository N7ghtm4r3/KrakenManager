package com.tecknobit.krakenmanager.Managers.Private;

import com.tecknobit.apimanager.Manager.APIRequest.Headers;
import com.tecknobit.apimanager.Manager.APIRequest.Params;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Base64;

import static com.tecknobit.apimanager.Manager.APIRequest.POST_METHOD;
import static com.tecknobit.apimanager.Tools.Formatters.JsonHelper.getJSONObject;

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
        headers = new Headers();
        setHeaders();
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
        headers = new Headers();
        setHeaders();
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
        headers = new Headers();
        setHeaders();
    }

    /** Constructor to init a {@link KrakenPrivateManager} <br>
     * @param apiKey: api key of Kraken's platform
     * @param apiSign: api sign of Kraken's platform
     * **/
    public KrakenPrivateManager(String apiKey, String apiSign) {
        super();
        this.apiKey = apiKey;
        this.apiSign = apiSign;
        headers = new Headers();
        setHeaders();
    }

    /** Method to set base headers for request<br>
     * Any params required
     * **/
    private void setHeaders(){
        headers.addHeader(API_KEY_HEADER, apiKey);
        headers.addHeader(CONTENT_TYPE_HEADER, "application/x-www-form-urlencoded; charset=utf-8'");
    }

    /** Method to send a POST request<br>
     * @param endpoint: endpoint of API request
     * @param bodyParams: body params of the HTTP api request
     * @return response as {@link String}
     * **/
    public String sendPostRequest(String endpoint, Params bodyParams) throws Exception {
        if(bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("nonce", System.currentTimeMillis());
        headers.addHeader(API_SIGN_HEADER, getSignature(endpoint, bodyParams));
        apiRequest.sendBodyAPIRequest(BASE_ENDPOINT + "/private/" + endpoint, POST_METHOD, headers, bodyParams);
        JSONObject response = apiRequest.getJSONResponse();
        if(getJSONObject(response, "result") == null) {
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
            // TODO: 28/07/2022 IMPORT FROM LIBRARY MESSAGE AND SHA256 METHOD
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update((data.get("nonce") + apiRequest.assembleBodyParams(data)).getBytes());
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(new SecretKeySpec(Base64.getDecoder().decode(apiSign.getBytes()), "HmacSHA512"));
            mac.update(("/0/private/" + path).getBytes());
            return new String(Base64.getEncoder().encode(mac.doFinal(messageDigest.digest())));
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
