package com.hxk.community.provider;

import com.alibaba.fastjson.JSON;
import com.hxk.community.dto.AccessTokenDTO;
import com.hxk.community.dto.GitUser;
import com.hxk.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName GithubProvider
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 17:07
 * @Version 1.0
 **/
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
       MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String[] split1 = split[0].split("=");
            String token = split1[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   public GitUser getUser(String accessToken){
       OkHttpClient client = new OkHttpClient();
       Request request = new Request.Builder()
               .url("https://api.github.com/user")
               .header("Authorization", "token "+accessToken)
               .build();

       try (Response response = client.newCall(request).execute()) {
           String string = response.body().string();
           GitUser gitUser = JSON.parseObject(string, GitUser.class);
           return gitUser;
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }
}
