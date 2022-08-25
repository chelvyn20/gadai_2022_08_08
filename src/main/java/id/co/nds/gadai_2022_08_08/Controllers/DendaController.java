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

import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.models.DendaModel;
import id.co.nds.gadai_2022_08_08.models.ResponseModel;
import id.co.nds.gadai_2022_08_08.services.DendaService;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.UpdatingById;
import id.co.nds.gadai_2022_08_08.entities.DendaEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;

@RestController
@Validated
@RequestMapping("/denda")
public class DendaController {
    @Autowired
    private DendaService dendaService;

    @PostMapping(value = "/doInsert")
    public ResponseEntity<ResponseModel> addBarangController(
            @Validated(PostingNew.class) @RequestBody DendaModel dendaModel) {
        try {
            // Request
            DendaEntity Barang = dendaService.add(dendaModel);
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
            @Validated(GettingAllByCriteria.class) @RequestBody DendaModel DendaModel) {
        try {
            List<DendaEntity> Barang = dendaService.findAllByCriteria(DendaModel);
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
            DendaEntity Barang = dendaService.findById(noTransaksi);
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
            @Validated(UpdatingById.class) @RequestBody DendaModel DendaModel) {
        try {
            DendaEntity Barang = dendaService.edit(DendaModel);
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
            @Validated(DeletingById.class) @RequestBody DendaModel DendaModel) {
        try {
            DendaEntity Barang = dendaService.delete(DendaModel);
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
