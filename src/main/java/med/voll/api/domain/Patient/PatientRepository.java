package med.voll.api.domain.Patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findAllByStatusTrue(Pageable paginator);

    @Query("""
    select p.status
    from Patient p 
    where 
    p.id = :idPatient
    """)
    Boolean findStatusById(Long idPatient);
}
