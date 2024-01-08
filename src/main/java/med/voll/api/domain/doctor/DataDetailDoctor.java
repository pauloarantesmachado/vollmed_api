package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

public record DataDetailDoctor(Long id,
                               String name,
                               String email,
                               String phone,
                               String crm,
                               EnumMedicalSpecialty medicalSpecialty,
                               Address address) {
    public DataDetailDoctor(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getMedicalSpecialty(), doctor.getAddress() );
    }
}
