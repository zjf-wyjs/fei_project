package plus.feifei.common.utils;

import plus.feifei.common.entity.UserEntity;

import java.util.Objects;

/**
 * 2 * @Author: feifei
 * 3 * @Date: 2021/4/19 21:05
 * 4
 */
public class UserContext {
    private static final ThreadLocal<UserEntity> context = new ThreadLocal<UserEntity>();

    /**
     * 当前是否登录，如果是代表已经登录
     * @return 是否登录
     */
    public static boolean isLogin(){
        UserEntity userEntity = context.get();
        return Objects.nonNull(userEntity);
    }

    /**
     * 存放用户信息
     * @param entity
     */
    public static void set(UserEntity entity){
        context.set(entity);
    }

    /**
     * 获取用户信息
     * @return
     */
    public static UserEntity get(){
        return context.get();
    }
    /**
     * 清除当前线程引用，防止内存泄漏
     */
    public static void remove(){
        context.remove();
    }



}
