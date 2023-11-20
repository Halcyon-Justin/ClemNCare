package halcyon.clemncare.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Guardian;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian, Long>{
    
}
