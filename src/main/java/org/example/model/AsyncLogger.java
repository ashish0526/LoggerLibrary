package org.example.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import lombok.Setter;

public class AsyncLogger extends Logger{

    private final BlockingQueue<LogEntry> logQueue;
    private volatile boolean isRunning;

    public AsyncLogger(LoggerConfiguration loggerConfiguration) {
        super(loggerConfiguration);
        this.logQueue = new LinkedBlockingQueue<>(loggerConfiguration.getBufferSize());
        this.isRunning = true;
        startAsyncLoggingThread();
    }

    private void startAsyncLoggingThread() {
        Thread asyncThread = new Thread(() -> {
            while (isRunning || !logQueue.isEmpty()) {
                try {
                    LogEntry logEntry = logQueue.take();
                    super.log(logEntry.getMessage(), logEntry.getLogLevel());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        asyncThread.setDaemon(true);
        asyncThread.start();
    }

    @Override
    public void log(String msg, LogLevel level) {
        try {
            logQueue.put(new LogEntry(msg, level));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stopAsyncLogging() {
        isRunning = false;
    }

    @Getter
    @Setter
    private static class LogEntry implements Comparable<LogEntry>{
        private final String message;
        private final LogLevel logLevel;
        private final LocalDateTime timestamp;

        public LogEntry(String message, LogLevel level) {
            this.message = message;
            this.logLevel = level;
            this.timestamp = LocalDateTime.now();
        }

        @Override
        public int compareTo(LogEntry other) {
            return this.timestamp.compareTo(other.timestamp);
        }

    }
}
