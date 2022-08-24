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
        if (transaksi.length() != 7 ) {
            throw new ClientException("no transaksi must started with TX and contains 7 chars");
        }
    }

    public void validateCustId(String custId) throws ClientException {
        if (custId.length() != 8 ) {
            throw new ClientException("no transaksi must started with yearMonth and contains 8 chars");
        }
    }

    public void validateproductId(String productId) throws ClientException {
        if (productId.length() != 6 ) {
            throw new ClientException("no transaksi must started with PRD and contains 6 chars");
        }
    }

    public void validateStatus(String status) throws ClientException{
        if (!status.trim().equalsIgnoreCase("AKTIF") || 
            !status.trim().equalsIgnoreCase("JATUH TEMPO CICILAN") || 
            !status.trim().equalsIgnoreCase("TERLAMBAR BAYAR") || 
            !status.trim().equalsIgnoreCase("LUNAS") ||
            !status.trim().equalsIgnoreCase("JATUH TEMPO TRANSAKSI")){
                throw new ClientException("status not valid");
        }
    }

    public void validateProductName(String Name) throws ClientException{
        if (Name.trim().equalsIgnoreCase(" ")){
            throw new ClientException("product name is not valid");
        }
    }

    public void validateCustName(String Name) throws ClientException{
        if (Name.trim().equalsIgnoreCase(" ")){
            throw new ClientException("Customer name is not valid");
        }
    }

    public void validateDateBegin(String begin) throws ClientException{
        if (begin.trim().equalsIgnoreCase(" ")){
            throw new ClientException("date begin  is not valid");
        }
    }

    public void validateDateEnd(String end) throws ClientException{
        if (end.trim().equalsIgnoreCase(" ")){
            throw new ClientException("date end  is not valid");
        }
    }
    
    public void validateKtp(String ktp) throws ClientException{
        if (ktp.length() != 16){
            throw new ClientException("KTP is not valid");
        }
    }

    public void validateActorId(String id) throws ClientException{
        if (id.trim().equalsIgnoreCase(" ")){
            throw new ClientException("Actor Id is not valid");
        }
    }
    
}
