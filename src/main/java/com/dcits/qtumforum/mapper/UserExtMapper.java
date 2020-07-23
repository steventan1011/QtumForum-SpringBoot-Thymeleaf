package com.dcits.qtumforum.mapper;

import com.dcits.qtumforum.dto.UserQueryDTO;
import com.dcits.qtumforum.model.User;

import java.util.List;

public interface UserExtMapper {
    List<User> selectLatestLoginUser(UserQueryDTO userQueryDTO);
    Integer count(UserQueryDTO userQueryDTO);
}
