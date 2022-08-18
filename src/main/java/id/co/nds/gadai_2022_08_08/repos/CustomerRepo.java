package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.CustomerEntity;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstant;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, String>, JpaSpecificationExecutor<CustomerEntity> {
    @Query(value = "SELECT COUNT(*) FROM ms_customer WHERE rec_status = '"
            + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND LOWER(customer_identity_no) = LOWER(:customer_identity_no)"
            + " AND customer_id != :customer_id", nativeQuery = true)
    long checkDuplicateKtp(@Param("customer_identity_no") String custKtp, @Param("customer_id") String custId);

    @Query(value = "SELECT COUNT(*) FROM ms_customer WHERE rec_status = '"
            + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND LOWER(customer_phone) = LOWER(:customer_phone)"
            + " AND customer_id != :customer_id", nativeQuery = true)
    long checkDuplicateHp(@Param("customer_phone") String custHp, @Param("customer_id") String custId);
}
