package com.jeffcaijf.playrpc.core.rpc;

import org.junit.Test;

/**
 * Created by jeff on 6/23/16.
 */
public class ExporterTest {

    @Test
    public void export() throws Exception {
        HelloServiceImpl helloService = new HelloServiceImpl();
        Provider provider = new DefaultProvider(helloService, HelloServiceImpl.class);
        DefaultExporter exporter = new DefaultExporter(provider);

        exporter.export(helloService, 1234);
    }



}
