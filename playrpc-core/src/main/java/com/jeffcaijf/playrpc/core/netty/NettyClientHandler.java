package com.jeffcaijf.playrpc.core.netty;

import com.jeffcaijf.playrpc.core.rpc.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by jeff on 6/23/16.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<Response> {

    private NettyClient client;

    public NettyClientHandler(NettyClient client) {
        this.client = client;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response response) throws Exception {
        // TODO key
        System.out.println("Response in Client handler: " + response);
        client.getResponseMap().put("key", response);
    }

}
