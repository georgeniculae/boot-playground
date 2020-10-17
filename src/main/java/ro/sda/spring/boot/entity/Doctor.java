package ro.sda.spring.boot.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Doctor extends BaseEntity {

    @Column(nullable = false, length = 32)
    @NotBlank(message = "First name cannot be blank.")
    private String firstName;

    @Column(nullable = false, length = 32)
    @NotBlank(message = "Last name cannot be blank.")
    private String lastName;

    @Column(nullable = true, length = 128)
    private String street;

    @Column(nullable = true)
    private Long streetNumber;

    @Column(nullable = true, length = 32)
    private String postalCode;

    @Column(nullable = false, length = 64)
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email not valid")
    private String email;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Patient> patients;

    public Doctor(String firstName, String lastName, String street, Long streetNumber, String postalCode, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.email = email;
        this.patients = new ArrayList<>();
    }

    public Doctor() {
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) &&
                Objects.equals(firstName, doctor.firstName) &&
                Objects.equals(lastName, doctor.lastName) &&
                Objects.equals(street, doctor.street) &&
                Objects.equals(streetNumber, doctor.streetNumber) &&
                Objects.equals(postalCode, doctor.postalCode) &&
                Objects.equals(email, doctor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, street, streetNumber, postalCode, email);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", postalCode='" + postalCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
