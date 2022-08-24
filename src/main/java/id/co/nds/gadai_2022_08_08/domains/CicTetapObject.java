package id.co.nds.gadai_2022_08_08.domains;

import id.co.nds.gadai_2022_08_08.entities.CicTetapEntity;

public class CicTetapObject extends CicTetapEntity{
    private String namaProduct;

    CicTetapEntity cicilan = new CicTetapEntity();

    public CicTetapObject(CicTetapEntity cicilan, String namaProduct) {
        setNoTransaksi(cicilan.getNoTransaksi());
        setTotalNilaiTak(cicilan.getTotalNilaiTak());
        setNilaiPencairanPel(cicilan.getNilaiPencairanPel());
        setDiskonAdmBuka(cicilan.getDiskonAdmBuka());
        setTxLtv(cicilan.getTxLtv());
        setMaxNilaiPinj(cicilan.getMaxNilaiPinj());
        setBiayaAdmBuka(cicilan.getBiayaAdmBuka());
        setBiayaAdmBukaAk(cicilan.getBiayaAdmBukaAk());
        setTotalNilaiPinj(cicilan.getTotalNilaiPinj());
        setTanggalTx(cicilan.getTanggalTx());
        setTanggalJatuhTempo(cicilan.getTanggalJatuhTempo());
        setTxBiayaJasaPeny(cicilan.getTxBiayaJasaPeny());
        setTxBiayaJasaPenyPer(cicilan.getTxBiayaJasaPenyPer());
        setTotalBiayaJasaPeny(cicilan.getTotalBiayaJasaPeny());
        setTxBiayaAdmTutup(cicilan.getTxBiayaAdmTutup());
        setTotalPengem(cicilan.getTotalPengem());
        setCustomer(cicilan.getCustomer());
        setProduct(cicilan.getProduct());
        setBarangList(cicilan.getBarangList());
        setCicilanList(cicilan.getCicilanList());
        setCreatedDate(cicilan.getCreatedDate());
        this.namaProduct = namaProduct;
    }

    public String getnamaProduct() {
        return namaProduct;
    }

    public void setnamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }
}
