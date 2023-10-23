package com.project.appjava.config;

import com.project.appjava.entity.user.User;
import com.project.appjava.enums.RoleName;
import com.project.appjava.repository.user.RoleRepository;
import com.project.appjava.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) throw new UsernameNotFoundException("Email: " + email + "não existe.");
        else {
            User user = userOptional.get();
            return new org.springframework.security.core.userdetails.User(
                  user.getUsername(),
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    user.getRoles()
            );
        }
    }

}
