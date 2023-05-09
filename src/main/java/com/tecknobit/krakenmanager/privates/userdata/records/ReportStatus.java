package com.tecknobit.krakenmanager.privates.userdata.records;

import com.tecknobit.krakenmanager.KrakenManager;
import org.json.JSONObject;

/**
 * The {@code ReportStatus} class is useful to format report object
 * @apiNote see the official documentation at:
 * <ul>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
 *           Request Export Report</a>
 *    </li>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus">
 *           Get Export Report Status</a>
 *    </li>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/retrieveExport">
 *           Retrieve Data Export</a>
 *    </li>
 *    <li>
 *        <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport">
 *           Delete Export Report</a>
 *    </li>
 * </ul>
 * @author N7ghtm4r3 - Tecknobit
 * **/
public class ReportStatus extends KrakenManager.KrakenResponse {

    /**
     * {@code TRADES_REPORT} is constant for trades report type
     * **/
    public static final String TRADES_REPORT = "trades";

    /**
     * {@code LEDGERS_REPORT} is constant for ledgers report type
     * **/
    public static final String LEDGERS_REPORT = "ledgers";

    /**
     * {@code ALL_FIELDS} is constant for all fields type
     * **/
    public static final String ALL_FIELDS = "all";

    /**
     * {@code TRADES_FIELDS} is constant for trades fields type
     * **/
    public static final String TRADES_FIELDS = TRADES_REPORT;

    /**
     * {@code LEDGERS_FIELDS} is constant for ledgers fields type
     * **/
    public static final String LEDGERS_FIELDS = LEDGERS_REPORT;

    /**
     * {@code format} is instance that memorizes format value
     **/
    private final ReportFormat format;
    /**
     * {@code createTimestamp} is instance that memorizes {@code "UNIX"} timestamp of report request
     **/
    private final long createTimestamp;
    /**
     * {@code startTimestamp} is instance that memorizes {@code "UNIX"} timestamp report processing began
     **/
    private final long startTimestamp;

    /**
     * {@code reportId} is instance that memorizes report identifier value
     **/
    private final String reportId;

    /**
     * {@code description} is instance that memorizes description value
     **/
    private final String description;
    /**
     * {@code completedTimestamp} is instance that memorizes {@code "UNIX"} timestamp report processing finished
     **/
    private final long completedTimestamp;

    /**
     * {@code report} is instance that memorizes report value
     * **/
    private final String report;

    /**
     * {@code subtype} is instance that memorizes subtype value
     * **/
    private final String subtype;

    /**
     * {@code status} is instance that memorizes status of the report
     * **/
    private final String status;

    /**
     * {@code fields} is instance that memorizes fields value
     * **/
    private final String fields;
    /**
     * {@code dataStartTimestamp} is instance that memorizes {@code "UNIX"} timestamp of the report data start time
     * **/
    private final long dataStartTimestamp;
    /**
     * {@code dataEndTimestamp} is instance that memorizes {@code "UNIX"} timestamp of the report data end time
     * **/
    private final long dataEndTimestamp;

    /** Constructor to init a {@link ReportStatus} object
     * @param reportId: report identifier value
     * @param description: description value
     * @param format: format value
     * @param report: report value
     * @param subtype: subtype value
     * @param status: status of the report
     * @param fields: fields value
     * @param createTimestamp:  {@code "UNIX"} timestamp of report request
     * @param startTimestamp: {@code "UNIX"} timestamp report processing began
     * @param completedTimestamp: {@code "UNIX"} timestamp report processing finished
     * @param dataStartTimestamp: {@code "UNIX"} timestamp of the report data start time
     * @param dataEndTimestamp: {@code "UNIX"} timestamp of the report data end time
     * @param asset: asset value
     **/
    public ReportStatus(String reportId, String description, ReportFormat format, String report, String subtype,
                        String status, String fields, long createTimestamp, long startTimestamp, long completedTimestamp,
                        long dataStartTimestamp, long dataEndTimestamp, String asset) {
        super(null);
        this.reportId = reportId;
        this.description = description;
        this.format = format;
        this.report = report;
        this.subtype = subtype;
        this.status = status;
        this.fields = fields;
        this.createTimestamp = createTimestamp;
        this.startTimestamp = startTimestamp;
        this.completedTimestamp = completedTimestamp;
        this.dataStartTimestamp = dataStartTimestamp;
        this.dataEndTimestamp = dataEndTimestamp;
        this.asset = asset;
    }

    /** Constructor to init a {@link ReportStatus} object
     * @param jsonResponse: base json response
     **/
    public ReportStatus(JSONObject jsonResponse) {
        super(jsonResponse);
        reportId = result.getString("id");
        description = result.getString("descr");
        format = ReportFormat.valueOf(result.getString("format", ReportFormat.CSV.name()));
        report = result.getString("report");
        subtype = result.getString("subtype");
        status = result.getString("status");
        fields = result.getString("fields");
        createTimestamp = result.getLong("createdtm", 0);
        startTimestamp = result.getLong("starttm", 0);
        completedTimestamp = result.getLong("completedtm", 0);
        dataStartTimestamp = result.getLong("datastarttm", 0);
        dataEndTimestamp = result.getLong("dataendtm", 0);
        asset = result.getString("asset");
    }

    /**
     * Method to get {@link #format} instance <br>
     * No-any params required
     *
     * @return {@link #format} instance as {@link String}
     **/
    public ReportFormat getFormat() {
        return format;
    }

    /**
     * {@code asset} is instance that memorizes asset value
     **/
    private final String asset;

    /**
     * {@code "DeletionType"} list of deletion type for a report
     **/
    public enum DeletionType {

        /**
         * {@code "cancel"} can only be used for queued or processing reports
         **/
        cancel,

        /**
         * {@code "delete"} can only be used for reports that have already been processed
         **/
        delete

    }

    /**
     * {@code ReportType} list for report types
     **/
    public enum ReportType {

        /**
         * {@code "trades"} report type
         **/
        trades,

        /**
         * {@code "ledgers"} report type
         **/
        ledgers

    }

    /**
     * Method to get {@link #reportId} instance <br>
     * No-any params required
     *
     * @return {@link #reportId} instance as {@link String}
     **/
    public String getReportId() {
        return reportId;
    }

    /**
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * {@code ReportFormat} list for format types
     * **/
    public enum ReportFormat {

        /**
         * {@code "CSV"} format type
         **/
        CSV,

        /**
         * {@code "TSV"} format type
         **/
        TSV

    }

    /**
     * Method to get {@link #report} instance <br>
     * No-any params required
     *
     * @return {@link #report} instance as {@link String}
     **/
    public String getReport() {
        return report;
    }

    /**
     * Method to get {@link #subtype} instance <br>
     * No-any params required
     *
     * @return {@link #subtype} instance as {@link String}
     **/
    public String getSubtype() {
        return subtype;
    }

    /**
     * Method to get {@link #status} instance <br>
     * No-any params required
     *
     * @return {@link #status} instance as {@link String}
     **/
    public String getStatus() {
        return status;
    }

    /**
     * Method to get {@link #fields} instance <br>
     * No-any params required
     *
     * @return {@link #fields} instance as {@link String}
     **/
    public String getFields() {
        return fields;
    }

    /**
     * Method to get {@link #createTimestamp} instance <br>
     * No-any params required
     *
     * @return {@link #createTimestamp} instance as long
     **/
    public long getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * Method to get {@link #startTimestamp} instance <br>
     * No-any params required
     *
     * @return {@link #startTimestamp} instance as long
     **/
    public long getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * Method to get {@link #completedTimestamp} instance <br>
     * No-any params required
     *
     * @return {@link #completedTimestamp} instance as long
     **/
    public long getCompletedTimestamp() {
        return completedTimestamp;
    }

    /**
     * Method to get {@link #dataStartTimestamp} instance <br>
     * No-any params required
     *
     * @return {@link #dataStartTimestamp} instance as long
     **/
    public long getDataStartTimestamp() {
        return dataStartTimestamp;
    }

    /**
     * Method to get {@link #dataEndTimestamp} instance <br>
     * No-any params required
     *
     * @return {@link #dataEndTimestamp} instance as long
     **/
    public long getDataEndTimestamp() {
        return dataEndTimestamp;
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

}
