package com.example.toyotaprojesi.Service;

import com.example.toyotaprojesi.Model.ERole;
import com.example.toyotaprojesi.Model.User;
import com.example.toyotaprojesi.Model.UserRole;
import com.example.toyotaprojesi.Repository.UserRepository;
import com.example.toyotaprojesi.Repository.UserRoleRepository;
import com.example.toyotaprojesi.exception.MailAlreadyExist;
import com.example.toyotaprojesi.exception.UsernameAlreadyExist;
import com.example.toyotaprojesi.jwt.JwtProvider;
import com.example.toyotaprojesi.request.LoginRequest;
import com.example.toyotaprojesi.request.SignRequest;
import com.example.toyotaprojesi.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;


    public JwtResponse login(LoginRequest loginRequest) {

        if(!userRepository.existByUsername(loginRequest.getUsername()))
        {
            throw  new UsernameNotFoundException("Username not found");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtProvider.genereateJwtToken(authentication);
        UserDetailImplement userDetailImplement = (UserDetailImplement) authentication.getPrincipal();
        List<String> roles = userDetailImplement.getAuthorities().stream().map(
                item -> item.getAuthority()
        ).collect(Collectors.toList());

        return new JwtResponse(jwtToken,
                userDetailImplement.getId(),
                userDetailImplement.getUsername(),
                userDetailImplement.getMail(),
                roles);
    }

    public void signUp(SignRequest signRequest) throws Exception {

        if (userRepository.existByMail(signRequest.getEmail())) {
        throw new MailAlreadyExist("Mail already exist");
        }

        if (userRepository.existByUsername(signRequest.getUsername()))
        {
            throw new UsernameAlreadyExist("Username already exist");

        }

        User newUser = new User(
                signRequest.getName(),
                signRequest.getSurname(),
                signRequest.getUsername(),
                signRequest.getEmail(),
                passwordEncoder.encode(signRequest.getPassword()));

        List<String> role = signRequest.getRoles();
        List<UserRole> roles = new ArrayList<>();
        if (role == null) {
            UserRole userRole = userRoleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            role.forEach(
                    role1 ->
                    {
                        switch (role1) {
                            case "admin":
                                UserRole role_admin = userRoleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(
                                        () -> new RuntimeException("Role is not found")
                                );
                                roles.add(role_admin);
                                break;
                            case "team leader":
                                UserRole role_teamLeader = userRoleRepository.findByName(ERole.ROLE_TEAMLEADER).orElseThrow(
                                        () -> new RuntimeException("Role is not found")
                                );
                                roles.add(role_teamLeader);
                                break;

                            case "operator":
                                UserRole role_operator = userRoleRepository.findByName(ERole.ROLE_OPERATOR).orElseThrow(
                                        () -> new RuntimeException("Role is not found")
                                );
                                roles.add(role_operator);
                                break;
                            default:
                                UserRole userRole = userRoleRepository.findByName(ERole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(userRole);

                        }
                    }
            );


        }


        newUser.setRoles(roles);
        userRepository.save(newUser);
    }
}
