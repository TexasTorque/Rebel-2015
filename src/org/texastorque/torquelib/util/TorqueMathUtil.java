package org.texastorque.torquelib.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TorqueMathUtil {

    public static double constrain(double value, double absMax) {
        if (value > absMax) {
            return absMax;
        } else if (value < -absMax) {
            return -absMax;
        } else {
            return value;
        }
    }
}
