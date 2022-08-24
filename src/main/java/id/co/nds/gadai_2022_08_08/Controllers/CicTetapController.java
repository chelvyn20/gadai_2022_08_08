package id.co.nds.gadai_2022_08_08.Controllers;

import id.co.nds.gadai_2022_08_08.services.CicTetapService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.UpdatingById;
import id.co.nds.gadai_2022_08_08.domains.CicTetapObject;
import id.co.nds.gadai_2022_08_08.domains.CustNewObject;
import id.co.nds.gadai_2022_08_08.domains.ProdNewObject;
import id.co.nds.gadai_2022_08_08.entities.BarangEntity;
import id.co.nds.gadai_2022_08_08.entities.CicTetapEntity;
import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.entities.InfoBarangEntity;
import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.models.BarangModel;
import id.co.nds.gadai_2022_08_08.models.CicTetapModel;
import id.co.nds.gadai_2022_08_08.models.CustomerModel;
import id.co.nds.gadai_2022_08_08.models.ProductModel;
import id.co.nds.gadai_2022_08_08.models.ResponseModel;
import id.co.nds.gadai_2022_08_08.services.BarangService;

@RestController
@Validated
@RequestMapping("/")
public class CicTetapController {
    @Autowired
    private CicTetapService cicTetapService;

    @GetMapping(value = "/getPelanggan")
    public ResponseEntity<ResponseModel> doSearchPelanggan(
            @Validated(GettingAllByCriteria.class) @RequestBody CicTetapModel cicTetapModel) {
        CustNewObject[] customers = cicTetapService.doSearchPelanggan(cicTetapModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value="/getetail")
    public ResponseEntity<ResponseModel> doGetDetailCicTetap(@PathVariable String noTransaksi) throws ClientException, NotFoundException {
        CicTetapEntity cicilan = cicTetapService.doGetCicTetap(noTransaksi);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(cicilan);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/findDetail")
    public ResponseEntity<ResponseModel> searchTrasactionController(@RequestBody CicTetapModel cicTetapModel) {
        try {
            ProdNewObject[] Product = cicTetapService.doGetListProduct(cicTetapModel);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(Product);
            return ResponseEntity.ok(response);
        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        } catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/getTrans")
    public ResponseEntity<ResponseModel> searchProductController(
            @Validated(PostingNew.class) @RequestBody CicTetapModel cicTetapModel) {
        try {
            CicTetapObject[] cicilan = cicTetapService.doSearchTrans(cicTetapModel);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(cicilan);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/hitung")
    public ResponseEntity<ResponseModel> postProductController(@RequestBody CicTetapModel cicTetapModel) throws ClientException, NotFoundException {
        // Request
        CicTetapEntity cicil = cicTetapService.doHitungTrans(cicTetapModel);
        // Response
        ResponseModel response = new ResponseModel();
        response.setMsg("New Transaction is Successfully added");
        response.setData(cicil);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postBook(@RequestBody CicTetapModel cicTetapModel) throws ClientException {
        try {
            // Request
            CicTetapEntity book = cicTetapService.doSaveTrans(cicTetapModel);
            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("New Transaction is Successfully added");
            response.setData(book);

            return ResponseEntity.ok(response);
        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
