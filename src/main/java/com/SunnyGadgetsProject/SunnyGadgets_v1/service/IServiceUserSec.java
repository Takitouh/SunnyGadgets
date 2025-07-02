package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;

import java.util.List;
import java.util.Optional;

public interface IServiceUserSec {
     List<UserSecResponseDTO> allUsers();
     UserSecResponseDTO getUserById(Long id);
     UserSecResponseDTO createUser(UserSecCreateDTO userSec);
     void deleteUser(Long id);
     void updateUser(UserSec userSec, Long id);
     String encryptPassword(String password);
}
