package id.co.nds.gadai_2022_08_08.controllers;

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

import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.DeletingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.GettingById;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.PostingNew;
import id.co.nds.gadai_2022_08_08.controllers.ControllerGroup.UpdatingById;
import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.models.ProductModel;
import id.co.nds.gadai_2022_08_08.models.ResponseModel;
import id.co.nds.gadai_2022_08_08.services.ProductService;

@RestController
@Validated
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value="/get/search")
    public ResponseEntity<ResponseModel> doSearchProduk(@Validated(GettingAllByCriteria.class) @RequestBody ProductModel productModel) throws ClientException {
        ProductEntity[] products = productService.doSearchProduk(productModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value="/get/detail")
    public ResponseEntity<ResponseModel> doGetDetailProduk(@Validated(GettingById.class) @RequestBody ProductModel productModel) {
        ProductEntity product = productService.doGetDetailProduk(productModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Request successful");
        response.setData(product);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value="/add")
    public ResponseEntity<ResponseModel> doInsertProduk(@Validated(PostingNew.class) @RequestBody ProductModel productModel) throws ClientException {
        ProductEntity product = productService.doInsertProduk(productModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Produk baru berhasil disimpan");
        response.setData(product);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value="/update")
    public ResponseEntity<ResponseModel> doUpdateProduk(@Validated(UpdatingById.class) @RequestBody ProductModel productModel) throws NoSuchElementException, ClientException {
        ProductEntity product = productService.doUpdateProduk(productModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Sukses ubah data produk dengan ID {" + productModel.getProductId() + "}");
        response.setData(product);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value="/delete")
    public ResponseEntity<ResponseModel> doDeleteProduk(@Validated(DeletingById.class) @RequestBody ProductModel productModel) throws NoSuchElementException, ClientException {
        ProductEntity product = productService.doDeleteProduk(productModel);

        ResponseModel response = new ResponseModel();
        response.setMsg("Sukses hapus data produk {" + productModel.getProductId() + "}");
        response.setData(product);
        return ResponseEntity.ok(response);
    }
}
