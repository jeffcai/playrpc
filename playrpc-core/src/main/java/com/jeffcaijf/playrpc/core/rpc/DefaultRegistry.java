package com.jeffcaijf.playrpc.core.rpc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jeff on 7/21/16.
 */
public class DefaultRegistry implements Registry {

    private static final Map<String, String> registry = new ConcurrentHashMap();

    @Override
    public void register(String service, String serviceImpl) {
        registry.put(service, serviceImpl);
    }

    @Override
    public String getServiceImpl(String service) {
        return registry.get(service);
    }

}
