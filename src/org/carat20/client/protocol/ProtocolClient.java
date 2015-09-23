package org.carat20.client.protocol;

import android.util.Log;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.carat20.client.thrift.CaratService;

class ProtocolClient {

    public static int SERVER_PORT = 8080;
    public static String SERVER_ADDRESS = "caratserver.cs.helsinki.fi";

    public static CaratService.Client open() throws TTransportException {
        TSocket soc = new TSocket(SERVER_ADDRESS, SERVER_PORT, 60000);
        Log.v("Carat", "Opening socket in ProtocolClient, socket is "+soc);
        TProtocol p = new TBinaryProtocol(soc, true, true);

        CaratService.Client instance = new CaratService.Client(p);
        if (!soc.isOpen()) {
            soc.open();
        }
        Log.v("Carat", "Opening socket, socket.isOpen is "+soc.isOpen());
        Log.v("Carat", "Returning socketed instance, instance is "+instance);
        return instance;
    }
}
