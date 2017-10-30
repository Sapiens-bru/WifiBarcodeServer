package ru.icash24.wifibarcodeserver;

import com.sun.net.httpserver.*;
import com.sun.net.httpserver.Authenticator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Enumeration;


public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(4242), 0);

        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            if ( n.isLoopback() || ! n.isUp())
                continue;
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                if (i instanceof Inet6Address) continue;
                System.out.println("");
                System.out.println("IP Adress: "+ i.getHostAddress());
            }
        }
        HttpContext context = server.createContext("/", new EchoHandler());
        context.setAuthenticator(new Auth());
        server.setExecutor(null);
        server.start();

    }

    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            RobotExp.type(exchange.getRequestURI().toString());
            exchange.sendResponseHeaders(200, 0);
            String response = "Barcode server ready";
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }
    }

    static class Auth extends Authenticator {
        @Override
        public Result authenticate(HttpExchange httpExchange) {
             return new Success(new HttpPrincipal("", ""));
        }
    }
}