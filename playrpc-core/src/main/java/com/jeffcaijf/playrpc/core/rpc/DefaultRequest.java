package com.jeffcaijf.playrpc.core.rpc;

import java.io.Serializable;

/**
 * Created by jeff on 6/16/16.
 */
public class DefaultRequest implements Request, Serializable {

    private String interfaceName;
    private String methodName;
    private String parameterTypes;
    private Object[] arguments;

    @Override
    public String getInterfaceName() {
        return interfaceName;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public String getParameterTypes() {
        return parameterTypes;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParameterTypes(String parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return interfaceName + "." + methodName + "(" + parameterTypes + ")" + " with args: " + arguments;
    }

}
