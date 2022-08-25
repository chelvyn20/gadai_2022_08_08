package id.co.nds.gadai_2022_08_08.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_08_08.entities.CicilanEntity;
import id.co.nds.gadai_2022_08_08.models.CicilanModel;

public class CicilanSpec implements Specification<CicilanEntity> {
    private CicilanModel CicilanModel;

    public CicilanSpec(CicilanModel CicilanModel) {
        super();
        this.CicilanModel = CicilanModel;
    }

    @Override
    public Predicate toPredicate(Root<CicilanEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        
    //     private Double txPokok;
    // private Double txBunga;
    // private String txStatus;
    // private Date tanggalAktif;
    // private Date tanggalJatuhTempo;
    // private Date tanggalBayar;
        Predicate p = cb.and();

        if (CicilanModel.getNoTransaksi() != null && CicilanModel.getNoTransaksi().hashCode() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("noTransaksi")),
                            "%" + CicilanModel.getNoTransaksi().toString() + "%"));
        }

        if (CicilanModel.getTxBunga() != null ) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("txBunga")), "%" + CicilanModel.getTxBunga()));
        }

        if (CicilanModel.getTxPokok() != null ) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("txPokok")), "%" + CicilanModel.getTxPokok()));
        }

        if (CicilanModel.getCicilanKe() != null && CicilanModel.getCicilanKe() != 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("cicilanKe")), "%" + CicilanModel.getCicilanKe()));
        }

        if (CicilanModel.getTxStatus() != null && CicilanModel.getTxStatus().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("txStatus")), "%" + CicilanModel.getTxStatus().toLowerCase() + "%"));
        }

        if (CicilanModel.getTanggalAktif() != null ) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("tanggalAktif")), "%" + CicilanModel.getTanggalAktif()));
        }

        if (CicilanModel.getTanggalJatuhTempo() != null ) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("tanggalJatuhTempo")), "%" + CicilanModel.getTanggalJatuhTempo()));
        }

        if (CicilanModel.getTanggalBayar() != null ) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("tanggalBayar")), "%" + CicilanModel.getTanggalBayar()));
        }

        cq.orderBy(cb.asc(root.get("noTransaksi")));
        return p;
    }

}
