package id.co.nds.gadai_2022_08_08.composites;

import java.io.Serializable;

public class TrxBarangId implements Serializable {
    private String noTransaksi;
    private Integer noUrut;
    
    public TrxBarangId(String noTransaksi, Integer noUrut) {
        this.noTransaksi = noTransaksi;
        this.noUrut = noUrut;
    }

    public TrxBarangId() {}
}
