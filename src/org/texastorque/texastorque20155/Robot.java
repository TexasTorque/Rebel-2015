package org.texastorque.texastorque20155;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.feedback.Feedback;
import org.texastorque.texastorque20155.input.Input;
import org.texastorque.texastorque20155.output.Output;
import org.texastorque.texastorque20155.subsystem.Drivebase;
import org.texastorque.torquelib.base.TorqueIterative;
import org.texastorque.torquelib.util.Parameters;

public class Robot extends TorqueIterative {

    private int numCycles;

    private Feedback feedback;
    private Input input;
    private Output output;

    private Drivebase drivebase;

    @Override
    public void robotInit() {
        Parameters.load();
        feedback = Feedback.getInstance();
        input = Input.getInstance();
        output = Output.getInstance();

        drivebase = Drivebase.getInstance();

        numCycles = 0;
    }

    @Override
    public void teleopInit() {
        loadParams();
        initSubsystems();

        numCycles = 0;
    }

    @Override
    public void teleopPeriodic() {
        //open
        input.update();
        feedback.update();

        //process
        drivebase.run();

        //close
        updateDashboard();
    }

    @Override
    public void disabledPeriodic() {
        updateDashboard();
    }

    private void initSubsystems() {
        drivebase.init();
    }

    private void loadParams() {
        drivebase.loadParams();
    }

    private void updateDashboard() {
        drivebase.pushToDashboard();
        feedback.pushToDashboard();
        SmartDashboard.putNumber("NumCycles", numCycles++);
    }
}
