package com.jeffcaijf.playrpc.core.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jeff on 6/19/16.
 */
public class RefererInvocationHandler<T> implements InvocationHandler {

    private Referer referer;
    private Class<T> clz;

    public RefererInvocationHandler(Referer referer, Class<T> clz) {
        this.referer = referer;
        this.clz = clz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DefaultRequest request = new DefaultRequest();
        request.setInterfaceName(clz.getName());
        request.setMethodName(method.getName());
        request.setArguments(args);
        request.setParameterTypes(ReflectUtil.getMethodParamDesc(method));
        System.out.println("request: " + request);
        // TODO need implement ha strategy
        return referer.call(request).getValue();
    }

}
