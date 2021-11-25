package com.ironhack.edgeservice.security;

import com.ironhack.edgeservice.model.RoleEntity;
import com.ironhack.edgeservice.model.UserEntity;
import com.ironhack.edgeservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            //user not found
            return null;
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (RoleEntity role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        User userDetails = new User(user.getUsername(),
                                    user.getPassword(),
                                    user.isEnabled(), !user.isExpired(),
                                    !user.isCredentialsexpired(),
                                    !user.isLocked(), grantedAuthorities);
        return userDetails;
    }
}
