package ro.sda.spring.boot.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.sda.spring.boot.dto.UserRegisterDTO;
import ro.sda.spring.boot.entity.User;
import ro.sda.spring.boot.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserMVCController {

    private final UserService userService;

    @Autowired
    public UserMVCController(UserService userService) {
        this.userService = userService;
    }

    //se putea pune si path = "/login" in argumentul de la GetMapping
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRegister", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute("userRegister") @Valid UserRegisterDTO userRegisterDTO, BindingResult result) {
        Optional<User> userOptional = userService.findUserByUsername(userRegisterDTO.getUsername());
        if (userOptional.isPresent()) {
            result.rejectValue("username", null, "Username already exists!");
        }
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            result.rejectValue("password", null, "Passwords do not match!");
        }
        if (result.hasErrors()) {
            return "register";
        }
        userService.saveUserDTO(userRegisterDTO);
        return "login";
    }
}
