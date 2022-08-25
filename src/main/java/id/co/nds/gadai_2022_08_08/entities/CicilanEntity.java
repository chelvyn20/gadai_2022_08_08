package id.co.nds.gadai_2022_08_08.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import id.co.nds.gadai_2022_08_08.domains.noTransaksi;

@Entity
@Table(name = "tx_cicilan")
public class CicilanEntity implements Serializable {
    @Id
    // @GenericGenerator(name = "cicil_id_seq", strategy =
    // "id.co.nds.catalogue.generators.CicilanGenerator")
    // @GeneratedValue(generator = "cicil_id_seq")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JoinColumn(name = "no_transaksi", referencedColumnName = "no_transaksi")
    @Column(name = "no_transaksi")
    private String noTransaksi;

    @Column(name = "cicilan_ke")
    private Integer cicilanKe;

    @Column(name = "tx_pokok")
    private Double txPokok;

    @Column(name = "tx_bunga")
    private Double txBunga;

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

    public Double getTxPokok() {
        return txPokok;
    }

    public void setTxPokok(double d) {
        this.txPokok = d;
    }

    public Double getTxBunga() {
        return txBunga;
    }

    public void setTxBunga(double d) {
        this.txBunga = d;
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

    public void setTanggalAktif(Date aktif) {
        this.tanggalAktif = aktif;
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
