package ro.sda.spring.boot.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Treatment extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "patient")
    private List<PatientTreatment> patientTreatments = new ArrayList<>();

    public Treatment(String name) {
        this.name = name;
    }

    public Treatment() {
    }
}
