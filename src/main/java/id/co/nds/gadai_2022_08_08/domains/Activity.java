package id.co.nds.gadai_2022_08_08.domains;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class Activity implements Serializable{
    @Column(name = "log_time")
    private Timestamp logTime;
    
    @Column(name = "log_by")
    private String logBy;

    
    public Activity(Timestamp time, String logBy) {
        this.logTime = time;
        this.logBy = logBy;
    }

    public Activity() {

    }

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
}
