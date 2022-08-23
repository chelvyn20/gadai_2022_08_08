package id.co.nds.gadai_2022_08_08.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;
import id.co.nds.gadai_2022_08_08.models.ProductModel;

public class ProductSpec implements Specification<ProductEntity> {
    private ProductModel productModel;

    public ProductSpec(ProductModel productModel) {
        super();
        this.productModel = productModel;
    }

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        // TODO Auto-generated method stub
        Predicate p = cb.and();

        if (productModel.getProductId() != null && productModel.getProductId().length() > 0) {
            p.getExpressions().add(
                    cb.like(cb.lower(root.get("productId")), "%" + productModel.getProductId().toLowerCase() + "%"));
        }

        if (productModel.getProductType() != null && productModel.getProductType().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("productType")),
                    "%" + productModel.getProductType().toLowerCase() + "%"));
        }

        if (productModel.getProductName() != null && productModel.getProductName().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("productName")),
                    "%" + productModel.getProductName().toLowerCase() + "%"));
        }

        if (productModel.getProductStatus() != null && (productModel.getProductStatus().trim()
                .equalsIgnoreCase(GlobalConstanst.REC_STATUS_ACTIVE)
                || productModel.getProductStatus().trim().equalsIgnoreCase(GlobalConstanst.REC_STATUS_NON_ACTIVE))) {
            p.getExpressions()
                    .add(cb.equal(cb.upper(root.get("recStatus")), productModel.getProductStatus().toUpperCase()));
        }

        if (productModel.getProductLtvBefore() != null && productModel.getProductLtvBefore().doubleValue() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("productLtv")), "%" + productModel.getProductLtvBefore()));
        }

        if (productModel.getProductLtvAfter() != null && productModel.getProductLtvAfter().doubleValue() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("productLtv")), "%" + productModel.getProductLtvAfter()));
        }

        if (productModel.getActorId() != null && productModel.getActorId().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("createdBy")), "%" + productModel.getActorId().toLowerCase() + "%"));
        }

        if (productModel.getProductBiayaJasaPenyBefore() != null
                && productModel.getProductBiayaJasaPenyBefore().doubleValue() > 0) {
            p.getExpressions().add(
                    cb.like(cb.lower(root.get("biayaJsPenyRate")), "%" + productModel.getProductBiayaJasaPenyBefore()));
        }
        if (productModel.getProductBiayaJasaPenyAfter() != null
                && productModel.getProductBiayaJasaPenyAfter().doubleValue() > 0) {
            p.getExpressions().add(
                    cb.like(cb.lower(root.get("biayaJsPenyRate")), "%" + productModel.getProductBiayaJasaPenyAfter()));
        }

        cq.orderBy(cb.asc(root.get("productId")));
        return p;
    }
}
