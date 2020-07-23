package com.dcits.qtumforum.cache;

import com.dcits.qtumforum.model.User;
import lombok.Data;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 15:27
 */

@Component
@Data
public class LoginUserCache {
    private List<User> loginUsers = new ArrayList<>();

    ExpiringMap<Long,Long> loginUserMap = ExpiringMap.builder()
            .maxSize(16)//最大容量，防止恶意注入
            .expiration(11, TimeUnit.MINUTES)//过期时间10分钟
            .expirationPolicy(ExpirationPolicy.CREATED)
            .variableExpiration()
            .build();

    // 更新
    public void updateLoginUsers(List<User> loginUsers){
        this.loginUsers=loginUsers;
    }

    // 把uid和当前时间put进去
    public void putLoginUser(Long uid,Long gmt){
        loginUserMap.put(uid, gmt);
    }

    // 通过uid获取
    public Long getLoginUser(Long uid){
        return loginUserMap.get(uid);
    }


}
