package com.jeffcaijf.playrpc.core.rpc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeff on 6/19/16.
 */
public abstract class AbstractProvider<T> implements Provider<T> {

    protected Class<T> clz;
    protected Map<String, Method> methodMap = new HashMap<String, Method>();

    public AbstractProvider(Class clz) {
        this.clz = clz;

        initMethodMap(clz);
    }

    @Override
    public Response call(Request request) throws Exception {
        return invoke(request);
    }

    public abstract Response invoke(Request request) throws Exception;

    protected Method lookup(Request request) {
        String methodDesc = ReflectUtil.getMethodDesc(request.getMethodName(), request.getParameterTypes());

        return methodMap.get(methodDesc);
    }

    private void initMethodMap(Class<T> clz) {
        Method[] methods = clz.getMethods();

        for (Method method : methods) {
            String methodDesc = ReflectUtil.getMethodDesc(method);
            methodMap.put(methodDesc, method);
        }
    }

}
