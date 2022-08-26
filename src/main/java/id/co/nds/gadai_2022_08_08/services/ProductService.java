package id.co.nds.gadai_2022_08_08.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.exceptions.ClientException;
import id.co.nds.gadai_2022_08_08.exceptions.NotFoundException;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;
import id.co.nds.gadai_2022_08_08.models.ProductModel;
import id.co.nds.gadai_2022_08_08.repos.ProductRepo;
import id.co.nds.gadai_2022_08_08.repos.specs.ProductSpec;
import id.co.nds.gadai_2022_08_08.validators.ProductValidator;

@Service
public class ProductService implements Serializable {
    @Autowired
    private ProductRepo productRepo;

    ProductValidator productValidator = new ProductValidator();

    public ProductEntity add(ProductModel productModel) throws ClientException {
        productValidator.nullCheckId(productModel.getProductId());
        productValidator.validateproductId(productModel.getActorId());
        productValidator.nullCheckName(productModel.getProductName());
        productValidator.validateName(productModel.getProductName());
        productValidator.validateJangkaWaktu(productModel.getProductJangkaWaktu());
        productValidator.validateLtv(productModel.getProductLtv().doubleValue());
        productValidator.validateBiayaAdminBuka(productModel.getProductBiayaAdminBuka().doubleValue());
        productValidator.validateBiayaAdminTutup(productModel.getProductBiayaAdminTutup().doubleValue());
        productValidator.validateJasaPenyPeriode(productModel.getProductBiayaJasaPenyPeriode(),
                productModel.getProductJangkaWaktu());
        productValidator.validateBiayaDenda(productModel.getProductBiayaDenda().doubleValue());

        Long count = productRepo.countByName(productModel.getProductName());
        if (count > 0) {
            throw new ClientException("Product is already exists");
        }

        ProductEntity product = new ProductEntity();
        product.setProductId(productModel.getActorId());
        product.setProductName(productModel.getProductName());
        product.setProductType(productModel.getProductType());
        product.setProductDesc(productModel.getProductDesc());
        product.setProductTenor(productModel.getProductJangkaWaktu());
        product.setProductLtv(productModel.getProductLtv().doubleValue());
        product.setBiayaAdmBukaVal(productModel.getProductBiayaAdminBuka().doubleValue());
        product.setBiayaAdmTutupVal(productModel.getProductBiayaAdminTutup().doubleValue());
        product.setBiayaJsPenyRate(productModel.getProductBiayaJasaPeny().doubleValue());
        product.setBiayaJsPenyPer(productModel.getProductBiayaJasaPenyPeriode());
        product.setBiayaDendaKeterlambatanrate(productModel.getProductBiayaDenda().doubleValue());
        product.setBiayaDendaKeterlambatanPer(productModel.getProductBiayaDendaperiode());
        product.setCreatedBy(productModel.getActorId());
        product.setRecStatus(GlobalConstanst.REC_STATUS_ACTIVE);
        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        return productRepo.save(product);
    }

    public ProductEntity findById(String ProductId) throws ClientException, NotFoundException {
        productValidator.nullCheckId(ProductId);
        productValidator.validateproductId(ProductId);

        ProductEntity product = productRepo.findById(ProductId).orElse(null);
        productValidator.nullCheckObject(product);
        return product;
    }

    public List<ProductEntity> findAllByCriteria(ProductModel productModel) throws ClientException {
        productValidator.range("LTV", productModel.getProductLtvBefore().doubleValue(),
                productModel.getProductLtvAfter().doubleValue());
        productValidator.range("Biaya Jasa Penyimpanan", productModel.getProductBiayaJasaPenyBefore().doubleValue(),
                productModel.getProductBiayaJasaPenyAfter().doubleValue());
        List<ProductEntity> product = new ArrayList<>();
        ProductSpec spec = new ProductSpec(productModel);
        productRepo.findAll(spec).forEach(product::add);
        return product;
    }

    public ProductEntity edit(ProductModel ProductModel) throws ClientException, NotFoundException {
        productValidator.nullCheckId(ProductModel.getProductId());
        productValidator.validateproductId(ProductModel.getProductId());
        if (!productRepo.existsById(ProductModel.getProductId())) {
            throw new NotFoundException("Cannot find Product with id = " + ProductModel.getProductId());
        }

        ProductEntity Product = new ProductEntity();
        Product = findById(ProductModel.getProductId());

        if (ProductModel.getProductName() != null) {
            productValidator.validateName(ProductModel.getProductName());
            Product.setProductName(ProductModel.getProductName());
        }

        if (ProductModel.getProductType() != null) {
            Product.setProductType(ProductModel.getProductType());
        }

        if (ProductModel.getProductDesc() != null) {
            productValidator.validateDesc(ProductModel.getProductDesc());
            Product.setProductDesc(ProductModel.getProductDesc());
        }

        if (ProductModel.getProductLtv() != null) {
            productValidator.validateLtv(ProductModel.getProductLtv().doubleValue());
            Product.setProductLtv(ProductModel.getProductLtv().doubleValue());
        }
        if (ProductModel.getProductBiayaAdminBuka() != null) {
            productValidator.validateBiayaAdminBuka(ProductModel.getProductBiayaAdminBuka().doubleValue());
            Product.setBiayaAdmBukaVal(ProductModel.getProductBiayaAdminBuka().doubleValue());
        }
        if (ProductModel.getProductBiayaAdminBukaTipe() != null) {
            Product.setBiayaAdmBukaType(ProductModel.getProductBiayaAdminBukaTipe());
        }
        if (ProductModel.getProductBiayaAdminTutup() != null) {
            productValidator.validateBiayaAdminTutup(ProductModel.getProductBiayaAdminTutup().doubleValue());
            Product.setBiayaAdmTutupVal(ProductModel.getProductBiayaAdminTutup().doubleValue());
        }
        if (ProductModel.getProductBiayaAdminTutupTipe() != null) {
            Product.setBiayaAdmTutupType(ProductModel.getProductBiayaAdminTutupTipe());
        }
        if (ProductModel.getProductBiayaJasaPeny() != null) {
            productValidator.validateBiayaJasaPeny(ProductModel.getProductBiayaJasaPeny().doubleValue());
            Product.setBiayaJsPenyRate(ProductModel.getProductBiayaJasaPeny().doubleValue());
        }
        if (ProductModel.getProductBiayaJasaPenyPeriode() != null) {
            productValidator.validateBiayaJasaPenyPeriode(ProductModel.getProductBiayaJasaPenyPeriode());
            productValidator.validateJasaPenyPeriode(ProductModel.getProductBiayaJasaPenyPeriode(),
                    ProductModel.getProductJangkaWaktu());
            Product.setBiayaJsPenyPer(ProductModel.getProductBiayaJasaPenyPeriode());
        }
        if (ProductModel.getProductBiayaDenda() != null) {
            productValidator.validateBiayaDenda(ProductModel.getProductBiayaDenda().doubleValue());
            Product.setBiayaDendaKeterlambatanrate(ProductModel.getProductBiayaDenda().doubleValue());
        }
        if (ProductModel.getProductBiayaDendaperiode() != null) {
            productValidator.validateBiayaDendaPeriode(ProductModel.getProductBiayaDendaperiode());
            Product.setBiayaDendaKeterlambatanPer(ProductModel.getProductBiayaDendaperiode());
        }

        Product.setUpdatedBy(ProductModel.getActorId());

        return productRepo.save(Product);
    }

    public ProductEntity delete(ProductModel ProductModel) throws ClientException, NotFoundException {
        productValidator.nullCheckId(ProductModel.getProductId());
        productValidator.validateproductId(ProductModel.getProductId());
        if (!productRepo.existsById(ProductModel.getProductId())) {
            throw new NotFoundException("Cannot find Product with id : " + ProductModel.getProductId());
        }

        // proses
        ProductEntity Product = new ProductEntity();
        Product = findById(ProductModel.getProductId());
        if (Product.getRecStatus().equalsIgnoreCase(GlobalConstanst.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Product id ( " + ProductModel.getProductId() + " ) is already been deleted.");
        }

        Product.setRecStatus(GlobalConstanst.REC_STATUS_NON_ACTIVE);
        Product.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        Product.setDeletedBy(ProductModel.getActorId());

        return productRepo.save(Product);
    }
}
