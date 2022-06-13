package org.genesisnest.jupiter.front.server.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.genesisnest.jupiter.front.service.LoginServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GRPCServer {
    private Server server;

    public void start() throws IOException {
        /* The port on which the server should run */
        int port = 10051;
        server = ServerBuilder.forPort(port)
                .addService(new LoginServiceImpl())
                .build()
                .start();
        log.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                log.error("*** shutting down gRPC server since JVM is shutting down");
                try {
                    GRPCServer.this.stop();
                } catch (InterruptedException e) {
                    log.error("", e);
                }
                log.error("*** server shut down");
            }
        });
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
