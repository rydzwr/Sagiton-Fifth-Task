package com.rydzwr.service;

import com.rydzwr.model.User;
import com.rydzwr.model.UserPrincipal;
import com.rydzwr.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("Username Not Found");
        }
        return new UserPrincipal(user);
    }
}
