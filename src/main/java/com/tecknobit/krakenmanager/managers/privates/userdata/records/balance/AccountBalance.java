package com.tecknobit.krakenmanager.managers.privates.userdata.records.balance;

import com.tecknobit.krakenmanager.managers.KrakenManager;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code AccountBalance} class is useful to format account balance data object
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance">
 * Get Account Balance</a>
 **/
public class AccountBalance extends KrakenManager.KrakenResponse {

    /**
     * {@code wallet} is instance that memorizes wallet
     **/
    private final Wallet wallet;

    /**
     * Constructor to init an {@link AccountBalance} object
     *
     * @param jsonResponse: base json response
     **/
    public AccountBalance(JSONObject jsonResponse) {
        super(jsonResponse);
        this.wallet = new Wallet(getResult());
    }

    /**
     * Method to get {@link #wallet} instance <br>
     * No-any params required
     *
     * @return {@link #wallet} instance as {@link Wallet}
     **/
    public Wallet getWallet() {
        return wallet;
    }

    /**
     * {@code Wallet} is class useful to format wallet details as custom object
     **/
    public static class Wallet {

        /**
         * {@code wallet} is instance that memorizes assets as keys and their balances as values
         **/
        private final HashMap<String, BalanceAsset> wallet;

        /**
         * Constructor to init a {@link Wallet} object
         *
         * @param jsonWallet : wallet data in {@code "JSON"} format
         **/
        public Wallet(JSONObject jsonWallet) {
            wallet = new HashMap<>();
            for (String asset : jsonWallet.keySet())
                wallet.put(asset, new BalanceAsset(asset, jsonWallet.getDouble(asset)));
        }

        /** Method to insert a new {@link BalanceAsset} in list
         * @param asset: asset name es. ZUSD
         * @param balance: balance value of asset's address
         * @throws IllegalArgumentException when balance value is a negative value
         * **/
        public void insertAsset(String asset, double balance){
            wallet.put(asset, new BalanceAsset(asset, balance));
        }

        /**
         * Method to refresh value of {@link BalanceAsset} already in list
         *
         * @param asset:            asset name es. ZUSD
         * @param balanceRefreshed: new balance value of asset's address
         * @throws IllegalArgumentException when balance value is a negative value
         **/
        public void refreshAssetBalance(String asset, double balanceRefreshed) {
            wallet.replace(asset, new BalanceAsset(asset, balanceRefreshed));
        }

        /**
         * Method to remove a {@link BalanceAsset} from list
         *
         * @param assetToRemove: asset name to remove from assets list es ZUSD
         **/
        public void removeAsset(String assetToRemove) {
            wallet.remove(assetToRemove);
        }

        /**
         * Method to get {@link BalanceAsset}'s list<br>
         * No-any params required
         *
         * @return balances of assets as {@link ArrayList} of {@link BalanceAsset} custom object
         **/
        public ArrayList<BalanceAsset> getAssets() {
            ArrayList<BalanceAsset> balanceAssets = new ArrayList<>();
            for (String asset : wallet.keySet())
                balanceAssets.add(wallet.get(asset));
            return balanceAssets;
        }

        /** Method to get single {@link BalanceAsset} from list
         * @param asset: asset name es. ZUSD
         * @return balance of asset as {@link BalanceAsset} custom object
         * **/
        public BalanceAsset getAsset(String asset){
            BalanceAsset balanceAssetCoin = wallet.get(asset);
            if(balanceAssetCoin == null)
                return new BalanceAsset(asset, 0);
            return balanceAssetCoin;
        }

        /** Method to total balance of user's account <br>
         * No-any params required
         * @return total balance as double
         * **/
        public double getTotalBalance() {
            double walletBalance = 0;
            for (BalanceAsset asset : wallet.values())
                walletBalance += asset.balance;
            return walletBalance;
        }

        /**
         * Method to get total balance of user's account <br>
         *
         * @param decimals: number of digits to round final value
         * @return total balance instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getPrice(int decimals) {
            return roundValue(getTotalBalance(), decimals);
        }

        /**
         * Returns a string representation of the object <br>
         * No-any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        /**
         * {@code BalanceAsset} is class useful to format balance asset details as custom object
         **/
        public static class BalanceAsset {

            /**
             * {@code asset} is instance that memorizes asset value
             * **/
            private final String asset;

            /**
             * {@code balance} is instance that memorizes balance value
             * **/
            private double balance;

            /**
             * Constructor to init a {@link BalanceAsset} object
             * @param asset: asset name es. ZUSD
             * @param balance: balance value of asset's address
             * @throws IllegalArgumentException when balance value is a negative value
             **/
            public BalanceAsset(String asset, double balance) {
                this.asset = asset;
                if (balance < 0)
                    throw new IllegalArgumentException("Balance value cannot be lesser than 0");
                else
                    this.balance = balance;
            }

            /**
             * Method to get {@link #asset} instance <br>
             * No-any params required
             *
             * @return {@link #asset} instance as {@link String}
             **/
            public String getAsset() {
                return asset;
            }

            /**
             * Method to get {@link #balance} instance <br>
             * No-any params required
             *
             * @return {@link #balance} instance as double
             **/
            public double getBalance() {
                return balance;
            }

            /**
             * Method to get {@link #balance} instance
             *
             * @param decimals: number of digits to round final value
             * @return {@link #balance} instance rounded with decimal digits inserted
             * @throws IllegalArgumentException if decimalDigits is negative
             **/
            public double getBalance(int decimals) {
                return roundValue(balance, decimals);
            }

            /**
             * Method to refresh value of {@link BalanceAsset}
             *
             * @param refreshedBalance: new balance value of asset's address
             * @throws IllegalArgumentException when balance value is a negative value
             **/
            public void refreshBalance(double refreshedBalance) {
                if (refreshedBalance < 0)
                    throw new IllegalArgumentException("Balance value cannot be lesser than 0");
                this.balance = refreshedBalance;
            }

            /**
             * Returns a string representation of the object <br>
             * No-any params required
             *
             * @return a string representation of the object as {@link String}
             */
            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

        }

    }

}
