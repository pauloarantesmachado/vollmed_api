package med.voll.api.domain.consultation.validators;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DataSchedule;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDoctorActive  implements  ValidationScheduleConsultation{
    @Autowired
    private DoctorRepository repository;
    public void validator(DataSchedule dataSchedule){

        if (dataSchedule.idDoctor() == null){
            return;
        }
        var doctorIsActive = repository.findStatusById(dataSchedule.idDoctor());
        if (!doctorIsActive){
            throw new ValidationException("Consultation cannot be scheduled with an inactive doctor ");

        }
    }
}
