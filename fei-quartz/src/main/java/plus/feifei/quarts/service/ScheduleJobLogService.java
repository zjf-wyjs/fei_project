

package plus.feifei.quarts.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.quarts.entity.ScheduleJobLogEntity;
import plus.feifei.common.utils.PageUtils;

import java.util.Map;

/**
 * 定时任务日志
 *
 * 
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);

}
