package com.mec.didadida.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Timer implements Runnable{
    private volatile boolean goon;
    private int dalayTime;
    private ITimeWorker timeWorker;
    private final ExecutorService theadPool;

    {
        theadPool = Executors.newFixedThreadPool(11);
    }

    public Timer() {
        dalayTime = 1000;
    }

    public int getDalayTime() {
        return dalayTime;
    }

    public void start() {
        goon = true;
        if (timeWorker == null) {
            System.out.println("Do Nothing");
            return;
        }

        new Thread(this).start();
    }

    public Timer setTimeWorker(ITimeWorker timeWorker) {
        this.timeWorker = timeWorker;
        return this;
    }

    public Timer setDalayTime(int dalayTime) {
        this.dalayTime = dalayTime;
        return this;
    }

    @Override
    public void run() {
        try {
            while(goon) {
                synchronized (this) {
                    theadPool.execute(timeWorker);
                    this.wait(dalayTime);
                }
            }
        } catch (InterruptedException ignored) {
        } finally {
            goon = false;
        }
        theadPool.shutdown();
    }

    public void stop() {
        goon = false;

    }

}
