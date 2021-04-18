package com.zte.sangfor.auth.service;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ZteSangforUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SimpleGrantedAuthority r1 = new SimpleGrantedAuthority("p1");
        SimpleGrantedAuthority r2 = new SimpleGrantedAuthority("p1");
        Set<SimpleGrantedAuthority> powers = new HashSet<SimpleGrantedAuthority>();
        powers.add(r1);
        powers.add(r2);
        //铭文：123，密文：
        UserDetails userDetails = new User("123","$2a$10$KW.ucjmih5J5FUszi2ie6O9HybI2.gguuNfV5sHjFy.WgU0bVYx8.",powers);
        return userDetails;
    }

//    public static void main(String[] args) {
//       String str = BCrypt.hashpw("123",BCrypt.gensalt());
//        System.out.println(str);
//    }
}
