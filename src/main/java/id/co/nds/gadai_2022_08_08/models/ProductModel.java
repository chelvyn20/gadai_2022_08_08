package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.UpdatingById;

public class ProductModel extends RecordModel {
    private String productId;

    // @Pattern(regexp = "^Konsinyasi Cicilan Tetap$|^Konsinyasi Cicilan
    // Fleksibel$", message = "dont input other", groups = {
    // PostingNew.class, GettingById.class, UpdatingById.class })
    private String productType;

    private String productName;

    // @Pattern(regexp = "^[a-zA-Z0-9]{1}.{0,254}$", groups = { PostingNew.class,
    // UpdatingById.class })
    private String productDesc;

    private Integer productJangkaWaktu;
    private BigDecimal productLtv;
    private BigDecimal productLtvBefore;
    private BigDecimal productLtvAfter;
    private BigDecimal productBiayaAdminBuka;
    private BigDecimal productBiayaAdminTutup;

    // @NotBlank(groups = { PostingNew.class,
    // UpdatingById.class })
    // @Pattern(regexp = "^PERSEN$|^NOMINAL$", message = "dont input other", groups
    // = { PostingNew.class,
    // UpdatingById.class })
    private String productBiayaAdminBukaTipe;

    // @NotBlank(groups = { PostingNew.class,
    // UpdatingById.class })
    // @Pattern(regexp = "^PERSEN$|^NOMINAL$", message = "dont input other", groups
    // = { PostingNew.class,
    // UpdatingById.class })
    private String productBiayaAdminTutupTipe;

    @Digits(integer = 3, fraction = 2, groups = { PostingNew.class, UpdatingById.class })
    private BigDecimal productBiayaJasaPeny;

    @Digits(integer = 3, fraction = 2, groups = { PostingNew.class, UpdatingById.class })
    private BigDecimal productBiayaJasaPenyBefore;

    @Digits(integer = 3, fraction = 2, groups = { PostingNew.class, UpdatingById.class })
    private BigDecimal productBiayaJasaPenyAfter;

    @Range(min = 1, max = 999, groups = { PostingNew.class, UpdatingById.class })
    private Integer productBiayaJasaPenyPeriode;
    private BigDecimal productBiayaDenda;

    @Range(min = 1, max = 999, groups = { PostingNew.class, UpdatingById.class })
    private Integer productBiayaDendaPeriode;

    private String actorId;
    private String productStatus;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductJangkaWaktu() {
        return productJangkaWaktu;
    }

    public void setProductJangkaWaktu(Integer productJangkaWaktu) {
        this.productJangkaWaktu = productJangkaWaktu;
    }

    public BigDecimal getProductLtv() {
        return productLtv;
    }

    public void setProductLtv(BigDecimal productLtv) {
        this.productLtv = productLtv;
    }

    public BigDecimal getProductBiayaAdminBuka() {
        return productBiayaAdminBuka;
    }

    public void setProductBiayaAdminBuka(BigDecimal productBiayaAdminBuka) {
        this.productBiayaAdminBuka = productBiayaAdminBuka;
    }

    public BigDecimal getProductBiayaAdminTutup() {
        return productBiayaAdminTutup;
    }

    public void setProductBiayaAdminTutup(BigDecimal productBiayaAdminTutup) {
        this.productBiayaAdminTutup = productBiayaAdminTutup;
    }

    public String getProductBiayaAdminBukaTipe() {
        return productBiayaAdminBukaTipe;
    }

    public void setProductBiayaAdminBukaTipe(String productBiayaAdminBukaTipe) {
        this.productBiayaAdminBukaTipe = productBiayaAdminBukaTipe;
    }

    public String getProductBiayaAdminTutupTipe() {
        return productBiayaAdminTutupTipe;
    }

    public void setProductBiayaAdminTutupTipe(String productBiayaAdminTutupTipe) {
        this.productBiayaAdminTutupTipe = productBiayaAdminTutupTipe;
    }

    public BigDecimal getProductBiayaJasaPeny() {
        return productBiayaJasaPeny;
    }

    public void setProductBiayaJasaPeny(BigDecimal productBiayaJasaPeny) {
        this.productBiayaJasaPeny = productBiayaJasaPeny;
    }

    public Integer getProductBiayaJasaPenyPeriode() {
        return productBiayaJasaPenyPeriode;
    }

    public void setProductBiayaJasaPenyPeriode(Integer productBiayaJasaPenyPeriode) {
        this.productBiayaJasaPenyPeriode = productBiayaJasaPenyPeriode;
    }

    public BigDecimal getProductBiayaDenda() {
        return productBiayaDenda;
    }

    public void setProductBiayaDenda(BigDecimal productBiayaDenda) {
        this.productBiayaDenda = productBiayaDenda;
    }

    public Integer getProductBiayaDendaperiode() {
        return productBiayaDendaPeriode;
    }

    public void setProductBiayaDendaperiode(Integer productBiayaDendaperiode) {
        this.productBiayaDendaPeriode = productBiayaDendaperiode;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public BigDecimal getProductLtvBefore() {
        return productLtvBefore;
    }

    public void setProductLtvBefore(BigDecimal productLtvBefore) {
        this.productLtvBefore = productLtvBefore;
    }

    public BigDecimal getProductLtvAfter() {
        return productLtvAfter;
    }

    public void setProductLtvAfter(BigDecimal productLtvAfter) {
        this.productLtvAfter = productLtvAfter;
    }

    public BigDecimal getProductBiayaJasaPenyBefore() {
        return productBiayaJasaPenyBefore;
    }

    public void setProductBiayaJasaPenyBefore(BigDecimal productBiayaJasaPenyBefore) {
        this.productBiayaJasaPenyBefore = productBiayaJasaPenyBefore;
    }

    public BigDecimal getProductBiayaJasaPenyAfter() {
        return productBiayaJasaPenyAfter;
    }

    public void setProductBiayaJasaPenyAfter(BigDecimal productBiayaJasaPenyAfter) {
        this.productBiayaJasaPenyAfter = productBiayaJasaPenyAfter;
    }
}
