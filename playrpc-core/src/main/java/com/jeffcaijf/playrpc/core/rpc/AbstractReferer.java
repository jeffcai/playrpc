package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 6/19/16.
 */
public abstract class AbstractReferer<T> implements Referer<T> {

    protected Class<T> clz;
    protected String host;
    protected int port;

    public AbstractReferer(Class<T> clz, String host, int port) {
        this.clz = clz;
        this.host = host;
        this.port = port;
    }

}
