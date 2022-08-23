package id.co.nds.gadai_2022_08_08.domains;

import java.io.Serializable;

import javax.persistence.Column;

public class Barang implements Serializable {
    @Column(name = "no_transaksi")
    private String noTransaksi;
    @Column(name = "no_urut")
    private Integer noUrut;

    public Barang(String noTransaksi, Integer noUrut) {
        this.noTransaksi = noTransaksi;
        this.noUrut = noUrut;
    }

    public Barang() {
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
}
