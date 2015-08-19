package org.texastorque.texastorque20155.input;

import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.texastorque20155.feedback.Feedback;
import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueToggle;

public class HumanInput extends Input {

    private double X_DRIVE_MULTIPLIER;
    private double Y_DRIVE_MULTIPLIER;

    private GenericController driver;
    private GenericController operator;

    private TorqueToggle stabilizerToggle;
    private TorqueToggle canRakeToggle;
    
    private boolean wantStabilizer;

    public HumanInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.12);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.12);

        stabilizerToggle = new TorqueToggle();
        stabilizerToggle.set(false);
        
        canRakeToggle = new TorqueToggle();
    }

    @Override
    public void loadParams() {
        X_DRIVE_MULTIPLIER = Constants.XBOX_X_DRIVE_MULTIPLIER.getDouble();
        Y_DRIVE_MULTIPLIER = Constants.XBOX_Y_DRIVE_MULTIPLIER.getDouble();
    }

    @Override
    public void update() {
        //driver
        leftDriveSpeed = -driver.getLeftYAxis() * 0.7 + driver.getRightXAxis() * 0.7;
        rightDriveSpeed = -driver.getLeftYAxis() * 0.6 - driver.getRightXAxis() * 0.6;

        //operator
        if (operator.getLeftCenterButton()) {
            override = true;
        } else if (operator.getRightCenterButton()) {
            override = false;
        }

        leftIntakeSpeed = operator.getLeftYAxis() - operator.getLeftXAxis();
        rightIntakeSpeed = operator.getLeftYAxis() + operator.getLeftXAxis();

        elevatorSpeed = -operator.getRightYAxis();
        if (operator.getYButton()) {
            elevatorSetpoint = Constants.E_UP_POSITION.getDouble();
        } else if (operator.getBButton()) {
            elevatorSetpoint = Constants.E_DOWN_POSITION.getDouble();
        } else if (operator.getXButton()) {
            elevatorSetpoint = Constants.E_SIX_POSITION.getDouble();
        } else if (operator.getAButton()) {
            elevatorSetpoint = Constants.E_UP_POSITION.getDouble();
            wantStabilizer = true;
        }

        tailDown = operator.getLeftBumper();
        
        if (wantStabilizer && Feedback.getInstance().getElevatorPosition() >= 1.0) {
            stackStabilized = true;
        }
        if (operator.getLeftBumper()) {
            wantStabilizer = false;
            stackStabilized = false;
        }

        if (driver.getRightTrigger()) {
            stackStabilized = false;
            stackStabilized = false;
            wantStabilizer = false;
            elevatorSetpoint = Constants.E_DOWN_POSITION.getDouble();
        }
        
        canRakeToggle.calc(operator.getRightBumper());
        tailDown = canRakeToggle.get();
    }
}
