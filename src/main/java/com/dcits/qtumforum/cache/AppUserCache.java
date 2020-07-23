package com.dcits.qtumforum.cache;

import lombok.Data;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/23 10:20
 */

@Component
@Data
public class AppUserCache {
    ExpiringMap<String,String> map = ExpiringMap.builder()
            .maxSize(100)//最大容量，防止恶意注入
            .expiration(1, TimeUnit.MINUTES)//过期时间1分钟
            .expirationPolicy(ExpirationPolicy.ACCESSED)
            .variableExpiration()
            .build();

    public void put(String loginId,String token){
        map.put(loginId,token);
    }

    public String get(String loginId){
        return map.get(loginId);
    }


}
