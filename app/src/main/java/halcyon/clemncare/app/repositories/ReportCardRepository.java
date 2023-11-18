package halcyon.clemncare.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import halcyon.clemncare.app.model.ReportCard;

public interface ReportCardRepository extends JpaRepository<ReportCard, Long>{
    
}
