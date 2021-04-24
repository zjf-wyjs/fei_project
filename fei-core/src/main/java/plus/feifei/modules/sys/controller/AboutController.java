package plus.feifei.modules.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.feifei.common.entity.AboutEntity;
import plus.feifei.common.utils.R;
import plus.feifei.data.service.AboutService;

import java.time.LocalDate;

/**
 * 2 * @Author: feifei
 * 3 * @Date: 2021/4/24 11:44
 * 4
 */
@RestController
@RequestMapping("sys/about")
public class AboutController extends AbstractController{
    @Autowired
    private AboutService aboutService;
    @RequiresPermissions("sys:config:save")
    @PostMapping("/save")
    public R save(@RequestBody AboutEntity entity){
        entity.setUpdateTime(LocalDate.now());
        entity.setId(1);
        aboutService.updateById(entity);
        return R.ok();
    }
    @RequiresPermissions("sys:config:info")
    @GetMapping("/info")
    public R info(){
        AboutEntity r = aboutService.getById(1);
        return R.ok().put("info",r);
    }
}
