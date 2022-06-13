package org.genesisnest.jupiter.front.service;

import lombok.extern.slf4j.Slf4j;
import org.genesisnest.jupiter.proto.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl extends LoginTestGrpc.LoginTestImplBase {
    @Override
    public void loginCall(LoginRequest request,
                          io.grpc.stub.StreamObserver<LoginResponse> responseObserver) {
        log.info("Req:Login[{}]", request.toString());

        LoginResponse loginResponse = LoginResponse.newBuilder().setResultCode(200).setResultMessage("OK").build();
        responseObserver.onNext(loginResponse);
        responseObserver.onCompleted();
    }
}
