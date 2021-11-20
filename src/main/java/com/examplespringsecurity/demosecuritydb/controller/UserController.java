package com.examplespringsecurity.demosecuritydb.controller;

import com.examplespringsecurity.demosecuritydb.Models.User;
import com.examplespringsecurity.demosecuritydb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/student/greet")
    public String greetStudent(@RequestParam("name")String name)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "hi Student: " +name;
    }

    @PostMapping("/student/greet2")
    public String greetStudent2(@RequestParam("name")String name)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "hi Student: " +name;
    }

    @GetMapping("/faculty")
    public String getFaculty(@RequestParam("name")String name)

    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();// its just holding the authentication object, i.e details of the user
        return "hi faculty: " +name;
    }
    @GetMapping("/")
    public String greetPublic(@RequestParam("name")String name)

    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "hi Public User: " +name;
    }
    @GetMapping("/signup")
    public void signup(@RequestBody User user)  //try to implement it with other databases like mongodb or any other etc.
        {
            //change the password in the encrypted one

            userRepository.save(user);

            //500 unique constraint failed
            //return an error msg to client  to change to something else


        }




}
