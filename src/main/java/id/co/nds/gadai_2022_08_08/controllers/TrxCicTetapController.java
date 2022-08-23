package id.co.nds.gadai_2022_08_08.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.ProcessData;
import id.co.nds.gadai_2022_08_08.entities.TrxCicTetapEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.models.ResponseModel;
import id.co.nds.gadai_2022_08_08.models.TrxCicTetapModel;
import id.co.nds.gadai_2022_08_08.objects.CustomerTransaction;
import id.co.nds.gadai_2022_08_08.objects.ProductTransaction;
import id.co.nds.gadai_2022_08_08.services.TrxCicTetapService;

@RestController
@RequestMapping("/txtetap")
public class TrxCicTetapController {
    @Autowired
    TrxCicTetapService trxCicTetapService;

    @GetMapping(value="/get/search")
    public ResponseEntity<ResponseModel> doSearchTransCicTetap(@Validated(GettingAllByCriteria.class) @RequestBody TrxCicTetapModel trxCicTetapModel) throws ParseException, ClientException {
        TrxCicTetapEntity[] trx = trxCicTetapService.doSearchTransCicTetap(trxCicTetapModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(trx);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value="/get/detail")
    public ResponseEntity<ResponseModel> doGetDetailCicTetap(@Validated(GettingById.class) @RequestBody TrxCicTetapModel trxCicTetapModel) {
        TrxCicTetapEntity trx = trxCicTetapService.doGetDetailCicTetap(trxCicTetapModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(trx);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value="/get/customer")
    public ResponseEntity<ResponseModel> doSearchPelanggan(@Validated(GettingAllByCriteria.class) @RequestBody TrxCicTetapModel trxCicTetapModel) {
        CustomerTransaction[] customers = trxCicTetapService.doSearchPelanggan(trxCicTetapModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value="/get/product")
    public ResponseEntity<ResponseModel> doGetListProduk(@Validated(GettingAllByCriteria.class) @RequestBody TrxCicTetapModel trxCicTetapModel) throws ClientException {
        ProductTransaction[] products = trxCicTetapService.doGetListProduk(trxCicTetapModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value="/get/calculate")
    public ResponseEntity<ResponseModel> doHitungTrxCicTetap(@Validated(ProcessData.class) @RequestBody TrxCicTetapModel trxCicTetapModel) {
        TrxCicTetapEntity trx = trxCicTetapService.doHitungTrxCicTetap(trxCicTetapModel);
        
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(trx);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value="/add")
    public ResponseEntity<ResponseModel> doSaveTrxCicTetap(@Validated(PostingNew.class) @RequestBody TrxCicTetapModel trxCicTetapModel) throws ClientException {
        TrxCicTetapEntity trx = trxCicTetapService.doSaveTrxCicTetap(trxCicTetapModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Sukses simpan dengan nomor transaksi {" + trx.getNoTransaksi() + "}");
        response.setData(trx);
        return ResponseEntity.ok(response);
    }
}
