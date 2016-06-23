package com.jeffcaijf.playrpc.core.rpc;

import java.io.Serializable;

/**
 * Created by jeff on 6/19/16.
 */
public class DefaultResponse implements Response, Serializable {

    private Object value;

    public DefaultResponse(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
