package id.co.nds.gadai_2022_08_08.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.composites.TrxCicilanId;
import id.co.nds.gadai_2022_08_08.entities.TrxCicilanEntity;

@Repository
public interface TrxCicilanRepo extends JpaRepository<TrxCicilanEntity, TrxCicilanId> {
    @Query(value = "SELECT COUNT(*) FROM \"TX_CICILAN\" WHERE no_transaksi = :no_transaksi"
        + " AND UPPER(tx_status) = 'DIBAYAR'", nativeQuery = true)
    long countDibayar(@Param("no_transaksi") String noTransaksi);

    @Query(value = "SELECT COUNT(*) FROM \"TX_CICILAN\" WHERE no_transaksi = :no_transaksi"
        + " AND UPPER(tx_status) = 'AKTIF'"
        + " AND tanggal_jatuh_tempo = CURRENT_DATE", nativeQuery = true)
    long countJatuhTempo(@Param("no_transaksi") String noTransaksi);

    @Query(value = "SELECT * FROM \"TX_CICILAN\" WHERE UPPER(tx_status) = 'BELUM AKTIF'"
        + " AND tanggal_aktif <= CURRENT_DATE", nativeQuery = true)
    List<TrxCicilanEntity> getChangeToActive();

    @Query(value = "SELECT * FROM \"TX_CICILAN\" WHERE UPPER(tx_status) = 'AKTIF'"
        + " AND tanggal_jatuh_tempo < CURRENT_DATE", nativeQuery = true)
    List<TrxCicilanEntity> getChangeToLate();

    @Query(value = "SELECT * FROM \"TX_CICILAN\" WHERE UPPER(tx_status) = 'TERLAMBAT' ORDER BY no_transaksi, cicilan_ke", nativeQuery = true)
    List<TrxCicilanEntity> getLateCicilan();
}
