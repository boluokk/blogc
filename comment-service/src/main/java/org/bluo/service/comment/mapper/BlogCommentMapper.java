package org.bluo.service.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluo.service.comment.pojo.BlogComment;
import org.bluo.service.comment.pojo.vo.BlogCommentVO;

import java.util.List;

/**
 * 博客评论 持久层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Mapper
public interface BlogCommentMapper extends BaseMapper<BlogComment> {
    List<BlogComment> selectByBlogId(@Param("blogId") int blogId);

    List<BlogComment> selectByParentId(IPage<BlogComment> page, @Param("parentId") int parentId);

    List<BlogComment> selectPartChild(@Param("parentIds") List<Integer> parentIds);

    List<Integer> selectChildCount(@Param("parentIds") List<Integer> parentIds);

    List<BlogCommentVO> selectPage(IPage<BlogCommentVO> page, @Param("blogId") int blogId);

}
