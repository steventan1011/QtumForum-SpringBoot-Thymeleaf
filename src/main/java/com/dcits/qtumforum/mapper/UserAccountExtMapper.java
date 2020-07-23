package com.dcits.qtumforum.mapper;

import com.dcits.qtumforum.model.UserAccount;

public interface UserAccountExtMapper {

    int incScore(UserAccount userAccount);
    int decScore(UserAccount userAccount);
}
