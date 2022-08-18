package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.UpdatingById;

public class CustomerModel extends RecordModel {
    @NotBlank(message = "Customer ID is required",
            groups = {GettingById.class, UpdatingById.class, DeletingById.class})
    @Pattern(regexp = "^[0-9]{10}$", message = "Customer ID has 10 digits of number",
            groups = {GettingById.class, UpdatingById.class, DeletingById.class, GettingAllByCriteria.class})
    @Null(message = "Customer ID is auto generated, do not input ID",
            groups = {PostingNew.class})
    private String custId;

    @NotBlank(message = "Customer name is required",
            groups = {PostingNew.class})
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,30}$", message = "Customer name can't be empty, and has a maximum length of 30 characters",
            groups = {UpdatingById.class, GettingAllByCriteria.class})
    private String custName;

    @NotBlank(message = "Customer KTP is required",
            groups = {PostingNew.class})
    @Pattern(regexp = "^[0-9]{16}$", message = "Customer KTP has 16 digits of number",
            groups = {PostingNew.class, UpdatingById.class, GettingAllByCriteria.class})
    private String custKtp;

    @Pattern(regexp = "^08[0-9]{7,10}$", message = "Customer phone number starts with 08 followed by 7 to 10 digits of number",
            groups = {PostingNew.class, UpdatingById.class, GettingAllByCriteria.class})
    private String custHp;

    @NotBlank(message = "Customer gender is required",
            groups = {PostingNew.class})
    @Pattern(regexp = "^[pPwW]$", message = "Customer gender is either P or W",
            groups = {PostingNew.class, UpdatingById.class, GettingAllByCriteria.class})
    private String custJk;

    @NotBlank(message = "Customer jenis usaha is required",
            groups = {PostingNew.class})
    private String custJenisUsahaId;

    @NotNull(message = "Customer transaction limit is required",
            groups = {PostingNew.class})
    @Range(min = 1000000, max = 3000000000L, message = "Customer transaction limit is between 1.000.000 to 3.000.000.000",
            groups = {PostingNew.class, UpdatingById.class})
    private BigDecimal custLimitTxn;

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

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }
}
