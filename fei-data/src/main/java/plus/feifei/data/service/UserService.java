

package plus.feifei.data.service;


import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.common.entity.UserEntity;
import plus.feifei.data.vo.LoginForm;

/**
 * 用户
 *
 *
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	long login(LoginForm form);
}
