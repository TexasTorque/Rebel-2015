package org.texastorque.texastorque20155.auto.modes;

import edu.wpi.first.wpilibj.Timer;
import org.texastorque.texastorque20155.auto.AutoCommand;
import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.texastorque20155.input.Input;

public abstract class AutoMode extends Input implements Runnable {

    public AutoMode() {
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

    public final class StackCommand extends AutoCommand {

        @Override
        public void run() {
            elevatorSetpoint = Constants.E_DOWN_POSITION.getDouble();
            while (!close(elevatorSetpoint, Constants.E_DOWN_POSITION.getDouble())) {
            }
            elevatorSetpoint = Constants.E_UP_POSITION.getDouble();
        }
    }
}
