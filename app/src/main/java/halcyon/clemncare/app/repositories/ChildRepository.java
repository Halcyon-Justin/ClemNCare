package halcyon.clemncare.app.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    @Query("SELECT c.guardians FROM Child c WHERE c.id = :id")
    Set<Guardian> findGuardiansByChildId(@Param("id") Long id);
}