package id.co.nds.gadai_2022_08_08.validators;

import java.math.BigDecimal;

import id.co.nds.gadai_2022_08_08.exceptions.ClientException;

public class ProductValidator {
    public void validatePeriode(int periodePenyimpanan, int jangkaWaktu) throws ClientException {
        if(periodePenyimpanan > jangkaWaktu) {
            throw new ClientException("Product periode biaya jasa penyimpanan can't be higher than product tenor");
        }

        if(jangkaWaktu % periodePenyimpanan != 0) {
            throw new ClientException("Product periode biaya jasa penyimpanan has to be a multiple of product tenor");
        }
    }

    public void validatePercentRange(String type, Double value) throws ClientException {
        if(value > 100) {
            throw new ClientException(type + " has a maximum value of 100 since the type is 'PERSEN'");
        }
    }

    public void comparePercentRange(String type, BigDecimal before, BigDecimal after) throws ClientException {
        if(before == null && after == null) {
            return;
        }

        if(before == null || after == null) {
            throw new ClientException("Both " + type + " fields have to be inputted");
        }

        if(before.doubleValue() > after.doubleValue()) {
            throw new ClientException(type + " after can't be lower than " + type + " before");
        }
    }
}
