package com.jeffcaijf.playrpc.core.netty;

import com.jeffcaijf.playrpc.core.rpc.AbstractClient;
import com.jeffcaijf.playrpc.core.rpc.Request;
import com.jeffcaijf.playrpc.core.rpc.Response;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by jeff on 6/23/16.
 */
public class NettyClient extends AbstractClient {

    public static final int TIMEOUT = 3000;
    private String host;
    private int port;
    private Bootstrap bootstrap;
    private Map<String, Response> responseMap = new ConcurrentHashMap<>();

    public NettyClient(String host, int port) {
        super(host, port);
        this.host = host;
        this.port = port;
    }

    public void connect() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

//        try {
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT);
        final NettyClientHandler handler = new NettyClientHandler(this);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(handler);
            }

        });

//            ChannelFuture f = bootstrap.bind(host, port);
//            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//        }
    }

    @Override
    public Response request(Request request) throws Exception {
        ChannelFuture channelFuture = this.bootstrap.connect(new InetSocketAddress(this.host, this.port));
        boolean result = channelFuture.awaitUninterruptibly(TIMEOUT, TimeUnit.MILLISECONDS);
        boolean success = channelFuture.isSuccess();
        Response response = null;
        if (result && success) {
            ChannelFuture writeFuture = channelFuture.channel().writeAndFlush(request);
            boolean responseResult = writeFuture.awaitUninterruptibly(TIMEOUT, TimeUnit.MILLISECONDS);
            if (responseResult && writeFuture.isSuccess()) {
                writeFuture.addListener(getListener());
            }
            writeFuture.await();
        }
        // TODO key?
        return this.getResponseMap().get("key");
    }

    private ChannelFutureListener getListener() {
        return new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess() || future.isDone()) {
                    // 成功的调用

                } else {
                    // 失败的调用
                }
            }

        };
    }

    public Map<String, Response> getResponseMap() {
        return responseMap;
    }

}
