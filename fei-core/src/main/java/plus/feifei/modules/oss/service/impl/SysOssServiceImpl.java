

package plus.feifei.modules.oss.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import plus.feifei.common.utils.Query;
import plus.feifei.modules.oss.dao.SysOssDao;
import plus.feifei.modules.oss.entity.SysOssEntity;
import plus.feifei.modules.oss.service.SysOssService;
import org.springframework.stereotype.Service;
import plus.feifei.common.utils.PageUtils;

import java.util.Map;


@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		IPage<SysOssEntity> page = this.page(
			new Query<SysOssEntity>().getPage(params)
		);

		return new PageUtils(page);
	}

}
