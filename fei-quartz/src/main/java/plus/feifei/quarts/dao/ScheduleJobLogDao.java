

package plus.feifei.quarts.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plus.feifei.quarts.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 * 
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {

}
