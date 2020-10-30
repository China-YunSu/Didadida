package com.mec.didadida.core;

public interface ITimeWorker extends Runnable {
    @Override
    default void run() {
        runTask();
    }
    void runTask();
}
