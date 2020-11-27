package io.github.sruby.designpattern.proxy.dynamic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @date 2020/10/23 17:13
 */
@Slf4j
public class DynamicAgent{
    static class  MyHandler  implements InvocationHandler {
        private Object proxy;

        public MyHandler(Object proxy) {
            this.proxy = proxy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
            log.info("dynamic invoke start");
            Object result = method.invoke(this.proxy, objects);
            log.info("dynamic invoke end");
            return result;
        }
    }


    /**
     *
     * @return
     */
    public static Object createProxy(Class interfaceClass,Object proxy){
        Object newProxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},
                new MyHandler(proxy));
        return  newProxyInstance;
    }
}
