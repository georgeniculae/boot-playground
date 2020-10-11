package ro.sda.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.spring.boot.dto.DoctorDTO;
import ro.sda.spring.boot.dto.PageableDoctorResponseDTO;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.service.DoctorService;
import ro.sda.spring.boot.transformer.DoctorTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/doctor")
@CrossOrigin(origins = "*")
//feature de securitate care se asigura ca request-ul vine de la anumita origine, '*' e placeholder pentru oricare origin
//pentru incercari Postman din browser
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorTransformer doctorTransformer;

    @Autowired
    public DoctorController(DoctorService doctorService, DoctorTransformer doctorTransformer) {
        this.doctorService = doctorService;
        this.doctorTransformer = doctorTransformer;
    }

    //@GetMapping(path = "/api/doctor/{id}") inainte sa fie declarat path-ul la RequestMapping
    @GetMapping(path = "/{id}")
    public ResponseEntity<DoctorDTO> findDoctorById(@PathVariable("id") Long id) {
        //gets value from service
        Doctor doctor = doctorService.findDoctorById(id);
        //transform from entity to DTO (Data Transfer Object)
        DoctorDTO doctorDTO = doctorTransformer.transformReversed(doctor);
        //put doctorDTO into response entity
        return ResponseEntity.ok(doctorDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable("id") Long id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping//poate fi lasat gol path-ul
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        //construct the required entity object
        Doctor doctor = doctorTransformer.transform(doctorDTO);
        //assign saved entity to new object
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        //transform from entity DTO
        DoctorDTO savedDoctorDTO = doctorTransformer.transformReversed(savedDoctor);
        //put doctor Dto into response entity
        return ResponseEntity.ok(savedDoctorDTO);
    }

    @PutMapping
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDto) {
        Doctor doctor = doctorTransformer.transform(doctorDto);
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        DoctorDTO savedDoctorDTO = doctorTransformer.transformReversed(savedDoctor);
        return ResponseEntity.ok(savedDoctorDTO);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getByFirstName(@RequestParam(value = "first-name") String firstName) {
        List<Doctor> doctors = doctorService.findByFirstName(firstName);

        //varianta cu enhanced for
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for (Doctor d : doctors) {
            doctorDTOS.add(doctorTransformer.transformReversed(d));
        }
        //varianta cu stream

        //map() schimba tipul elementelor din stream
        //findFirst() returneaza un Optional cu o singura valoare din stream
        //collect() ia toate valorile ramase din stream si le converteste intr-o colectie specificata
        //anyMatch() e un operator terminal care returneaza true daca vreun element din stream se potriveste cu conditia data, altfel returneaza fals

        //List<DoctorDTO> doctorDTOS = doctors.stream().map(DoctorTransformer::transformReversed).collect(Collectors.toList());

        //a doua varianta cu stream
        //List<DoctorDTO> doctorDTOS = doctors.stream().map(d -> DoctorTransformer.transformReversed(d)).collect(Collectors.toList());
        return ResponseEntity.ok(doctorDTOS);
    }

    @GetMapping(path = "/pageable")
    public ResponseEntity<PageableDoctorResponseDTO> getDoctorsPageable(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        List<Doctor> doctors = doctorService.findAllDoctorsPageable(page, size);
        List<DoctorDTO> doctorDTOS = doctors.stream().map(doctorTransformer::transformReversed).collect(Collectors.toList());

        // create pageable response
        PageableDoctorResponseDTO responseDTO = new PageableDoctorResponseDTO();
        responseDTO.setDoctors(doctorDTOS);
        responseDTO.setPage(page);
        responseDTO.setSize(size);
        responseDTO.setTotal(doctorService.countDoctors());

        return ResponseEntity.ok(responseDTO);
    }
}