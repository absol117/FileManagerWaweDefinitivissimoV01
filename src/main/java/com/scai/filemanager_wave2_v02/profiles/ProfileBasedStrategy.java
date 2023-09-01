package com.scai.filemanager_wave2_v02.profiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.function.Function;

@Slf4j
@Configuration
public class ProfileBasedStrategy {

    @Bean
    @Profile("dev")
    public GeneratorStrategy<String, String> devGenerator() {
        return (String t) -> {
            log.info("devGenerator {}", t);
            return null;
        };
    }

    @Bean
    @Profile("prod")
    public GeneratorStrategy<String, String> prodGenerator() {
        return (String t) -> {
            log.info("prodGenerator {}", t);
            return null;
        };
    }

    @Bean
    @Profile("test")
    public GeneratorStrategy<String, String> testGenerator() {
        return (String t) -> {
            log.info("testGenerator {}", t);
            return null;
        };
    }

    @FunctionalInterface
    public interface GeneratorStrategy<T, R> extends Function<T, R> { }

}
