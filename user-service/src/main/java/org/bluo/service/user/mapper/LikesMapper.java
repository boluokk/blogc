package org.bluo.service.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.bluo.service.user.pojo.Likes;

import java.util.List;

/**
 * 用户收藏 持久层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Mapper
public interface LikesMapper extends BaseMapper<Likes> {
    List<Integer> getBlogIdByUserId(int userId);
}
