package com.tecknobit.krakenmanager.privates.usersubaccounts;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.krakenmanager.privates.KrakenPrivateManager;
import com.tecknobit.krakenmanager.privates.usersubaccounts.records.AccountTransfer;
import org.json.JSONObject;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.krakenmanager.KrakenManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code KrakenUserSubaccountsManager} class is useful to manage all user subaccounts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Subaccounts">
 * User Subaccounts</a>
 **/
public class KrakenUserSubaccountsManager extends KrakenPrivateManager {

    /**
     * {@code CREATE_SUBACCOUNT_ENDPOINT} is constant for CREATE_SUBACCOUNT_ENDPOINT's endpoint
     **/
    public static final String CREATE_SUBACCOUNT_ENDPOINT = "CreateSubaccount";

    /**
     * {@code ACCOUNT_TRANSFER_ENDPOINT} is constant for ACCOUNT_TRANSFER_ENDPOINT's endpoint
     **/
    public static final String ACCOUNT_TRANSFER_ENDPOINT = "AccountTransfer";

    /**
     * Constructor to init a {@link KrakenUserSubaccountsManager}
     *
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      : custom timeout for request
     * @param apiKey              : api key of Kraken's platform
     * @param apiSign             : api sign of Kraken's platform
     **/
    public KrakenUserSubaccountsManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserSubaccountsManager}
     *
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param apiKey              : api key of Kraken's platform
     * @param apiSign             : api sign of Kraken's platform
     **/
    public KrakenUserSubaccountsManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserSubaccountsManager}
     *
     * @param requestTimeout : custom timeout for request
     * @param apiKey         : api key of Kraken's platform
     * @param apiSign        : api sign of Kraken's platform
     **/
    public KrakenUserSubaccountsManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserSubaccountsManager} <br>
     *
     * @param apiKey  :  api key of Kraken's platform
     * @param apiSign : api sign of Kraken's platform
     **/
    public KrakenUserSubaccountsManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    /**
     * Constructor to init a {@link KrakenUserSubaccountsManager} <br>
     * No-any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
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
    public KrakenUserSubaccountsManager() {
        super();
    }

    /**
     * Request to create a trading subaccount
     *
     * @param username: username for the subaccount
     * @param email:    email address for the subaccount
     * @return creation of a trading subaccount result as boolean
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Subaccounts/operation/createSubaccount">
     * Create Subaccount</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/CreateSubaccount")
    public boolean createSubaccount(String username, String email) throws Exception {
        return Boolean.parseBoolean(createSubaccount(username, email, LIBRARY_OBJECT));
    }

    /**
     * Request to create a trading subaccount
     *
     * @param username: username for the subaccount
     * @param email:    email address for the subaccount
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return creation of a trading subaccount result as {"format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Subaccounts/operation/createSubaccount">
     * Create Subaccount</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/CreateSubaccount")
    public <T> T createSubaccount(String username, String email, ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("username", username);
        payload.addParam("email", email);
        String createResponse = sendPostRequest(CREATE_SUBACCOUNT_ENDPOINT, payload);
        switch (format) {
            case JSON:
                return (T) new JSONObject(createResponse);
            case LIBRARY_OBJECT:
                return (T) String.valueOf(new JSONObject(createResponse).getBoolean("result"));
            default:
                return (T) createResponse;
        }
    }

    /**
     * Request to transfer funds to and from master and subaccounts
     *
     * @param asset:  asset being transferred
     * @param amount: amount of asset to transfer
     * @param from:   IBAN of the source account
     * @param to:     IBAN of the destination account
     * @return transfer result as {@link AccountTransfer} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Subaccounts/operation/accountTransfer">
     * Account Transfer</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/AccountTransfer")
    public AccountTransfer execAccountTransfer(String asset, double amount, String from, String to) throws Exception {
        return execAccountTransfer(asset, amount, from, to, LIBRARY_OBJECT);
    }

    /**
     * Request to transfer funds to and from master and subaccounts
     *
     * @param asset:  asset being transferred
     * @param amount: amount of asset to transfer
     * @param from:   IBAN of the source account
     * @param to:     IBAN of the destination account
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return transfer result as {"format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Subaccounts/operation/accountTransfer">
     * Account Transfer</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "https://api.kraken.com/0/private/AccountTransfer")
    public <T> T execAccountTransfer(String asset, double amount, String from, String to, ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("asset", asset);
        payload.addParam("amount", amount);
        payload.addParam("from", from);
        payload.addParam("to", to);
        String transferResponse = sendPostRequest(ACCOUNT_TRANSFER_ENDPOINT, payload);
        switch (format) {
            case JSON:
                return (T) new JSONObject(transferResponse);
            case LIBRARY_OBJECT:
                return (T) new AccountTransfer(new JSONObject(transferResponse));
            default:
                return (T) transferResponse;
        }
    }

}
