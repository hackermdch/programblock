package com.hacker.programblock;

import java.util.Map;

@SuppressWarnings("unused")
public interface ProgramFunction {
    void putArg(String key, Object val);

    Object getArg(String key);

    Map<String, Object> getArgs();

    Object getReturnValue();

    void setArgs(Map<String, Object> args);

    void execute() throws Exception;
}

