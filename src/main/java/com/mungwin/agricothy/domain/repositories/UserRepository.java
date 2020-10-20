package com.mungwin.agricothy.domain.repositories;

import com.mungwin.agricothy.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findFirstByEmail(String email);
  Optional<User> findFirstByPhone(String phone);
  Optional<User> findFirstByPasswordResetCode(String code);
}
