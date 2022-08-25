package id.co.nds.gadai_2022_08_08.entities;

import java.io.Serializable;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "lg_activities")
public class ActivityEntity implements Serializable {
    @Id
    // @GenericGenerator(name = "activiy_id_seq", strategy =
    // "id.co.nds.catalogue.generators.ActivityGenerator")
    // @GeneratedValue(generator = "activity_id_seq")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "log_by")
    private String logBy;

    @Column(name = "log_time")
    private Date logTime;

    @Column(name = "log_type")
    private String logType;

    @Column(name = "log_module")
    private String logModule;

    @Column(name = "log_description")
    private String logDesc;

    @Column(name = "data_before")
    private String dataBefore;

    @Column(name = "data_after")
    private String dataAfter;

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
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

    public String getDataBefore() {
        return dataBefore;
    }

    public void setDataBefore(String dataBefore) {
        this.dataBefore = dataBefore;
    }

    public String getDataAfter() {
        return dataAfter;
    }

    public void setDataAfter(String dataAfter) {
        this.dataAfter = dataAfter;
    }

}
