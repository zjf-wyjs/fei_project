

package plus.feifei.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plus.feifei.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * 
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
