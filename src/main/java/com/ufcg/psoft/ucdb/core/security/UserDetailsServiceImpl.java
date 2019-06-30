package com.ufcg.psoft.ucdb.core.security;

import com.ufcg.psoft.ucdb.api.repositories.UserRepository;
import com.ufcg.psoft.ucdb.core.models.User;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findById(email).orElse(null);
        if(user == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(user.getEmail(), user.getPassword(), new ArrayList<SimpleGrantedAuthority>());

    }
}
