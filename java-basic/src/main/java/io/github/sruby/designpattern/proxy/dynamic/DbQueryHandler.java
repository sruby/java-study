package io.github.sruby.designpattern.proxy.dynamic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @date 2020/10/23 17:13
 */
@Slf4j
public class DbQueryHandler implements InvocationHandler {
    private IDbQuery iDbQuery;
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        log.info("invoke start");
        if (iDbQuery == null){
            iDbQuery = new IDbQueryImpl();
        }
        String query = iDbQuery.query();
        log.info("invoke end");
        return query+"proxy";
    }


    /**
     *
     * @return
     */
    public static IDbQuery createProxy(){
        Object newProxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IDbQuery.class}, new DbQueryHandler());
        return (IDbQuery) newProxyInstance;
    }
}
