package med.voll.api.domain.Patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressRecord;

public record DataUpdateRecordPatient(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressRecord address) {
}
