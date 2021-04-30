package plus.feifei.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.feifei.common.entity.BannerEntity;
import plus.feifei.common.utils.PageUtils;
import plus.feifei.common.utils.R;
import plus.feifei.data.service.BannerService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 *
 *
 * @author feifei
 * @email demo@gmail.com
 * @date 2021-01-25 10:42:17
 */
@Api()
@RestController
@RequestMapping("substance/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
     * 列表
     */
    @ApiOperation("获取列表的接口")
    @RequestMapping("/list")
    @RequiresPermissions("substance:banner:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bannerService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 修改显示状态
     */
    @PutMapping("/update/by/{id}/{status}")
    public R updateStatus(@PathVariable("id")Integer id,@PathVariable("status")Integer status){
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setId(id);
        bannerEntity.setStatus(status);
        bannerService.updateById(bannerEntity);
        return R.ok();
    }
    /**
     * 修改banner排序
     */
    @PutMapping("/update/by/{id}/{sort}/{type}")
    public R updateBa(@PathVariable("id")Integer id,@PathVariable("sort")Integer sort,@PathVariable("type")Integer type){
        if(id<0){
            id=0;
        }
        BannerEntity one = bannerService.getOne(new QueryWrapper<BannerEntity>().eq("sort", sort));
        int s = one.getSort();
        if(type==-1){
             s++;
        }
        if(type==1){
            s--;
        }
        one.setSort(s);
        bannerService.updateById(one);

        BannerEntity entity = new BannerEntity();
        entity.setId(id);
        entity.setSort(sort);
        bannerService.updateById(entity);
        return R.ok();
    }

    /**
     * 信息
     */
    @ApiOperation("获取信息的接口")
    @RequestMapping("/info/{id}")
    @RequiresPermissions("substance:banner:info")
    public R info(@PathVariable("id") Integer id){
		BannerEntity banner = bannerService.getById(id);

        return R.ok().put("banner", banner);
    }

    /**
     * 保存
     */
    @ApiOperation("保存的接口")
    @RequestMapping("/save")
    @RequiresPermissions("substance:banner:save")
    public R save(@RequestBody BannerEntity banner){
        int count = bannerService.count();
        banner.setSort(count);
        bannerService.save(banner);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("修改列表的接口")
    @RequestMapping("/update")
    @RequiresPermissions("substance:banner:update")
    public R update(@RequestBody BannerEntity banner){
		bannerService.updateById(banner);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除的接口")
    @RequestMapping("/delete")
    @RequiresPermissions("substance:banner:delete")
    public R delete(@RequestBody Integer[] ids){
		bannerService.removeByIds(Arrays.asList(ids));
        // 重组排序
        List<BannerEntity> sort = bannerService.list(new QueryWrapper<BannerEntity>().select("id","sort").orderByAsc("sort"));
        for (int i = 0; i < sort.size(); i++) {
            sort.get(i).setSort(i);
        }
        bannerService.saveOrUpdateBatch(sort);
        return R.ok();
    }

}
