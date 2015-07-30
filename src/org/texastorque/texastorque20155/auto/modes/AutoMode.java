package org.texastorque.texastorque20155.auto.modes;

import edu.wpi.first.wpilibj.Timer;
import org.texastorque.texastorque20155.auto.AutoCommand;
import org.texastorque.texastorque20155.feedback.Feedback;
import org.texastorque.texastorque20155.input.Input;

public abstract class AutoMode extends Input implements Runnable {

    protected final Feedback feedback;

    public AutoMode() {
        feedback = Feedback.getInstance();
    }

    @Override
    public void loadParams() {
        //method stub - won't do anything in auto
    }

    @Override
    public void update() {
        //method stub - won't do anything in auto
    }

    protected final void runCommand(AutoCommand command) {
        command.run();
        while (!command.isDone());
    }

    protected final void pause(double seconds) {
        double start = Timer.getFPGATimestamp();
        while (Timer.getFPGATimestamp() < start + seconds) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }

    public final class ElevatorCommand extends AutoCommand {

        private double height;

        public ElevatorCommand(double height_) {
            height = height_;
        }

        @Override
        public boolean isDone() {
            return Math.abs(feedback.getElevatorPosition() - height) < 0.12;
        }

        @Override
        public void run() {
            override = false;
            elevatorSetpoint = height;
        }
    }

    public final class DriveCommand extends AutoCommand {

        private double distance;

        public DriveCommand(double distance_) {
            distance = distance_;
        }

        @Override
        public boolean isDone() {
            return Math.abs(((feedback.getLeftDrivePosition() + feedback.getRightDrivePosition()) / 2.0) - distance) < 1.0;
        }

        @Override
        public void run() {
            override = false;
            drivebaseSetpoint = distance;
        }
    }
}
