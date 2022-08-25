package id.co.nds.gadai_2022_08_08.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
// import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
// import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;
// import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
// import org.hibernate.validator.constraints.Range;

import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.UpdatingById;
// import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.DeletingById;

@Entity
@Table(name = "ms_product")
public class ProductEntity {
    @Id
    @GenericGenerator(name = "product_id_seq", strategy = "id.co.nds.gadai_2022_08_08.generators.ProductGenerator")
    @GeneratedValue(generator = "product_id_seq")

    // @OneToMany(targetEntity = ProductEntity.class, mappedBy = "productId")
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    // private CicTetapEntity cicil;
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_name")
    private String productName;

    @NotNull(groups = { PostingNew.class, GettingAllByCriteria.class, UpdatingById.class })
    @Pattern(regexp = "^[a-zA-Z0-9]{1}.{0,254}$", groups = { PostingNew.class, UpdatingById.class })
    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "product_ltv")
    private Double productLtv;

    @Column(name = "product_tenor")
    private Integer productTenor;

    @Column(name = "biaya_adm_buka_type")
    private String biayaAdmBukaType;

    @Column(name = "biaya_adm_buka_val")
    private Double biayaAdmBukaVal;

    @Column(name = "biaya_adm_tutup_type")
    private String biayaAdmTutupType;

    @Column(name = "biaya_adm_tutup_val")
    private Double biayaAdmTutupVal;

    @Column(name = "biaya_js_peny_rate")
    private Double biayaJsPenyRate;

    @Column(name = "biaya_js_peny_per")
    private Integer biayaJsPenyPer;

    @Column(name = "biaya_denda_keterlambatan_rate")
    private Double biayaDendaKeterlambatanrate;

    @Column(name = "biaya_denda_keterlambatan_per")
    private Integer biayaDendaKeterlambatanPer;

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

    public Double getProductLtv() {
        return productLtv;
    }

    public void setProductLtv(Double productLtv) {
        this.productLtv = productLtv;
    }

    public Integer getProductTenor() {
        return productTenor;
    }

    public void setProductTenor(Integer productTenor) {
        this.productTenor = productTenor;
    }

    public String getBiayaAdmBukaType() {
        return biayaAdmBukaType;
    }

    public void setBiayaAdmBukaType(String biayaAdmBukaType) {
        this.biayaAdmBukaType = biayaAdmBukaType;
    }

    public Double getBiayaAdmBukaVal() {
        return biayaAdmBukaVal;
    }

    public void setBiayaAdmBukaVal(Double biayaAdmBukaVal) {
        this.biayaAdmBukaVal = biayaAdmBukaVal;
    }

    public String getBiayaAdmTutupType() {
        return biayaAdmTutupType;
    }

    public void setBiayaAdmTutupType(String biayaAdmTutupType) {
        this.biayaAdmTutupType = biayaAdmTutupType;
    }

    public Double getBiayaAdmTutupVal() {
        return biayaAdmTutupVal;
    }

    public void setBiayaAdmTutupVal(Double biayaAdmTutupVal) {
        this.biayaAdmTutupVal = biayaAdmTutupVal;
    }

    public Double getBiayaJsPenyRate() {
        return biayaJsPenyRate;
    }

    public void setBiayaJsPenyRate(Double biayaJsPenyRate) {
        this.biayaJsPenyRate = biayaJsPenyRate;
    }

    public Integer getBiayaJsPenyPer() {
        return biayaJsPenyPer;
    }

    public void setBiayaJsPenyPer(Integer biayaJsPenyPer) {
        this.biayaJsPenyPer = biayaJsPenyPer;
    }

    public Double getBiayaDendaKeterlambatanrate() {
        return biayaDendaKeterlambatanrate;
    }

    public void setBiayaDendaKeterlambatanrate(Double biayaDendaKeterlambatanrate) {
        this.biayaDendaKeterlambatanrate = biayaDendaKeterlambatanrate;
    }

    public Integer getBiayaDendaKeterlambatanPer() {
        return biayaDendaKeterlambatanPer;
    }

    public void setBiayaDendaKeterlambatanPer(Integer biayaDendaKeterlambatanPer) {
        this.biayaDendaKeterlambatanPer = biayaDendaKeterlambatanPer;
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

    public ProductEntity(String productId, String productName, String productDesc){
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
    }

    public ProductEntity() {
    }
}
