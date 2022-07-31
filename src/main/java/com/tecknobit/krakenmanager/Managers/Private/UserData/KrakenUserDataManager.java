package com.tecknobit.krakenmanager.Managers.Private.UserData;

import com.tecknobit.krakenmanager.Managers.Private.KrakenPrivateManager;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Balance.AccountBalance;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Balance.TradeBalance;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Ledger;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.OpenPosition;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.ClosedOrder;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Orders.Order;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.ReportStatus;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades.HistoryTrade;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades.QueryTrade;
import com.tecknobit.krakenmanager.Managers.Private.UserData.Records.Trades.TradeVolume;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.tecknobit.apimanager.Manager.APIRequest.Params;
import static com.tecknobit.krakenmanager.Constants.EndpointsList.*;

public class KrakenUserDataManager extends KrakenPrivateManager {

    public KrakenUserDataManager(String defaultErrorMessage, int requestTimeout, String apiKey, String apiSign) {
        super(defaultErrorMessage, requestTimeout, apiKey, apiSign);
    }

    public KrakenUserDataManager(String defaultErrorMessage, String apiKey, String apiSign) {
        super(defaultErrorMessage, apiKey, apiSign);
    }

    public KrakenUserDataManager(int requestTimeout, String apiKey, String apiSign) {
        super(requestTimeout, apiKey, apiSign);
    }

    public KrakenUserDataManager(String apiKey, String apiSign) {
        super(apiKey, apiSign);
    }

    public String getAccountBalance() throws Exception {
        return sendPostRequest(GET_ACCOUNT_BALANCE_ENDPOINT, null);
    }

    public JSONObject getAccountBalanceJSON() throws Exception {
        return new JSONObject(getAccountBalance());
    }

    public AccountBalance getAccountBalanceObject() throws Exception {
        return new AccountBalance(getAccountBalanceJSON());
    }

    public String getTradeBalance() throws Exception {
        return sendPostRequest(GET_TRADE_BALANCE_ENDPOINT, null);
    }

    public JSONObject getTradeBalanceJSON() throws Exception {
        return new JSONObject(getTradeBalance());
    }

    public TradeBalance getTradeBalanceObject() throws Exception {
        return new TradeBalance(getTradeBalanceJSON());
    }

    public String getTradeBalance(String asset) throws Exception {
        Params params = new Params();
        params.addParam("asset", asset);
        return sendPostRequest(GET_TRADE_BALANCE_ENDPOINT, params);
    }

    public JSONObject getTradeBalanceJSON(String asset) throws Exception {
        return new JSONObject(getTradeBalance(asset));
    }

    public TradeBalance getTradeBalanceObject(String asset) throws Exception {
        return new TradeBalance(getTradeBalanceJSON(asset));
    }

    public String getOpenOrders(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(GET_OPEN_ORDERS_ENDPOINT, params);
    }

    public JSONObject getOpenOrdersJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getOpenOrders(insertTrades));
    }

    public ArrayList<Order> getOpenOrdersList(boolean insertTrades) throws Exception {
        return assembleOrdersList(getOpenOrdersJSON(insertTrades).getJSONObject("result").getJSONObject("open"));
    }

    public String getOpenOrders(boolean insertTrades, long userRef) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("userref", userRef);
        return sendPostRequest(GET_OPEN_ORDERS_ENDPOINT, params);
    }

    public JSONObject getOpenOrdersJSON(boolean insertTrades, long userRef) throws Exception {
        return new JSONObject(getOpenOrders(insertTrades, userRef));
    }

    public ArrayList<Order> getOpenOrdersList(boolean insertTrades, long userRef) throws Exception {
        return assembleOrdersList(getOpenOrdersJSON(insertTrades, userRef).getJSONObject("result")
                .getJSONObject("open"));
    }

    private ArrayList<Order> assembleOrdersList(JSONObject jsonOrder){
        ArrayList<Order> openOrders = new ArrayList<>();
        for (String order : jsonOrder.keySet())
            openOrders.add(new Order(jsonOrder.getJSONObject(order)));
        return openOrders;
    }

    // TODO: 29/07/2022 INSERT COUNT CORRESPONDING TO LIST SIZE INTO DOCUSTRING
    public String getClosedOrders(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(GET_CLOSED_ORDERS_ENDPOINT, params);
    }

    public JSONObject getClosedOrdersJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getClosedOrders(insertTrades));
    }

    public ArrayList<ClosedOrder> getClosedOrdersList(boolean insertTrades) throws Exception {
        return assembleClosedOrdersList(getClosedOrdersJSON(insertTrades));
    }

    public String getClosedOrders(Params params) throws Exception {
        return sendPostRequest(GET_CLOSED_ORDERS_ENDPOINT, params);
    }

    public JSONObject getClosedOrdersJSON(Params params) throws Exception {
        return new JSONObject(getClosedOrders(params));
    }

    public ArrayList<ClosedOrder> getClosedOrdersList(Params params) throws Exception {
        return assembleClosedOrdersList(getClosedOrdersJSON(params));
    }

    private ArrayList<ClosedOrder> assembleClosedOrdersList(JSONObject jsonOrder){
        ArrayList<ClosedOrder> openOrders = new ArrayList<>();
        jsonOrder = jsonOrder.getJSONObject("result").getJSONObject("closed");
        for (String order : jsonOrder.keySet())
            openOrders.add(new ClosedOrder(jsonOrder.getJSONObject(order)));
        return openOrders;
    }

    public String getQueryOrdersInfo(long txId, boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", txId);
        return sendPostRequest(QUERY_ORDERS_ENDPOINT, params);
    }

    public JSONObject getQueryOrdersInfoJSON(long txId, boolean insertTrades) throws Exception {
        return new JSONObject(getQueryOrdersInfo(txId, insertTrades));
    }

    public ArrayList<Order> getQueryOrdersInfoList(long txId, boolean insertTrades) throws Exception {
        return assembleOrdersList(getQueryOrdersInfoJSON(txId, insertTrades).getJSONObject("result"));
    }

    public String getQueryOrdersInfo(long txId, boolean insertTrades, long userRef) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", txId);
        params.addParam("userref", userRef);
        return sendPostRequest(QUERY_ORDERS_ENDPOINT, params);
    }

    public JSONObject getQueryOrdersInfoJSON(long txId, boolean insertTrades, long userRef) throws Exception {
        return new JSONObject(getQueryOrdersInfo(txId, insertTrades, userRef));
    }

    public ArrayList<Order> getQueryOrdersInfoList(long txId, boolean insertTrades, long userRef) throws Exception {
        return assembleOrdersList(getQueryOrdersInfoJSON(txId, insertTrades, userRef).getJSONObject("result"));
    }

    // TODO: 29/07/2022 INSERT COUNT CORRESPONDING TO LIST SIZE INTO DOCUSTRING
    public String getTradesHistory() throws Exception {
        return sendPostRequest(GET_TRADES_HISTORY_ENDPOINT, null);
    }

    public JSONObject getTradesHistoryJSON() throws Exception {
        return new JSONObject(getTradesHistory());
    }

    public ArrayList<HistoryTrade> getTradesHistoryList() throws Exception {
        return assebleHistoryTradesList(getTradesHistoryJSON());
    }

    public String getTradesHistory(Params params) throws Exception {
        return sendPostRequest(GET_TRADES_HISTORY_ENDPOINT, params);
    }

    public JSONObject getTradesHistoryJSON(Params params) throws Exception {
        return new JSONObject(getTradesHistory(params));
    }

    public ArrayList<HistoryTrade> getTradesHistoryList(Params params) throws Exception {
        return assebleHistoryTradesList(getTradesHistoryJSON(params));
    }

    private ArrayList<HistoryTrade> assebleHistoryTradesList(JSONObject jsonTrades) {
        ArrayList<HistoryTrade> trades = new ArrayList<>();
        jsonTrades = jsonTrades.getJSONObject("result").getJSONObject("trades");
        for (String trade : jsonTrades.keySet())
            trades.add(new HistoryTrade(jsonTrades.getJSONObject(trade), trade));
        return trades;
    }

    public String getQueryTradesInfo(boolean insertTrades) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        return sendPostRequest(QUERY_TRADES_ENDPOINT, params);
    }

    public JSONObject getQueryTradesInfoJSON(boolean insertTrades) throws Exception {
        return new JSONObject(getQueryTradesInfo(insertTrades));
    }

    public ArrayList<QueryTrade> getQueryTradesInfoList(boolean insertTrades) throws Exception {
        return assebleQueryTradesList(getQueryTradesInfoJSON(insertTrades));
    }

    public String getQueryTradesInfo(boolean insertTrades, String txId) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("txid", txId);
        return sendPostRequest(QUERY_TRADES_ENDPOINT, params);
    }

    public JSONObject getQueryTradesInfoJSON(boolean insertTrades, String txId) throws Exception {
        return new JSONObject(getQueryTradesInfo(insertTrades, txId));
    }

    public ArrayList<QueryTrade> getQueryTradesInfoList(boolean insertTrades, String txId) throws Exception {
        return assebleQueryTradesList(getQueryTradesInfoJSON(insertTrades, txId));
    }

    private ArrayList<QueryTrade> assebleQueryTradesList(JSONObject jsonTrades) {
        ArrayList<QueryTrade> trades = new ArrayList<>();
        for (String trade : jsonTrades.keySet())
            trades.add(new QueryTrade(jsonTrades.getJSONObject(trade), trade));
        return trades;
    }

    public String getOpenPositions(boolean doCalcs) throws Exception {
        Params params = new Params();
        if(doCalcs)
            params.addParam("docalcs", true);
        return sendPostRequest(GET_OPEN_POSITIONS_ENDPOINT, params);
    }

    public JSONObject getOpenPositionsJSON(boolean doCalcs) throws Exception {
        return new JSONObject(getOpenPositions(doCalcs));
    }

    public ArrayList<OpenPosition> getOpenPositionsList(boolean doCalcs) throws Exception {
        return assembleOpenPositionsList(getOpenPositionsJSON(doCalcs));
    }

    public String getOpenPositions(boolean doCalcs, String txId) throws Exception {
        Params params = new Params();
        if(doCalcs)
            params.addParam("docalcs", true);
        params.addParam("txid", txId);
        return sendPostRequest(GET_OPEN_POSITIONS_ENDPOINT, params);
    }

    public JSONObject getOpenPositionsJSON(boolean doCalcs, String txId) throws Exception {
        return new JSONObject(getOpenPositions(doCalcs, txId));
    }

    public ArrayList<OpenPosition> getOpenPositionsList(boolean doCalcs, String txId) throws Exception {
        return assembleOpenPositionsList(getOpenPositionsJSON(doCalcs, txId));
    }

    private ArrayList<OpenPosition> assembleOpenPositionsList(JSONObject jsonPositions) {
        ArrayList<OpenPosition> openPositions = new ArrayList<>();
        jsonPositions = jsonPositions.getJSONObject("result");
        for (String positionId : jsonPositions.keySet())
            openPositions.add(new OpenPosition(jsonPositions.getJSONObject(positionId), positionId));
        return openPositions;
    }

    // TODO: 29/07/2022 INSERT COUNT CORRESPONDING TO LIST SIZE INTO DOCUSTRING
    public String getLedgersInfo() throws Exception {
        return sendPostRequest(GET_LEDGERS_ENDPOINT, null);
    }

    public JSONObject getLedgersInfoJSON() throws Exception {
        return new JSONObject(getLedgersInfo());
    }

    public ArrayList<Ledger> getLedgersInfoList() throws Exception {
        return assembleLedgersList(getLedgersInfoJSON().getJSONObject("result").getJSONObject("ledger"));
    }

    public String getLedgersInfo(Params params) throws Exception {
        return sendPostRequest(GET_LEDGERS_ENDPOINT, params);
    }

    public JSONObject getLedgersInfoJSON(Params params) throws Exception {
        return new JSONObject(getLedgersInfo(params));
    }

    public ArrayList<Ledger> getLedgersInfoList(Params params) throws Exception {
        return assembleLedgersList(getLedgersInfoJSON(params).getJSONObject("result").getJSONObject("ledger"));
    }

    public String getQueryLedgers(boolean insertTrades, String... id) throws Exception {
        Params params = new Params();
        if(insertTrades)
            params.addParam("trades", true);
        params.addParam("id", apiRequest.assembleParamsList(",", id));
        return sendPostRequest(QUERY_LEDGERS_ENDPOINT, params);
    }

    public JSONObject getQueryLedgersJSON(boolean insertTrades, String... id) throws Exception {
        return new JSONObject(getQueryLedgers(insertTrades, id));
    }

    public ArrayList<Ledger> getQueryLedgersList(boolean insertTrades, String... id) throws Exception {
        return assembleLedgersList(getQueryLedgersJSON(insertTrades, id).getJSONObject("result"));
    }

    public String getQueryLedgers(boolean insertTrades, ArrayList<String> id) throws Exception {
       return getQueryLedgers(insertTrades, id.toArray(new String[id.size()]));
    }

    public JSONObject getQueryLedgersJSON(boolean insertTrades, ArrayList<String> id) throws Exception {
        return new JSONObject(getQueryLedgers(insertTrades, id));
    }

    public ArrayList<Ledger> getQueryLedgersList(boolean insertTrades, ArrayList<String> id) throws Exception {
        return assembleLedgersList(getQueryLedgersJSON(insertTrades, id).getJSONObject("result"));
    }

    private ArrayList<Ledger> assembleLedgersList(JSONObject jsonLedgers) {
        ArrayList<Ledger> ledgers = new ArrayList<>();
        for (String ledgerId : jsonLedgers.keySet())
            ledgers.add(new Ledger(jsonLedgers.getJSONObject(ledgerId), ledgerId));
        return ledgers;
    }

    public String getTradeVolume(String pair, boolean insertFeeInfo) throws Exception {
        Params params = new Params();
        if(insertFeeInfo)
            params.addParam("fee-info", true);
        return sendPostRequest(GET_TRADE_VOLUME_ENDPOINT + "?pair=" + pair, params);
    }

    public JSONObject getTradeVolumeJSON(String pair, boolean insertFeeInfo) throws Exception {
        return new JSONObject(getTradeVolume(pair, insertFeeInfo));
    }

    public TradeVolume getTradeVolumeObject(String pair, boolean insertFeeInfo) throws Exception {
        return new TradeVolume(getTradeVolumeJSON(pair, insertFeeInfo));
    }

    public String getTradeVolume(String pair, boolean insertFeeInfo, String... pairs) throws Exception {
        Params params = new Params();
        if(insertFeeInfo)
            params.addParam("fee-info", true);
        params.addParam("pair", apiRequest.assembleParamsList(",", pairs));
        return sendPostRequest(GET_TRADE_VOLUME_ENDPOINT + "?pair=" + pair, params);
    }

    public JSONObject getTradeVolumeJSON(String pair, boolean insertFeeInfo, String... pairs) throws Exception {
        return new JSONObject(getTradeVolume(pair, insertFeeInfo, pairs));
    }

    public TradeVolume getTradeVolumeObject(String pair, boolean insertFeeInfo, String... pairs) throws Exception {
        return new TradeVolume(getTradeVolumeJSON(pair, insertFeeInfo, pairs));
    }

    public String addExport(String report, String description) throws Exception {
        Params params = new Params();
        params.addParam("report", report);
        params.addParam("description", description);
        return sendPostRequest(ADD_EXPORT_ENDPOINT, params);
    }

    public JSONObject addExportJSON(String report, String description) throws Exception {
        return new JSONObject(addExport(report, description));
    }

    public String getExportIdAdded(String report, String description) throws Exception {
        return addExportJSON(report, description).getJSONObject("result").getString("id");
    }

    public String addExport(String report, String description, Params params) throws Exception {
        params.addParam("report", report);
        params.addParam("description", description);
        return sendPostRequest(ADD_EXPORT_ENDPOINT, params);
    }

    public JSONObject addExportJSON(String report, String description, Params params) throws Exception {
        return new JSONObject(addExport(report, description, params));
    }

    public String getExportIdAdded(String report, String description, Params params) throws Exception {
        return addExportJSON(report, description, params).getJSONObject("result").getString("id");
    }

    public String getExportStatus(String report) throws Exception {
        Params params = new Params();
        params.addParam("report", report);
        return sendPostRequest(GET_EXPORT_STATUS_ENDPOINT, params);
    }

    public JSONObject getExportStatusJSON(String report) throws Exception {
        return new JSONObject(getExportStatus(report));
    }

    public ArrayList<ReportStatus> getExportStatusList(String report) throws Exception {
        ArrayList<ReportStatus> reports = new ArrayList<>();
        JSONArray jsonReports = getExportStatusJSON(report).getJSONArray("result");
        for (int j = 0; j < jsonReports.length(); j++)
            reports.add(new ReportStatus(jsonReports.getJSONObject(j)));
        return reports;
    }

    // TODO: 30/07/2022 INSERT CREATE TICKET IF DOES NOT WORK INTO DOCU STRING IF THIS METHOD DOES NOT WORK
    public File retrieveDataExport(long id, String reportName) throws Exception {
        Params params = new Params();
        params.addParam("id", id);
        JSONArray chunks = new JSONArray(sendPostRequest(RETRIEVE_EXPORT_ENDPOINT, params));
        StringBuilder chunksBuilder = new StringBuilder();
        for (int j = 0; j < chunks.length(); j++)
            chunksBuilder.append(chunks.getString(j));
        if(!reportName.contains(".zip"))
            reportName += ".zip";
        File fileToZip = new File(reportName);
        FileWriter fileWriter = new FileWriter(fileToZip.getName());
        fileWriter.write(chunksBuilder.toString());
        fileWriter.flush();
        fileWriter.close();
        ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(reportName));
        outputStream.putNextEntry(new ZipEntry(reportName));
        FileInputStream inputStream = new FileInputStream(fileToZip);
        int binary;
        while ((binary = inputStream.read()) != -1)
            outputStream.write(binary);
        inputStream.close();
        outputStream.close();
        return fileToZip;
    }

    public String deleteExportReport(String id, String type) throws Exception {
        Params params = new Params();
        params.addParam("id", id);
        params.addParam("type", type);
        return sendPostRequest(DELETE_EXPORT_ENDPOINT, params);
    }

    public JSONObject deleteExportReportJSON(String id, String type) throws Exception {
        return new JSONObject(deleteExportReport(id, type));
    }

    public boolean getDeleteExportReportConfirm(String id, String type) throws Exception {
        try {
            return deleteExportReportJSON(id, type).getJSONObject("result").getBoolean(type);
        }catch (JSONException e){
            return false;
        }
    }

}
