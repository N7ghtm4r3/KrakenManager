package com.tecknobit.krakenmanager.Managers.Private.UserData.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * The {@code ReportStatus} class is useful to format report object
 * @apiNote see official documentation at:
 <ul>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/addExport">
            https://docs.kraken.com/rest/#tag/User-Data/operation/addExport</a>
     </li>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus">
            https://docs.kraken.com/rest/#tag/User-Data/operation/exportStatus</a>
     </li>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/retrieveExport">
            https://docs.kraken.com/rest/#tag/User-Data/operation/retrieveExport</a>
     </li>
     <li>
         <a href="https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport">
            https://docs.kraken.com/rest/#tag/User-Data/operation/removeExport</a>
     </li>
 </ul>
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
     * {@code CANCEL_DELETION_TYPE} is constant for cancel deletion type
     * **/
    public static final String CANCEL_DELETION_TYPE = "cancel";

    /**
     * {@code DELETE_DELETION_TYPE} is constant for cancel delete type
     * **/
    public static final String DELETE_DELETION_TYPE = "delete";

    /**
     * {@code CSV_FORMAT_TYPE} is constant for csv format type
     * **/
    public static final String CSV_FORMAT_TYPE = "CSV";

    /**
     * {@code TSV_FORMAT_TYPE} is constant for tsv format type
     * **/
    public static final String TSV_FORMAT_TYPE = "TSV";

    /**
     * {@code reportId} is instance that memorizes report identifier value
     * **/
    private final String reportId;

    /**
     * {@code description} is instance that memorizes description value
     * **/
    private final String description;

    /**
     * {@code format} is instance that memorizes format value
     * **/
    private final String format;

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
     * {@code createTimestamp} is instance that memorizes UNIX timestamp of report request
     * **/
    private final long createTimestamp;

    /**
     * {@code startTimestamp} is instance that memorizes UNIX timestamp report processing began
     * **/
    private final long startTimestamp;

    /**
     * {@code completedTimestamp} is instance that memorizes UNIX timestamp report processing finished
     * **/
    private final long completedTimestamp;

    /**
     * {@code dataStartTimestamp} is instance that memorizes UNIX timestamp of the report data start time
     * **/
    private final long dataStartTimestamp;

    /**
     * {@code dataEndTimestamp} is instance that memorizes UNIX timestamp of the report data end time
     * **/
    private final long dataEndTimestamp;

    /**
     * {@code asset} is instance that memorizes asset value
     * **/
    private final String asset;

    /** Constructor to init a {@link ReportStatus} object
     * @param jsonResponse: base json response
     * @param reportId: report identifier value
     * @param description: description value
     * @param format: format value
     * @param report: report value
     * @param subtype: subtype value
     * @param status: status of the report
     * @param fields: fields value
     * @param createTimestamp:  UNIX timestamp of report request
     * @param startTimestamp: UNIX timestamp report processing began
     * @param completedTimestamp: UNIX timestamp report processing finished
     * @param dataStartTimestamp: UNIX timestamp of the report data start time
     * @param dataEndTimestamp: UNIX timestamp of the report data end time
     * @param asset: asset value
     **/
    public ReportStatus(JSONObject jsonResponse, String reportId, String description, String format, String report,
                        String subtype, String status, String fields, long createTimestamp, long startTimestamp,
                        long completedTimestamp, long dataStartTimestamp, long dataEndTimestamp, String asset) {
        super(jsonResponse);
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
     * @param reportId: report identifier value
     * @param description: description value
     * @param format: format value
     * @param report: report value
     * @param subtype: subtype value
     * @param status: status of the report
     * @param fields: fields value
     * @param createTimestamp:  UNIX timestamp of report request
     * @param startTimestamp: UNIX timestamp report processing began
     * @param completedTimestamp: UNIX timestamp report processing finished
     * @param dataStartTimestamp: UNIX timestamp of the report data start time
     * @param dataEndTimestamp: UNIX timestamp of the report data end time
     * @param asset: asset value
     **/
    public ReportStatus(String reportId, String description, String format, String report, String subtype, String status,
                        String fields, long createTimestamp, long startTimestamp, long completedTimestamp,
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
        reportId = jsonResponse.getString("id");
        description = jsonResponse.getString("descr");
        format = jsonResponse.getString("format");
        report = jsonResponse.getString("report");
        subtype = jsonResponse.getString("subtype");
        status = jsonResponse.getString("status");
        fields = jsonResponse.getString("fields");
        createTimestamp = jsonResponse.getLong("createdtm");
        startTimestamp = jsonResponse.getLong("starttm");
        completedTimestamp = jsonResponse.getLong("completedtm");
        dataStartTimestamp = jsonResponse.getLong("datastarttm");
        dataEndTimestamp = jsonResponse.getLong("dataendtm");
        asset = jsonResponse.getString("asset");
    }

    public String getReportId() {
        return reportId;
    }

    public String getDescription() {
        return description;
    }

    public String getFormat() {
        return format;
    }

    public String getReport() {
        return report;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getStatus() {
        return status;
    }

    public String getFields() {
        return fields;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public long getCompletedTimestamp() {
        return completedTimestamp;
    }

    public long getDataStartTimestamp() {
        return dataStartTimestamp;
    }

    public long getDataEndTimestamp() {
        return dataEndTimestamp;
    }

    public String getAsset() {
        return asset;
    }

    @Override
    public String toString() {
        return "ReportStatus{" +
                "reportId='" + reportId + '\'' +
                ", description='" + description + '\'' +
                ", format='" + format + '\'' +
                ", report='" + report + '\'' +
                ", subtype='" + subtype + '\'' +
                ", status='" + status + '\'' +
                ", fields='" + fields + '\'' +
                ", createTimestamp=" + createTimestamp +
                ", startTimestamp=" + startTimestamp +
                ", completedTimestamp=" + completedTimestamp +
                ", dataStartTimestamp=" + dataStartTimestamp +
                ", dataEndTimestamp=" + dataEndTimestamp +
                ", asset='" + asset + '\'' +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
