package org.bluo.service.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.bluo.service.comment.pojo.BlinkComment;
import org.bluo.service.comment.pojo.vo.BlinkCommentVO;

import java.util.List;

/**
 * 动态评论 持久层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Mapper
public interface BlinkCommentMapper extends BaseMapper<BlinkComment> {
    List<BlinkCommentVO> selectPage(IPage<BlinkCommentVO> page, Integer blinkId);

    List<BlinkComment> selectByParentId(int parentId);

    List<Integer> selectChildCount(List<Integer> parentIds);

    List<BlinkComment> selectPartChild(List<Integer> parentIds);
}
