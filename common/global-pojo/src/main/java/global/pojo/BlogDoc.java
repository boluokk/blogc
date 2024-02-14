package global.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/13
 */

@Data
@Document(indexName = "blog")
public class BlogDoc {
    @Id
    private int id;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String title;
    @Field
    private int userId;
    @Field
    private int typeId;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String description;
    @Field(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
