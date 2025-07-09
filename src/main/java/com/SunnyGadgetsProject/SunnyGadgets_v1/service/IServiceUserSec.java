package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecResponseDTO;

import java.util.List;

public interface IServiceUserSec {
     List<UserSecResponseDTO> allUsers();
     UserSecResponseDTO getUserById(Long id);
     UserSecResponseDTO createUser(UserSecCreateDTO userSec);
     void deleteUser(Long id);
     UserSecResponseDTO updateUser(UserSecCreateDTO userSec, Long id);
     String encryptPassword(String password);
}
