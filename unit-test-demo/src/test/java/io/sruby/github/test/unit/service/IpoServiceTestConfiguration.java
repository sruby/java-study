package io.sruby.github.test.unit.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class IpoServiceTestConfiguration {
    @Bean
    @Primary
    public IpoCompanyService companyService() {
        return Mockito.mock(IpoCompanyService.class);
    }
}