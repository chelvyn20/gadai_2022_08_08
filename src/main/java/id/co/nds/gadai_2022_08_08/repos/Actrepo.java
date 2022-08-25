package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_08_08.entities.ActivityEntity;

@Repository
public interface Actrepo extends JpaRepository<ActivityEntity, String>{
    

}
