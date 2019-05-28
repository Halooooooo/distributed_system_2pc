package com.example.coordinate;

import com.example.coordinate.feigninterface.TwoPhaseCommit;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@Import(FeignClientsConfiguration.class)
@Configuration
public class DemoApplication {

    @Autowired
    private DiscoveryClient discoveryClient;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean("twoPhaseCommit1")
    public TwoPhaseCommit twoPhaseCommit1(){
        String url = discoveryClient.getInstances("participant-b").get(0).getUri().toString();
        return Feign.builder().contract(new SpringMvcContract())
                .target(TwoPhaseCommit.class, url);
    }
    @Bean("twoPhaseCommit2")
    public TwoPhaseCommit twoPhaseCommit2(){
        String url = discoveryClient.getInstances("participant-c").get(0).getUri().toString();

        return Feign.builder().contract(new SpringMvcContract())
                .target(TwoPhaseCommit.class, url);
    }

}
