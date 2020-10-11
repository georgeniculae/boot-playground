package ro.sda.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.entity.Patient;
import ro.sda.spring.boot.exception.NotFoundException;
import ro.sda.spring.boot.repository.PatientRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostConstruct
    public void init() {
        this.initializePatients();
//        this.findPatientById(1l);
//        this.patientsBornAfter(LocalDate.of(1990, 9, 9));
//        this.findPatientsByDateOfBirthBefore(LocalDate.of(1989, 1, 1));
//        this.deletePatientById(3l);
//        this.countPatientByLastName("Popescu");
//        this.findPatientByWeight(BigDecimal.valueOf(80));
    }

    private void initializePatients() {
        List<Patient> patientList = new ArrayList<>();
        Patient patient1 = new Patient("Ion", "Popescu", LocalDate.of(1975, 9, 14), "Aviatorilor", 1l, "200598", 165l, BigDecimal.valueOf(68));
        Patient patient2 = new Patient("Florin", "Calinescu", LocalDate.of(1959, 6, 4), "Zambilelor", 2l, "400876", 172l, BigDecimal.valueOf(83));
        Patient patient3 = new Patient("Alexandru", "Alexandrescu", LocalDate.of(1987, 9, 11), "Republicii", 5L, "100983", 189l, BigDecimal.valueOf(88));
        Patient patient4 = new Patient("Cristian", "Antonescu", LocalDate.of(1991, 7, 29), "Garii", 5l, "209314", 190l, BigDecimal.valueOf(93));
        Patient patient5 = new Patient("Catalin", "Zamfirescu", LocalDate.of(1997, 12, 4), "Aleea Profesorilor", 21l, "100293", 175l, BigDecimal.valueOf(87));
        patientList.addAll(Arrays.asList(patient1, patient2, patient3, patient4, patient5));
        patientRepository.saveAll(patientList);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatientById(Long id) {
        this.findPatientById(id);
        patientRepository.deleteById(id);
    }

    public Patient findPatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            System.out.println(patient.toString());
            return patient;
        } else {
            System.out.println("Patient with id " + id + " does not exist.");
            throw new NotFoundException("Patient with id " + id + " does not exist.");
        }
    }

    public List<Patient> findPatientsByDateOfBirthBefore(LocalDate dateBefore) {
        List<Patient> patients = patientRepository.patientBornAfterGivenDate(dateBefore);
        patients.forEach(patient -> System.out.println(patient));
        return patients;
    }

    public List<Patient> patientsBornAfter(LocalDate dateAfter) {
        List<Patient> patients = patientRepository.patientBornAfterGivenDate(dateAfter);
        System.out.println(patients);
        return patients;
    }

    public Integer countPatientByLastName(String lastName) {
        Integer numberOfOccurrences = patientRepository.countPatientByLastName(lastName);
        System.out.println(numberOfOccurrences);
        return numberOfOccurrences;
    }

    public List<Patient> findPatientByWeight(BigDecimal weight) {
        List<Patient> patientByWeightLessThan = patientRepository.findPatientByWeightLessThan(weight);
        System.out.println(patientByWeightLessThan);
        return patientByWeightLessThan;
    }
}