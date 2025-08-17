package com.SunnyGadgetsProject.SunnyGadgets_v1.security.config;


import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.AuthLoginDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.AuthResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryUserSec;
import com.SunnyGadgetsProject.SunnyGadgets_v1.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final IRepositoryUserSec userRepo;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImp(IRepositoryUserSec userRepo, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        //tenemos User sec y necesitamos devolver UserDetails
        //traemos el usuario de la bd
        UserSec userSec = userRepo.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user " + username + " was not found"));

        //con GrantedAuthority Spring Security maneja permisos
        List<GrantedAuthority> authorityList = new ArrayList<>();

        //Programación funcional a full
        //tomamos roles y los convertimos en SimpleGrantedAuthority para poder agregarlos a la authorityList
        userSec.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));


        //ahora tenemos que agregar los permisos
        userSec.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream()) //acá recorro los permisos de los roles
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        //retornamos el usuario en formato Spring Security con los datos de nuestro userSec
        return new User(userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNotExpired(),
                userSec.isCredentialsNotExpired(),
                userSec.isAccountNotLocked(),
                authorityList);
    }


    public AuthResponseDTO loginUser(AuthLoginDTO userRequest){
        String username = userRequest.username();
        String password = userRequest.password();
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "Login successful", accessToken, true);
        return authResponseDTO;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if(!Objects.equals(userDetails.getUsername(), username) || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
       return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}

