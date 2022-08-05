package com.tecknobit.krakenmanager.Managers;

import com.tecknobit.apimanager.Manager.APIRequest;
import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.apimanager.Tools.Trading.TradingTools;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.tecknobit.apimanager.Manager.APIRequest.*;
import static com.tecknobit.apimanager.Tools.Trading.TradingTools.computeAssetPercent;
import static com.tecknobit.apimanager.Tools.Trading.TradingTools.textualizeAssetPercent;

/**
 *  The {@code KrakenManager} class is useful to manage all KrakenManager's endpoints
 *  giving basics methods for others Kraken's managers and basics endpoints for API requests
 *  @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#section/General-Usage">
 *      https://docs.kraken.com/rest/#section/General-Usage</a>
 *  @author N7ghtm4r3 - Tecknobit
 * **/

public class KrakenManager {

    /**
     * {@code BASE_ENDPOINT} is Kraken's base API endpoint
     * **/
    public static final String BASE_ENDPOINT = "https://api.kraken.com/0";

    /**
     * {@code apiRequest} is instance to make the API requests
     * **/
    protected APIRequest apiRequest;

    /**
     * {@code errorResponse} is instance for responses error
     * **/
    protected String errorResponse;

    /** Constructor to init a {@link KrakenManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout: custom timeout for request
     * **/
    public KrakenManager(String defaultErrorMessage, int requestTimeout) {
        apiRequest = new APIRequest(defaultErrorMessage, requestTimeout);
    }

    /** Constructor to init a {@link KrakenManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * **/
    public KrakenManager(String defaultErrorMessage) {
        apiRequest = new APIRequest(defaultErrorMessage);
    }

    /** Constructor to init a {@link KrakenManager}
     * @param requestTimeout: custom timeout for request
     * **/
    public KrakenManager(int requestTimeout) {
        apiRequest = new APIRequest(requestTimeout);
    }

    /** Constructor to init a {@link KrakenManager} <br>
     * Any params required
     * **/
    public KrakenManager() {
        apiRequest = new APIRequest();
    }

    /** Method to get status code of request response <br>
     * Any params required
     * @return status code of request response
     * **/
    public int getStatusResponse(){
        return apiRequest.getResponseStatusCode();
    }

    /** Method to get error response of request <br>
     * Any params required
     * @return error of the response as {@link String}
     * **/
    public String getErrorResponse(){
        if(errorResponse == null)
            return DEFAULT_ERROR_RESPONSE;
        return errorResponse;
    }

    /** Method to print error response of request <br>
     * Any params required
     * **/
    public void printErrorResponse(){
        System.out.println(getErrorResponse());
    }

    /** Method to round a value
     * @param value: value to round
     * @param decimalDigits: number of digits to round final value
     * @return value rounded with decimalDigits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     * **/
    public double roundValue(double value, int decimalDigits){
        return TradingTools.roundValue(value, decimalDigits);
    }

    /** Method to get percent between two values
     * @param startValue: first value to make compare
     * @param finalValue: last value to compare and get percent by first value
     * @return percent value as double es. 8 or -8
     * @throws IllegalArgumentException if startValue or lastValue are negative
     * **/
    public double getTrendPercent(double startValue, double finalValue){
        return computeAssetPercent(startValue, finalValue);
    }

    /** Method to get percent between two values and round it
     * @param startValue: first value to make compare
     * @param finalValue: last value to compare and get percent by first value
     * @param decimalDigits: number of digits to round final percent value
     * @return percent value as double es. 8 or -8
     * @throws IllegalArgumentException if startValue or lastValue are negative
     * **/
    public double getTrendPercent(double startValue, double finalValue, int decimalDigits){
        return computeAssetPercent(startValue, finalValue, decimalDigits);
    }

    /** Method to format percent between two values and textualize it
     * @param percent: value to format
     * @return percent value formatted es. +8% or -8% as {@link String}
     * **/
    public String getTextTrendPercent(double percent){
        return textualizeAssetPercent(percent);
    }

    /** Method to get percent between two values and textualize it
     * @param startValue: first value to make compare
     * @param finalValue: last value to compare and get percent by first value
     * @return percent value es. +8% or -8% as {@link String}
     * **/
    public String getTextTrendPercent(double startValue, double finalValue){
        return textualizeAssetPercent(startValue, finalValue);
    }

    /** Method to get percent between two values and textualize it
     * @param startValue: first value to make compare
     * @param finalValue: last value to compare and get percent by first value
     * @param decimalDigits: number of digits to round final percent value
     * @return percent value es. +8% or -8% as {@link String}
     * **/
    public String getTextTrendPercent(double startValue, double finalValue, int decimalDigits){
        return textualizeAssetPercent(startValue, finalValue, decimalDigits);
    }

    /**
     * The {@code KrakenResponse} class is useful to format all responses and give base details for
     * others custom records given by library
     * **/

    public static class KrakenResponse {

        /**
         * {@code jsonResponse} is instance that memorizes base json response
         * **/
        protected final JsonHelper jsonResponse;

        /**
         * {@code errors} is instance that memorizes list of the errors
         * **/
        protected final String[] errors;

        /** Constructor to init a {@link KrakenResponse} object
         * @param jsonResponse: base json response
         * **/
        public KrakenResponse(JSONObject jsonResponse) {
            boolean jsonArrayList = false;
            this.jsonResponse = new JsonHelper(jsonResponse);
            JSONArray jsonErrors = this.jsonResponse.getJSONArray("error", new JSONArray());
            int errorsLength = jsonErrors.length();
            errors = new String[errorsLength];
            for (int j = 0; j < errorsLength; j++) {
                try {
                    errors[j] = jsonErrors.getString(j);
                }catch (JSONException e){
                    jsonArrayList = true;
                    break;
                }
            }
            if(jsonArrayList){
                int counter = 0;
                for (int j = 0; j < errorsLength; j++){
                    JSONArray subErrors = jsonErrors.getJSONArray(j);
                    for (int i = 0; i < subErrors.length(); i++, counter++)
                        errors[counter] = subErrors.getString(i);
                }
            }
        }

        public <T> T getResult(){
            return (T) jsonResponse.get("result");
        }

        public String[] getErrors() {
            return errors;
        }

        @Override
        public String toString() {
            return "KrakenResponse{" +
                    "jsonResponse=" + jsonResponse +
                    ", errors=" + Arrays.toString(errors) +
                    '}';
        }

    }

}
