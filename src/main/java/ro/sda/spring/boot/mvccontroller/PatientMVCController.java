package ro.sda.spring.boot.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.sda.spring.boot.service.PatientService;

@Controller
public class PatientMVCController {

    private final PatientService patientService;

    @Autowired
    public PatientMVCController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path = "/patient/")
    public String showPatients(Model model) {
        model.addAttribute("patients", this.patientService.findAllPatients());
        return "index";
    }
}
