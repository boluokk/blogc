package org.bluo.service.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import global.pojo.BlogDoc;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.bluo.common.util.exception.impl.BusinessException;
import org.bluo.service.blog.config.BlogIPageImpl;
import org.bluo.service.blog.convert.BlogDocConvert;
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
import org.bluo.service.esblog.ESBlogClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.bluo.common.rabbitmq.autoconfig.constant.RabbitMQConstant.*;

/**
 * 博客 业务层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private BlogDocConvert blogDocConvert;
    @Resource
    private ESBlogClient esBlogClient;
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private TagMapper tagMapper;
    @Resource
    private TypeMapper typeMapper;
    @Resource
    private PreviewInfoMapper previewInfoMapper;
    private static final int pageSize = 10;

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

    /**
     * 添加
     *
     * @param blog 博客
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Blog blog) {
        int i = blogMapper.insert(blog);
        try {
            rabbitTemplate.convertAndSend(BLOG_EXCHANGE, BLOG_ADD_KEY, blogDocConvert.toBlogDoc(blog));
        } catch (Exception e) {
            log.warn("blog add fail, {}", e);
            throw new BusinessException("发送mq失败");
        }
        return i == 1;
    }

    /**
     * 删除
     *
     * @param blogId 博客ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long blogId) {
        // 删除博客
        int deleteBlog = blogMapper.deleteById(blogId);
        // 删除博客标签
        int deleteTag = tagMapper.deleteById(blogId);
        try {
            rabbitTemplate.convertAndSend(BLOG_EXCHANGE, BLOG_DELETE_KEY, blogId);
        } catch (Exception e) {
            log.warn("blog add fail, {}", e);
            throw new BusinessException("发送mq失败");
        }
        return deleteBlog == 1 && deleteTag >= 0;
    }

    /**
     * 关键字查询博客
     *
     * @param key     关键字
     * @param pageNum 页码
     * @return
     */
    @Override
    public List<Blog> search(String key, int pageNum) {
        List<BlogDoc> list = esBlogClient.getPage(key, key, pageNum, pageSize);
        if (ObjectUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        ArrayList<Integer> ids = new ArrayList<>();
        HashMap<Integer, BlogDoc> hash = new HashMap<>();
        list.forEach(item -> hash.put(item.getId(), item));
        // 获取内容
        List<Blog> contents = blogMapper.findByIds(ids);
        contents.forEach(item -> {
            BlogDoc blogDoc = hash.get(item.getId());
            if (ObjectUtil.isNotNull(blogDoc)) {
                item.setId(blogDoc.getId());
                item.setTitle(blogDoc.getTitle());
                item.setDescription(blogDoc.getDescription());
                item.setTypeId(blogDoc.getTypeId());
                item.setUserId(blogDoc.getUserId());
                item.setCreateTime(blogDoc.getCreateTime());
            }
        });

        return contents;
    }
}
