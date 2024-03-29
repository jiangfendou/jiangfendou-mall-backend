package com.jiangfendou.mall.auth.web;

import static com.jiangfendou.common.constant.AuthServerConstant.LOGIN_USER;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jiangfendou.common.utils.HttpUtils;
import com.jiangfendou.common.utils.R;
import com.jiangfendou.common.vo.MemberResponseVo;
import com.jiangfendou.mall.auth.feign.MemberFeignService;
import com.jiangfendou.mall.auth.vo.SocialUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class OAuth2Controller {

    @Autowired
    private MemberFeignService memberFeignService;


    @GetMapping(value = "/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("client_id", "3364825010");
        map.put("client_secret", "9c845fd5d4763047dc2af5f1a5065643");
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", "http://auth.jiangfendou.com/oauth2.0/weibo/success");
        map.put("code", code);

        //1、根据用户授权返回的code换取access_token
        HttpResponse response = HttpUtils
            .doPost("https://api.weibo.com", "/oauth2/access_token", "post",
                new HashMap<>(), map, new HashMap<>());

        //2、处理
        if (response.getStatusLine().getStatusCode() == 200) {
            //获取到了access_token,转为通用社交登录对象
            String json = EntityUtils.toString(response.getEntity());
            //String json = JSON.toJSONString(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);

            //知道了哪个社交用户
            //1）、当前用户如果是第一次进网站，自动注册进来（为当前社交用户生成一个会员信息，以后这个社交账号就对应指定的会员）
            //登录或者注册这个社交用户
            System.out.println(socialUser.getAccess_token());
            //调用远程服务
            R oauthLogin = memberFeignService.oauthLogin(socialUser);
            if (oauthLogin.getCode() == 0) {
                MemberResponseVo data = oauthLogin.getData("data", new TypeReference<MemberResponseVo>() {});
                log.info("登录成功：用户信息：{}", data.toString());

                //1、第一次使用session，命令浏览器保存卡号，JSESSIONID这个cookie
                //以后浏览器访问哪个网站就会带上这个网站的cookie
                // 发卡的时候（指定域名为父域名），即使是子域系统发的卡，也能让父亲域直接使用
                session.setAttribute(LOGIN_USER, data);
                // 默认发的里令牌，作用域当前作用域；（解决子域session共享问题）
                // 使用JSON的序列化方式类序列化对象数据到redis中
                //2、登录成功跳回首页
                return "redirect:http://jiangfendou.com";
            } else {
                
                return "redirect:http://auth.jiangfendou.com/login.html";
            }

        } else {
            return "redirect:http://auth.jiangfendou.com/login.html";
        }

    }

}
