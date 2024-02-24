package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Logger {
    private final LoggerConfig config;
    private final LoggerConfiguration loggerConfiguration;

    public Logger(LoggerConfiguration loggerConfiguration) {
        this.loggerConfiguration = loggerConfiguration;
        this.config = createLoggerConfig();
    }

    private LoggerConfig createLoggerConfig() {
        LoggerConfig loggerConfig = new LoggerConfig();

        // Setup log level
        loggerConfig.setLogLevel(loggerConfiguration.getLogLevel());

        // Setup sinks based on sink type
        switch (loggerConfiguration.getSinkType()) {
            case STDOUT:
                loggerConfig.mapLevelToSink(loggerConfiguration.getLogLevel(), new ConsoleLoglink());
                break;
            case FILE:
                loggerConfig.mapLevelToSink(loggerConfiguration.getLogLevel(), new FileSink(loggerConfiguration.getSinkDetails()));
                break;
            case DATABASE:
                // Assume DatabaseSink initialization with session
                loggerConfig.mapLevelToSink(loggerConfiguration.getLogLevel(), new DatabaseLink());
                break;
        }

        return loggerConfig;
    }

    public void log(String msg, LogLevel logLevel) {
        if (loggerConfiguration.getLogLevel().ordinal() >= config.getLogLevel().ordinal()) {
            if (loggerConfiguration.getLogLevel().ordinal() > logLevel.ordinal()) {
                LogLevel currentLevel = loggerConfiguration.getLogLevel();
                ISink sink = config.getSink(currentLevel);
                sink.writeMessage(currentLevel, msg);
            }
        }
    }
}
