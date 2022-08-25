package id.co.nds.gadai_2022_08_08.Controllers;

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
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.UpdatingById;
import id.co.nds.gadai_2022_08_08.entities.BarangEntity;
// import id.co.nds.gadai_2022_08_08.entities.InfoBarangEntity;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.models.BarangModel;
import id.co.nds.gadai_2022_08_08.models.ResponseModel;
import id.co.nds.gadai_2022_08_08.services.BarangService;

@RestController
@Validated
@RequestMapping("/barang")
public class BarangController {
    @Autowired
    private BarangService BarangService;

    @PostMapping(value = "/doInsert")
    public ResponseEntity<ResponseModel> addBarangController(
            @Validated(PostingNew.class) @RequestBody BarangModel BarangModel) {
        try {
            // Request
            BarangEntity Barang = BarangService.add(BarangModel);
            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("New Barang is Successfully added");
            response.setData(Barang);

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

    @GetMapping(value = "/doSearchBarang")
    public ResponseEntity<ResponseModel> searchBarangController(
            @Validated(GettingAllByCriteria.class) @RequestBody BarangModel BarangModel) {
        try {
            List<BarangEntity> Barang = BarangService.findAllByCriteria(BarangModel);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(Barang);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/doGetDetailBarang/{noTransaksi}")
    public ResponseEntity<ResponseModel> searchProductController(@PathVariable String noTransaksi) {
        try {
            BarangEntity Barang = BarangService.findById(noTransaksi);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(Barang);
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

    @PutMapping(value = "/doUpdate")
    public ResponseEntity<ResponseModel> putProductController(
            @Validated(UpdatingById.class) @RequestBody BarangModel BarangModel) {
        try {
            BarangEntity Barang = BarangService.edit(BarangModel);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(Barang);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @DeleteMapping(value = "/doDelete")
    public ResponseEntity<ResponseModel> deleteProductController(
            @Validated(DeletingById.class) @RequestBody BarangModel BarangModel) {
        try {
            BarangEntity Barang = BarangService.delete(BarangModel);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(Barang);
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
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

   
}
