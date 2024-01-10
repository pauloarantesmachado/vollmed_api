package med.voll.api.domain.consultation.validators;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DataSchedule;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorBeforeConsultation  implements  ValidationScheduleConsultation{

    public void validator(DataSchedule data){
        var consultationDate = data.date();
        var now = LocalDateTime.now();
        var differenceBetweenTime = Duration.between(now, consultationDate).toMinutes();

        if(differenceBetweenTime < 30){
            throw new ValidationException("appointments must be booked 30 minutes in advance ");
        }

    }
}
