package med.voll.api.domain.consultation;

import jakarta.validation.ValidationException;
import med.voll.api.domain.Patient.PatientRepository;
import med.voll.api.domain.consultation.validators.ValidationScheduleConsultation;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedule {
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidationScheduleConsultation> validators;
    public DataScheduleDetails schedule(DataSchedule data){
        if(!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Id patient not exist");
        }
        if(data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())){
            throw new ValidationException("Id doctor not exist");
        }
        validators.forEach(v ->v.validator(data));
        var doctor = chooseDoctor(data);
        if(doctor == null){
            throw new ValidationException("there is no doctor available on that date");
        }
        var patient = patientRepository.findById(data.idPatient()).get();
        var consultation = new Consultation(null, doctor,patient, data.date());

        consultationRepository.save(consultation);

        return new DataScheduleDetails(consultation);
    }

    private Doctor chooseDoctor(DataSchedule data) {
        if(data.idDoctor() != null){
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        if(data.specialty() == null){
            throw new  ValidationException("Mandatory specialty!");
        }

        return doctorRepository.chooseDoctorRandom(data.specialty(), data.date());
    }
}
