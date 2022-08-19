package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstant;
import id.co.nds.gadai_2022_08_08.models.ProductModel;
import id.co.nds.gadai_2022_08_08.repos.ProductRepo;
import id.co.nds.gadai_2022_08_08.repos.specs.ProductSpec;
import id.co.nds.gadai_2022_08_08.validators.ProductValidator;

@Service
public class ProductService implements Serializable {
    @Autowired
    ProductRepo productRepo;

    ProductValidator productValidator = new ProductValidator();

    public ProductEntity[] doSearchProduk(ProductModel productModel) throws ClientException {
        productValidator.comparePercentRange("LTV", productModel.getProductLtvBefore(), productModel.getProductLtvAfter());
        productValidator.comparePercentRange("Biaya Jasa Penyimpanan", productModel.getProductBiayaJasaPenyBefore(), productModel.getProductBiayaJasaPenyAfter());

        ArrayList<ProductEntity> products = new ArrayList<>();
        ProductSpec spec = new ProductSpec(productModel);
        productRepo.findAll(spec).forEach(products::add);
        
        ProductEntity[] productsArray = {};
        productsArray = products.toArray(productsArray);
        return productsArray;
    }

    public ProductEntity doGetDetailProduk(ProductModel productModel) throws NoSuchElementException {
        ProductEntity product = productRepo.findById(productModel.getProductId()).orElse(null);
        if(product == null) {
            throw new NoSuchElementException("Product not found");
        }

        return product;
    }

    public ProductEntity doInsertProduk(ProductModel productModel) throws ClientException {
        if(productRepo.existsById(productModel.getProductId())) {
            throw new ClientException("Product ID already exists");
        }

        productValidator.validatePeriode(productModel.getProductBiayaJasaPenyPeriode(), productModel.getProductJangkaWaktu());
        if(productModel.getProductBiayaAdminBukaTipe().equalsIgnoreCase("PERSEN")) {
            productValidator.validatePercentRange("Biaya admin buka", productModel.getProductBiayaAdminBuka().doubleValue());
        }
        if(productModel.getProductBiayaAdminTutupTipe().equalsIgnoreCase("PERSEN")) {
            productValidator.validatePercentRange("Biaya admin tutup", productModel.getProductBiayaAdminTutup().doubleValue());
        }

        ProductEntity product = new ProductEntity();
        product.setProductTipe(productModel.getProductTipe());
        product.setProductId(productModel.getProductId());
        product.setProductName(productModel.getProductName());
        product.setProductDesc(productModel.getProductDesc());
        product.setProductLtv(productModel.getProductLtv());
        product.setProductJangkaWaktu(productModel.getProductJangkaWaktu());
        product.setProductBiayaAdminBukaTipe(productModel.getProductBiayaAdminBukaTipe().toUpperCase());
        product.setProductBiayaAdminBuka(productModel.getProductBiayaAdminBuka());
        product.setProductBiayaAdminTutupTipe(productModel.getProductBiayaAdminTutupTipe().toUpperCase());
        product.setProductBiayaAdminTutup(productModel.getProductBiayaAdminTutup());
        product.setProductBiayaJasaPeny(productModel.getProductBiayaJasaPeny());
        product.setProductBiayaJasaPenyPeriode(productModel.getProductBiayaJasaPenyPeriode());
        product.setProductBiayaDenda(productModel.getProductBiayaDenda());
        product.setProductBiayaDendaPeriode(productModel.getProductBiayaDendaPeriode());

        product.setProductStatus(GlobalConstant.REC_STATUS_ACTIVE);

        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        product.setCreatedBy(productModel.getActorId());

        return productRepo.save(product);
    }

    public ProductEntity doUpdateProduk(ProductModel productModel) throws NoSuchElementException, ClientException {
        if(!productRepo.existsById(productModel.getProductId())) {
            throw new NoSuchElementException("Cannot find product with id: " + productModel.getProductId());
        }

        ProductEntity product = new ProductEntity();
        product = doGetDetailProduk(productModel);

        if(productModel.getProductName() != null) {
            product.setProductName(productModel.getProductName());
        }

        if(productModel.getProductDesc() != null) {
            product.setProductDesc(productModel.getProductDesc());
        }

        if(productModel.getProductLtv() != null) {
            product.setProductLtv(productModel.getProductLtv());
        }

        if(productModel.getProductJangkaWaktu() != null) {
            product.setProductJangkaWaktu(productModel.getProductJangkaWaktu());
        }

        if(productModel.getProductBiayaAdminBukaTipe() != null) {
            product.setProductBiayaAdminBukaTipe(productModel.getProductBiayaAdminBukaTipe().toUpperCase());
        }

        if(productModel.getProductBiayaAdminBuka() != null) {
            product.setProductBiayaAdminBuka(productModel.getProductBiayaAdminBuka());
        }

        if(productModel.getProductBiayaAdminTutupTipe() != null) {
            product.setProductBiayaAdminTutupTipe(productModel.getProductBiayaAdminTutupTipe().toUpperCase());
        }

        if(productModel.getProductBiayaAdminTutup() != null) {
            product.setProductBiayaAdminTutup(productModel.getProductBiayaAdminTutup());
        }

        if(productModel.getProductBiayaJasaPeny() != null) {
            product.setProductBiayaJasaPeny(productModel.getProductBiayaJasaPeny());
        }

        if(productModel.getProductBiayaJasaPenyPeriode() != null) {
            product.setProductBiayaJasaPenyPeriode(productModel.getProductBiayaJasaPenyPeriode());
        }

        if(productModel.getProductBiayaDenda() != null) {
            product.setProductBiayaDenda(productModel.getProductBiayaDenda());
        }

        if(productModel.getProductBiayaDendaPeriode() != null) {
            product.setProductBiayaDendaPeriode(productModel.getProductBiayaDendaPeriode());
        }

        productValidator.validatePeriode(product.getProductBiayaJasaPenyPeriode(), product.getProductJangkaWaktu());
        if(product.getProductBiayaAdminBukaTipe().equalsIgnoreCase("PERSEN")) {
            productValidator.validatePercentRange("Biaya admin buka", product.getProductBiayaAdminBuka().doubleValue());
        }
        if(product.getProductBiayaAdminTutupTipe().equalsIgnoreCase("PERSEN")) {
            productValidator.validatePercentRange("Biaya admin tutup", product.getProductBiayaAdminTutup().doubleValue());
        }

        product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        product.setUpdatedBy(productModel.getActorId());

        return productRepo.save(product);
    }

    public ProductEntity doDeleteProduk(ProductModel productModel) throws NoSuchElementException, ClientException {
        if(!productRepo.existsById(productModel.getProductId())) {
            throw new NoSuchElementException("Cannot find product with id: " + productModel.getProductId());
        }

        ProductEntity product = new ProductEntity();
        product = doGetDetailProduk(productModel);

        if(product.getProductStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Product id (" + productModel.getProductId() + ") has already been deleted");
        }

        product.setProductStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);

        product.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        product.setDeletedBy(productModel.getActorId());

        return productRepo.save(product);
    }
}
