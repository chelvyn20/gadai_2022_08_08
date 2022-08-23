package id.co.nds.gadai_2022_08_08.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ms_customer")
public class CustomerEntity {
    @Id
    @GenericGenerator(name = "customer_id_seq", strategy = "id.co.nds.gadai_2022_08_08.generators.CustomerIdGenerator")
    @GeneratedValue(generator = "customer_id_seq")

    // @OneToMany(targetEntity = CicTetapEntity.class, mappedBy = "customer")  
    
    @JoinColumn (name = "customer_id" , referencedColumnName = "customer_id")
    // private CicTetapEntity cicil;

    @Column(name = "customer_id")
    private String custId;

    @Column(name = "customer_name")
    private String custName;

    @Column(name = "customer_identity_no")
    private String custKtp;

    @Column(name = "customer_phone")
    private String custHp;

    @Column(name = "customer_gender")
    private String custJk;

    @Column(name = "customer_jenis_usaha")
    private String custJenisUsahaId;

    @Column(name = "customer_max_limit")
    private BigDecimal custLimitTxn;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "rec_status")
    private String custStatus;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustKtp() {
        return custKtp;
    }

    public void setCustKtp(String custKtp) {
        this.custKtp = custKtp;
    }

    public String getCustHp() {
        return custHp;
    }

    public void setCustHp(String custHp) {
        this.custHp = custHp;
    }

    public String getCustJk() {
        return custJk;
    }

    public void setCustJk(String custJk) {
        this.custJk = custJk;
    }

    public String getCustJenisUsahaId() {
        return custJenisUsahaId;
    }

    public void setCustJenisUsahaId(String custJenisUsahaId) {
        this.custJenisUsahaId = custJenisUsahaId;
    }

    public BigDecimal getCustLimitTxn() {
        return custLimitTxn;
    }

    public void setCustLimitTxn(BigDecimal custLimitTxn) {
        this.custLimitTxn = custLimitTxn;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    public CustomerEntity(String custId, String custKTP, String custHp, String custName){
        this.custId = custId;
        this.custKtp = custKTP;
        this.custHp = custHp;
        this.custName = custName;
    }

    public CustomerEntity() {
    }
}
