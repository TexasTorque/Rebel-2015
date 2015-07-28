package org.texastorque.texastorque20155.auto;

import org.texastorque.texastorque20155.input.Input;
import org.texastorque.texastorque20155.output.Output;

public abstract class AutoMode extends Input implements Runnable {

    protected Output output;//dead reckoning

    public AutoMode() {
        output = Output.getInstance();
    }

    @Override
    public void loadParams() {
        //method stub - won't do anything in auto
    }

    @Override
    public void update() {
        //method stub - won't do anything in auto
    }
}
