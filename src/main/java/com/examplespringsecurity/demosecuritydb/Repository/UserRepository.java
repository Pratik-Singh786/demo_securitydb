package com.examplespringsecurity.demosecuritydb.Repository;

import com.examplespringsecurity.demosecuritydb.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> //2. param is the datatype of the primary key
{
  public User findByUsername(String name);

}
