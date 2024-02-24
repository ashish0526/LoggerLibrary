package org.example.model;

import java.time.format.DateTimeFormatter;

public class LogClient {

    public static void main(String[] args) {
        // Example Usage
        LoggerConfig loggerConfig = new LoggerConfig();
        loggerConfig.setLogLevel(LogLevel.Warn);

        loggerConfig.mapLevelToSink(LogLevel.Info, new ConsoleLoglink());
        loggerConfig.mapLevelToSink(LogLevel.Warn, new FileSink("log.txt"));
        loggerConfig.mapLevelToSink(LogLevel.Error, new DatabaseLink(/* provide database session */));

        LoggerConfiguration loggerConfiguration = new LoggerConfiguration(DateTimeFormatter.ISO_LOCAL_DATE_TIME,
               LogLevel.Error, true, 64, SinkType.STDOUT, "output to console",
                LoggerType.ASYNC);
        Logger logger = new Logger(loggerConfiguration);

        // Logging example
        logger.log("This is an information message", LogLevel.Info);
        logger.log("This is a warning message", LogLevel.Warn);
        logger.log("This is an error message", LogLevel.Error);
    }
}
