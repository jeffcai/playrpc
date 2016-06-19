package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 6/19/16.
 */
public abstract class AbstractClient implements Client {

    protected String host;
    protected int port;

    public AbstractClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

}
