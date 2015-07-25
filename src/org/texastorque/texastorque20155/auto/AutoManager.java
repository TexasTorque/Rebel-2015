package org.texastorque.texastorque20155.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.auto.modes.DoNothingAuto;
import org.texastorque.texastorque20155.auto.modes.DriveAuto;

public class AutoManager {

    private final int DO_NOTHING_AUTO = 0;
    private final int DRIVE_AUTO = 9;

    private Thread autoThread;
    private AutoMode mode;

    private AutoManager() {
        SmartDashboard.putNumber("Autonomous Mode", 0);
    }

    public void init() {
        autoThread = new Thread(createAutoMode());
        autoThread.start();
    }

    private AutoMode createAutoMode() {
        int modeChoice = (int) SmartDashboard.getNumber("Autonomous Mode", 0);

        switch (modeChoice) {
            case DO_NOTHING_AUTO:
                return mode = DoNothingAuto.getInstance();
            case DRIVE_AUTO:
                return mode = DriveAuto.getInstance();
            default:
                return mode = DoNothingAuto.getInstance();
        }
    }

    public void destroy() {
        autoThread.interrupt();
    }

    public Thread getAutoThread() {
        return autoThread;
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
