package plus.feifei.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.feifei.common.entity.AboutEntity;
import plus.feifei.data.dao.AboutDao;
import plus.feifei.data.service.AboutService;

/**
 * 2 * @Author: feifei
 * 3 * @Date: 2021/4/24 11:40
 * 4
 */
@Service("aboutServiceImpl")
public class AboutServiceImpl extends ServiceImpl<AboutDao, AboutEntity> implements AboutService {
}
