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
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.UpdatingById;
import id.co.nds.gadai_2022_08_08.entities.CicilanEntity;
import id.co.nds.gadai_2022_08_08.Controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.models.CicilanModel;
import id.co.nds.gadai_2022_08_08.models.ResponseModel;
import id.co.nds.gadai_2022_08_08.services.CicilanService;

@RestController
@Validated
@RequestMapping("/cicilan")
public class CicilanController {
    @Autowired
    private CicilanService CicilanService;

    @PostMapping(value = "/doInsert")
    public ResponseEntity<ResponseModel> addCicilanController(
            @Validated(PostingNew.class) @RequestBody CicilanModel CicilanModel) {
        try {
            // Request
            CicilanEntity Cicilan = CicilanService.add(CicilanModel);
            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("New Cicilan is Successfully added");
            response.setData(Cicilan);

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

    @GetMapping(value = "/doSearchCicilan")
    public ResponseEntity<ResponseModel> searchCicilanController(
            @Validated(GettingAllByCriteria.class) @RequestBody CicilanModel CicilanModel) {
        try {
            List<CicilanEntity> Cicilan = CicilanService.findAllByCriteria(CicilanModel);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(Cicilan);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/doGetDetailCicilan/{noTransaksi}")
    public ResponseEntity<ResponseModel> searchProductController(@PathVariable String noTransaksi) {
        try {
            CicilanEntity Cicilan = CicilanService.findById(noTransaksi);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(Cicilan);
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
            @Validated(UpdatingById.class) @RequestBody CicilanModel CicilanModel) {
        try {
            CicilanEntity Cicilan = CicilanService.edit(CicilanModel);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(Cicilan);
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
            @Validated(DeletingById.class) @RequestBody CicilanModel CicilanModel) {
        try {
            CicilanEntity Cicilan = CicilanService.delete(CicilanModel);
            // Respon
            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(Cicilan);
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
