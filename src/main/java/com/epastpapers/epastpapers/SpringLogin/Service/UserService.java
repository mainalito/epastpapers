package com.epastpapers.epastpapers.SpringLogin.Service;
import com.epastpapers.epastpapers.SpringLogin.UserDTO.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserService extends UserDetailsService {
    void saveByRegisterUser(UserRegistrationDto registrationDto);
}
