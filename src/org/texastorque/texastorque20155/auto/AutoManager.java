package org.texastorque.texastorque20155.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.auto.modes.DoNothingAuto;
import org.texastorque.texastorque20155.auto.modes.DriveAuto;

public class AutoManager {

    private final int DRIVE_AUTO = 9;

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
            default:
                SmartDashboard.putString("Running Auto Mode", "Do Nothing Auto");
                return mode = new DoNothingAuto();
        }
    }

    public AutoMode getAutoMode() {
        return mode;
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
