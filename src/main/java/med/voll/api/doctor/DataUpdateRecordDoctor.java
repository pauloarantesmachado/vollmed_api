package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressRecord;

public record DataUpdateRecordDoctor(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressRecord address) {
}
