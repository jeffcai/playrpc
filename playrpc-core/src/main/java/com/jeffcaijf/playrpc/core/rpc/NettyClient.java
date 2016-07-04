package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 7/5/16.
 */
public class NettyClient extends AbstractClient {

    private NettyClient nettyClient;

    public NettyClient(String host, int port) {
        super(host, port);
        nettyClient = new NettyClient(host, port);
    }

    @Override
    public Response request(Request request) throws Exception {

        return null;
    }

}
