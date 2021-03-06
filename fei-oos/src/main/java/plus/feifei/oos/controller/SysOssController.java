

package plus.feifei.oos.controller;

import plus.feifei.common.utils.*;
import plus.feifei.common.validator.ValidatorUtils;
import plus.feifei.common.validator.group.AliyunGroup;
import plus.feifei.common.validator.group.LocalGroup;
import plus.feifei.common.validator.group.QcloudGroup;
import plus.feifei.common.validator.group.QiniuGroup;
import plus.feifei.oos.cloud.CloudStorageConfig;
import plus.feifei.oos.cloud.OSSFactory;
import plus.feifei.oos.entity.SysOssEntity;
import plus.feifei.oos.service.SysOssService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import plus.feifei.sys.service.SysConfigService;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 文件上传
 *
 *
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
	private FileDiskUtil fileDiskUtil;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysOssService.queryPage(params);

		return R.ok().put("page", page);
	}


    /**
     * 云存储配置信息
     */
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@PostMapping("/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}else  if(config.getType()==Constant.CloudService.LOCAL_CLOUD.getValue()){
			//校验本地存储数据
			ValidatorUtils.validateEntity(config, LocalGroup.class);
		}
        sysConfigService.updateValueByKey(KEY, JsonUtils.toJson(config));

		return R.ok();
	}

	/**
	 * ck编辑器图片上传接口
	 */
	@PostMapping("/upload/save/ckeditor")
	public R save(@RequestParam("upload") MultipartFile file){
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String url = OSSFactory.build().uploadMultipartFile(file, suffix);



		return R.ok().put("url", url).put("uploaded",true);
	}
	/**
	 * 上传文件
	 */
	@PostMapping("/upload")
	@RequiresPermissions("sys:oss:all")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String url = OSSFactory.build().uploadMultipartFile(file, suffix);

		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);

		return R.ok().put("url", url);
	}


	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public R delete(@RequestBody Long[] ids){
		sysOssService.removeByIds(Arrays.asList(ids));

		return R.ok();
	}

}
