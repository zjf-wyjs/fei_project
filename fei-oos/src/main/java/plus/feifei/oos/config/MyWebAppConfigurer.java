package plus.feifei.oos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import plus.feifei.common.utils.ConfigConstant;
import plus.feifei.common.utils.Constant;
import plus.feifei.oos.cloud.CloudStorageConfig;
import plus.feifei.sys.service.SysConfigService;

/**
 * @author haha
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
        if (config.getType() == Constant.CloudService.LOCAL_CLOUD.getValue()) {
            String localPrefix = config.getLocalPrefix();
            String path = System.getProperty("user.dir") + "/" + localPrefix + "/";
            path = path.replace("\\", "/");
            registry.addResourceHandler("/imageslocal/"+config.getLocalPrefix()+"/**").addResourceLocations("file:" +path);
        }
    }
}
