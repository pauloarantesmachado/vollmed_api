package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Page<Doctor> findAllByStatusTrue(Pageable paginator);

    @Query("""
        select m from Doctor m
               where
               m.status = true
               and
               m.medicalSpecialty = :specialty
               and
               m.id not in(
                   select c.doctor.id from Consultation c
                   where
                   c.date = :date
               )
               order by rand()
               limit 1
    """)
    Doctor chooseDoctorRandom(EnumMedicalSpecialty specialty, LocalDateTime date);

    @Query("""
    select m.status
    from Doctor m
    where
    m.id =:idDoctor

    """)
    Boolean findStatusById(Long idDoctor);
}
