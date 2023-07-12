package com.example.toyotaprojesi.Service;

import com.example.toyotaprojesi.Model.ERole;
import com.example.toyotaprojesi.Model.User;
import com.example.toyotaprojesi.Model.UserRole;
import com.example.toyotaprojesi.Repository.UserRepository;
import com.example.toyotaprojesi.Repository.UserRoleRepository;
import com.example.toyotaprojesi.request.SignRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRoleRepository userRoleRepository;

    //Kullanıcı Guncelleme

    public User updateByUsername(User user)
    {
User oldUser=userRepository.findByUsername(user.getUsername()).orElseThrow(()->new RuntimeException("User not found"));

if(!oldUser.isDeletes())
{
    oldUser.setUsername(user.getUsername());
    oldUser.seteMail(user.geteMail());
    oldUser.setName(user.getName());
    oldUser.setSurName(user.getSurName());
    oldUser.setDeletes(user.isDeletes());
    oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
     userRepository.save(oldUser);
return oldUser;
}
return null;
    }
//Kullanıcı silme
    public void deleteUser(String username)
    {
        User deleteUser=userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        if(!deleteUser.isDeletes())
        {
            deleteUser.setDeletes(true);
            userRepository.save(deleteUser);
        }


    }

//Kullanıcı ekleme
    public void signUp(SignRequest signRequest)
    {
        if(userRepository.existByMail(signRequest.getEmail()))
        {
            return;

        }

        if(userRepository.existByUsername(signRequest.getUsername())) return;

        User newUser = new User(
                signRequest.getName(),
                signRequest.getSurname(),
                signRequest.getUsername(),
                signRequest.getEmail(),
                passwordEncoder.encode(signRequest.getPassword()));

        List<String> role = signRequest.getRoles();
        role.forEach(roles -> System.out.println(roles));
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
