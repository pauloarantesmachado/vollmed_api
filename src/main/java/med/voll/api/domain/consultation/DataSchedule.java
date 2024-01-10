package med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.EnumMedicalSpecialty;

import java.time.LocalDateTime;

public record DataSchedule(
        Long idDoctor,
        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date,

        EnumMedicalSpecialty specialty
) {

}
