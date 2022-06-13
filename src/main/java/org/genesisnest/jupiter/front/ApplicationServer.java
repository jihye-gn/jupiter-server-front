package org.genesisnest.jupiter.front;

import org.genesisnest.jupiter.front.server.grpc.GRPCServer;

import java.io.IOException;

public class ApplicationServer {
    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final GRPCServer server = new GRPCServer();
        server.start();
        server.blockUntilShutdown();
    }
}
