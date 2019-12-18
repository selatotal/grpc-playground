package br.com.selat.grpc.users.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class UserServer {

    private static Logger logger = Logger.getLogger(UserServer.class.getName());

    public static void main(String[] args) throws InterruptedException, IOException {
        logger.info("gRPC userServer starting...");

        Server server = ServerBuilder.forPort(5000)
                .addService(new UserServiceImpl())
                .build();
        server.start();
        logger.info("gRPC userServer started");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Received shutdown");
            server.shutdown();
            logger.info("userServer stopped");
        }));

        server.awaitTermination();
    }
}
