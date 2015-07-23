package org.texastorque.texastorque20155;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.feedback.SensorFeedback;
import org.texastorque.texastorque20155.input.DriverInput;
import org.texastorque.texastorque20155.output.RobotOutput;
import org.texastorque.torquelib.base.TorqueIterative;
import org.texastorque.torquelib.util.Parameters;

public class Robot extends TorqueIterative {

    private int numCycles;

    private SensorFeedback feedback;
    private DriverInput input;
    private RobotOutput output;

    @Override
    public void robotInit() {
        Parameters.load();
        feedback = SensorFeedback.getInstance();
        input = DriverInput.getInstance();
        output = RobotOutput.getInstance();

        numCycles = 0;
    }

    @Override
    public void teleopPeriodic() {
        //open
        numCycles++;
        input.update();
        feedback.update();

        //process
        //close
        updateDashboard();
    }

    private void updateDashboard() {
        feedback.putToDashboard();
        SmartDashboard.putNumber("NumCycles", numCycles);
    }
}
