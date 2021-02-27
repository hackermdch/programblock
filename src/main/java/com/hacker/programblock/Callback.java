package com.hacker.programblock;

import java.util.function.Consumer;

public class Callback implements Runnable {
    protected Consumer<Object> callback;
    protected ProgramFunction delegate;

    @Override
    public void run() {
        try {
            delegate.execute();
            if (callback != null)
                callback.accept(delegate.getReturnValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
