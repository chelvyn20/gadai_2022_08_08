package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.BarangEntity;

@Repository
public interface BarangRepo extends JpaRepository<BarangEntity, String>, JpaSpecificationExecutor<BarangEntity> {
    @Query(value = "SELECT COUNT(*) from TX_TRANSAKSI_BARANG WHERE LOWER(nama_barang) = LOWER(:namaBarang) ", nativeQuery = true)
    Long countByTransaksi(@Param("namaBarang") String namaBarang);

}
