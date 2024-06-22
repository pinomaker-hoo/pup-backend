package com.pup.api.user.repository;


import com.pup.api.user.event.vo.LoginUser;

public interface UserJpaCustomRepository {
    boolean existByUsername(String username);

    LoginUser findUserByUsername(String username);
}
