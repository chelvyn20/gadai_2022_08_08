package id.co.nds.gadai_2022_08_08.objects;

import id.co.nds.gadai_2022_08_08.entities.TrxCicTetapEntity;

public class SearchTransaksiObject extends TrxCicTetapEntity {
    private String statusTransaksi;

    public SearchTransaksiObject(TrxCicTetapEntity trx, String statusTransaksi) {
        setNoTransaksi(trx.getNoTransaksi());
        setTotalNilaiTak(trx.getTotalNilaiTak());
        setNilaiPencairanPel(trx.getNilaiPencairanPel());
        setDiskonAdmBuka(trx.getDiskonAdmBuka());
        setTxLtv(trx.getTxLtv());
        setMaxNilaiPinj(trx.getMaxNilaiPinj());
        setBiayaAdmBuka(trx.getBiayaAdmBuka());
        setBiayaAdmBukaAk(trx.getBiayaAdmBukaAk());
        setTotalNilaiPinj(trx.getTotalNilaiPinj());
        setTanggalTx(trx.getTanggalTx());
        setTanggalJatuhTempo(trx.getTanggalJatuhTempo());
        setTxBiayaJasaPeny(trx.getTxBiayaJasaPeny());
        setTxBiayaJasaPenyPer(trx.getTxBiayaJasaPenyPer());
        setTotalBiayaJasaPeny(trx.getTotalBiayaJasaPeny());
        setTxBiayaAdmTutup(trx.getTxBiayaAdmTutup());
        setTotalPengem(trx.getTotalPengem());
        setCustomer(trx.getCustomer());
        setProduct(trx.getProduct());
        setTrxBarangList(trx.getTrxBarangList());
        setTrxCicilanList(trx.getTrxCicilanList());
        setCreatedDate(trx.getCreatedDate());

        this.statusTransaksi = statusTransaksi;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }
}
