package br.com.selat.grpc.users.server;

import br.com.selat.grpc.users.User;
import br.com.selat.grpc.users.UserRequest;
import br.com.selat.grpc.users.UserResponse;
import br.com.selat.grpc.users.UserServiceGrpc;
import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void user(UserRequest request, StreamObserver<UserResponse> responseObserver){
        User user = request.getUser();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String responseStr =  firstName + " " + lastName;

        UserResponse response = UserResponse.newBuilder()
                .setResult(responseStr)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}