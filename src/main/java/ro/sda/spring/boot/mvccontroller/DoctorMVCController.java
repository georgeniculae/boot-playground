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
import ro.sda.spring.boot.service.DoctorService;
import ro.sda.spring.boot.service.PatientService;

import javax.validation.Valid;

@Controller
public class DoctorMVCController {

    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    public DoctorMVCController(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping
    public String showDoctorsAndPatients(Model model) {
        model.addAttribute("doctors", this.doctorService.findAllDoctors());
        model.addAttribute("patients", this.patientService.findAllPatients());
        return "index";
    }

    //pentru delete in MVC se foloseste get, pentru ca browser-ul face get
    @GetMapping(path = "/doctor/delete/{id}")
    public String deleteDoctorById(@PathVariable("id") Long id, Model model) {
        this.doctorService.deleteDoctorById(id);
        //model.addAttribute("doctors", this.doctorService.findAllDoctors());
        //redirectionare pe localhost:8888
        return "redirect:/";
    }

    @GetMapping(path = "/signup")
    public String showSignUpPage(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";
    }

    @PostMapping(path = "/doctor/add")
    public String addDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-doctor";
        }
        this.doctorService.saveDoctor(doctor);
        return "redirect:/";
    }

    @GetMapping(path = "/doctor/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("doctor", this.doctorService.findDoctorById(id));
        return "edit-doctor";
    }

    @PostMapping(path = "/doctor/update")
    public String editDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-doctor";
        }
        this.doctorService.saveDoctor(doctor);
        return "redirect:/";
    }
}
