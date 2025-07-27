package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.PermissionMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryPermission;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ServicePermission implements IServicePermission {

    private final IRepositoryPermission permissionRepository;
    private final PermissionMapper permissionMapper;
    private final Logger logger = LoggerFactory.getLogger(ServicePermission.class);

    public ServicePermission(IRepositoryPermission permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<PermissionResponseDTO> allPermissions() {
        List<PermissionResponseDTO> permissionResponseDTOS = new ArrayList<>();
        for (Permission pe : permissionRepository.findAll()) {
            permissionResponseDTOS.add(permissionMapper.toDto(pe));
        }
        if (permissionResponseDTOS.isEmpty()) {
            throw new EntityNotFoundException("No permission's found"); //Exception Not Found
        }
        logger.info("Get all permissions from repository");
        return permissionResponseDTOS;
    }

    @Override
    public PermissionResponseDTO getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.info("Get permission by id: {}", id);
        return permissionMapper.toDto(permission);
    }

    @Override
    public PermissionResponseDTO createPermission(PermissionCreateDTO permission) {
        Permission perm = permissionMapper.toEntity(permission);
        perm = permissionRepository.save(perm);
        PermissionResponseDTO responseDTO = permissionMapper.toDto(perm);
        logger.info("Permission created: {}", perm.getPermissionName());
        return responseDTO;
    }

    @Override
    public List<PermissionResponseDTO> createPermission(Set<PermissionCreateDTO> permissions) {
        List<Permission> per = new ArrayList<>();
        List<PermissionResponseDTO> permissionDTOList = new ArrayList<>();
        for (PermissionCreateDTO pe : permissions) {
            per.add(permissionMapper.toEntity(pe));
        }
        permissionRepository.saveAll(per);
        for (Permission permission : per) {
            permissionDTOList.add(permissionMapper.toDto(permission));
        }
        logger.info("Permission's created: {}", per);
        return permissionDTOList;
    }


    @Override
    public PermissionResponseDTO updatePermission(PermissionPutDTO permissionPutDTO, Long id) {
        Permission permissionEntity = permissionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Permission with id " + id + " not found"));

        permissionEntity.setPermissionName(permissionPutDTO.permissionName());
        permissionRepository.save(permissionEntity);
        logger.info("Permission updated with PUT: {}", permissionPutDTO.permissionName());
        return permissionMapper.toDto(permissionEntity);
    }

    @Override
    public PermissionResponseDTO updatePermission(PermissionPatchDTO permissionPatchDTO, Long id) {
        Permission permissionEntity = permissionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Permission with id " + id + " not found"));

        permissionEntity.setPermissionName(permissionPatchDTO.permissionName().isEmpty() ? permissionEntity.getPermissionName() : permissionPatchDTO.permissionName());
        permissionRepository.save(permissionEntity);
        logger.info("Permission updated with PATCH: {}", permissionPatchDTO.permissionName());
        return permissionMapper.toDto(permissionEntity);
    }

    @Override
    public void deletePermission(Long id) {
        if (permissionRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Permission with id " + id + " not found");
        }
        logger.info("Permission deleted: {}", id);
        permissionRepository.deleteById(id);
    }
}

