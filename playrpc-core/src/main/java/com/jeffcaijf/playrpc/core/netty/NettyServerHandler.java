package com.jeffcaijf.playrpc.core.netty;

import com.jeffcaijf.playrpc.core.rpc.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by jeff on 6/23/16.
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<Request> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
        Registry registry = new DefaultRegistry();
        String interfaceName = request.getInterfaceName();
        DefaultProvider provider = new DefaultProvider(interfaceName, Class.forName(registry.getServiceImpl(interfaceName)));
        Response result = provider.invoke(request);

        ctx.channel().writeAndFlush(new DefaultResponse(result));
    }


}
