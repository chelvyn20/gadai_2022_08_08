package id.co.nds.gadai_2022_08_08.entities;

import java.io.Serializable;
// import java.math.Double;
// import java.math.Double;
// import java.sql.Date;
import java.sql.Timestamp;
// import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

// import id.co.nds.gadai_2022_08_08.models.CicTetapModel;

@Entity
@Table(name = "tx_transaksi_cicilan_tetap")
public class CicTetapEntity implements Serializable{
    @Id
    // @GenericGenerator(name = "cicilanTetap_id_seq", strategy = "id.co.nds.catalogue.generators.CicilantetapGenerator")
    // @GeneratedValue(generator = "cicilantetap_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonManagedReference
    @Column(name = "no_transaksi")
    private String noTransaksi;

    // @Id
    @Column(name = "total_nilai_tak")
    private Double totalNilaiTak;

    @Column(name = "nilai_pencairan_pel")
    private Double nilaiPencairanPel;

    @Column(name = "diskon_adm_buka")
    private Double diskonAdmBuka;

    @Column(name = "tx_ltv")
    private Double txLtv;

    @Column(name = "max_nilai_pinj")
    private Double maxNilaiPinj;

    @Column(name = "biaya_adm_buka")
    private Double biayaAdmBuka;

    @Column(name = "biaya_adm_buka_ak")
    private Double biayaAdmBukaAk;

    @Column(name = "total_nilai_pinj")
    private Double totalNilaiPinj;

    @Column(name = "tanggal_tx")
    private Timestamp tanggalTx;

    @Column(name = "tanggal_jatuh_tempo")
    private Timestamp tanggalJatuhTempo;

    @Column(name = "tx_biaya_jasa_peny")
    private Double txBiayaJasaPeny;

    @Column(name = "tx_biaya_jasa_peny_per")
    private Double txBiayaJasaPenyPer;

    @Column(name = "total_biaya_jasa_peny")
    private Double totalBiayaJasaPeny;

    @Column(name = "tx_biaya_adm_tutup")
    private Double txBiayaAdmTutup;

    @Column(name = "total_pengem")
    private Double totalPengem;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = ProductEntity.class)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @OneToMany(targetEntity = BarangEntity.class, mappedBy = "noTransaksi")
    private List<BarangEntity> BarangList;

    @OneToMany(targetEntity = CicilanEntity.class, mappedBy = "noTransaksi")
    private List<CicilanEntity> CicilanList;

    @Column(name = "created_date")
    private Timestamp createdDate;

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public Double getTotalNilaiTak() {
        return totalNilaiTak;
    }

    public void setTotalNilaiTak(Double totalNilaiTak) {
        this.totalNilaiTak = totalNilaiTak;
    }

    public Double getNilaiPencairanPel() {
        return nilaiPencairanPel;
    }

    public void setNilaiPencairanPel(Double nilaiPencairanPel) {
        this.nilaiPencairanPel = nilaiPencairanPel;
    }

    public Double getDiskonAdmBuka() {
        return diskonAdmBuka;
    }

    public void setDiskonAdmBuka(Double diskonAdmBuka) {
        this.diskonAdmBuka = diskonAdmBuka;
    }

    public Double getTxLtv() {
        return txLtv;
    }

    public void setTxLtv(Double txLtv) {
        this.txLtv = txLtv;
    }

    public Double getMaxNilaiPinj() {
        return maxNilaiPinj;
    }

    public void setMaxNilaiPinj(Double maxNilaiPinj) {
        this.maxNilaiPinj = maxNilaiPinj;
    }

    public Double getBiayaAdmBukaAk() {
        return biayaAdmBukaAk;
    }

    public void setBiayaAdmBukaAk(Double biayaAdmBukaAk) {
        this.biayaAdmBukaAk = biayaAdmBukaAk;
    }

    public Double getTotalNilaiPinj() {
        return totalNilaiPinj;
    }

    public void setTotalNilaiPinj(Double totalNilaiPinj) {
        this.totalNilaiPinj = totalNilaiPinj;
    }

    public Timestamp getTanggalTx() {
        return tanggalTx;
    }

    public void setTanggalTx(Timestamp tanggalTx) {
        this.tanggalTx = tanggalTx;
    }

    public Timestamp getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(Timestamp tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public Double getTxBiayaJasaPeny() {
        return txBiayaJasaPeny;
    }

    public void setTxBiayaJasaPeny(Double txBiayaJasaPeny) {
        this.txBiayaJasaPeny = txBiayaJasaPeny;
    }

    public Double getTxBiayaJasaPenyPer() {
        return txBiayaJasaPenyPer;
    }

    public void setTxBiayaJasaPenyPer(Double txBiayaJasaPenyPer) {
        this.txBiayaJasaPenyPer = txBiayaJasaPenyPer;
    }

    public Double getTotalBiayaJasaPeny() {
        return totalBiayaJasaPeny;
    }

    public void setTotalBiayaJasaPeny(Double totalBiayaJasaPeny) {
        this.totalBiayaJasaPeny = totalBiayaJasaPeny;
    }

    public Double getTxBiayaAdmTutup() {
        return txBiayaAdmTutup;
    }

    public void setTxBiayaAdmTutup(Double txBiayaAdmTutup) {
        this.txBiayaAdmTutup = txBiayaAdmTutup;
    }

    public Double getTotalPengem() {
        return totalPengem;
    }

    public void setTotalPengem(Double totalPengem) {
        this.totalPengem = totalPengem;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Double getBiayaAdmBuka() {
        return biayaAdmBuka;
    }

    public void setBiayaAdmBuka(Double biayaAdmBuka) {
        this.biayaAdmBuka = biayaAdmBuka;
    }

    public List<BarangEntity> getBarangList() {
        return BarangList;
    }

    public void setBarangList(List<BarangEntity> BarangList) {
        this.BarangList = BarangList;
    }

    public List<CicilanEntity> getCicilanList() {
        return CicilanList;
    }

    public void setCicilanList(List<CicilanEntity> CicilanList) {
        this.CicilanList = CicilanList;
    }

  
}
