package id.co.nds.gadai_2022_08_08.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ms_product")
public class ProductEntity {
    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_type")
    private String productTipe;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "product_ltv")
    private BigDecimal productLtv;

    @Column(name = "product_tenor")
    private Integer productJangkaWaktu;

    @Column(name = "biaya_adm_buka_type")
    private String productBiayaAdminBukaTipe;

    @Column(name = "biaya_adm_buka_val")
    private BigDecimal productBiayaAdminBuka;

    @Column(name = "biaya_adm_tutup_type")
    private String productBiayaAdminTutupTipe;

    @Column(name = "biaya_adm_tutup_val")
    private BigDecimal productBiayaAdminTutup;

    @Column(name = "biaya_js_peny_rate")
    private BigDecimal productBiayaJasaPeny;

    @Column(name = "biaya_js_peny_per")
    private Integer productBiayaJasaPenyPeriode;

    @Column(name = "biaya_denda_keterlambatan_rate")
    private BigDecimal productBiayaDenda;

    @Column(name = "biaya_denda_keterlambatan_per")
    private Integer productBiayaDendaPeriode;

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

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
