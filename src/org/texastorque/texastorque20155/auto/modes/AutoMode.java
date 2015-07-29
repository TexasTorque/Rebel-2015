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

    public final class DriveCommand extends AutoCommand {

        private double distance;

        public DriveCommand(double distance_) {
            distance = distance_;
        }

        @Override
        public void run() {
            drivebaseSetpoint = distance;
            while (Math.abs(((feedback.getLeftDrivePosition() + feedback.getRightDrivePosition()) / 2.0) - distance) > 0.05);
        }
    }
}
