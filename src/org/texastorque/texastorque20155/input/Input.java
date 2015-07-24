package org.texastorque.texastorque20155.input;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.logging.TorqueToggle;

public class Input {

    private DriverStation ds;

    private GenericController driver;
    private GenericController operator;

    private double D_leftYAxis;
    private double D_rightXAxis;

    private double O_rightXAxis;
    private double O_rightYAxis;
    private double O_leftYAxis;
    
    private boolean O_Y;
    private boolean O_B;

    private boolean overrides;

    private Input() {
        ds = DriverStation.getInstance();
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.15);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.15);
        overrides = false;
    }

    public void update() {
        D_leftYAxis = driver.getLeftYAxis();
        D_rightXAxis = driver.getRightXAxis();

        O_rightXAxis = operator.getRightXAxis();
        O_rightYAxis = operator.getRightYAxis();
        O_leftYAxis = operator.getLeftYAxis();
        
        O_Y = operator.getYButton();
        O_B = operator.getAButton();

        if (operator.getLeftCenterButton()) {
            overrides = false;
        } else if (operator.getRightCenterButton()) {
            overrides = true;
        }
    }

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
        return O_leftYAxis;
    }
    
    public boolean getElevatorUp() {
        return O_Y;
    }
    
    public boolean getElevatorDown() {
        return O_B;
    }

    public boolean isAutonomous() {
        return ds.isAutonomous();
    }

    public boolean isOperatorControlled() {
        return ds.isOperatorControl();
    }

    public boolean inOverride() {
        return overrides;
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
