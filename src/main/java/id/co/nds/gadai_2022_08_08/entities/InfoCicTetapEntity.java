package id.co.nds.gadai_2022_08_08.entities;
// import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
// import java.util.ArrayList;
// import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

// import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TX_TRANSAKSI_CICILAN_TETAP")
public class InfoCicTetapEntity {
    @Id
    @GenericGenerator(name = "cicilanTetap_id_seq", strategy = "id.co.nds.catalogue.generators.CicilantetapGenerator")
    @GeneratedValue(generator = "cicilantetap_id_seq")

    @Column(name = "no_transaksi")
    private String noTransaksi;

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

    @Column(name = "tanggal_tx")
    private Date tanggalTx;

    @Column(name = "tanggal_jatuh_tempo")
    private Date tanggalJatuhTempo;

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

    // @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @Column(name = "customer_id")
    private String customerId;

    // @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @Column(name = "product_id")
    private String productId;

    @Column(name = "created_date")
    private Timestamp createdDate;

    
    @Column(name="product_name")
    private String productName;


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


    public Date getTanggalTx() {
        return tanggalTx;
    }


    public void setTanggalTx(Date tanggalTx) {
        this.tanggalTx = tanggalTx;
    }


    public Date getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }


    public void setTanggalJatuhTempo(Date tanggalJatuhTempo) {
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


    public String getCustomerId() {
        return customerId;
    }


    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getProductId() {
        return productId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }
}
