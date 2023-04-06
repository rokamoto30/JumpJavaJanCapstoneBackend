package com.cognixia.jump.tutorcapstone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.tutorcapstone.filter.JwtRequestFilter;

@Configuration
public class SecurityConfiguration {

    @Autowired
    JwtRequestFilter jwtRequestFilter;
    
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    protected UserDetailsService userDetailsService(){
        return userDetailsService;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/authenticate").permitAll()
        .antMatchers("/api/subject").permitAll()
        .anyRequest().authenticated()                                                           //any other request is authorized
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
        
        //this request will go through many filters, but first it goes through the jwt filter so if a user is logged in they can use their token instead of checking username & password
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    protected PasswordEncoder encoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder( encoder() );
		
		return authProvider;
	}

    @Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

    
}
