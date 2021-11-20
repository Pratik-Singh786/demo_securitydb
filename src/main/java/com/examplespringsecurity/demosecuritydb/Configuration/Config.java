package com.examplespringsecurity.demosecuritydb.Configuration;

import com.examplespringsecurity.demosecuritydb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config extends WebSecurityConfigurerAdapter
{
    //authentication function
    @Autowired
    UserService service;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //i am not doing inmemory authentication here
        auth.userDetailsService(service);




    }
    //authorization function
    //.Every faculty should be able to operate on student APIs as well

    //1.Either give your faculty multiple authorities
    //2.Give only one authority to the user and while  authorizing check for multiple scopes
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                //.antMatchers("/student/**").hasAuthority("STUDENT")
                .antMatchers(HttpMethod.POST,"/student/greet2/**").hasAuthority("FACULTY")
                .antMatchers(HttpMethod.GET,"/student/*").hasAnyAuthority("STUDENT")//bcz it contains all after student/

                .antMatchers("/faculty/**").hasAuthority("FACULTY")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }
   /* @Bean
    public PasswordEncoder getEncoder()
    {
        return NoOpPasswordEncoder.getInstance();// this  is using the singleton concept
    }*/


    @Bean
    public PasswordEncoder getBEncoder()  // and for this spring is creating a new password everytime.
    {
        return new BCryptPasswordEncoder();//plain password that we are passing in login will be encoded and then matched with  the password
        //present in the inMemoryAuthentication() which is also in encoded form
    }



}
