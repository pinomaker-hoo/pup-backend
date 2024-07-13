package com.pup.api.user.repository;


import com.pup.api.user.event.vo.LoginUser;

public interface UserJpaCustomRepository {
    boolean existedUserByEmail(String email);

    LoginUser findUserByUsername(String username);
}
