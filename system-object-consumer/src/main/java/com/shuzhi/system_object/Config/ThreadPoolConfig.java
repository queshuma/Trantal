package com.shuzhi.system_object.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/10/22
 *
 * @version 1.0
 */

@Configuration
@EnableAsync
public class ThreadPoolConfig {
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 核心线程数
        executor.setMaxPoolSize(10); // 最大线程数
        executor.setQueueCapacity(25); // 队列容量
        executor.setThreadNamePrefix("MyThreadPool-"); // 线程名称前缀
        executor.setKeepAliveSeconds(60); // 非核心线程的空闲时间
        executor.setAllowCoreThreadTimeOut(true); // 允许核心线程超时
        executor.setWaitForTasksToCompleteOnShutdown(true); // 等待所有任务完成后再关闭线程池
        executor.setAwaitTerminationSeconds(60); // 在关闭线程池前等待的最长时间
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略

        executor.initialize(); // 初始化线程池
        return executor;
    }
}