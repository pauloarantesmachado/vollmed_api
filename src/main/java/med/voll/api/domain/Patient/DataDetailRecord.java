package med.voll.api.domain.Patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.Address;

public record DataDetailRecord(@NotBlank
                               String name,
                               @NotBlank
                               @Email
                               String email,
                               @NotBlank
                               String phone,
                               @NotNull
                               @Valid
                               Address address){
    public DataDetailRecord(Patient patient){
        this(patient.getName(), patient.getEmail(), patient.getPhone(), patient.getAddress());

    }
}
