package com.pup.api.user.repository;


import com.pup.api.user.event.vo.LoginUser;
import com.pup.api.user.event.vo.UserV0;
import com.pup.api.user.event.vo.UserV1;

import java.util.List;

public interface UserJpaCustomRepository {
    boolean existedUserByEmail(String email);

    LoginUser findUserByEmail(String email);

    UserV0 findUserByUserId(Integer userId);

    List<UserV1> findUserList(Integer userId);
}
