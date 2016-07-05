package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 7/5/16.
 */
public class NettyChannel extends AbstractClient {

    private NettyChannel nettyClient;

    public NettyChannel(String host, int port) {
        super(host, port);
        nettyClient = new NettyChannel(host, port);
    }

    @Override
    public Response request(Request request) throws Exception {

        return null;
    }

}
