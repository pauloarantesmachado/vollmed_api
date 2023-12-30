package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.Patient.*;
import med.voll.api.doctor.DataListDoctorRecord;
import med.voll.api.doctor.DataUpdateRecordDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class DoctorPatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public void register(@RequestBody @Valid DataRecordPatient json){
        patientRepository.save(new Patient(json));
    }

    @GetMapping
    public Page<DataListPatientRecord> listPatient(@PageableDefault(size = 10, sort={"name"}) Pageable paginator){
        return patientRepository.findAllByStatusTrue(paginator).map(DataListPatientRecord::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdateRecordPatient data){
        var doctor = patientRepository.getReferenceById(data.id());
        doctor.updateData(data);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var doctor = patientRepository.getReferenceById(id);
        doctor.delete();
    }

}
