package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.BarangEntity;

@Repository
public interface BarangRepo extends JpaRepository<BarangEntity, String>{
    @Query(value = "SELECT COUNT(*) from tx_transaksi_barang WHERE LOWER(no_transaksi) = LOWER(:noTransaksi) ", nativeQuery = true)
    Long countByTransaksi(@Param("noTransaksi") String noTransaksi);

}
