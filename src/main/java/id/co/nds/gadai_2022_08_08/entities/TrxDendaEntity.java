package id.co.nds.gadai_2022_08_08.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "\"TX_DENDA_KETERLAMBATAN\"")
public class TrxDendaEntity {
    @Id
    @GenericGenerator(name = "denda_id_seq", strategy = "id.co.nds.gadai_2022_08_08.generators.TrxDendaIdGenerator")
    @GeneratedValue(generator = "denda_id_seq")
    @Column(name = "id_denda")
    private String idDenda;
    
    @Column(name = "no_transaksi")
    private String noTransaksi;

    @Column(name = "cicilan_ke")
    private Integer cicilanKe;

    @Column(name = "tgl_denda")
    private Date tglDenda;

    @Column(name = "biaya_denda")
    private BigDecimal biayaDenda;

    @Column(name = "tgl_pembayaran_denda")
    private Date tglPembayaranDenda;

    @Column(name = "no_pembayaran")
    private String noPembayaran;

    public String getIdDenda() {
        return idDenda;
    }

    public void setIdDenda(String idDenda) {
        this.idDenda = idDenda;
    }

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

    public Date getTglDenda() {
        return tglDenda;
    }

    public void setTglDenda(Date tglDenda) {
        this.tglDenda = tglDenda;
    }

    public BigDecimal getBiayaDenda() {
        return biayaDenda;
    }

    public void setBiayaDenda(BigDecimal biayaDenda) {
        this.biayaDenda = biayaDenda;
    }

    public Date getTglPembayaranDenda() {
        return tglPembayaranDenda;
    }

    public void setTglPembayaranDenda(Date tglPembayaranDenda) {
        this.tglPembayaranDenda = tglPembayaranDenda;
    }

    public String getNoPembayaran() {
        return noPembayaran;
    }

    public void setNoPembayaran(String noPembayaran) {
        this.noPembayaran = noPembayaran;
    }
}
