package com.jeffcaijf.playrpc.core.rpc;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by jeff on 6/19/16.
 */
public class ReflectUtil {

    public static final String PARAM_CLASS_SPLIT = ",";
    public static final String EMPTY_PARAM = "void";
    private static final ConcurrentMap<Class<?>, String> class2NameCache = new ConcurrentHashMap<Class<?>, String>();

    public static String getMethodParamDesc(Method method) {
        if (method.getParameterTypes() == null || method.getParameterTypes().length == 0) {
            return EMPTY_PARAM;
        }

        StringBuilder builder = new StringBuilder();

        Class<?>[] clzs = method.getParameterTypes();

        for (Class<?> clz : clzs) {
            String className = getName(clz);
            builder.append(className).append(PARAM_CLASS_SPLIT);
        }

        return builder.substring(0, builder.length() - 1);
    }

    public static String getName(Class<?> clz) {
        if (clz == null) {
            return null;
        }

        String className = class2NameCache.get(clz);

        if (className != null) {
            return className;
        }

        className = getNameWithoutCache(clz);

        // 与name2ClassCache同样道理，如果没有恶心的代码，这块内存大小应该可控
        class2NameCache.putIfAbsent(clz, className);

        return className;
    }

    private static String getNameWithoutCache(Class<?> clz) {
        if (!clz.isArray()) {
            return clz.getName();
        }

        StringBuilder sb = new StringBuilder();
        while (clz.isArray()) {
            sb.append("[]");
            clz = clz.getComponentType();
        }

        return clz.getName() + sb.toString();
    }

    public static String getMethodDesc(String methodName, String parameterTypes) {
        if (parameterTypes == null) {
            return methodName + "()";
        } else {
            return methodName + "(" + parameterTypes + ")";
        }
    }

    public static String getMethodDesc(Method method) {
        String methodParamDesc = getMethodParamDesc(method);
        return getMethodDesc(method.getName(), methodParamDesc);
    }

}
