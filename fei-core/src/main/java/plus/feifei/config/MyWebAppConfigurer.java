package plus.feifei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path=System.getProperty("user.dir") + "/images/";
        path=path.replace("\\","/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+path);
    }
}
