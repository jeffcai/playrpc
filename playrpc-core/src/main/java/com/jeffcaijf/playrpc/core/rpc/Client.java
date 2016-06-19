package com.jeffcaijf.playrpc.core.rpc;

import javax.print.attribute.standard.RequestingUserName;
import java.io.IOException;

/**
 * Created by jeff on 6/19/16.
 */
public interface Client {

    String getHost();

    int getPort();

    Response request(Request request) throws Exception;

}
