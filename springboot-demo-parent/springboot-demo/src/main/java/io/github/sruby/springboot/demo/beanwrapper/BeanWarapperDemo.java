package io.github.sruby.springboot.demo.beanwrapper;

import io.github.sruby.springboot.demo.service.FxNewsProvider;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

/**
 * @author sruby
 * @date 2020/10/28 11:27
 */
public class BeanWarapperDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object provider = Class.forName("io.github.sruby.springboot.demo.service.FxNewsProvider").newInstance();
        Object dowJonesNewsListener = Class.forName("io.github.sruby.springboot.demo.service.DowJonesNewsListener").newInstance();
        Object dowjonesNewsPersister = Class.forName("io.github.sruby.springboot.demo.service.DowjonesNewsPersister").newInstance();

        BeanWrapper providerBeanWrapper = new BeanWrapperImpl(provider);
        providerBeanWrapper.setPropertyValue("dowJonesNewsListener",dowJonesNewsListener);
        providerBeanWrapper.setPropertyValue("dowjonesNewsPersister",dowjonesNewsPersister);

        Assert.isTrue(providerBeanWrapper.getWrappedInstance() instanceof FxNewsProvider,"");
    }
}
