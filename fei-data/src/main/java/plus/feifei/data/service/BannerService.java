package plus.feifei.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.common.entity.BannerEntity;
import plus.feifei.common.utils.PageUtils;

import java.util.Map;

/**
 *
 *
 * @author feifei
 * @email demo@gmail.com
 * @date 2021-01-25 10:42:17
 */
public interface BannerService extends IService<BannerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

