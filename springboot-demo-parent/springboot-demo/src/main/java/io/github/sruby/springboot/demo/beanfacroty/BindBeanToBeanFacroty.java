package io.github.sruby.springboot.demo.beanfacroty;

import io.github.sruby.springboot.demo.service.ISpringService;
import io.github.sruby.springboot.demo.service.impl.SpringServiceImpl;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 *
 * @date 2020/10/26 10:51
 */
public class BindBeanToBeanFacroty {
    public static void main(String[] args) {
        AbstractBeanDefinition serBeanDefinition = new RootBeanDefinition(SpringServiceImpl.class);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("iSpringServbiceImpl",serBeanDefinition);

        ISpringService iSpringServiceImpl = (ISpringService) beanFactory.getBean("iSpringServbiceImpl");
        iSpringServiceImpl.out();
    }
}
