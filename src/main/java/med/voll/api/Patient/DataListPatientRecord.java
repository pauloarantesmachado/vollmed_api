package med.voll.api.Patient;

import med.voll.api.doctor.Doctor;

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
