package io.sruby.github.skywalking.config;

import io.sruby.github.skywalking.intercceptor.TraceTagInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public TraceTagInterceptor traceTagInterceptor() {
        return new TraceTagInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.traceTagInterceptor()).addPathPatterns("/**");
    }

}
