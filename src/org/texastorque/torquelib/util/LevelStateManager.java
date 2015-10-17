package org.texastorque.torquelib.util;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.texastorque20155.feedback.Feedback;

public class LevelStateManager {

    private static double setpoint = Constants.E_UP_POSITION.getDouble();
    private static boolean stabilized = false;
    private static boolean skipFlag = false;
    private static double skipLastTime = 0.0;
    private static boolean finishFlag = false;

    private static boolean prevBottom;

    private static boolean top;
    private static boolean middle;
    private static boolean bottom;

    private static boolean can = false;
    private static int timesTriggered = 0;

    private static double lastCommandTime = 0.0;
    private static double lastTriggerTime = 0.0;
    private static double sixStackInit = 0.0;

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
    
    public static void reset() {
        prevBottom = false;
        can = false;
        skipFlag = false;
        finishFlag = false;
        stabilized = false;
        timesTriggered = 0;
        setpoint = Constants.E_UP_POSITION.getDouble();
        lastCommandTime = 0.0;
        sixStackInit = 0.0;
        skipLastTime = 0.0;
    }

    public static void pushToDashboard() {
        SmartDashboard.putBoolean("Top Level", top);
        SmartDashboard.putBoolean("Middle Level", middle);
        SmartDashboard.putBoolean("Bottom Level", bottom);
    }

    public static double calc() {
        SmartDashboard.putBoolean("Top Level", top);
        SmartDashboard.putBoolean("Middle Level", middle);
        SmartDashboard.putBoolean("Bottom Level", bottom);
        SmartDashboard.putBoolean("Stack Stabilized", stabilized);
        SmartDashboard.putNumber("Times Triggered", timesTriggered);
        SmartDashboard.putBoolean("Can in Auto Stack", can);

        if (bottom != prevBottom && bottom) {
            if (lastTriggerTime == 0.0 || Timer.getFPGATimestamp() - lastTriggerTime > 2.0) {
                lastTriggerTime = Timer.getFPGATimestamp();
                timesTriggered++;
            }
        }
        if (!can && top && timesTriggered == 2) {//!can ?
            can = true;
            timesTriggered--;
            //stabilized = true;
        }
        if (bottom && timesTriggered < 6) {
            if (skipFlag && !finishFlag) {
                if (Timer.getFPGATimestamp() - skipLastTime > .5) {
                    setpoint = Constants.E_UP_POSITION.getDouble();
                    if (Timer.getFPGATimestamp() - skipLastTime > .75) {
                        stabilized = true;
                        skipFlag = false;
                        finishFlag = true;
                    }
                }
            } else {
                if (finishFlag) {
                    setpoint = Constants.E_UP_POSITION.getDouble();
                    lastCommandTime = Timer.getFPGATimestamp();
                    finishFlag = false;
                } else {
                    if (elevatorAtPosition(Constants.E_DOWN_POSITION.getDouble())) {
                        if (can && timesTriggered == 2 && !finishFlag) {
                            setpoint = Constants.E_SIX_POSITION.getDouble();
                            skipFlag = true;
                            skipLastTime = Timer.getFPGATimestamp();
                        } else {
                            setpoint = Constants.E_UP_POSITION.getDouble();
                            lastCommandTime = Timer.getFPGATimestamp();
                        }
                    } else {
                        if (Timer.getFPGATimestamp() - lastCommandTime > 1.2) {
                            setpoint = Constants.E_DOWN_POSITION.getDouble();
                        }
                    }
                }
            }
        }
        if (!can) {
            stabilized = timesTriggered >= 3;
        }
        if (timesTriggered >= 6) {
            if (sixStackInit == 0.0) {
                sixStackInit = Timer.getFPGATimestamp();
                setpoint = Constants.E_DOWN_POSITION.getDouble();
            } else if (Timer.getFPGATimestamp() - sixStackInit > 0.75) {
                setpoint = Constants.E_SIX_POSITION.getDouble();
            }
        }
        return setpoint;
    }

    public static boolean getStabilized() {
        return stabilized;
    }

    private static boolean elevatorAtPosition(double value) {
        return Math.abs(Feedback.getInstance().getElevatorPosition() - value) < 1.5;
    }
}
