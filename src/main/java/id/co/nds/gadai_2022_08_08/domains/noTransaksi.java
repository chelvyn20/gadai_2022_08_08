package id.co.nds.gadai_2022_08_08.domains;

import java.io.Serializable;
// import java.util.Objects;

import javax.persistence.Column;
// import javax.persistence.Embeddable;

// @Embeddable
public class noTransaksi implements Serializable{
    @Column(name = "no_transaksi")
    private String noTransaksi;

    @Column(name = "cicilan_ke")
    private Integer cicilanKe;

    public noTransaksi(){
        
    }

    public noTransaksi(String noTransaksi, Integer cicilanKe) {
        this.noTransaksi = noTransaksi;
        this.cicilanKe = cicilanKe;
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
    

}
