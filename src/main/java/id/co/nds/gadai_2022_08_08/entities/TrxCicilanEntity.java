package id.co.nds.gadai_2022_08_08.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import id.co.nds.gadai_2022_08_08.composites.TrxCicilanId;

@Entity
@IdClass(TrxCicilanId.class)
@Table(name = "\"TX_CICILAN\"")
public class TrxCicilanEntity {
    @Id
    @JoinColumn(name = "no_transaksi", referencedColumnName = "no_transaksi")
    @Column(name = "no_transaksi")
    private String noTransaksi;

    @Id
    @Column(name = "cicilan_ke")
    private Integer cicilanKe;

    @Column(name = "tx_pokok")
    private BigDecimal txPokok;

    @Column(name = "tx_bunga")
    private BigDecimal txBunga;

    @Column(name = "tx_status")
    private String txStatus;

    @Column(name = "tanggal_aktif")
    private Date tanggalAktif;

    @Column(name = "tanggal_jatuh_tempo")
    private Date tanggalJatuhTempo;

    @Column(name = "tanggal_bayar")
    private Date tanggalBayar;

    @Column(name = "created_date")
    private Timestamp createdDate;

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public Integer getCicilanKe() {
        return cicilanKe;
    }

    public void setCicilanKe(Integer cicilanKe) {
        this.cicilanKe = cicilanKe;
    }

    public BigDecimal getTxPokok() {
        return txPokok;
    }

    public void setTxPokok(BigDecimal txPokok) {
        this.txPokok = txPokok;
    }

    public BigDecimal getTxBunga() {
        return txBunga;
    }

    public void setTxBunga(BigDecimal txBunga) {
        this.txBunga = txBunga;
    }

    public String getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    public Date getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public Date getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(Date tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
