package ru.icash24.wifibarcodeserver;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.net.InetSocketAddress;


public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(4242), 0);

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
        }
    }

    static class Auth extends Authenticator {
        @Override
        public Result authenticate(HttpExchange httpExchange) {
             return new Success(new HttpPrincipal("", ""));
        }
    }
}