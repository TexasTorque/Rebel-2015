package org.texastorque.texastorque20155.auto;

import org.texastorque.texastorque20155.feedback.Feedback;
import org.texastorque.texastorque20155.output.Output;

public abstract class AutoMode implements Runnable {

    private Thread autoThread;
    protected Feedback feedback;
    protected Output output;

    protected double startTime;

    public AutoMode() {
        autoThread = AutoManager.getInstance().getAutoThread();
        feedback = Feedback.getInstance();
        output = Output.getInstance();
    }

    protected final void pause(double seconds) {
        try {
            autoThread.wait((long) (seconds / 1000.0));
        } catch (Exception e) {
        }
    }
}
