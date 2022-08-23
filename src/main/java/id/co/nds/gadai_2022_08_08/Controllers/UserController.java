package id.co.nds.gadai_2022_08_08.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.UpdatingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.entities.UserEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.models.ResponseModel;
import id.co.nds.gadai_2022_08_08.models.UserModel;
import id.co.nds.gadai_2022_08_08.services.UserService;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/doInsert")
    public ResponseEntity<ResponseModel> addUserController(@Validated(PostingNew.class) @RequestBody UserModel userModel) throws ClientException, ParseException {
        UserEntity user = userService.add(userModel);
        // Response
        ResponseModel response = new ResponseModel();
        response.setMsg("New User is Successfully added");
        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/doSearchUser")
    public ResponseEntity<ResponseModel> searchUserController(@Validated(GettingAllByCriteria.class) @RequestBody UserModel userModel) {
        List<UserEntity> user = userService.findAllByCriteria(userModel);
        // Respon
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/doGetDetailUser")
    public ResponseEntity<ResponseModel> searchProductController(@Validated(GettingById.class) @RequestBody UserModel userModel) throws ClientException, NoSuchElementException {
        UserEntity user = userService.findById(userModel);
        // Respon
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/doUpdate")
    public ResponseEntity<ResponseModel> putProductController(@Validated(UpdatingById.class) @RequestBody UserModel userModel) throws ClientException, NoSuchElementException {
        UserEntity user = userService.edit(userModel);
        // Respon
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/doDelete")
    public ResponseEntity<ResponseModel> deleteProductController(@Validated(DeletingById.class) @RequestBody UserModel userModel) throws ClientException, NoSuchElementException {
        UserEntity user = userService.delete(userModel);
        // Respon
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(user);
        return ResponseEntity.ok(response);
    }
}
