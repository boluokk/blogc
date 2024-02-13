package org.bluo.service.comment.pojo.vo;

import lombok.Data;
import org.bluo.service.comment.pojo.BlinkComment;

import java.util.Date;
import java.util.List;

/**
 * 动态评论VO
 *
 * @author boluo
 * @date 2024/02/01
 */
@Data
public class BlinkCommentVO {
    private int id;
    private int blinkId;
    private int parentId;
    private String content;
    private Date createTime;
    private int like;
    private int total;
    private List<BlinkComment> children;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public List<BlinkComment> getChildren() {
        return children;
    }

    public void setChildren(List<BlinkComment> children) {
        this.children = children;
    }
}
