package id.co.nds.gadai_2022_08_08.domains;

import java.io.Serializable;

import javax.persistence.Column;

public class Denda implements Serializable {
    @Column(name = "id_denda")
    private String idDenda;
    @Column(name = "no_transaksi")
    private String noTransaksi;
    @Column(name = "cicilan_ke")
    private Integer cicilanKe;

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

    public Denda(String idDenda, String noTransaksi, Integer cicilanKe) {
        this.idDenda = idDenda;
        this.noTransaksi = noTransaksi;
        this.cicilanKe = cicilanKe;
    }

    public Denda() {

    }
}
