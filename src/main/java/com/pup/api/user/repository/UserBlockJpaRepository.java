package com.pup.api.user.repository;

import com.pup.api.user.domain.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlockJpaRepository extends JpaRepository<UserBlock, Long>, UserBlockCustomRepository {
}
