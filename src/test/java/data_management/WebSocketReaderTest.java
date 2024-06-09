package com.data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class WebSocketReaderTest {

    private WebSocketReader webSocketReader;
    private DataStorage dataStorage;

    @BeforeEach
    void setUp() throws URISyntaxException {
        dataStorage = new DataStorage(new Object()); // dummy variable
        webSocketReader = new WebSocketReader("ws://localhost:8080", dataStorage);
    }

    @Test
    void testConnect() throws InterruptedException, URISyntaxException, IOException {
        assertDoesNotThrow(() -> webSocketReader.connect());
        webSocketReader.disconnect();
    }

    @Test
    void testOnMessage() throws IOException {
        String message = "111,1620000000000,HeartRate,80\n";
        assertDoesNotThrow(() -> webSocketReader.parseData(new StringReader(message), dataStorage, 0));
        assertEquals(1, dataStorage.getRecords(111, 0, Long.MAX_VALUE).size());
        assertEquals(80.0, dataStorage.getRecords(111, 0, Long.MAX_VALUE).get(0).getMeasurementValue());
    }
}
