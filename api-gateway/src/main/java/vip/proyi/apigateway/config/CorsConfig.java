package vip.proyi.apigateway.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        // 是否支持跨域
        config.setAllowCredentials(true);
        // 允许哪些原始域
        config.setAllowedOrigins(Arrays.asList("*"));
        // 允许的请求头
        config.setAllowedHeaders(Arrays.asList("*"));
        // 允许的请求方法
        config.setAllowedMethods(Arrays.asList("*"));
        // 缓存时间 对当前的跨域请求不再检查  300秒
        config.setMaxAge(300L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter();
    }
}
