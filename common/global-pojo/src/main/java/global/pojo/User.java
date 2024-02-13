package global.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户 实体
 *
 * @author boluo
 * @date 2024/01/27
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
}
