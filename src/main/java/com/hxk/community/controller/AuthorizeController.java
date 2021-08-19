package com.hxk.community.controller;

import com.hxk.community.dto.*;
import com.hxk.community.provider.GiteeProvider;
import com.hxk.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName AuthorizeController
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 16:59
 * @Version 1.0
 **/
@Controller
public class AuthorizeController {

    @Value("${github.Client_id}")
    private String github_Client_id;

    @Value("${github.Client_secret}")
    private String github_Client_secret;

    @Value("${github.Redirect_uri}")
    private String github_Redirect_uri;

    @Value("${gitee.Client_id}")
    private String gitee_Client_id;

    @Value("${gitee.Client_secret}")
    private String gitee_Client_secret;

    @Value("${gitee.Grant_type}")
    private String gitee_Grant_type;

    @Value("${gitee.Redirect_uri}")
    private String gitee_Redirect_uri;

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private GiteeProvider giteeProvider;

    @RequestMapping("/callbackToGithub")
    public String callbackToGithub(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(github_Client_id);
        accessTokenDTO.setClient_secret(github_Client_secret);
        accessTokenDTO.setRedirect_uri(github_Redirect_uri);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GitUser gitUser = githubProvider.getUser(token);
        System.out.println(gitUser.toString());
        //登陆成功后返回首页面
        return "index";
    }

    @RequestMapping("callbackToGitee")
    public String callbackToGitee(@RequestParam(name = "code")String code){
        AccessTokenDTOGitee accessTokenDTOGitee = new AccessTokenDTOGitee();
        accessTokenDTOGitee.setRedirect_uri(gitee_Redirect_uri);
        accessTokenDTOGitee.setCode(code);
        accessTokenDTOGitee.setClient_secret(gitee_Client_secret);
        accessTokenDTOGitee.setClient_id(gitee_Client_id);
        accessTokenDTOGitee.setGrant_type(gitee_Grant_type);
        String token = giteeProvider.getGiteeAccessToken(accessTokenDTOGitee);
        GitUser gitUser = giteeProvider.getGiteeUser(token);
        System.out.println(gitUser.toString());
        return "index";
    }
}
