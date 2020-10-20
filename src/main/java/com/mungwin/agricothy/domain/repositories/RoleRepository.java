package com.mungwin.agricothy.domain.repositories;


import com.mungwin.agricothy.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findFirstByName(String name);
}
