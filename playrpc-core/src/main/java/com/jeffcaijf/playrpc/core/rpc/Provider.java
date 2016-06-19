package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 6/19/16.
 */
public interface Provider<T> {

    Response call(Request request) throws Exception;

}
