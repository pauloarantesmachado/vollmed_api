package med.voll.api.doctor;

public record DataListDoctorRecord(
                                   Long id,
                                   String name,
                                   String email,
                                   String crm,
                                   EnumMedicalSpecialty medicalSpecialty ) {
    public DataListDoctorRecord(Doctor doctor){
            this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getMedicalSpecialty());
    }
}
