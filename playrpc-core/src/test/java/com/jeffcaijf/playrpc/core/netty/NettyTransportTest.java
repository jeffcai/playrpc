package com.jeffcaijf.playrpc.core.netty;

import com.jeffcaijf.playrpc.core.rpc.*;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by jeff on 7/21/16.
 */
public class NettyTransportTest {

    @Test
    public void transport() throws Exception {
        Registry registry = new DefaultRegistry();
        registry.register("com.jeffcaijf.playrpc.core.rpc.HelloService", "com.jeffcaijf.playrpc.core.rpc.HelloServiceImpl");

        // TODO replace default client with netty client
        DefaultReferer<HelloService> referer = new DefaultReferer<HelloService>(HelloService.class, "127.0.0.1", 1234);

        HelloService service = (HelloService) Proxy.newProxyInstance(HelloService.class.getClassLoader(), new Class[]
                {HelloService.class}, new RefererInvocationHandler<HelloService>(referer, HelloService.class));



    }

}
