package id.co.nds.gadai_2022_08_08.repos;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.TrxDendaEntity;

@Repository
public interface TrxDendaRepo extends JpaRepository<TrxDendaEntity, String> {
    @Query(value = "SELECT COUNT(*) FROM \"TX_DENDA_KETERLAMBATAN\" WHERE UPPER(no_transaksi) = UPPER(:no_transaksi)"
        + " AND cicilan_ke = :cicilan_ke"
        + " AND tgl_denda = :tgl_denda", nativeQuery = true)
    long countDuplicate(@Param("no_transaksi") String noTransaksi, @Param("cicilan_ke") Integer cicilanKe, @Param("tgl_denda") Date tglDenda);
}
