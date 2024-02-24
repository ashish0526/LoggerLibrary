package org.example.model;

import lombok.Getter;
import lombok.Setter;

import  java.util.*;
@Getter
@Setter
public class LoggerConfig {
    private final EnumMap<LogLevel, ISink> mapping = new EnumMap<LogLevel, ISink>(LogLevel.class);
    private LogLevel logLevel;

    public void mapLevelToSink(LogLevel level, ISink sink) {
        mapping.put(level, sink);
    }

    public ISink getSink(LogLevel level) {
        return mapping.get(level);
    }
}