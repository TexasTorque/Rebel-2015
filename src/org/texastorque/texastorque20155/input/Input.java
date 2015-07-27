package org.texastorque.texastorque20155.input;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.torquelib.util.GenericController;

public class Input {

    private DriverStation ds;

    private GenericController driver;
    private GenericController operator;

    private double D_leftYAxis;
    private double D_rightXAxis;

    private boolean D_rightTrigger;

    private double O_rightXAxis;
    private double O_rightYAxis;
    private double O_leftYAxis;

    private boolean O_Y;
    private boolean O_B;
    private boolean O_X;
    private boolean O_A;

    private boolean O_leftBumper;

    private boolean override;

    private Input() {
        ds = DriverStation.getInstance();
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.12);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.12);
        override = false;
    }

    public void update() {
        D_leftYAxis = driver.getLeftYAxis();
        D_rightXAxis = driver.getRightXAxis();

        D_rightTrigger = driver.getRightTrigger();

        O_rightXAxis = operator.getRightXAxis();
        O_rightYAxis = operator.getRightYAxis();
        O_leftYAxis = operator.getLeftYAxis();

        O_Y = operator.getYButton();
        O_B = operator.getBButton();
        O_X = operator.getXButton();
        O_A = operator.getAButton();

        O_leftBumper = operator.getLeftBumper();

        if (operator.getLeftCenterButton()) {
            override = true;
        } else if (operator.getRightCenterButton()) {
            override = false;
        }
    }

    //numbers
    public double getLeftDriveSpeed() {
        return -D_leftYAxis + D_rightXAxis;
    }

    public double getRightDriveSpeed() {
        return -D_leftYAxis - D_rightXAxis;
    }

    public double getLeftIntakeSpeed() {
        return O_rightYAxis - O_rightXAxis;
    }

    public double getRightIntakeSpeed() {
        return O_rightYAxis + O_rightXAxis;
    }

    public double getElevatorOverrideSpeed() {
        return -O_leftYAxis;
    }

    //booleans
    public boolean getElevatorUp() {
        return O_Y;
    }

    public boolean getElevatorDown() {
        return O_B;
    }

    public boolean getToggleAutoStack() {
        return O_X;
    }

    public boolean getToggleCanHolder() {
        return O_A;
    }

    public boolean getTailDown() {
        return O_leftBumper;
    }

    public boolean getPlace() {
        return D_rightTrigger;
    }

    public boolean isAutonomous() {
        return ds.isAutonomous();
    }

    public boolean isOperatorControlled() {
        return ds.isOperatorControl();
    }

    public boolean inOverride() {
        return override;
    }

    public void pushToDashboard() {
        SmartDashboard.putBoolean("IsOperatorControl", isOperatorControlled());
        SmartDashboard.putBoolean("Overrides", inOverride());
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
