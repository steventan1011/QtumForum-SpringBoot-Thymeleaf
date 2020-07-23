package com.dcits.qtumforum.provider;

import com.dcits.qtumforum.dto.GithubAccessTokenDTO;
import com.dcits.qtumforum.dto.GithubUserDTO;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/23 10:55
 */

@Component
public class GithubProvider {

    public String getAccessToken(GithubAccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public GithubUserDTO getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        /*
        *使用参数的方式明文传输，并不推荐，即将被Github废弃
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        */

        //作为header中的参数传输，强烈推荐
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUserDTO githubUser = JSON.parseObject(string, GithubUserDTO.class);

            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }

}
