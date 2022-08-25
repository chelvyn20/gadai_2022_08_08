package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;
// import java.sql.Date;

public class CicTetapModel {
    private String noTransaksi;
    private String productId;
    private BigDecimal totalNilaiTaksiran;
    private BigDecimal nilaiPencairanPelanggan;
    private BigDecimal diskonAdmBuka;
    private String custId;
    private String actorId;
    private String trxDateBegin;
    private String trxDateEnd;
    private String statusTrans;
    private Double biayaAdminBuka;
    private Double biayaAdminTutup;
    
    public Double getBiayaAdminTutup() {
        return biayaAdminTutup;
    }
    public void setBiayaAdminTutup(Double biayaAdminTutup) {
        this.biayaAdminTutup = biayaAdminTutup;
    }
    private Double biayaJasaPenyimpanan;
    private Double totalNilaiPinjam;
    private Double TxLtv;

    
    public Double getTxLtv() {
        return TxLtv;
    }
    public void setTxLtv(ProductModel productModel) {
        TxLtv = productModel.getProductLtv().doubleValue();
    }
    public Double getBiayaJasaPenyimpanan() {
        return biayaJasaPenyimpanan;
    }
    public void setBiayaJasaPenyimpanan(Double biayaJasaPenyimpanan) {
        this.biayaJasaPenyimpanan = biayaJasaPenyimpanan;
    }
    public Double getTotalNilaiPinjam() {
        return totalNilaiPinjam;
    }
    public void setTotalNilaiPinjam(Double totalNilaiPinjam) {
        this.totalNilaiPinjam = totalNilaiPinjam;
    }
    public Double getBiayaAdminBuka() {
        return biayaAdminBuka;
    }
    public void setBiayaAdminBuka(Double biayaAdminBuka) {
        this.biayaAdminBuka = biayaAdminBuka;
    }
    public String getNoTransaksi() {
        return noTransaksi;
    }
    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public BigDecimal getTotalNilaiTaksiran() {
        return totalNilaiTaksiran;
    }
    public void setTotalNilaiTaksiran(BigDecimal totalNilaiTaksiran) {
        this.totalNilaiTaksiran = totalNilaiTaksiran;
    }
    public BigDecimal getNilaiPencairanPelanggan() {
        return nilaiPencairanPelanggan;
    }
    public void setNilaiPencairanPelanggan(BigDecimal nilaiPencairanPelanggan) {
        this.nilaiPencairanPelanggan = nilaiPencairanPelanggan;
    }
    public BigDecimal getDiskonAdmBuka() {
        return diskonAdmBuka;
    }
    public void setDiskonAdmBuka(BigDecimal diskonAdmBuka) {
        this.diskonAdmBuka = diskonAdmBuka;
    }
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    public String getActorId() {
        return actorId;
    }
    public void setActorId(String actorId) {
        this.actorId = actorId;
    }
    public String getTrxDateBegin() {
        return trxDateBegin;
    }
    public void setTrxDateBegin(String trxDateBegin) {
        this.trxDateBegin = trxDateBegin;
    }
    public String getTrxDateEnd() {
        return trxDateEnd;
    }
    public void setTrxDateEnd(String trxDateEnd) {
        this.trxDateEnd = trxDateEnd;
    }
    public String getStatusTrans() {
        return statusTrans;
    }
    
    public void setStatusTrans(String statusTrans) {
        this.statusTrans = statusTrans;
    }

    private String custKtp;
    public String getCustKtp() {
        return custKtp;
    }

    public void setCustKtp(String custKtp) {
        this.custKtp = custKtp;
    }

    private String custName;
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    private String custHp;
    public String getCustHp() {
        return custHp;
    }

    public void setCustHp(String custHp) {
        this.custHp = custHp;
    }
    
    private String productName;
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    

    
}
