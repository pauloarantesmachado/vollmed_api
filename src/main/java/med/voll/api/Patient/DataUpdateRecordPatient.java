package med.voll.api.Patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressRecord;

public record DataUpdateRecordPatient(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressRecord address) {
}
