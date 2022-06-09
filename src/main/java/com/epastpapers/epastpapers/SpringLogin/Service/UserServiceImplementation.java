package com.epastpapers.epastpapers.SpringLogin.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epastpapers.epastpapers.SpringLogin.Repository.UserRepository;
import com.epastpapers.epastpapers.SpringLogin.UserDTO.UserRegistrationDto;
import com.epastpapers.epastpapers.SpringLogin.models.Roles;
import com.epastpapers.epastpapers.SpringLogin.models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
     BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    //LOGIN FUNCTIONALITY
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Users> users = userRepository.findByUserName(username);
       if(users.isEmpty()){
           throw new UsernameNotFoundException("Invalid Username or Password");

       }
       Users user = users.get();
       return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(
           user.getRoles()));
        
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Roles> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
    }

    @Override
    public void saveByRegisterUser(UserRegistrationDto registrationDto) {
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
        Users users = new Users(registrationDto.getFirstName(), registrationDto.getLastName(),
        registrationDto.getEmail(), registrationDto.getUserName().toUpperCase(), encodedPassword,
        Collections.singletonList(new Roles("USER")));
      

        userRepository.save(users);

    }

}
