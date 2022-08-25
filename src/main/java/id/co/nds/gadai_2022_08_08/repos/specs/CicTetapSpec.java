package id.co.nds.gadai_2022_08_08.repos.specs;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_08_08.entities.CicTetapEntity;
import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
// import id.co.nds.gadai_2022_08_08.models.BarangModel;
import id.co.nds.gadai_2022_08_08.models.CicTetapModel;
// import id.co.nds.gadai_2022_08_08.models.CustomerModel;
// import id.co.nds.gadai_2022_08_08.models.ProductModel;

public class CicTetapSpec implements Specification<CicTetapEntity> {
    private CicTetapModel cicTetapModel;

    public CicTetapSpec(CicTetapModel cicTetapModel) {
        super();
        this.cicTetapModel = cicTetapModel;
    }

    @Override
    public Predicate toPredicate(Root<CicTetapEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        
        Predicate p = cb.and();

        Join<CicTetapEntity, ProductEntity> joinProduct = root.join("product");
        Join<CicTetapEntity, CustomerEntity> joinCustomer = root.join("customer");

        if (cicTetapModel.getNoTransaksi() != null && cicTetapModel.getNoTransaksi().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("noTransaksi")),
                            "%" + cicTetapModel.getNoTransaksi().toLowerCase() + "%"));
        }

        if (cicTetapModel.getCustKtp() != null) {
            p.getExpressions().add(cb.like(cb.lower(joinCustomer.get("custKtp")),
                    "%" + cicTetapModel.getCustKtp() + "%"));
        }

        if (cicTetapModel.getCustName() != null) {

            p.getExpressions().add(cb.like(cb.lower(joinCustomer.get("custName")),
                    "%" + cicTetapModel.getCustName() + "%"));
        }

        if (cicTetapModel.getProductId() != null && cicTetapModel.getProductId().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("productId")),
                            "%" + cicTetapModel.getNoTransaksi().toLowerCase() + "%"));
        }

        if (cicTetapModel.getCustId() != null && cicTetapModel.getCustId().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("customerId")),
                            "%" + cicTetapModel.getCustId().toLowerCase() + "%"));
        }

        if (cicTetapModel.getProductName() != null) {

            p.getExpressions().add(cb.like(cb.lower(joinProduct.get("productName")),
                    "%" + cicTetapModel.getProductName() + "%"));
        }

        if (cicTetapModel.getTrxDateBegin() != null && cicTetapModel.getTrxDateEnd() != null) {
            try {
                p.getExpressions().add(cb.between(root.get("tanggalTx"), new SimpleDateFormat("yyyy-MM-dd").parse(cicTetapModel.getTrxDateBegin()), new SimpleDateFormat("yyyy-MM-dd").parse(cicTetapModel.getTrxDateEnd())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        cq.orderBy(cb.asc(root.get("noTransaksi")));
        return p;
    }

}
