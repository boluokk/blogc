package org.bluo.service.blink.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 动态浏览 实体
 *
 * @author boluo
 * @date 2024/01/30
 */
@Data
@TableName("preview_info")
public class PreViewInfo {
    @TableId(type = IdType.AUTO)
    private int id;
    private int blinkId;
    private int preview;
    private int like;
    private int dislike;
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlinkId() {
        return blinkId;
    }

    public void setBlinkId(int blinkId) {
        this.blinkId = blinkId;
    }

    public int getPreview() {
        return preview;
    }

    public void setPreview(int preview) {
        this.preview = preview;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
