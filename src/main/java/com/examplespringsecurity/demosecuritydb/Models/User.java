package com.examplespringsecurity.demosecuritydb.Models;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.ValueGenerationType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    //Student:Faculty

    public static final String delimiter=":";
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
   @Column(unique=true)
   private String username;
   private String password;
   private String authorities;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        String[] authorities=this.authorities.split(delimiter);
        return  Arrays.stream(authorities)
                .map(x-> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());
    }

    //these methods are define are UserDetails  interface

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
