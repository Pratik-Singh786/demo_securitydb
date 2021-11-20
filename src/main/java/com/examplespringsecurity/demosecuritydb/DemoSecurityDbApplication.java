package com.examplespringsecurity.demosecuritydb;

import com.examplespringsecurity.demosecuritydb.Models.User;
import com.examplespringsecurity.demosecuritydb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class DemoSecurityDbApplication  implements CommandLineRunner
{

	public static void main(String[] args)
	{
		SpringApplication.run(DemoSecurityDbApplication.class, args);
		System.out.println("hello!!");
	}
	@Autowired
	UserRepository repository;


	@Override
	public void run(String... args) throws Exception
	{


		BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
		String enCodedPwdForJhon=bCryptPasswordEncoder.encode("jhon123");
		String enCodedPwdForJim=bCryptPasswordEncoder.encode("jim123");

		User user = User.builder().username("Jhon")
				.authorities("FACULTY:STUDENT")
				.password(enCodedPwdForJhon)
				.build();

		User user2= User.builder().username("Jim")
				.authorities("STUDENT")
				.password(enCodedPwdForJim)
				.build();

		repository.saveAll(Arrays.asList(user,user2)); //bcz saveall accepts iterable

	}
}
