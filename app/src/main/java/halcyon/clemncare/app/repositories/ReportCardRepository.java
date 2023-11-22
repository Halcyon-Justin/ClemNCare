package halcyon.clemncare.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.ReportCard;
import java.util.List;


@Repository
public interface ReportCardRepository extends JpaRepository<ReportCard, Long> {
    
    List<ReportCard> findByChildId(Long id);
}
