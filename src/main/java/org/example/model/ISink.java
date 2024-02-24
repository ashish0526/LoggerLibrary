package org.example.model;

public interface ISink {
    void writeMessage(LogLevel level, String message);
}
