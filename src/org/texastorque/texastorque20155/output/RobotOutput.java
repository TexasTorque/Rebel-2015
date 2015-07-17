package org.texastorque.texastorque20155.output;

import edu.wpi.first.wpilibj.Victor;
import org.texastorque.texastorque20155.constants.Ports;
import org.texastorque.torquelib.component.TorqueMotor;

public class RobotOutput {

    private boolean outputEnabled;

    private TorqueMotor leftDriveMini;
    private TorqueMotor leftDriveCim;

    private TorqueMotor rightDriveMini;
    private TorqueMotor rightDriveCim;

    private TorqueMotor leftIntake;
    private TorqueMotor rightIntake;

    private RobotOutput() {
        leftDriveMini = new TorqueMotor(new Victor(Ports.LEFT_DRIVE_MINI_MOTOR_PORT), false, true);
        leftDriveCim = new TorqueMotor(new Victor(Ports.LEFT_DRIVE_CIM_MOTOR_PORT), false, true);

        rightDriveMini = new TorqueMotor(new Victor(Ports.RIGHT_DRIVE_MINI_MOTOR_PORT), true, true);
        rightDriveCim = new TorqueMotor(new Victor(Ports.RIGHT_DRIVE_CIM_MOTOR_PORT), true, true);

        leftIntake = new TorqueMotor(new Victor(Ports.LEFT_INTAKE_MOTOR_PORT), false, true);
        rightIntake = new TorqueMotor(new Victor(Ports.RIGHT_INTAKE_MOTOR_PORT), true, true);
    }

    public void setOutputEnabled(boolean enabled) {
        outputEnabled = enabled;
    }

    public void setDriveOutput(double left, double right) {
        if (!outputEnabled) {
            return;
        }
        leftDriveMini.set(left);
        leftDriveCim.set(left);
        rightDriveMini.set(right);
        rightDriveCim.set(right);
    }

    public void setIntakeOutput(double left, double right) {
        if (!outputEnabled) {
            return;
        }
        leftIntake.set(left);
        rightIntake.set(right);
    }

    //singleton
    private static RobotOutput instance = null;

    public static RobotOutput getInstance() {
        if (instance == null) {
            instance = new RobotOutput();
        }
        return instance;
    }
}
