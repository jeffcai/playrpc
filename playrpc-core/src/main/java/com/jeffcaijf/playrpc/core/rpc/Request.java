package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 6/16/16.
 */
public interface Request {

    String getInterfaceName();

    String getMethodName();

    Object[] getArguments();

    String getParameterTypes();

}
