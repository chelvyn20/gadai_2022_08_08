package id.co.nds.gadai_2022_08_08.validators;

import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;

public class CicilanValidator {
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
        if (transaksi.length() != 7) {
            throw new ClientException("no transaksi must started with TX and contains 7 chars");
        }
    }

    public void validateStatus(String status) throws ClientException {
        if (!status.trim().equalsIgnoreCase("BELUM AKTIF") ||
                !status.trim().equalsIgnoreCase("AKTIF") ||
                !status.trim().equalsIgnoreCase("TERLAMBAT") ||
                !status.trim().equalsIgnoreCase("DIBAYAR")) {
            throw new ClientException("status not valid");
        }
    }
}
