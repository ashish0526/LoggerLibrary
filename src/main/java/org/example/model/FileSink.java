package org.example.model;

import java.io.*;

public class FileSink implements ISink {
    private final String path;
    private BufferedWriter writer;

    public FileSink(String path) {
        this.path = path;
        openFile();
    }

    private void openFile() {
        try {
            writer = new BufferedWriter(new FileWriter(path, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToLogFile(LogLevel level, String message) {
        try {
            writer.write(String.format("[%s] [%s] %s: %s%n", level,  java.time.LocalDateTime.now(), message));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeMessage(LogLevel level, String message) {
        writeToLogFile(level, message);
    }
}
