package org.example.model;

public class ConsoleLoglink implements ISink {
        @Override
        public void writeMessage(LogLevel level,  String message) {
            writeToConsole(level, message);
        }

        private void writeToConsole(LogLevel level, String message) {
            System.out.printf("[%s] %s: %s%n", level, java.time.LocalDateTime.now(), message);
        }

}
