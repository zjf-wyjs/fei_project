package plus.feifei.common.utils;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import plus.feifei.common.utils.R;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Component
public class FileDiskUtil {
    @Value("${image.pre}")
    private String url;
    public String saveDisk(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String end = fileName.substring(fileName.indexOf("."));
        fileName= UUID.randomUUID().toString().replace("-","")+end;
        String path = System.getProperty("user.dir");
        File dest = new File(path+"/images",fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return url+"images/"+fileName;
    }
    public R saveDisk(String base64Data) {
//        System.out.println(base64Data);
        String dataPrix = ""; //base64格式前头
        String data = "";//实体部分数据
        if(base64Data==null||"".equals(base64Data)){
            return R.error();
        }else {
            String [] d = base64Data.split("base64,");//将字符串分成数组
            if(d != null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
            }else {
                return R.error("上传失败，数据不合法");
            }
        }
        String suffix = "";//图片后缀，用以识别哪种格式数据
        //data:image/jpeg;base64,base64编码的jpeg图片数据
//        System.out.println(dataPrix);
        if("\"data:image/jpg;".equalsIgnoreCase(dataPrix)){
            suffix = ".jpg";
        }else if("\"[data:image/x-icon;".equalsIgnoreCase(dataPrix)){
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        }else if("\"data:image/gif;".equalsIgnoreCase(dataPrix)){
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        }else if("\"data:image/png;".equalsIgnoreCase(dataPrix)){
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else {
            return R.error("上传图片格式不合法");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String tempFileName=uuid+suffix;

        String path = System.getProperty("user.dir");
        File dest = new File(path+"/images",tempFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        String imgFilePath = dest.getAbsolutePath();//新生成的图片
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(data);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            @Cleanup OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            String imgurl=url+"images/"+tempFileName;
            return R.ok().put("path",imgurl);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("上传图片失败");
        }
    }
}
