package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.TrxCicTetapEntity;

@Repository
public interface TrxCicTetapRepo extends JpaRepository<TrxCicTetapEntity, String>, JpaSpecificationExecutor<TrxCicTetapEntity> {
    
}
