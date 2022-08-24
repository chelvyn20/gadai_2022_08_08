package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.nds.gadai_2022_08_08.entities.DendaEntity;

public interface DendaRepo extends JpaRepository<DendaEntity, String>{
    @Query(value = "SELECT COUNT(*) from \"TX_DENDA_KETERLAMBATAN\" WHERE LOWER(id_denda) = LOWER(:idDenda) ", nativeQuery = true)
    Long countByDenda(@Param("idDenda") String idDenda);
}
