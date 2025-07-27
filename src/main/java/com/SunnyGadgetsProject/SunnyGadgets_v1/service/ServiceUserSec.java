package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecPatchDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecPutDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.UserMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryRole;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryUserSec;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceUserSec implements IServiceUserSec{

    private final IRepositoryUserSec userRepository;
    private final IRepositoryRole roleRepository;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(ServiceUserSec.class);

    public ServiceUserSec(IRepositoryUserSec userRepository, IRepositoryRole roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

        user.setPassword(encryptPassword(userSecDTO.password()));

         userRepository.save(user);

        UserSecResponseDTO responseDTO = userMapper.toDto(user);

        logger.info("User created: {}", user);
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
    public UserSecResponseDTO updateUser(UserSecPutDTO userSecPutDTO, Long id) {
        UserSec userSec = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        UserSec user = userMapper.toEntity(userSecPutDTO);
        userSec.setUsername(userSecPutDTO.username());
        userSec.setAccountNotLocked(userSecPutDTO.accountNotLocked());
        userSec.setCredentialsNotExpired(userSecPutDTO.accountNotExpired());
        userSec.setAccountNotExpired(userSecPutDTO.accountNotExpired());
        userSec.setEnabled(userSecPutDTO.enabled());
        userSec.setPassword(encryptPassword(userSecPutDTO.password()));
        userSec.setRoles(user.getRoles());
        userRepository.save(userSec);

        logger.info("User updated with PUT: {}", user);

        return userMapper.toDto(userSec);
    }

    @Override
    public UserSecResponseDTO updateUser(UserSecPatchDTO userSecPatchDTO, Long id) {
        UserSec oldUserSeC = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        UserSec newUserSec = userMapper.toEntity(userSecPatchDTO);
        Set<Role> roles = userSecPatchDTO.existingRolesIds().isEmpty()? oldUserSeC.getRoles() : userSecPatchDTO.existingRolesIds().stream().map(idRole -> roleRepository.findById(idRole).orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found"))).collect(Collectors.toSet());

        oldUserSeC.setUsername(userSecPatchDTO.username() == null || userSecPatchDTO.username().isEmpty()? oldUserSeC.getUsername() : userSecPatchDTO.username());
        oldUserSeC.setAccountNotLocked(userSecPatchDTO.accountNotLocked() == null? oldUserSeC.isAccountNotLocked() : userSecPatchDTO.accountNotLocked());
        oldUserSeC.setCredentialsNotExpired(userSecPatchDTO.credentialsNotExpired() == null? oldUserSeC.isCredentialsNotExpired() : userSecPatchDTO.credentialsNotExpired());
        oldUserSeC.setAccountNotExpired(userSecPatchDTO.accountNotExpired() == null? oldUserSeC.isAccountNotExpired() : userSecPatchDTO.accountNotExpired());
        oldUserSeC.setEnabled(userSecPatchDTO.enabled() == null ? oldUserSeC.isEnabled() : userSecPatchDTO.enabled());
        oldUserSeC.setPassword(userSecPatchDTO.password() == null || userSecPatchDTO.password().isEmpty()? oldUserSeC.getPassword() : encryptPassword(userSecPatchDTO.password()));
        oldUserSeC.setRoles(roles);
        userRepository.save(oldUserSeC);

        logger.info("User updated with PATCH: {}", newUserSec);

        return userMapper.toDto(oldUserSeC);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }


}
