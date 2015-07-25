package org.texastorque.texastorque20155.subsystem;

import org.texastorque.texastorque20155.auto.AutoManager;
import org.texastorque.texastorque20155.auto.AutoMode;
import org.texastorque.texastorque20155.feedback.Feedback;
import org.texastorque.texastorque20155.input.Input;
import org.texastorque.texastorque20155.output.Output;

public abstract class Subsystem {

    protected final Input input;
    protected final Feedback feedback;
    protected final Output output;
    protected final AutoMode mode;

    public Subsystem() {
        input = Input.getInstance();
        feedback = Feedback.getInstance();
        output = Output.getInstance();
        mode = AutoManager.getInstance().getAutoMode();
    }

    //load parameters (eg PID constants, speeds, timing)
    public abstract void loadParams();

    //put values to dashboard
    public abstract void pushToDashboard();

    //initialize control loops/misc to 0
    public abstract void init();

    //run the subsystem (every 1/100 of a second)
    public abstract void run();

    //send values to motors/pneumatics
    protected abstract void output();
}
