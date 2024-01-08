package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.Patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
public class DoctorPatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid DataRecordPatient json, UriComponentsBuilder uriBuilder){
        var patient = new Patient(json);
        patientRepository.save(patient);
        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailRecord(patient));

    }

    @GetMapping
    public ResponseEntity<Page<DataListPatientRecord>> listPatient(@PageableDefault(size = 10, sort={"name"}) Pageable paginator){
        var page = patientRepository.findAllByStatusTrue(paginator).map(DataListPatientRecord::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateRecordPatient data){
        var patient = patientRepository.getReferenceById(data.id());
        patient.updateData(data);
        return ResponseEntity.ok(new DataDetailRecord(patient));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = patientRepository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity details(@PathVariable Long id){
        var patient = patientRepository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailRecord(patient));
    }

}
