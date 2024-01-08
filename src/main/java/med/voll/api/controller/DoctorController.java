package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRecordDoctor data, UriComponentsBuilder uriBuilder){
        var doctor = new Doctor(data);
        doctorRepository.save(doctor);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailDoctor(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DataListDoctorRecord>> listDoctor(@PageableDefault(size = 10, sort={"name"}) Pageable paginator){
        var page = doctorRepository.findAllByStatusTrue(paginator).map(DataListDoctorRecord::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateRecordDoctor data){
        var doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateData(data);
        return ResponseEntity.ok(new DataDetailDoctor(doctor));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity details(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailDoctor(doctor));
    }
}
