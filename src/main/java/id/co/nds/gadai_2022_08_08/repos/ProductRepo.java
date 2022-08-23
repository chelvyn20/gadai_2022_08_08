package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.ProductEntity;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstanst;

public interface ProductRepo extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {
    @Query(value = "SELECT COUNT(*) from ms_product WHERE rec_status = '"
            + GlobalConstanst.REC_STATUS_ACTIVE
            + "' AND LOWER(product_name) = LOWER(:productName) ", nativeQuery = true)
    long countByName(@Param("productName") String productName);
}
