package cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 喻浩
 * @create 2020-05-26-6:48
 */
@SpringBootApplication
@EnableDiscoveryClient // 表明是一个Nacos客户端，该注解是 SpringCloud 提供的原生注解。
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
