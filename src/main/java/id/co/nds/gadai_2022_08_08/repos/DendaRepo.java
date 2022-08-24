package id.co.nds.gadai_2022_08_08.repos;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.nds.gadai_2022_08_08.entities.DendaEntity;

public interface DendaRepo extends JpaRepository<DendaEntity, String>{
    @Query(value = "SELECT COUNT(*) from \"TX_DENDA_KETERLAMBATAN\" WHERE LOWER(no_transaksi) = LOWER(:noTransaksi)  AND cicilan_ke = :cicilanKe AND tgl_denda = :tglDenda", 
            nativeQuery = true) 
    long count(@Param("noTransaksi") String noTransaksi, @Param("cicilanKe") Integer cicilanKe, @Param("tglDenda") Date tglDenda);
}
