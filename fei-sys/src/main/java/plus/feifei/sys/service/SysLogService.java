

package plus.feifei.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.sys.entity.SysLogEntity;
import plus.feifei.common.utils.PageUtils;

import java.util.Map;


/**
 * 系统日志
 *
 *
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
