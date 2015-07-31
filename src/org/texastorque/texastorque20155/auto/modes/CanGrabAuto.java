package org.texastorque.texastorque20155.auto.modes;

import org.texastorque.texastorque20155.constants.Constants;

public class CanGrabAuto extends AutoMode {

    @Override
    public void run() {
        tailDown = true;
        
        runCommand(new DriveCommand(-1 * Constants.AUTO_CAN_GRAB_INCHES.getDouble()));
        
        pause(1.0);
        
        tailDown = false;
        
        runCommand(new DriveCommand(Constants.AUTO_CAN_GRAB_INCHES.getDouble()));
    }
    
}
