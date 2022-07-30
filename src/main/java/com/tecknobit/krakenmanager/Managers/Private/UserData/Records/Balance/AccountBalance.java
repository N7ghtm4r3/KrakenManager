package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Balance;

import com.tecknobit.apimanager.Tools.Trading.TradingTools;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The {@code AccountBalance} class is useful to format account balance data object
 * @apiNote see official documentation at: <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance">
 *     https://docs.kraken.com/rest/#tag/User-Data/operation/getAccountBalance</a>
 * @author N7ghtm4r3 - Tecknobit
 * **/

public class AccountBalance extends KrakenManager.KrakenResponse {

    /**
     * {@code wallet} is instance that memorizes wallet
     * **/
    private final Wallet wallet;

    /**
     * Constructor to init an {@link AccountBalance} object
     * @param jsonResponse : base json response
     **/
    public AccountBalance(JSONObject jsonResponse) {
        super(jsonResponse);
        this.wallet = new Wallet(getResult());
    }

    public Wallet getWallet() {
        return wallet;
    }

    @Override
    public String toString() {
        return "AccountBalance{" +
                "wallet=" + wallet +
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

    /**
     * {@code Wallet} is class useful to format wallet details as custom object
     * **/

    public static class Wallet {

        /**
         * {@code wallet} is instance that memorizes assets as keys and their balances as values
         * **/
        private final HashMap<String, BalanceAsset> wallet;

        /**
         * Constructor to init a {@link Wallet} object
         * @param jsonWallet : wallet data in JSON format
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

        /** Method to refresh value of {@link BalanceAsset} already in list
         * @param asset: asset name es. ZUSD
         * @param balanceRefreshed: new balance value of asset's address
         * @throws IllegalArgumentException when balance value is a negative value
         * **/
        public void refreshAssetBalance(String asset, double balanceRefreshed){
            wallet.replace(asset, new BalanceAsset(asset, balanceRefreshed));
        }

        /** Method to remove a {@link BalanceAsset} from list
         * @param assetToRemove: asset name to remove from assets list es ZUSD
         * **/
        public void removeAsset(String assetToRemove){
            wallet.remove(assetToRemove);
        }

        /** Method to get {@link BalanceAsset}'s list<br>
         * Any params required
         * @return balances of assets as {@link ArrayList} of {@link BalanceAsset} custom object
         * **/
        public ArrayList<BalanceAsset> getAssets(){
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
         * Any params required
         * @return total balance as double
         * **/
        public double getTotalBalance() {
            double walletBalance = 0;
            for (BalanceAsset asset : wallet.values())
                walletBalance += asset.balance;
            return walletBalance;
        }

        /** Method to total balance of user's account
         * @param decimals: decimals digits to round value
         * @return total balance as double
         * **/
        public double getTotalBalance(int decimals) {
            // TODO: 28/07/2022 INSERT STATIC METHOD FROM LIBRARY 
            return new TradingTools().roundValue(getTotalBalance(), decimals);
        }

        @Override
        public String toString() {
            return "Wallet{" +
                    "wallet=" + wallet +
                    '}';
        }

        /**
         * {@code BalanceAsset} is class useful to format balance asset details as custom object
         * **/

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
                if(balance < 0)
                    throw new IllegalArgumentException("Balance value cannot be lesser than 0");
                else
                    this.balance = balance;
            }

            public String getAsset() {
                return asset;
            }

            public double getBalance() {
                return balance;
            }

            /** Method to refresh value of {@link BalanceAsset}
             * @param refreshedBalance: new balance value of asset's address
             * @throws IllegalArgumentException when balance value is a negative value
             * **/
            public void refreshBalance(double refreshedBalance) {
                if(refreshedBalance < 0)
                    throw new IllegalArgumentException("Balance value cannot be lesser than 0");
                this.balance = refreshedBalance;
            }

            @Override
            public String toString() {
                return "BalanceAsset{" +
                        "asset='" + asset + '\'' +
                        ", balance=" + balance +
                        '}';
            }

        }

    }

}
