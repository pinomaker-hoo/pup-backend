package com.pup.api.dog.repository;

import com.pup.api.dog.domain.Dog;
import com.pup.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogJpaRepository extends JpaRepository<Dog, Long>, DogJpaCustomRepository {
    long countByUser(User user);
}
