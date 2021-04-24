

package plus.feifei.api.web;


import plus.feifei.common.utils.R;
import plus.feifei.common.validator.ValidatorUtils;
import plus.feifei.common.entity.UserEntity;
import plus.feifei.data.vo.RegisterForm;
import plus.feifei.data.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

/**
 * 注册
 *
 *
 */
@RestController
@RequestMapping("/app")
@Api("APP注册接口")
public class AppRegisterApi {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        UserEntity user = new UserEntity();
        user.setMobile(form.getMobile());
        user.setUsername(form.getMobile());
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setCreateTime(LocalDate.now());
        userService.save(user);

        return R.ok();
    }
}
