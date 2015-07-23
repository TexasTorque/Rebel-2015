package org.texastorque.texastorque20155.input;

import edu.wpi.first.wpilibj.DriverStation;
import org.texastorque.torquelib.util.GenericController;

public class Input {

    private DriverStation ds;

    private GenericController driver;
    private GenericController operator;

    private double D_leftYAxis;
    private double D_rightXAxis;

    private Input() {
        ds = DriverStation.getInstance();
        driver = new GenericController(1, GenericController.TYPE_XBOX, 0.1);
        operator = new GenericController(2, GenericController.TYPE_XBOX, 0.1);
    }

    public void update() {
        D_leftYAxis = driver.getLeftYAxis();
        D_rightXAxis = driver.getRightXAxis();
    }

    public double getLeftDriveSpeed() {
        return -D_leftYAxis + D_rightXAxis;
    }

    public double getRightDriveSpeed() {
        return -D_leftYAxis - D_rightXAxis;
    }

    public boolean isAutonomous() {
        return ds.isAutonomous();
    }

    public boolean isOperatorControlled() {
        return ds.isOperatorControl();
    }

    public boolean isActive() {
        return ds.isSysActive();
    }

    //singleton
    private static Input instance;

    public static Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }
        return instance;
    }
}
