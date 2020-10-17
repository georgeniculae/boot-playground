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

import javax.naming.Binding;

@Controller
public class DoctorMVCController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorMVCController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public String showDoctors(Model model) {
        model.addAttribute("doctors", this.doctorService.findAllDoctors());
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
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor, BindingResult result, Model model) {
        this.doctorService.saveDoctor(doctor);
        return "redirect:/";
    }
}
