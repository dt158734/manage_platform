package com.example.manage_platform.manage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration // 启动前加载配置
@PropertySource("classpath:redis-spring.properties") // 指定配置稳点地址
public class RedisServiceConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPass;

    @Value("${spring.redis.database}")
    private int redisDb;

    @Value("${spring.redis.timeout}")
    private String timeout;

    /*@Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${redis.task.config.maxTotal}")
    private int maxTotal;
    @Value("${redis.task.config.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${redis.task.config.minEvictableIdleTimeMillis}")
    private String minEvictableIdleTimeMillis;
    @Value("${redis.task.config.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;
    @Value("${redis.task.config.timeBetweenEvictionRunsMillis}")
    private String timeBetweenEvictionRunsMillis;
    @Value("${redis.task.config.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${redis.task.config.testWhileIdle}")
    private Boolean testWhileIdle;*/

    @Bean
    @Primary
    public RedisConnectionFactory taskConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxTotal(maxTotal);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis));
//        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis));
//        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
//        jedisPoolConfig.setTestWhileIdle(testWhileIdle);


        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig);

        connectionFactory.setPort(redisPort);
        connectionFactory.setHostName(redisHost);
        connectionFactory.setDatabase(redisDb);
//        connectionFactory.setPassword(redisPass);
        //配置连接池属性
        connectionFactory.setTimeout(Integer.parseInt(timeout));


        return connectionFactory;
    }

    @Bean
    public RedisTemplate taskRedisTemplate() {
        RedisTemplate template = new StringRedisTemplate();
//        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(taskConnectionFactory());
        return template;
    }

}