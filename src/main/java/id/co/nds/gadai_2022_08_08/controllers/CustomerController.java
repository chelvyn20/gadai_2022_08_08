package id.co.nds.gadai_2022_08_08.Controllers;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.UpdatingById;
import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.entities.JenisUsahaEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.models.CustomerModel;
import id.co.nds.gadai_2022_08_08.models.ResponseModel;
import id.co.nds.gadai_2022_08_08.services.CustomerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Validated
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/get/search")
    public ResponseEntity<ResponseModel> doSearchPelanggan(
            @Validated(GettingAllByCriteria.class) @RequestBody CustomerModel customerModel) {
        CustomerEntity[] customers = customerService.doSearchPelanggan(customerModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/detail")
    public ResponseEntity<ResponseModel> doGetDetailPelanggan(
            @Validated(GettingById.class) @RequestBody CustomerModel customerModel) {
        CustomerEntity customer = customerService.doGetDetailPelanggan(customerModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(customer);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> doInsertPelanggan(
            @Validated(PostingNew.class) @RequestBody CustomerModel customerModel) throws ClientException {
        CustomerEntity customer = customerService.doInsert(customerModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Sukses Input Data Pelanggan Baru.");
        response.setData(customer);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> doUpdatePelanggan(
            @Validated(UpdatingById.class) @RequestBody CustomerModel customerModel)
            throws NoSuchElementException, ClientException {
        CustomerEntity customer = customerService.doUpdatePelanggan(customerModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Sukses Ubah Data Pelanggan dengan Id {" + customerModel.getCustId() + "}");
        response.setData(customer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseModel> doDeletePelanggan(
            @Validated(DeletingById.class) @RequestBody CustomerModel customerModel)
            throws NoSuchElementException, ClientException {
        CustomerEntity customer = customerService.doDeletePelanggan(customerModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Sukses Hapus Data Pelanggan {" + customerModel.getCustId() + "}");
        response.setData(customer);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/usaha")
    public ResponseEntity<ResponseModel> doGetListJenisUsaha() {
        ArrayList<JenisUsahaEntity> jenisUsaha = customerService.doGetListJenisUsaha();

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(jenisUsaha);
        return ResponseEntity.ok(response);
    }
}
