package ro.sda.spring.boot.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientDTO extends BaseDTO {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String street;
    private Long streetNo;
    private String postalCode;
    private Long height;
    private BigDecimal weight;

    private Long doctorId;

    public PatientDTO(Long id, LocalDateTime createdTime, LocalDateTime lastTimeModified, String firstName, String lastName, LocalDate dateOfBirth, String street, Long streetNo, String postalCode, Long height, BigDecimal weight, Long doctorId) {
        super(id, createdTime, lastTimeModified);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.streetNo = streetNo;
        this.postalCode = postalCode;
        this.height = height;
        this.weight = weight;
        this.doctorId = doctorId;
    }

    public PatientDTO() {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(Long streetNo) {
        this.streetNo = streetNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
