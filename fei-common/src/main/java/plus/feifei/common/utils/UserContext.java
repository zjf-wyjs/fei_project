package plus.feifei.common.utils;

import plus.feifei.data.entity.UserEntity;

/**
 * 2 * @Author: feifei
 * 3 * @Date: 2021/4/19 21:05
 * 4
 */
public class UserContext {
    private static final ThreadLocal<UserEntity> context = new ThreadLocal<UserEntity>();

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
