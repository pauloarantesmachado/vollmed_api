package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataRecordDoctor data){
        doctorRepository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DataListDoctorRecord> listDoctor(@PageableDefault(size = 10, sort={"name"}) Pageable paginator){
        return doctorRepository.findAllByStatusTrue(paginator).map(DataListDoctorRecord::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdateRecordDoctor data){
        var doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateData(data);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
    }
}
