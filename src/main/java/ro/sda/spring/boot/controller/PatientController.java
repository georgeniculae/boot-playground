package ro.sda.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.spring.boot.dto.PatientDTO;
import ro.sda.spring.boot.entity.Patient;
import ro.sda.spring.boot.service.PatientService;
import ro.sda.spring.boot.transformer.PatientTransformer;

@RestController
@RequestMapping(path = "/api/patient")
@CrossOrigin(origins = "*") //pentru incercari Postman din browser
public class PatientController {

    private final PatientService patientService;
    private final PatientTransformer patientTransformer;

    @Autowired
    public PatientController(PatientService patientService, PatientTransformer patientTransformer) {
        this.patientService = patientService;
        this.patientTransformer = patientTransformer;
    }

    //C
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        Patient patient = patientTransformer.transform(patientDTO);
        Patient savedPatient = patientService.savePatient(patient);
        PatientDTO savedPatientDTO = patientTransformer.transformReversed(savedPatient);
        return ResponseEntity.ok(savedPatientDTO);
    }

    //R
    @GetMapping(path = "/{id}")
    public ResponseEntity<PatientDTO> findDoctorById(@PathVariable("id") Long id) {
        Patient patient = patientService.findPatientById(id);
        PatientDTO patientDTO = patientTransformer.transformReversed(patient);
        return ResponseEntity.ok(patientDTO);
    }

    //U
    @PutMapping
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
        Patient patient = patientTransformer.transform(patientDTO);
        Patient savedPatient = patientService.savePatient(patient);
        PatientDTO savedPatientDTO = patientTransformer.transformReversed(savedPatient);
        return ResponseEntity.ok(savedPatientDTO);
    }

    //D
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable("id") Long id) {
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }
}
