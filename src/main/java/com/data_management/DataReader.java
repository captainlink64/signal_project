package com.data_management;

import java.io.IOException;
import java.io.Reader;

public interface DataReader {
    void readData(DataStorage dataStorage, String directoryPath) throws IOException;
    int parseData(Reader reader, DataStorage dataStorage, int lineNumber) throws IOException;

}