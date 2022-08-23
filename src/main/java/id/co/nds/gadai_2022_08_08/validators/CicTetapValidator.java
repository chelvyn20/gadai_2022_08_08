package id.co.nds.gadai_2022_08_08.validators;

import java.math.BigDecimal;
import java.time.YearMonth;

import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;

public class CicTetapValidator {
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

    public void validateCustId(String custId) throws ClientException {
        if (custId.length() != 8 ) {
            throw new ClientException("no transaksi must started with yearMonth and contains 8 chars");
        }
    }

    public void validateproductId(String productId) throws ClientException {
        if (productId.length() != 6 || productId.startsWith("PRD")) {
            throw new ClientException("no transaksi must started with PRD and contains 6 chars");
        }
    }


    
}
