package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressRecord;

public record DataUpdateRecordDoctor(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressRecord address) {
}
