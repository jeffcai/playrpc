package com.jeffcaijf.playrpc.core.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jeff on 6/19/16.
 */
public class DefaultExporter<T> implements Exporter<T> {

    private Provider<T> provider;

    public DefaultExporter(Provider<T> provider) {
        this.provider = provider;
    }

    @Override
    public Provider<T> getProvider() {
        return provider;
    }

    @Override
    public void export(final T serviceImpl, int port) throws Exception {
        final ServerSocket server = new ServerSocket(port);
        for (; ; ) {
            try {
                final Socket socket = server.accept();
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            try {
                                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                                try {
//                                    input.readUTF();
                                    Request request = (Request) input.readObject();
                                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                    System.out.println("To respond to the request: " + request);
                                    try {
                                        output.writeObject(provider.call(request));
                                    } catch (Throwable t) {
                                        output.writeObject(t);
                                    } finally {
                                        output.close();
                                    }
                                } finally {
                                    input.close();
                                }
                            } finally {
                                socket.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
