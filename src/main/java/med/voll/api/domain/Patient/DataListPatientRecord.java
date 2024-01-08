package med.voll.api.domain.Patient;

public record DataListPatientRecord(
        Long id,
        String name,
        String email,
        String phone
) {
    public DataListPatientRecord(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone());
    }
}
