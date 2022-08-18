package id.co.nds.gadai_2022_08_08.entities;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.UpdatingById;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.deletingById;

@Entity
@Table(name="ms_user")
public class UserEntity {
    @Id
    @GenericGenerator(name = "user_id_seq", strategy = "id.co.nds.gadai_2022_08_08.generators.UserGenerator")
    @GeneratedValue(generator = "user_id_seq")

    @NotBlank(message = "user id is required", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class, deletingById.class})
    @Column(name = "user_id")
    private String userId;

    @NotBlank(message = "user name is required", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class})
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "user phone is required", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class})
    @Column(name = "user_phone")
    private String userPhone;

    @NotBlank(message = "user notes is required", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class})
    @Column(name = "user_notes")
    private String userNotes;

    @Column(name = "max_limit")
    private Double maxLimit;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    @Column(name = "deleted_By")
    private String deletedBy;

    @Column(name = "rec_status")
    private String recStatus;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }

    public Double getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Double maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }
}
