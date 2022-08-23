package id.co.nds.gadai_2022_08_08.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "\"TX_TRANSAKSI_CICILAN_TETAP\"")
public class TrxCicTetapEntity {
    @Id
    @GenericGenerator(name = "tx_cic_tetap_id_seq", strategy = "id.co.nds.gadai_2022_08_08.generators.TrxCicTetapIdGenerator")
    @GeneratedValue(generator = "tx_cic_tetap_id_seq")
    @Column(name = "no_transaksi")
    private String noTransaksi;

    @Column(name = "total_nilai_tak")
    private BigDecimal totalNilaiTak;

    @Column(name = "nilai_pencairan_pel")
    private BigDecimal nilaiPencairanPel;

    @Column(name = "diskon_adm_buka")
    private BigDecimal diskonAdmBuka;

    @Column(name = "tx_ltv")
    private BigDecimal txLtv;

    @Column(name = "max_nilai_pinj")
    private BigDecimal maxNilaiPinj;

    @Column(name = "biaya_adm_buka")
    private BigDecimal biayaAdmBuka;

    @Column(name = "biaya_adm_buka_ak")
    private BigDecimal biayaAdmBukaAk;

    @Column(name = "total_nilai_pinj")
    private BigDecimal totalNilaiPinj;

    @Column(name = "tanggal_tx")
    private Timestamp tanggalTx;

    @Column(name = "tanggal_jatuh_tempo")
    private Timestamp tanggalJatuhTempo;

    @Column(name = "tx_biaya_jasa_peny")
    private BigDecimal txBiayaJasaPeny;

    @Column(name = "tx_biaya_jasa_peny_per")
    private BigDecimal txBiayaJasaPenyPer;

    @Column(name = "total_biaya_jasa_peny")
    private BigDecimal totalBiayaJasaPeny;

    @Column(name = "tx_biaya_adm_tutup")
    private BigDecimal txBiayaAdmTutup;

    @Column(name = "total_pengem")
    private BigDecimal totalPengem;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = ProductEntity.class)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @OneToMany(targetEntity = TrxBarangEntity.class, mappedBy = "noTransaksi")
    private List<TrxBarangEntity> trxBarangList;

    @OneToMany(targetEntity = TrxCicilanEntity.class, mappedBy = "noTransaksi")
    private List<TrxCicilanEntity> trxCicilanList;

    @Column(name = "created_date")
    private Timestamp createdDate;

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public BigDecimal getTotalNilaiTak() {
        return totalNilaiTak;
    }

    public void setTotalNilaiTak(BigDecimal totalNilaiTak) {
        this.totalNilaiTak = totalNilaiTak;
    }

    public BigDecimal getNilaiPencairanPel() {
        return nilaiPencairanPel;
    }

    public void setNilaiPencairanPel(BigDecimal nilaiPencairanPel) {
        this.nilaiPencairanPel = nilaiPencairanPel;
    }

    public BigDecimal getDiskonAdmBuka() {
        return diskonAdmBuka;
    }

    public void setDiskonAdmBuka(BigDecimal diskonAdmBuka) {
        this.diskonAdmBuka = diskonAdmBuka;
    }

    public BigDecimal getTxLtv() {
        return txLtv;
    }

    public void setTxLtv(BigDecimal txLtv) {
        this.txLtv = txLtv;
    }

    public BigDecimal getMaxNilaiPinj() {
        return maxNilaiPinj;
    }

    public void setMaxNilaiPinj(BigDecimal maxNilaiPinj) {
        this.maxNilaiPinj = maxNilaiPinj;
    }

    public BigDecimal getBiayaAdmBukaAk() {
        return biayaAdmBukaAk;
    }

    public void setBiayaAdmBukaAk(BigDecimal biayaAdmBukaAk) {
        this.biayaAdmBukaAk = biayaAdmBukaAk;
    }

    public BigDecimal getTotalNilaiPinj() {
        return totalNilaiPinj;
    }

    public void setTotalNilaiPinj(BigDecimal totalNilaiPinj) {
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

    public BigDecimal getTxBiayaJasaPeny() {
        return txBiayaJasaPeny;
    }

    public void setTxBiayaJasaPeny(BigDecimal txBiayaJasaPeny) {
        this.txBiayaJasaPeny = txBiayaJasaPeny;
    }

    public BigDecimal getTxBiayaJasaPenyPer() {
        return txBiayaJasaPenyPer;
    }

    public void setTxBiayaJasaPenyPer(BigDecimal txBiayaJasaPenyPer) {
        this.txBiayaJasaPenyPer = txBiayaJasaPenyPer;
    }

    public BigDecimal getTotalBiayaJasaPeny() {
        return totalBiayaJasaPeny;
    }

    public void setTotalBiayaJasaPeny(BigDecimal totalBiayaJasaPeny) {
        this.totalBiayaJasaPeny = totalBiayaJasaPeny;
    }

    public BigDecimal getTxBiayaAdmTutup() {
        return txBiayaAdmTutup;
    }

    public void setTxBiayaAdmTutup(BigDecimal txBiayaAdmTutup) {
        this.txBiayaAdmTutup = txBiayaAdmTutup;
    }

    public BigDecimal getTotalPengem() {
        return totalPengem;
    }

    public void setTotalPengem(BigDecimal totalPengem) {
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

    public BigDecimal getBiayaAdmBuka() {
        return biayaAdmBuka;
    }

    public void setBiayaAdmBuka(BigDecimal biayaAdmBuka) {
        this.biayaAdmBuka = biayaAdmBuka;
    }

    public List<TrxBarangEntity> getTrxBarangList() {
        return trxBarangList;
    }

    public void setTrxBarangList(List<TrxBarangEntity> trxBarangList) {
        this.trxBarangList = trxBarangList;
    }

    public List<TrxCicilanEntity> getTrxCicilanList() {
        return trxCicilanList;
    }

    public void setTrxCicilanList(List<TrxCicilanEntity> trxCicilanList) {
        this.trxCicilanList = trxCicilanList;
    }
}
