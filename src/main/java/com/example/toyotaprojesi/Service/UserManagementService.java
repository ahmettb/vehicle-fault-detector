package com.example.toyotaprojesi.Service;

import com.example.toyotaprojesi.Model.ERole;
import com.example.toyotaprojesi.Model.User;
import com.example.toyotaprojesi.Model.UserRole;
import com.example.toyotaprojesi.Repository.UserRepository;
import com.example.toyotaprojesi.Repository.UserRoleRepository;
import com.example.toyotaprojesi.request.SignRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    public User updateByUsername(User user) {
        User oldUser = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!oldUser.isDeletes()) {
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String username) {
        User deleteUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!deleteUser.isDeletes()) {
            deleteUser.setDeletes(true);
            userRepository.save(deleteUser);
        }


    }


}
