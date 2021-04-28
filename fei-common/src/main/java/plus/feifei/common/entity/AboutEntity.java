package plus.feifei.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * 2 * @Author: feifei
 * 3 * @Date: 2021/4/24 11:35
 * 4
 */
@Data
@TableName("tb_about")
public class AboutEntity {
    @TableId
    private Integer id;
    private String content;
    private LocalDate updateTime;
}
