package id.co.nds.gadai_2022_08_08.domains;

import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;

public class CustNewObject extends CustomerEntity{
    private String id;
    private String noKtp;
    private String noHp;
    private String nama;

    public CustNewObject(String id, String noKtp, String noHp, String nama) {
        this.id = id;
        this.noKtp = noKtp;
        this.noHp = noHp;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

   
}
