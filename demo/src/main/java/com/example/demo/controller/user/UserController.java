package com.example.demo.controller.user;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.HttpClientUtil;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.ResultCodeUtil;
import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * UserController
 * 处理用户登录相关操作
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param data 包含用户登录信息的数据
     *
     * @return 包含登录结果的响应数据
     */
    @PostMapping
    public ResultUtil login(@RequestBody HashMap<String, ?> data) {
        // 微信小程序的 AppID 和 Secret
        String appId = "your_app_id";
        String secret = "your_secret_key";
        // 从请求数据中获取用户的昵称和头像URL
        String nickName = (data.get("nickName")).toString();
        String avatarUrl = (data.get("avatarUrl")).toString();
        // 构建微信登录验证的 URL
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + data.get("code") + "&grant_type=authorization_code";
        ResponseEntity<String> request = new HttpClientUtil(new RestTemplate()).sendGetRequest(url);
        // 解析微信登录响应数据
        String[] split = request.getBody().split("\"");
        String session_key = split[3];
        String openId = split[7];

        User user = new User();
        user.setOpenId(openId);
        user.setNickName(nickName);
        user.setAvatarUrl(avatarUrl);

        User one = userService.getOne(openId);
        if (one.getIsDeleted() == 1) {
            return new ResultUtil(ResultCodeUtil.USER_NO_EXIST);
        }
        if (one == null) {
            userService.save(user);
        } else {
            userService.update(user);
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("openId", openId);
        payload.put("nickName", nickName);
        payload.put("type", "user");

        String token = JwtUtil.generateToken(payload);
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        return new ResultUtil(result);
    }

}
