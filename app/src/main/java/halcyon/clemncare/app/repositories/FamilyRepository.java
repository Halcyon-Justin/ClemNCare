package halcyon.clemncare.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    
}
