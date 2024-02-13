package org.bluo.service.blog.pojo.convert;

import cn.hutool.core.util.ObjectUtil;
import org.bluo.service.blog.pojo.Blog;
import org.bluo.service.blog.pojo.PreviewInfo;
import org.bluo.service.blog.pojo.Tag;
import org.bluo.service.blog.pojo.Type;
import org.bluo.service.blog.pojo.vo.BlogPreviewVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客预览 手动转换
 *
 * @author boluo
 * @date 2024/02/03
 */
public class BlogConvertImpl {
    public static List<BlogPreviewVO> toBlogPreviewVO(List<Blog> blogs,
                                                      List<Type> types, List<Tag> tags,
                                                      List<PreviewInfo> previewInfos) {
        if (ObjectUtil.isEmpty(blogs)) {
            return new ArrayList<>();
        }
        Map<Integer, BlogPreviewVO> hash = new HashMap<>();
        List<BlogPreviewVO> result = new ArrayList<>();

        // 填充 博客
        for (Blog blog : blogs) {
            BlogPreviewVO blogPreviewVO = new BlogPreviewVO();
            blogPreviewVO.setTitle(blog.getTitle());
            blogPreviewVO.setDescription(blog.getDescription());
            blogPreviewVO.setTypeId(blog.getTypeId());
            blogPreviewVO.setBlogId(blog.getId());
            blogPreviewVO.setUserId(blog.getUserId());
            blogPreviewVO.setCreateTime(blog.getCreateTime());
            result.add(blogPreviewVO);
            hash.put(blog.getId(), blogPreviewVO);
        }

        // 填充 博客类型
        if (ObjectUtil.isNotEmpty(types)) {
            for (BlogPreviewVO blogPreviewVO : result) {
                for (Type type : types) {
                    if (blogPreviewVO.getTypeId() == type.getId()) {
                        blogPreviewVO.setTypeName(type.getName());
                        break;
                    }
                }
            }
        }

        // 填充 博客标签
        if (ObjectUtil.isNotEmpty(tags)) {
            for (Tag tag : tags) {
                BlogPreviewVO blogPreviewVO = hash.get(tag.getBlogId());
                // 存在
                if (ObjectUtil.isNotEmpty(blogPreviewVO.getTagName())) {
                    blogPreviewVO.getTagName().add(tag.getName());
                } else {
                    List<String> firstTagsList = new ArrayList<>();
                    firstTagsList.add(tag.getName());
                    blogPreviewVO.setTagName(firstTagsList);
                }
            }
        }
        // 填充 一些点赞信息
        if (ObjectUtil.isNotEmpty(previewInfos)) {
            for (PreviewInfo previewInfo : previewInfos) {
                BlogPreviewVO blogPreviewVO = hash.get(previewInfo.getBlogId());
                blogPreviewVO.setLike(previewInfo.getLike());
                blogPreviewVO.setDislike(previewInfo.getDislike());
                blogPreviewVO.setAuthor(previewInfo.getAuthor());
                blogPreviewVO.setPreview(previewInfo.getPreview());
            }
        }
        return result;
    }
}
