package id.co.nds.gadai_2022_08_08.repos;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_08_08.entities.InfoCicTetapEntity;

@Repository
@Transactional
public interface InfoCicTetapRepo extends JpaRepository<InfoCicTetapEntity, String> {
    @Query(value = "SELECT p.*, c.product_name AS product_name from \"TX_TRANSAKSI_CICILAN_TETAP\" as p" +
            " JOIN ms_product AS c ON p.product_id = c.product_id" +
            " WHERE p.product_id = ?1", nativeQuery = true)
    List<InfoCicTetapEntity> SearchTrans(String productId);

    
}
