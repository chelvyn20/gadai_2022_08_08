package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.domains.noTransaksi;
import id.co.nds.gadai_2022_08_08.entities.BarangEntity;
import id.co.nds.gadai_2022_08_08.entities.CicilanEntity;

@Repository
public interface CicilanRepo extends JpaRepository<CicilanEntity, String> {
    @Query(value = "SELECT COUNT(*) from tx_cicilan WHERE LOWER(no_transaksi) = LOWER(:noTransaksi) ", nativeQuery = true)
    Long countByTransaksi(@Param("noTransaksi") String noTransaksi);

    @Query(value = "SELECT COUNT(*) FROM tx_cicilan WHERE no_transaksi = :noTransaksi"
            + " AND UPPER(tx_status) = 'DIBAYAR'", nativeQuery = true)
    long countbayar(@Param("noTransaksi") String noTransaksi);

    @Query(value = "SELECT COUNT(*) FROM tx_cicilan WHERE no_transaksi = :noTransaksi"
            + " AND UPPER(tx_status) = 'AKTIF' AND tanggal_jatuh_tempo = CURRENT_DATE", nativeQuery = true)
    long countTempo(@Param("noTransaksi") String noTransaksi);
}