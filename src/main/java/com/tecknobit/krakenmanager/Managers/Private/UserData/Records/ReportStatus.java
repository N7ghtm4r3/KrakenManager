package com.tecknobit.krakenmanager.Managers.Private.UserData.Records;

import com.tecknobit.krakenmanager.Managers.KrakenManager;
import org.json.JSONObject;

import java.util.Arrays;

public class ReportStatus extends KrakenManager.KrakenResponse {

    public static final String TRADES_REPORT = "trades";
    public static final String LEDGERS_REPORT = "ledgers";
    public static final String ALL_FIELDS = "all";
    public static final String TRADES_FIELDS = TRADES_REPORT;
    public static final String LEDGERS_FIELDS = LEDGERS_REPORT;
    public static final String CANCEL_DELETION_TYPE = "cancel";
    public static final String DELETE_DELETION_TYPE = "delete";

    private final String reportId;
    private final String description;
    private final String format;
    private final String report;
    private final String subtype;
    private final String status;
    private final String fields;
    private final long createTimestamp;
    private final long startTimestamp;
    private final long completedTimestamp;
    private final long dataStartTimestamp;
    private final long dataEndTimestamp;
    private final String asset;

    public ReportStatus(JSONObject jsonResponse, String reportId, String description, String format, String report,
                        String subtype, String status, String fields, long createTimestamp, long startTimestamp, long completedTimestamp,
                        long dataStartTimestamp, long dataEndTimestamp, String asset) {
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

    public ReportStatus(String reportId, String description, String format, String report, String subtype, String status,
                        String fields, long createTimestamp, long startTimestamp, long completedTimestamp, long dataStartTimestamp,
                        long dataEndTimestamp, String asset) {
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

    /**
     * Constructor to init a {@link ReportStatus}
     * @param jsonResponse : base json response
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
                ", jsonResponse=" + jsonResponse +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }

}
