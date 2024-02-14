package org.bluo.es.blog.listener;

import global.pojo.BlogDoc;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.bluo.es.blog.mapper.BlogMapper;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static org.bluo.common.rabbitmq.autoconfig.constant.RabbitMQConstant.*;

/**
 * 监听消息队列-博客
 *
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/14
 */
@Component
@Slf4j
public class RabbitMQMessageListener {

    @Resource
    private BlogMapper blogMapper;

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(name = BLOG_EXCHANGE, type = ExchangeTypes.TOPIC),
                    value = @Queue(BLOG_DELETE_QUEUE),
                    key = BLOG_DELETE_KEY
            )
    )
    public void delete(Long blogId) {
        log.debug("delete blog, id -> {}", blogId);
        blogMapper.deleteById(blogId);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(name = BLOG_EXCHANGE, type = ExchangeTypes.TOPIC),
                    value = @Queue(BLOG_ADD_QUEUE),
                    key = BLOG_ADD_KEY
            )
    )
    public void add(BlogDoc blogDoc) {
        log.debug("add blogDoc {}", blogDoc);
        blogMapper.save(blogDoc);
    }
}
