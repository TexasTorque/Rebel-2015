package org.texastorque.texastorque20155.auto;

import org.texastorque.texastorque20155.input.Input;
import org.texastorque.texastorque20155.output.Output;

public abstract class AutoMode extends Input implements Runnable {

    protected Output output;//dead reckoning

    public AutoMode() {
        output = Output.getInstance();
    }
    
    public void loadParams() {
    }
}
