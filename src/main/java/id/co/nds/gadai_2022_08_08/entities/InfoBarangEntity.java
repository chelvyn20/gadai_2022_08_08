package id.co.nds.gadai_2022_08_08.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
import javax.persistence.Table;

// import org.hibernate.annotations.GenericGenerator;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "\"TX_TRANSAKSI_BARANG\"")
public class InfoBarangEntity {
    @Id
    // @GenericGenerator(name = "barang_id_seq", strategy = "id.co.nds.catalogue.generators.BarangGenerator")
    // @GeneratedValue(generator = "barang_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "no_transaksi")
    private String noTransaksi; 

    @Column(name = "no_urut")
    private Integer noUrut;

    @Column(name = "nama_barang")
    private String namaBarang;

    @Column(name = "kondisi")
    private String kondisi;

    @Column(name = "jumlah")
    private Integer jumlah;

    @Column(name = "harga_per_satuan")
    private Double hargaPerSatuan;

    @Column(name = "total")
    private Double total;

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

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Double getHargaPerSatuan() {
        return hargaPerSatuan;
    }

    public void setHargaPerSatuan(Double hargaPerSatuan) {
        this.hargaPerSatuan = hargaPerSatuan;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
