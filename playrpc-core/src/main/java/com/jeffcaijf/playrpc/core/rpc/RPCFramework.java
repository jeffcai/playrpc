package com.jeffcaijf.playrpc.core.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jeff on 6/15/16.
 */
public class RPCFramework {

    /**
     * Exports service
     *
     * @param service service impl
     * @param port    server port
     * @throws Exception
     */
    public static void export(final Object service, int port) throws Exception {
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
                                    String methodName = input.readUTF();
                                    Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                                    Object[] arguments = (Object[]) input.readObject();
                                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                    System.out.println("To invoke method: " + methodName + " of the service: "
                                            + service.getClass().getName() + " with parameters: " + parameterTypes
                                            + "with arguments: " + arguments);
                                    try {
                                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                                        Object result = method.invoke(service, arguments);
                                        output.writeObject(result);
                                    } catch (Throwable t) {
                                        output.writeObject(t);
                                    } finally {
                                        output.close();
                                        ;
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

    /**
     * Refers a service
     *
     * @param interafceClass service interface
     * @param host
     * @param port
     * @param <T> inerterface generic type
     * @return
     * @throws Exception
     */
    public static <T> T refer(final Class<T> interafceClass, final String host, final int port) throws Exception {
        return (T) Proxy.newProxyInstance(interafceClass.getClassLoader(), new Class<?>[]{interafceClass}, new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host, port);
                try {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                    try {
                        output.writeUTF(method.getName());
                        output.writeObject(method.getParameterTypes());
                        output.writeObject(args);
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                        try {
                            Object result = input.readObject();
                            return result;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
    }

}
