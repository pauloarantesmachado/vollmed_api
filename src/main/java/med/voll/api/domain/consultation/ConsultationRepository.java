package med.voll.api.domain.consultation;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);
    boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime dateTime);
}
