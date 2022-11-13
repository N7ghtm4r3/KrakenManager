package com.tecknobit.krakenmanager.managers;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.apis.APIRequest;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.apimanager.trading.TradingTools;
import com.tecknobit.krakenmanager.managers.privates.KrakenPrivateManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Properties;

import static com.tecknobit.apimanager.apis.APIRequest.DEFAULT_ERROR_RESPONSE;
import static com.tecknobit.apimanager.trading.TradingTools.computeAssetPercent;
import static com.tecknobit.apimanager.trading.TradingTools.textualizeAssetPercent;

/**
 * The {@code KrakenManager} class is useful to manage all KrakenManager's endpoints
 * giving basics methods for others Kraken's managers and basics endpoints for API requests
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#section/General-Usage">
 * General-Usage</a>
 **/
public class KrakenManager {

    /**
     * {@code formatters} is the instance to pass in {@link Returner} methods to format as you want the response by
     * {@code "Kraken"}
     *
     * @apiNote you can choose between:
     * <ul>
     * <li>
     * {@link Returner.ReturnFormat#STRING} -> returns the response formatted as {@link String}
     * </li>
     * <li>
     * {@link Returner.ReturnFormat#JSON} -> returns the response formatted as {@code "JSON"}
     * </li>
     * <li>
     * {@link Returner.ReturnFormat#LIBRARY_OBJECT} -> returns the response formatted as custom object offered by library that uses this list
     * </li>
     * </ul>
     **/
    public static Returner.ReturnFormat formatters;

    /**
     * {@code properties} is a local instance used to instantiate a new {@link KrakenPrivateManager}'s manager without
     * re-insert credentials
     **/
    protected static final Properties properties = new Properties();

    /**
     * {@code BASE_ENDPOINT} is Kraken's base API endpoint
     **/
    public static final String BASE_ENDPOINT = "https://api.kraken.com/0";

    /**
     * {@code apiRequest} is instance to make the API requests
     **/
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
        storeProperties(defaultErrorMessage, requestTimeout);
    }

    /** Constructor to init a {@link KrakenManager}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * **/
    public KrakenManager(String defaultErrorMessage) {
        apiRequest = new APIRequest(defaultErrorMessage);
        storeProperties(defaultErrorMessage, -1);
    }

    /** Constructor to init a {@link KrakenManager}
     * @param requestTimeout: custom timeout for request
     * **/
    public KrakenManager(int requestTimeout) {
        apiRequest = new APIRequest(requestTimeout);
        storeProperties(null, requestTimeout);
    }

    /** Constructor to init a {@link KrakenManager} <br>
     * Any params required
     * **/
    public KrakenManager() {
        String defaultErrorMessage = properties.getProperty("defaultErrorMessage");
        int requestTimeout;
        try {
            requestTimeout = Integer.parseInt(properties.getProperty("requestTimeout"));
        } catch (NumberFormatException e) {
            requestTimeout = -1;
        }
        if (defaultErrorMessage != null && requestTimeout != -1)
            apiRequest = new APIRequest(defaultErrorMessage, requestTimeout);
        else if (defaultErrorMessage != null)
            apiRequest = new APIRequest(defaultErrorMessage);
        else if (requestTimeout != -1)
            apiRequest = new APIRequest(requestTimeout);
        else
            apiRequest = new APIRequest();
    }

    /**
     * Method to store some properties
     *
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout:      custom timeout for request
     **/
    protected void storeProperties(String defaultErrorMessage, int requestTimeout) {
        properties.clear();
        if (defaultErrorMessage != null)
            properties.setProperty("defaultErrorMessage", defaultErrorMessage);
        if (requestTimeout != -1)
            properties.setProperty("requestTimeout", String.valueOf(requestTimeout));
    }

    /**
     * Method to get status code of request response <br>
     * Any params required
     *
     * @return status code of request response
     **/
    public int getStatusResponse() {
        return apiRequest.getResponseStatusCode();
    }

    /**
     * Method to get error response of request <br>
     * Any params required
     *
     * @return error of the response as {@link String}
     **/
    public String getErrorResponse() {
        if (errorResponse == null)
            return DEFAULT_ERROR_RESPONSE;
        return errorResponse;
    }

    /**
     * Method to get the error response of the request <br>
     * Any params required
     *
     * @return error response of the request formatted as {@code "JSON"}
     **/
    public String getJSONErrorResponse() {
        return apiRequest.getJSONErrorResponse();
    }

    /**
     * Method to print error response of request <br>
     * Any params required
     **/
    public void printErrorResponse() {
        System.out.println(getErrorResponse());
    }

    /**
     * Method to round a value
     *
     * @param value:         value to round
     * @param decimalDigits: number of digits to round final value
     * @return value rounded with decimalDigits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double roundValue(double value, int decimalDigits) {
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
     **/
    public static class KrakenResponse {

        /**
         * {@code hResponse} is instance that memorizes base json response
         **/
        protected final JsonHelper hResponse;

        /**
         * {@code result} is instance useful to work on {@code "JSON"}
         **/
        protected final JsonHelper result;

        /**
         * {@code errors} is instance that memorizes list of the errors
         **/
        protected final String[] errors;

        /**
         * Constructor to init a {@link KrakenResponse} object
         *
         * @param hResponse: base json response
         **/
        public KrakenResponse(JSONObject hResponse) {
            boolean jsonArrayList = false;
            if (hResponse == null)
                hResponse = new JSONObject();
            this.hResponse = new JsonHelper(hResponse);
            result = new JsonHelper(this.hResponse.get("result", hResponse));
            JSONArray jsonErrors = this.hResponse.getJSONArray("error", new JSONArray());
            int errorsLength = jsonErrors.length();
            errors = new String[errorsLength];
            for (int j = 0; j < errorsLength; j++) {
                try {
                    errors[j] = jsonErrors.getString(j);
                } catch (JSONException e) {
                    jsonArrayList = true;
                    break;
                }
            }
            if (jsonArrayList) {
                int counter = 0;
                for (int j = 0; j < errorsLength; j++) {
                    JSONArray subErrors = jsonErrors.getJSONArray(j);
                    for (int i = 0; i < subErrors.length(); i++, counter++)
                        errors[counter] = subErrors.getString(i);
                }
            }
        }

        /**
         * Method to get result response <br>
         * Any params required
         *
         * @return result response as {@link T}
         **/
        public <T> T getResult() {
            return (T) hResponse.get("result");
        }

        /**
         * Method to get {@link #errors} instance <br>
         * Any params required
         *
         * @return {@link #errors} instance as array of {@link String}
         **/
        public String[] getErrors() {
            return errors;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

    /**
     * The {@code Params} class is useful to assemble params values for the request
     * @implNote this class can be used to assemble body payload or query request params
     * @implSpec look this library <a href="https://github.com/N7ghtm4r3/APIManager">here</a>
     * @see com.tecknobit.apimanager.apis.APIRequest.Params
     * **/
    public static class Params extends APIRequest.Params {}

}
