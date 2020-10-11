package ro.sda.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.sda.spring.boot.entity.Patient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    //Patient findPatientById(Long id);

    List<Patient> findPatientsByDateOfBirthBefore(LocalDate date);

    @Query(value = "from Patient p where p.dateOfBirth < :parameter", nativeQuery = false)
    List<Patient> patientBornAfterGivenDate(@Param("parameter") LocalDate date);

    Integer countPatientByLastName(String lastName);

    List<Patient> findPatientByWeightLessThan(BigDecimal weight);
}