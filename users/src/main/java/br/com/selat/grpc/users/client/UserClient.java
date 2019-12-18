package br.com.selat.grpc.users.client;

import br.com.selat.grpc.users.User;
import br.com.selat.grpc.users.UserRequest;
import br.com.selat.grpc.users.UserResponse;
import br.com.selat.grpc.users.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

public class UserClient {

    private static Logger logger = Logger.getLogger(UserClient.class.getName());

    public static void main(String[] args){

        logger.info("gRPC client starting...");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000)
                .usePlaintext()
                .build();
        UserServiceGrpc.UserServiceBlockingStub client = UserServiceGrpc.newBlockingStub(channel);

        User tales = User.newBuilder()
                .setFirstName("Tales")
                .setLastName("Viegas")
                .build();

        UserRequest request = UserRequest.newBuilder()
                .setUser(tales)
                .build();

        UserResponse response = client.user(request);

        logger.info("Response is: " + response.getResult());
        logger.info("Shutting down channel");
        channel.shutdown();
    }
}
