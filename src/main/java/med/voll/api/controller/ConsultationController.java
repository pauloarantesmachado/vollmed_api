package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultation.AppointmentSchedule;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.domain.consultation.DataSchedule;
import med.voll.api.domain.consultation.DataScheduleDetails;
import med.voll.api.domain.doctor.DataListDoctorRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultation")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {
    @Autowired
    private AppointmentSchedule schedule;

    @Autowired
    private ConsultationRepository repository;


    @GetMapping
    public ResponseEntity<Page<DataScheduleDetails>> listConsultation(@PageableDefault(size = 10, sort={"date"}) Pageable paginator){
        var page = repository.findAll(paginator).map(DataScheduleDetails::new);
        return ResponseEntity.ok(page);
    }


    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid DataSchedule data){
        var dto = schedule.schedule(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cancel(@PathVariable Long id){
        var consultation = repository.getReferenceById(id);
        repository.delete(consultation);
        return ResponseEntity.noContent().build();
    }

}
