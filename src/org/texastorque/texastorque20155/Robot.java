package org.texastorque.texastorque20155;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.input.HumanInput;
import org.texastorque.texastorque20155.monitor.Monitor;
import org.texastorque.texastorque20155.output.RobotOutput;
import org.texastorque.texastorque20155.subsystem.Drivebase;
import org.texastorque.texastorque20155.subsystem.Intake;

public class Robot extends IterativeRobot {

    private Watchdog watchdog;
    private int numCycles;

    private HumanInput input;
    private Monitor monitor;
    private RobotOutput output;

    private Drivebase drivebase;
    private Intake intake;

    public void robotInit() {
        watchdog = Watchdog.getInstance();
        input = HumanInput.getInstance();
        monitor = Monitor.getInstance();
        output = RobotOutput.getInstance();

        output.setOutputEnabled(true);

        drivebase = new Drivebase();
        intake = new Intake();
    }

    public void autonomousInit() {
        drivebase.setSetpoint(100);
    }

    public void autonomousPeriodic() {
        //open
        numCycles++;
        watchdog.feed();
        monitor.update();

        //process
        drivebase.run();

        //close
        output.setDriveOutput(drivebase.getLeftSpeed(), drivebase.getRightSpeed());
        updateDashboard();
    }

    public void teleopPeriodic() {
        //open
        numCycles++;
        watchdog.feed();
        input.update();
        monitor.update();

        //process
        drivebase.setSpeed(input.getDriveLeftSpeed(), input.getDriveRightSpeed());
        intake.setSpeed(input.getIntakeLeftSpeed(), input.getIntakeRightSpeed());

        //close
        output.setIntakeOutput(intake.getLeftIntakeSpeed(), intake.getRightIntakeSpeed());
        output.setDriveOutput(drivebase.getLeftSpeed(), drivebase.getRightSpeed());

        updateDashboard();
    }

    private void updateDashboard() {
        drivebase.putToDashboard();
        monitor.putToDashboard();
        SmartDashboard.putNumber("NumCycles", numCycles);
    }
}
