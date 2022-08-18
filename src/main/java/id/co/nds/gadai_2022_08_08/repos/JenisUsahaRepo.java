package id.co.nds.gadai_2022_08_08.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.JenisUsahaEntity;
import id.co.nds.gadai_2022_08_08.globals.GlobalConstant;

@Repository
public interface JenisUsahaRepo extends JpaRepository<JenisUsahaEntity, String> {
    @Query(value = "SELECT * FROM ms_jenis_usaha WHERE rec_status = '"
            + GlobalConstant.REC_STATUS_ACTIVE
            + "'", nativeQuery = true)
    ArrayList<JenisUsahaEntity> doGetListJenisUsaha();
}
