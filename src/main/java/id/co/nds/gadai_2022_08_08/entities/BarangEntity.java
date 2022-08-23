package id.co.nds.gadai_2022_08_08.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "\"TX_TRANSAKSI_BARANG\"")
public class BarangEntity implements Serializable{
    @Id
    // @GenericGenerator(name = "barang_id_seq", strategy = "id.co.nds.catalogue.generators.BarangGenerator")
    // @GeneratedValue(generator = "barang_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    
    @JsonManagedReference
    @JoinColumn(name = "no_transaksi", referencedColumnName = "no_transaksi")
   
    @Column(name = "no_transaksi")
    private String noTransaksi;

    // @Id
    @Column(name = "no_urut")
    private Integer noUrut;

    @Column(name = "nama_barang")
    private String namaBarang;

    @Column(name = "kondisi")
    private String kondisi;

    @Column(name = "jumlah")
    private Integer jlh;

    @Column(name = "harga_per_satuan")
    private Double hargaPerSatuan;

    public Double getHargaPerSatuan() {
        return hargaPerSatuan;
    }

    public void setHargaPerSatuan(Double hargaPerSatuan) {
        this.hargaPerSatuan = hargaPerSatuan;
    }

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

    

}
