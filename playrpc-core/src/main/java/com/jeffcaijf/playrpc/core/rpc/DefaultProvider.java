package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 6/19/16.
 */
public class DefaultProvider<T> extends AbstractProvider<T> {

    private T serviceImpl;

    public DefaultProvider(T serviceImpl, Class<T> clz) {
        super(clz);
        this.serviceImpl = serviceImpl;
    }

    @Override
    public Response invoke(Request request) throws Exception {
        Object result = lookup(request).invoke(serviceImpl, request.getArguments());
        return new DefaultResponse(result);
    }

}
