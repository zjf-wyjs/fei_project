

package plus.feifei.api.controller;


import plus.feifei.api.adnnotation.Login;
import plus.feifei.api.adnnotation.LoginUser;
import plus.feifei.api.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import plus.feifei.common.utils.HttpContextUtils;
import plus.feifei.common.utils.R;
import plus.feifei.common.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.feifei.common.utils.UserContext;

/**
 * APP测试接口
 *
 *
 */
@RestController
@RequestMapping("/app")
@Api("APP测试接口")
public class AppTestController {
    @Autowired
    private NativeWebRequest request;
    @Login
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public R userInfo(@LoginUser UserEntity user){
        return R.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public R userInfo(@RequestAttribute("userId") Integer userId){
        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        System.out.println(object);
        return R.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public R notToken(){
        return R.ok().put("msg", "无需token也能访问。。。");
    }

}
