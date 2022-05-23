package com.epastpapers.epastpapers.SpringLogin.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto{

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
}