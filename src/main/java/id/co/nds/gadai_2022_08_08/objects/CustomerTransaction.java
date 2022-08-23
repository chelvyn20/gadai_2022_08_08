package id.co.nds.gadai_2022_08_08.objects;

public class CustomerTransaction {
    private String idPelanggan;
    private String noKtpPelanggan;
    private String noHp;
    private String namaPelanggan;

    public CustomerTransaction(String idPelanggan, String noKtpPelanggan, String noHp, String namaPelanggan) {
        this.idPelanggan = idPelanggan;
        this.noKtpPelanggan = noKtpPelanggan;
        this.noHp = noHp;
        this.namaPelanggan = namaPelanggan;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNoKtpPelanggan() {
        return noKtpPelanggan;
    }

    public void setNoKtpPelanggan(String noKtpPelanggan) {
        this.noKtpPelanggan = noKtpPelanggan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
