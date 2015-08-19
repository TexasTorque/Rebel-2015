package org.texastorque.texastorque20155.auto;

import org.texastorque.texastorque20155.auto.modes.AutoMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.auto.modes.CanGrabAuto;
import org.texastorque.texastorque20155.auto.modes.DoNothingAuto;
import org.texastorque.texastorque20155.auto.modes.DriveAuto;
import org.texastorque.texastorque20155.auto.modes.TestAuto;
import org.texastorque.texastorque20155.auto.modes.ThreeToteAuto;

public class AutoManager {

    private final int DRIVE_AUTO = 1;
    private final int THREE_TOTE_AUTO = 3;
    private final int CAN_AUTO = 4;
    private final int TEST_AUTO = 100;

    private AutoMode mode;

    private AutoManager() {
        SmartDashboard.putNumber("AutonomousMode", 0);
        SmartDashboard.putString("RunningAutoMode", "N/A");
    }

    public AutoMode createAutoMode() {
        int modeChoice = (int) SmartDashboard.getNumber("AutonomousMode", 0);

        switch (modeChoice) {
            case DRIVE_AUTO:
                SmartDashboard.putString("RunningAutoMode", "DriveAuto");
                return mode = new DriveAuto();
            case THREE_TOTE_AUTO:
                SmartDashboard.putString("RunningAutoMode", "ThreeToteAuto");
                return mode = new ThreeToteAuto();
            case TEST_AUTO:
                SmartDashboard.putString("RunningAutoMode", "TestAuto");
                return mode = new TestAuto();
            case CAN_AUTO:
                SmartDashboard.putString("RunningAutoMode", "CanAuto");
                return mode = new CanGrabAuto();
            default:
                SmartDashboard.putString("RunningAutoMode", "DoNothingAuto");
                return mode = new DoNothingAuto();
        }
    }

    public AutoMode getAutoMode() {
        return mode;
    }

    public void reset() {
        SmartDashboard.putString("RunningAutoMode", "N/A");
        mode = null;
    }

    //singleton
    private static AutoManager instance;

    public static AutoManager getInstance() {
        if (instance == null) {
            instance = new AutoManager();
        }
        return instance;
    }
}
