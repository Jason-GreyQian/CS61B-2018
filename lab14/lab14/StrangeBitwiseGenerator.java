package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }

    /**
     * Returns a number between -1 and 1
     * this waveform should start at -1.0 and linearly increase towards 1.0
     */
    @Override
    public double next() {
        state = state + 1;
        int weirdState = state & (state >> 7) % period;
        return normalize(weirdState);
    }

    /**
     * converts values between 0 and period - 1 to values between -1.0 and 1.0.
     */
    private double normalize(int value) {
        return value * 2.0 / period - 1.0;
    }
}
