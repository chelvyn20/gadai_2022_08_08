package id.co.nds.gadai_2022_08_08.composites;

import java.io.Serializable;

public class TrxCicilanId implements Serializable {
    private String noTransaksi;
    private Integer cicilanKe;
    
    public TrxCicilanId(String noTransaksi, Integer cicilanKe) {
        this.noTransaksi = noTransaksi;
        this.cicilanKe = cicilanKe;
    }

    public TrxCicilanId() {}
}
