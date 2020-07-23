package com.dcits.qtumforum.service;

import com.dcits.qtumforum.mapper.UserAccountMapper;
import com.dcits.qtumforum.model.UserAccount;
import com.dcits.qtumforum.model.UserAccountExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 16:54
 */

@Service
public class UserAccountService {

    @Autowired
    private UserAccountMapper userAccountMapper;


    public UserAccount selectUserAccountByUserId(Long userId) {
        //  Long id = Long.parseLong(userId);
        UserAccountExample userAccountExample = new UserAccountExample();
        userAccountExample.createCriteria().andUserIdEqualTo(userId);
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
        UserAccount userAccount = userAccounts.get(0);
        return userAccount;
    }

    public boolean isAdminByUserId(Long userId){
        if (selectUserAccountByUserId(userId).getGroupId() >= 18)
            return true;
        else
            return false;

    }

}
