package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.ERole;
import com.example.toyotaprojesi.Model.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {


    Optional<UserRole> findByName(ERole name);
}
