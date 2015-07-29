package org.texastorque.texastorque20155;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.ArrayList;
import org.texastorque.texastorque20155.auto.AutoManager;
import org.texastorque.texastorque20155.feedback.Feedback;
import org.texastorque.texastorque20155.input.HumanInput;
import org.texastorque.texastorque20155.input.Input;
import org.texastorque.texastorque20155.subsystem.Stabilizer;
import org.texastorque.texastorque20155.subsystem.Drivebase;
import org.texastorque.texastorque20155.subsystem.Elevator;
import org.texastorque.texastorque20155.subsystem.Intake;
import org.texastorque.texastorque20155.subsystem.Subsystem;
import org.texastorque.torquelib.base.TorqueIterative;
import org.texastorque.torquelib.util.Parameters;

public class Robot extends TorqueIterative {

    private int numCycles;

    private AutoManager autoManager;
    private Thread autoThread;
    private Input currentInput;

    private Feedback feedback;

    private ArrayList<Subsystem> subsystems;

    @Override
    public void robotInit() {
        Parameters.load();
        autoManager = AutoManager.getInstance();
        feedback = Feedback.getInstance();

        subsystems = new ArrayList<>();
        subsystems.add(Drivebase.getInstance());
        subsystems.add(Intake.getInstance());
        subsystems.add(Elevator.getInstance());
        subsystems.add(Stabilizer.getInstance());
    }

    @Override
    public void autonomousInit() {
        Parameters.load();
        autoManager.reset();
        currentInput = autoManager.createAutoMode();
        currentInput.loadParams();
        subsystems.forEach((subsystem) -> {
            subsystem.setInput(currentInput);
            subsystem.init();
        });

        numCycles = 0;

        autoThread = new Thread(autoManager.getAutoMode());
        autoThread.start();
    }

    @Override
    public void autonomousPeriodic() {
        updateDashboard();
    }

    @Override
    public void autonomousContinuous() {
        currentInput.update();
        feedback.update();

        subsystems.forEach((subsystem) -> subsystem.run());
    }

    @Override
    public void teleopInit() {
        if (autoThread != null) {
            autoThread.interrupt();
        }

        Parameters.load();
        if (currentInput == null) {
            currentInput = new HumanInput();
        }
        currentInput.loadParams();
        subsystems.forEach((subsystem) -> {
            subsystem.setInput(currentInput);
            subsystem.init();
        });

        numCycles = 0;
    }

    @Override
    public void teleopPeriodic() {
        updateDashboard();
    }

    @Override
    public void teleopContinuous() {
        currentInput.update();
        feedback.update();

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

    @Override
    public void disabledContinuous() {
        feedback.update();
    }

    private void updateDashboard() {
        subsystems.forEach((subsystem) -> subsystem.pushToDashboard());
        SmartDashboard.putNumber("NumCycles", numCycles++);
    }
}
