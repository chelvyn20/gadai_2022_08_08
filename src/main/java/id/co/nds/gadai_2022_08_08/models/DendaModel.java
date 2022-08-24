package id.co.nds.gadai_2022_08_08.models;

import java.sql.Date;

public class DendaModel {
    
    private String idDenda;
    private String noTransaksi;
    private Integer cicilanKe;
    private Double biayaDenda;
    private Date tglDenda;
    private Date tglPembayaranDenda;
    private String noPembayaran;
    
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
    public Double getBiayaDenda() {
        return biayaDenda;
    }
    public void setBiayaDenda(Double biayaDenda) {
        this.biayaDenda = biayaDenda;
    }
    public Date getTglDenda() {
        return tglDenda;
    }
    public void setTglDenda(Date tglDenda) {
        this.tglDenda = tglDenda;
    }
    public Date getTglPembayaranDenda() {
        return tglPembayaranDenda;
    }
    public void setTglPembayaranDenda(Date tglPembayaranDenda) {
        this.tglPembayaranDenda = tglPembayaranDenda;
    }
    public String getNoPembayaran() {
        return noPembayaran;
    }
    public void setNoPembayaran(String noPembayaran) {
        this.noPembayaran = noPembayaran;
    }

}
