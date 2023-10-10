package com.tutorial.main;

import java.io.IOException;

public class GameClient {
    private Client client;

    public GameClient() {
        client = new Client();
        client.start();

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof MyNetworkData) {
                    MyNetworkData data = (MyNetworkData) object;
                    // Handle data received from the server
                }
            }
        });
    }

    public void connect(String address) throws IOException {
        client.connect(5000, address, 54555, 54777);
    }

    // Add methods to send data to the server
}
