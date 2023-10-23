package com.project.appjava.repository.user;

import com.project.appjava.entity.user.Role;
import com.project.appjava.entity.user.User;
import com.project.appjava.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByRoleName(RoleName roleName);

}
