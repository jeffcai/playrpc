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

    @Override
    public String getInterfaceName() {
        return null;
    }

    @Override
    public String getMethodName() {
        return null;
    }

    @Override
    public Object[] getArguments() {
        return new Object[0];
    }

    @Override
    public String getParameterTypes() {
        return this.parameterTypes;
    }

    public void setInterfanceName(String interfanceName) {
        this.interfanceName = interfanceName;
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
        return interfanceName + "." + methodName + "(" + parameterTypes + ")" + " with args: " + arguments;
    }

}
