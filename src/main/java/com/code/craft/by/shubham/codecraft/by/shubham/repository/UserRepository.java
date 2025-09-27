package com.code.craft.by.shubham.codecraft.by.shubham.repository;


import com.code.craft.by.shubham.codecraft.by.shubham.model.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

}
