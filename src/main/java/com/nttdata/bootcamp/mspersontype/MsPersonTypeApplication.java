package com.nttdata.bootcamp.mspersontype;

import com.nttdata.bootcamp.mspersontype.model.PersonType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
public class MsPersonTypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPersonTypeApplication.class, args);
    }

    @Bean
    public ReactiveRedisTemplate<String, PersonType> reactiveJsonPostRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {

        RedisSerializationContext<String, PersonType> serializationContext = RedisSerializationContext
                .<String, PersonType>newSerializationContext(new StringRedisSerializer())
                .hashKey(new StringRedisSerializer())
                .hashValue(new Jackson2JsonRedisSerializer<>(PersonType.class))
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

}
