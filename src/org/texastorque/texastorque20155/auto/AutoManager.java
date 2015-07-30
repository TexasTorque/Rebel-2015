package org.texastorque.texastorque20155.auto;

import org.texastorque.texastorque20155.auto.modes.AutoMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.auto.modes.DoNothingAuto;
import org.texastorque.texastorque20155.auto.modes.DriveAuto;
import org.texastorque.texastorque20155.auto.modes.TestAuto;
import org.texastorque.texastorque20155.auto.modes.ThreeToteAuto;

public class AutoManager {

    private final int DRIVE_AUTO = 1;
    private final int THREE_TOTE_AUTO = 3;
    private final int TEST_AUTO = 100;

    private AutoMode mode;

    private AutoManager() {
        SmartDashboard.putNumber("Autonomous Mode", 0);
        SmartDashboard.putString("Running Auto Mode", "N/A");
    }

    public AutoMode createAutoMode() {
        int modeChoice = (int) SmartDashboard.getNumber("Autonomous Mode", 0);

        switch (modeChoice) {
            case DRIVE_AUTO:
                SmartDashboard.putString("Running Auto Mode", "Drive Auto");
                return mode = new DriveAuto();
            case THREE_TOTE_AUTO:
                SmartDashboard.putString("Running Auto Mode", "Three Tote Auto");
                return mode = new ThreeToteAuto();
            case TEST_AUTO:
                SmartDashboard.putString("Running Auto Mode", "Test Auto");
                return mode = new TestAuto();
            default:
                SmartDashboard.putString("Running Auto Mode", "Do Nothing Auto");
                return mode = new DoNothingAuto();
        }
    }

    public AutoMode getAutoMode() {
        return mode;
    }

    public void reset() {
        SmartDashboard.putString("Running Auto Mode", "N/A");
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
