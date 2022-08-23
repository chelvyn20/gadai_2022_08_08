package id.co.nds.gadai_2022_08_08.validators;

import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;

public class ProductValidator {
    public void nullCheckId(String productId) throws ClientException {
        if (productId == null) {
            throw new ClientException("product id is required");
        }
    }

    public void notNullId(String productId) throws ClientException {
        if (productId != null) {
            throw new ClientException("product id is auto generated, do not input id");
        }
    }

    public void nullCheckName(String productName) throws ClientException {
        if (productName == null) {
            throw new ClientException("product Name is required");
        }
    }

    public void nullCheckDesc(String productDesc) throws ClientException {
        if (productDesc == null) {
            throw new ClientException("product Description is required");
        }
    }

    public void nullCheckObject(Object o) throws NotFoundException {
        if (o == null) {
            throw new NotFoundException("product is not found");
        }
    }

    public void validateproductId(String productId) throws ClientException {
        if (productId.length() != 15) {
            throw new ClientException("product id must contains 15 chars");
        }
    }

    public void validateName(String productName) throws ClientException {
        if (productName.trim().equalsIgnoreCase("")) {
            throw new ClientException("product name is required");
        }
    }

    public void validateDesc(String productDesc) throws ClientException {
        if (productDesc.trim().equalsIgnoreCase("")) {
            throw new ClientException("product Desc is required");
        }
    }

    public void validateRecStatus(String productId, String userStatus) throws ClientException {
        if (userStatus.equalsIgnoreCase(GlobalConstanst.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Product with id = " + productId + "is already been deleted");
        }
    }

    public void validateLtv(Double productLtv) throws ClientException {
        if (productLtv < 0.00 || productLtv > 100.00) {
            throw new ClientException("Loan to Value is out of range");
        }
    }

    public void validateLtvBefore(Double productLtv) throws ClientException {
        if (productLtv < 0.00 || productLtv > 100.00) {
            throw new ClientException("Loan to Value is out of range");
        }
    }

    public void validateLtvAfter(Double productLtv) throws ClientException {
        if (productLtv < 0.00 || productLtv > 100.00) {
            throw new ClientException("Loan to Value is out of range");
        }
    }

    public void validateJangkaWaktu(Integer ProductJangkaWaktu) throws ClientException {
        if (ProductJangkaWaktu <= 0) {
            throw new ClientException("jangka Waktu is not default");
        }
    }

    public void validateBiayaAdminBuka(Double ProductBiayaAdminBuka) throws ClientException {
        if (ProductBiayaAdminBuka < 0.00 || ProductBiayaAdminBuka > 100.00) {
            throw new ClientException("Biaya Admin Buka is out of range");
        }
    }

    public void validateBiayaAdminTutup(Double ProductBiayaAdminTutup) throws ClientException {
        if (ProductBiayaAdminTutup < 0.00 || ProductBiayaAdminTutup > 100.00) {
            throw new ClientException("Biaya Admin Tutup is out of range");
        }
    }

    public void validateBiayaJasaPeny(Double ProductBiayaJasaPeny) throws ClientException {
        if (ProductBiayaJasaPeny < 0.00 || ProductBiayaJasaPeny > 100.00) {
            throw new ClientException("Biaya Admin Buka is out of range");
        }
    }

    public void validateBiayaJasaPenyBefore(Double ProductBiayaJasaPeny) throws ClientException {
        if (ProductBiayaJasaPeny < 0.00 || ProductBiayaJasaPeny > 100.00) {
            throw new ClientException("Biaya Admin Buka is out of range");
        }
    }

    public void validateBiayaJasaPenyAfter(Double ProductBiayaJasaPeny) throws ClientException {
        if (ProductBiayaJasaPeny < 0.00 || ProductBiayaJasaPeny > 100.00) {
            throw new ClientException("Biaya Admin Buka is out of range");
        }
    }

    public void range(String text, Double validateBefore, Double validateAfter) throws ClientException {
        if (validateAfter < validateBefore) {
            throw new ClientException("invalid data");
        }
    }

    public void validateBiayaJasaPenyPeriode(Integer ProductBiayaJasaPenyPeriode) throws ClientException {
        if (ProductBiayaJasaPenyPeriode <= 0) {
            throw new ClientException("Periode Penyimpanan is not default");
        }
    }

    public void validateJasaPenyPeriode(Integer ProductBiayaJasaPenyPeriode, Integer ProductJangkaWaktu)
            throws ClientException {
        if (ProductJangkaWaktu < ProductBiayaJasaPenyPeriode) {
            throw new ClientException("Periode Jasa Penyimpanan is not valid");
        } else {
            if (ProductJangkaWaktu % ProductBiayaJasaPenyPeriode != 0) {
                throw new ClientException("ERROR");
            }
        }
    }

    public void validateBiayaDendaPeriode(Integer ProductBiayaDendaPeriode) throws ClientException {
        if (ProductBiayaDendaPeriode <= 0) {
            throw new ClientException("Periode Penyimpanan is not default");
        }
    }

    public void validateBiayaDenda(Double ProductBiayaDenda) throws ClientException {
        if (ProductBiayaDenda < 0.00 || ProductBiayaDenda > 100.00) {
            throw new ClientException("Biaya Denda is out of range");
        }
    }

}
