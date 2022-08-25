package id.co.nds.gadai_2022_08_08.repos;

import java.util.List;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.CicTetapEntity;

@Repository
public interface CicTetapRepo extends JpaRepository<CicTetapEntity, String>, JpaSpecificationExecutor<CicTetapEntity> {
    @Query(value = "SELECT COUNT(*) from tx_transaksi_cicilan_tetap WHERE LOWER(no_transaksi) = LOWER(:noTransaksi) ", nativeQuery = true)
    Long countByTransaksi(@Param("noTransaksi") String noTransaksi);

    // @Query(value = "SELECT c.customer_id, a.customer_name, c.created_date, c.no_transaksi, c.product_id, b.product_name, b.product_desc, d.* " +
    //                 "from TX_TRANSAKSI_CICILAN_TETAP as c "+
    //                 "INNER JOIN ms_customer as a ON a.customer_id = c.customer_id "+
    //                 "INNER JOIN ms_product as b ON a.product_id = c.product_id "+
    //                 "INNER JOIN TX_TRANSAKSI_BARANG as d ON a.no_transaksi = d.no_transaksi "+
    //                 "where p.no_transaksi = ?1 ", nativeQuery = true)
    // List<CicTetapEntity> findCicTetap(String noTransaksi);

    // // @Query(value = "select count(*) from TX_TRANSAKSI_CICILAN_TETAP where year(tanggal_tx) = ?1", nativeQuery = true)
    
    }
