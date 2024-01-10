package med.voll.api.domain.consultation;

import java.time.LocalDateTime;

public record DataScheduleDetails(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime date
) {
    public DataScheduleDetails(Consultation consultation) {
        this(consultation.getId(), consultation.getDoctor().getId(), consultation.getPatient().getId(), consultation.getDate());
    }
}
