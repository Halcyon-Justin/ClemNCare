package halcyon.clemncare.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Child;
import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    public List<Child> getChildByFirstName(String firstName);
}