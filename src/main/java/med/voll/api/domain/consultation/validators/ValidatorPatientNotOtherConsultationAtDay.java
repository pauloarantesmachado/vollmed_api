package med.voll.api.domain.consultation.validators;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.domain.consultation.DataSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientNotOtherConsultationAtDay   implements  ValidationScheduleConsultation{
    @Autowired
    private ConsultationRepository repository;
    public void validator(DataSchedule dataSchedule){
        var firstTime = dataSchedule.date().withHour(7);
        var lastTime = dataSchedule.date().withHour(18);
        var patientHasOtherConsultation = repository.existsByPatientIdAndDateBetween(dataSchedule.idPatient(), firstTime, lastTime);
        if(patientHasOtherConsultation){
            throw new ValidationException("patient already has an appointment that day ");
        }
    }
}
