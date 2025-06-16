package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryUserSec;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUserSec implements IServiceUserSec{

    private final IRepositoryUserSec userRepository;

    public ServiceUserSec(IRepositoryUserSec userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserSec> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserSec> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserSec createUser(UserSec userSec) {
        return userRepository.save(userSec);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(UserSec userSec, Long id) {
        createUser(userSec);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }


}
