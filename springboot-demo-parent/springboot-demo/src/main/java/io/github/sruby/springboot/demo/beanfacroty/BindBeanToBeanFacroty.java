package io.github.sruby.springboot.demo.beanfacroty;

import io.github.sruby.springboot.demo.service.ISpringServbice;
import io.github.sruby.springboot.demo.service.impl.SpringServbiceImpl;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 *
 * @date 2020/10/26 10:51
 */
public class BindBeanToBeanFacroty {
    public static void main(String[] args) {
        AbstractBeanDefinition serBeanDefinition = new RootBeanDefinition(SpringServbiceImpl.class);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("iSpringServbiceImpl",serBeanDefinition);

        ISpringServbice iSpringServbiceImpl = (ISpringServbice) beanFactory.getBean("iSpringServbiceImpl");
        iSpringServbiceImpl.out();
    }
}
