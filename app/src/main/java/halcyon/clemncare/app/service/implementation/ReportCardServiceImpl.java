package halcyon.clemncare.app.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.ReportCardDTO;
import halcyon.clemncare.app.exception.ChildNotFoundException;
import halcyon.clemncare.app.exception.ReportCardNotFoundException;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.ReportCardRepository;
import halcyon.clemncare.app.service.ReportCardService;

@Service
public class ReportCardServiceImpl implements ReportCardService {

    @Autowired
    ChildRepository childRepository;

    @Autowired
    ReportCardRepository reportCardRepository;

    @Override
    public ReportCard createReportCard(ReportCardDTO reportCardDTO) {
        try {
            Long childId = reportCardDTO.getChildId();
            Child child = childRepository.getById(childId);

            ReportCard reportCard = mapReportCardToDTO(reportCardDTO, child);

            return reportCardRepository.save(reportCard);
        } catch (ChildNotFoundException e) {
            throw new ChildNotFoundException("Child not found. Can not map Report Card to Child.");
        } catch (Exception e) {
            throw new RuntimeException("Error creating report card", e);
        }
    }

    @Override
    public ReportCard updateReportCard(Long id, ReportCardDTO reportCardDTO) {
        Optional<ReportCard> optionalReportCard = reportCardRepository.findById(id);
        if (optionalReportCard.isPresent()) {
            ReportCard existingReportCard = optionalReportCard.get();
            BeanUtils.copyProperties(reportCardDTO, existingReportCard);
            return reportCardRepository.save(existingReportCard);
        } else {
            throw new ReportCardNotFoundException("Report Card with ID " + id + " not found");
        }
    }

    @Override
    public ReportCard partialUpdateReportCard(Long id, ReportCardDTO reportCardDTO) {
        Optional<ReportCard> optionalReportCard = reportCardRepository.findById(id);
        if (optionalReportCard.isPresent()) {
            ReportCard existingReportCard = optionalReportCard.get();
            BeanUtils.copyProperties(reportCardDTO, existingReportCard);
            return reportCardRepository.save(existingReportCard);
        } else {
            throw new ReportCardNotFoundException("Report Card with ID " + id + " not found");
        }
    }

    @Override
    public void deleteReportCard(Long reportCardId) {
        reportCardRepository.deleteById(reportCardId);
    }

    @Override
    public ReportCard getReportCard(Long reportCardId) {
        return reportCardRepository.findById(reportCardId).orElse(null);
    }

    @Override
    public Page<ReportCard> getReportCardsByChildId(Long childId, Pageable pageable) {
        return reportCardRepository.findByChildId(childId, pageable);
    }

    private ReportCard mapReportCardToDTO(ReportCardDTO reportCardDTO, Child child) {
        ReportCard reportCard = new ReportCard();
        reportCard.setChildId(child.getId());
        reportCard.setHasNapped(reportCardDTO.isHasNapped());
        reportCard.setNotes(reportCardDTO.getNotes());
        reportCard.setSendTo(getGuardianEmails(child.getFamily().getGuardians()));
    
        return reportCard;
    }

    private List<String> getGuardianEmails(List<Guardian> guardians) {
        return guardians.stream()
                .map(Guardian::getEmailAddress)
                .collect(Collectors.toList());
    }
    
}
