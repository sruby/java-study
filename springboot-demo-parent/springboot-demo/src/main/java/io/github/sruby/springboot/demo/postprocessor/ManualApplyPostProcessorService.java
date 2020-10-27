package io.github.sruby.springboot.demo.postprocessor;

import io.github.sruby.springboot.demo.service.ISpringServbice;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @date 2020/10/27 11:09
 */
public class ManualApplyPostProcessorService {

    public static void main(String[] args) {
        ConfigurableListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("postprocessor\\beans.xml"));
        PropertyPlaceholderConfigurer postProcessor = new PropertyPlaceholderConfigurer();
        postProcessor.setLocations(new ClassPathResource("postprocessor\\pro.properties"));
        postProcessor.setBeanFactory(beanFactory);

        ISpringServbice iSpringServbice = (ISpringServbice) beanFactory.getBean("SpringServbice");
        iSpringServbice.out();
    }
}
