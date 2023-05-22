package com.evasionera.models;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    private int remainingTime;
    private TimerTask countdownTask;
    private OnCountdownListener countdownListener;

    public void start(int duration) {
        remainingTime = duration;
        Timer myTimer = new Timer();
        countdownTask = new TimerTask() {
            @Override
            public void run() {
                remainingTime--;
                if (countdownListener != null) {
                    countdownListener.onCountdown(remainingTime);
                }
                if (remainingTime == 0) {
                    stop();
                }
            }
        };
        myTimer.scheduleAtFixedRate(countdownTask, 1000, 1000);
    }

    public void stop() {
        if (countdownTask != null) {
            countdownTask.cancel();
        }
    }

    public void setOnCountdownListener(OnCountdownListener listener) {
        countdownListener = listener;
    }

    public interface OnCountdownListener {
        void onCountdown(int remainingTime);
    }
}