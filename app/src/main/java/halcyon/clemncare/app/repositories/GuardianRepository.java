package halcyon.clemncare.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import halcyon.clemncare.app.model.Guardian;

public interface GuardianRepository extends JpaRepository<Guardian, Long>{
    
}
