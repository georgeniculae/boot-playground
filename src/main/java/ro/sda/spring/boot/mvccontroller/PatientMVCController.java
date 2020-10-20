package ro.sda.spring.boot.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.entity.Patient;
import ro.sda.spring.boot.service.PatientService;

import javax.validation.Valid;

@Controller
public class PatientMVCController {

    private final PatientService patientService;

    @Autowired
    public PatientMVCController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(path = "/patient/add")
    public String addPatient(@ModelAttribute("patient") @Valid Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            return "add-patient";
        }
        this.patientService.savePatient(patient);
        return "redirect:/";
    }

    @GetMapping(path = "/patient/delete/{id}")
    public String deletePatientById(@PathVariable("id") Long id) {
        this.patientService.deletePatientById(id);
        return "redirect:/";
    }

    @GetMapping(path = "/signup/patient")
    public String showSignUpPage(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";
    }

    @GetMapping(path = "/patient/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("patient", this.patientService.findPatientById(id));
        return "edit-patient";
    }

    @PostMapping(path = "/patient/update")
    public String editPatient(@ModelAttribute("patient") @Valid Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-patient";
        }
        this.patientService.savePatient(patient);
        return "redirect:/";
    }
}
