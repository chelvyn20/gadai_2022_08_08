package id.co.nds.gadai_2022_08_08.domains;

import java.io.Serializable;

public class Barang implements Serializable{
    private String noTransaksi;
    private Integer noUrut;
    
    public Barang(String noTransaksi, Integer noUrut) {
        this.noTransaksi = noTransaksi;
        this.noUrut = noUrut;
    }

    public Barang() {}
}
