package id.co.nds.gadai_2022_08_08.repos;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.UserEntity;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {
    @Query(value = "SELECT COUNT(*) from ms_user WHERE rec_status = '"
            + GlobalConstanst.REC_STATUS_ACTIVE + "' AND LOWER(user_name) = LOWER(:userName) ", nativeQuery = true)
    long countByName(@Param("userName") String userName);

}
