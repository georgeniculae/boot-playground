package ro.sda.spring.boot.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DoctorDTO extends BaseDTO {

    private String firstName;
    private String lastName;
    private String street;
    private Long streetNumber;
    private String postalCode;
    private String email;

    private List<PatientDTO> patients;

    public DoctorDTO(Long id, LocalDateTime createTime, LocalDateTime lastTimeModified, String firstName, String lastName, String street, Long streetNumber, String postalCode, String email, List<PatientDTO> patients) {
        super(id, createTime, lastTimeModified);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.email = email;
        this.patients = patients;
    }

    public DoctorDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Long streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PatientDTO> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDTO> patients) {
        this.patients = patients;
    }
}
