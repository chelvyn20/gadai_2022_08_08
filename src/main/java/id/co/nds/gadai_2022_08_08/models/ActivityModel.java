package id.co.nds.gadai_2022_08_08.models;

import java.sql.Timestamp;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.Ansi.Text;

public class ActivityModel {
    private Timestamp logTime;
    private String logBy;
    private String logType;
    private String logModule;
    private String logDesc;
    private Text dataBefore;
    private Text dataAfter;
    
    public Timestamp getLogTime() {
        return logTime;
    }
    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }
    public String getLogBy() {
        return logBy;
    }
    public void setLogBy(String logBy) {
        this.logBy = logBy;
    }
    public String getLogType() {
        return logType;
    }
    public void setLogType(String logType) {
        this.logType = logType;
    }
    public String getLogModule() {
        return logModule;
    }
    public void setLogModule(String logModule) {
        this.logModule = logModule;
    }
    public String getLogDesc() {
        return logDesc;
    }
    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc;
    }
    public Text getDataBefore() {
        return dataBefore;
    }
    public void setDataBefore(Text dataBefore) {
        this.dataBefore = dataBefore;
    }
    public Text getDataAfter() {
        return dataAfter;
    }
    public void setDataAfter(Text dataAfter) {
        this.dataAfter = dataAfter;
    }
}
