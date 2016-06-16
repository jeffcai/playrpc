package com.jeffcaijf.playrpc.core.rpc;

import java.io.Serializable;

/**
 * Created by jeff on 6/16/16.
 */
public class DefaultRequest implements Request, Serializable {

    private String interfanceName;
    private String methodName;
    private String parameterTypes;
    private Object[] arguments;

    public String getInterfaceName() {
        return null;
    }

    public String getMethodName() {
        return null;
    }

    public Object[] getArguments() {
        return new Object[0];
    }

    public Class<?>[] getParameterTypes() {
        return new Class<?>[0];
    }

}
