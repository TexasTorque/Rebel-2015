package org.texastorque.texastorque20155.output;

import org.texastorque.texastorque20155.constants.Ports;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.texastorque.torquelib.component.TorqueMotor;

public class Output {

    private boolean outputEnabled;

    private TorqueMotor leftDriveCim;
    private TorqueMotor leftDriveMini;
    private TorqueMotor rightDriveCim;
    private TorqueMotor rightDriveMini;

    private TorqueMotor leftIntakeMotor;
    private TorqueMotor rightIntakeMotor;

    private TorqueMotor leftElevatorMotor;
    private TorqueMotor rightElevatorMotor;

//    private Solenoid canHolderSolenoid;
//    private Solenoid tailSolenoid;
    private Output() {
        leftDriveCim = new TorqueMotor(new VictorSP(Ports.LEFT_DRIVE_CIM_MOTOR_PORT), false);
        leftDriveMini = new TorqueMotor(new VictorSP(Ports.LEFT_DRIVE_MINI_MOTOR_PORT), false);
        rightDriveCim = new TorqueMotor(new VictorSP(Ports.RIGHT_DRIVE_CIM_MOTOR_PORT), true);
        rightDriveMini = new TorqueMotor(new VictorSP(Ports.RIGHT_DRIVE_MINI_MOTOR_PORT), true);

        leftIntakeMotor = new TorqueMotor(new VictorSP(Ports.LEFT_INTAKE_MOTOR_PORT), false);
        rightIntakeMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_INTAKE_MOTOR_PORT), true);

        leftElevatorMotor = new TorqueMotor(new VictorSP(Ports.LEFT_ELEVATOR_MOTOR_PORT), true);
        rightElevatorMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_ELEVATOR_MOTOR_PORT), false);

//        canHolderSolenoid = new Solenoid(Ports.CANHOLDER_SOLENOID_PORT);
//        tailSolenoid = new Solenoid(Ports.TAIL_SOLENOID_PORT);
        outputEnabled = true;
    }

    public void setDriveSpeed(double left, double right) {
        if (!outputEnabled) {
            leftDriveCim.set(0.0);
            leftDriveMini.set(0.0);
            rightDriveCim.set(0.0);
            rightDriveMini.set(0.0);
            return;
        }
        leftDriveCim.set(left);
        leftDriveMini.set(left);
        rightDriveCim.set(right);
        rightDriveMini.set(right);
    }

    public void setIntakeSpeed(double left, double right) {
        if (!outputEnabled) {
            leftIntakeMotor.set(0.0);
            rightIntakeMotor.set(0.0);
            return;
        }
        leftIntakeMotor.set(left);
        rightIntakeMotor.set(right);
    }

    public void setElevatorSpeed(double speed) {
        if (!outputEnabled) {
            leftElevatorMotor.set(0.0);
            rightElevatorMotor.set(0.0);
            return;
        }
        leftElevatorMotor.set(speed);
        rightElevatorMotor.set(speed);
    }

    public void setCanHolder(boolean on) {
//        if (!outputEnabled) {
//            canHolderSolenoid.set(false);
//            return;
//        }
//        canHolderSolenoid.set(on);
    }

    public void setTail(boolean down) {
//        if (!outputEnabled) {
//            tailSolenoid.set(false);
//            return;
//        }
//        tailSolenoid.set(down);
    }

    //singleton
    private static Output instance;

    public static Output getInstance() {
        if (instance == null) {
            instance = new Output();
        }
        return instance;
    }
}
