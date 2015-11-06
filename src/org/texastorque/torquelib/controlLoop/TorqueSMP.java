package org.texastorque.torquelib.controlLoop;

import static java.lang.Math.*;

public class TorqueSMP {

    private double maxV, maxA;

    private double t1, t2, t3;
    private double c, k;

    public TorqueSMP(double mV, double mA) {
        maxV = mV;
        maxA = mA;
    }
    
    private double func(double x) {
        return (sin(k * x - PI / 2.0) + 1.0) / c;
    }
    
    private double sigma(double x) {
        return (-(t1 * maxV) / (2.0 * PI)) * cos((PI / t1) - (PI / 2)) + (1.0 / c) * x;
    }

    public void generate(double distance) {
        t1 = PI * maxV / (2.0 * maxA);
        c = 2.0 / maxV;
        k = PI / t1;
        
        t2 = sigma(distance) - t1;
        
        t3 = t2 + t1;
    }

    public double getVelocity(double time) {
        if (time < t1) {
            return func(time);
        } else if (time < t2) {
            return maxV;
        } else if (time < t3) {
            return -func(time) + maxV;
        }
        return 0.0;
    }
    
    public double getT3() {
        return t3;
    }
}
