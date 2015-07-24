package org.texastorque.texastorque20155.constants;

import org.texastorque.torquelib.util.Parameters;

public class Constants extends Parameters {

    //drivebase
    public static final Constant D_MAX_SPEED = new Constant("D_MAX_SPEED", 1.0);
    
    //intake
    public static final Constant I_MAX_SPEED = new Constant("I_MAX_SPEED", 1.0);

    static {
        load();
    }
}
