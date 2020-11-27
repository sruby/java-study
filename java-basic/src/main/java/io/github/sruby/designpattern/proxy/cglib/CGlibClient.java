package io.github.sruby.designpattern.proxy.cglib;

import io.github.sruby.designpattern.proxy.dynamic.IDbQueryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author sruby on 2020-11-21 9:45
 */
@Slf4j
public class CGlibClient implements MethodInterceptor {
    private Object proxy;
    public Object getInstance(Object proxy) {
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.proxy.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("before");
        Object result = methodProxy.invoke(o, objects);
        log.info("after");
        return result;
    }

    public static void main(String[] args) {
         CGlibClient client = new CGlibClient();
        IDbQueryImpl instance = (IDbQueryImpl) client.getInstance(new IDbQueryImpl());
        instance.query();
    }
}
