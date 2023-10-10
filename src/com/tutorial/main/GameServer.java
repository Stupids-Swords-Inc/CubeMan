package com.tutorial.main;

import java.io.IOException;

public class GameServer {
    private Server server;

    public GameServer() throws IOException {
        server = new Server();
        server.start();
        server.bind(54555, 54777);

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof MyNetworkData) {
                    MyNetworkData data = (MyNetworkData) object;
                    // Handle data received from clients
                }
            }
        });
    }

    // Add methods to send data to clients
}
