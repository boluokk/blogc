package org.bluo.common.rabbitmq.autoconfig.constant;


/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/14
 */
public class RabbitMQConstant {
    public static final String BLOG_EXCHANGE = "blog.topic";
    public static final String BLOG_DELETE_KEY = "blog.delete";
    public static final String BLOG_DELETE_QUEUE = BLOG_DELETE_KEY + "es.queue";
    public static final String BLOG_ADD_KEY = "blog.add";
    public static final String BLOG_ADD_QUEUE = BLOG_ADD_KEY + "es.queue";
}
