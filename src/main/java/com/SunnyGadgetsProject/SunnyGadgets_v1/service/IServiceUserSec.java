package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;

import java.util.List;
import java.util.Optional;

public interface IServiceUserSec {
     List<UserSec> allUsers();
     Optional<UserSec> getUserById(Long id);
     UserSec createUser(UserSec userSec);
     void deleteUser(Long id);
     void updateUser(UserSec userSec, Long id);
     String encryptPassword(String password);
}
