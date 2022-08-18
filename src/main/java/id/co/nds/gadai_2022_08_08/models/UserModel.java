package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;
import java.sql.Date;

public class UserModel extends RecordModel{
    private String userId;
    private String userName;
    private String userNoHP;
    private String userDesc;
    private String actorId;
    private String userStatus;
    private BigDecimal userTxnLimit;
    private Date userRegData;
    
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
    public String getUserNoHP() {
        return userNoHP;
    }
    public void setUserNoHP(String userNoHP) {
        this.userNoHP = userNoHP;
    }
    public String getUserDesc() {
        return userDesc;
    }
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }
    public String getActorId() {
        return actorId;
    }
    public void setActorId(String actorId) {
        this.actorId = actorId;
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
    public Date getUserRegData() {
        return userRegData;
    }
    public void setUserRegData(Date userRegData) {
        this.userRegData = userRegData;
    }

   
}
