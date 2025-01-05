package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private double factor;
    private int state;
    private int period;

    public AcceleratingSawToothGenerator(int period, double factor) {
        this.period = period;
        this.factor = factor;
        this.state = 0;
    }

    /**
     * Returns a number between -1 and 1
     */
    @Override
    public double next() {
        if (state == period) {
            state = 0;
            period = (int) (period * factor);
        }

        return normalize(state++);
    }

    /**
     * converts values between 0 and period - 1 to values between -1.0 and 1.0.
     */
    private double normalize(int value) {
        return value * 2.0 / period - 1.0;
    }
}
