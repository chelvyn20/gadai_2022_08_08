package id.co.nds.gadai_2022_08_08.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.models.CustomerModel;

public class CustomerSpec implements Specification<CustomerEntity> {
    private CustomerModel customerModel;

    public CustomerSpec(CustomerModel customerModel) {
        super();
        this.customerModel = customerModel;
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {
        Predicate p = cb.and();

        // KTP
        if (customerModel.getCustKtp() != null) {
            p.getExpressions().add(cb.equal(root.get("custKtp"),
                    customerModel.getCustKtp()));
        }

        // Nama Pelanggan
        if (customerModel.getCustName() != null) {
            p.getExpressions().add(cb.like(cb.lower(root.get("custName")),
                    "%" + customerModel.getCustName().toLowerCase() + "%"));
        }

        // ID Customer
        if (customerModel.getCustId() != null) {
            p.getExpressions()
                    .add(cb.equal(cb.lower(root.get("custId")), customerModel.getCustId().toLowerCase()));
        }

        // Status
        if (customerModel.getCustStatus() != null && !customerModel.getCustStatus().trim().equalsIgnoreCase("")) {
            p.getExpressions()
                    .add(cb.equal(cb.upper(root.get("custStatus")), customerModel.getCustStatus().toUpperCase()));
        }

        // Nomor HP
        if (customerModel.getCustHp() != null) {
            p.getExpressions().add(cb.equal(root.get("custHp"), customerModel.getCustHp()));
        }

        // Jenis Usaha
        if (customerModel.getCustJenisUsahaId() != null) {
            p.getExpressions().add(cb.equal(root.get("custJenisUsahaId"), customerModel.getCustJenisUsahaId()));
        }

        cq.orderBy(cb.asc(root.get("custId")));

        return p;
    }
}
