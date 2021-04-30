package plus.feifei.common.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author feifei
 * @email demo@gmail.com
 * @date 2021-01-25 10:42:17
 */
@Data
@TableName("tb_banner")
@ApiModel("tb_banner")
public class BannerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty("")
    @TableId
    private Integer id;
    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String path;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;
    /**
     * 显示状态
     */
    @ApiModelProperty("显示状态")
    private Integer status;
    /**
     * 排序字段
     */
    @ApiModelProperty("排序字段")
    private Integer sort;
    /**
     * 参数
     */
    private String param;

}
