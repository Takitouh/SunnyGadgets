package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecResponseDTO;
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
    public UserSecResponseDTO createUser(UserSecCreateDTO userSec) {

        if (userSec.getExistingRolesIds().isEmpty()) {
            throw new EntityNotFoundException("The user must have at least one role");
        }
        //Set<Role> roleList = userSec.getNewRoles().isEmpty() ? new HashSet<>() : roleMapper.toDto(userSec.getNewRoles());

        userSec.setPassword(encryptPassword(userSec.getPassword()));

        UserSec newUser = userMapper.toEntity(userSec);

         userRepository.save(newUser);

        UserSecResponseDTO responseDTO = userMapper.toDto(newUser);

        logger.info("User created: {}", newUser);
        return responseDTO;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(UserSec userSec, Long id) {
        return; //createUser(userSec);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }


}
