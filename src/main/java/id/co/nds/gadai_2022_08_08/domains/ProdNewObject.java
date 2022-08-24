package id.co.nds.gadai_2022_08_08.domains;

import id.co.nds.gadai_2022_08_08.entities.ProductEntity;

public class ProdNewObject extends ProductEntity{
    private String kode;
    private String nama;
    private String keterangan;

    public ProdNewObject(String kode, String nama, String keterangan) {
        this.kode = kode;
        this.nama = nama;
        this.keterangan = keterangan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
