package com.mec.didadida.demo;

import com.mec.didadida.core.ITimeWorker;
import com.mec.didadida.core.ITimeWorker2;
import com.mec.didadida.core.Timer;
import com.mec.didadida.core.Timer2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        ITimeWorker timeWork = new ITimeWorker() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH时mm分ss秒SSS");

            @Override
            public void runTask() {
                Calendar instance = Calendar.getInstance();
                Date now = instance.getTime();
                String time = sdf.format(now);
                System.out.println(time);
            }
        };

        Timer timer = new Timer().setDalayTime(1000).setTimeWorker(timeWork);
        timer.start();

        Thread.sleep(10000);
        timer.stop();
    }
}
