package com.example.toyotaprojesi.runner;

import com.example.toyotaprojesi.Model.ERole;
import com.example.toyotaprojesi.Model.User;
import com.example.toyotaprojesi.Model.UserRole;
import com.example.toyotaprojesi.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleRunner implements ApplicationRunner {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!userRoleRepository.findByName(ERole.ROLE_ADMIN).isPresent())
        {
            UserRole userRoleAdmin=new UserRole();
            userRoleAdmin.setName(ERole.ROLE_ADMIN);
            userRoleRepository.save(userRoleAdmin);

        }
        if(!userRoleRepository.findByName(ERole.ROLE_TEAMLEADER).isPresent())
        {
            UserRole userRoleTeamLeader=new UserRole();
            userRoleTeamLeader.setName(ERole.ROLE_TEAMLEADER);
            userRoleRepository.save(userRoleTeamLeader);

        }
        if(!userRoleRepository.findByName(ERole.ROLE_OPERATOR).isPresent())
        {
            UserRole userRoleOperator=new UserRole();
            userRoleOperator.setName(ERole.ROLE_OPERATOR);
            userRoleRepository.save(userRoleOperator);;

        }



    }
}
