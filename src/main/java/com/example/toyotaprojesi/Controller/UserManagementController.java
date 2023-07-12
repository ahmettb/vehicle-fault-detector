package com.example.toyotaprojesi.Controller;

import com.example.toyotaprojesi.Model.User;
import com.example.toyotaprojesi.Service.AuthService;
import com.example.toyotaprojesi.Service.UserManagementService;
import com.example.toyotaprojesi.request.SignRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller/auth")
public class UserManagementController {

    @Autowired
    UserManagementService userManagementService;


//    @Autowired
//    AuthService authService;

//    @PostMapping("signin")
//    public ResponseEntity<String> signUser(@RequestBody SignRequest signRequest) {
//
//        userManagementService.signUp(signRequest);
//        return new ResponseEntity<>("User added succesfuly", HttpStatus.CREATED);
//
//
//    }

@GetMapping("test1")
public ResponseEntity<String>test1()
{
    return new ResponseEntity<>(" access",HttpStatus.OK);
}

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userManagementService.updateByUsername(user), HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteUser(@RequestParam("username") String username) {
        userManagementService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}





