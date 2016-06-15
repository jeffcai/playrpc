package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 6/15/16.
 */
public class RPCConsumer {

    public static void main(String[] args) throws Exception {
        HelloService service = RPCFramework.refer(HelloService.class, "127.0.0.1", 1234);

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String hello = service.hello("World");
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }

}
