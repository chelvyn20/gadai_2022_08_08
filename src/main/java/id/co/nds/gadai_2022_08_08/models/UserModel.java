package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.UpdatingById;

public class UserModel extends RecordModel {
    @NotBlank(message = "User ID is required",
        groups = {PostingNew.class, GettingById.class, UpdatingById.class, DeletingById.class})
    @Pattern(regexp = "^[a-zA-Z0-9]{1,15}$", message = "User ID has a maximum of 15 digits, alphanumerics only",
        groups = {PostingNew.class, GettingAllByCriteria.class, GettingById.class, UpdatingById.class, DeletingById.class})
    private String userId;

    @NotBlank(message = "User name is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9 ]{0,29}$", message = "User name can't be empty, and has a maximum length of 30 characters",
        groups = {PostingNew.class, UpdatingById.class, GettingAllByCriteria.class})
    private String userName;

    @NotBlank(message = "User phone number is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^08[0-9]{7,10}$", message = "User phone number starts with 08 followed by 7 to 10 digits of number",
        groups = {PostingNew.class, UpdatingById.class, GettingAllByCriteria.class})
    private String userHp;

    @Pattern(regexp = "^[a-zA-Z0-9]{1}.{0,49}$", message = "User description can't start with whitespace and has a maximum of 50 digits, alphanumeric",
        groups = {PostingNew.class, UpdatingById.class, GettingAllByCriteria.class})
    private String userDesc;

    private String userStatus;

    @NotNull(message = "User transaction limit is required",
        groups = {PostingNew.class})
    @Range(min = 500000, max = 1000000000L, message = "User transaction limit is between 500.000 to 1.000.000.000",
        groups = {PostingNew.class, UpdatingById.class})
    private BigDecimal userTxnLimit;
    
    @NotBlank(message = "User registration date is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^[0-9]{4}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$", message = "User registration date format is yyyyMMdd",
        groups = {PostingNew.class, UpdatingById.class})
    private String userRegDate;

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

    public String getUserHp() {
        return userHp;
    }

    public void setUserHp(String userHp) {
        this.userHp = userHp;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public BigDecimal getUserTxnLimit() {
        return userTxnLimit;
    }

    public void setUserTxnLimit(BigDecimal userTxnLimit) {
        this.userTxnLimit = userTxnLimit;
    }

    public String getUserRegDate() {
        return userRegDate;
    }

    public void setUserRegDate(String userRegDate) {
        this.userRegDate = userRegDate;
    }

}
