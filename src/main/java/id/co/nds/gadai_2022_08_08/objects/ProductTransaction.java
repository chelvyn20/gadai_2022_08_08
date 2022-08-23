package id.co.nds.gadai_2022_08_08.objects;

public class ProductTransaction {
    private String kodeProduk;
    private String namaProduk;
    private String keteranganProduk;

    public ProductTransaction(String kodeProduk, String namaProduk, String keteranganProduk) {
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.keteranganProduk = keteranganProduk;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getKeteranganProduk() {
        return keteranganProduk;
    }

    public void setKeteranganProduk(String keteranganProduk) {
        this.keteranganProduk = keteranganProduk;
    }
}
