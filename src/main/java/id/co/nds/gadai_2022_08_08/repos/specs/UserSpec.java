package id.co.nds.gadai_2022_08_08.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_08_08.entities.UserEntity;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;
import id.co.nds.gadai_2022_08_08.models.UserModel;

public class UserSpec implements Specification<UserEntity> {
    private UserModel userModel;

    public UserSpec(UserModel userModel) {
        super();
        this.userModel = userModel;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        // TODO Auto-generated method stub
        Predicate p = cb.and();

        if (userModel.getUserId() != null && userModel.getUserId().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("userId")), "%" + userModel.getUserId().toLowerCase() + "%"));
        }

        if (userModel.getUserDesc() != null && userModel.getUserDesc().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("userNotes")), "%" + userModel.getUserDesc().toLowerCase() + "%"));
        }

        if (userModel.getUserName() != null && userModel.getUserName().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("userName")), "%" + userModel.getUserName().toLowerCase() + "%"));
        }

        if (userModel.getUserStatus() != null
                && (userModel.getUserStatus().trim().equalsIgnoreCase(GlobalConstanst.REC_STATUS_ACTIVE)
                        || userModel.getUserStatus().trim().equalsIgnoreCase(GlobalConstanst.REC_STATUS_NON_ACTIVE))) {
            p.getExpressions().add(cb.equal(cb.upper(root.get("recStatus")), userModel.getUserStatus().toUpperCase()));
        }

        if (userModel.getUserNoHP() != null && userModel.getUserNoHP().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("userPhone")), "%" + userModel.getUserNoHP().toLowerCase() + "%"));
        }

        if (userModel.getActorId() != null && userModel.getActorId().length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("createdBy")), "%" + userModel.getActorId().toLowerCase() + "%"));
        }

        cq.orderBy(cb.asc(root.get("userId")));
        return p;
    }

}
