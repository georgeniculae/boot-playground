package ro.sda.spring.boot.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PatientTreatmentKey implements Serializable {

    private Long patientId;
    private Long treatmentId;

    public PatientTreatmentKey(Long patientId, Long treatmentId) {
        this.patientId = patientId;
        this.treatmentId = treatmentId;
    }

    public PatientTreatmentKey() {
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }
}
