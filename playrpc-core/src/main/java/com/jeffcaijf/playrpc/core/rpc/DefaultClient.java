package com.jeffcaijf.playrpc.core.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by jeff on 6/19/16.
 */
public class DefaultClient extends AbstractClient {

    public DefaultClient(String host, int port) {
        super(host, port);
    }

    @Override
    public Response request(Request request) throws Exception {
        Socket socket = new Socket(host, port);
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            try {
                output.writeUTF(request.getMethodName());
                output.writeObject(request.getParameterTypes());
                output.writeObject(request.getArguments());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                try {
                    return (Response) input.readObject();
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

}
