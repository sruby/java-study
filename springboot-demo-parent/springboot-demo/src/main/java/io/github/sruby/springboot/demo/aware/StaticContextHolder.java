package io.github.sruby.springboot.demo.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

/**
 * @date 2020/10/23 14:36
 */
@Service
public class StaticContextHolder implements BeanFactoryAware {
    private static BeanFactory CONTEXT_FACTORY;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        CONTEXT_FACTORY = beanFactory;
    }

    public static Object getBean(String beanName){
        return CONTEXT_FACTORY.getBean(beanName);
    }
}
