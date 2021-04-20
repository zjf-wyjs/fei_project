package plus.feifei.oos.cloud;

import java.io.InputStream;

/**
 * @author haha
 */
public class LocalCloudStorageService extends CloudStorageService {
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
}
