package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.composites.TrxBarangId;
import id.co.nds.gadai_2022_08_08.entities.TrxBarangEntity;

@Repository
public interface TrxBarangRepo extends JpaRepository<TrxBarangEntity, TrxBarangId> {
    
}
