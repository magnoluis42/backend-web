package com.project.appjava.controller.user;

import java.util.Optional;

import com.project.appjava.dtos.user.UserDTO;
import com.project.appjava.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.appjava.dtos.user.UserRegisterDTO;
import com.project.appjava.dtos.user.UserResponseDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
    ModelMapper modelMapper = new ModelMapper();
    UserDTO userDTO = modelMapper.map(userRegisterDTO, UserDTO.class);
    var saveUser = userService.register(userDTO);
    return new ResponseEntity<>(modelMapper.map(userDTO, UserResponseDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<UserResponseDTO>> findUserById(@PathVariable(value = "id") Long id){
        Optional<UserDTO> userRecordDTOOptional = userService.findUserById(id);
        if (userRecordDTOOptional.isPresent()){
            UserResponseDTO userResponseDTO = new ModelMapper()
                    .map(userRecordDTOOptional.get(), UserResponseDTO.class);
            return new ResponseEntity<>(Optional.of(userResponseDTO), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
