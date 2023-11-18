package halcyon.clemncare.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import halcyon.clemncare.app.model.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {
    
}
