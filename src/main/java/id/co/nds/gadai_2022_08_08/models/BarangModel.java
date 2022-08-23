package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;

public class BarangModel {
    private String noTransaksi;
    private String namaBarang;
    private Integer noUrut;
    private String kondisi;
    private Integer jumlah;
    private BigDecimal hargaPerSatuan;

    public String getNamaBarang() {
        return namaBarang;
    }
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }
    public Integer getNoUrut() {
        return noUrut;
    }
    public void setNoUrut(Integer noUrut) {
        this.noUrut = noUrut;
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
    public BigDecimal getHargaPerSatuan() {
        return hargaPerSatuan;
    }
    public void setHargaPerSatuan(BigDecimal hargaPerSatuan) {
        this.hargaPerSatuan = hargaPerSatuan;
    }
    public String getNoTransaksi() {
        return noTransaksi;
    }
    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }
}
