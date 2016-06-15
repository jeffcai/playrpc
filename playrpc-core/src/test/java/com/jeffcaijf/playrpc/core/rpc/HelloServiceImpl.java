package com.jeffcaijf.playrpc.core.rpc;

/**
 * Created by jeff on 6/15/16.
 */
public class HelloServiceImpl implements HelloService {

    public String hello(String name) {
        return "Hello, " + name + "!";
    }

}
