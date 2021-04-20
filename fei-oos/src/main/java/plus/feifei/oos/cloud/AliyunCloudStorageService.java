

package plus.feifei.oos.cloud;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;
import plus.feifei.common.utils.RRException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云存储
 */
public class AliyunCloudStorageService extends CloudStorageService {
    private OSSClient client;

    public AliyunCloudStorageService(CloudStorageConfig config) {
        super(config);

        //初始化
        init();
    }

    private void init() {
        client = new OSSClient(getConfig().getAliyunEndPoint(), getConfig().getAliyunAccessKeyId(),
                getConfig().getAliyunAccessKeySecret());
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(getConfig().getAliyunBucketName(), path, inputStream);
        } catch (Exception e) {
            throw new RRException("上传文件失败，请检查配置信息", e);
        }

        return getConfig().getAliyunDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(getConfig().getAliyunPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(getConfig().getAliyunPrefix(), suffix));
    }

    @Override
    public String uploadMultipartFile(MultipartFile multipartFile, String suffix) {
        return null;
    }
}
