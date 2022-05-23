package com.epastpapers.epastpapers.SpringLogin.Controllers;

import java.util.regex.Pattern;

import com.epastpapers.epastpapers.SpringLogin.Service.UserService;
import com.epastpapers.epastpapers.SpringLogin.UserDTO.UserRegistrationDto;
import com.epastpapers.epastpapers.SpringLogin.models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    // private final UserService userService;

    // public RegistrationController(UserService userService) {
    //     this.userService = userService;
    // }

    @GetMapping("/home")
    public String home() {
        return "This is home page";

    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {

        return "registration";
    }

    public static boolean checkForRegex(String registrationNumber) {
        Pattern pattern = Pattern.compile("^\\w{3}[- .]\\w{3}[- .]\\w{3}[/ .]\\w{4}$");
        return pattern.matcher(registrationNumber).matches();
    }

    @PostMapping
    public String registerUserAccount(RedirectAttributes redirectAttributes,
            @ModelAttribute("user") UserRegistrationDto userRegistrationDto) throws Exception {

        Users userPerson = new Users();
        String registrationNumber = userRegistrationDto.getUserName();
        if (checkForRegex(registrationNumber)) {
            userPerson.setUserName(registrationNumber);
            userService.saveByRegisterUser(userRegistrationDto);
            return "redirect:/registration?success";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Provide correct details");
        return "redirect:/registration";
    }
}
