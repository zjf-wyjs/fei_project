package plus.feifei.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import plus.feifei.common.utils.FileDiskUtil;
import plus.feifei.common.utils.RedisUtils;


/**
 * @author Administrator
 */
@Configuration
@ComponentScan(basePackages = {"plus.feifei"})
public class MyConfig {
}
