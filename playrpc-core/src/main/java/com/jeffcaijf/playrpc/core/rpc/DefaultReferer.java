package com.jeffcaijf.playrpc.core.rpc;

import java.lang.reflect.Proxy;

/**
 * Created by jeff on 6/19/16.
 */
public class DefaultReferer<T> extends AbstractReferer<T> {

    public DefaultReferer(Class<T> clz, String host, int port) {
        super(clz, host, port);
    }

    @Override
    public Response call(Request request) throws Exception {
        Client client = new DefaultClient(host, port);
        return client.request(request);
    }

}
