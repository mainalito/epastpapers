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

////JAVA 14 RECORDS AS DTOS
//public record UserRegistrationDto(String firstName, String lastName,
//                                  String email, String userName, String password){
//
//    public UserRegistrationDto(String firstName, String lastName, String email, String userName, String password) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.userName = userName;
//        this.password = password;
//    }
//
//}