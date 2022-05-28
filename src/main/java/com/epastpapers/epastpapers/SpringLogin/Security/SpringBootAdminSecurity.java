package com.epastpapers.epastpapers.SpringLogin.Security;

import com.epastpapers.epastpapers.SpringLogin.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(1)
public class SpringBootAdminSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/admin*").authorizeRequests().anyRequest().hasRole("ADMIN")
                // log in
                .and().formLogin().loginPage("/loginAdmin")
                .loginProcessingUrl("/admin_login").failureUrl("/loginAdmin?error=loginError")
                .defaultSuccessUrl("/")
                // logout
                .and()
                .logout().logoutUrl("/admin_logout").logoutSuccessUrl("/protectedLinks").deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedPage("/403").and().csrf().disable();
    }

}
