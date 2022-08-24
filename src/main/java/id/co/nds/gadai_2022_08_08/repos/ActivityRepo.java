package id.co.nds.gadai_2022_08_08.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.nds.gadai_2022_08_08.entities.ActivityEntity;

public interface ActivityRepo extends JpaRepository<ActivityEntity, String> {
    @Query(value = "SELECT COUNT(*) from lg_activities WHERE LOWER(log_by) = LOWER(:logBy) ", nativeQuery = true)
    Long countByActivity(@Param("logBy") String logBy);
}
