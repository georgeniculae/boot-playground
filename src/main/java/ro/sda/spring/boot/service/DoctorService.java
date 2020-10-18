package ro.sda.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.exception.NotFoundException;
import ro.sda.spring.boot.repository.DoctorRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostConstruct
    public void init() {
        this.createDefaultDoctors();
//        this.findDoctorById(2l);
//        this.countDoctors();
//        this.findAllDoctorsPageable(0, 2);
//        System.out.println("---------- next page ----------");
//        this.findAllDoctorsPageable(1, 2);
//        System.out.println("---------- next page ----------");
//        this.findAllDoctorsPageable(2, 2);
//        this.findByFirstName("Adrian");
//        this.findByFirstNameOrLastName("Adrian", "Juncu");
//        this.findByStreetNumberGreaterThan(1l);
//        System.out.println(this.doctorRepository.countDoctorsByFirstName("Adrian"));
//        System.out.println(this.doctorRepository.countDoctorsByLastName("Juncu"));
    }

    public void createDefaultDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        if (this.doctorRepository.count() == 0) {
            doctors.add(new Doctor("Adrian", "Bobocel", "str. Carpenului", 12l, "500421", "a.bobocel@gmail.com"));
            doctors.add(new Doctor("Adrian", "Rotila", "str. Socului", 45l, "500435", "a.rotila@yahoo.com"));
            doctors.add(new Doctor("Bogdan", "Gabor", "str. Nucului", 5l, "589234", "b.gabor@gmail.com"));
            doctors.add(new Doctor("Constantin", "Juncu", "str. Ciresului", 37l, "457890", "c.juncu@yahoo.com"));
            doctors.add(new Doctor("George", "Niculae", "str. Aviatorilor", 1l, "400193", "g.niculae@yahoo.com"));
            doctorRepository.saveAll(doctors);
        }
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctorById(Long id) {
        this.findDoctorById(id);
        doctorRepository.deleteById(id);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findDoctorById(Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByIdFull(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            System.out.println(doctor.toString());
            return doctor;
        } else {
            System.out.println("Doctor with id " + id + " does not exist.");
            throw new NotFoundException("Doctor with id " + id + " does not exist.");
        }
    }

    public long countDoctors() {
        System.out.println("There are " + doctorRepository.count() + " doctors.");
        return doctorRepository.count();
    }

    public List<Doctor> findAllDoctorsPageable(int page, int size) {
        //page - numarul paginii
        //size - numarul de obiecte din paagina
        Pageable pageable = PageRequest.of(page, size);
        List<Doctor> doctors = doctorRepository.findAll(pageable).get().collect(Collectors.toList());
        doctors.forEach(d -> {
            System.out.println(d.toString());
        });
        return doctors;
    }

    public List<Doctor> findByFirstName(String firstName) {
        List<Doctor> doctors = doctorRepository.findByFirstName(firstName);
        doctors.forEach(d -> System.out.println(d.toString()));
        return doctors;
    }

    public List<Doctor> findByFirstNameOrLastName(String firstName, String lastName) {
        List<Doctor> doctors = doctorRepository.findByFirstNameOrLastName(firstName, lastName);
        doctors.forEach(d -> System.out.println(d.toString()));
        return doctors;
    }

    public List<Doctor> findByStreetNumberGreaterThan(Long id) {
        Doctor doctor = this.findDoctorById(id);
        List<Doctor> doctors = doctorRepository.findByStreetNumberGreaterThan(doctor.getStreetNumber());
        doctors.forEach(d -> System.out.println(d.toString()));
        return doctors;
    }
}
