package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import org.hibernate.validator.constraints.Range;

import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.UpdatingById;

public class ProductModel extends RecordModel {
    @NotBlank(message = "Product ID is required",
        groups = {PostingNew.class, GettingById.class, UpdatingById.class, DeletingById.class})
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "Product ID has a maximum of 20 digits, alphanumeric only",
        groups = {PostingNew.class, GettingById.class, UpdatingById.class, DeletingById.class, GettingAllByCriteria.class})
    private String productId;

    @NotBlank(message = "Product type is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z ]{0,}$", message = "Product type can't be empty string",
        groups = {GettingAllByCriteria.class})
    @Pattern(regexp = "^Konsinyasi Cicilan Tetap$|^Konsinyasi Cicilan Fleksibel$", message = "Product type can only be 'Konsinyasi Cicilan Tetap' or 'Konsinyasi Cicilan Fleksibel', case sensitive",
        groups = {PostingNew.class})
    private String productTipe;

    @NotBlank(message = "Product name is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^[a-zA-Z0-9]{1}[a-zA-Z0-9 ]{0,49}$", message = "Product name can't start with whitespace and has a maximum of 50 digits, alphanumeric",
        groups = {PostingNew.class, UpdatingById.class, GettingAllByCriteria.class})
    private String productName;

    @Pattern(regexp = "^[a-zA-Z0-9]{1}.{0,254}$", message = "Product description can't start with whitespace and has a maximum of 255 digits, alphanumeric",
        groups = {PostingNew.class, UpdatingById.class})
    private String productDesc;

    @NotNull(message = "Product LTV is required",
        groups = {PostingNew.class})
    @DecimalMin(value = "1", message = "Product LTV has a minimum value of 1",
        groups = {PostingNew.class, UpdatingById.class})
    @DecimalMax(value = "100", message = "Product LTV has a maximum value of 100",
        groups = {PostingNew.class, UpdatingById.class})
    @Digits(integer = 3, fraction = 2, message = "Product LTV only has 2 digits of fraction number",
        groups = {PostingNew.class, UpdatingById.class})
    private BigDecimal productLtv;

    @DecimalMin(value = "1", message = "Product LTV before has a minimum value of 1",
        groups = {GettingAllByCriteria.class})
    @DecimalMax(value = "100", message = "Product LTV before has a maximum value of 100",
        groups = {GettingAllByCriteria.class})
    @Digits(integer = 3, fraction = 2, message = "Product LTV before only has 2 digits of fraction number",
        groups = {GettingAllByCriteria.class})
    private BigDecimal productLtvBefore;

    @DecimalMin(value = "1", message = "Product LTV after has a minimum value of 1",
        groups = {GettingAllByCriteria.class})
    @DecimalMax(value = "100", message = "Product LTV after has a maximum value of 100",
        groups = {GettingAllByCriteria.class})
    @Digits(integer = 3, fraction = 2, message = "Product LTV after only has 2 digits of fraction number",
        groups = {GettingAllByCriteria.class})
    private BigDecimal productLtvAfter;

    @NotNull(message = "Product tenor is required",
        groups = {PostingNew.class})
    @Range(min = 1, max = 999, message = "Product tenor has a minimum value of 1, and only 3 digits of numbers allowed",
        groups = {PostingNew.class, UpdatingById.class})
    private Integer productJangkaWaktu;

    @NotBlank(message = "Product tipe biaya admin buka is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^persen$|^nominal$", flags = Flag.CASE_INSENSITIVE, message = "Product tipe biaya admin buka can only be 'PERSEN' or 'NOMINAL'",
        groups = {PostingNew.class, UpdatingById.class})
    private String productBiayaAdminBukaTipe;

    @NotNull(message = "Product biaya admin buka is required",
        groups = {PostingNew.class})
    @DecimalMin(value = "1", message = "Product biaya admin buka has a minimum value of 1",
        groups = {PostingNew.class, UpdatingById.class})
    @DecimalMax(value = "999.99", message = "Product biaya admin buka has a maximum value of 100",
        groups = {PostingNew.class, UpdatingById.class})
    @Digits(integer = 3, fraction = 2, message = "Product biaya admin buka only has 2 digits of fraction number",
        groups = {PostingNew.class, UpdatingById.class})
    private BigDecimal productBiayaAdminBuka;

    @NotBlank(message = "Product tipe biaya admin tutup is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^persen$|^nominal$", flags = Flag.CASE_INSENSITIVE, message = "Product tipe biaya admin tutup can only be 'PERSEN' or 'NOMINAL'",
        groups = {PostingNew.class, UpdatingById.class})
    private String productBiayaAdminTutupTipe;

    @NotNull(message = "Product biaya admin tutup is required",
        groups = {PostingNew.class})
    @DecimalMin(value = "1", message = "Product biaya admin tutup has a minimum value of 1",
        groups = {PostingNew.class, UpdatingById.class})
    @DecimalMax(value = "999.99", message = "Product biaya admin tutup has a maximum value of 100",
        groups = {PostingNew.class, UpdatingById.class})
    @Digits(integer = 3, fraction = 2, message = "Product biaya admin tutup only has 2 digits of fraction number",
        groups = {PostingNew.class, UpdatingById.class})
    private BigDecimal productBiayaAdminTutup;

    @NotNull(message = "Product biaya jasa penyimpanan is required",
        groups = {PostingNew.class})
    @DecimalMin(value = "1", message = "Product biaya jasa penyimpanan has a minimum value of 1",
        groups = {PostingNew.class, UpdatingById.class})
    @DecimalMax(value = "100", message = "Product biaya jasa penyimpanan has a maximum value of 100",
        groups = {PostingNew.class, UpdatingById.class})
    @Digits(integer = 3, fraction = 2, message = "Product biaya jasa penyimpanan only has 2 digits of fraction number",
        groups = {PostingNew.class, UpdatingById.class})
    private BigDecimal productBiayaJasaPeny;

    @DecimalMin(value = "1", message = "Product biaya jasa penyimpanan before has a minimum value of 1",
        groups = {GettingAllByCriteria.class})
    @DecimalMax(value = "100", message = "Product biaya jasa penyimpanan before has a maximum value of 100",
        groups = {GettingAllByCriteria.class})
    @Digits(integer = 3, fraction = 2, message = "Product biaya jasa penyimpanan before only has 2 digits of fraction number",
        groups = {GettingAllByCriteria.class})
    private BigDecimal productBiayaJasaPenyBefore;
    
    @DecimalMin(value = "1", message = "Product biaya jasa penyimpanan after has a minimum value of 1",
        groups = {GettingAllByCriteria.class})
    @DecimalMax(value = "100", message = "Product biaya jasa penyimpanan after has a maximum value of 100",
        groups = {GettingAllByCriteria.class})
    @Digits(integer = 3, fraction = 2, message = "Product biaya jasa penyimpanan after only has 2 digits of fraction number",
        groups = {GettingAllByCriteria.class})
    private BigDecimal productBiayaJasaPenyAfter;

    @NotNull(message = "Product periode biaya jasa penyimpanan is required",
        groups = {PostingNew.class})
    @Range(min = 1, max = 999, message = "Product periode biaya jasa penyimpanan has a minimum value of 1, and only 3 digits of numbers allowed",
        groups = {PostingNew.class, UpdatingById.class})
    private Integer productBiayaJasaPenyPeriode;

    @NotNull(message = "Product biaya denda is required",
        groups = {PostingNew.class})
    @DecimalMin(value = "1", message = "Product biaya denda has a minimum value of 1",
        groups = {PostingNew.class, UpdatingById.class})
    @DecimalMax(value = "100", message = "Product biaya denda has a maximum value of 100",
        groups = {PostingNew.class, UpdatingById.class})
    @Digits(integer = 3, fraction = 2, message = "Product biaya denda only has 2 digits of fraction number",
        groups = {PostingNew.class, UpdatingById.class})
    private BigDecimal productBiayaDenda;

    @NotNull(message = "Product periode biaya denda is required",
        groups = {PostingNew.class})
    @Range(min = 1, max = 999, message = "Product periode biaya denda has a minimum value of 1, and only 3 digits of numbers allowed",
        groups = {PostingNew.class, UpdatingById.class})
    private Integer productBiayaDendaPeriode;

    private String productStatus;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTipe() {
        return productTipe;
    }

    public void setProductTipe(String productTipe) {
        this.productTipe = productTipe;
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

    public BigDecimal getProductLtv() {
        return productLtv;
    }

    public void setProductLtv(BigDecimal productLtv) {
        this.productLtv = productLtv;
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

    public Integer getProductJangkaWaktu() {
        return productJangkaWaktu;
    }

    public void setProductJangkaWaktu(Integer productJangkaWaktu) {
        this.productJangkaWaktu = productJangkaWaktu;
    }

    public String getProductBiayaAdminBukaTipe() {
        return productBiayaAdminBukaTipe;
    }

    public void setProductBiayaAdminBukaTipe(String productBiayaAdminBukaTipe) {
        this.productBiayaAdminBukaTipe = productBiayaAdminBukaTipe;
    }

    public BigDecimal getProductBiayaAdminBuka() {
        return productBiayaAdminBuka;
    }

    public void setProductBiayaAdminBuka(BigDecimal productBiayaAdminBuka) {
        this.productBiayaAdminBuka = productBiayaAdminBuka;
    }

    public String getProductBiayaAdminTutupTipe() {
        return productBiayaAdminTutupTipe;
    }

    public void setProductBiayaAdminTutupTipe(String productBiayaAdminTutupTipe) {
        this.productBiayaAdminTutupTipe = productBiayaAdminTutupTipe;
    }

    public BigDecimal getProductBiayaAdminTutup() {
        return productBiayaAdminTutup;
    }

    public void setProductBiayaAdminTutup(BigDecimal productBiayaAdminTutup) {
        this.productBiayaAdminTutup = productBiayaAdminTutup;
    }

    public BigDecimal getProductBiayaJasaPeny() {
        return productBiayaJasaPeny;
    }

    public void setProductBiayaJasaPeny(BigDecimal productBiayaJasaPeny) {
        this.productBiayaJasaPeny = productBiayaJasaPeny;
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

    public Integer getProductBiayaDendaPeriode() {
        return productBiayaDendaPeriode;
    }

    public void setProductBiayaDendaPeriode(Integer productBiayaDendaPeriode) {
        this.productBiayaDendaPeriode = productBiayaDendaPeriode;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
