package com.bosch.employeeman.repository;

import com.bosch.employeeman.model.Employee;
import com.bosch.employeeman.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String > {

    User findByUsername(String username);
}
