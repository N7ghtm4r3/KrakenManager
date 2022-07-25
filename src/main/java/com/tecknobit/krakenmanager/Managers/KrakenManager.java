package com.tecknobit.krakenmanager.Managers;

import com.tecknobit.apimanager.Manager.APIRequest;
import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class KrakenManager {

    public static final String BASE_ENDPOINT = "https://api.kraken.com/0";
    protected APIRequest apiRequest;

    public KrakenManager(String defaultErrorMessage, int requestTimeout) {
        apiRequest = new APIRequest(defaultErrorMessage, requestTimeout);
    }

    public KrakenManager(String defaultErrorMessage) {
        apiRequest = new APIRequest(defaultErrorMessage);
    }

    public KrakenManager(int requestTimeout) {
        apiRequest = new APIRequest(requestTimeout);
    }

    public KrakenManager() {
        apiRequest = new APIRequest();
    }

    public String getErrorResponse(){
        return apiRequest.getErrorResponse();
    }

    public void printErrorResponse(){
        apiRequest.printErrorResponse();
    }

    /** Method to get status code of request response <br>
     * Any params required
     * @return status code of request response
     * **/
    public int getStatusResponse(){
        return apiRequest.getResponseStatusCode();
    }

    public static class KrakenResponse {

        protected final JsonHelper jsonResponse;
        protected final String[] errors;

        public KrakenResponse(JSONObject jsonResponse) {
            this.jsonResponse = new JsonHelper(jsonResponse);
            JSONArray jsonErrors = this.jsonResponse.getJSONArray("error", new JSONArray());
            int errorsLength = jsonErrors.length();
            errors = new String[errorsLength];
            for (int j = 0; j < errorsLength; j++){
                errors[j] = jsonErrors.getString(j);
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
