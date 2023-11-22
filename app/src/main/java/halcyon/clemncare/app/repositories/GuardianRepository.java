package halcyon.clemncare.app.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian, Long>{

    @Query("SELECT g.children FROM Guardian g WHERE g.id = :guardianId")
    Set<Child> findChildrenByGuardianId(@Param("guardianId") Long guardianId);
    
}
