package med.voll.api.domain.consultation.validators;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DataSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidatorOpeningHours  implements  ValidationScheduleConsultation {

    public void validator(DataSchedule data){
        var dateSchedule = data.date();
        var sunday = dateSchedule.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        var beforeOpen = dateSchedule.getHour() < 7;
        var afterClinicClose = dateSchedule.getHour() > 18;

        if(sunday || beforeOpen || afterClinicClose){
            throw new ValidationException("Consultation outside clinic opening hours");
        }

    }
}
