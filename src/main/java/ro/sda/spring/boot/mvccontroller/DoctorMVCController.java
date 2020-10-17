package ro.sda.spring.boot.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.sda.spring.boot.service.DoctorService;

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
        model.addAttribute("doctors", this.doctorService.findAllDoctors());
        return "index";
    }
}
