package graviwave;

class Clock {
    private double seconds;

    Clock(double seconds) {
        this.setSeconds(seconds);
    }

    void setSeconds(double seconds) {
        this.seconds = seconds % 60;
    }

    double getSeconds() {
        return seconds;
    }
}

