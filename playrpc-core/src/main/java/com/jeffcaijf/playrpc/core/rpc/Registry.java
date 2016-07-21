package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 7/21/16.
 */
public interface Registry {

    void register(String service, String serviceImpl);

    String getServiceImpl(String service);

}
