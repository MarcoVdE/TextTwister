package com.google.android.instantapps.samples.hello.feature;

public class Timer {

    private double startingTime;
    private double remainingTime;

    public Timer(double startingTime) {
        this.startingTime = startingTime;
        this.remainingTime = startingTime;
    }

    public double getStartingTime() {
        return startingTime;
    }

    public double getRemainingTime() {
        return remainingTime;
    }


    private void updateTime() {
        this.remainingTime =- 0.1;
    }
}
