

package plus.feifei.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plus.feifei.data.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * 
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
