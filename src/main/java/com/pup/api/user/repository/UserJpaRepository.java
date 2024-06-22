package com.pup.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pup.api.user.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Integer>, UserJpaCustomRepository {
}
