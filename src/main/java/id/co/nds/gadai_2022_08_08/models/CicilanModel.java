package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import id.co.nds.gadai_2022_08_08.domains.*;

public class CicilanModel {
    // @EmbeddedId
    private String noTransaksi;

    private Integer cicilanKe;
    private Timestamp createdDate;
    private  BigDecimal txPokok;
    private BigDecimal txBunga;
    private String txStatus;
    private Date tanggalAktif;
    private Date tanggalJatuhTempo;
    private Date tanggalBayar;

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
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
}
