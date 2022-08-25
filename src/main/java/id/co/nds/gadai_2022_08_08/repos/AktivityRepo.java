package id.co.nds.gadai_2022_08_08.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.domains.noTransaksi;
import id.co.nds.gadai_2022_08_08.entities.CicilanEntity;

public interface AktivityRepo extends JpaRepository<CicilanEntity, noTransaksi> {
    @Query(value = "SELECT * FROM tx_cicilan WHERE UPPER(tx_status) = 'BELUM AKTIF'"
            + " AND tanggal_aktif <= CURRENT_DATE", nativeQuery = true)
    List<CicilanEntity> Aktif();

    @Query(value = "SELECT * FROM tx_cicilan WHERE UPPER(tx_status) = 'AKTIF'"
            + " AND tanggal_jatuh_tempo < CURRENT_DATE", nativeQuery = true)
    List<CicilanEntity> Terlambat();

    @Query(value = "SELECT * FROM tx_cicilan WHERE UPPER(tx_status) = 'TERLAMBAT'", nativeQuery = true)
    List<CicilanEntity> BelumBayar();
}
