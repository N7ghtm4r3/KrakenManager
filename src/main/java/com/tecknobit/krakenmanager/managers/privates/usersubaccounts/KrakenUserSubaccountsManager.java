package com.tecknobit.krakenmanager.managers.privates.usersubaccounts;

import com.tecknobit.krakenmanager.managers.privates.KrakenPrivateManager;

/**
 * The {@code KrakenUserSubaccountsManager} class is useful to manage all user subaccounts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Subaccounts">
 * User Subaccounts</a>
 **/
public class KrakenUserSubaccountsManager extends KrakenPrivateManager {

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
    public KrakenUserSubaccountsManager() {
        super();
    }

}
