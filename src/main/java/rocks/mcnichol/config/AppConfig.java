package rocks.mcnichol.config;

import brave.propagation.CurrentTraceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    @Bean
    @Primary
    public CurrentTraceContext slf4jSpanLogger() {
        return CustomTraceContext.create();
    }
}