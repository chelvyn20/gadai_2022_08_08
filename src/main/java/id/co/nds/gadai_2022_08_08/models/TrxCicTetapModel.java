package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.ProcessData;

public class TrxCicTetapModel extends RecordModel {
    @NotBlank(message = "Product ID is required",
        groups = {PostingNew.class, ProcessData.class})
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "Product ID has a maximum of 20 digits, alphanumeric only",
        groups = {GettingAllByCriteria.class, PostingNew.class, ProcessData.class})
    private String productId;

    @Pattern(regexp = "^[a-zA-Z0-9]{1}[a-zA-Z0-9 ]{0,29}$", message = "Nama Barang can't start with whitespace, maximum 30 characters",
        groups = {GettingAllByCriteria.class})
    private String productName;

    @Pattern(regexp = "^[0-9]{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3[0-1]))$", message = "Transaction Date Begin format is yyyy-MM-dd",
        groups = {GettingAllByCriteria.class})
    private String trxDateBegin;

    @Pattern(regexp = "^[0-9]{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3[0-1]))$", message = "Transaction Date End format is yyyy-MM-dd",
        groups = {GettingAllByCriteria.class})
    private String trxDateEnd;

    @Pattern(regexp = "^aktif$|^jatuh tempo cicilan$|^terlambat bayar$|^jatuh tempo transaksi$|^lunas$|^[ ]{0,1}$", flags = Flag.CASE_INSENSITIVE, message = "Status Transaksi can only be 'AKTIF', 'JATUH TEMPO CICILAN', 'TERLAMBAT BAYAR', 'JATUH TEMPO TRANSAKSI', 'LUNAS', or empty",
        groups = {GettingAllByCriteria.class})
    private String statusTrans;

    @NotBlank(message = "Customer ID is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^[0-9]{10}$", message = "Customer ID has 10 digits of number",
        groups = {PostingNew.class, GettingAllByCriteria.class})
    private String custId;

    @Pattern(regexp = "^[0-9]{16}$", message = "Customer KTP has 16 digits of number",
        groups = {GettingAllByCriteria.class})
    private String custKtp;

    @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9 ]{0,29}$", message = "Customer name can't be empty, and has a maximum length of 30 characters",
        groups = {GettingAllByCriteria.class})
    private String custName;

    @Pattern(regexp = "^08[0-9]{7,10}$", message = "Customer phone number starts with 08 followed by 7 to 10 digits of number",
        groups = {GettingAllByCriteria.class})
    private String custHp;

    @NotBlank(message = "No Transaksi is required",
        groups = {GettingById.class})
    @Pattern(regexp = "^[0-9]{1,11}$", message = "No Transaksi has a maximum digit of 11, all numbers",
        groups = {GettingAllByCriteria.class})
    @Pattern(regexp = "^[0-9]{11}$", message = "No Transaksi has 11 digits of number",
        groups = {GettingById.class})
    private String noTransaksi;

    @NotNull(message = "Total Nilai Taksiran is required",
        groups = {PostingNew.class, ProcessData.class})
    @DecimalMin(value = "0.01", message = "Total Nilai Taksiran buka can't be 0 or negative",
        groups = {PostingNew.class, ProcessData.class})
    private BigDecimal totalNilaiTaksiran;

    @NotNull(message = "Nilai Pencairan Pelanggan is required",
        groups = {PostingNew.class, ProcessData.class})
    @DecimalMin(value = "1000000.00", message = "Nilai Pencairan Pelanggan can't be lower than 1.000.000,00",
        groups = {PostingNew.class, ProcessData.class})
    private BigDecimal nilaiPencairanPelanggan;

    @NotNull(message = "Diskon Admin Buka is required",
        groups = {PostingNew.class, ProcessData.class})
    @DecimalMin(value = "0", message = "Diskon Admin Buka can't be negative",
        groups = {PostingNew.class, ProcessData.class})
    @DecimalMax(value = "100", message = "Diskon Admin Buka can't be higher than 100",
        groups = {PostingNew.class, ProcessData.class})
    private BigDecimal diskonAdmBuka;

    @Valid
    private TrxBarangModel[] daftarBarangGadai;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustKtp() {
        return custKtp;
    }

    public void setCustKtp(String custKtp) {
        this.custKtp = custKtp;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustHp() {
        return custHp;
    }

    public void setCustHp(String custHp) {
        this.custHp = custHp;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
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

    public TrxBarangModel[] getDaftarBarangGadai() {
        return daftarBarangGadai;
    }

    public void setDaftarBarangGadai(TrxBarangModel[] daftarBarangGadai) {
        this.daftarBarangGadai = daftarBarangGadai;
    }
}
