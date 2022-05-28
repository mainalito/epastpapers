package com.epastpapers.epastpapers.SpringLogin.Security;

import com.epastpapers.epastpapers.SpringLogin.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(2)
public class SpringBootUserSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Autowired
    private UserService userService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.authorizeRequests()
        // // .antMatchers("/exams/new").hasAuthority("ADMIN")
        // .antMatchers("/registration**",
        // "/js/**", "/css/**")
        // .permitAll().anyRequest().authenticated()
        // .and().formLogin().loginPage("/login")
        // .permitAll()
        // .and()
        // .logout()
        // .invalidateHttpSession(true)
        // .clearAuthentication(true)
        // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // .logoutSuccessUrl("/login?logout")
        // .permitAll();
        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and().logout().logoutSuccessUrl("/login");

        http.csrf().disable();
    }

}
