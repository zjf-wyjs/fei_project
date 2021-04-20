

package plus.feifei.oos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.common.utils.PageUtils;
import plus.feifei.oos.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 *
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
