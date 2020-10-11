package ro.sda.spring.boot.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.sda.spring.boot.dto.DoctorDTO;
import ro.sda.spring.boot.entity.Doctor;

import java.util.stream.Collectors;

@Component
public class DoctorTransformer {

    @Autowired
    PatientTransformer patientTransformer;

    public Doctor transform(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO, doctor);
        return doctor;
    }

    public DoctorDTO transformReversed(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor, doctorDTO);
        doctorDTO.setPatients(doctor.getPatients().stream().map(patientTransformer::transformReversed).collect(Collectors.toList()));
        return doctorDTO;
    }
}
