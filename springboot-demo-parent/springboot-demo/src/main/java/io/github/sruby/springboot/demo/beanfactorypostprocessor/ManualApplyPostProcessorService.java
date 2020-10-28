package io.github.sruby.springboot.demo.beanfactorypostprocessor;

import io.github.sruby.springboot.demo.service.ISpringService;
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
        //PropertyPlaceholderConfigurer is implemented BeanFactoryPostProcessor
        PropertyPlaceholderConfigurer postProcessor = new PropertyPlaceholderConfigurer();
        postProcessor.setLocations(new ClassPathResource("postprocessor\\pro.properties"));
        postProcessor.setBeanFactory(beanFactory);

        //TODO why cant read value of "postprocessor.key" in "postprocessor\\pro.properties"
        ISpringService iSpringService = (ISpringService) beanFactory.getBean("springService");
        iSpringService.out();
    }
}
