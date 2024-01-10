package med.voll.api.domain.doctor;

import med.voll.api.domain.Patient.DataRecordPatient;
import med.voll.api.domain.Patient.Patient;
import med.voll.api.domain.address.AddressRecord;
import med.voll.api.domain.consultation.Consultation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;
    @Test
    @DisplayName("should return null when the only registered doctor is not available on the date ")
    void chooseDoctorRandomScenario1() {
        var date = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var freeDoctor = doctorRepository.chooseDoctorRandom(EnumMedicalSpecialty.CARDIOLOGIA, date );
        var doctor = postDoctor("Test Doctor", "test@voll.med", "123456", EnumMedicalSpecialty.DERMATOLOGIA);
        var patient = postPatient("Test Patient", "patient@voll.med");
        var consultation = postConsultation(doctor, patient, date);

        Assertions.assertThat(freeDoctor).isNull();
    }
//    @Test
//    @DisplayName("should see a doctor if he has a free day")
//    void chooseDoctorRandomScenario2() {
//        var date = LocalDate.now()
//                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
//                .atTime(10, 0);
//
//        var doctor = postDoctor("Test Doctor", "test@voll.med", "123456", EnumMedicalSpecialty.DERMATOLOGIA);
//        var freeDoctor = doctorRepository.chooseDoctorRandom(EnumMedicalSpecialty.CARDIOLOGIA, date );
//        Assertions.assertThat(freeDoctor).isEqualTo(doctor);
//
//    }
    private Consultation postConsultation(Doctor doctor, Patient patient, LocalDateTime date) {
        var consultation =new Consultation(null, doctor, patient, date);
        em.persist(consultation);
        return consultation;

    }

    private Doctor postDoctor(String name, String email, String crm, EnumMedicalSpecialty specialty) {
        var doctor = new Doctor(dataDoctor(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient postPatient(String name, String email) {
        var patient = new Patient(dataPatient(name, email));
        em.persist(patient);
        return patient;
    }

    private DataRecordDoctor dataDoctor(String name, String email, String crm, EnumMedicalSpecialty specialty) {
        return new DataRecordDoctor(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                dataAddress()
        );
    }

    private DataRecordPatient dataPatient(String name, String email) {
        return new DataRecordPatient(
                name,
                email,
                "61999999999",
                dataAddress()
        );
    }

    private AddressRecord dataAddress() {
        return new AddressRecord(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}