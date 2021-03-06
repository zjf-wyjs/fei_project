

package plus.feifei.api.web;


import plus.feifei.common.validator.ValidatorUtils;
import plus.feifei.data.vo.LoginForm;
import plus.feifei.data.service.UserService;
import plus.feifei.common.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.feifei.common.utils.R;

import java.util.HashMap;
import java.util.Map;

/**
 * APP登录授权
 *
 *
 */
@RestController
@RequestMapping("/app")
@Api(tags = "APP登录接口")
public class AppLoginApi {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        //用户登录
        long userId = userService.login(form);

        //生成token
        String token = jwtUtils.generateToken(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());

        return R.ok(map);
    }

}
