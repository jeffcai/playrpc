package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 6/15/16.
 */
public class RPCProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RPCFramework.export(service, 1234);
    }

}
