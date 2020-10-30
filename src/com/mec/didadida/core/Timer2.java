package com.mec.didadida.core;


public class Timer2 implements Runnable{
    private volatile boolean goon;
    private int dalayTime;
    private ITimeWorker2 timeWorker;
    private Object lock = new Object();
    private TimeWork timeWork;

    public int getDalayTime() {
        return dalayTime;
    }

    public void start() {
        goon = true;
        timeWork = new TimeWork();
        new Thread(this).start();

    }

    public Timer2 setTimeWorker(ITimeWorker2 timeWorker) {
        this.timeWorker = timeWorker;
        return this;
    }

    public Timer2 setDalayTime(int dalayTime) {
        this.dalayTime = dalayTime;
        return this;
    }

    @Override
    public void run() {
        try {
            while(goon) {
                synchronized (lock) {
                    lock.wait(dalayTime);
                    lock.notify();
                }
            }
        } catch (InterruptedException e) {
        } finally {
            goon = false;
        }
    }

    public void stop() {
        goon = false;
    }

    private class TimeWork implements Runnable {
        public TimeWork() {
            new Thread(this).start();
        }

        @Override
        public void run() {
            while (goon) {
                synchronized (lock) {
                    try {
                        lock.wait();
                        timeWorker.runTask();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
