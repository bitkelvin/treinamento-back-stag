package br.com.seniorsolution.estagiario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;

@Configuration
public class FeignConfig {
     public static final int CONNECT_TIME = 12000;
     public static final int READ_TIME = 12000;
     
     @Bean
     public Request.Options options() {
            return new Request.Options(CONNECT_TIME, READ_TIME);
     }
     
}
