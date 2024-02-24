package org.example.model;

public class DatabaseLink implements  ISink{

        @Override
        public void writeMessage(LogLevel level, String message) {
            writeToDB(level, message);
        }

        private void writeToDB(LogLevel level, String message) {

        }
    }

