package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT *FROM users  WHERE users.email= :mail ", nativeQuery = true)
    List<User> findByMail(@Param("mail") String mail);

    @Query(value = "SELECT CASE WHEN COUNT(users)>0 THEN true ELSE false END FROM users  WHERE users.email = :mail", nativeQuery = true)
    Boolean existByMail(@Param("mail") String eMail);

    @Query(value = "SELECT CASE WHEN COUNT(users)>0 THEN true ELSE false END FROM users  WHERE users.username = :username", nativeQuery = true)
    Boolean existByUsername(@Param("username") String username);

    @Query(value = "SELECT *FROM users WHERE users.username= :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

   // @Query(value = "SELECT *FROM users WHERE users.deleted= false", nativeQuery = true)
   // List<User>activeUsers();


}
