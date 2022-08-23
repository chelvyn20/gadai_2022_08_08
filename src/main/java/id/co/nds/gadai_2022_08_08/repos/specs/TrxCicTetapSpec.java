package id.co.nds.gadai_2022_08_08.repos.specs;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.entities.TrxCicTetapEntity;
import id.co.nds.gadai_2022_08_08.models.TrxCicTetapModel;

public class TrxCicTetapSpec implements Specification<TrxCicTetapEntity> {
    private TrxCicTetapModel trxCicTetapModel;

    public TrxCicTetapSpec(TrxCicTetapModel trxCicTetapModel) {
        super();
        this.trxCicTetapModel = trxCicTetapModel;
    }

    @Override
    public Predicate toPredicate(Root<TrxCicTetapEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate p = cb.and();

        Join<TrxCicTetapEntity, ProductEntity> joinProduct = root.join("product");
        Join<TrxCicTetapEntity, CustomerEntity> joinCustomer = root.join("customer");

        // ID Produk
        if (trxCicTetapModel.getProductId() != null) {
            p.getExpressions().add(cb.like(cb.lower(joinProduct.get("productId")),
                    "%" + trxCicTetapModel.getProductId().toLowerCase() + "%"));
        }

        // Nama Produk
        if (trxCicTetapModel.getProductName() != null) {

            p.getExpressions().add(cb.like(cb.lower(joinProduct.get("productName")),
                    "%" + trxCicTetapModel.getProductName().toLowerCase() + "%"));
        }

        // Tanggal Transaksi
        if (trxCicTetapModel.getTrxDateBegin() != null && trxCicTetapModel.getTrxDateEnd() != null) {
            try {
                p.getExpressions().add(cb.between(root.get("tanggalTx"), new SimpleDateFormat("yyyy-MM-dd").parse(trxCicTetapModel.getTrxDateBegin()), new SimpleDateFormat("yyyy-MM-dd").parse(trxCicTetapModel.getTrxDateEnd())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // No Transaksi
        if (trxCicTetapModel.getNoTransaksi() != null) {
            p.getExpressions().add(cb.like(cb.lower(root.get("noTransaksi")),
                    "%" + trxCicTetapModel.getNoTransaksi().toLowerCase() + "%"));
        }

        // Status Transaksi checked in Service

        // Nomor KTP
        if (trxCicTetapModel.getCustKtp() != null) {

            p.getExpressions().add(cb.like(cb.lower(joinCustomer.get("custKtp")),
                    "%" + trxCicTetapModel.getCustKtp().toLowerCase() + "%"));
        }

        // ID Pelanggan
        if (trxCicTetapModel.getCustId() != null) {

            p.getExpressions().add(cb.like(cb.lower(joinCustomer.get("custId")),
                    "%" + trxCicTetapModel.getCustId().toLowerCase() + "%"));
        }

        // Nama Pelanggan
        if (trxCicTetapModel.getCustName() != null) {

            p.getExpressions().add(cb.like(cb.lower(joinCustomer.get("custName")),
                    "%" + trxCicTetapModel.getCustName().toLowerCase() + "%"));
        }

        cq.orderBy(cb.desc(root.get("noTransaksi")));

        return p;
    }

}
