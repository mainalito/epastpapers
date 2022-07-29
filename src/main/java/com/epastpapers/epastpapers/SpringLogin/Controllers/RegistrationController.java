package com.epastpapers.epastpapers.SpringLogin.Controllers;

import java.util.regex.Pattern;

import com.epastpapers.epastpapers.SpringLogin.Repository.UserRepository;
import com.epastpapers.epastpapers.SpringLogin.Service.UserService;
import com.epastpapers.epastpapers.SpringLogin.UserDTO.UserRegistrationDto;
import com.epastpapers.epastpapers.SpringLogin.models.Users;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private  UserService userService;
    private  UserRepository userRepository;

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

    public static boolean checkForRegErrors(String registrationNumber) {
        Pattern pattern = Pattern.compile("^\\w{3}[- .]\\w{3}[- .]000[/ .]\\w{4}$");
        return pattern.matcher(registrationNumber).matches();
    }

    @PostMapping
    public String registerUserAccount(RedirectAttributes redirectAttributes,
            @ModelAttribute("user") UserRegistrationDto userRegistrationDto) throws Exception {

        Users userPerson = new Users();
        String registrationNumber = userRegistrationDto.getUserName();
        boolean existsUsersByUsername = userRepository.existsByUserName(registrationNumber);
        if (checkForRegex(registrationNumber)
                && checkForRegErrors(registrationNumber) == false
                && existsUsersByUsername == false) {
            userPerson.setUserName(registrationNumber);
            userService.saveByRegisterUser(userRegistrationDto);
            return "redirect:/registration?success";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Provide correct registration number");
        return "redirect:/registration";
    }
}
