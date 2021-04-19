

package plus.feifei.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.modules.oss.entity.SysOssEntity;
import plus.feifei.common.utils.PageUtils;

import java.util.Map;

/**
 * 文件上传
 *
 *
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
