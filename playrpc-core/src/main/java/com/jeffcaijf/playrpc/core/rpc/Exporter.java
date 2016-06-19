package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 6/19/16.
 */
public interface Exporter<T> {

    Provider<T> getProvider();

    void export(T serviceImpl, int port) throws Exception;

}
