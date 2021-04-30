package plus.feifei.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.feifei.common.entity.BannerEntity;
import plus.feifei.common.utils.PageUtils;
import plus.feifei.common.utils.Query;
import plus.feifei.data.dao.BannerDao;
import plus.feifei.data.service.BannerService;

import java.util.Map;


/**
 * @author haha
 */
@Service("bannerService")
public class BannerServiceImpl extends ServiceImpl<BannerDao, BannerEntity> implements BannerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<BannerEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        IPage<BannerEntity> page = this.page(
                new Query<BannerEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}
