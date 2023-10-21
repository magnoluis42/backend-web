package com.project.appjava.service.user;

import com.project.appjava.config.WebSecurityConfig;
import com.project.appjava.dtos.user.UserDTO;
import com.project.appjava.entity.user.User;
import com.project.appjava.enums.RoleName;
import com.project.appjava.exception.EmailExistsException;
import com.project.appjava.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebSecurityConfig webSecurityConfig;

    public UserDTO register(UserDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();

        Optional<User> validateEmail = userRepository.findByEmail(userDTO.getEmail());
        if (validateEmail.isPresent()){
            throw new EmailExistsException();
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(webSecurityConfig.passwordEncoder().encode(user.getPassword()));
        user.setName("Cliente" + user.getName());
        var newUser = userRepository.save(user);
        return userDTO;
    }
    public Optional<UserDTO> findUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        UserDTO userDTO = new ModelMapper().map(user.get(), UserDTO.class);
        return Optional.of(userDTO);
    }
}
