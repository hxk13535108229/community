package com.hxk.community.provider;

import com.alibaba.fastjson.JSON;
import com.hxk.community.dto.*;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName GiteeProvider
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 18:39
 * @Version 1.0
 **/
@Component
public class GiteeProvider {
    public String getGiteeAccessToken(AccessTokenDTOGitee accessTokenDTOGitee){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTOGitee));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GiteeAccessToken giteeAccessToken = JSON.parseObject(string, GiteeAccessToken.class);
            return giteeAccessToken.getAccess_token();
        } catch (IOException e) {
            e.printStackTrace();
        }
return null;
    }

    public GitUser getGiteeUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token="+accessToken)
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
