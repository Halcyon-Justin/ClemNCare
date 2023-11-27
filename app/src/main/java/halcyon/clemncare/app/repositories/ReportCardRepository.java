package halcyon.clemncare.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.ReportCard;


@Repository
public interface ReportCardRepository extends JpaRepository<ReportCard, Long> {
    Optional<ReportCard> getReportCardById(Long id);
    List<ReportCard> findByChildId(Long id);
}
