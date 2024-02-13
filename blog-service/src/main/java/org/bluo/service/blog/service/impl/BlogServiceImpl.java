package org.bluo.service.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.service.blog.config.BlogIPageImpl;
import org.bluo.service.blog.mapper.BlogMapper;
import org.bluo.service.blog.mapper.PreviewInfoMapper;
import org.bluo.service.blog.mapper.TagMapper;
import org.bluo.service.blog.mapper.TypeMapper;
import org.bluo.service.blog.pojo.Blog;
import org.bluo.service.blog.pojo.PreviewInfo;
import org.bluo.service.blog.pojo.Tag;
import org.bluo.service.blog.pojo.Type;
import org.bluo.service.blog.pojo.convert.BlogConvertImpl;
import org.bluo.service.blog.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客 业务层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;
    @Resource
    private TagMapper tagMapper;
    @Resource
    private TypeMapper typeMapper;
    @Resource
    private PreviewInfoMapper previewInfoMapper;

    /**
     * 博客预览 分页
     *
     * @param pageNum 页码
     * @param userId  用户Id
     * @return
     */
    @Override
    public BlogIPageImpl getPreviewPage(int pageNum, int userId) {
        // 1、查询所有博客 title、description。。
        BlogIPageImpl blogIPage = new BlogIPageImpl(pageNum);
        List<Blog> blogPage = blogMapper.findPreviewPage(blogIPage, userId);
        if (ObjectUtil.isEmpty(blogPage)) {
            return blogIPage;
        }
        List<Integer> blogIds = new ArrayList<>();
        List<Integer> typeIds = new ArrayList<>();
        blogPage.forEach(blog -> {
            blogIds.add(blog.getId());
            typeIds.add(blog.getTypeId());
        });
        // 2、拼装tag
        List<Tag> tags = tagMapper.findByBlogIds(blogIds);
        // 3、拼装type
        List<Type> types = typeMapper.selectByIds(typeIds);
        // 4、一些统计信息
        List<PreviewInfo> previewInfos = previewInfoMapper.findByBlogIds(blogIds);
        // 5、转换
        blogIPage.setRecords(BlogConvertImpl.toBlogPreviewVO(blogPage, types, tags, previewInfos));
        return blogIPage;
    }

    /**
     * 获取博客
     *
     * @param blogId 博客Id
     * @return 博客
     */
    @Override
    public Blog findOne(int blogId) {
        return blogMapper.findOne(blogId);
    }
}
