package ca.bazlur.llmspring.config;

import ca.bazlur.llmspring.service.Assistant;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public Assistant assistant() {
        return message -> "Test response for: " + message;
    }
}
