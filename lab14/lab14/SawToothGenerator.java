package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int samples) {
        this.period = samples;
        this.state = 0;
    }

    /**
     * Returns a number between -1 and 1
     * this waveform should start at -1.0 and linearly increase towards 1.0
     */
    @Override
    public double next() {
        state = (state + 1) % period;
        return normalize(state);
    }

    /**
     * converts values between 0 and period - 1 to values between -1.0 and 1.0.
     */
    private double normalize(int value) {
        return value * 2.0 / period - 1.0;
    }
}
