package demo.ssm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author 邝明山
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Employee对象", description="")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "用户姓名不能为空")
    @Size(min = 6,max = 18,message = "长度在6-18")
    @ApiModelProperty(value = "用户名称")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Max(value = 200,message = "最大值是200")
    @Min(value = 0,message = "最小值是0")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @NotNull(message = "性别不能为空")
    @Max(value = 1,message = "最大值是1")
    @Min(value = 0,message = "最小值是0")
    @ApiModelProperty(value = "性别，0女1男")
    private Integer sex;

    @NotBlank(message = "地址不能为空")
    @ApiModelProperty(value = "地址")
    private String address;

    @NotBlank(message = "电话不能为空")
    @ApiModelProperty(value = "电话")
    private String phone;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;


}
