package org.texastorque.texastorque20155;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.ArrayList;
import org.texastorque.texastorque20155.feedback.Feedback;
import org.texastorque.texastorque20155.input.Input;
import org.texastorque.texastorque20155.output.Output;
import org.texastorque.texastorque20155.subsystem.Drivebase;
import org.texastorque.texastorque20155.subsystem.Elevator;
import org.texastorque.texastorque20155.subsystem.Intake;
import org.texastorque.texastorque20155.subsystem.Subsystem;
import org.texastorque.torquelib.base.TorqueIterative;
import org.texastorque.torquelib.util.Parameters;

public class Robot extends TorqueIterative {

    private int numCycles;

    private Feedback feedback;
    private Input input;

    private ArrayList<Subsystem> subsystems;

    @Override
    public void robotInit() {
        Parameters.load();
        feedback = Feedback.getInstance();
        input = Input.getInstance();

        subsystems = new ArrayList<>();
        subsystems.add(Drivebase.getInstance());
        subsystems.add(Intake.getInstance());
        subsystems.add(Elevator.getInstance());

        numCycles = 0;
    }

    @Override
    public void teleopInit() {
        subsystems.forEach((subsystem) -> {
            subsystem.init();
            subsystem.loadParams();
        });

        numCycles = 0;
    }

    @Override
    public void teleopPeriodic() {
        updateDashboard();
    }

    @Override
    public void teleopContinuous() {
        //open
        input.update();
        feedback.update();

        //process
        subsystems.forEach((subsystem) -> subsystem.run());
    }

    @Override
    public void disabledInit() {
        numCycles = 0;
    }

    @Override
    public void disabledPeriodic() {
        updateDashboard();
    }

    private void updateDashboard() {
        input.pushToDashboard();
        feedback.pushToDashboard();
        subsystems.forEach((subsystem) -> subsystem.pushToDashboard());
        SmartDashboard.putNumber("NumCycles", numCycles++);
    }
}
