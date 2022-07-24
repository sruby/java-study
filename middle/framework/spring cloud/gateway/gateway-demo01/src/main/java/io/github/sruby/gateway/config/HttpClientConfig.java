package io.github.sruby.gateway.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import reactor.netty.http.client.HttpClient;

@Component
public class HttpClientConfig implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof HttpClient) {
			HttpClient client = (HttpClient) bean;
			return client.metrics(true, s -> s);
		}
		return bean;
	}
}