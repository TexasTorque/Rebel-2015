
package org.texastorque.torquelib.util;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.texastorque20155.feedback.Feedback;

public class LevelStateManager {

    private static double setpoint = Constants.E_UP_POSITION.getDouble();
    private static boolean stabilized = false;

    private static boolean prevBottom;

    private static boolean top;
    private static boolean middle;
    private static boolean bottom;

    private static boolean can = false;
    private static int timesTriggered = 0;

    private static double lastCommandTime = 0.0;

    public static void passTopLevel(boolean triggered) {
        top = triggered;
    }

    public static void passMiddleLevel(boolean triggered) {
        middle = triggered;
    }

    public static void passBottomLevel(boolean triggered) {
        prevBottom = bottom;
        bottom = triggered;
    }

    public static double calc() {
        SmartDashboard.putBoolean("Top Level", top);
        SmartDashboard.putBoolean("Middle Level", middle);
        SmartDashboard.putBoolean("Bottom Level", bottom);
        SmartDashboard.putBoolean("Stack Stabilized", stabilized);
        SmartDashboard.putNumber("Times Triggered", timesTriggered);
        SmartDashboard.putBoolean("Can in Auto Stack", can);

        if (bottom != prevBottom && bottom == true) {
            timesTriggered++;
        }
        if (!can && top && timesTriggered == 1) {//!can ?
            can = true;
            timesTriggered--;
        }
        if (bottom && timesTriggered < 6) {
            if (elevatorAtPosition(Constants.E_DOWN_POSITION.getDouble())) {
                setpoint = Constants.E_UP_POSITION.getDouble();
                lastCommandTime = Timer.getFPGATimestamp();
            } else {
                if (Timer.getFPGATimestamp() - lastCommandTime > 1.0) {
                    setpoint = Constants.E_DOWN_POSITION.getDouble();
                }
            }
        }
        if (timesTriggered == 3) {
            stabilized = true;
        }
        if (timesTriggered >= 6) {
            setpoint = Constants.E_SIX_POSITION.getDouble();
        }
        return setpoint;
    }

    public static boolean getStabilized() {
        return stabilized;
    }

    private static boolean elevatorAtPosition(double value) {
        return Math.abs(Feedback.getInstance().getElevatorPosition() - value) < 3.0;
    }
}
