package com.example.toyotaprojesi.Controller;

import com.example.toyotaprojesi.Model.User;
import com.example.toyotaprojesi.Repository.UserRepository;
import com.example.toyotaprojesi.Service.UserDetailImplement;
import com.example.toyotaprojesi.jwt.JwtProvider;
import com.example.toyotaprojesi.request.LoginRequest;
import com.example.toyotaprojesi.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usercontrol")
public class UserController {


    @Autowired
    UserRepository userRepository;







    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers()
    //@RequestParam int pageSize,@RequestParam int page
    {
        //  Pageable pageable=PageRequest.of(page,pageSize);
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);

    }


}
