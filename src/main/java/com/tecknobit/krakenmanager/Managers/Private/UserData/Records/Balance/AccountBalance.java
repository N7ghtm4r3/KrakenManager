package com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Balance;

import com.tecknobit.apimanager.Tools.Trading.TradingTools;
import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AccountBalance extends KrakenManager.KrakenResponse {

    private final Wallet wallet;

    /**
     * Constructor to init a {@link AccountBalance}
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

    public static class Wallet {

        private final HashMap<String, BalanceAsset> wallet;

        public Wallet(JSONObject jsonWallet) {
            wallet = new HashMap<>();
            for (String asset : jsonWallet.keySet())
               wallet.put(asset, new BalanceAsset(asset, jsonWallet.getDouble(asset)));
        }

        public void insertAsset(String asset, double balance){
            wallet.put(asset, new BalanceAsset(asset, balance));
        }

        public void refreshAssetBalance(String asset, double balanceRefreshed){
            wallet.replace(asset, new BalanceAsset(asset, balanceRefreshed));
        }

        public void removeAsset(String assetToRemove){
            wallet.remove(assetToRemove);
        }

        public ArrayList<BalanceAsset> getAssets(){
            ArrayList<BalanceAsset> balanceAssets = new ArrayList<>();
            for (String asset : wallet.keySet())
                balanceAssets.add(wallet.get(asset));
            return balanceAssets;
        }

        public BalanceAsset getAsset(String asset){
            BalanceAsset balanceAssetCoin = wallet.get(asset);
            if(balanceAssetCoin == null)
                return new BalanceAsset(asset, 0);
            return balanceAssetCoin;
        }

        public double getTotalBalance() {
            double walletBalance = 0;
            for (BalanceAsset asset : wallet.values())
                walletBalance += asset.balance;
            return walletBalance;
        }

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

        public static class BalanceAsset {

            private final String asset;
            private double balance;

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
