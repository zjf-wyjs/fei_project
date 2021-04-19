package plus.feifei.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import plus.feifei.common.utils.UserContext;

/**
 * 接口切面类，处理前端接口
 * 2 * @Author: feifei
 * 3 * @Date: 2021/4/19 21:54
 */
@Aspect
@Component
@Slf4j
public class ApiAspect {
    @Pointcut("execution(public * plus.feifei.api.controller..*.*(..))")
    public void controller(){}
    @After("controller()")
    public void after(){
        // 防止内存溢出，销毁
        UserContext.remove();
    }
}
