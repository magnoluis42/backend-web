package com.project.appjava.service.user;

import com.project.appjava.dtos.user.UserDTO;
import com.project.appjava.entity.user.User;
import com.project.appjava.exception.EmailExistsException;
import com.project.appjava.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserDTO register(UserDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();

        User validateEmail = userRepository.findByEmail(userDTO.getEmail());
        if (validateEmail != null){
            throw new EmailExistsException();
        }
        User user = modelMapper.map(userDTO, User.class);
        var newUser = userRepository.save(user);
        return userDTO;
    }
    public Optional<UserDTO> findUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        UserDTO userDTO = new ModelMapper().map(user.get(), UserDTO.class);
        return Optional.of(userDTO);
    }
}
