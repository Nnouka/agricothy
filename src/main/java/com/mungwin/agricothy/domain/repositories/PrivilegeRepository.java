package com.mungwin.agricothy.domain.repositories;


import com.mungwin.agricothy.domain.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
