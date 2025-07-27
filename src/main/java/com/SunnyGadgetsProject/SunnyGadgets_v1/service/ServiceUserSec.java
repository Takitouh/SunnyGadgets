package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.UserMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryUserSec;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceUserSec implements IServiceUserSec{

    private final IRepositoryUserSec userRepository;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(ServiceUserSec.class);

    public ServiceUserSec(IRepositoryUserSec userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserSecResponseDTO> allUsers() {
        List<UserSecResponseDTO> users = new ArrayList<>();
        for (UserSec us : userRepository.findAll()) {
            users.add(userMapper.toDto(us));
        }
        if (users.isEmpty()) {
            throw new EntityNotFoundException("No users found"); //Excepcion Not Found
        }
        return users;
    }

    @Override
    public UserSecResponseDTO getUserById(Long id) {
        UserSec userSec = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return userMapper.toDto(userSec);
    }

    @Override
    public UserSecResponseDTO createUser(UserSecCreateDTO userSecDTO) {
        
        UserSec user = userMapper.toEntity(userSecDTO);

        userSec.setPassword(encryptPassword(userSec.getPassword()));

        UserSec newUser = userMapper.toEntity(userSec);

         userRepository.save(newUser);

        UserSecResponseDTO responseDTO = userMapper.toDto(newUser);

        logger.info("User created: {}", newUser);
        return responseDTO;
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserSec> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with ID " + id + " not found"); //Exception not found
        }
        logger.info("User deleted: {}", userOptional.get());
        userRepository.deleteById(id);
    }

    @Override
    public UserSecResponseDTO updateUser(UserSecCreateDTO userSec, Long id) {
        Optional<UserSec> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        UserSec user = userMapper.toEntity(userSec);
        userOptional.get().setUsername(userSec.getUsername());
        userOptional.get().setAccountNotLocked(userSec.getAccountNotLocked());
        userOptional.get().setCredentialsNotExpired(userSec.getCredentialsNotExpired());
        userOptional.get().setAccountNotExpired(userSec.getAccountNotExpired());
        userOptional.get().setEnabled(userSec.getEnabled());
        userOptional.get().setPassword(encryptPassword(userSec.getPassword()));
        userOptional.get().setRoles(user.getRoles());
        userRepository.save(userOptional.get());

        logger.info("User updated: {}", user);

        return userMapper.toDto(userOptional.get());
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }


}
