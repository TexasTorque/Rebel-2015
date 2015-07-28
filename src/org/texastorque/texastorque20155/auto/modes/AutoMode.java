package org.texastorque.texastorque20155.auto.modes;

import org.texastorque.texastorque20155.auto.commands.AutoCommand;
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
    
    protected final void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }
}
