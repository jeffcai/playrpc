package com.jeffcaijf.playrpc.core.rpc;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by jeff on 6/19/16.
 */
public class RefererTest {

    @Test
    public void request() {
        DefaultReferer<HelloService> referer = new DefaultReferer<HelloService>(HelloService.class, "127.0.0.1", 1234);

        HelloService service = (HelloService) Proxy.newProxyInstance(HelloService.class.getClassLoader(), new Class[]
                {HelloService.class}, new RefererInvocationHandler<HelloService>(referer, HelloService.class));

        service.hello("hi");
    }

}
