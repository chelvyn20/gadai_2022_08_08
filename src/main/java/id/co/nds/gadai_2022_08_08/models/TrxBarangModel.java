package id.co.nds.gadai_2022_08_08.models;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.PostingNew;

public class TrxBarangModel {
    @NotNull(message = "No Urut is required",
        groups = {PostingNew.class})
    @Min(value = 1, message = "No Urut can't be lower than 1",
        groups = {PostingNew.class})
    private Integer noUrut;

    @NotBlank(message = "Nama Barang is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^[a-zA-Z0-9]{1}[a-zA-Z0-9 ]{0,29}$", message = "Nama Barang can't start with whitespace, maximum 30 characters",
        groups = {PostingNew.class})
    private String namaBarang;

    @NotBlank(message = "Kondisi is required",
        groups = {PostingNew.class})
    @Pattern(regexp = "^[a-zA-Z0-9]{1}[a-zA-Z0-9 ]{0,149}$", message = "Kondisi can't start with whitespace, maximum 150 characters",
        groups = {PostingNew.class})
    private String kondisi;

    @NotNull(message = "Jumlah is required",
        groups = {PostingNew.class})
    @Min(value = 1, message = "Jumlah can't be lower than 1",
        groups = {PostingNew.class})
    private Integer jlh;

    @NotNull(message = "Harga Per Satuan is required",
        groups = {PostingNew.class})
    @DecimalMin(value = "0.01", message = "Harga Per Satuan can't be 0 or negative",
        groups = {PostingNew.class})
    @Digits(integer = 10, fraction = 2, message = "Harga Per Satuan only has 2 digits of fraction number",
        groups = {PostingNew.class})
    private BigDecimal hargaPerSatuan;

    public Integer getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(Integer noUrut) {
        this.noUrut = noUrut;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public Integer getJlh() {
        return jlh;
    }

    public void setJlh(Integer jlh) {
        this.jlh = jlh;
    }

    public BigDecimal getHargaPerSatuan() {
        return hargaPerSatuan;
    }

    public void setHargaPerSatuan(BigDecimal hargaPerSatuan) {
        this.hargaPerSatuan = hargaPerSatuan;
    }
}
