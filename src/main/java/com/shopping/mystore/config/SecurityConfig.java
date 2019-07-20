package com.shopping.mystore.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/").hasRole("USER")
                .antMatchers("/checkout").hasRole("USER")
                .antMatchers("/addToCart").hasRole("USER")
                .antMatchers("/getCartCount").hasRole("USER")
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login-error")
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("scott")
                .password(encoder.encode("tiger"))
                .roles("USER")
                .and()
                .withUser("john")
                .password(encoder.encode("snow"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN");
    }


}