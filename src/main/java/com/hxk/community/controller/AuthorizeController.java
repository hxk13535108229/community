package com.hxk.community.controller;

import com.hxk.community.dto.*;
import com.hxk.community.entity.User;
import com.hxk.community.provider.GiteeProvider;
import com.hxk.community.provider.GithubProvider;
import com.hxk.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Autowired
    private UserService userService;

    @RequestMapping("/callbackToGithub")
    public String callbackToGithub(@RequestParam(name = "code") String code,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(github_Client_id);
        accessTokenDTO.setClient_secret(github_Client_secret);
        accessTokenDTO.setRedirect_uri(github_Redirect_uri);
        String accessTokentoken = githubProvider.getAccessToken(accessTokenDTO);
        GitUser gitUser = githubProvider.getUser(accessTokentoken);
        if (gitUser != null && gitUser.getName() != null) {
            User user = new User();
            user.setAccount_id(String.valueOf(gitUser.getId()));
            user.setAccount_name(gitUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modify(user.getGmt_create());
            user.setAvatar_url(gitUser.getAvatar_url());
            userService.insertUser(user);
            httpServletResponse.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("callbackToGitee")
    public String callbackToGitee(@RequestParam(name = "code") String code,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        AccessTokenDTOGitee accessTokenDTOGitee = new AccessTokenDTOGitee();
        accessTokenDTOGitee.setRedirect_uri(gitee_Redirect_uri);
        accessTokenDTOGitee.setCode(code);
        accessTokenDTOGitee.setClient_secret(gitee_Client_secret);
        accessTokenDTOGitee.setClient_id(gitee_Client_id);
        accessTokenDTOGitee.setGrant_type(gitee_Grant_type);
        String accessToken = giteeProvider.getGiteeAccessToken(accessTokenDTOGitee);
        GitUser gitUser = giteeProvider.getGiteeUser(accessToken);
        if (gitUser != null && gitUser.getName() != null) {
            User user = new User();
            user.setAccount_id(String.valueOf(gitUser.getId()));
            user.setAccount_name(gitUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modify(user.getGmt_create());
            user.setAvatar_url(gitUser.getAvatar_url());
            userService.insertUser(user);
            httpServletResponse.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
