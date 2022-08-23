package id.co.nds.gadai_2022_08_08.validators;

import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;

public class BarangValidator {
    public void nullCheckNoTransaksi(String transaksi) throws ClientException {
        if (transaksi == null) {
            throw new ClientException("no transaksi is required");
        }
    }

    public void notnullTransaksi(String transaksi) throws ClientException {
        if (transaksi != null) {
            throw new ClientException("no transaksi is auto generated, do not input again");
        }
    }

    public void nullCheckObject(Object o) throws NotFoundException {
        if (o == null) {
            throw new NotFoundException("transaksi is not found");
        }
    }

    public void validateNotransaksi(String transaksi) throws ClientException {
        if (transaksi.length() != 7 || transaksi.startsWith("TX")) {
            throw new ClientException("no transaksi must started with TX and contains 7 chars");
        }
    }

    public void validateName(String name) throws ClientException {
        if (name.trim().equalsIgnoreCase("")) {
            throw new ClientException("barang name is required");
        }
    }
    public void validateKondisi(String kondisi) throws ClientException {
        if (kondisi.trim().equalsIgnoreCase("")) {
            throw new ClientException("barang kondisi is required");
        }
    }
}
