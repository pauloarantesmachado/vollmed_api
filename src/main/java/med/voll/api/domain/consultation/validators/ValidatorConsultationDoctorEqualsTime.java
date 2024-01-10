package med.voll.api.domain.consultation.validators;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.domain.consultation.DataSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorConsultationDoctorEqualsTime   implements  ValidationScheduleConsultation{
    @Autowired
    private ConsultationRepository repository;

    public void validator(DataSchedule data){
        var doctorHasConsultationSameTime = repository.existsByDoctorIdAndDate(data.idDoctor(), data.date());
        if(doctorHasConsultationSameTime){
            throw new ValidationException("Doctor has other at the same time");
        }
    }
}
