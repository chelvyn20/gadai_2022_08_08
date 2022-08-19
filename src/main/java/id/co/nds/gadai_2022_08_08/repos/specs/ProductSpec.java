package id.co.nds.gadai_2022_08_08.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.models.ProductModel;

public class ProductSpec implements Specification<ProductEntity> {
    private ProductModel productModel;

    public ProductSpec(ProductModel productModel) {
        super();
        this.productModel = productModel;
    }

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate p = cb.and();

        // Tipe Produk
        if (productModel.getProductTipe() != null) {
            p.getExpressions().add(cb.like(cb.lower(root.get("productTipe")),
                    "%" + productModel.getProductTipe().toLowerCase() + "%"));
        }

        // Kode Produk
        if (productModel.getProductId() != null) {
            p.getExpressions()
                    .add(cb.equal(cb.lower(root.get("productId")), productModel.getProductId().toLowerCase()));
        }

        // Nama Produk
        if (productModel.getProductName() != null) {
            p.getExpressions().add(cb.like(cb.lower(root.get("productName")),
                    "%" + productModel.getProductName().toLowerCase() + "%"));
        }

        // Status
        if (productModel.getProductStatus() != null && !productModel.getProductStatus().trim().equalsIgnoreCase("")) {
            p.getExpressions()
                    .add(cb.equal(cb.upper(root.get("productStatus")), productModel.getProductStatus().toUpperCase()));
        }

        // LTV Before After
        if (productModel.getProductLtvBefore() != null && productModel.getProductLtvAfter() != null && productModel.getProductLtvAfter().doubleValue() >= productModel.getProductLtvBefore().doubleValue()) {
            p.getExpressions().add(cb.between(root.get("productLtv"), productModel.getProductLtvBefore(), productModel.getProductLtvAfter()));
        }

        // Biaya Peny Before After
        if (productModel.getProductBiayaJasaPenyBefore() != null && productModel.getProductBiayaJasaPenyAfter() != null && productModel.getProductBiayaJasaPenyAfter().doubleValue() >= productModel.getProductBiayaJasaPenyBefore().doubleValue()) {
            p.getExpressions().add(cb.between(root.get("productBiayaJasaPeny"), productModel.getProductBiayaJasaPenyBefore(), productModel.getProductBiayaJasaPenyAfter()));
        }

        cq.orderBy(cb.asc(root.get("productId")));

        return p;
    }
    
}
