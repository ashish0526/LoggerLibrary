package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoggerConfiguration {
    private DateTimeFormatter timestampFormat;
    private LogLevel logLevel;
    private boolean isAsync;
    private int bufferSize;
    private SinkType sinkType;
    private String sinkDetails;
    private LoggerType loggerType;
    // Add constructor and getters/setters

}