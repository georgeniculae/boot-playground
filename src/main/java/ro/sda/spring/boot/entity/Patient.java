package ro.sda.spring.boot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Patient extends BaseEntity {

    @Column(nullable = false, length = 32)
    @NotBlank(message = "First name cannot be blank.")
    private String firstName;

    @Column(nullable = false, length = 32)
    @NotBlank(message = "Last name cannot be blank.")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "Date of birth cannot be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(nullable = true, length = 128)
    private String street;

    @Column(nullable = true, length = 32)
    private Long streetNo;

    @Column(nullable = true, length = 32)
    private String postalCode;

    @Column(nullable = true, length = 32)
    //@NotBlank(message = "Height cannot be blank.")
    private Long height;

    @Column(nullable = true, length = 32)
    //@NotBlank(message = "Weight cannot be blank.")
    private BigDecimal weight;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Doctor doctor;

    @OneToMany(mappedBy = "treatment")
    private List<PatientTreatment> patientTreatments = new ArrayList<>();

    public Patient(String firstName, String lastName, LocalDate dateOfBirth, String street, Long streetNo, String postalCode, Long height, BigDecimal weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.streetNo = streetNo;
        this.postalCode = postalCode;
        this.height = height;
        this.weight = weight;
    }

    public Patient() {
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(lastName, patient.lastName) &&
                Objects.equals(dateOfBirth, patient.dateOfBirth) &&
                Objects.equals(street, patient.street) &&
                Objects.equals(streetNo, patient.streetNo) &&
                Objects.equals(postalCode, patient.postalCode) &&
                Objects.equals(height, patient.height) &&
                Objects.equals(weight, patient.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, street, streetNo, postalCode, height, weight);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", street='" + street + '\'' +
                ", streetNo='" + streetNo + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}