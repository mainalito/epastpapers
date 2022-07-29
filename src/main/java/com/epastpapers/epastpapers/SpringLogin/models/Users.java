package com.epastpapers.epastpapers.SpringLogin.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "userPerson", uniqueConstraints = @UniqueConstraint(columnNames = "registrationNumber"))
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(name = "registrationNumber")
    private String userName;

    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Roles> roles;

    public Users(String firstName, String lastName, String email, String userName, String password,
            List<Roles> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public Users( String email, String password,
                 List<Roles> roles) {
        this.password = password;
        this.roles = roles;
        this.email = email;
    }

   
}
