package plus.feifei.oos.cloud;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author haha
 */
public class LocalCloudStorageService extends CloudStorageService {
    /**
     * 组装前缀地址
     * @param config
     * @return
     */
    public static String getPre(CloudStorageConfig config){
        return config.getUrlPrefix()+"/imageslocal/"+config.getLocalPrefix();
    }
    public LocalCloudStorageService(CloudStorageConfig config) {
        super(config);
    }

    @Override
    public String upload(byte[] data, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {

        return null;
    }

    @Override
    public String uploadMultipartFile(MultipartFile multipartFile, String suffix) {
        String fileName= UUID.randomUUID().toString().replace("-","")+suffix;
        String path = System.getProperty("user.dir");
        File dest = new File(path+"/"+getConfig().getLocalPrefix(),fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(dest);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return getPre(getConfig())+"/"+fileName;
    }
}
