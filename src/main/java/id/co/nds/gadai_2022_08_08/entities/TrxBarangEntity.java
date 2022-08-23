package id.co.nds.gadai_2022_08_08.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import id.co.nds.gadai_2022_08_08.composites.TrxBarangId;

@Entity
@IdClass(TrxBarangId.class)
@Table(name = "\"TX_TRANSAKSI_BARANG\"")
public class TrxBarangEntity {
    @Id
    @JoinColumn(name = "no_transaksi", referencedColumnName = "no_transaksi")
    @Column(name = "no_transaksi")
    private String noTransaksi;

    @Id
    @Column(name = "no_urut")
    private Integer noUrut;

    @Column(name = "nama_barang")
    private String namaBarang;

    @Column(name = "kondisi")
    private String kondisi;

    @Column(name = "jumlah")
    private Integer jlh;

    @Column(name = "harga_per_satuan")
    private BigDecimal hargaPerSatuan;

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public Integer getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(Integer noUrut) {
        this.noUrut = noUrut;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public Integer getJlh() {
        return jlh;
    }

    public void setJlh(Integer jlh) {
        this.jlh = jlh;
    }

    public BigDecimal getHargaPerSatuan() {
        return hargaPerSatuan;
    }

    public void setHargaPerSatuan(BigDecimal hargaPerSatuan) {
        this.hargaPerSatuan = hargaPerSatuan;
    }
}
