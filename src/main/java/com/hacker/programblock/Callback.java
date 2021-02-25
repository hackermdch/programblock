package com.hacker.programblock;

import java.util.function.Consumer;

public class Callback implements Runnable {
    protected Consumer<Object> callback;
    protected Runnable delegate;

    @Override
    public void run() {
        delegate.run();
        if (callback != null)
            callback.accept(((ProgramFunction) delegate).getReturnValue());
    }
}
