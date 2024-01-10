package med.voll.api.domain.consultation.validators;

import jakarta.validation.ValidationException;
import med.voll.api.domain.Patient.PatientRepository;
import med.voll.api.domain.consultation.DataSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientActive   implements  ValidationScheduleConsultation{
    @Autowired
    private PatientRepository repository;

    public void validator(DataSchedule dataSchedule){
        var patientIsActive = repository.findStatusById(dataSchedule.idPatient());
        if(!patientIsActive){
            throw new ValidationException("Consultation can not schedule with patient not active");
        }

    }
}
