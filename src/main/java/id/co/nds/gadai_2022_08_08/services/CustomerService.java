package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.entities.JenisUsahaEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstant;
import id.co.nds.gadai_2022_08_08.models.CustomerModel;
import id.co.nds.gadai_2022_08_08.repos.CustomerRepo;
import id.co.nds.gadai_2022_08_08.repos.JenisUsahaRepo;
import id.co.nds.gadai_2022_08_08.repos.specs.CustomerSpec;

@Service
public class CustomerService implements Serializable {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    JenisUsahaRepo jenisUsahaRepo;

    public CustomerEntity[] doSearchPelanggan(CustomerModel customerModel) {
        ArrayList<CustomerEntity> customers = new ArrayList<>();
        CustomerSpec spec = new CustomerSpec(customerModel);
        customerRepo.findAll(spec).forEach(customers::add);
        
        CustomerEntity[] customersArray = {};
        customersArray = customers.toArray(customersArray);
        return customersArray;
    }

    public CustomerEntity doGetDetailPelanggan(CustomerModel customerModel) throws NoSuchElementException {
        CustomerEntity customer = customerRepo.findById(customerModel.getCustId()).orElse(null);
        if(customer == null) {
            throw new NoSuchElementException("Customer not found");
        }

        return customer;
    }

    public CustomerEntity doInsert(CustomerModel customerModel) throws ClientException {
        Long count = customerRepo.checkDuplicateKtp(customerModel.getCustKtp(), "");
        if(count > 0) {
            throw new ClientException("Customer KTP already exists");
        }

        count = customerRepo.checkDuplicateHp(customerModel.getCustHp(), "");
        if(count > 0) {
            throw new ClientException("Customer phone number already exists");
        }

        CustomerEntity customer = new CustomerEntity();
        customer.setCustName(customerModel.getCustName());
        customer.setCustKtp(customerModel.getCustKtp());
        customer.setCustHp(customerModel.getCustHp());
        customer.setCustJk(customerModel.getCustJk().toUpperCase());
        customer.setCustJenisUsahaId(customerModel.getCustJenisUsahaId());
        customer.setCustLimitTxn(customerModel.getCustLimitTxn());
        customer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        customer.setCreatedBy(customerModel.getActorId());
        customer.setCustStatus(GlobalConstant.REC_STATUS_ACTIVE);

        return customerRepo.save(customer);
    }

    public CustomerEntity doUpdatePelanggan(CustomerModel customerModel) throws ClientException, NoSuchElementException {
        if(!customerRepo.existsById(customerModel.getCustId())) {
            throw new NoSuchElementException("Cannot find customer with id: " + customerModel.getCustId());
        }

        CustomerEntity customer = new CustomerEntity();
        customer = doGetDetailPelanggan(customerModel);

        if(customerModel.getCustKtp() != null) {
            Long count = customerRepo.checkDuplicateKtp(customerModel.getCustKtp(), customerModel.getCustId());
            if(count > 0) {
                throw new ClientException("Customer KTP already exists");
            }

            customer.setCustKtp(customerModel.getCustKtp());
        }

        if(customerModel.getCustHp() != null) {
            Long count = customerRepo.checkDuplicateHp(customerModel.getCustHp(), customerModel.getCustId());
            if(count > 0) {
                throw new ClientException("Customer phone number already exists");
            }

            customer.setCustHp(customerModel.getCustHp());
        }

        if(customerModel.getCustName() != null) {
            customer.setCustName(customerModel.getCustName());
        }

        if(customerModel.getCustJk() != null) {
            customer.setCustJk(customerModel.getCustJk().toUpperCase());
        }

        if(customerModel.getCustJenisUsahaId() != null) {
            customer.setCustJenisUsahaId(customerModel.getCustJenisUsahaId());
        }

        if(customerModel.getCustLimitTxn() != null) {
            customer.setCustLimitTxn(customerModel.getCustLimitTxn());
        }

        customer.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        customer.setUpdatedBy(customerModel.getActorId());

        return customerRepo.save(customer);
    }

    public CustomerEntity doDeletePelanggan(CustomerModel customerModel) throws ClientException, NoSuchElementException {
        if(!customerRepo.existsById(customerModel.getCustId())) {
            throw new NoSuchElementException("Cannot find customer with id: " + customerModel.getCustId());
        }

        CustomerEntity customer = new CustomerEntity();
        customer = doGetDetailPelanggan(customerModel);

        if(customer.getCustStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Customer id (" + customerModel.getCustId() + ") has already been deleted");
        }

        customer.setCustStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        customer.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        customer.setDeletedBy(customerModel.getActorId());

        return customerRepo.save(customer);
    }

    public ArrayList<JenisUsahaEntity> doGetListJenisUsaha() {
        ArrayList<JenisUsahaEntity> jenisUsaha = jenisUsahaRepo.doGetListJenisUsaha();
        if(jenisUsaha == null) {
            throw new NoSuchElementException("Jenis usaha not found");
        }

        return jenisUsaha;
    }
}
