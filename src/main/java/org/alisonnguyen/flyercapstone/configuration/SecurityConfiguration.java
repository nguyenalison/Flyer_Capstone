package org.alisonnguyen.flyercapstone.configuration;

import org.alisonnguyen.flyercapstone.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/**
 * SecurityConfiguration class completely refactored
 * IMPORTANT:
 * if you are going to use for specific endpoint more than one user role
 * always use hasAnyRole(...)
 * In order to be customizable always with Thymeleaf successForwardUrl(...) method */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserServiceImpl userDetailsService;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }
    //beans
   //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/",
                                        "/static/**",
                                        "/css/*", "/js/*",
                                        "/sign-up",
                                        "/home",
                                        "/dashboard",
                                        "/loginprocessed",
                                        "/signup-process",
                                        "/calendarlist",
                                        "/addcalendar",
                                        "/delete/**",
                                        "/event-form",
                                        "/edit-calendar-name",
                                        "/saveCalendar",
                                        "/events",
                                        "/edit-event-form",
                                        "/edit/**",
                                        "/update/{id}"

                                ).permitAll()
                                .requestMatchers("/dashboard").hasAnyRole("USER")
                                .requestMatchers("/addcalendar").authenticated()
                                .requestMatchers("/event-form").fullyAuthenticated()
                                .requestMatchers("/dashboard/saveCalendar").fullyAuthenticated()
                                .requestMatchers("/display-all-events").fullyAuthenticated()
                                .requestMatchers("/delete/**").fullyAuthenticated()
                                .requestMatchers("edit/**").fullyAuthenticated()
                                .requestMatchers("update/**").fullyAuthenticated()
                                .anyRequest().authenticated()

                ).formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true) // should point to login page.successForwardUrl("/home") // must be in order thymeleaf securityextras work
                        .permitAll()
                )
                .logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }
}