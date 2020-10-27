package io.github.sruby.springboot.demo.reader;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @date 2020/10/26 11:20
 */
public class BeanDefinitionReaderDemo {
    public static void main(String[] args) {
        BeanDefinitionRegistry beanRegistry = new DefaultListableBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanRegistry);
        beanDefinitionReader.loadBeanDefinitions("");
    }
}
