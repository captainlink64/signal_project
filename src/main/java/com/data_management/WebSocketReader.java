package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class WebSocketReader implements DataReader {
    private WebSocketClient client;
    private DataStorage dataStorage;
    private StringBuilder dataContent = new StringBuilder();
    private int lineNumber = 0;

    public WebSocketReader(String uri, DataStorage dataStorage) throws URISyntaxException {
        this.dataStorage = dataStorage;
        this.client = new CustomWebSocketClient(new URI(uri));
    }

    private class CustomWebSocketClient extends WebSocketClient {
        public CustomWebSocketClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onMessage(String message) {
            dataContent.append(message).append("\n");
        }

        @Override
        public void onOpen(ServerHandshake handshake) {
            System.out.println("WebSocket connection opened.");
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            System.out.println("WebSocket connection closed.");
        }

        @Override
        public void onError(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void connect() throws InterruptedException {
        client.connectBlocking();
    }

    public void disconnect() {
        client.close();
    }

    @Override
    public void readData(DataStorage dataStorage, String directoryPath) throws IOException {
        try (Reader reader = new StringReader(dataContent.toString())) {
            lineNumber = parseData(reader, dataStorage, lineNumber);
        }
    }

    @Override
    public int parseData(Reader reader, DataStorage dataStorage, int lineNumber) throws IOException {
        int charVal;
        StringBuilder dataString = new StringBuilder();
        while ((charVal = reader.read()) != -1) {
            dataString.append((char) charVal);
        }

        String[] lines = dataString.toString().split("\n");
        lines = Arrays.copyOfRange(lines, lineNumber, lines.length);

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 4) {
                throw new IOException("Invalid data format: too many properties");
            }
            int patientId = Integer.parseInt(parts[0]);
            long timestamp = Long.parseLong(parts[1]);
            String label = parts[2];
            double data = Double.parseDouble(parts[3].replace("%", ""));
            dataStorage.addPatientData(patientId, data, label, timestamp);
        }
        return lines.length;
    }
}
