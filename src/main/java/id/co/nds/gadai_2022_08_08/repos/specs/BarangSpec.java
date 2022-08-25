package id.co.nds.gadai_2022_08_08.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_08_08.entities.BarangEntity;
import id.co.nds.gadai_2022_08_08.models.BarangModel;

public class BarangSpec implements Specification<BarangEntity> {
    private BarangModel barangModel;

    public BarangSpec(BarangModel barangModel) {
        super();
        this.barangModel = barangModel;
    }

    @Override
    public Predicate toPredicate(Root<BarangEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
    
        // private String noTransaksi;
        // private String namaBarang;
        // private Integer noUrut;
        // private String kondisi;
        // private Integer jumlah;
        // private Double hargaPerSatuan;
        Predicate p = cb.and();

        if (barangModel.getNoTransaksi() != null && barangModel.getNoTransaksi().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("noTransaksi")),
                            "%" + barangModel.getNoTransaksi().toLowerCase() + "%"));
        }

        if (barangModel.getNoUrut() != null && barangModel.getNoUrut() != 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("noUrut")), "%" + barangModel.getNoUrut()));
        }

        if (barangModel.getKondisi() != null && barangModel.getKondisi().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("kondisi")), "%" + barangModel.getKondisi().toLowerCase() + "%"));
        }

        if (barangModel.getJumlah() != null && barangModel.getJumlah() != 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("noUrut")), "%" + barangModel.getJumlah()));
        }

        cq.orderBy(cb.asc(root.get("noTransaksi")));
        return p;
    }

}
